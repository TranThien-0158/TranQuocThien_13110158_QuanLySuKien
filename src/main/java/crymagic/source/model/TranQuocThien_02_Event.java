package crymagic.source.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * Created by TranQuocThien on 12/21/2016.
 */

//Tạo bảng event
@Entity
@Table(name="event")
public class TranQuocThien_02_Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty(message="Bạn chưa nhập tiêu đề sự kiện")
	private String title;

	private String alias;
	@NotEmpty(message="Bạn chưa nhập nơi diễn ra sự kiện")
	private String address;
	
	private Timestamp starttime;
	@NotEmpty(message="Bạn chưa nhập nội dung sự kiện")
	private String content;
	private String description;
	
	private String banner;
	private boolean important;
	private Timestamp created_at;
	private int view;
	@NotEmpty(message="Bạn chưa nhập thời gian bắt đầu sự kiện")
	private String start;
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	@ManyToOne
	@JoinColumn(name="user_id")
	
	private TranQuocThien_02_Acount account;
	
	@ManyToOne
	@JoinColumn(name="topic_id")
	private TranQuocThien_02_Topic topic;
	
	@ManyToOne
	@JoinColumn(name="place_id")
	private TranQuocThien_02_Place place;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Comment> comments;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Hobby> hobbies;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Attend> attends;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Timestamp getStarttime() {
		return starttime;
	}
	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public boolean isImportant() {
		return important;
	}
	public void setImportant(boolean isImportant) {
		this.important = isImportant;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	
	public TranQuocThien_02_Acount getAccount() {
		return account;
	}
	public void setAccount(TranQuocThien_02_Acount account) {
		this.account = account;
	}
	public TranQuocThien_02_Topic getTopic(){
		return topic;
	}
	public void setTopic(TranQuocThien_02_Topic topic){
		this.topic = topic;
	}
	public TranQuocThien_02_Place getPlace(){
		return place;
	}
	public void setPlace(TranQuocThien_02_Place place){
		this.place = place;
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
	public TranQuocThien_02_Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param title
	 * @param alias
	 * @param address
	 * @param starttime
	 * @param content
	 * @param description
	 * @param banner
	 * @param isImportant
	 * @param created_at
	 * @param view
	 * @param account
	 * @param topic
	 * @param place
	 * @param comments
	 * @param hobbies
	 * @param attends
	 */
	public TranQuocThien_02_Event(Long id, String title, String alias, String address, Timestamp starttime, String content,
			String description, String banner, boolean isImportant, Timestamp created_at, int view, TranQuocThien_02_Acount account, TranQuocThien_02_Topic topic, TranQuocThien_02_Place place, Set<TranQuocThien_02_Comment> comments, Set<TranQuocThien_02_Hobby> hobbies, Set<TranQuocThien_02_Attend> attends) {
		super();
		this.id = id;
		this.title = title;
		this.alias = alias;
		this.address = address;
		this.starttime = starttime;
		this.content = content;
		this.description = description;
		this.banner = banner;
		this.important = isImportant;
		this.created_at = created_at;
		this.view = view;
		this.account = account;
		this.topic = topic;
		this.place = place;
		this.comments = comments;
		this.hobbies = hobbies;
		this.attends = attends;
	}
	

	
}
