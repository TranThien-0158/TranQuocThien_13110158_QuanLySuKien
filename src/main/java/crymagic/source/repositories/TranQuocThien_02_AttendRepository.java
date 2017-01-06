package crymagic.source.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Attend;
import crymagic.source.model.TranQuocThien_02_Event;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_AttendRepository extends CrudRepository<TranQuocThien_02_Attend,Long>{
	
	//Lấy danh sách tất cả user tham gia môt sư kiện
	public Iterable<TranQuocThien_02_Attend> findByEvent(TranQuocThien_02_Event event);
	//Kiểm tra user đã đăng ký tham gia sự kiện hay chưa
	@Query("select count(a) from TranQuocThien_02_Attend a where a.event=?1 and a.account=?2")
	public Integer  CheckAttend(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
	
	@Modifying
	@Transactional
	@Query("delete from TranQuocThien_02_Attend a where a.event =?1 and a.account=?2")
	public void deleteAttend(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
}
