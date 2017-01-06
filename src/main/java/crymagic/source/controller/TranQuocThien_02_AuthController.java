package crymagic.source.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import crymagic.source.model.TranQuocThien_02_Acount;
import crymagic.source.repositories.TranQuocThien_02_AccountRepository;
import crymagic.source.services.TranQuocThien_02_AccountService;
/**
 * Created by TranQuocThien on 12/20/2016.
 */
@Controller
@RequestMapping("/admin")
public class TranQuocThien_02_AuthController {
	private TranQuocThien_02_AccountService accountService;

	@Autowired
	public void setAccountService(TranQuocThien_02_AccountService accountService){
		this.accountService = accountService;
	}
	//Hiển thị view đăng nhập
	@RequestMapping("/login")
	public String login(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") != null){
			return "redirect:/admin/index";
		}
		model.addAttribute("account", new TranQuocThien_02_Acount());
		return "admin/login";
	}
	//Thực hiện chức năng đăng nhâp
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String toLogin(@ModelAttribute("account") TranQuocThien_02_Acount account, Model model, RedirectAttributes redirectAttributes, HttpSession session ){
		String email = account.getEmail();
		String password = account.getPassword();
		
		Integer role = accountService.getRoleByEmail(email);
		String fullname = accountService.getFullnameByEmail(email);
		String avatar = accountService.getAvatarByEmail(email);
		if(accountService.checkLogin(email, password) == 1){
			if(role == 1){
				System.out.println("Login success");
				session.setAttribute("sessionAdmin", email);
				session.setAttribute("sessionFullname", fullname);
				session.setAttribute("sessionAvatar", avatar);
				redirectAttributes.addFlashAttribute("message", "Đăng nhập thành công");
				return "redirect:/admin/index";
			}
			else{
				redirectAttributes.addFlashAttribute("message", "Tài khoản của bạn không có quyền truy cập");
				return "redirect:login";
			}
		}
		redirectAttributes.addFlashAttribute("message", "Email hoặc password không chính xác");
		return "redirect:login";
	}
	//Thực hiện chức năng logout
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("sessionAdmin");
		return "redirect:/admin/login";
	}
}
