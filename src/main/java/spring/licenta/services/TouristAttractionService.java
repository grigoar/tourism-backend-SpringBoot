package spring.licenta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import spring.licenta.dto.TouristAttractionDTO;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Restaurant;
import spring.licenta.entities.TouristAttraction;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.ImageRepository;
import spring.licenta.repositories.TouristAttractionRepository;

@Service
@Transactional
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TouristAttractionService {
	@Autowired
	private TouristAttractionRepository touristAttractionRepository;
	
	@Autowired
	private ImageRepository imageRepository;

	public List<TouristAttractionDTO> findAll() {
		List<TouristAttraction> touristAttractions = touristAttractionRepository.findAll();
		List<TouristAttractionDTO> toReturn = new ArrayList<TouristAttractionDTO>();
		for (TouristAttraction touristAttraction : touristAttractions) {
			// String[] names = extractNames(user.getName());
			TouristAttractionDTO dto = new TouristAttractionDTO.Builder().id(touristAttraction.getId())
					.name(touristAttraction.getName()).address(touristAttraction.getAddress())
					.details(touristAttraction.getDetails()).city_id(touristAttraction.getCityId())
					.ratingavr(touristAttraction.getRatingavr()).lat(touristAttraction.getLat())
					.lon(touristAttraction.getLon()).create();
			toReturn.add(dto);
		}
		return toReturn;
	}

	public TouristAttractionDTO findTouristAttractionById(int touristAttractionId) {
		TouristAttraction touristAttraction = touristAttractionRepository.findById(touristAttractionId);
		if (touristAttraction == null) {
			throw new ResourceNotFoundException(TouristAttraction.class.getSimpleName());
		}
		// String[] names = extractNames(usr.getName());

		TouristAttractionDTO touristAttractiondto = new TouristAttractionDTO.Builder().id(touristAttraction.getId())
				.name(touristAttraction.getName()).address(touristAttraction.getAddress())
				.details(touristAttraction.getDetails()).city_id(touristAttraction.getCityId())
				.ratingavr(touristAttraction.getRatingavr()).lat(touristAttraction.getLat())
				.lon(touristAttraction.getLon()).create();
		return touristAttractiondto;
	}

	public TouristAttractionRepository create(TouristAttractionDTO touristAttractionDTO) {
		System.out.println("Ajunge sa creeze un TouristAttraction?");
		List<String> validationErrors = validateTouristAttraction(touristAttractionDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(TouristAttraction.class.getSimpleName(), validationErrors);
		}

		TouristAttraction touristAttraction = new TouristAttraction();
		// city.setId(cityDTO.getId());
		touristAttraction.setName(touristAttractionDTO.getName());
		touristAttraction.setAddress(touristAttractionDTO.getAddress());
		touristAttraction.setDetails(touristAttractionDTO.getDetails());
		touristAttraction.setCityId(touristAttractionDTO.getCity_id());
		touristAttraction.setRatingavr(0.0);
		touristAttraction.setLat(touristAttractionDTO.getLat());
		touristAttraction.setLon(touristAttractionDTO.getLon());

		TouristAttraction touristAttractionR = touristAttractionRepository.save(touristAttraction);
		// am schimbat aici ce returnez
		// return city.getId();
		return touristAttractionRepository;
	}

	public void updateTouristAttraction(TouristAttractionDTO touristAttractionDTO) {
		System.out.println("Ajunge sa faca update un TouristAttraction?");
		List<String> validationErrors = validateTouristAttraction(touristAttractionDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(TouristAttraction.class.getSimpleName(), validationErrors);
		}
		int touristAttractionid = touristAttractionDTO.getId();
		TouristAttraction touristAttractionupd = touristAttractionRepository.findById(touristAttractionid);

		TouristAttraction touristAttraction = new TouristAttraction();
		// city.setId(cityDTO.getId());
		touristAttraction.setId(touristAttractionDTO.getId());
		touristAttraction.setName(touristAttractionDTO.getName());
		touristAttraction.setAddress(touristAttractionDTO.getAddress());
		touristAttraction.setDetails(touristAttractionDTO.getDetails());
		touristAttraction.setCityId(touristAttractionDTO.getCity_id());
		touristAttraction.setRatingavr(touristAttractionupd.getRatingavr());
		touristAttraction.setLat(touristAttractionDTO.getLat());
		touristAttraction.setLon(touristAttractionDTO.getLon());

		TouristAttraction touristAttractionR = touristAttractionRepository.save(touristAttraction);
		// am schimbat aici ce returnez
		// return city.getId();
		// return touristAttractionRepository;

	}

	public void deleteTouristAttractionById(int id) {
		System.out.println(id + " Incercam sa stergem touristAttractionul");
		touristAttractionRepository.deleteById(id);
		System.out.println(id + " asta ii ID-ul");
	}

	private List<String> validateTouristAttraction(TouristAttractionDTO touristAttraction) {
		List<String> validationErrors = new ArrayList<String>();

		if (touristAttraction.getCity_id() == null || "".equals(touristAttraction.getCity_id())) {
			validationErrors.add("City ID field should not be empty");
		}

		if (touristAttraction.getName() == null || "".equals(touristAttraction.getName())) {
			validationErrors.add("Name field should not be empty");
		}

		/*
		 * if (city.getCountry() == null || "".equals(city.getCountry())) {
		 * validationErrors.add("Country does not have the correct format."); }
		 */
		return validationErrors;
	}

//cautam toate punctele turistice dintr-un oras
	public List<TouristAttractionDTO> findAllTouristAttractionsById(Integer cityId) {
		List<TouristAttraction> touristAttractions = touristAttractionRepository.findAllTouristAttractionsById(cityId);
		List<TouristAttractionDTO> toReturn = new ArrayList<TouristAttractionDTO>();
		for (TouristAttraction touristAttraction : touristAttractions) {
			// String comments = savings.getComment();
			TouristAttractionDTO dto = new TouristAttractionDTO.Builder().id(touristAttraction.getId())
					.name(touristAttraction.getName()).address(touristAttraction.getAddress())
					.details(touristAttraction.getDetails()).city_id(touristAttraction.getCityId())
					.ratingavr(touristAttraction.getRatingavr()).lat(touristAttraction.getLat())
					.lon(touristAttraction.getLon()).create();
			toReturn.add(dto);
		}
		System.out.println("Am terminat de cautat punctele turistice dintr-un oras");
		return toReturn;
	}

	public List<ImageModel> findAllTouristAttractionImagesById(int touristAttraction_id) {
		int id = touristAttraction_id;
		List<ImageModel> imagesTouristAttractions = imageRepository.findAllTouristAttractionImagesById(id);

		// List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
		// for (Comment comment :comments) {
		List<ImageModel> imageToReturn = new ArrayList<ImageModel>();
		for (ImageModel imageTouristAttraction : imagesTouristAttractions) {

			ImageModel imagenew = new ImageModel();
			imagenew.setId(imageTouristAttraction.getId());
			imagenew.setName(imageTouristAttraction.getName());
			imagenew.setPic(imageTouristAttraction.getPic());
			imagenew.setHotel_id(imageTouristAttraction.getHotel_id());
			imagenew.setRestaurant_id(imageTouristAttraction.getRestaurant_id());
			imagenew.setEvent_id(imageTouristAttraction.getEvent_id());
			imagenew.setCity_id(imageTouristAttraction.getCity_id());
			imagenew.setUser_id(imageTouristAttraction.getUser_id());
			imagenew.setTouristAttraction_id(imageTouristAttraction.getTouristAttraction_id());
			imageToReturn.add(imagenew);
		}

		return imageToReturn;

	}

}