package crymagic.source.services;

import crymagic.source.model.TranQuocThien_02_Event;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_EventService {
	//Lấy danh sách tất cả các sự kiện
	Iterable<TranQuocThien_02_Event> getAllEvents();
	//Lấy thông tin 1 sự kiện theo id
	TranQuocThien_02_Event getEventById(Long id);
	//Thêm mới môt sự kiện
	TranQuocThien_02_Event createEvent(TranQuocThien_02_Event event);
	//Cập nhật thông tin một sự kiện
	TranQuocThien_02_Event updateEvent(TranQuocThien_02_Event event);
	//Xoá môt sư kiện
	void deleteEventById(Long id);
	
	//Lấy danh sách tất cả các sự kiện qan trọng
	Iterable<TranQuocThien_02_Event> findAllByImportant();
	
	//Lấy ra danh sách tất cả các sự kiện không quan trọng
	Iterable<TranQuocThien_02_Event> findAllByNotImportant();
	
	//Lấy ra danh sách các sự kiện theo chủ đè sư kiện
	Iterable<TranQuocThien_02_Event> findByTopic(Long topic);
	
	//Lấy ra danh sách tất cả các sư kiện theo đơn vị tổ chức
	Iterable<TranQuocThien_02_Event> findByPlace(Long place);
	
	//Tìm kiếm danh sách sự kiên theo field title
	Iterable<TranQuocThien_02_Event> findByTitle(String title);
	
	//Đếm sự kiện
	
	Integer countEvent();
}
