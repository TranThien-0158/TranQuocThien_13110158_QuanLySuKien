package crymagic.source.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Place;
import crymagic.source.repositories.TranQuocThien_02_PlaceRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_PlaceServiceImpl implements TranQuocThien_02_PlaceService{
	//Ovrride lại các phương thức bên inteface TranQuocThien_02_PlaceService
	
	private TranQuocThien_02_PlaceRepository placeRepository;
	@Autowired
	public void setPlaceRepository(TranQuocThien_02_PlaceRepository placeRepository){
		this.placeRepository = placeRepository;
	}
	@Override
	public Iterable<TranQuocThien_02_Place> getAllPlaces(){
		return placeRepository.findAll();
	}
	@Override
	public TranQuocThien_02_Place getPlaceById(Long id){
		return placeRepository.findOne(id);
	}
	@Override
	public TranQuocThien_02_Place createPlace(TranQuocThien_02_Place place){
		return placeRepository.save(place);
	}
	@Override
	public TranQuocThien_02_Place updatePlace(TranQuocThien_02_Place place){
		return placeRepository.save(place);
	}
	@Override
	public void deletePlaceById(Long id){
		placeRepository.delete(id);
	}
}
