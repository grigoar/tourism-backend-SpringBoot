package spring.licenta.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String details;
	private Date date;
	private Integer restaurant_id=0;
	private Integer hotel_id=0;
	private Integer event_id=0;
	private Integer touristAttraction_id=0;
	private Integer user_id;
	private Integer rating;
	
	private String commentusername;
	
	public Comment() {
		
	};
	
	public Comment(Integer id, String details, Date date,Integer restaurant_id,Integer hotel_id,
			Integer event_id, Integer touristAttraction_id, Integer user_id, String commentusername){
		super();
		this.id=id;
		this.details=details;
		this.setDate(date);
		this.setRestaurant_id(restaurant_id);
		this.setHotel_id(hotel_id);
		this.setEvent_id(event_id);
		this.setTouristAttraction_id(touristAttraction_id);
		this.user_id=user_id;
		this.commentusername=commentusername;
		
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	@Column(name="idComments", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Column(name="details", nullable = false)
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Column(name="date", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="restaurant_id")
	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	@Column(name="hotel_id" )
	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}

	@Column(name="event_id")
	public Integer getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	@Column(name="touristAttraction_id")
	public Integer getTouristAttraction_id() {
		return touristAttraction_id;
	}

	public void setTouristAttraction_id(Integer touristAttraction_id) {
		this.touristAttraction_id = touristAttraction_id;
	}

	@Column(name="user_id", nullable = false)
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}


	@Column(name="commentUsername", nullable = true)
	public String getCommentUsername() {
		return commentusername;
	}
	
	public void setCommentUsername(String commentusername) {
		this.commentusername = commentusername;
	}
	
	@Column(name="rating")
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

}
