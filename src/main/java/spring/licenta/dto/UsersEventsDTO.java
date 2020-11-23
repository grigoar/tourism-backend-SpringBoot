package spring.licenta.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import spring.licenta.entities.Event;
import spring.licenta.entities.User;

public class UsersEventsDTO {

	private long id;
	private long user_id=0;
	private long event_id=0;
	private int goMaybe=0;
	
	Set<UserDTO> users=new HashSet<>();
	Set<EventDTO> events=new HashSet<>();
	
	public UsersEventsDTO(long user_id, long event_id) {
		this.user_id = user_id;
		this.event_id = event_id;
		this.goMaybe = 0;
	}
	
	public UsersEventsDTO(long id, long user_id, long event_id, int goMaybe) {
		this.id = id;
		this.user_id = user_id;
		this.event_id = event_id;
		this.goMaybe = goMaybe;
	}
	
	public UsersEventsDTO(long user_id, long event_id, int goMaybe) {
		this.user_id = user_id;
		this.event_id = event_id;
		this.goMaybe = goMaybe;
	}
	

	public UsersEventsDTO() {
		
	}

	public Set<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}

	public Set<EventDTO> getEvents() {
		return events;
	}

	public void setEvents(Set<EventDTO> events) {
		this.events = events;
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
