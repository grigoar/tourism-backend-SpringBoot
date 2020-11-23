package spring.licenta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.licenta.entities.Event;
import spring.licenta.entities.UsersEvents;
import spring.licenta.repositories.EventRepository;
import spring.licenta.repositories.UsersEventsRepository;

@Service
@Transactional
public class UsersEventsService {

	@Autowired
	UsersEventsRepository usersEventsR;
	
	@Autowired
	EventRepository eventRepository;
	
	public UsersEvents addUserEvent(UsersEvents toAdd) {
		
		long maybe=0, going=0, notGoing=0;
		
		UsersEvents userEventAdd=new UsersEvents(toAdd.getUser_id(), toAdd.getEvent_id(),toAdd.getGoMaybe());
		
		usersEventsR.save(userEventAdd);
		
		List<UsersEvents> usersEvents=usersEventsR.findAllEventEntries(toAdd.getEvent_id());
		
		for(UsersEvents userEvent:usersEvents) {
			if(userEvent.getGoMaybe()==2) {
				maybe++;
			}else if(userEvent.getGoMaybe()==1) {
				going++;
			}else if (userEvent.getGoMaybe()==0) {
				notGoing++;
			}
		}
		
		int eventid=(int) toAdd.getEvent_id();
		Event eventgm=eventRepository.findById(eventid);
		Event event = new Event();
		//city.setId(cityDTO.getId());
		event.setId(eventgm.getId());
		event.setName(eventgm.getName());
		event.setStart_time(eventgm.getStart_time());
		event.setAddress(eventgm.getAddress());
		event.setDetails(eventgm.getDetails());
		event.setCityId(eventgm.getCityId());
		event.setGoing(going);
		event.setMaybe(maybe);
		event.setLat(eventgm.getLat());
		event.setLon(eventgm.getLon());
		
		

		Event eventR = eventRepository.save(event);
		
		
		return userEventAdd;
	}
	
	public UsersEvents changeOptionUser(UsersEvents toChange) {
		long userEventId=toChange.getId();
		UsersEvents oldUserEvent = usersEventsR.findById(userEventId);
		UsersEvents newUserEvent= new UsersEvents();
		
		newUserEvent.setId(toChange.getId());
		newUserEvent.setUser_id(toChange.getUser_id());
		newUserEvent.setEvent_id(toChange.getEvent_id());
		newUserEvent.setGoMaybe(toChange.getGoMaybe());
		
		usersEventsR.save(newUserEvent);
		
		return newUserEvent;
		
	} 
	
	public List<UsersEvents> showIfUserEvent(long user_id,long event_id) {
		ArrayList<UsersEvents> touristEvents=(ArrayList<UsersEvents>) usersEventsR.checkUserEvent(user_id, event_id) ;
		return touristEvents;
	}
}
