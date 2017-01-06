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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.model.TranQuocThien_02_Attend;
import crymagic.source.model.TranQuocThien_02_Comment;
import crymagic.source.model.TranQuocThien_02_Event;
import crymagic.source.model.TranQuocThien_02_Hobby;
import crymagic.source.services.TranQuocThien_02_AccountService;
import crymagic.source.services.TranQuocThien_02_CommentService;
import crymagic.source.services.TranQuocThien_02_EventService;
import crymagic.source.services.TranQuocThien_02_HobbyService;
import crymagic.source.services.TranQuocThien_02_TopicService;
import crymagic.source.services.TranQuocThien_02_AttendService;
/**
 * Created by TranQuocThien on 12/21/2016.
 */
@Controller
public class TranQuocThien_02_HomeController {
	private TranQuocThien_02_EventService eventService;
	private TranQuocThien_02_TopicService topicService;
	private TranQuocThien_02_AccountService accountService;
	private TranQuocThien_02_CommentService commentService;
	private TranQuocThien_02_AttendService attendService;
	private TranQuocThien_02_HobbyService hobbeService;
	@Autowired
	public void setHomeService(TranQuocThien_02_EventService eventService, 
			TranQuocThien_02_TopicService topicService, 
			TranQuocThien_02_AccountService accountService , 
			TranQuocThien_02_CommentService commentService ,
			TranQuocThien_02_AttendService attendService,
			TranQuocThien_02_HobbyService hobbeService){
		this.eventService = eventService;
		this.topicService = topicService;
		this.accountService = accountService;
		this.commentService = commentService;
		this.hobbeService = hobbeService;
		this.attendService = attendService;
	}
	
	//Hiển thị trang index
	@RequestMapping("/admin/index")
	public String index(HttpSession session, Model model){
		Object email = session.getAttribute("sessionAdmin");
		if(email == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("comment", commentService.countComment());
		model.addAttribute("event", eventService.countEvent());
		model.addAttribute("user", accountService.countUser());
		return "admin/index";
	}
	//Hiển thị trang chủ người dùng
	@RequestMapping("/")
	public String home(Model model){
		model.addAttribute("events", eventService.getAllEvents());
		model.addAttribute("eventtops", eventService.findAllByImportant());
		model.addAttribute("eventnormals", eventService.findAllByNotImportant());
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/home";
	}
	//Hiển thị trang chủ người dùng
	@RequestMapping("/home")
	public String homepage(Model model){
		model.addAttribute("events", eventService.getAllEvents());
		model.addAttribute("eventtops", eventService.findAllByImportant());
		model.addAttribute("eventnormals", eventService.findAllByNotImportant());
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/home";
	}
	//Hiển thị thông tin chi tiết môt sự kiện
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable Long id, Model model, HttpSession session){
		
		model.addAttribute("eventtops", eventService.findAllByImportant());
		model.addAttribute("eventdetail", eventService.getEventById(id));
		model.addAttribute("topics", topicService.getAllTopics());
		model.addAttribute("comment", new TranQuocThien_02_Comment());
		model.addAttribute("attend", new TranQuocThien_02_Attend());
		model.addAttribute("hobby", new TranQuocThien_02_Hobby());
		model.addAttribute("comments", commentService.getAllCommentByEvent(eventService.getEventById(id)));
		if(session.getAttribute("sessionUser")!=null){
			String sessionEmail = session.getAttribute("sessionUser").toString();
			model.addAttribute("checkfavorite",hobbeService.checkFavorite(eventService.getEventById(id), accountService.findByEmail(sessionEmail)));
			model.addAttribute("checkattend",attendService.checkAttend(eventService.getEventById(id), accountService.findByEmail(sessionEmail)));
		}
		return "front-end/detail";
	}
	//Hiển thị danh sách các sự kiện theo chủ đề sự kiên
	@RequestMapping("/category/{id}")
	public String category(@PathVariable Long id, Model model){
		model.addAttribute("eventdetail", eventService.getEventById(id));
		model.addAttribute("eventtops", eventService.findAllByImportant());
		model.addAttribute("eventcategory", eventService.findByTopic(id));
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/category";
	}
	//Hiển thị danh sách các sự kiện theo đơn vị 
	@RequestMapping("/place/{id}")
	public String place(@PathVariable Long id, Model model){
		
		model.addAttribute("eventdetail", eventService.getEventById(id));
		model.addAttribute("eventtops", eventService.findAllByImportant());
		model.addAttribute("eventplace", eventService.findByPlace(id));
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/place";
	}
	
	//Hiển thị view login người dùng
	@RequestMapping("/login")
	public String showlogin(Model model){
		model.addAttribute("login", new TranQuocThien_02_Acount());
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/login";
	}
	//Thực hiện chức năng login người dùng
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String postLogin(@ModelAttribute("account") TranQuocThien_02_Acount account, Model model, RedirectAttributes redirectAttributes, HttpSession session ){
		String email = account.getEmail();
		String password = account.getPassword();
		if(accountService.checkLogin(email, password)>0){
			session.setAttribute("sessionUser", email);
			
			session.setAttribute("sessionNameUser", accountService.getFullnameByEmail(email));
			session.setAttribute("sessionAvatarUser", accountService.getAvatarByEmail(email));
			return "redirect:home";
		}
		redirectAttributes.addFlashAttribute("message","Email hoặc Password chưa chỉnh xác");
		return "redirect:login";
		
	}
	//Thưc hiện chức năng logout
	@RequestMapping("/userlogout")
	public String logout(HttpSession session){
		session.removeAttribute("sessionUser");
		session.removeAttribute("sessionNameUser");
		session.removeAttribute("sessionAvatarUser");
		return "redirect:home";
	}
	//Hiển thì view đăng ký tài khoản
	@RequestMapping("/register")
	public String showRegister(Model model){
		model.addAttribute("account", new TranQuocThien_02_Acount());
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/register";
	}
	//Thực hiện chức năng đăng ký tài khoản người dùng
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String postRegister(@Valid @ModelAttribute("account") TranQuocThien_02_Acount account,
								BindingResult bindingResult,
								RedirectAttributes redirectAttributes,
								@RequestParam("file")MultipartFile file)
	{
		
		if(bindingResult.hasErrors()){
			return "front-end/register";
		}
		else{
			String name=file.getOriginalFilename();
			if(!file.isEmpty()){
	            try
	            {
	                byte[] bytes = file.getBytes();
	                // Tạo thư mục lưu trữ file
	                File dir = new File( "src/main/resources/static/upload");
	                if(!dir.exists())
	                    dir.mkdirs();
	                // Create the file on Server
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
	        account.setAvatar(name);
			accountService.createAccount(account);
			redirectAttributes.addFlashAttribute("notify", "Tạo tài khoản thành công !");
			return "redirect:login";
		}
	}
	//Thực hiên chức năng comment
	@RequestMapping(value="/detail/{id}", method= RequestMethod.POST)
	public String postComment(@ModelAttribute("comment") TranQuocThien_02_Comment comment,TranQuocThien_02_Event event, @PathVariable Long id, HttpSession session){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		Timestamp created_at = new Timestamp(System.currentTimeMillis());
		comment.setCreated_at(created_at);
		comment.setAccount(accountService.findByEmail(sessionEmail));
		comment.setEvent(eventService.getEventById(id));
		
		commentService.CreateComment(comment);
		
		return "redirect:/detail/"+comment.getEvent().getId();
	}
	// Thực hiên chức năng đăng ký tham gia
	@RequestMapping(value="/attendant/{id}", method=RequestMethod.POST)
	public String postAttendant(@ModelAttribute("attend") TranQuocThien_02_Attend attend, @PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		attend.setAccount(accountService.findByEmail(sessionEmail));
		
		attend.setEvent(eventService.getEventById(id));
		System.out.println("account:"+accountService.findByEmail(sessionEmail).getFullname());
		System.out.println("event:"+eventService.getEventById(id).getTitle());
		attendService.createAttend(attend);
		redirectAttributes.addFlashAttribute("message", "Đăng ký tham gia sự kiện thành công");
		return "redirect:/detail/"+attend.getEvent().getId();
	}
	//Huỷ đăng ký theo dõi 1 sự kiện
	@RequestMapping("/attendant-event/{id}")
	public String deleteAttend(@PathVariable Long id,@ModelAttribute("attend") TranQuocThien_02_Attend attend, HttpSession session, RedirectAttributes redirectAttributes){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		attendService.deleteAttend(eventService.getEventById(id), accountService.findByEmail(sessionEmail));
		redirectAttributes.addFlashAttribute("message", "Huỷ tham gia thành công");
		return "redirect:/detail/"+ id;
	}
	//Thực hiện chức năng thêm 1 sự kiên vào danh sách yêu thích người dùng
	@RequestMapping(value="/farorite/{id}", method=RequestMethod.POST)
	public String postFavorite(@ModelAttribute("favorite") TranQuocThien_02_Hobby favorite, @PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		
		favorite.setAccount(accountService.findByEmail(sessionEmail));	
		favorite.setEvent(eventService.getEventById(id));
		
		
		hobbeService.createHobby(favorite);
		redirectAttributes.addFlashAttribute("message", "Thêm vào sự kiện yêu thích thành công");
		return "redirect:/detail/"+favorite.getEvent().getId();
	}
	
	//Huỷ theo dõi 1 sự kiện yêu thích
	@RequestMapping("/favorite-event/{id}")
	public String deleteFavorite(@PathVariable Long id ,@ModelAttribute("favorite") TranQuocThien_02_Hobby favorite, HttpSession session, RedirectAttributes redirectAttributes){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		hobbeService.deleteFavorite(eventService.getEventById(id), accountService.findByEmail(sessionEmail));
		redirectAttributes.addFlashAttribute("message", "Huỷ theo dõi sự kiện thành công");
		return "redirect:/detail/"+ id;
	}
	//Hiển thị view trang cá nhân người dùng
	@RequestMapping("/profile")
	public String showProfile(Model model, HttpSession session){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		
		model.addAttribute("account", accountService.findByEmail(sessionEmail));
		return "/front-end/profile";
	}
	
	//Thực hiên chức năng cập nhập thông tin người dùng
	@RequestMapping(value="/profile", method=RequestMethod.POST)
	public String Profile(Model model,@ModelAttribute("account") TranQuocThien_02_Acount account, HttpSession session){
		
		accountService.updateAccount(account);
		return "redirect:profile";
	}
	
	//Lấy ra danh sách sự kiện theo user
	@RequestMapping("/favorite-user")
	public String favoriteUser(Model model, HttpSession session){
		String sessionEmail = session.getAttribute("sessionUser").toString();
		model.addAttribute("eventuser", hobbeService.getAllFavoriteByAccount(accountService.findByEmail(sessionEmail)));
		model.addAttribute("topics", topicService.getAllTopics());
		return "front-end/favorite";
	}
	
}	

