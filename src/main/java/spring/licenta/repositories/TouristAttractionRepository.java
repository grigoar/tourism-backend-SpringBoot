package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.TouristAttraction;





public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Integer> {
	@Query("SELECT t FROM TouristAttraction t WHERE t.cityId=?1")
	List <TouristAttraction> findAllTouristAttractionsById(Integer cityId);
	TouristAttraction findById(int id);

	void deleteById(int id);
}