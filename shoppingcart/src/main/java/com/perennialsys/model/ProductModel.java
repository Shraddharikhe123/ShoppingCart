package com.perennialsys.model;

public class ProductModel {

	private int id;
	private String productName;
	private int quantity;
	private int price;

	public ProductModel() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "id=" + id + ", productName=" + productName + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}

}
