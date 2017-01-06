package crymagic.source.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.repositories.TranQuocThien_02_EventRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_EventServiceImpl implements TranQuocThien_02_EventService{
	//Override lại các phương thức của interface TranQuocThien_02_EventService
	
	private TranQuocThien_02_EventRepository eventRepository;
	@Autowired
	public void setEventRepository(TranQuocThien_02_EventRepository eventRepository){
		this.eventRepository = eventRepository;
	}
	@Override
	public Iterable<TranQuocThien_02_Event> getAllEvents(){
		return eventRepository.findAll();
	}
	@Override
	public TranQuocThien_02_Event getEventById(Long id){
		return eventRepository.findOne(id);
	}
	@Override
	public TranQuocThien_02_Event createEvent(TranQuocThien_02_Event event){
		return eventRepository.save(event);
	}
	@Override
	public TranQuocThien_02_Event updateEvent(TranQuocThien_02_Event event){
		return eventRepository.save(event);
	}
	@Override
	public void deleteEventById(Long id){
		eventRepository.delete(id);
	}
	@Override
	public Iterable<TranQuocThien_02_Event> findAllByImportant(){
		return eventRepository.findAllByImportant();
	}
	@Override
	public Iterable<TranQuocThien_02_Event> findAllByNotImportant(){
		return eventRepository.findAllByNotImportant();
	}
	@Override
	public Iterable<TranQuocThien_02_Event> findByTopic(Long topic){
		return eventRepository.findEventByTopic(topic);
	}
	
	@Override
	public Iterable<TranQuocThien_02_Event> findByPlace(Long topic){
		return eventRepository.findEventByPlace(topic);
	}
	@Override
	public Iterable<TranQuocThien_02_Event> findByTitle(String title){
		return eventRepository.findByTitleContaining(title);
	}
	@Override
	public Integer countEvent(){
		return eventRepository.countEvent();
	}
}
