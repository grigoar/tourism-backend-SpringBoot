package spring.licenta.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import spring.licenta.dto.CityDTO;
import spring.licenta.dto.HotelDTO;
import spring.licenta.entities.City;
import spring.licenta.entities.ImageModel;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.CityRepository;
import spring.licenta.repositories.ImageRepository;
import spring.licenta.services.CityService;
import spring.licenta.services.ImageService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ImageRepository imageRepository;
	//@Autowired
	//private UserService userService;

	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CityDTO> getAllCities() {
		
		return cityService.findAll();
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public CityDTO getCityById(@PathVariable("id") int id) {
		return cityService.findCityById(id);
	}

	// merge
//		@RequestMapping(value = "/insert", method = RequestMethod.POST)
//		public UserRepository insertUser(@RequestBody UserDTO userDTO) {
//			System.out.println("Poate se intampla ceva");
//			System.out.println("date user"+userDTO.getEmail()+userDTO.getFirstname()+userDTO.getSurname()+userDTO.getUsername()+userDTO.getTelephone()+userDTO.getPassword()+userDTO.getCity()+userDTO.getCountry());
//			return userService.create(userDTO);
//		}
//	//merge
//			@RequestMapping(value = "/insert", method = RequestMethod.POST)
//			public int inserCurrent(@RequestBody CurrentDTO currentDTO) {
//				System.out.println("Poate se intampla ceva");
//				System.out.println("date user"+currentDTO.getValue()+currentDTO.getComment());
//				return currentService.create(currentDTO);
//			}
	
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  @RequestMapping(value = "/insert", method = RequestMethod.POST) 
	  public City insertCity(@RequestBody CityDTO cityDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date city"+cityDTO.getId()+cityDTO.getName()+cityDTO.getCountry()); 
		  return cityService.create(cityDTO); 
	  }
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		@RequestMapping(value = "/edit", method = RequestMethod.PUT)
		public ResponseEntity<?> editCity(@RequestBody CityDTO cityDTO){
			//UserDTO user = userService.findUserById(id);
			System.out.println("Ajunge aici?1 edit city");
			//userDTO.setId(id);
			cityService.updateCity(cityDTO);
			return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
		}
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public void removeCity(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		CityDTO city = cityService.findCityById(id);
		if (city == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		cityService.deleteCityById(id);
		System.out.println("City sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	// adaugare imagini in functie de city_id
	@PostMapping("/upload/{city_id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ImageModel uplaodImage(@PathVariable("city_id") int city_id, @RequestParam("myFile") MultipartFile file) throws IOException {

       // ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0, city_id,0 );


      //  final ImageModel savedImage = imageRepository.save(img);

		ImageModel savedImage = imageService.createCityImages(file, city_id);
        System.out.println("Image saved");


        return savedImage;


    }
	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public ImageModel getCityImagesById(@PathVariable("id") int id) {
		return cityService.findCityImagesById(id);
	}
	@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
	public List<ImageModel> getAllCityImagesById(@PathVariable("id") int id) {
		return cityService.findAllCityImagesById(id);
	}
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value = "/images/remove/{id}", method = RequestMethod.DELETE)
	public void deleteImageById(@PathVariable("id") int id) {
		System.out.println("Am intrat pentru stergere");
		long imageID= (long)id;
		ImageModel image = imageRepository.findById(imageID);
		if (image == null) {
			System.out.println("Unable to delete image. Image with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere image");
		imageRepository.deleteById(imageID);
		System.out.println("Imagine stearsa");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
