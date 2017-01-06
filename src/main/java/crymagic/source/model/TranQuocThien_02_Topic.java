package crymagic.source.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
//Tạo bảng topic
@Entity
@Table(name="topic")
public class TranQuocThien_02_Topic {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty(message = "Bạn chưa nhập chủ đề sự kiện")
	private String name;
	private String alias;
	@OneToMany(mappedBy="topic", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Event> events;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Set<TranQuocThien_02_Event> getEvents() {
		return events;
	}
	public void setEvents(Set<TranQuocThien_02_Event> events) {
		this.events = events;
	}
	
	/**
	 * 
	 */
	public TranQuocThien_02_Topic() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name
	 * @param alias
	 * @param events
	 */
	public TranQuocThien_02_Topic(Long id, String name, String alias, Set<TranQuocThien_02_Event> events) {
		super();
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.events = events;
	}
	
	
}
