package spring.licenta.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import spring.licenta.dto.RestaurantDTO.Builder;
import spring.licenta.entities.City;
import spring.licenta.entities.Comment;
import spring.licenta.entities.ImageModel;



public class TouristAttractionDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;

	private String address;
	private String details;
	private Integer city_id;
	
	private Double ratingavr=0.0;
	private Double lat=0.0;
	private Double lon=0.0;
	
	private Set<CommentDTO>touristAttractionComments=new HashSet<>();
	private Set<ImageDTO> touristAttractionImages=new HashSet<>();
	
	public TouristAttractionDTO(Integer id, String name,String address, String details, 
			Integer city_id,Double ratingavr, Double lat, Double lon){
		this.setId(id);
		this.setName(name);
		this.setAddress(address);
		this.setDetails(details);
		this.setCity_id(city_id);
		this.ratingavr=ratingavr;
		this.lat=lat;
		this.lon=lon;
	}
	
	public TouristAttractionDTO() {
		
	};
	
	

		public Set<CommentDTO> getTouristAttractionComments() {
		return touristAttractionComments;
	}

	public void setTouristAttractionComments(Set<CommentDTO> touristAttractionComments) {
		this.touristAttractionComments = touristAttractionComments;
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

		public Double getRatingavr() {
		return ratingavr;
	}

	public void setRatingavr(Double ratingavr) {
		this.ratingavr = ratingavr;
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

		public Set<ImageDTO> getTouristAttractionImages() {
		return touristAttractionImages;
	}

	public void setTouristAttractionImages(Set<ImageDTO> touristAttractionImages) {
		this.touristAttractionImages = touristAttractionImages;
	}

		public static class Builder{
		private Integer nestedid;
		private String nestedname;
		private String nestedaddress;
		private String nesteddetails;
		private Integer nestedcity_id;
		private Double nestedratingavr;
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
		public Builder ratingavr(Double ratingavr) {
			this.nestedratingavr = ratingavr;
			return this;
		}
		public Builder lat(Double lat) {
			this.nestedlat = lat;
			return this;
		}
		public Builder lon(Double lon) {
			this.nestedlon = lon;
			return this;
		}
		public TouristAttractionDTO create() {
			return new TouristAttractionDTO(nestedid, nestedname,nestedaddress, nesteddetails, nestedcity_id,
					nestedratingavr,nestedlat,nestedlon);
		}
		
	}
}