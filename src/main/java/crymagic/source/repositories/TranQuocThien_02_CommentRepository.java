package crymagic.source.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import crymagic.source.model.TranQuocThien_02_Comment;
import crymagic.source.model.TranQuocThien_02_Event;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_CommentRepository extends CrudRepository<TranQuocThien_02_Comment, Long>{
	
	//Lấy ra tất cả các danh sách theo event và account
	@Query("select a from TranQuocThien_02_Comment a where a.event=?1 order by a.created_at desc")
	public Iterable<TranQuocThien_02_Comment> findByEvent(TranQuocThien_02_Event event);
	
	//Đếm số lượng comment
	@Query("select count(a) from TranQuocThien_02_Comment a")
	public Integer countComment();
}
