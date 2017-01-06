package crymagic.source.services;

import crymagic.source.model.TranQuocThien_02_Topic;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_TopicService {
	//Lấy ra tất cả các chủ đề sự kiện
	Iterable<TranQuocThien_02_Topic> getAllTopics();
	//Lấy ra thông tin chủ đề sự kiện theo id
	TranQuocThien_02_Topic getTopicById(Long id);
	//thêm mới một chủ đề
	TranQuocThien_02_Topic createTopic(TranQuocThien_02_Topic topic);
	//câp nhât thông tin 1 sự kiên
	TranQuocThien_02_Topic updateTopic(TranQuocThien_02_Topic topic);
	//Xoá một sự 
	void deleteTopicById(Long id);
}
