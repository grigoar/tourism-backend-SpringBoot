package spring.licenta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.RestaurantDTO;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Restaurant;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.ImageRepository;
import spring.licenta.repositories.RestaurantRepository;

@Service
@Transactional
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private ImageRepository imageRepository;
	
	public List<RestaurantDTO> findAll() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		List<RestaurantDTO> toReturn = new ArrayList<RestaurantDTO>();
		for (Restaurant restaurant : restaurants) {
			// String[] names = extractNames(user.getName());
			RestaurantDTO dto = new RestaurantDTO.Builder().id(restaurant.getId()).name(restaurant.getName())
					.address(restaurant.getAddress()).details(restaurant.getDetails()).contact(restaurant.getContact())
					.website(restaurant.getWebsite()).city_id(restaurant.getCityId())
					.ratingavr(restaurant.getRatingavr()).lat(restaurant.getLat()).lon(restaurant.getLon()).tables(restaurant.getTables()).create();
			toReturn.add(dto);
		}
		return toReturn;
	}

	public RestaurantDTO findRestaurantById(int restaurantId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId);
		if (restaurant == null) {
			throw new ResourceNotFoundException(Restaurant.class.getSimpleName());
		}
		// String[] names = extractNames(usr.getName());
		if(restaurant.getRatingavr()==null) {
			RestaurantDTO restaurantdto = new RestaurantDTO.Builder()
					.id(restaurant.getId()).name(restaurant.getName())
					.address(restaurant.getAddress())
					.details(restaurant.getDetails())
					.contact(restaurant.getContact())
					.website(restaurant.getWebsite())
					.city_id(restaurant.getCityId())
					.ratingavr(0.0)
					.lat(restaurant.getLat())
					.lon(restaurant.getLon())
					.tables(restaurant.getTables())
					.create();
			return restaurantdto;
		}else {
			RestaurantDTO restaurantdto = new RestaurantDTO.Builder()
					.id(restaurant.getId()).name(restaurant.getName())
					.address(restaurant.getAddress())
					.details(restaurant.getDetails())
					.contact(restaurant.getContact())
					.website(restaurant.getWebsite())
					.city_id(restaurant.getCityId())
					.ratingavr(restaurant.getRatingavr())
					.lat(restaurant.getLat())
					.lon(restaurant.getLon())
					.tables(restaurant.getTables())
					.create();
			return restaurantdto;
		}
		
	}

	public RestaurantRepository create(RestaurantDTO restaurantDTO) {
		System.out.println("Ajunge sa creeze un Restaurant?");
		List<String> validationErrors = validateRestaurant(restaurantDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Restaurant.class.getSimpleName(), validationErrors);
		}

		Restaurant restaurant = new Restaurant();
		// city.setId(cityDTO.getId());
		restaurant.setName(restaurantDTO.getName());
		restaurant.setAddress(restaurantDTO.getAddress());
		restaurant.setDetails(restaurantDTO.getDetails());
		restaurant.setContact(restaurantDTO.getContact());
		restaurant.setWebsite(restaurantDTO.getWebsite());
		restaurant.setCityId(restaurantDTO.getCity_id());
		restaurant.setRatingavr(0.0);
		restaurant.setLat(restaurantDTO.getLat());
		restaurant.setLon(restaurantDTO.getLon());
		restaurant.setTables(restaurantDTO.getTables());

		Restaurant restaurantR = restaurantRepository.save(restaurant);
		// am schimbat aici ce returnez
		// return city.getId();
		return restaurantRepository;
	}

	public void updateRestaurant(RestaurantDTO restaurantDTO) {
		System.out.println("Ajunge sa creeze un Restaurant?");
		List<String> validationErrors = validateRestaurant(restaurantDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Restaurant.class.getSimpleName(), validationErrors);
		}

		int restaurantid = restaurantDTO.getId();
		Restaurant restaurantupd = restaurantRepository.findById(restaurantid);
		Restaurant restaurant = new Restaurant();
		// city.setId(cityDTO.getId());
		restaurant.setId(restaurantDTO.getId());
		restaurant.setName(restaurantDTO.getName());
		restaurant.setAddress(restaurantDTO.getAddress());
		restaurant.setDetails(restaurantDTO.getDetails());
		restaurant.setContact(restaurantDTO.getContact());
		restaurant.setWebsite(restaurantDTO.getWebsite());
		restaurant.setCityId(restaurantDTO.getCity_id());
		restaurant.setRatingavr(restaurantupd.getRatingavr());
		restaurant.setLat(restaurantDTO.getLat());
		restaurant.setLon(restaurantDTO.getLon());
		restaurant.setTables(restaurantDTO.getTables());

		Restaurant restaurantR = restaurantRepository.save(restaurant);
		// am schimbat aici ce returnez
		// return city.getId();
		// return restaurantRepository;

	}

	public void deleteRestaurantById(int id) {
		System.out.println(id + " Incercam sa stergem restaurantul");
		restaurantRepository.deleteById(id);
		System.out.println(id + " asta ii ID-ul");
	}

	private List<String> validateRestaurant(RestaurantDTO restaurant) {
		List<String> validationErrors = new ArrayList<String>();

		if (restaurant.getCity_id() == null || "".equals(restaurant.getCity_id())) {
			validationErrors.add("City ID field should not be empty");
		}

		if (restaurant.getName() == null || "".equals(restaurant.getName())) {
			validationErrors.add("Name field should not be empty");
		}

		/*
		 * if (city.getCountry() == null || "".equals(city.getCountry())) {
		 * validationErrors.add("Country does not have the correct format."); }
		 */
		return validationErrors;
	}

//cautare restaurante in functie de orasul selectat
	public List<RestaurantDTO> findAllRestaurantsById(Integer cityId) {
		List<Restaurant> restaurants = restaurantRepository.findAllRestaurantsById(cityId);
		List<RestaurantDTO> toReturn = new ArrayList<RestaurantDTO>();
		for (Restaurant restaurant : restaurants) {
			// String comments = savings.getComment();
			RestaurantDTO dto = new RestaurantDTO.Builder().id(restaurant.getId()).name(restaurant.getName())
					.address(restaurant.getAddress()).details(restaurant.getDetails()).contact(restaurant.getContact())
					.website(restaurant.getWebsite()).city_id(restaurant.getCityId())
					.ratingavr(restaurant.getRatingavr()).lat(restaurant.getLat()).lon(restaurant.getLon()).tables(restaurant.getTables()).create();
			toReturn.add(dto);
		}
		System.out.println("Am terminat de cautat restaurantele dintr-un oras");
		return toReturn;
	}
	
	public List<ImageModel> findAllRestaurantImagesById(int restaurant_id) {
		int id=restaurant_id;
		List<ImageModel> imagesRestaurants = imageRepository.findAllRestaurantImagesById(id);
		
		//List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
		//for (Comment comment :comments) {
		List<ImageModel> imageToReturn = new ArrayList<ImageModel>();
		for(ImageModel imageRestaurant: imagesRestaurants) {
			
			ImageModel imagenew = new ImageModel();
						imagenew.setId(imageRestaurant.getId());
						imagenew.setName(imageRestaurant.getName());
						imagenew.setPic(imageRestaurant.getPic());
						imagenew.setHotel_id(imageRestaurant.getHotel_id());
						imagenew.setRestaurant_id(imageRestaurant.getRestaurant_id());
						imagenew.setEvent_id(imageRestaurant.getEvent_id());
						imagenew.setCity_id(imageRestaurant.getCity_id());
						imagenew.setUser_id(imageRestaurant.getUser_id());
						imagenew.setTouristAttraction_id(imageRestaurant.getTouristAttraction_id());
			imageToReturn.add(imagenew);
		}
		
		return imageToReturn;
		
	}

}