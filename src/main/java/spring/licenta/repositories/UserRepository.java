package spring.licenta.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.licenta.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	//@Query("SELECT t,y FROM User t WHERE t.username=?1 and y.password=?2")
	/* @Query("SELECT t FROM User t WHERE t.username=?1") */
	/*
	 * User findByUsername(String username);
	 * 
	 * //User findByName(String name);
	 * 
	 * User findById(long id);
	 * 
	 * void deleteById(int id);
	 */
	//Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	User findById(long id);
	User findByUsername(String username);
}
