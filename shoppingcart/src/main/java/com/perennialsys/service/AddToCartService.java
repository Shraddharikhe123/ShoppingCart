package com.perennialsys.service;

public class AddToCartService {

	public int getDetails(int input,int quantity,int price) {
		
		if(input <=quantity) {
			int total = input * price;
			return total;
		}
		return 0;
	}
}
