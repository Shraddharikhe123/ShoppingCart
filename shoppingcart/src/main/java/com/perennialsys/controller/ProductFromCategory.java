package com.perennialsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.ProductModel;

@Controller
public class ProductFromCategory {
	@Autowired
	private DatabaseQueries databasequeries;


	/**
	 * Method to get all Products belong to particular category
	 *
	 *@param id   user selected category Id
	 * 
	 * @return
	 */
	@RequestMapping("/getproductDetailsfromcategory")
	public ModelAndView getProductFormCategory(@RequestParam int id) {
		ModelAndView mav = new ModelAndView();
		List<ProductModel> productList = databasequeries.getProductDetailsfromCategory(id);
		mav.addObject("productList", productList);
		mav.setViewName("getSortedProduct");
		return mav;

	}

}
