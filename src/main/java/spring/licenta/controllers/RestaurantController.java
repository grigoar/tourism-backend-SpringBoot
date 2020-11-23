package spring.licenta.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.licenta.dto.HotelDTO;
import spring.licenta.dto.RestaurantDTO;
import spring.licenta.entities.ImageModel;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.ImageRepository;
import spring.licenta.repositories.RestaurantRepository;
import spring.licenta.services.ImageService;
import spring.licenta.services.RestaurantService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<RestaurantDTO> getAllRestaurants() {
		return restaurantService.findAll();
		
	}
	
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<RestaurantDTO> getAllRestaurantsById(@PathVariable("id") int id) {
		System.out.println("cautam toate hotelurile dintr-un oras");
		return restaurantService.findAllRestaurantsById(id);
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public RestaurantDTO getRestaurantById(@PathVariable("id") int id) {
		return restaurantService.findRestaurantById(id);
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public RestaurantRepository insertRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date restaurant"+restaurantDTO.getId()+restaurantDTO.getName()+restaurantDTO.getAddress()+restaurantDTO.getDetails()+restaurantDTO.getContact()+restaurantDTO.getWebsite()+restaurantDTO.getCity_id()); 
		  return restaurantService.create(restaurantDTO); 
	  }
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editRestaurant(@RequestBody RestaurantDTO restaurantDTO){
		//UserDTO user = userService.findUserById(id);
		System.out.println("Ajunge aici?1 edit hotel");
		//userDTO.setId(id);
		restaurantService.updateRestaurant(restaurantDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeRestaurant(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		RestaurantDTO restaurant = restaurantService.findRestaurantById(id);
		if (restaurant == null) {
			System.out.println("Unable to delete. Restaurant with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		restaurantService.deleteRestaurantById(id);
		System.out.println("City sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	//pentru imagini
		// adaugare imagini in functie de restaurant_id
			@PostMapping("/upload/{restaurant_id}")
			@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
			public ImageModel uplaodImage(@PathVariable("restaurant_id") int restaurant_id, @RequestParam("myFile") MultipartFile file) throws IOException {

		       // ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0, city_id,0 );


		      //  final ImageModel savedImage = imageRepository.save(img);

				ImageModel savedImage = imageService.createRestaurantImages(file,restaurant_id);
		        System.out.println("Image saved");


		        return savedImage;


		    }

			@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
			public List<ImageModel> getAllRestaurantImagesById(@PathVariable("id") int id) {
				return restaurantService.findAllRestaurantImagesById(id);
			}
}