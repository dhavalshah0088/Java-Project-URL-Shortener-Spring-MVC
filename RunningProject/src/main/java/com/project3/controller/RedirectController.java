package com.project3.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project3.Model.Links;
import com.project3.implementation.LinkServiceImplementation;
import com.project3.implementation.UserServiceImplementation;
import com.project3.service.LinkService;


@Controller
public class RedirectController {

	
	@Resource(name = "UserService")
	private UserServiceImplementation userService;
	@Resource(name = "LinkService")
	private LinkServiceImplementation linkService;
	
	int click;
	
	@RequestMapping(value = "short/*", method = RequestMethod.GET)
	public void redirectToLong (Model model, HttpServletRequest request, HttpServletResponse response) {

        try{
        	String uniURLPath=request.getRequestURL().toString();
        	//String uniURLIdentifier=uniURLPath.replaceAll("/","");
        	System.out.println("URI-------"+uniURLPath);
        	
        	//click=ShortnerServlet.shortnerDatabase.get(uniURLPath).getclicks();
        	
    		//LinksDbAccess longUrlDao = (LinksDbAccess) ctx.getBean("LinksDbAccess");
    		//Links longLink = longUrlDao.getLongUrl(public_short);
    		Links clickLink = linkService.getClicks(uniURLPath);
    		//got the clicks and longurl
    		System.out.println("CLICKs:"+clickLink.getClicks()+"LongURL:"+clickLink.getLongUrl());
    		click=clickLink.getClicks();
    		click++;
    		System.out.println(click);
    		//update the click
    		String re=clickLink.getLongUrl();
    		linkService.updateClicks(click,clickLink.getLongUrl());
    		//Redirect
    		
    		response.sendRedirect(re);
        	return;

     //   	click++;
        	//ShortnerServlet.shortnerDatabase.get(uniURLPath).setclicks(click);
        	//System.out.println(ShortnerServlet.shortnerDatabase.get(uniURLPath).getlongUrl());
        	//resp.sendRedirect(ShortnerServlet.shortnerDatabase.get(uniURLPath).getlongUrl());
        	//return;
        }
    	catch (Exception e)
    	{
    		System.out.println("Invalid Short Link, or other.");
    	}

	}
	
	

}
