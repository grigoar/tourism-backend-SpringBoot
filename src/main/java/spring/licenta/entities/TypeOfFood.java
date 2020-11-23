package spring.licenta.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "type_of_food")
public class TypeOfFood implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	private Integer id;
	
	@Column(name="type_of_food", nullable = false)
	private String type_of_food;
	
	@Column(name="details", nullable = true)
	private String details;//might be null
	
	@Column(name="restaurant_id", nullable = false)
	private Integer restaurant_id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id_foreign", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Restaurant restaurant;
	
	public TypeOfFood() {
		
	};
	
	public TypeOfFood(Integer id, String type_of_food, String details, Integer restaurant_id, Restaurant restaurant){
		super();
		this.id=id;
		this.type_of_food=type_of_food;
		this.details=details;
		this.setRestaurant_id(restaurant_id);
		this.restaurant= restaurant;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getType_of_food() {
		return type_of_food;
	}
	public void setType_of_food(String type_of_food) {
		this.type_of_food = type_of_food;
	}
	
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	
	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
	
	
	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}