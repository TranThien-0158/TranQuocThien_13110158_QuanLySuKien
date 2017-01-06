package crymagic.source.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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
import crymagic.source.services.TranQuocThien_02_AccountService;
/**
* Created by TranQuocThien on 12/21/2016.
*/
@Controller
@RequestMapping("/admin")
public class TranQuocThien_02_AccountController extends WebMvcConfigurerAdapter {
	//Thực hiện các chức năng liên quan đến Model
	
	private TranQuocThien_02_AccountService accountService;

	@Autowired
	public void setAccountService(TranQuocThien_02_AccountService accountService){
		this.accountService = accountService;
	}
	
	//Hiển thị danh sách các account
	@RequestMapping(value = "/account/list",method = RequestMethod.GET)
	public String getList(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("accounts", accountService.getAllAccounts());
		return "admin/account/list";
	}

	// Hiển thị view thêm tài khoản
	@RequestMapping("/account/add")
	public String newAccount(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("account", new TranQuocThien_02_Acount());
		return "admin/account/add";
	}
	//Thêm 1 tài khoản vào database
	@RequestMapping(value="/account/add", method= RequestMethod.POST)
	public String insertAccount(@Valid @ModelAttribute("account") TranQuocThien_02_Acount account,
								BindingResult bindingResult,
								HttpSession session,
								RedirectAttributes redirectAttributes,
								@RequestParam("file")MultipartFile file){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		if(bindingResult.hasErrors()){
			return "admin/account/add";
		}
		else{
			String name=file.getOriginalFilename();
			if(!file.isEmpty()){
	            try
	            {
	                byte[] bytes = file.getBytes();
	                //Tạo đường dẫn lưu trữ file
	                File dir = new File( "src/main/resources/static/upload");
	                if(!dir.exists())
	                    dir.mkdirs();
	                
	                File serverFile = new File(dir.getPath() + File.separator + name);
	                BufferedOutputStream stream=new BufferedOutputStream( new FileOutputStream(serverFile));
	                stream.write(bytes);
	                stream.close();

	            }	
	            catch (Exception e){
	                return "File Error:" + e.getMessage();
	            }
	        }else {
	            name=null;
	        }
	        account.setAvatar(name);
			accountService.createAccount(account);
			redirectAttributes.addFlashAttribute("message", "Thêm tài khoản thành công");
			return "redirect:/admin/account/list";
		}
		
	}
	//Hiển thị view chình sửa thông tin tài khoản
	@RequestMapping("account/edit/{id}")
	public String editAccount(@PathVariable Long id, Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("account", accountService.getAccountById(id));
		return "admin/account/edit";
	}
	// Câp nhật thông tin một tài khoản
	@RequestMapping(value="/account/edit", method=RequestMethod.POST)
	public String updateAccount(@Valid @ModelAttribute("account") TranQuocThien_02_Acount account,
								BindingResult bindingResult,
								RedirectAttributes redirectAttributes,
								@RequestParam("file")MultipartFile file){
		//Kiểm tra các các trường nhập vào hợp lệ
		if(bindingResult.hasErrors()){
			return "redirect:/admin/account/edit/"+account.getId();
		}
		else{
			String name=file.getOriginalFilename();
			if(!file.isEmpty()){
				
	            try
	            {
	                byte[] bytes = file.getBytes();
	                //Tạo đường dẫn lưu trữ file
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
	            account.setAvatar(name);
	            accountService.updateAccount(account);
				
	        }
			else{
				accountService.updateAccount(account);
			}
			redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin tài khoản thành công");
			return "redirect:/admin/account/list";
		}
	}
	//Xoá một tài khoản
	@RequestMapping("account/delete/{id}")
	public String deleteAccount(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		accountService.deleteAccountById(id);
		redirectAttributes.addFlashAttribute("message", "Xoá tài khoản thành công");
		return "redirect:/admin/account/list";
	}
}
