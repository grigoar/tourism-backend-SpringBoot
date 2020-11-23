package spring.licenta.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.CityDTO;
import spring.licenta.dto.CommentDTO;
import spring.licenta.entities.City;
import spring.licenta.entities.Comment;
import spring.licenta.entities.ImageModel;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.CityRepository;
import spring.licenta.repositories.ImageRepository;


@Service
@Transactional
public class CityService {
	@Autowired
	private CityRepository cityRepository;

	/*
	 * @Autowired private PasswordEncoder bcryptEncoder;
	 */
	
	@Autowired
	private ImageRepository imageRepository;
	
	public String get() {
	        return "Hello JUnit 5";
	    }
	   
	public List<CityDTO> findAll() {
		System.out.println("hello for testing");
		List<City> cities = cityRepository.findAll();
		List<CityDTO> toReturn = new ArrayList<CityDTO>();
		for (City city : cities) {
			//String[] names = extractNames(user.getName());
			CityDTO dto = new CityDTO.Builder()
						.id(city.getId())
						.name(city.getName())
						.country(city.getCountry())
						.description(city.getDescription())
						.lat(city.getLat())
						.lon(city.getLon())
						.create();
			toReturn.add(dto);
		}
		return toReturn;
	}
	public CityDTO findCityById(int cityId) {
		City city = cityRepository.findById(cityId);
		if (city == null) {
			throw new ResourceNotFoundException(City.class.getSimpleName());
		}
		//String[] names = extractNames(usr.getName());

		CityDTO citydto = new CityDTO.Builder()
						.id(city.getId())
						.name(city.getName())
						.country(city.getCountry())
						.description(city.getDescription())
						.lat(city.getLat())
						.lon(city.getLon())
						.create();
		return citydto;
	}
	
	public City create(CityDTO cityDTO) {
		System.out.println("Ajunge sa creeze un oras?");
		List<String> validationErrors = validateCity(cityDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(City.class.getSimpleName(),validationErrors);
		}

		City city = new City();
		//city.setId(cityDTO.getId());
		city.setName(cityDTO.getName());
		city.setCountry(cityDTO.getCountry());
		city.setDescription(cityDTO.getDescription());
		city.setLat(cityDTO.getLat());
		city.setLon(cityDTO.getLon());
		
		
		City cityR = cityRepository.save(city);
		//am schimbat aici ce returnez
		//return city.getId();
		return cityR;
	}
	public void updateCity(CityDTO cityDTO) {
		System.out.println("Ajunge sa faca update un oras?");
		List<String> validationErrors = validateCity(cityDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(City.class.getSimpleName(),validationErrors);
		}

		City city = new City();
		//city.setId(cityDTO.getId());
		city.setId(cityDTO.getId());
		city.setName(cityDTO.getName());
		city.setCountry(cityDTO.getCountry());
		city.setDescription(cityDTO.getDescription());
		city.setLat(cityDTO.getLat());
		city.setLon(cityDTO.getLon());
		
		City cityR = cityRepository.save(city);
		//am schimbat aici ce returnez
		//return city.getId();
		//return cityRepository;
	}
	
	public void deleteCityById(int id) {
		 System.out.println(id+" Incercam sa il stergem");
			cityRepository.deleteById(id);
			System.out.println(id+" asta ii ID-ul");
		    }
	
	private List<String> validateCity(CityDTO city) {
		List<String> validationErrors = new ArrayList<String>();

		/*
		 * if (city.getId() == null || "".equals(city.getId())) {
		 * validationErrors.add("ID field should not be empty"); }
		 */

		if (city.getName() == null || "".equals(city.getName())) {
			validationErrors.add("Name field should not be empty");
		}

		if (city.getCountry() == null || "".equals(city.getCountry())) {
			validationErrors.add("Country does not have the correct format.");
		}

		return validationErrors;
	}
	public ImageModel findCityImagesById(int city_id) {
		int id=city_id;
		List<ImageModel> imageModel = imageRepository.findCityImageById(id);
		if(imageModel.size()<1) {
			ImageModel imageModelDeafult=imageRepository.findById(72L);
			ImageModel imagenew = new ImageModel();
			
			imagenew.setId(imageModelDeafult.getId());
			imagenew.setName(imageModelDeafult.getName());
			imagenew.setPic(imageModelDeafult.getPic());
			imagenew.setHotel_id(imageModelDeafult.getHotel_id());
			imagenew.setRestaurant_id(imageModelDeafult.getRestaurant_id());
			imagenew.setEvent_id(imageModelDeafult.getEvent_id());
			imagenew.setCity_id(imageModelDeafult.getCity_id());
			imagenew.setUser_id(imageModelDeafult.getUser_id());
			imagenew.setTouristAttraction_id(imageModelDeafult.getTouristAttraction_id());
			
			return imagenew;
		}else {
			ImageModel imagenew = new ImageModel();
			
			imagenew.setId(imageModel.get(0).getId());
			imagenew.setName(imageModel.get(0).getName());
			imagenew.setPic(imageModel.get(0).getPic());
			imagenew.setHotel_id(imageModel.get(0).getHotel_id());
			imagenew.setRestaurant_id(imageModel.get(0).getRestaurant_id());
			imagenew.setEvent_id(imageModel.get(0).getEvent_id());
			imagenew.setCity_id(imageModel.get(0).getCity_id());
			imagenew.setUser_id(imageModel.get(0).getUser_id());
			imagenew.setTouristAttraction_id(imageModel.get(0).getTouristAttraction_id());
			
return imagenew;
		}
		

	}
	public List<ImageModel> findAllCityImagesById(int city_id) {
		int id=city_id;
		List<ImageModel> imagesCity = imageRepository.findAllCityImagesById(id);
		
		//List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
		//for (Comment comment :comments) {
		List<ImageModel> imageToReturn = new ArrayList<ImageModel>();
		for(ImageModel imageCity: imagesCity) {
			
			ImageModel imagenew = new ImageModel();
						imagenew.setId(imageCity.getId());
						imagenew.setName(imageCity.getName());
						imagenew.setPic(imageCity.getPic());
						imagenew.setHotel_id(imageCity.getHotel_id());
						imagenew.setRestaurant_id(imageCity.getRestaurant_id());
						imagenew.setEvent_id(imageCity.getEvent_id());
						imagenew.setCity_id(imageCity.getCity_id());
						imagenew.setUser_id(imageCity.getUser_id());
						imagenew.setTouristAttraction_id(imageCity.getTouristAttraction_id());
			imageToReturn.add(imagenew);
		}
		
		return imageToReturn;
		
	}
}
