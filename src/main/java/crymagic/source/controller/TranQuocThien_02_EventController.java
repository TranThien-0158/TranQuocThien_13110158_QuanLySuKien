package crymagic.source.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.model.TranQuocThien_02_Place;
import crymagic.source.services.TranQuocThien_02_AccountService;
import crymagic.source.services.TranQuocThien_02_AttendService;
import crymagic.source.services.TranQuocThien_02_EventService;
import crymagic.source.services.TranQuocThien_02_PlaceService;
import crymagic.source.services.TranQuocThien_02_TopicService;

/**
 * Created by TranQuocThien on 12/21/2016.
 */

@Controller
@RequestMapping("/admin")
public class TranQuocThien_02_EventController extends WebMvcConfigurerAdapter{
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/event/list").setViewName("admin/event/list");
    }
	
	private TranQuocThien_02_EventService eventService;
	private TranQuocThien_02_TopicService topicService;
	private TranQuocThien_02_PlaceService placeService;
	private TranQuocThien_02_AccountService accountService;
	private TranQuocThien_02_AttendService attendService;

	@Autowired
	public void setEventService(TranQuocThien_02_EventService eventService, TranQuocThien_02_TopicService topicService, TranQuocThien_02_PlaceService placeService, TranQuocThien_02_AccountService accountService,TranQuocThien_02_AttendService attendService){
		this.eventService = eventService;
		this.topicService = topicService;
		this.placeService = placeService;
		this.accountService = accountService;
		this.attendService = attendService;
	}
	//Hiển thị danh sách các sự kiện
	@RequestMapping(value = "/event/list",method = RequestMethod.GET)
	public String getList(Model model){
		model.addAttribute("events", eventService.getAllEvents());
		return "admin/event/list";
	}
	//Hiển thị danh sách các tài khoàn tham gia sự kiện
	@RequestMapping(value = "/event/view/{id}",method = RequestMethod.GET)
	public String getListEvent(@PathVariable Long id,Model model){
		TranQuocThien_02_Event event = eventService.getEventById(id);
		model.addAttribute("eventattends", attendService.getAllByEvent(event));
		return "admin/event/view-attendant";
	}

	// Hiển thị view thêm 1 sự kiên
	@RequestMapping("/event/add")
	public String newEvent(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("places", placeService.getAllPlaces());
		model.addAttribute("topics", topicService.getAllTopics());
		model.addAttribute("event", new TranQuocThien_02_Event());
		return "admin/event/add";
	}
	//Thêm một sự kiện vào database
	@RequestMapping(value="/event/add", method= RequestMethod.POST)
	public String insertEvent(@Valid @ModelAttribute("event") TranQuocThien_02_Event event,
							BindingResult bindingResult,
							HttpSession session,
							Model model,
							RedirectAttributes redirectAttributes,
							@RequestParam("file") MultipartFile file){
		if(bindingResult.hasErrors()){
			model.addAttribute("places", placeService.getAllPlaces());
			model.addAttribute("topics", topicService.getAllTopics());
			return "admin/event/add";
		}
		else{
			String name=file.getOriginalFilename();
			if(!file.isEmpty()){
	            try
	            {
	                byte[] bytes = file.getBytes();
	                // Tạo 1 đường dẫn lưu trữ file
	                File dir = new File( "src/main/resources/static/banner");
	                if(!dir.exists())
	                    dir.mkdirs();

	                File serverFile = new File(dir.getPath() + File.separator + name);
	                BufferedOutputStream stream=new BufferedOutputStream( new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();

	            }	
	            catch (Exception e){
	                return "You failed to upload =>" + e.getMessage();
	            }
	        }else {
	            name=null;
	        }
			Timestamp create_at = new Timestamp(System.currentTimeMillis());
			String start = event.getStart();
			Timestamp timestart = Timestamp.valueOf(start);
			String sessionEmail = session.getAttribute("sessionAdmin").toString();
			
			
			event.setAccount(accountService.findByEmail(sessionEmail));
	        event.setBanner(name);
	        event.setCreated_at(create_at);
	        event.setStarttime(timestart);
	        
			eventService.createEvent(event);
			redirectAttributes.addFlashAttribute("message", "Tạo mới một sự kiện thành công");
			return "redirect:/admin/event/list";
		}
		
	}
	//Hiển thị view chỉnh sửa thông tin sự kiện
	@RequestMapping("event/edit/{id}")
	public String editTopic(@PathVariable Long id, Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("places", placeService.getAllPlaces());
		model.addAttribute("topics", topicService.getAllTopics());
		model.addAttribute("event", eventService.getEventById(id));
		return "admin/event/edit";
	}
	// Thực hiện chức năng câp nhật thông tin sự kiên
	@RequestMapping(value="/event/edit", method=RequestMethod.POST)
	public String updateEvent(@Valid @ModelAttribute("event") TranQuocThien_02_Event event,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file){
		
		if(bindingResult.hasErrors()){
			
			return "redirect:admin/event/edit/"+event.getId();
		}
		else{
			
			if(!file.isEmpty()){
				String name=file.getOriginalFilename();
	            try
	            {
	                byte[] bytes = file.getBytes();
	                // Tạo thư mục lưu trữ file
	                File dir = new File( "src/main/resources/static/banner");
	                if(!dir.exists())
	                    dir.mkdirs();
	                // Create the file on Server
	                File serverFile = new File(dir.getPath() + File.separator + name);
	                BufferedOutputStream stream=new BufferedOutputStream( new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();
	                event.setBanner(name);

	            }	
	            catch (Exception e){
	                return "You failed to upload =>" + e.getMessage();
	            }
	        }
			String start = event.getStart();
			Timestamp timestart = Timestamp.valueOf(start);
			
	        event.setStarttime(timestart);
	        
			eventService.updateEvent(event);
			
			redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin sự kiện thành công");
			return "redirect:/admin/event/list";
		}
	}
	//Xoá một sự kiện
	@RequestMapping("event/delete/{id}")
	public String deleteEvent(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		eventService.deleteEventById(id);
		redirectAttributes.addFlashAttribute("message", "Xoá thành công record");
		return "redirect:/admin/event/list";
	}
}
