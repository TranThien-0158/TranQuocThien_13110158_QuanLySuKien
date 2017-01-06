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
//Tạo bảng place

@Entity
@Table(name="place")
public class TranQuocThien_02_Place {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotEmpty(message="Bạn chưa nhập địa điểm diễn ra sự kiện")
	private String name_place;
	@OneToMany(mappedBy="place", cascade = CascadeType.ALL)
	private Set<TranQuocThien_02_Event> events;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName_place() {
		return name_place;
	}
	public void setName_place(String name_place) {
		this.name_place = name_place;
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
	public TranQuocThien_02_Place() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param name_place
	 * @param events
	 */
	public TranQuocThien_02_Place(Long id, String name_place, Set<TranQuocThien_02_Event> events) {
		super();
		this.id = id;
		this.name_place = name_place;
		this.events = events;
	}
	
	
}
