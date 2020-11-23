package spring.licenta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import spring.licenta.dto.CityDTO.Builder;
import spring.licenta.entities.City;
import spring.licenta.entities.Event;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.Restaurant;
import spring.licenta.entities.TouristAttraction;
import spring.licenta.entities.User;

import javax.persistence.*;


public class ImageDTO {
   
    private Long id;
    private String name;
    private String type;
    private byte[] pic;
    private Integer hotel_id;
	private int restaurant_id;
	private int touristAttraction_id;
   	private int event_id;
	private int city_id;
	private int user_id;
	
	
	public ImageDTO(Long id, String name, String type, byte[] pic, int hotel_id, int restaurant_id, 
			int touristAttraction_id,int event_id, int city_id, int user_id) {
		this.id = id;
		this.name = name;
        this.type = type;
        this.pic = pic;
        this.hotel_id=hotel_id;
        this.restaurant_id=restaurant_id;
        this.event_id=event_id;
        this.city_id=city_id;
        this.user_id=user_id;
        this.touristAttraction_id= touristAttraction_id;
	}

    

//Custom Construtor
    public ImageDTO(String name, String type, byte[] pic) {
        this.name = name;
        this.type = type;
        this.pic = pic;
    }

	public ImageDTO(String name, String type, byte[] pic, int hotel_id, int restaurant_id, int touristAttraction_id, int event_id, int city_id, int user_id) {
		this.name = name;
        this.type = type;
        this.pic = pic;
        this.hotel_id=hotel_id;
        this.restaurant_id=restaurant_id;
        this.event_id=event_id;
        this.city_id=city_id;
        this.user_id=user_id;
        this.touristAttraction_id= touristAttraction_id;
	}
	

	public ImageDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	public int getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public int getTouristAttraction_id() {
		return touristAttraction_id;
	}

	public void setTouristAttraction_id(int touristAttraction_id) {
		this.touristAttraction_id = touristAttraction_id;
	}
	
	public void setEvent_id(int event_id) {
		this.event_id=event_id;
	}

	public int getEvent_id() {
		return this.event_id;
	}
	
	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	
}