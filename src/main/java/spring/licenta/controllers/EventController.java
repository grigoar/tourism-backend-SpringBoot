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

import spring.licenta.dto.EventDTO;
import spring.licenta.dto.HotelDTO;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.UsersEvents;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.EventRepository;
import spring.licenta.services.EventService;
import spring.licenta.services.ImageService;
import spring.licenta.services.UsersEventsService;




@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private UsersEventsService usersEventsService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<EventDTO> getAllHotels() {
		return eventService.findAll();
	}
	
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<EventDTO> getAllEventsById(@PathVariable("id") int id) {
		System.out.println("cautam toate eventurile dintr-un oras");
		return eventService.findAllEventsById(id);
	}
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public EventDTO getHotelById(@PathVariable("id") int id) {
		return eventService.findEventById(id);
	}
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public EventRepository insertEvent(@RequestBody EventDTO eventDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date hotel"+eventDTO.getId()+eventDTO.getName()+eventDTO.getStart_time()+eventDTO.getAddress()+eventDTO.getDetails()+eventDTO.getCity_id()); 
		  return eventService.create(eventDTO); 
	  }
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editEvent(@RequestBody EventDTO eventDTO){
		//UserDTO user = userService.findUserById(id);
		System.out.println("Ajunge aici?1 edit hotel");
		//userDTO.setId(id);
		eventService.updateEvent(eventDTO);
		return ResponseEntity.ok(new MessageResponse("Event updated successfully!"));
	}
	@RequestMapping(value = "/editGM", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editEventGM(@RequestBody EventDTO eventDTO){
		//UserDTO user = userService.findUserById(id);
		System.out.println("Ajunge aici?1 edit hotel");
		//userDTO.setId(id);
		eventService.updateEventGM(eventDTO);
		return ResponseEntity.ok(new MessageResponse("Event updated successfully!"));
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeEvent(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		EventDTO event = eventService.findEventById(id);
		if (event == null) {
			System.out.println("Unable to delete. Event with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		eventService.deleteEventById(id);
		System.out.println("Event sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/adduserevent", method = RequestMethod.POST)
	public UsersEvents addUserEvent(@RequestBody UsersEvents userEvent) {
		return usersEventsService.addUserEvent(userEvent);
	}
	@RequestMapping(value = "/edituserevent", method = RequestMethod.POST)
	public UsersEvents editUserEventOption(@RequestBody UsersEvents userEvent) {
		return usersEventsService.changeOptionUser(userEvent);
	}
	@RequestMapping(value = "/checkuserevent/{userid}/{eventid}", method = RequestMethod.GET)
	public List<UsersEvents> checkUserEventOption(@PathVariable("userid") int user_id,@PathVariable("eventid") int event_id) {
		return usersEventsService.showIfUserEvent(user_id, event_id);
	}
	
	//pentru imagini
		// adaugare imagini in functie de hotel_id
			@PostMapping("/upload/{event_id}")
			@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
			public ImageModel uplaodImage(@PathVariable("event_id") int event_id, @RequestParam("myFile") MultipartFile file) throws IOException {

		       // ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0, city_id,0 );


		      //  final ImageModel savedImage = imageRepository.save(img);

				ImageModel savedImage = imageService.createEventImages(file,event_id);
		        System.out.println("Image saved");


		        return savedImage;


		    }

			@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
			public List<ImageModel> getAllEventImagesById(@PathVariable("id") int id) {
				return eventService.findAllEventImagesById(id);
			}
}
