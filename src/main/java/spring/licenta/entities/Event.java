package spring.licenta.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
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
@Table(name = "events")
public class Event implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date start_time;
	private String address;
	private String details;
	private Integer city_id;
	private Long going;
	private Long maybe;
	private Double lat=0.0;
	private Double lon=0.0;
	
	@Transient
	private Set<Comment> eventComments=new HashSet<>();	
	@Transient
	private Set<ImageModel> eventImages=new HashSet<>();

	public Event() {
		
	};
	
	public Event(Integer id, String name,Date start_time, String address, String details, Integer city_id,
			Long going, Long maybe, Double lat, Double lon){
		super();
		this.id=id;
		this.name=name;
		this.start_time=start_time;
		this.address=address;
		this.details=details;
		this.city_id=city_id;
		this.going=going;
		this.maybe=maybe;
		this.lat=lat;
		this.lon=lon;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	@Column(name="idEvents", unique = true, nullable = false)
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
	
	@Column(name="start_time", nullable = false)
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	@Column(name="address", nullable = true)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address =address;
	}
	@Column(name="details", nullable = true)
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details =details;
	}
	@Column(name = "city_id", nullable = false, length = 200)
	public int getCityId() {
		return city_id;
	}

	public void setCityId(int city_id) {
		this.city_id = city_id;
	}
	
	@Column(name="going", nullable = true)
	public Long getGoing() {
		return going;
	}

	public void setGoing(Long going) {
		this.going = going;
	}
	
	@Column(name="maybe", nullable = true)
	public Long getMaybe() {
		return maybe;
	}

	public void setMaybe(Long maybe) {
		this.maybe = maybe;
	}
	
	@Column(name="lat", nullable = true)
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Column(name="lon", nullable = true)
	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	

}