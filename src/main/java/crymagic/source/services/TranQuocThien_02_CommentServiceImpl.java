package crymagic.source.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Comment;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.repositories.TranQuocThien_02_CommentRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_CommentServiceImpl implements TranQuocThien_02_CommentService{
	//Override lai các phương thức interface TranQuocThien_02_CommentService
	private TranQuocThien_02_CommentRepository commentRepository;
	
	@Autowired
	public void setAccountRepository(TranQuocThien_02_CommentRepository commentRepository){
		this.commentRepository = commentRepository;
	}
	
	@Override
	public Iterable<TranQuocThien_02_Comment> getAllComments(){
		return commentRepository.findAll();
	}
	@Override
	public TranQuocThien_02_Comment CreateComment(TranQuocThien_02_Comment comment){
		return commentRepository.save(comment);
	}
	@Override
	public void DeleteCommentById(Long id){
		commentRepository.delete(id);
	}
	public Iterable<TranQuocThien_02_Comment> getAllCommentByEvent(TranQuocThien_02_Event event){
		return commentRepository.findByEvent(event);
	}
	@Override
	public TranQuocThien_02_Comment getCommentById(Long id){
		return commentRepository.findOne(id);
	}
	@Override
	public Integer countComment(){
		return commentRepository.countComment();
	}
}
