package com.perennialsys.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.perennialsys.Queries.DatabaseQueries;
import com.perennialsys.model.ProductModel;

@Controller
public class GetProductController {

	@Autowired
	private DatabaseQueries databasequeries;

	/**
	 * Method to get user selected ProductDetails  
	 *@param id  id of Product
	 * 
	 * @return
	 */
	@RequestMapping("/getproductDetails")
	public String ProductFromCategory(@RequestParam int id ,Model model) {
		List<ProductModel> productDetails = databasequeries.getProductDetails(id); 
		model.addAttribute("productDetails", productDetails);
		return "addingToCart";

	}
}
