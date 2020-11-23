package spring.licenta.dto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import spring.licenta.entities.Comment;
import spring.licenta.entities.Event;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Reservation;
import spring.licenta.entities.Role;

public class UserDTO {
	
	private Long id;
	private String name;
	private String username;
	private String password;
	private String email;
	
	private String telephone;
	private Date created;
	private String country;
	private String city;
	private List<GrantedAuthority> roles;
	
	private ImageDTO userProfileImage;
	private Set<CommentDTO> userComments=new HashSet<>();
	private Set<ReservationDTO> userReservations=new HashSet<>(); 
	
	public UserDTO(Long id, String name, String username, String email, String password,
			String city, String country, String telephone,List<GrantedAuthority> roles) {
		this.id = id;
		this.name=name;
		this.email = email;
		this.username=username;
		this.password=password;
		this.city = city;
		this.country = country;
		this.telephone = telephone;
		this.roles=roles;
	}
	
	public UserDTO() {
		
	}
	public UserDTO(Long id, String username, String email, String password,List<GrantedAuthority> authorities) {
		this.setId(id);
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		this.setRoles(authorities);
	}

	


	public UserDTO(Long id2, String username2, String email2, String password2) {
		this.setId(id);
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
		
	}



	public ImageDTO getUserProfileImage() {
		return userProfileImage;
	}

	public void setUserProfileImage(ImageDTO userProfileImage) {
		this.userProfileImage = userProfileImage;
	}

	public Set<CommentDTO> getUserComments() {
		return userComments;
	}

	public void setUserComments(Set<CommentDTO> userComments) {
		this.userComments = userComments;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<GrantedAuthority> getRoles() {
		return roles;
	}

	public void setRoles(List<GrantedAuthority> roles) {
		this.roles = roles;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreated() {
		return created;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ReservationDTO> getUserReservations() {
		return userReservations;
	}

	public void setUserReservations(Set<ReservationDTO> userReservations) {
		this.userReservations = userReservations;
	}

	
	


	




}
