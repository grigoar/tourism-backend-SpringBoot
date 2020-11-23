package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Event;
import spring.licenta.entities.Hotel;



public interface EventRepository extends JpaRepository<Event, Integer> {
	@Query("SELECT t FROM Event t WHERE t.cityId=?1")
	List <Event> findAllEventsById(Integer cityId);
	
	Event findById(int id);

	void deleteById(int id);
}