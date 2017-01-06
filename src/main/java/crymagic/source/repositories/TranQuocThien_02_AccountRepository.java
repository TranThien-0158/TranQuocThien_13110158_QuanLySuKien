package crymagic.source.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import crymagic.source.model.TranQuocThien_02_Acount;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
public interface TranQuocThien_02_AccountRepository extends CrudRepository<TranQuocThien_02_Acount, Long>{
	//Thực hiện try vấn kiểm tra đăng nhâp
	@Query("select count(a) from TranQuocThien_02_Acount a where a.email=?1 and a.password=?2")
	public Integer  checkLogin(String email, String password);
	
	//Lấy quyền môt tài khoản bằng email
	@Query(value="select role from TranQuocThien_02_Acount a where a.email=?1")
	public Integer getRoleByEmail(String email);
	
	//Lấy tên môt tài khoản bằng email
	@Query(value="select fullname from TranQuocThien_02_Acount a where a.email=?1")
	public String getFullnameByEmail(String email);
	//Lấy một record bằng email
	public TranQuocThien_02_Acount findByEmail(String email);
	
	//Lấy hình môt tài khoản bằng email
	@Query(value="select avatar from TranQuocThien_02_Acount a where a.email=?1")
	public String getAvatarByEmail(String email);
	
	//Lấy id môt tài khoản bằng email
	@Query(value="select id from TranQuocThien_02_Acount a where a.id=?1")
	public TranQuocThien_02_Acount getId(String email);
	
	//Đếm số lượng user 
	@Query("select count(a) from TranQuocThien_02_Acount a")
	public Integer countUser();
}
