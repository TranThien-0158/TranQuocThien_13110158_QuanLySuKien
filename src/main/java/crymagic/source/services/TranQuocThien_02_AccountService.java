package crymagic.source.services;

import java.util.List;

import crymagic.source.model.TranQuocThien_02_Acount;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_AccountService {
	Iterable<TranQuocThien_02_Acount> getAllAccounts();
	//lấy ra một user theo id
	TranQuocThien_02_Acount getAccountById(Long id);
	//thêm 1 record
	TranQuocThien_02_Acount createAccount(TranQuocThien_02_Acount account);
	//update 1 record
	TranQuocThien_02_Acount updateAccount(TranQuocThien_02_Acount account);
	//xoá một record
	void deleteAccountById(Long id);
	//kiểm tra login
	Integer checkLogin(String email, String password);
	//lấy ra quyên của user
	Integer getRoleByEmail(String email);
	//lấy ra tên của user
	String getFullnameByEmail(String email);
	//lấy ra hình của user
	String getAvatarByEmail(String email);
	//lấy ra đối tượng user theo email
	TranQuocThien_02_Acount getIdByEmail(String email);
	
	TranQuocThien_02_Acount findByEmail(String email);
	
	Integer countUser();
	
}
