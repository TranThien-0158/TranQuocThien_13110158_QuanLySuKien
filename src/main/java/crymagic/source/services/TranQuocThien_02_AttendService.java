package crymagic.source.services;


import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Attend;
import crymagic.source.model.TranQuocThien_02_Event;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_AttendService {
	//Lấy ra danh sách các user tham gia sự kiện
	Iterable<TranQuocThien_02_Attend> getAllAttend();
	//lấy ra danh sacah tất các các user tham gia môt sư kiện
	Iterable<TranQuocThien_02_Attend> getAllByEvent(TranQuocThien_02_Event event);
	//Huỷ tham gia sự kiện
	void deleteById(Long id);
	//Đăng ký tham gia sư kiện
	TranQuocThien_02_Attend createAttend(TranQuocThien_02_Attend attend);
	
	//Kiểm tra tồn tại việc tham gia sự kiện cả user
	Integer checkAttend(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
	//Huỷ đăng ký tham gia sự kiện
	void deleteAttend(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
}
