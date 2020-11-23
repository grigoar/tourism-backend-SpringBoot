package spring.licenta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.TypeOfFoodDTO;
import spring.licenta.entities.TypeOfFood;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.TypeOfFoodRepository;



@Service
@Transactional
public class TypeOfFoodService {
	@Autowired
	private TypeOfFoodRepository typeOfFoodRepository;

	/*
	 * @Autowired private PasswordEncoder bcryptEncoder;
	 */
	
	public List<TypeOfFoodDTO> findAll() {
		List<TypeOfFood> typeOfFoods = typeOfFoodRepository.findAll();
		List<TypeOfFoodDTO> toReturn = new ArrayList<TypeOfFoodDTO>();
		for (TypeOfFood typeOfFood : typeOfFoods) {
			//String[] names = extractNames(user.getName());
			TypeOfFoodDTO dto = new TypeOfFoodDTO.Builder()
						.id(typeOfFood.getId())
						.type_of_food(typeOfFood.getType_of_food())
						.details(typeOfFood.getDetails())
						.restaurant_id(typeOfFood.getRestaurant_id())
						.create();
			toReturn.add(dto);
		}
		return toReturn;
	}
	public TypeOfFoodDTO findTypeOfFoodById(int typeOfFoodId) {
		TypeOfFood typeOfFood = typeOfFoodRepository.findById(typeOfFoodId);
		if (typeOfFood == null) {
			throw new ResourceNotFoundException(TypeOfFood.class.getSimpleName());
		}
		//String[] names = extractNames(usr.getName());

		TypeOfFoodDTO typeOfFooddto = new TypeOfFoodDTO.Builder()
				.id(typeOfFood.getId())
				.type_of_food(typeOfFood.getType_of_food())
				.details(typeOfFood.getDetails())
				.restaurant_id(typeOfFood.getRestaurant_id())
				.create();
		return typeOfFooddto;
	}
	
	public TypeOfFoodRepository create(TypeOfFoodDTO typeOfFoodDTO) {
		System.out.println("Ajunge sa creeze un tip de mancare?");
		List<String> validationErrors = validateTypeOfFood(typeOfFoodDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(TypeOfFood.class.getSimpleName(),validationErrors);
		}

		TypeOfFood typeOfFood = new TypeOfFood();
		//typeOfFood.setId(typeOfFoodDTO.getId());
		typeOfFood.setType_of_food(typeOfFoodDTO.getType_of_food());
		typeOfFood.setDetails(typeOfFoodDTO.getDetails());
		typeOfFood.setRestaurant_id(typeOfFoodDTO.getRestaurant_id());		

		TypeOfFood typeOfFoodR = typeOfFoodRepository.save(typeOfFood);
		//am schimbat aici ce returnez
		//return typeOfFood.getId();
		return typeOfFoodRepository;
	}
	public void updateTypeOfFood(TypeOfFoodDTO typeOfFoodDTO) {
		System.out.println("Ajunge sa creeze un tip de mancare?");
		List<String> validationErrors = validateTypeOfFood(typeOfFoodDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(TypeOfFood.class.getSimpleName(),validationErrors);
		}

		TypeOfFood typeOfFood = new TypeOfFood();
		typeOfFood.setId(typeOfFoodDTO.getId());
		typeOfFood.setType_of_food(typeOfFoodDTO.getType_of_food());
		typeOfFood.setDetails(typeOfFoodDTO.getDetails());
		typeOfFood.setRestaurant_id(typeOfFoodDTO.getRestaurant_id());		

		TypeOfFood typeOfFoodR = typeOfFoodRepository.save(typeOfFood);
		//am schimbat aici ce returnez
		//return typeOfFood.getId();
		//return typeOfFoodRepository;
		
	}
	
	public void deleteTypeOfFoodById(int id) {
		 System.out.println(id+" Incercam sa il stergem");
			typeOfFoodRepository.deleteById(id);
			System.out.println(id+" asta ii ID-ul");
		    }
	
	public List<TypeOfFoodDTO> getRestaurantTypeOfFoods(int restaurant_id) {
		System.out.println("Ajunge sa caute tipurile de mancare pentru un restaurant?");
		List<TypeOfFood> restaurantTypeOfFoods = typeOfFoodRepository.findAllRestaurnatTypeOdFoodsById(restaurant_id);
		List<TypeOfFoodDTO> toReturn = new ArrayList<TypeOfFoodDTO>();
		for (TypeOfFood typeOfFood : restaurantTypeOfFoods) {
			//String[] names = extractNames(user.getName());
			TypeOfFoodDTO dto = new TypeOfFoodDTO.Builder()
						.id(typeOfFood.getId())
						.type_of_food(typeOfFood.getType_of_food())
						.details(typeOfFood.getDetails())
						.restaurant_id(typeOfFood.getRestaurant_id())
						.create();
			toReturn.add(dto);
		}
		return toReturn;
	}
	
	private List<String> validateTypeOfFood(TypeOfFoodDTO typeOfFood) {
		List<String> validationErrors = new ArrayList<String>();

		/*
		 * if (typeOfFood.getId() == null || "".equals(typeOfFood.getId())) {
		 * validationErrors.add("ID field should not be empty"); }
		 */

		if (typeOfFood.getType_of_food() == null || "".equals(typeOfFood.getType_of_food())) {
			validationErrors.add("Type_of_food field should not be empty");
		}

		if (typeOfFood.getRestaurant_id() == null || "".equals(typeOfFood.getRestaurant_id())) {
			validationErrors.add("Restaurant id does not has an invalid value.");
		}

		return validationErrors;
	}
	
}