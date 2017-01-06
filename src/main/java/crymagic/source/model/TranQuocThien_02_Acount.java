package crymagic.source.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Created by TranQuocThien on 12/21/2016.
 */

//Tạo bảng account
@Entity
@Table(name="account")
public class TranQuocThien_02_Acount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Bạn chưa nhập E-mail")
    @Email(message = "Định dạng E-mail chưa đúng")
	private String email;
	@Size(min=4 , message = "Mật khẩu tối thiểu 4 kí tự")
	@NotEmpty(message = "Bạn chưa nhập mật khẩu")
	private String password;

	private int role;
	@NotEmpty(message = "Bạn chưa nhập tên tài khoản")
	private String fullname;
	private int status;
	private String phone;
	private String avatar;
	private int gender;
	
	private String birthday;
	
	@Version
	private Integer version;
	
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Event> events;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Comment> comments;
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Hobby> hobbies;
	
	@OneToMany(mappedBy="account", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Attend> attends;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public Set<TranQuocThien_02_Event> getEvents() {
		return events;
	}
	public void setEvents(Set<TranQuocThien_02_Event> events) {
		this.events = events;
	}
	public Set<TranQuocThien_02_Comment> getComments(){
		return comments;
	}
	public void setComments(Set<TranQuocThien_02_Comment> comments){
		this.comments = comments;
	}
	public Set<TranQuocThien_02_Hobby> getHobbies(){
		return hobbies;
	}
	public void setHobbies(Set<TranQuocThien_02_Hobby> hobbies){
		this.hobbies = hobbies;
	}
	public Set<TranQuocThien_02_Attend> getAttends(){
		return attends;
	}
	public void setAttends(Set<TranQuocThien_02_Attend> attends){
		this.attends = attends;
	}
	/**
	 * 
	 */
	public TranQuocThien_02_Acount() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param email
	 * @param password
	 * @param role
	 * @param fullname
	 * @param status
	 * @param phone
	 * @param avatar
	 * @param birthday
	 * @param events
	 * @param comments
	 * @param hobbies
	 * @param attends
	 */
	public TranQuocThien_02_Acount(TranQuocThien_02_Acount acount) {
		super();
		this.id = acount.id;
		this.email = acount.email;
		this.password = acount.password;
		this.role = acount.role;
		this.fullname = acount.fullname;
		this.status = acount.status;
		this.phone = acount.phone;
		this.avatar = acount.avatar;
		this.gender = acount.gender;
		this.birthday = acount.birthday;
		this.version = acount.version;
		this.events = acount.events;
		this.comments = acount.comments;
		this.hobbies = acount.hobbies;
		this.attends = acount.attends;
	}
}
