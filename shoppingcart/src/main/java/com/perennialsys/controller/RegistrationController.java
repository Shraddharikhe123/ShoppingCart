package com.perennialsys.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.UserModel;

@Controller
public class RegistrationController  {

	@Autowired
	private DatabaseQueries databasequeries;
	
	BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
	@RequestMapping(value="/" , method = RequestMethod.GET)
	public String home() {
		return "register";
	}
	
	/**
	 * Method to register userInformation
	 *
	 *@param userModel  contains all information related to user.
	 *
	 * 
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registrationController(@Valid @ModelAttribute("userModel") UserModel userModel,BindingResult result){			
		String password = bpe.encode(userModel.getPassword());
		System.out.println(userModel.getMobileNo());
		databasequeries.register(userModel,password);
		if(result.hasErrors()) {
			ModelAndView mav =new ModelAndView("register");
			return mav;
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("registerSuccessful");
		return mav;
	}
}
