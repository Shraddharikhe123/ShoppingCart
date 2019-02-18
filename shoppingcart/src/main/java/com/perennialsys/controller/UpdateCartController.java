package com.perennialsys.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.CheckCartModel;
import com.perennialsys.model.ProductModel;
import com.perennialsys.service.AddToCartService;

@Controller
public class UpdateCartController {

	@Autowired
	private DatabaseQueries databasequeries;
	@Autowired
	private AddToCartService addCart;



	/**
	 * Method to update cart 
	 *
	 *@param checkCartModel contains all information related to cart attributes.
	 *@param orderId         orderId of updated Product.
	 *
	 *
	 * 
	 * @return
	 */
	@RequestMapping("/updateCart")
	public ModelAndView updateCartController(@ModelAttribute("checkCartModel") CheckCartModel checkCartModel,@RequestParam("orderId") int orderId,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String productName = checkCartModel.getProductName();
		int input = checkCartModel.getQuantity();
		List<ProductModel> productDetails = databasequeries.getProductDetail(productName);
		System.out.println(productDetails);
		int price =productDetails.get(0).getQuantity();
		int quantity = productDetails.get(0).getPrice();
		int total = addCart.getDetails(input,quantity,price);
		if(total > 0) {
			boolean result = databasequeries.updateCart(input,total,orderId);

			if(result) {
				mav.setViewName("cart");
				return mav;
			}
			else {
				mav.setViewName("cart");
				return mav;
			}
		}
		mav.addObject("result", "Product quantity not available");
		mav.setViewName("loginPage");
		return mav;
	}
}
