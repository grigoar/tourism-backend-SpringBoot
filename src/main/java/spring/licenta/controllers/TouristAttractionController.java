package spring.licenta.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.licenta.dto.RestaurantDTO;
import spring.licenta.dto.TouristAttractionDTO;
import spring.licenta.entities.ImageModel;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.TouristAttractionRepository;
import spring.licenta.services.ImageService;
import spring.licenta.services.TouristAttractionService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/touristAttraction")
public class TouristAttractionController {

	@Autowired
	private TouristAttractionService touristAttractionService;
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<TouristAttractionDTO> getAllHotels() {
		return touristAttractionService.findAll();
	}

	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<TouristAttractionDTO> getAllTouristAttractionsById(@PathVariable("id") int id) {
		System.out.println("cautam toate Tourist Attractions dintr-un oras");
		return touristAttractionService.findAllTouristAttractionsById(id);
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public TouristAttractionDTO getHotelById(@PathVariable("id") int id) {
		return touristAttractionService.findTouristAttractionById(id);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@PreAuthorize("hhasRole('MODERATOR') or hasRole('ADMIN')")
	public TouristAttractionRepository insertTouristAttraction(@RequestBody TouristAttractionDTO touristAttractionDTO) {
		System.out.println("Poate se intampla ceva");
		System.out.println("date touristAttraction" + touristAttractionDTO.getId() + touristAttractionDTO.getName()
				+ touristAttractionDTO.getAddress() + touristAttractionDTO.getDetails()
				+ touristAttractionDTO.getCity_id());
		return touristAttractionService.create(touristAttractionDTO);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editTouristAttraction(@RequestBody TouristAttractionDTO touristAttractionDTO) {
		// UserDTO user = userService.findUserById(id);
		System.out.println("Ajunge aici?1 edit hotel");
		// userDTO.setId(id);
		touristAttractionService.updateTouristAttraction(touristAttractionDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeTouristAttraction(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		TouristAttractionDTO touristAttraction = touristAttractionService.findTouristAttractionById(id);
		if (touristAttraction == null) {
			System.out.println("Unable to delete. TouristAttraction with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		touristAttractionService.deleteTouristAttractionById(id);
		System.out.println("TouristAttraction sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// pentru imagini
	// adaugare imagini in functie de touristAttraction_id
	@PostMapping("/upload/{touristAttraction_id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ImageModel uplaodImage(@PathVariable("touristAttraction_id") int touristAttraction_id,
			@RequestParam("myFile") MultipartFile file) throws IOException {

		// ImageModel img = new ImageModel(
		// file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0,
		// city_id,0 );

		// final ImageModel savedImage = imageRepository.save(img);

		ImageModel savedImage = imageService.createTouristAttractionImages(file, touristAttraction_id);
		System.out.println("Image saved");

		return savedImage;

	}

	@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
	public List<ImageModel> getAllTouristAttractionImagesById(@PathVariable("id") int id) {
		return touristAttractionService.findAllTouristAttractionImagesById(id);
	}
}
