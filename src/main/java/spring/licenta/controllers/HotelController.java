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

import spring.licenta.dto.HotelDTO;
import spring.licenta.entities.ImageModel;
import spring.licenta.jwt.MessageResponse;
import spring.licenta.repositories.HotelRepository;
import spring.licenta.services.HotelService;
import spring.licenta.services.ImageService;



@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/hotel")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<HotelDTO> getAllHotels() {
		return hotelService.findAll();
	}
	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<HotelDTO> getAllHotelsById(@PathVariable("id") int id) {
		System.out.println("cautam toate hotelurile dintr-un oras");
		return hotelService.findAllHotelsById(id);
	}
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public HotelDTO getHotelById(@PathVariable("id") int id) {
		return hotelService.findHotelById(id);
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	  public HotelRepository insertHotel(@RequestBody HotelDTO hotelDTO) {
		  System.out.println("Poate se intampla ceva");
		  System.out.println("date hotel"+hotelDTO.getId()+hotelDTO.getName()+hotelDTO.getAddress()+hotelDTO.getDetails()+
				  hotelDTO.getContact()+hotelDTO.getWebsite()+hotelDTO.getCity_id()+hotelDTO.getLat()+hotelDTO.getLon()+hotelDTO.getRooms()); 
		  return hotelService.create(hotelDTO); 
	  }
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editHotel(@RequestBody HotelDTO hotelDTO){
		//UserDTO user = userService.findUserById(id);
		System.out.println("Ajunge aici?1 edit hotel");
		//userDTO.setId(id);
		hotelService.updateHotel(hotelDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeHotel(@PathVariable("id") int id) {
		System.out.println("Am intrat");
		HotelDTO hotel = hotelService.findHotelById(id);
		if (hotel == null) {
			System.out.println("Unable to delete. Hotel with id " + id + " not found");
			// return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		System.out.println("Inainte de stergere");
		hotelService.deleteHotelById(id);
		System.out.println("City sters");
		// return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
	
	//pentru imagini
	// adaugare imagini in functie de hotel_id
		@PostMapping("/upload/{hotel_id}")
		@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
		public ImageModel uplaodImage(@PathVariable("hotel_id") int hotel_id, @RequestParam("myFile") MultipartFile file) throws IOException {

	       // ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes(),0,0,0, city_id,0 );


	      //  final ImageModel savedImage = imageRepository.save(img);

			ImageModel savedImage = imageService.createHotelImages(file,hotel_id);
	        System.out.println("Image saved");


	        return savedImage;


	    }

		@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
		public List<ImageModel> getAllHotelImagesById(@PathVariable("id") int id) {
			System.out.println("am ajuns sa returnam imaginile hotelului");
			return hotelService.findAllHotelImagesById(id);
		}
}
