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
public class SearchController {

	@Autowired
	private DatabaseQueries databasequeries;
	

	/**
	 * get the productName searched by user  
	 *
	 * @param  searchedText  get the text searched by user
	 * @return
	 */

	@RequestMapping("/search")
	public ModelAndView searchController(@RequestParam("searchedText") String searchedText) {
		ModelAndView mav = new ModelAndView();
		List<ProductModel> allProduct = databasequeries.getSearchedProduct(searchedText);
		mav.addObject("allProduct", allProduct); 
		mav.setViewName("productDetails");
		return mav;
	}

}
