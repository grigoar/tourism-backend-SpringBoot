package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Restaurant;



public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	@Query("SELECT t FROM Restaurant t WHERE t.cityId=?1")
	List <Restaurant> findAllRestaurantsById(Integer cityId);
	Restaurant findById(int id);

	void deleteById(int id);
}