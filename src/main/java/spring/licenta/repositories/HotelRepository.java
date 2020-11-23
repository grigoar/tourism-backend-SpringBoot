package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Hotel;



public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	@Query("SELECT t FROM Hotel t WHERE t.cityId=?1")
	List <Hotel> findAllHotelsById(Integer cityId);
//	@Query("SELECT t FROM Restaurant t WHERE t.cityId=?1")
//	List <Restaurant> findAllRestaurantsById(Integer cityId);
	Hotel findById(int id);

	void deleteById(int id);
}