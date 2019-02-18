package com.perennialsys.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.CheckCartModel;

@Controller
public class CheckCartController {
	
	@Autowired
	private DatabaseQueries databasequeries;
	
	/**
	 * Check the selected items in cart
	 *
	 * 
	 * @return
	 */
	@RequestMapping("/checkCart")
	public String CheckCart(HttpSession session,Model model) {
		int userId = (Integer) session.getAttribute("userId");
		List<CheckCartModel> cart = databasequeries.getcart(userId);
		model.addAttribute("cart", cart);
		return "cart";
	}
}
