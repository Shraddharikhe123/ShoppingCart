package com.perennialsys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.CheckCartModel;

@Controller
public class DeleteProductController {

	@Autowired
	private DatabaseQueries databasequeries;

	/**
	 * Delete Product from cart
	 *
	 * 
	 * @return
	 */
	
	@RequestMapping("/deleteProduct")
	public ModelAndView deleteProductController(@ModelAttribute("checkCartModel") CheckCartModel checkCartModel,HttpSession session) {
		System.out.println(checkCartModel.getProductName());
		System.out.println(checkCartModel.getCartId());
		int userId = (Integer) session.getAttribute("userId");
		ModelAndView mav = new ModelAndView();

		boolean result = databasequeries.deleteFromCart(userId,checkCartModel);
		mav.setViewName("cart"); 
		if(result) {
			return mav;
		}else { 
			String errormessage= null;
			mav.addObject("Try Again", errormessage); return mav;
		}



	}	
}