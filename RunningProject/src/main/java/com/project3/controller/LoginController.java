package com.project3.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.project3.Model.Links;
import com.project3.Model.User;
import com.project3.implementation.LinkServiceImplementation;
import com.project3.implementation.UserServiceImplementation;
import com.project3.service.LinkService;

@Controller
public class LoginController {

	@RequestMapping("/helloWorld")
	public String helloWorld(Model model) {
		model.addAttribute("welcomeMessage", "Hello World!");
		return "index";
	}

	@Resource(name = "UserService")
	private UserServiceImplementation userService;
	@Resource(name = "LinkService")
	private LinkServiceImplementation linkService;

	@RequestMapping("logout")
	public View logout(HttpSession session) {
		session.invalidate();

		return new RedirectView("/login", true, false);
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(Model model, HttpSession session) {
		if (session.getAttribute("username") != null) {
			model.addAttribute("urlForm", new Links());
			return "redirect:/shortener";
		}
		model.addAttribute("loginForm", new User());
		model.addAttribute("loginFailed", false);
		return "login";

	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public View login(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginForm") User loginForm, BindingResult result) {
		System.out.println("USERNAME" + loginForm.getUsername() + "PASSWORD" + loginForm.getPassword());
		User userlogin = null;
		userlogin = loginCheck(loginForm.getUsername(), loginForm.getPassword());
		if (session.getAttribute("username") != null) {
			return this.goPrivate();
		}
		if (userlogin == null) {
			model.addAttribute("loginFailed", true);
			model.addAttribute("loginForm", loginForm);
			return new RedirectView("/login", true, false);
		} else {
			session.setAttribute("username", loginForm.getUsername());
			request.changeSessionId();
			return this.goPrivate();
		}
	}

	private View goPrivate() {
		// TODO Auto-generated method stub
		return new RedirectView("/shortener", true, false);
	}

	private User loginCheck(String username, String password) {
		// TODO Auto-generated method stub
		return userService.getUser(username, password);
	}

	@RequestMapping("signup")
	public String signup(HttpSession session) {

		return "redirect:/signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(Model model, HttpSession session) {
		model.addAttribute("signupForm", new User());
		return "signup";
	}

	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(Model model, HttpSession session, @ModelAttribute("signupForm") User signupForm,
			BindingResult result) {
		
		
		System.out.println("USERNAME" + signupForm.getUsername() + "PASSWORD" + signupForm.getPassword());
		User user=userService.checkUser(signupForm.getUsername());
		//System.out.println(user.getUsername());
		if(user==null){
			userService.insertUser(signupForm);
			model.addAttribute("Database",false);
			model.addAttribute("loginForm", new User());
			return "redirect:/login";
		}
		//userService.insertUser(signupForm);
		//model.addAttribute("loginForm", new User());
		model.addAttribute("Database",true);
		return "signup";
	}
	

	@RequestMapping(value = "publicForm", method = RequestMethod.GET)
	public String publicUrl(Model model) {
		model.addAttribute("publicForm", new Links());
		return "publicForm";
	}

	@RequestMapping(value = "publicForm", method = RequestMethod.POST)
	public String publicUrl (Model model, HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("publicForm") Links publicForm, BindingResult result) {
		System.out.println("ShortURL:"+publicForm.getShortUrl());
		String short_url=publicForm.getShortUrl();
		Links longLink =linkService.getLongUrl(short_url);
		System.out.println("HELLOCONTROLLER:"+longLink);
		if(longLink.getLongUrl()==null){
		model.addAttribute("CheckNull",true);	
		}
		else{
		model.addAttribute("longLink",longLink);
		model.addAttribute("CheckNull",false);
		}
		return "publicLongUrl";
}
}
