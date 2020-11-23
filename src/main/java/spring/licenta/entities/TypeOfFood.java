package spring.licenta.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_of_food")
public class TypeOfFood implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type_of_food;
	private String details;//might be null
	private Integer restaurant_id;
	
	public TypeOfFood() {
		
	};
	
	public TypeOfFood(Integer id, String type_of_food, String details, Integer restaurant_id){
		super();
		this.id=id;
		this.type_of_food=type_of_food;
		this.details=details;
		this.setRestaurant_id(restaurant_id);
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	@Column(name="id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="type_of_food", nullable = false)
	public String getType_of_food() {
		return type_of_food;
	}
	public void setType_of_food(String type_of_food) {
		this.type_of_food = type_of_food;
	}
	
	@Column(name="details", nullable = true)
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name="restaurant_id", nullable = false)
	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	

}