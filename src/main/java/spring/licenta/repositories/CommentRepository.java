package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Comment;





public interface CommentRepository extends JpaRepository<Comment, Integer> {
	@Query("SELECT t FROM Comment t WHERE t.hotel_id=?1")
	List <Comment> findAllHotelCommentsById(Integer hotelId);
	@Query("SELECT t FROM Comment t WHERE t.event_id=?1")
	List <Comment> findAllEventCommentsById(Integer eventId);
	@Query("SELECT t FROM Comment t WHERE t.restaurant_id=?1")
	List <Comment> findAllRestaurantCommentsById(Integer restaurantId);
	@Query("SELECT t FROM Comment t WHERE t.touristAttraction_id=?1")
	List <Comment> findAllTouristAttractionCommentsById(Integer touristAttractionId);
	Comment findById(int id);

	void deleteById(int id);
}
