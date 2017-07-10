package com.project3.controller;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project3.Model.Links;
import com.project3.Model.User;
import com.project3.implementation.LinkServiceImplementation;
import com.project3.implementation.UserServiceImplementation;

@Controller
public class ShortenerController {

	@Resource(name="UserService")
    private UserServiceImplementation userService;
	@Resource(name="LinkService")
	private LinkServiceImplementation linkService;
	
	private static final String char_List = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-.";
	private static final int string_length = 6;

	
	@RequestMapping(value = "shortener", method = RequestMethod.GET)
	public String shortener(Model model,HttpSession session) {

		User user=null;
		model.addAttribute("urlForm", new Links());
		String username = (String) session.getAttribute("username");
		user=userService.getUserID(username);
		listUrls(user.getId());
		
		List<Links> listoflinks= (List<Links>) linkService.getUrlByUser(user.getId());
		model.addAttribute("listoflinks", listoflinks);
		
		return "shortener";
	}

	
	private List<Links> listUrls(int id) {
		// TODO Auto-generated method stub
		return linkService.getUrlByUser(id);
	}


	@RequestMapping(value = "shortener", method = RequestMethod.POST)
	public String login(Model model,HttpSession session,HttpServletRequest request,HttpServletResponse response,@ModelAttribute("urlForm")Links urlForm, BindingResult result) {
		System.out.println("LONG URL" + urlForm.getLongUrl());
		String domain_string = "http://localhost:8080/RunningProject/short/";
		String Databaseusername = (String) request.getSession().getAttribute("username");
		
		User user = userService.getUserID(Databaseusername);
		Links link=new Links();
		link.setUserId(user.getId());
		
		link.setLongUrl(urlForm.getLongUrl());
		
		String string_random = randomStringGenerator();
		String short_url = domain_string + string_random;
		link.setShortUrl(short_url);
		linkService.insertLinks(link);
		// new code end
		return "redirect:/shortener";
	}
	public String randomStringGenerator() {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < string_length; i++) {
			int number = randomNumber();
			char ch = char_List.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private int randomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(char_List.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

}
