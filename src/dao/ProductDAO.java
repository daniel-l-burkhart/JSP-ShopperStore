package dao;

import java.util.ArrayList;

import model.Product;

public interface ProductDAO {

	public void addProduct(Product myProduct);

	public ArrayList<Product> getProducts();

	public Product getSingleProductFromID(int productID);

	public ArrayList<Product> searchProducts(String productName, String categoryName);

	public ArrayList<Product> getAllProductsFromCategory(int categoryID);

}
