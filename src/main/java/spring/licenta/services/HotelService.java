package spring.licenta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.dto.HotelDTO;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.ImageModel;
import spring.licenta.errorhandler.EntityValidationException;
import spring.licenta.errorhandler.ResourceNotFoundException;
import spring.licenta.repositories.HotelRepository;
import spring.licenta.repositories.ImageRepository;

@Service
@Transactional
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ImageRepository imageRepository;

	public List<HotelDTO> findAll() {
		List<Hotel> hotels = hotelRepository.findAll();
		List<HotelDTO> toReturn = new ArrayList<HotelDTO>();
		for (Hotel hotel : hotels) {
			// String[] names = extractNames(user.getName());
			HotelDTO dto = new HotelDTO.Builder()
					.id(hotel.getId())
					.name(hotel.getName())
					.address(hotel.getAddress())
					.details(hotel.getDetails())
					.contact(hotel.getContact())
					.website(hotel.getWebsite())
					.city_id(hotel.getCityId())
					.ratingavr(hotel.getRatingavr())
					.lat(hotel.getLat())
					.lon(hotel.getLon())
					.rooms(hotel.getRooms())
					.create();
			toReturn.add(dto);
		}
		return toReturn;
	}

	public HotelDTO findHotelById(int hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId);
		if (hotel == null) {
			throw new ResourceNotFoundException(Hotel.class.getSimpleName());
		}
		// String[] names = extractNames(usr.getName());

		HotelDTO hoteldto = new HotelDTO.Builder()
				.id(hotel.getId())
				.name(hotel.getName())
				.address(hotel.getAddress())
				.details(hotel.getDetails())
				.contact(hotel.getContact())
				.website(hotel.getWebsite())
				.city_id(hotel.getCityId())
				.ratingavr(hotel.getRatingavr())
				.lat(hotel.getLat())
				.lon(hotel.getLon())
				.rooms(hotel.getRooms())
				.create();
		return hoteldto;
	}

	public HotelRepository create(HotelDTO hotelDTO) {
		System.out.println("Ajunge sa creeze un Hotel?");
		List<String> validationErrors = validateHotel(hotelDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Hotel.class.getSimpleName(), validationErrors);
		}

		Hotel hotel = new Hotel();
		// city.setId(cityDTO.getId());
		hotel.setName(hotelDTO.getName());
		hotel.setAddress(hotelDTO.getAddress());
		hotel.setDetails(hotelDTO.getDetails());
		hotel.setContact(hotelDTO.getContact());
		hotel.setWebsite(hotelDTO.getWebsite());
		hotel.setCityId(hotelDTO.getCity_id());
		hotel.setRatingavr(0.0);
		hotel.setLat(hotelDTO.getLat());
		hotel.setLon(hotelDTO.getLon());
		hotel.setRooms(hotelDTO.getRooms());
//		hotel.setName(hotelDTO.getName());
//		hotel.setAddress(hotelDTO.getAddress());
//		hotel.setDetails(hotelDTO.getDetails());
//		hotel.setContact(hotelDTO.getContact());
//		hotel.setWebsite(hotelDTO.getWebsite());
//		hotel.setCityId(hotelDTO.getCity_id());
//		hotel.setRatingavr(0.0);
//		if(hotelDTO.getLat()!=null)hotel.setLat(hotelDTO.getLat());
//			else hotel.setLat(0.0);
//		if(hotelDTO.getLon()!=null)hotel.setLon(hotelDTO.getLon());
//			else hotel.setLon(0.0);
//		if(hotelDTO.getRooms()==null)hotel.setRooms(hotelDTO.getRooms());
//			else hotel.setRooms(1);
		
		Hotel hotelR = hotelRepository.save(hotel);
		// am schimbat aici ce returnez
		// return city.getId();
		return hotelRepository;
	}

	public void updateHotel(HotelDTO hotelDTO) {
		System.out.println("Ajunge sa faca update la un Hotel?");
		List<String> validationErrors = validateHotel(hotelDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(Hotel.class.getSimpleName(), validationErrors);
		}
		System.out.println("hotelul are "+hotelDTO.getRooms()+ " camere");
		int hotelid = hotelDTO.getId();
		Hotel hotelupd = hotelRepository.findById(hotelid);
		Hotel hotel = new Hotel();
		// city.setId(cityDTO.getId());
		hotel.setId(hotelDTO.getId());
		hotel.setName(hotelDTO.getName());
		hotel.setAddress(hotelDTO.getAddress());
		hotel.setDetails(hotelDTO.getDetails());
		hotel.setContact(hotelDTO.getContact());
		hotel.setWebsite(hotelDTO.getWebsite());
		hotel.setCityId(hotelDTO.getCity_id());
		hotel.setRatingavr(hotelupd.getRatingavr());
		hotel.setLat(hotelDTO.getLat());
		hotel.setLon(hotelDTO.getLon());
		hotel.setRooms(hotelDTO.getRooms());
		Hotel hotelR = hotelRepository.save(hotel);
		// am schimbat aici ce returnez
		// return city.getId();
		// return hotelRepository;
	}

	public void deleteHotelById(int id) {
		System.out.println(id + " Incercam sa stergem hotelul");
		hotelRepository.deleteById(id);
		System.out.println(id + " asta ii ID-ul");
	}

	private List<String> validateHotel(HotelDTO hotel) {
		List<String> validationErrors = new ArrayList<String>();

		if (hotel.getCity_id() == null || "".equals(hotel.getCity_id())) {
			validationErrors.add("City ID field should not be empty");
		}

		if (hotel.getName() == null || "".equals(hotel.getName())) {
			validationErrors.add("Name field should not be empty");
		}

		/*
		 * if (city.getCountry() == null || "".equals(city.getCountry())) {
		 * validationErrors.add("Country does not have the correct format."); }
		 */
		return validationErrors;
	}

// extra

	public List<HotelDTO> findAllHotelsById(Integer city_id) {
		System.out.println("Ce id" + city_id);
		List<Hotel> hotels = hotelRepository.findAllHotelsById(city_id);
		List<HotelDTO> toReturn = new ArrayList<HotelDTO>();
		for (Hotel hotel : hotels) {
			// String comments = savings.getComment();
			HotelDTO dto = new HotelDTO.Builder()
					.id(hotel.getId())
					.name(hotel.getName())
					.address(hotel.getAddress())
					.details(hotel.getDetails())
					.contact(hotel.getContact())
					.website(hotel.getWebsite())
					.city_id(hotel.getCityId())
					.lat(hotel.getLat())
					.lon(hotel.getLon())
					.rooms(hotel.getRooms())
					.create();
			toReturn.add(dto);
		}
		System.out.println("Am terminat de cautat hotelurile dintr-un oras");
		return toReturn;
	}

	public List<ImageModel> findAllHotelImagesById(int hotel_id) {
		int id = hotel_id;
		List<ImageModel> imagesHotels = imageRepository.findAllHotelImagesById(id);

		// List<CommentDTO> toReturn = new ArrayList<CommentDTO>();
		// for (Comment comment :comments) {
		List<ImageModel> imageToReturn = new ArrayList<ImageModel>();
		for (ImageModel imageHotel : imagesHotels) {

			ImageModel imagenew = new ImageModel();
			imagenew.setId(imageHotel.getId());
			imagenew.setName(imageHotel.getName());
			imagenew.setPic(imageHotel.getPic());
			imagenew.setHotel_id(imageHotel.getHotel_id());
			imagenew.setRestaurant_id(imageHotel.getRestaurant_id());
			imagenew.setEvent_id(imageHotel.getEvent_id());
			imagenew.setCity_id(imageHotel.getCity_id());
			imagenew.setUser_id(imageHotel.getUser_id());
			imagenew.setTouristAttraction_id(imageHotel.getTouristAttraction_id());
			imageToReturn.add(imagenew);
		}

		System.out.println("succes");
		return imageToReturn;

	}
}