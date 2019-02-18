package com.perennialsys.Queries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.perennialsys.connection.GetConnection;
import com.perennialsys.model.CategoryModel;
import com.perennialsys.model.CheckCartModel;
import com.perennialsys.model.ProductModel;
import com.perennialsys.model.UserModel;

public class DatabaseQueries {
	private PreparedStatement ps;
	private Connection connection;

	public DatabaseQueries() {
		connection = GetConnection.getDbCon();
	}

	public void register(UserModel userModel, String password) {
		try {

			ps = connection.prepareStatement("Insert into user(userName,password,email,mobileNo,address) values(?,?,?,?,?)");
			ps.setString(1, userModel.getUserName());
			ps.setString(2, password);
			ps.setString(3, userModel.getEmail());
			ps.setLong(4, userModel.getMobileNo());
			ps.setString(5, userModel.getAddress());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int login(UserModel userModel) {
		try {
			BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
			ps = connection.prepareStatement("select uId,password from user where userName=?");
			ps.setString(1, userModel.getUserName());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				if (bpe.matches(userModel.getPassword(), resultSet.getString(2))) {
					return resultSet.getInt(1);
				}
			}
			ps.close();
			return 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public List<ProductModel> getSearchedProduct(String searchText) {
		try {
			ps = connection.prepareStatement("select * from product where product_name like ? ");
			ps.setString(1, searchText + "%");
			ResultSet resultSet = ps.executeQuery();
			List<ProductModel> product = new ArrayList<ProductModel>();
			while (resultSet.next()) {
				ProductModel productModel = new ProductModel();
				productModel.setId(resultSet.getInt(1));
				productModel.setProductName(resultSet.getString(2));
				productModel.setQuantity(resultSet.getInt(3));
				productModel.setPrice(resultSet.getInt(4));
				product.add(productModel);

			}
			ps.close();
			return product;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<CategoryModel> getCategory() {
		try {
			ps = connection.prepareStatement("select * from category");
			ResultSet resultSet = ps.executeQuery();
			List<CategoryModel> category = new ArrayList<CategoryModel>();
			while (resultSet.next()) {
				CategoryModel categoryModel = new CategoryModel();
				categoryModel.setCategoryId(resultSet.getInt(1));
				categoryModel.setCategoryName(resultSet.getString(2));
				category.add(categoryModel);
			}
			ps.close();
			return category;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public List<ProductModel> getProductDetails(){ 
		try {
			ps= connection.prepareStatement("select * from product");
			ResultSet resultSet = ps.executeQuery();
			List<ProductModel> product = new ArrayList<ProductModel>(); 
			while(resultSet.next()) { 
				ProductModel productModel = new ProductModel();
				productModel.setId(resultSet.getInt(1));
				productModel.setProductName(resultSet.getString(2));
				productModel.setQuantity(resultSet.getInt(3));
				productModel.setPrice(resultSet.getInt(4)); 
				product.add(productModel);
			}
			ps.close();
			return product;

		}catch(SQLException e) { 
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductModel> getProductDetails(int id) {
		try {
			ps = connection.prepareStatement("select * from product where pId =?");
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			List<ProductModel> product = new ArrayList<ProductModel>();
			while (resultSet.next()) {
				ProductModel productModel = new ProductModel();
				productModel.setId(resultSet.getInt(1));
				productModel.setProductName(resultSet.getString(2));
				productModel.setQuantity(resultSet.getInt(3));
				productModel.setPrice(resultSet.getInt(4));
				product.add(productModel);
			}
			ps.close();
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductModel> getProductDetailsfromCategory(int id) {
		try {
			ps = connection.prepareStatement("select * from product  where cId=?  ");
			ps.setInt(1, id);
			List<ProductModel> products = new ArrayList<ProductModel>();
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ProductModel productModel = new ProductModel();
				productModel.setId(resultSet.getInt(1));
				productModel.setProductName(resultSet.getString(2));
				productModel.setQuantity(resultSet.getInt(3));
				productModel.setPrice(resultSet.getInt(4));
				products.add(productModel);
			}
			ps.close();
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addToCart(int uId, ProductModel productModel, int pId, int quantity, int total) {

		try {
			ps = connection.prepareStatement("Insert into cart(uId,pId,input_quantity,total,available_quantity) values(?,?,?,?,?)");
			ps.setInt(1, uId);
			ps.setInt(2, pId);
			ps.setInt(3, quantity);
			ps.setInt(4, total);
			ps.setInt(5, productModel.getQuantity());
			int x = ps.executeUpdate();
			if (x == 1) {
				return true;
			}
			ps.close();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CheckCartModel> getcart(int userId) {
		try {
			ps = connection.prepareStatement("SELECT userName,product_Name,input_quantity,total,cartId,available_quantity from user,product,cart where user.uId = cart.uId AND product.pId=cart.pId AND cart.uId=?");
			ps.setInt(1, userId);
			List<CheckCartModel> cart = new ArrayList<CheckCartModel>();
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				CheckCartModel cartDetails = new CheckCartModel();
				cartDetails.setUserName(resultSet.getString(1));
				cartDetails.setProductName(resultSet.getString(2));
				cartDetails.setQuantity(resultSet.getInt(3));
				cartDetails.setTotalPrice(resultSet.getInt(4));
				cartDetails.setCartId(resultSet.getInt(5));
				cartDetails.setAvailableQuantity(resultSet.getInt(6));

				cart.add(cartDetails);
			}
			ps.close();
			return cart;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteFromCart(int userId, CheckCartModel checkCartModel) {
		try {
			ps = connection.prepareStatement("DELETE cart from cart  INNER JOIN product ON product.pId = cart.pId where uId=? AND product.product_Name=? And cartId=? ");
			ps.setInt(1, userId);
			ps.setString(2, checkCartModel.getProductName());
			ps.setInt(3, checkCartModel.getCartId());
			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<ProductModel> getProductDetail(String productName) {
		try {
			ps = connection.prepareStatement("select * from product where product_name=?");
			ps.setString(1, productName);
			List<ProductModel> productDetails = new ArrayList<ProductModel>();
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				ProductModel products = new ProductModel();
				products.setId(resultSet.getInt(1));
				products.setProductName(resultSet.getString(2));
				products.setPrice(resultSet.getInt(3));
				products.setQuantity(resultSet.getInt(4));
				productDetails.add(products);

			}
			ps.close();
			return productDetails;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateCart(int input, int total, int orderId) {
		try {

			ps = connection.prepareStatement("UPDATE cart set input_quantity=?,total=? where cartId=?");
			ps.setInt(1, input);
			ps.setInt(2, total);
			ps.setInt(3, orderId);
			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}
			ps.close();
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public void updateQuantity(int remainingProduct, int pId) {
		try {
			System.out.println(remainingProduct);
			System.out.println(pId);
			ps = connection.prepareStatement("UPDATE product set Quantity =? where pId=?");
			ps.setInt(1, remainingProduct);
			ps.setInt(2, pId);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
