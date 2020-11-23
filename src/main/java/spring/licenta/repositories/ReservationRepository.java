package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Hotel;
import spring.licenta.entities.Reservation;
import spring.licenta.entities.UsersEvents;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	@Query("SELECT t FROM Reservation t WHERE t.restaurant_id=?1")
	List <Reservation> findAllRestaurantReservationsById(Long restaurant_id);
	@Query("SELECT t FROM Reservation t WHERE t.hotel_id=?1")
	List <Reservation> findAllHotelReservationsById(Long hotel_id);
//	@Query("SELECT t FROM Restaurant t WHERE t.cityId=?1")
//	List <Restaurant> findAllRestaurantsById(Integer cityId);
	
	//cautam rezervarea facuta de un user-> poate is mai multe dar cel putin una sa fie
	@Query("SELECT t FROM Reservation t WHERE t.user_id=?1 AND t.hotel_id=?2")
	List<Reservation> findAllUserReservationHotel(Long user_id, long hotel_id);
	//cautam rezervarea facuta de un user-> poate is mai multe dar cel putin una sa fie
	@Query("SELECT t FROM Reservation t WHERE t.user_id=?1 AND t.restaurant_id=?2")
	List<Reservation> findAllUserReservationRestaurant(Long user_id, long restaurant_id);
	
	Reservation findById(long id);

	void deleteById(long id);
}