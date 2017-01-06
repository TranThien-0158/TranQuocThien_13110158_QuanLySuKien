package crymagic.source.services;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Comment;
import crymagic.source.model.TranQuocThien_02_Event;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_CommentService {
	//Lấy ra tất cả danh sách comment
	Iterable<TranQuocThien_02_Comment> getAllComments();
	
	//Lấy ra tất cả danh sách comment theo sự kiện
	Iterable<TranQuocThien_02_Comment> getAllCommentByEvent(TranQuocThien_02_Event event);
	
	//Thêm 1 comment
	TranQuocThien_02_Comment CreateComment(TranQuocThien_02_Comment comment);
	
	//Lấy 1 comment theo id
	TranQuocThien_02_Comment getCommentById(Long id);
	//Xoá một comment
	void DeleteCommentById(Long id);
	//Đếm comment
	Integer countComment();
}
