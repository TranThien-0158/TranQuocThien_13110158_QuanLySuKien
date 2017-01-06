package crymagic.source.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.model.TranQuocThien_02_Hobby;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_HobbyRepository extends CrudRepository<TranQuocThien_02_Hobby, Long>{
	//Lấy danh sách sư kiện theo sở thích của user
	public Iterable<TranQuocThien_02_Hobby> findByAccount(TranQuocThien_02_Acount account);
	
	//Kiểm tra user đã tham gia thêm sự kiện vào mục yêu thích hay chưa
	@Query("select count(a) from TranQuocThien_02_Hobby a where a.event=?1 and a.account=?2")
	public Integer  CheckFavorite(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
	
	@Modifying
	@Transactional
	@Query("delete from TranQuocThien_02_Hobby a where a.event =?1 and a.account=?2")
	public void deleteFavorite(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);

}
