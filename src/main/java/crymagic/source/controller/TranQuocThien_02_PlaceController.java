package crymagic.source.controller;

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
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import crymagic.source.model.TranQuocThien_02_Place;
import crymagic.source.services.TranQuocThien_02_PlaceService;

/**
 * Created by TranQuocThien on 12/21/2016.
 */

@Controller
@RequestMapping("/admin")
public class TranQuocThien_02_PlaceController extends WebMvcConfigurerAdapter{
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/place/list").setViewName("admin/place/list");
    }
	
	private TranQuocThien_02_PlaceService placeService;

	@Autowired
	public void setPlaceService(TranQuocThien_02_PlaceService placeService){
		this.placeService = placeService;
	}
	//Hiển thị danh sách các đơn vị tổ chức sự kiện
	@RequestMapping(value = "/place/list",method = RequestMethod.GET)
	public String getList(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("places", placeService.getAllPlaces());
		return "admin/place/list";
	}

	//Hiển thị view thêm đơn vị tổ chức
	@RequestMapping("/place/add")
	public String newPlace(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("place", new TranQuocThien_02_Place());
		return "admin/place/add";
	}
	//Thêm 1 đơn vị tổ chức sự kiện và database
	@RequestMapping(value="/place/add", method= RequestMethod.POST)
	public String insertPlace(@Valid @ModelAttribute("place") TranQuocThien_02_Place place, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "admin/place/add";
		}
		else{
			placeService.createPlace(place);
			return "redirect:/admin/place/list";
		}
		
	}
	//Hiển thị view chinh sửa thông tin về đơn vị tổ chức
	@RequestMapping("place/edit/{id}")
	public String editPlace(@PathVariable Long id, Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("place", placeService.getPlaceById(id));
		return "admin/place/edit";
	}
	//Thực hiện chức năng cập nhật thông tin chỉnh sửa
	@RequestMapping(value="/place/edit", method=RequestMethod.POST)
	public String updatePlace(TranQuocThien_02_Place place){
		
		placeService.updatePlace(place);
		return "redirect:/admin/place/list";
	}
	//Xoá một đơn vị tổ chức
	@RequestMapping("place/delete/{id}")
	public String deletePlace(@PathVariable Long id, HttpSession session){
		//Kiểm tra session
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		placeService.deletePlaceById(id);
		return "redirect:/admin/place/list";
	}
}
