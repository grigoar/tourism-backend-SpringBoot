package spring.licenta.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.licenta.dto.UserDTO;
import spring.licenta.services.EmailService;
import spring.licenta.services.UserDetailsServiceImpl;





@CrossOrigin(maxAge = 3600)
@RestController
public class EmailController {
	
	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private EmailService emailService;
	
   @RequestMapping(value = "/sendemail/{id}/{blocat}", method = RequestMethod.POST)
   public String sendEmail(@PathVariable("id") int id,@PathVariable("blocat") Boolean blocat) throws AddressException, MessagingException, IOException {
	   System.out.println("ajunge sa trimita mail?");
	   UserDTO user=new UserDTO();
	   user = userService.findUserById(id);
	   //emailService.sendmail(user.getEmail(),blocat);
      return "Email sent successfully";
   }   
   
}