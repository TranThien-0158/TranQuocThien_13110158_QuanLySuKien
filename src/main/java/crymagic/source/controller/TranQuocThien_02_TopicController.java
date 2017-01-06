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

import crymagic.source.model.TranQuocThien_02_Topic;
import crymagic.source.services.TranQuocThien_02_TopicService;

/**
 * Created by TranQuocThien on 12/21/2016.
 */

@Controller
@RequestMapping("/admin")
public class TranQuocThien_02_TopicController extends WebMvcConfigurerAdapter {
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/account/list").setViewName("admin/account/list");
    }
	
	private TranQuocThien_02_TopicService topicService;

	@Autowired
	public void setTopicService(TranQuocThien_02_TopicService topicService){
		this.topicService = topicService;
	}
	//Index	
	@RequestMapping(value = "/topic/list",method = RequestMethod.GET)
	public String getList(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("topics", topicService.getAllTopics());
		return "admin/topic/list";
	}

	// Display form create
	@RequestMapping("/topic/add")
	public String newTopic(Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("topic", new TranQuocThien_02_Topic());
		return "admin/topic/add";
	}
	//Insert record
	@RequestMapping(value="/topic/add", method= RequestMethod.POST)
	public String insertTopic(@Valid @ModelAttribute("topic") TranQuocThien_02_Topic topic, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "admin/topic/add";
		}
		else{
			topicService.createTopic(topic);
			return "redirect:/admin/topic/list";
		}
		
	}
	//Edit
	@RequestMapping("topic/edit/{id}")
	public String editTopic(@PathVariable Long id, Model model, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		model.addAttribute("topic", topicService.getTopicById(id));
		return "admin/topic/edit";
	}
		
	@RequestMapping(value="/topic/edit", method=RequestMethod.POST)
	public String updateTopic(TranQuocThien_02_Topic topic){
		
		topicService.updateTopic(topic);
		return "redirect:/admin/topic/list";
	}
	//Delete
	@RequestMapping("topic/delete/{id}")
	public String deleteTopic(@PathVariable Long id, HttpSession session){
		if(session.getAttribute("sessionAdmin") == null){
			return "redirect:/admin/login";
		}
		topicService.deleteTopicById(id);
		return "redirect:/admin/topic/list";
	}
}
