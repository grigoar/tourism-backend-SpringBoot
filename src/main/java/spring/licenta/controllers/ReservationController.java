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
import spring.licenta.dto.ReservationDTO;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.HotelRepository;
import spring.licenta.repositories.ReservationRepository;
import spring.licenta.services.ReservationService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public ReservationDTO getReservationById(@PathVariable("id") int id) {
		return reservationService.findReservationById(id);
	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<ReservationDTO> getAllReservations() {
		return reservationService.findAll();
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public ReservationRepository insertReservation(@RequestBody ReservationDTO reservationDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date hotel"+reservationDTO.getId()+reservationDTO.getBook_name()+reservationDTO.isRoomBooked()+reservationDTO.getDetails()+
				  reservationDTO.isTableBooked()); 
		  return reservationService.create(reservationDTO); 
	  }
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editReservation(@RequestBody ReservationDTO reservationDTO){
		System.out.println("Ajunge aici?1 edit reserVATION");
		reservationService.updateReservation(reservationDTO);
		return ResponseEntity.ok(new MessageResponse("Reservation updated successfully!"));
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeHotel(@PathVariable("id") int id) {
		
		System.out.println("Inainte de stergere");
		reservationService.deleteReservationById(id);
		System.out.println("Reservare stearsa");
	}
	
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllHotelReservationsById(@PathVariable("id") long hotel_id) {
		System.out.println("cautam toate rezervarile pentru un hotel dintr-un oras");
		return reservationService.findAllHotelReservationsById(hotel_id);
	}
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllRestaurantReservationsById(@PathVariable("id") long restaurant_id) {
		System.out.println("cautam toate rezervarile pentru un restaurant dintr-un oras");
		return reservationService.findAllRestaurantReservationsById(restaurant_id);
	}
	
	@RequestMapping(value = "/hotel/{user_id}/{hotel_id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllUserHotelReservations(@PathVariable("user_id") long user_id,@PathVariable("hotel_id") long hotel_id) {
		System.out.println("cautam toate rezervarile pentru un hotel al unui user");
		return reservationService.findAllUserReservationsHotel(user_id, hotel_id);
	}
	@RequestMapping(value = "/restaurant/{user_id}/{restaurant_id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllUserRestaurantReservations(@PathVariable("user_id") long user_id,@PathVariable("restaurant_id") long restaurant_id) {
		System.out.println("cautam toate rezervarile pentru un restaurant al unui user");
		return reservationService.findAllUserReservationsRestaurant(user_id, restaurant_id);
	}
}
