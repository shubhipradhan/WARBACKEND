package com.sample.Backendjava.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.Backendjava.dao.UserDAO;
import com.sample.Backendjava.model.User;

@Controller
public class HomeController {
	
	@Autowired
	 UserDAO userDao;

	@RequestMapping("/")
	public ModelAndView handleRequest() throws Exception {
		List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("UserList");
		System.out.println(listUsers);
		model.addObject("userList", listUsers);
		return model;
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newUser() {
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", new User());
		return model;		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = userDao.get(userId);
		ModelAndView model = new ModelAndView("UserForm");
		model.addObject("user", user);
		return model;		
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDao.delete(userId);
		return new ModelAndView("redirect:/");		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("user") User user) {
		if(user.getId()==0){
		
		userDao.saveOrUpdate(user);
		}
		
		return new ModelAndView("redirect:/");
	}
	
}

