package com.perennialsys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.ProductModel;

@Controller
public class ProductController {
	@Autowired
	private DatabaseQueries databasequeries;

	/**
	 * Method to get all product with details
	 *
	 * 
	 * @return
	 */
	@RequestMapping("/getProduct")    
	public ModelAndView productController(){ 

		ModelAndView mav = new ModelAndView();
		List<ProductModel> allProductList= databasequeries.getProductDetails(); 
		mav.addObject("allProduct",allProductList);
		mav.setViewName("getAllProductDetails");
		return mav;   
	}



}
