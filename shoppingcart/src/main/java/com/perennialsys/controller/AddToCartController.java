package com.perennialsys.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.ProductModel;
import com.perennialsys.service.AddToCartService;
import com.perennialsys.service.UpdateQuantity;

@Controller
public class AddToCartController {

	@Autowired
	private DatabaseQueries databasequeries;
	@Autowired
	private AddToCartService addCart;
	@Autowired
	private UpdateQuantity update;


	/**
	 * adding the Product selected  by user to cart.  
	 *
	 * @param  input  Quantity of Product selected by user.
	 * @param  pId    ProductId of user selected by user.
	 * 
	 * @return
	 */


	@RequestMapping(value= "/cart")
	public ModelAndView addToCartController(@ModelAttribute("productModel") ProductModel productModel,@RequestParam("input")int input,@RequestParam("pId")int pId,HttpSession session){
		int userId =  (Integer) session.getAttribute("userId");
		ModelAndView mav = new ModelAndView();
		int quantity = productModel.getQuantity();

		int price = productModel.getPrice();

		int total = addCart.getDetails(input,quantity,price);
		if(total > 0) {
			boolean result = databasequeries.addToCart(userId,productModel,pId,input,total);

			if(result ==  true) {

				int remainingProduct = update.getRequiredQuantity(input,productModel);

				databasequeries.updateQuantity(remainingProduct,pId);

				mav.addObject("message", "Successfully Added To Cart");
				mav.setViewName("loginPage");
				return mav;
			}else {

				mav.addObject("message",  "Product Not added to cart.. Try Again");
				mav.setViewName("loginPage");
				return mav;
			}
		}
		mav.addObject("message",  "Quantity not Available");
		mav.setViewName("loginPage");
		return mav;


	}


}
