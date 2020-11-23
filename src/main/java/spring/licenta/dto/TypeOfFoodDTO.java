package spring.licenta.dto;

import spring.licenta.entities.Restaurant;

public class TypeOfFoodDTO {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type_of_food;
	private String details;//might be null
	private Integer restaurant_id;
	
	
	public TypeOfFoodDTO() {
		
	};
	
	public TypeOfFoodDTO(Integer id, String type_of_food, String details, Integer restaurant_id){
		super();
		this.setId(id);
		this.setType_of_food(type_of_food);
		this.setDetails(details);
		this.setRestaurant_id(restaurant_id);
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
	public static class Builder{
		private Integer nestedid;
		private String nestedtype_of_food;
		private String nesteddetails;
		private Integer nestedrestaurant_id;
		
		public Builder id(int id) {
			this.nestedid = id;
			return this;
		}
		public Builder type_of_food(String type_of_food) {
			this.nestedtype_of_food = type_of_food;
			return this;
		}
		public Builder details(String details) {
			this.nesteddetails = details;
			return this;
		}
		public Builder restaurant_id(int restaurant_id) {
			this.nestedrestaurant_id = restaurant_id;
			return this;
		}
		
		public TypeOfFoodDTO create() {
			return new TypeOfFoodDTO(nestedid,nestedtype_of_food,nesteddetails,nestedrestaurant_id);
		}
		
	}
}
