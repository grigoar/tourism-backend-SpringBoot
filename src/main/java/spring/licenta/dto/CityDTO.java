package spring.licenta.dto;

import java.util.HashSet;
import java.util.Set;

import spring.licenta.entities.Event;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.ImageModel;
import spring.licenta.entities.Restaurant;
import spring.licenta.entities.TouristAttraction;

public class CityDTO {

	private Integer id;
	private String name;
	private String country;
	private String description;// might be null
	private Double lat;
	private Double lon;
	
	private Set<ImageDTO> cityImages=new HashSet<>();
	private Set<EventDTO>events=new HashSet<>();
	private Set<HotelDTO>hotels=new HashSet<>();
	private Set<RestaurantDTO> restaurants=new HashSet<>();
	private Set<TouristAttractionDTO>touristAttractions=new HashSet<>();
	
	public CityDTO(Integer id, String name, String country, String description,
			Double lat, Double lon){
		this.id=id;
		this.name=name;
		this.country=country;
		this.description=description;
		this.lat=lat;
		this.lon=lon;
	}
	
	public CityDTO() {
		
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
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<ImageDTO> getCityImages() {
		return cityImages;
	}

	public void setCityImages(Set<ImageDTO> cityImages) {
		this.cityImages = cityImages;
	}

	public Set<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(Set<EventDTO> events) {
		this.events = events;
	}

	public Set<HotelDTO> getHotels() {
		return hotels;
	}

	public void setHotels(Set<HotelDTO> hotels) {
		this.hotels = hotels;
	}

	public Set<RestaurantDTO> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<RestaurantDTO> restaurants) {
		this.restaurants = restaurants;
	}

	public Set<TouristAttractionDTO> getTouristAttractions() {
		return touristAttractions;
	}

	public void setTouristAttractions(Set<TouristAttractionDTO> touristAttractions) {
		this.touristAttractions = touristAttractions;
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
		private String nestedcountry;
		private String nesteddescription;
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
		public Builder country(String country) {
			this.nestedcountry = country;
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
		public Builder description(String description) {
			this.nesteddescription=description;
			return this;
		}
		public CityDTO create() {
			return new CityDTO(nestedid,nestedname,nestedcountry,nesteddescription, nestedlat, nestedlon);
		}
		
	}
}
