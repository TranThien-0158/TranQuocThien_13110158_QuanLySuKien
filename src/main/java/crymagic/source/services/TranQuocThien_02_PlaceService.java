package crymagic.source.services;

import crymagic.source.model.TranQuocThien_02_Place;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_PlaceService {
	//Lấy ra danh sách các đơn vị tổ chức sự kiện
	Iterable<TranQuocThien_02_Place> getAllPlaces();
	//Lấy ra các thông tin đơn v tổ chức sự kiện theo id
	TranQuocThien_02_Place getPlaceById(Long id);
	//Thêm mới một đơn vị
	TranQuocThien_02_Place createPlace(TranQuocThien_02_Place event);
	//Cập nhật thông tin 1 đơn vị
	TranQuocThien_02_Place updatePlace(TranQuocThien_02_Place event);
	//Xoá một đơn vị
	void deletePlaceById(Long id);
}
