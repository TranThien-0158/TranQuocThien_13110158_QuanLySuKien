package crymagic.source.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crymagic.source.model.TranQuocThien_02_Topic;
import crymagic.source.repositories.TranQuocThien_02_TopicRepository;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Service
public class TranQuocThien_02_TopicServiceImpl implements TranQuocThien_02_TopicService{
	//Override lại các phương thức Interface TranQuocThien_02_TopicService
	private TranQuocThien_02_TopicRepository topicRepository;
	@Autowired
	public void setTopicRepository(TranQuocThien_02_TopicRepository topicRepository){
		this.topicRepository = topicRepository;
	}
	@Override
	public Iterable<TranQuocThien_02_Topic> getAllTopics(){
		return topicRepository.findAll();
	}
	@Override
	public TranQuocThien_02_Topic getTopicById(Long id){
		return topicRepository.findOne(id);
	}
	@Override
	public TranQuocThien_02_Topic createTopic(TranQuocThien_02_Topic topic){
		return topicRepository.save(topic);
	}
	@Override
	public TranQuocThien_02_Topic updateTopic(TranQuocThien_02_Topic topic){
		return topicRepository.save(topic);
	}
	@Override
	public void deleteTopicById(Long id){
		topicRepository.delete(id);
	}
}
