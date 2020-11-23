package spring.licenta.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.EventDTO;
import spring.licenta.entities.Event;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.ImageModel;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.EventRepository;
import spring.licenta.repositories.ImageRepository;


@Service
@Transactional
public class EventService {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	

	public List<EventDTO> findAll() {
		List<Event> events = eventRepository.findAll();
		List<EventDTO> toReturn = new ArrayList<EventDTO>();
		for (Event event : events) {
			//String[] names = extractNames(user.getName());
			EventDTO dto = new EventDTO.Builder()
						.id(event.getId())
						.name(event.getName())
						.start_time(event.getStart_time())
						.address(event.getAddress())
						.details(event.getDetails())
						.city_id(event.getCityId())
						.going(event.getGoing())
						.maybe(event.getMaybe())
						.lat(event.getLat())
						.lon(event.getLon())
						.create();
			toReturn.add(dto);
		}
		return toReturn;
	}
	
	public EventDTO findEventById(int eventId) {
		Event event = eventRepository.findById(eventId);
		if (event == null) {
			throw new ResourceNotFoundException(Event.class.getSimpleName());
		}
		//String[] names = extractNames(usr.getName());

		EventDTO eventdto = new EventDTO.Builder()
				.id(event.getId())
				.name(event.getName())
				.start_time(event.getStart_time())
				.address(event.getAddress())
				.details(event.getDetails())
				.city_id(event.getCityId())
				.going(event.getGoing())
				.maybe(event.getMaybe())
				.lat(event.getLat())
				.lon(event.getLon())
				.create();
		return eventdto;
	}
	
	public EventRepository create(EventDTO eventDTO) {
		System.out.println("Ajunge sa creeze un Event?");
		List<String> validationErrors = validateEvent(eventDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Event.class.getSimpleName(),validationErrors);
		}
		System.out.println("timpul la care incepe evenimentul este"+eventDTO.getStart_time().toGMTString());
		Event event = new Event();
		//city.setId(cityDTO.getId());
		event.setName(eventDTO.getName());
		event.setStart_time(eventDTO.getStart_time());
		event.setAddress(eventDTO.getAddress());
		event.setDetails(eventDTO.getDetails());
		event.setCityId(eventDTO.getCity_id());
		event.setGoing(0L);
		event.setMaybe(0L);
		event.setLat(eventDTO.getLat());
		event.setLon(eventDTO.getLon());
		
		
		
		
		Event eventR = eventRepository.save(event);
		//am schimbat aici ce returnez
		//return city.getId();
		return eventRepository;
	}
	
	public void updateEvent(EventDTO eventDTO) {
		System.out.println("Ajunge sa faca update la un Event?");
		List<String> validationErrors = validateEvent(eventDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Event.class.getSimpleName(),validationErrors);
		}
//		int id=hotel_id;
//		Hotel ratinghotel=hotelRepository.findById(id);
//		Hotel hotel = new Hotel();
//		//city.setId(cityDTO.getId());
//		hotel.setId(ratinghotel.getId());
//		hotel.setName(ratinghotel.getName());
//		hotel.setAddress(ratinghotel.getAddress());
//		hotel.setDetails(ratinghotel.getDetails());
//		hotel.setContact(ratinghotel.getContact());
//		hotel.setWebsite(ratinghotel.getWebsite());
//		hotel.setCityId(ratinghotel.getCityId());
//		hotel.setRatingavr(ratingavr);
//		
//		Hotel hotelR = hotelRepository.save(hotel);
		int eventid=eventDTO.getId();
		Event eventgm=eventRepository.findById(eventid);
		Event event = new Event();
		//city.setId(cityDTO.getId());
		event.setId(eventDTO.getId());
		event.setName(eventDTO.getName());
		event.setStart_time(eventDTO.getStart_time());
		event.setAddress(eventDTO.getAddress());
		event.setDetails(eventDTO.getDetails());
		event.setCityId(eventDTO.getCity_id());
		event.setGoing(eventgm.getGoing());
		event.setMaybe(eventgm.getMaybe());
		event.setLat(eventDTO.getLat());
		event.setLon(eventDTO.getLon());
		
		

		Event eventR = eventRepository.save(event);
		//am schimbat aici ce returnez
		//return city.getId();
		//return eventRepository;
	}

	public void updateEventGM(EventDTO eventDTO) {
		System.out.println("Ajunge sa faca update la going maybe la un Event?");
		List<String> validationErrors = validateEvent(eventDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Event.class.getSimpleName(),validationErrors);
		}
//		
		int eventid=eventDTO.getId();
		Event eventgm=eventRepository.findById(eventid);
		Event event = new Event();
		//city.setId(cityDTO.getId());
		event.setId(eventDTO.getId());
		event.setName(eventDTO.getName());
		event.setStart_time(eventgm.getStart_time());
		event.setAddress(eventDTO.getAddress());
		event.setDetails(eventDTO.getDetails());
		event.setCityId(eventDTO.getCity_id());
		event.setGoing(eventDTO.getGoing());
		event.setMaybe(eventDTO.getMaybe());
		event.setLat(eventDTO.getLat());
		event.setLon(eventDTO.getLon());
		
		

		Event eventR = eventRepository.save(event);
	}
	public void deleteEventById(int id) {
		 System.out.println(id+" Incercam sa stergem eventul");
		 eventRepository.deleteById(id);
		System.out.println(id+" asta ii ID-ul");
    }
	
private List<String> validateEvent(EventDTO event) {
	List<String> validationErrors = new ArrayList<String>();

		
		  if (event.getCity_id() == null || "".equals(event.getCity_id())) {
		  validationErrors.add("City ID field should not be empty"); 
		  }
		 

	if (event.getName() == null || "".equals(event.getName())) {
		validationErrors.add("Name field should not be empty");
	}

	/*if (city.getCountry() == null || "".equals(city.getCountry())) {
		validationErrors.add("Country does not have the correct format.");
	}
*/
	return validationErrors;
}
//cauta toate events dintr-un oras
public List<EventDTO> findAllEventsById(Integer cityId) {
	List<Event> events = eventRepository.findAllEventsById(cityId);
	List<EventDTO> toReturn = new ArrayList<EventDTO>();
	for (Event event : events) {
		//String comments = savings.getComment();
		EventDTO dto = new EventDTO.Builder()
				.id(event.getId())
				.name(event.getName())
				.start_time(event.getStart_time())
				.address(event.getAddress())
				.details(event.getDetails())
				.city_id(event.getCityId())
				.going(event.getGoing())
				.maybe(event.getMaybe())
				.lat(event.getLat())
				.lon(event.getLon())
				.create();
		toReturn.add(dto);
	}
	System.out.println("Am terminat de cautat eventurile dintr-un oras");
	return toReturn;
}


//returnam toate imaginile care apartin de un event, fie reprezentative sau generale, fie de la editile anterioare ale eventului
public List<ImageModel> findAllEventImagesById(int event_id) {
	int id = event_id;
	List<ImageModel> imagesEvents = imageRepository.findAllEventImagesById(id);

	// List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
	// for (Comment comment :comments) {
	List<ImageModel> imageToReturn = new ArrayList<ImageModel>();
	for (ImageModel imageEvent : imagesEvents) {

		ImageModel imagenew = new ImageModel();
		imagenew.setId(imageEvent.getId());
		imagenew.setName(imageEvent.getName());
		imagenew.setPic(imageEvent.getPic());
		imagenew.setHotel_id(imageEvent.getHotel_id());
		imagenew.setRestaurant_id(imageEvent.getRestaurant_id());
		imagenew.setEvent_id(imageEvent.getEvent_id());
		imagenew.setCity_id(imageEvent.getCity_id());
		imagenew.setUser_id(imageEvent.getUser_id());
		imagenew.setTouristAttraction_id(imageEvent.getTouristAttraction_id());
		imageToReturn.add(imagenew);
	}

	return imageToReturn;

}
}