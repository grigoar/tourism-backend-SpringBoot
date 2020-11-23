package spring.licenta.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import spring.licenta.entities.Event;
import spring.licenta.entities.Hotel;
import spring.licenta.entities.Restaurant;
import spring.licenta.entities.TouristAttraction;
import spring.licenta.entities.User;

public class CommentDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String details;
	private Date date;
	private Integer restaurant_id = 0;
	private Integer hotel_id = 0;
	private Integer event_id = 0;
	private Integer touristAttraction_id = 0;
	private Integer user_id;
	private String commentusername;
	private Integer rating;
	

	public CommentDTO(Integer id, String details, Date date, Integer restaurant_id, Integer hotel_id, Integer event_id,
			Integer touristAttraction_id, Integer user_id, String commentusername, Integer rating) {
		this.setId(id);
		this.setDetails(details);
		this.setDate(date);
		this.setRestaurant_id(restaurant_id);
		this.setHotel_id(hotel_id);
		this.setEvent_id(event_id);
		this.setTouristAttraction_id(touristAttraction_id);
		this.setUser_id(user_id);
		this.setCommentUsername(commentusername);
		this.setRating(rating);
	}

	public CommentDTO() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	public Integer getTouristAttraction_id() {
		return touristAttraction_id;
	}

	public void setTouristAttraction_id(Integer touristAttraction_id) {
		this.touristAttraction_id = touristAttraction_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getCommentUsername() {
		return commentusername;
	}

	private void setCommentUsername(String commentusername) {
		this.commentusername = commentusername;

	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public static class Builder {
		private Integer nestedid;
		private String nesteddetails;
		private Date nesteddate;
		private Integer nestedrestaurant_id;
		private Integer nestedhotel_id;
		private Integer nestedevent_id;
		private Integer nestedtouristAttraction_id;
		private Integer nesteduser_id;
		private String nestedcommentusername;
		private Integer nestedrating;

		public Builder id(int id) {
			this.nestedid = id;
			return this;
		}

		public Builder details(String details) {
			this.nesteddetails = details;
			return this;
		}

		public Builder date(Date date) {
			this.nesteddate = date;
			return this;
		}

		public Builder restaurant_id(Integer restaurant_id) {
			this.nestedrestaurant_id = restaurant_id;
			return this;
		}

		public Builder hotel_id(int hotel_id) {
			this.nestedhotel_id = hotel_id;
			return this;
		}

		public Builder event_id(int event_id) {
			this.nestedevent_id = event_id;
			return this;
		}

		public Builder touristAttraction_id(int touristAttraction_id) {
			this.nestedtouristAttraction_id = touristAttraction_id;
			return this;
		}

		public Builder user_id(int user_id) {
			this.nesteduser_id = user_id;
			return this;
		}

		public Builder commentusername(String commentusername) {
			this.nestedcommentusername = commentusername;
			return this;
		}

		public Builder rating(Integer rating) {
			this.nestedrating = rating;
			return this;
		}

		public CommentDTO create() {
			return new CommentDTO(nestedid, nesteddetails, nesteddate, nestedrestaurant_id, nestedhotel_id,
					nestedevent_id, nestedtouristAttraction_id, nesteduser_id, nestedcommentusername, nestedrating);
		}

	}
}
