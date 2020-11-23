package spring.licenta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.licenta.dto.HotelDTO;
import spring.licenta.dto.TypeOfFoodDTO;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.TypeOfFoodRepository;
import spring.licenta.services.TypeOfFoodService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/typeoffood")
public class TypeOfFoodController {

	@Autowired
	private TypeOfFoodService typeOfFoodService;
	//@Autowired
	//private UserService userService;

	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<TypeOfFoodDTO> getAllCities() {
		return typeOfFoodService.findAll();
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public TypeOfFoodDTO getCityById(@PathVariable("id") int id) {
		return typeOfFoodService.findTypeOfFoodById(id);
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
	
	  @RequestMapping(value = "/insert", method = RequestMethod.POST) 
	  @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public TypeOfFoodRepository insertCity(@RequestBody TypeOfFoodDTO typeOfFoodDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date city"+typeOfFoodDTO.getId()+typeOfFoodDTO.getType_of_food()+typeOfFoodDTO.getDetails()+
				  typeOfFoodDTO.getRestaurant_id()); 
		  return typeOfFoodService.create(typeOfFoodDTO); 
	  }
	  
		@RequestMapping(value = "/edit", method = RequestMethod.PUT)
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ResponseEntity<?> editTypeOfFood(@RequestBody TypeOfFoodDTO typeOfFoodDTO){
			//UserDTO user = userService.findUserById(id);
			System.out.println("Ajunge aici?1 edit typeof food");
			//userDTO.setId(id);
			typeOfFoodService.updateTypeOfFood(typeOfFoodDTO);
			return ResponseEntity.ok(new MessageResponse("food updated successfully!"));
		}
	  
	 
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeCity(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		TypeOfFoodDTO typeOfFood = typeOfFoodService.findTypeOfFoodById(id);
		if (typeOfFood == null) {
			System.out.println("Unable to delete.TypeOfFood with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		typeOfFoodService.deleteTypeOfFoodById(id);
		System.out.println("City sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<TypeOfFoodDTO> getAllRestaurantTypesOfFoods(@PathVariable("id") int restaurant_id) {
		return typeOfFoodService.getRestaurantTypeOfFoods(restaurant_id);
	}
}