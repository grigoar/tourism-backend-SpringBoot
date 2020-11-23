package spring.licenta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.licenta.entities.City;



public interface CityRepository extends JpaRepository<City, Integer> {
	City findById(int id);

	void deleteById(int id);
}
