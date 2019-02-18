package com.perennialsys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	
	
	/**
	 * Method to destroy session and logout
	 *
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logoutController(HttpSession session) {
		session.invalidate();
		//System.out.println(session.getAttribute("userId"));
		return "register";
	}

}
