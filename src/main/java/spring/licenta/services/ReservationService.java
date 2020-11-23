package spring.licenta.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.CommentDTO;
import spring.licenta.dto.HotelDTO;
import spring.licenta.dto.ReservationDTO;
import spring.licenta.dto.RestaurantDTO;
import spring.licenta.dto.UserDTO;
import spring.licenta.entities.Comment;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Reservation;
import spring.licenta.entities.UsersEvents;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.ReservationRepository;
import spring.licenta.repositories.UserRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	UserDetailsServiceImpl userService;
//	private long id;
//	private boolean roomBooked;//for hotels
//	private boolean tableBooked;//for restaurants
//	private String book_name;
//	private String details;
//	private long user_id;
//	private long hotel_id;
//	private long restaurant_id;
//	private LocalDate reservation_start;
//	private LocalDate reservation_end;
	
	public ReservationDTO findReservationById(int reservationId) {
		Reservation reservation = reservationRepository.findById(reservationId);
//		if (hotel == null) {
//			throw new ResourceNotFoundException(Hotel.class.getSimpleName());
//		}
		// String[] names = extractNames(usr.getName());
		UserDTO userDTO=userService.findUserById(reservation.getUser_id());
		ReservationDTO reservationDTO = new ReservationDTO.Builder()
				.id(reservation.getId())
				.roomBooked(reservation.isRoomBooked())
				.tableBooked(reservation.isTableBooked())
				.book_name(reservation.getBook_name())
				.details(reservation.getDetails())
				.user_id(reservation.getUser_id())
				.hotel_id(reservation.getHotel_id())
				.restaurant_id(reservation.getRestaurant_id())
				.reservation_start(reservation.getReservation_start())
				.reservation_end(reservation.getReservation_end())
				.user(userDTO)
				.createFull();
		return reservationDTO;
	}
	public List<ReservationDTO> findAll() {
		List<Reservation> reservations = reservationRepository.findAll();
		List<ReservationDTO> toReturn = new ArrayList<ReservationDTO>();
		for (Reservation reservation : reservations) {
			// String[] names = extractNames(user.getName());
			UserDTO userDTO=userService.findUserById(reservation.getUser_id());
			ReservationDTO dto = new ReservationDTO.Builder()
					.id(reservation.getId())
					.roomBooked(reservation.isRoomBooked())
					.tableBooked(reservation.isTableBooked())
					.book_name(reservation.getBook_name())
					.details(reservation.getDetails())
					.user_id(reservation.getUser_id())
					.hotel_id(reservation.getHotel_id())
					.restaurant_id(reservation.getRestaurant_id())
					.reservation_start(reservation.getReservation_start())
					.reservation_end(reservation.getReservation_end())
					.user(userDTO)
					.createFull();
			toReturn.add(dto);
		}
		return toReturn;
	}

	public ReservationRepository create(ReservationDTO reservationDTO) {
		System.out.println("Ajunge sa creeze un a reservation?");
//		List<String> validationErrors = validateHotel(hotelDTO);
//		if (!validationErrors.isEmpty()) {
//			throw new EntityValidationException(Hotel.class.getSimpleName(), validationErrors);
//		}
		UserDTO userDTO=userService.findUserById(reservationDTO.getUser_id());
		if(reservationDTO.getRestaurant_id()!=0) {
			RestaurantDTO restaurantDTO=restaurantService.findRestaurantById((int) reservationDTO.getRestaurant_id());
			try {
				emailService.sendReservationEmail(userDTO.getEmail(),restaurantDTO.getName(), reservationDTO.getReservation_start(), reservationDTO.getReservation_end());
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			HotelDTO hotelDTO=hotelService.findHotelById((int) reservationDTO.getHotel_id());
			try {
				emailService.sendReservationEmail(userDTO.getEmail(),hotelDTO.getName(), reservationDTO.getReservation_start(), reservationDTO.getReservation_end());
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//emailService.sendReservationEmail(userDTO.getEmail(),restaurantDTO.getName(), reservationDTO.getReservation_start(), reservationDTO.getReservation_end());
		
		Reservation reservation = new Reservation();
		// city.setId(cityDTO.getId());
		reservation.setRoomBooked(reservationDTO.isRoomBooked());
		reservation.setTableBooked(reservationDTO.isTableBooked());
		reservation.setBook_name(reservationDTO.getBook_name());
		reservation.setDetails(reservationDTO.getDetails());
		reservation.setUser_id(reservationDTO.getUser_id());
		reservation.setHotel_id(reservationDTO.getHotel_id());
		reservation.setRestaurant_id(reservationDTO.getRestaurant_id());
		reservation.setReservation_start(reservationDTO.getReservation_start());
		reservation.setReservation_end(reservationDTO.getReservation_end());
		
		Reservation reservationR = reservationRepository.save(reservation);
		// am schimbat aici ce returnez
		// return city.getId();
		return reservationRepository;
	}

	public void updateReservation(ReservationDTO reservationDTO) {
		System.out.println("Ajunge sa faca update la o rezervare?");
//		List<String> validationErrors = validateHotel(hotelDTO);
//		if (!validationErrors.isEmpty()) {
//			throw new EntityValidationException(Hotel.class.getSimpleName(), validationErrors);
//		}
		
		long reservationid = reservationDTO.getId();
		//Reservation hotelupd = reservationRepository.findById(reservationid);
		Reservation reservation = new Reservation();
		// city.setId(cityDTO.getId());
		reservation.setId(reservationDTO.getId());
		reservation.setRoomBooked(reservationDTO.isRoomBooked());
		reservation.setTableBooked(reservationDTO.isTableBooked());
		reservation.setBook_name(reservationDTO.getBook_name());
		reservation.setDetails(reservationDTO.getDetails());
		reservation.setUser_id(reservationDTO.getUser_id());
		reservation.setHotel_id(reservationDTO.getHotel_id());
		reservation.setRestaurant_id(reservationDTO.getRestaurant_id());
		reservation.setReservation_start(reservationDTO.getReservation_start());
		reservation.setReservation_end(reservationDTO.getReservation_end());
		Reservation hotelR = reservationRepository.save(reservation);
		
		
	}
	
	public void deleteReservationById(long id) {
		System.out.println(id + " Incercam sa stergem reservarea");
		reservationRepository.deleteById(id);
		System.out.println(id + " asta ii ID-ul");
	}
	
	// cautam toate rezervarile de la un hotel dupa id_ul hotelului 
			public List<ReservationDTO> findAllHotelReservationsById(Long hotel_id) {
	
				System.out.println("Ce id"+hotel_id);
				List<Reservation> reservations = reservationRepository.findAllHotelReservationsById(hotel_id);
				List<ReservationDTO> toReturn = new ArrayList<ReservationDTO>();
				for (Reservation reservation :reservations) {
					UserDTO userDTO=userService.findUserById(reservation.getUser_id());
					ReservationDTO dto = new ReservationDTO.Builder()
							.id(reservation.getId())
							.roomBooked(reservation.isRoomBooked())
							.tableBooked(reservation.isTableBooked())
							.book_name(reservation.getBook_name())
							.details(reservation.getDetails())
							.user_id(reservation.getUser_id())
							.hotel_id(reservation.getHotel_id())
							.restaurant_id(reservation.getRestaurant_id())
							.reservation_start(reservation.getReservation_start())
							.reservation_end(reservation.getReservation_end())
							.user(userDTO)
							.createFull();
					toReturn.add(dto);
				}
				
				return toReturn;
			}

			public List<ReservationDTO> findAllRestaurantReservationsById(long restaurant_id) {
				System.out.println("Ce id"+restaurant_id);
				List<Reservation> reservations = reservationRepository.findAllRestaurantReservationsById(restaurant_id);
				List<ReservationDTO> toReturn = new ArrayList<ReservationDTO>();
				
				for (Reservation reservation :reservations) {
					UserDTO userDTO=userService.findUserById(reservation.getUser_id());
//					System.out.println("user email "+userDTO.getEmail()+" and the email is: "+userDTO.getTelephone());
					ReservationDTO dto = new ReservationDTO.Builder()
							.id(reservation.getId())
							.roomBooked(reservation.isRoomBooked())
							.tableBooked(reservation.isTableBooked())
							.book_name(reservation.getBook_name())
							.details(reservation.getDetails())
							.user_id(reservation.getUser_id())
							.hotel_id(reservation.getHotel_id())
							.restaurant_id(reservation.getRestaurant_id())
							.reservation_start(reservation.getReservation_start())
							.reservation_end(reservation.getReservation_end())
							.user(userDTO)
							.createFull();
					toReturn.add(dto);
				}
				
				return toReturn;
			}
			
//			public List<ReservationDTO> findAllUserReservationsHotel(long user_id,long hotel_id) {
//				System.out.println("Ce id"+hotel_id);
//				List<Reservation> reservations = reservationRepository.findAllUserReservationHotel(user_id, hotel_id);
//				List<ReservationDTO> toReturn = new ArrayList<ReservationDTO>();
//				for (Reservation reservation :reservations) {
//
//					ReservationDTO dto = new ReservationDTO.Builder()
//							.id(reservation.getId())
//							.roomBooked(reservation.isRoomBooked())
//							.tableBooked(reservation.isTableBooked())
//							.book_name(reservation.getBook_name())
//							.details(reservation.getDetails())
//							.user_id(reservation.getUser_id())
//							.hotel_id(reservation.getHotel_id())
//							.restaurant_id(reservation.getRestaurant_id())
//							.reservation_start(reservation.getReservation_start())
//							.reservation_end(reservation.getReservation_end())
//							.create();
//					toReturn.add(dto);
//				}
//				
//				return toReturn;
//			}
			public List<ReservationDTO> findAllUserReservationsHotel(long user_id,long hotel_id) {
				System.out.println("Ce id"+hotel_id);
				List<Reservation> reservations = reservationRepository.findAllUserReservationHotel(user_id, hotel_id);
				List<ReservationDTO> toReturn = new ArrayList<ReservationDTO>();
				UserDTO userDTO=userService.findUserById(user_id);
				System.out.println("user email "+userDTO.getEmail()+" and the email is: "+userDTO.getTelephone());
				for (Reservation reservation :reservations) {

					ReservationDTO dto = new ReservationDTO.Builder()
							.id(reservation.getId())
							.roomBooked(reservation.isRoomBooked())
							.tableBooked(reservation.isTableBooked())
							.book_name(reservation.getBook_name())
							.details(reservation.getDetails())
							.user_id(reservation.getUser_id())
							.hotel_id(reservation.getHotel_id())
							.restaurant_id(reservation.getRestaurant_id())
							.reservation_start(reservation.getReservation_start())
							.reservation_end(reservation.getReservation_end())
							.user(userDTO)
							.createFull();
					toReturn.add(dto);
				}
				
				return toReturn;
			}
			public List<ReservationDTO> findAllUserReservationsRestaurant(long user_id,long restaurant_id) {
				System.out.println("Ce id"+restaurant_id);
				List<Reservation> reservations = reservationRepository.findAllUserReservationRestaurant(user_id, restaurant_id);
				List<ReservationDTO> toReturn = new ArrayList<ReservationDTO>();
				UserDTO userDTO=userService.findUserById(user_id);
				System.out.println("user email "+userDTO.getEmail()+" and the email is: "+userDTO.getTelephone());
				for (Reservation reservation :reservations) {

					ReservationDTO dto = new ReservationDTO.Builder()
							.id(reservation.getId())
							.roomBooked(reservation.isRoomBooked())
							.tableBooked(reservation.isTableBooked())
							.book_name(reservation.getBook_name())
							.details(reservation.getDetails())
							.user_id(reservation.getUser_id())
							.hotel_id(reservation.getHotel_id())
							.restaurant_id(reservation.getRestaurant_id())
							.reservation_start(reservation.getReservation_start())
							.reservation_end(reservation.getReservation_end())
							.user(userDTO)
							.createFull();
					toReturn.add(dto);
				}
				
				return toReturn;
			}
}
