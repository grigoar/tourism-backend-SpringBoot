package spring.licenta.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// import spring.demo.dto.UserDTO;
import spring.licenta.entities.ImageModel;
import spring.licenta.repositories.ImageRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	ImageRepository imageRepository;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
//	public void editUser(@PathVariable("id") int id,@RequestBody UserDTO userDTO){
//		//UserDTO user = userService.findUserById(id);
//		System.out.println("Ajunge aici?1");
//		userDTO.setId(id);
//		userService.updateUser(userDTO);
//	}
	@PostMapping("/upload")
    public ImageModel uplaodImage(@PathVariable("city_id") int city_id, @RequestParam("myFile") MultipartFile file) throws IOException {

        ImageModel img = new ImageModel( file.getOriginalFilename(),file.getContentType(),file.getBytes() );


        final ImageModel savedImage = imageRepository.save(img);


        System.out.println("Image saved");


        return savedImage;


    }
}
