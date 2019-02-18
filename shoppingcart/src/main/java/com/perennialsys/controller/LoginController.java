package com.perennialsys.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.UserModel;

@Controller
public class LoginController {
	@Autowired
	private DatabaseQueries databasequeries;

	
	/**
	 * Method to check valid user login 
	 *
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView loginController(@Valid @ModelAttribute("userModel") UserModel userModel,BindingResult result,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("register");
		}
		int userId = databasequeries.login(userModel);
		if(userId > 0) {
			session.setAttribute("userId", userId);
			mav.addObject("userModel", userModel);
			mav.setViewName("loginPage");
			return mav;
		}
		mav.addObject("error", "Incorrect Username or password");
		mav.setViewName("register");
		return mav;

	}

}
