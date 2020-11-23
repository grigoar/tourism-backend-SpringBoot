package spring.licenta.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String name;
	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;
	
	

	
	@Size(max = 50)
	private String city;
	
	@Size(max = 50)
	private String country;
	
	@Size(max = 20)
	private String telephone;
	//private String IBAN;
	
	private Date created;


	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@Transient
	private ImageModel userProfileImage=new ImageModel();
	@Transient
	private HashSet<Comment> userComments=new HashSet<>();
	//@Transient
	//private HashSet<Event>userEvents=new HashSet<>(0);
	@Transient
	private Set<Reservation>reservations=new HashSet<>(); 
	
	public User() {
	}

//	public User(String username, String email, String password) {
//		this.username = username;
//		this.email = email;
//		this.password = password;
//	}
//	public User(String name, String email, String username, String password, String city, String country,
//			String telephone) {
//		this.name = name;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.city = city;
//		this.country = country;
//		this.telephone = telephone;
//	}
	
	
	public User(String name2, String telephone2, String city2, String country2, String username2, String email2,
			String encode, Date created) {
		this.name = name2;
		this.username = username2;
		this.email = email2;
		this.password = encode;
		this.city = city2;
		this.country = country2;
		this.telephone = telephone2;
		this.created=created;
	}

	public User(Long id2, String name2, String telephone2, String city2, String country2, String username2,
			String email2, String password2, Date date) {
		this.id=id2;
		this.name = name2;
		this.username = username2;
		this.email = email2;
		this.password =password2;
		this.city = city2;
		this.country = country2;
		this.telephone = telephone2;
		this.created=date;
	}



	public User(long id2, String name2, String telephone2, String city2, String country2, String username2,
			String email2, String encode, Set<Role> roles, Date date) {
		this.id=id2;
		this.name = name2;
		this.username = username2;
		this.email = email2;
		this.password =encode;
		this.city = city2;
		this.country = country2;
		this.telephone = telephone2;
		this.created=date;
		this.roles=roles;
	}

	public User(long id2, String name2, String telephone2, String city2, String country2, String email2) {
		this.id=id2;
		this.name = name2;
		this.email = email2;
		this.city = city2;
		this.country = country2;
		this.telephone = telephone2;
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setCreated(Date created) {
		this.created = created;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [roles=" + roles + "]";
	}
	
}
