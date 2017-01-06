package crymagic.source.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Attend;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.repositories.TranQuocThien_02_AttendRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_AttendServiceImpl implements TranQuocThien_02_AttendService{
	//Override lại các phương thức interface TranQuocThien_02_AttendService
	private TranQuocThien_02_AttendRepository attendRepository;
	
	@Autowired
	public void setAttendRepository(TranQuocThien_02_AttendRepository attendRepository){
		this.attendRepository = attendRepository;
	}
	@Override
	public Iterable<TranQuocThien_02_Attend> getAllAttend(){
		return attendRepository.findAll();
	}
	
	@Override
	public TranQuocThien_02_Attend createAttend(TranQuocThien_02_Attend attend){
		return attendRepository.save(attend);
	}
	@Override
	public void deleteById(Long id){
		attendRepository.delete(id);
	}
	@Override
	public Iterable<TranQuocThien_02_Attend> getAllByEvent(TranQuocThien_02_Event event){
		return attendRepository.findByEvent(event);
	}
	@Override
	public Integer checkAttend(TranQuocThien_02_Event event, TranQuocThien_02_Acount account){
		return attendRepository.CheckAttend(event, account);
	}
	@Override
	public void deleteAttend(TranQuocThien_02_Event event, TranQuocThien_02_Acount account){
		attendRepository.deleteAttend(event, account);
	}
}
