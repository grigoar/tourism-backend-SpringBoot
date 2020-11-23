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

import spring.licenta.dto.CommentDTO;
import spring.licenta.dto.ImageDTO;

@Entity
@Table(name = "hotels")
public class Hotel implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String address;
	private String details;
	private String contact;
	private String website;
	private Integer city_id;
	private Double ratingavr;
	private Double lat;
	private Double lon;
	private int rooms;
	
	
	@Transient
	private Set<Comment> hotelComments=new HashSet<>();
	@Transient
	private Set<ImageModel> hotelImages=new HashSet<>();
	@Transient
	private Set<Reservation> reservationsHotel=new HashSet<>();
	
	public Hotel() {
		
	};
	
	public Hotel(Integer id, String name,String address, String details, String contact, String website, Integer city_id){
		super();
		this.id=id;
		this.name=name;
		this.address=address;
		this.details=details;
		this.contact=contact;
		this.website=website;
		this.city_id=city_id;
	}
	public Hotel(Integer id, String name,String address, String details, String contact, String website, Integer city_id, Double ratingavr){
		super();
		this.id=id;
		this.name=name;
		this.address=address;
		this.details=details;
		this.contact=contact;
		this.website=website;
		this.city_id=city_id;
		this.ratingavr=ratingavr;
	}
	public Hotel(Integer id, String name,String address, String details, String contact, 
			String website, Integer city_id, Double ratingavr, Double lat, Double lon, Integer rooms){
		super();
		this.id=id;
		this.name=name;
		this.address=address;
		this.details=details;
		this.contact=contact;
		this.website=website;
		this.city_id=city_id;
		this.ratingavr=ratingavr;
		this.lat=lat;
		this.lon=lon;
		this.rooms=rooms;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	@Column(name="idHotels", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="details", nullable = true)
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Column(name="contact", nullable = true)
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact =contact;
	}
	@Column(name="website", nullable = true)
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website =website;
	}
	@Column(name = "city_id", nullable = false, length = 200)
	public int getCityId() {
		return city_id;
	}

	public void setCityId(int city_id) {
		this.city_id = city_id;
	}
	
	@Column(name = "address", nullable = false, length = 200)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "ratingavr")
	public Double getRatingavr() {
		return ratingavr;
	}
	
	public void setRatingavr(Double ratingavr) {
		this.ratingavr = ratingavr;
	}
	
	@Column(name = "lat")
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	@Column(name = "lon")
	public Double getLon() {
		return lon;
	}
	
	public void setLon(Double lon) {
		this.lon = lon;
	}

	@Column(name = "rooms")
	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	

}
