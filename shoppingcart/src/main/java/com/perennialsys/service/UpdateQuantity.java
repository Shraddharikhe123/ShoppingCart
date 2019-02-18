package com.perennialsys.service;

import com.perennialsys.model.ProductModel;

public class UpdateQuantity {

	public int getRequiredQuantity(int input, ProductModel productModel) {
	int updatedQuantity =productModel.getQuantity() - input;
		return updatedQuantity;
	}

}
