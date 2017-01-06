package crymagic.source.model;

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

//Tạo bảng attend
@Entity
@Table(name="attend")
public class TranQuocThien_02_Attend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="event_id")
	private TranQuocThien_02_Event event;

	@ManyToOne
	@JoinColumn(name="user_id")
	private TranQuocThien_02_Acount account;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TranQuocThien_02_Event getEvent() {
		return event;
	}
	public void setEvent(TranQuocThien_02_Event event) {
		this.event = event;
	}

	public TranQuocThien_02_Acount getAccount() {
		return account;
	}
	public void setAccount(TranQuocThien_02_Acount account) {
		this.account = account;
	}
	/**
	 * 
	 */
	public TranQuocThien_02_Attend() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param event
	 * @param level_attend
	 * @param account
	 */
	public TranQuocThien_02_Attend(Long id, TranQuocThien_02_Event event, TranQuocThien_02_Acount account) {
		super();
		this.id = id;
		this.event = event;
		this.account = account;
	}
	
	
	
}
