package spring.licenta.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

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
	
	//private User user=new User();
	//private Restaurant restaurant;
	//private Hotel hotel;
	public Reservation() {
		
	}
	public Reservation(long id, boolean roomBooked, boolean tableBooked, String book_name, String details, long user_id,
			long hotel_id, long restaurant_id, LocalDate reservation_start, LocalDate reservation_end) {
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
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY) 
	@Column(name="idReservation", unique = true, nullable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="tableBooked", nullable = false)
	public boolean isTableBooked() {
		return tableBooked;
	}

	public void setTableBooked(boolean tableBooked) {
		this.tableBooked = tableBooked;
	}

	@Column(name="roomBooked", nullable = false)
	public boolean isRoomBooked() {
		return roomBooked;
	}

	public void setRoomBooked(boolean roomBooked) {
		this.roomBooked = roomBooked;
	}

	@Column(name="book_name", nullable = false)
	public String getBook_name() {
		return book_name;
	}


	public void setBook_name(String user_name) {
		this.book_name = user_name;
	}

	@Column(name="details", nullable = true)
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	@Column(name="user_id", nullable = false)
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	@Column(name="hotel_id", nullable = true)
	public long getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(long hotel_id) {
		this.hotel_id = hotel_id;
	}
	
	@Column(name="restaurant_id", nullable = true)
	public long getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
	@Column(name="reservation_start", nullable = false)
	public LocalDate getReservation_start() {
		return reservation_start;
	}
	public void setReservation_start(LocalDate reservation_start) {
		this.reservation_start = reservation_start;
	}
	
	@Column(name="reservation_end", nullable = false)
	public LocalDate getReservation_end() {
		return reservation_end;
	}
	public void setReservation_end(LocalDate reservation_end) {
		this.reservation_end = reservation_end;
	}

	
	
}
