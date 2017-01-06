package crymagic.source.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
//Tạo bảng comment
@Entity
@Table(name="comment")
public class TranQuocThien_02_Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String content;
	private Timestamp created_at;
	@ManyToOne
	@JoinColumn(name="user_id")
	private TranQuocThien_02_Acount account;
	@ManyToOne
	@JoinColumn(name="event_id")
	private TranQuocThien_02_Event event;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public TranQuocThien_02_Acount getAccount() {
		return account;
	}
	public void setAccount(TranQuocThien_02_Acount account) {
		this.account = account;
	}
	public TranQuocThien_02_Event getEvent() {
		return event;
	}
	public void setEvent(TranQuocThien_02_Event event) {
		this.event = event;
	}
	/**
	 * 
	 */
	public TranQuocThien_02_Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param content
	 * @param created_at
	 * @param account
	 * @param event
	 */
	public TranQuocThien_02_Comment(Long id, String content, Timestamp created_at, TranQuocThien_02_Acount account, TranQuocThien_02_Event event) {
		super();
		this.id = id;
		this.content = content;
		this.created_at = created_at;
		this.account = account;
		this.event = event;
	}
	
	
}
