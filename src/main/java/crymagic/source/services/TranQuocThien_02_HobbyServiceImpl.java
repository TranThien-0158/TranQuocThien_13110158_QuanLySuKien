package crymagic.source.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Attend;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.model.TranQuocThien_02_Hobby;
import crymagic.source.repositories.TranQuocThien_02_HobbyRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_HobbyServiceImpl implements TranQuocThien_02_HobbyService{
	//Override lai các phuong thức bên interface TranQuocThien_02_PlaceService
	
	
	private TranQuocThien_02_HobbyRepository hobbeRepository;
	
	@Autowired
	public void setHobbyRepository(TranQuocThien_02_HobbyRepository hobbeRepository){
		this.hobbeRepository = hobbeRepository;
	}
	@Override
	public Iterable<TranQuocThien_02_Hobby> getAllFavoriteByAccount(TranQuocThien_02_Acount account){
		return hobbeRepository.findByAccount(account);
	}
	
	@Override
	public TranQuocThien_02_Hobby createHobby(TranQuocThien_02_Hobby hobby){
		return hobbeRepository.save(hobby);
	}
	@Override
	public void deleteById(Long id){
		hobbeRepository.delete(id);
	}
	@Override
	public Integer checkFavorite(TranQuocThien_02_Event event, TranQuocThien_02_Acount account){
		return hobbeRepository.CheckFavorite(event, account);
	}
	@Override
	public void deleteFavorite(TranQuocThien_02_Event event, TranQuocThien_02_Acount account){
		hobbeRepository.deleteFavorite(event, account);
	}

}
