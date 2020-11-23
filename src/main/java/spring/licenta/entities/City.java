package spring.licenta.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import spring.licenta.dto.EventDTO;
import spring.licenta.dto.HotelDTO;
import spring.licenta.dto.ImageDTO;
import spring.licenta.dto.RestaurantDTO;
import spring.licenta.dto.TouristAttractionDTO;

@Entity
@Table(name = "cities")
public class City implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String country;
	private String description;
	private Double lat;
	private Double lon;

	@Transient
	private Set<ImageModel> cityImages=new HashSet<>();
	@Transient
	private Set<Event>events=new HashSet<>();
	@Transient
	private Set<Hotel>hotels=new HashSet<>();
	@Transient
	private Set<Restaurant> restaurants=new HashSet<>();
	@Transient
	private Set<TouristAttraction>touristAttractions=new HashSet<>();
	
	public City() {

	};

	public City(Integer id, String name, String country, String description, Double lat, Double lon) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.description=description;
		this.lat = lat;
		this.lon = lon;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcities", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "country", nullable = false)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name = "description", nullable = false)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "lat", nullable = true)
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Column(name = "lon", nullable = true)
	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

}
