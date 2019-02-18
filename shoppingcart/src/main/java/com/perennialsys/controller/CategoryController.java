package com.perennialsys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.CategoryModel;

@Controller
public class CategoryController {
	@Autowired
	private DatabaseQueries databasequeries;

	/**
	 * get category available in shoppingKart
	 *
	 * 
	 * @return
	 */
	@RequestMapping(value="/getCategory")
	public ModelAndView categoryController() {
		List<CategoryModel> categories = databasequeries.getCategory();
		ModelAndView mav = new ModelAndView();
		mav.addObject("categories", categories);
		mav.setViewName("productDetails");
		return mav;

	}

}
