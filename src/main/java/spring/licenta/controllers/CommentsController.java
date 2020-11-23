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

import spring.licenta.dto.CommentDTO;
import spring.licenta.dto.HotelDTO;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.CommentRepository;
import spring.licenta.services.CommentService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/comments")
public class CommentsController {
	@Autowired
	private CommentService commentService;
	//@Autowired
	//private UserService userService;

	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CommentDTO> getAllComments() {
		return commentService.findAll();
	}
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET)
	public List<CommentDTO> getAllHotelCommentsById(@PathVariable("id") int hotel_id) {
		System.out.println("cautam toate commenturile pentru un hotel dintr-un oras");
		return commentService.findAllHotelCommentsById(hotel_id);
	}
	
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	public List<CommentDTO> getAllEventCommentsById(@PathVariable("id") int event_id) {
		System.out.println("cautam toate commenturile pentru un event dintr-un oras");
		return commentService.findAllEventCommentsById(event_id);
	}
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	public List<CommentDTO> getAllRestaurantCommentsById(@PathVariable("id") int restaurant_id) {
		System.out.println("cautam toate commenturile pentru un restaurant dintr-un oras");
		return commentService.findAllRestaurantCommentsById(restaurant_id);
	}
	@RequestMapping(value = "/touristAttraction/{id}", method = RequestMethod.GET)
	public List<CommentDTO> getAllTouristAttractionCommentsById(@PathVariable("id") int touristAttraction_id) {
		System.out.println("cautam toate commenturile pentru un touristAttraction dintr-un oras");
		return commentService.findAllTouristAttractionCommentsById(touristAttraction_id);
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public CommentDTO getCommentById(@PathVariable("id") int id) {
		return commentService.findCommentById(id);
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
	  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public CommentRepository insertComment(@RequestBody CommentDTO commentDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date city"+commentDTO.getId()+commentDTO.getDate()+commentDTO.getDetails()+commentDTO.getRestaurant_id()+
				  commentDTO.getHotel_id()+commentDTO.getEvent_id()+commentDTO.getTouristAttraction_id()+commentDTO.getUser_id()); 
		  return commentService.create(commentDTO); 
	  }
	  
		@RequestMapping(value = "/edit", method = RequestMethod.PUT)
		public ResponseEntity<?> editComment(@RequestBody CommentDTO commentDTO){
			//UserDTO user = userService.findUserById(id);
			System.out.println("Ajunge aici?1 edit hotel");
			//userDTO.setId(id);
			commentService.updateComment(commentDTO);
			return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
		}
	 
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public void removeComment(@PathVariable("id") int id) {
		System.out.println("Am intrat la stergerea de comments");
		CommentDTO comment = commentService.findCommentById(id);
		if (comment == null) {
			System.out.println("Unable to delete. comment with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		commentService.deleteCommentById(id);
		System.out.println("Comment sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/hotel/statistics/{id}", method = RequestMethod.GET)
	public List<Double> getAllHotelRatingsById(@PathVariable("id") int hotel_id) {
		System.out.println("cautam toate commenturile pentru un hotel dintr-un oras");
		return commentService.getCommentsByRatingHotel(hotel_id);
	}
	@RequestMapping(value = "/restaurant/statistics/{id}", method = RequestMethod.GET)
	public List<Double> getAllRestaurantRatingsById(@PathVariable("id") int restaurant_id) {
		System.out.println("cautam toate commenturile pentru un hotel dintr-un oras");
		return commentService.getCommentsByRatingRestaurant(restaurant_id);
	}
	
	@RequestMapping(value = "/touristAttraction/statistics/{id}", method = RequestMethod.GET)
	public List<Double> getAllTouristAttractionRatingsById(@PathVariable("id") int touristAttraction_id) {
		System.out.println("cautam toate commenturile pentru un hotel dintr-un oras");
		return commentService.getCommentsByRatingRestaurant(touristAttraction_id);
	}
}
