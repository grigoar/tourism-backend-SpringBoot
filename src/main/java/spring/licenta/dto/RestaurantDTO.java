package spring.licenta.dto;

import java.util.HashSet;
import java.util.Set;

import spring.licenta.dto.HotelDTO.Builder;
import spring.licenta.entities.City;
import spring.licenta.entities.Comment;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Reservation;
import spring.licenta.entities.TypeOfFood;

public class RestaurantDTO {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String address;
	private String details;
	private String contact;
	private String website;
	private Integer city_id;
	private Integer tables=1;
	
	private Double ratingavr=0.0;
	private Double lat=0.0;
	private Double lon=0.0;
	
	private Set<CommentDTO> restaurantComments=new HashSet<>();
	private Set<ImageDTO> restaurantImages=new HashSet<>();
	private Set<TypeOfFoodDTO>typesOfFood=new HashSet<>();
	private Set<ReservationDTO> reservationsRestaurant=new HashSet<>();
	
	public RestaurantDTO(Integer id, String name,String address, String details, String contact,
			String website,  Integer city_id, Double ratingavr, Double lat, Double lon,Integer tables){
		this.setId(id);
		this.setName(name);
		this.setAddress(address);
		this.setDetails(details);
		this.setContact(contact);
		this.setWebsite(website);
		this.setCity_id(city_id);
		this.setRatingavr(ratingavr);
		this.setLat(lat);
		this.setLon(lon);
		this.setTables(tables);
		
	}


public RestaurantDTO() {
	
};


public Set<CommentDTO> getRestaurantComments() {
	return restaurantComments;
}


public void setRestaurantComments(Set<CommentDTO> restaurantComments) {
	this.restaurantComments = restaurantComments;
}




public Set<TypeOfFoodDTO> getTypesOfFood() {
	return typesOfFood;
}


public void setTypesOfFood(Set<TypeOfFoodDTO> typesOfFood) {
	this.typesOfFood = typesOfFood;
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

public String getDetails() {
	return details;
}

public void setDetails(String details) {
	this.details = details;
}

public String getContact() {
	return contact;
}

public void setContact(String contact) {
	this.contact = contact;
}

public String getWebsite() {
	return website;
}

public void setWebsite(String website) {
	this.website = website;
}

public Integer getCity_id() {
	return city_id;
}

public void setCity_id(Integer city_id) {
	this.city_id = city_id;
}
public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}
public Integer getTables() {
	return tables;
}


public void setTables(Integer tables) {
	this.tables = tables;
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
public Set<ImageDTO> getRestaurantImages() {
	return restaurantImages;
}


public void setRestaurantImages(Set<ImageDTO> restaurantImages) {
	this.restaurantImages = restaurantImages;
}

public Set<ReservationDTO> getReservationsRestaurant() {
	return reservationsRestaurant;
}


public void setReservationsRestaurant(Set<ReservationDTO> reservationsRestaurant) {
	this.reservationsRestaurant = reservationsRestaurant;
}

public static class Builder{
	private Integer nestedid;
	private String nestedname;
	private String nestedaddress;
	private String nesteddetails;
	private String nestedcontact;
	private String nestedwebsite;
	private Integer nestedcity_id;
	private Double nestedratingavr;
	private Double nestedlat;
	private Double nestedlon;
	private Integer nestedtables;
	
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
	public Builder contact(String contact) {
		this.nestedcontact = contact;
		return this;
	}
	public Builder website(String website) {
		this.nestedwebsite = website;
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
	public Builder tables(Integer tables) {
		this.nestedtables =tables;
		return this;
	}
	public RestaurantDTO create() {
		return new RestaurantDTO(nestedid, nestedname,nestedaddress, nesteddetails, nestedcontact, nestedwebsite, nestedcity_id, nestedratingavr, nestedlat, nestedlon, nestedtables);
	}
	
}
}