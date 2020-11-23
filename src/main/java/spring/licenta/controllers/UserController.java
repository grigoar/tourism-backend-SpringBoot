package spring.licenta.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.licenta.config.JwtUtils;
import spring.licenta.dto.UserDTO;
import spring.licenta.entities.ERole;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Role;
import spring.licenta.entities.User;
import spring.licenta.jwt.JwtResponse;
import spring.licenta.jwt.LoginRequest;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.jwt.SignupRequest;
import spring.licenta.repositories.ImageRepository;
import spring.licenta.repositories.RoleRepository;
import spring.licenta.repositories.UserRepository;
import spring.licenta.services.EmailService;
import spring.licenta.services.ImageService;
import spring.licenta.services.UserDetailsImpl;
import spring.licenta.services.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserDetailsServiceImpl userService;

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	EmailService emailService;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private ImageService imageService;

	@Autowired
	private ImageRepository imageRepository;

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public UserDTO getUserById(@PathVariable("id") int id) {
		return userService.findUserById(id);
	}

	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public List<UserDTO> getAllUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public void removeUser(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		UserDTO user = userService.findUserById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		userService.deleteUserById(id);
		System.out.println("user sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		System.out.println("ceva date" + userDetails.getId() + userDetails.getUsername() + userDetails.getEmail());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getTelephone(), signUpRequest.getCity(),
				signUpRequest.getCountry(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), new Date());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signUpRequest) {

		User urole = userRepository.findById(signUpRequest.getId());
		System.out.println("ceva date user pentru update" + urole.getPassword() + urole.getUsername());
		signUpRequest.setUsername(urole.getUsername());
		signUpRequest.setPassword(urole.getPassword());
		signUpRequest.setCreated(urole.getCreated());
		// Create new user's account
		User user = new User(signUpRequest.getId(), signUpRequest.getName(), signUpRequest.getTelephone(),
				signUpRequest.getCity(), signUpRequest.getCountry(),
				// urole.getUsername(),
				signUpRequest.getUsername(), signUpRequest.getEmail(),
				// urole.getPassword(),
				// encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getPassword(),
				// urole.getCreated()
				signUpRequest.getCreated());

		user.setRoles(urole.getRoles());
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}

	@PutMapping("/updaterole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUserRole(@Valid @RequestBody SignupRequest signUpRequest) {

		User urole = userRepository.findById(signUpRequest.getId());
		System.out.println("ceva date user pentru update" + urole.getPassword() + urole.getUsername());
//		signUpRequest.setUsername(urole.getUsername());
//		signUpRequest.setPassword(urole.getPassword());
//		signUpRequest.setCreated(urole.getCreated());
		// Create new user's account
		User user = new User(urole.getId(), urole.getName(), urole.getTelephone(), urole.getCity(), urole.getCountry(),
				// urole.getUsername(),
				urole.getUsername(), urole.getEmail(),
				// urole.getPassword(),
				// encoder.encode(signUpRequest.getPassword()),
				urole.getPassword(), urole.getCreated()
		// signUpRequest.getCreated()
		);

		Set<String> strRoles = signUpRequest.getRole();
		System.out.println("signupRequest roles" + signUpRequest.getRole());
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(rol -> {
				switch (rol) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		try {
			emailService.sendmail(signUpRequest.getEmail());
		} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
	}

	// adaugare imagini in functie de city_id
	@PostMapping("/upload/{user_id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ImageModel uplaodImage(@PathVariable("user_id") int user_id, @RequestParam("myFile") MultipartFile file)
			throws IOException {

		// ImageModel img = new ImageModel(
		// file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0,
		// city_id,0 );

		// final ImageModel savedImage = imageRepository.save(img);
		System.out.println("Inainte de adaugare imagine user profil");
		ImageModel savedImage = imageService.createUserProfileImage(file, user_id);
		System.out.println("Image saved");

		return savedImage;

	}

	@RequestMapping(value = "/image/{user_id}", method = RequestMethod.GET)
	public ImageModel getCityImagesById(@PathVariable("user_id") int user_id) {
		return userService.findUserImageById(user_id);
	}

	/*
	 * @RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
	 * public List<ImageModel> getAllCityImagesById(@PathVariable("id") int id) {
	 * return cityService.findAllCityImagesById(id); }
	 */

}
