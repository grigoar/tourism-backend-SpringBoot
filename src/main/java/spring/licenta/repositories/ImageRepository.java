package spring.licenta.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Event;
import spring.licenta.entities.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {

	// imaginile corespunzatoare orasului destinatie
	@Query("SELECT t FROM ImageModel t WHERE t.city_id=?1")
	List<ImageModel> findAllCityImagesById(Integer city_id);

	// imaginile corespunzatoare hotelului selectat
	@Query("SELECT t FROM ImageModel t WHERE t.hotel_id=?1")
	List<ImageModel> findAllHotelImagesById(Integer hotel_id);

	// imaginile corespunzatoare restaurantului selectat
	@Query("SELECT t FROM ImageModel t WHERE t.restaurant_id=?1")
	List<ImageModel> findAllRestaurantImagesById(Integer restaurant_id);

	// imaginile corespunzatoare touristAttraction selectat
	@Query("SELECT t FROM ImageModel t WHERE t.touristAttraction_id=?1")
	List<ImageModel> findAllTouristAttractionImagesById(Integer touristAttraction_id);

	// imaginile corespunzatoare eventului selectat
	@Query("SELECT t FROM ImageModel t WHERE t.event_id=?1")
	List<ImageModel> findAllEventImagesById(Integer event_id);

	//will be changed to return the first element from the list
	@Query("SELECT t FROM ImageModel t WHERE t.city_id=?1")
	List<ImageModel> findCityImageById(Integer city_id);

	// pentru imaginea de profil
	@Query("SELECT t FROM ImageModel t WHERE t.user_id=?1")
	ImageModel findUserImageById(Integer user_id);
	
	ImageModel findById(long id);
}
