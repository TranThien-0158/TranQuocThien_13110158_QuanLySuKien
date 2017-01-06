package crymagic.source.services;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.model.TranQuocThien_02_Hobby;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_HobbyService {
	//Lấy ra danh sách các sự kiện yêu thích theo user
	Iterable<TranQuocThien_02_Hobby> getAllFavoriteByAccount(TranQuocThien_02_Acount account);
	//Xoá một sự kiên yêu thích
	void deleteById(Long id);
	//Thêm 1 sự kiện và danh mục yêu thích
	TranQuocThien_02_Hobby createHobby(TranQuocThien_02_Hobby hobby);
	//Kiểm tra tồn tại sự kiện yê thích cả user
	Integer checkFavorite(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
	//Huỷ yêu thích
	void deleteFavorite(TranQuocThien_02_Event event, TranQuocThien_02_Acount account);
}
