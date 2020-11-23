package spring.licenta.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import spring.licenta.dto.EventDTO;
import spring.licenta.dto.UserDTO;

@Entity
@Table(name="usersevents")
public class UsersEvents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="user_id", nullable = false)
	private long user_id=0;
	@Column(name="event_id", nullable = false)
	private long event_id=0;

	@Column(name="go_maybe", nullable = false)
	private int goMaybe=0;
	
	@Transient
	Set<User> users=new HashSet<>();
	@Transient
	Set<Event> events=new HashSet<>();
	
	public UsersEvents(long id, long user_id, long event_id, int goMaybe) {
		this.id = id;
		this.user_id = user_id;
		this.event_id = event_id;
		this.goMaybe = goMaybe;
	}
	
	public UsersEvents(long user_id, long event_id, int goMaybe) {
		this.user_id = user_id;
		this.event_id = event_id;
		this.goMaybe = goMaybe;
	}
	
	public UsersEvents(long user_id, long event_id) {
		this.user_id = user_id;
		this.event_id = event_id;
		this.goMaybe = 0;
	}
	public UsersEvents() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUser_id() {
		return user_id;
	}


	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}


	public long getEvent_id() {
		return event_id;
	}


	public void setEvent_id(long event_id) {
		this.event_id = event_id;
	}


	public int getGoMaybe() {
		return goMaybe;
	}


	public void setGoMaybe(int goMaybe) {
		this.goMaybe = goMaybe;
	}



	
}
