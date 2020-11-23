package spring.licenta.dto;

import java.time.LocalDate;

import spring.licenta.dto.HotelDTO.Builder;

public class ReservationDTO {

	private long id;
	private boolean roomBooked;//for hotels
	private boolean tableBooked;//for restaurants
	private String book_name;
	private String details;
	private long user_id;
	private long hotel_id;
	private long restaurant_id;
	private LocalDate reservation_start;
	private LocalDate reservation_end;
	
	private UserDTO user;
	private RestaurantDTO restaurant;
	private HotelDTO hotel;
	
	
	public ReservationDTO(long id, boolean roomBooked, boolean tableBooked, String book_name, String details,
			long user_id, long hotel_id, long restaurant_id, LocalDate reservation_start, LocalDate reservation_end) {
		super();
		this.id = id;
		this.roomBooked = roomBooked;
		this.tableBooked = tableBooked;
		this.book_name = book_name;
		this.details = details;
		this.user_id = user_id;
		this.hotel_id = hotel_id;
		this.restaurant_id = restaurant_id;
		this.reservation_start = reservation_start;
		this.reservation_end = reservation_end;
		this.user=new UserDTO();
	}
	public ReservationDTO(long id, boolean roomBooked, boolean tableBooked, String book_name, String details,
			long user_id, long hotel_id, long restaurant_id, LocalDate reservation_start, LocalDate reservation_end, UserDTO user) {
		super();
		this.id = id;
		this.roomBooked = roomBooked;
		this.tableBooked = tableBooked;
		this.book_name = book_name;
		this.details = details;
		this.user_id = user_id;
		this.hotel_id = hotel_id;
		this.restaurant_id = restaurant_id;
		this.reservation_start = reservation_start;
		this.reservation_end = reservation_end;
		this.user=user;
	}
	public ReservationDTO() {
		
	}
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public boolean isRoomBooked() {
		return roomBooked;
	}


	public void setRoomBooked(boolean roomBooked) {
		this.roomBooked = roomBooked;
	}


	public boolean isTableBooked() {
		return tableBooked;
	}


	public void setTableBooked(boolean tableBooked) {
		this.tableBooked = tableBooked;
	}


	public String getBook_name() {
		return book_name;
	}


	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}


	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public long getHotel_id() {
		return hotel_id;
	}


	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
	}


	public long getRestaurant_id() {
		return restaurant_id;
	}


	public void setRestaurant_id(long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}


	public LocalDate getReservation_start() {
		return reservation_start;
	}


	public void setReservation_start(LocalDate reservation_start) {
		this.reservation_start = reservation_start;
	}


	public LocalDate getReservation_end() {
		return reservation_end;
	}


	public void setReservation_end(LocalDate reservation_end) {
		this.reservation_end = reservation_end;
	}


	public UserDTO getUser() {
		return user;
	}


	public void setUser(UserDTO user) {
		this.user = user;
	}


	public RestaurantDTO getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}


	public HotelDTO getHotel() {
		return hotel;
	}


	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public static class Builder{
		private long nestedid;
		private boolean nestedroomBooked;//for hotels
		private boolean nestedtableBooked;//for restaurants
		private String nestedbook_name;
		private String nesteddetails;
		private long nesteduser_id;
		private long nestedhotel_id;
		private long nestedrestaurant_id;
		private LocalDate nestedreservation_start;
		private LocalDate nestedreservation_end;
		private UserDTO nesteduserDTO;
		
		public Builder id(long id) {
			this.nestedid = id;
			return this;
		}
		public Builder roomBooked(boolean roomBooked) {
			this.nestedroomBooked = roomBooked;
			return this;
		}
		public Builder tableBooked(boolean tableBooked) {
			this.nestedtableBooked = tableBooked;
			return this;
		}
		public Builder book_name(String book_name) {
			this.nestedbook_name = book_name;
			return this;
		}
		public Builder details(String details) {
			this.nesteddetails = details;
			return this;
		}
		public Builder user_id(Long user_id) {
			this.nesteduser_id = user_id;
			return this;
		}
		public Builder hotel_id(Long hotel_id) {
			this.nestedhotel_id = hotel_id;
			return this;
		}
		public Builder restaurant_id(Long restaurant_id) {
			this.nestedrestaurant_id = restaurant_id;
			return this;
		}
		public Builder reservation_start(LocalDate reservation_start) {
			this.nestedreservation_start = reservation_start;
			return this;
		}
		public Builder reservation_end(LocalDate reservation_end) {
			this.nestedreservation_end = reservation_end;
			return this;
		}
		public Builder user(UserDTO userDTO) {
			this.nesteduserDTO = userDTO;
			return this;
		}
		
		public ReservationDTO create() {
			return new ReservationDTO(nestedid, nestedroomBooked,nestedtableBooked,nestedbook_name, nesteddetails, nesteduser_id, 
					nestedhotel_id, nestedrestaurant_id, nestedreservation_start, nestedreservation_end);
		}
		public ReservationDTO createFull() {
			return new ReservationDTO(nestedid, nestedroomBooked,nestedtableBooked,nestedbook_name, nesteddetails, nesteduser_id, 
					nestedhotel_id, nestedrestaurant_id, nestedreservation_start, nestedreservation_end, nesteduserDTO);
		}
	}
	
	
	
}
