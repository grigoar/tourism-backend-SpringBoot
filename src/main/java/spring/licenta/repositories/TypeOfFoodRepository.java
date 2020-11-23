package spring.licenta.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.licenta.entities.Hotel;
import spring.licenta.entities.TypeOfFood;





public interface TypeOfFoodRepository extends JpaRepository<TypeOfFood, Integer> {
	@Query("SELECT t FROM TypeOfFood t WHERE t.restaurant_id=?1")
	List <TypeOfFood> findAllRestaurnatTypeOdFoodsById(Integer restaurant_id);
	TypeOfFood findById(int id);

	void deleteById(int id);
}
