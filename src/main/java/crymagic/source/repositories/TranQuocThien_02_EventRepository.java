package crymagic.source.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import crymagic.source.model.TranQuocThien_02_Event;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_EventRepository extends CrudRepository<TranQuocThien_02_Event, Long>{
	//Lấy danh sách tất cả các sự kiên nổi bât
	@Query("select  a from TranQuocThien_02_Event a where a.important = true")
	public Iterable<TranQuocThien_02_Event> findAllByImportant();

	//Lấy danh sách tất cả các sự kiện bình thường
	@Query("select  a from TranQuocThien_02_Event a where a.important = false")
	public Iterable<TranQuocThien_02_Event> findAllByNotImportant();
	
	//Lấy danh sách tất cả sự kiện theo chủ đề sự kiên
	@Query("select  a from TranQuocThien_02_Event a where a.topic.id = ?1")
	public Iterable<TranQuocThien_02_Event> findEventByTopic(Long id);
	
	//Lấy danh sách tất cả sự kiện theo đơn vị tổ chức
	@Query("select  a from TranQuocThien_02_Event a where a.place.id = ?1")
	public Iterable<TranQuocThien_02_Event> findEventByPlace(Long id);
	
	
	//Tìm các sự kiện theo title bài viết
	public Iterable<TranQuocThien_02_Event> findByTitleContaining(String title);
	//Đêm su kiẹn
	@Query("select count(a) from TranQuocThien_02_Event a")
	public Integer countEvent();
}
