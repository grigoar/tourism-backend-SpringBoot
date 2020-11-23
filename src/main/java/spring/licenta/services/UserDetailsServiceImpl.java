package spring.licenta.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import spring.licenta.dto.UserDTO;
import spring.licenta.entities.ERole;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.User;
import spring.licenta.repositories.ImageRepository;
import spring.licenta.repositories.UserRepository;



@Service

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	
	

	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUsername(username)
//				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//
//		return UserDetailsImpl.build(user);
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return UserDetailsImpl.build(user);

	}
	
	public UserDTO findUserByIdSimplified(long userId) {
		User usr = userRepository.findById(userId);
		List<GrantedAuthority> authorities = usr.getRoles().stream()
				.map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
				.collect(Collectors.toList());	
		System.out.println("ceva roluri"+ authorities);	
		
		//List<String> roles=new ArrayList<>();
		/*
		 * switch (authorities) { case ERole.ROLE_USER: roles.add("user"); break; case
		 * ERole.ROLE_MODERATOR: roles.add("mod"); break; case ERole.ROLE_ADMIN:
		 * roles.add("admin"); break; }
		 */
		return new UserDTO(
				usr.getId(), 
				usr.getName(),
				usr.getUsername(), 
				usr.getEmail(),
				usr.getPassword(), 
				usr.getCity(),
				usr.getCountry(),
				usr.getTelephone(),
				authorities);
			
	}
	
	public UserDTO findUserById(long userId) {
		User usr = userRepository.findById(userId);
		List<GrantedAuthority> authorities = usr.getRoles().stream()
				.map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
				.collect(Collectors.toList());	
		System.out.println("ceva roluri"+ authorities);	
		return new UserDTO(
				usr.getId(), 
				usr.getName(),
				usr.getUsername(), 
				usr.getEmail(),
				usr.getPassword(), 
				usr.getCity(),
				usr.getCountry(),
				usr.getTelephone(),
				authorities);
		
				
	}
	
	public List<UserDTO> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDTO> toReturn = new ArrayList<UserDTO>();
		for (User user : users) {
			//String[] names = extractNames(user.getName());
			List<GrantedAuthority> authorities = user.getRoles().stream()
					.map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
					.collect(Collectors.toList());	
			UserDTO dto = new UserDTO(
					user.getId(), 
					user.getName(),
					user.getUsername(), 
					user.getEmail(),
					user.getPassword(), 
					user.getCity(),
					user.getCountry(),
					user.getTelephone(),
					authorities);
			toReturn.add(dto);
		}
		return toReturn;
	}
	
	public void deleteUserById(int id) {
		 System.out.println(id+" Incercam sa il stergem");
			userRepository.deleteById(id);
			System.out.println(id+" asta ii ID-ul");
		    }

	public ImageModel findUserImageById(int user_id) {
		int id=user_id;
		ImageModel imageModel = imageRepository.findUserImageById(id);
		if(imageModel !=null) {
			ImageModel imagenew = new ImageModel();
			
			imagenew.setId(imageModel.getId());
			imagenew.setName(imageModel.getName());
			imagenew.setPic(imageModel.getPic());
			imagenew.setHotel_id(imageModel.getHotel_id());
			imagenew.setRestaurant_id(imageModel.getRestaurant_id());
			imagenew.setCity_id(imageModel.getCity_id());
			imagenew.setUser_id(imageModel.getUser_id());
			imagenew.setTouristAttraction_id(imageModel.getTouristAttraction_id());
			
			return imagenew;
		}else {
			imageModel=imageRepository.findById(74L);
			ImageModel imagenew = new ImageModel();
			
			imagenew.setId(imageModel.getId());
			imagenew.setName(imageModel.getName());
			imagenew.setPic(imageModel.getPic());
			imagenew.setHotel_id(imageModel.getHotel_id());
			imagenew.setRestaurant_id(imageModel.getRestaurant_id());
			imagenew.setCity_id(imageModel.getCity_id());
			imagenew.setUser_id(imageModel.getUser_id());
			imagenew.setTouristAttraction_id(imageModel.getTouristAttraction_id());
			
			return imagenew;
		}
		
	}
}
