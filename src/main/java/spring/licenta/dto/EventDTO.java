package spring.licenta.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import spring.licenta.dto.HotelDTO.Builder;
import spring.licenta.entities.City;
import spring.licenta.entities.Comment;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.User;



public class EventDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date start_time;
	private String address;
	private String details;
	private Integer city_id;
	private Long going;
	private Long maybe;
	private Double lat;
	private Double lon;
	
	private Set<CommentDTO> eventComments=new HashSet<>();
	private Set<ImageDTO> eventImages=new HashSet<>();
	
	public EventDTO(Integer id, String name,Date start_time,String address, String details, 
			Integer city_id,Long going, Long maybe, Double lat, Double lon){
		this.setId(id);
		this.setName(name);
		this.setStart_time(start_time);
		this.setAddress(address);
		this.setDetails(details);
		this.setCity_id(city_id);
		this.setGoing(going);
		this.setMaybe(maybe);
		this.setLat(lat);
		this.setLon(lon);
	}

	
	public EventDTO() {
		
	};
	
	public Set<CommentDTO> getEventComments() {
		return eventComments;
	}


	public void setEventComments(Set<CommentDTO> eventComments) {
		this.eventComments = eventComments;
	}


	public Set<ImageDTO> getEventImages() {
		return eventImages;
	}


	public void setEventImages(Set<ImageDTO> eventImages) {
		this.eventImages = eventImages;
	}
	
		public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

		public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

		public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

		public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

		public Long getMaybe() {
		return maybe;
	}

	public void setMaybe(Long maybe) {
		this.maybe = maybe;
	}

		public Long getGoing() {
		return going;
	}

	public void setGoing(Long going) {
		this.going = going;
	}

		public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

		public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

		public static class Builder{
		private Integer nestedid;
		private String nestedname;
		private Date nestedstart_time;
		private String nestedaddress;
		private String nesteddetails;
		private Integer nestedcity_id;
		private Long nestedgoing;
		private Long nestedmaybe;
		private Double nestedlat;
		private Double nestedlon;
		
		public Builder id(int id) {
			this.nestedid = id;
			return this;
		}
		public Builder name(String name) {
			this.nestedname = name;
			return this;
		}
		public Builder start_time(Date start_time) {
			this.nestedstart_time=start_time;
			return this;
		}
		public Builder details(String details) {
			this.nesteddetails = details;
			return this;
		}
		public Builder city_id(Integer city_id) {
			this.nestedcity_id = city_id;
			return this;
		}
		public Builder address(String address) {
			this.nestedaddress = address;
			return this;
		}
		public Builder going(Long going) {
			this.nestedgoing=going;
			return this;
		}
		public Builder maybe(Long maybe) {
			this.nestedmaybe=maybe;
			return this;
		}
		public Builder lat(double lat) {
			this.nestedlat = lat;
			return this;
		}
		public Builder lon(double lon) {
			this.nestedlon = lon;
			return this;
		}
		
		public EventDTO create() {
			return new EventDTO(nestedid, nestedname,nestedstart_time,nestedaddress, nesteddetails, nestedcity_id, nestedgoing, nestedmaybe, nestedlat, nestedlon);
		}
		
	}
}
