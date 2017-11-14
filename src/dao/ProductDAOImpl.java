package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.Product;

/**
 * Java class to interact with Product table
 * 
 * @author Daniel
 *
 */
public class ProductDAOImpl implements ProductDAO {

	private Connection mySQLConnection;

	/**
	 * Constructor for class
	 */
	public ProductDAOImpl() {
		this.mySQLConnection = DBConnect.getConnecttion();
	}

	@Override
	public void addProduct(Product myProduct) {
		this.getDBConnection();
		String sql = "insert into product value(?,?,?,?,?,?,?)";
		PreparedStatement ps;

		try {
			ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sql);

			ps.setInt(1, myProduct.getCategory());
			ps.setString(2, myProduct.getProductName());
			ps.setString(3, myProduct.getPictureName());
			ps.setDouble(4, myProduct.getPrice());
			ps.setString(5, myProduct.getManufacturer());
			ps.setString(6, myProduct.getInformation());
			ps.setInt(7, myProduct.getQuantity());

			ps.executeUpdate();
			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Product getSingleProductFromID(int productID) {

		this.getDBConnection();
		String sqlCommand = "SELECT * FROM product where productCode='" + productID + "'";
		Product myProduct = null;

		try {
			PreparedStatement myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);
			ResultSet myRS = myPS.executeQuery();

			while (myRS.next()) {
				int productCode = myRS.getInt("productCode");
				int categoryID = myRS.getInt("categoryID");
				String productName = myRS.getString("productName");
				String pictureName = myRS.getString("pictureName");
				Double price = myRS.getDouble("price");
				String manufacturer = myRS.getString("manufacturer");
				String information = myRS.getString("information");
				int quantity = myRS.getInt("quantity");

				myProduct = new Product(productCode, categoryID, productName, pictureName, price, manufacturer,
						information, quantity);
			}

			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myProduct;
	}

	@Override
	public ArrayList<Product> getProducts() {

		this.getDBConnection();
		String sqlCommand = "SELECT * FROM product";
		return this.getAllColumnsListOfProductsBySQL(sqlCommand);
	}

	@Override
	public ArrayList<Product> getAllProductsFromCategory(int categoryID) {

		this.getDBConnection();
		String sqlCommand = "SELECT * from product where categoryID = '" + categoryID + "'";
		return this.getAllColumnsListOfProductsBySQL(sqlCommand);
	}

	@Override
	public ArrayList<Product> searchProducts(String productName, String categoryName) {
		this.getDBConnection();

		String searchByBothFields = "SELECT * FROM product, category WHERE productName LIKE '%" + productName
				+ "%' AND product.categoryID = category.categoryID AND categoryName=N'" + categoryName + "'";

		String searchByCategoryName = "SELECT * FROM product, category WHERE product.categoryID = category.categoryID AND categoryName=N'"
				+ categoryName + "'";

		String searchByProductName = "SELECT * FROM product, category WHERE productName LIKE '%" + productName
				+ "%' AND product.categoryID=category.categoryID";

		String sqlCommand = "";

		if (!productName.equals("") && !categoryName.equals("")) {
			sqlCommand = searchByBothFields;
		} else if (productName.equals("") && !categoryName.equals("")) {
			sqlCommand = searchByCategoryName;
		} else if (!productName.equals("") && categoryName.equals("")) {
			sqlCommand = searchByProductName;
		} else {
			sqlCommand = "SELECT * FROM product";
		}

		return this.getAllColumnsListOfProductsBySQL(sqlCommand);
	}

	@Override
	public boolean updateQuantity(int productCode, int quantity) {

		this.getDBConnection();

		String sql = "SELECT quantity FROM product WHERE productCode='" + productCode + "'";
		int oldQuantity = 0;
		try {
			PreparedStatement myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(sql);
			ResultSet myRS = myPS.executeQuery();
			myRS.next();
			oldQuantity = myRS.getInt("quantity");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int resultQuantity = oldQuantity - quantity;

		String command = "UPDATE product SET quantity='" + resultQuantity + "' WHERE productCode='" + productCode + "'";

		int resultValue = -2;
		try {
			PreparedStatement myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(command);
			resultValue = myPS.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultValue == 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean replinishQuantity(int productCode, int newQuantity) {

		this.getDBConnection();

		String command = "UPDATE product SET quantity='" + newQuantity + "' WHERE productCode='" + productCode + "'";

		int resultValue = -2;
		try {
			PreparedStatement myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(command);
			resultValue = myPS.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultValue == 1) {
			return true;
		} else {
			return false;
		}

	}

	private void getDBConnection() {
		this.mySQLConnection = DBConnect.getConnecttion();
	}

	private ArrayList<Product> getAllColumnsListOfProductsBySQL(String sqlCommand) {

		ArrayList<Product> productList = new ArrayList<Product>();

		try {
			PreparedStatement myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);
			ResultSet myRS = myPS.executeQuery();

			while (myRS.next()) {
				int productCode = myRS.getInt("productCode");
				int categoryID = myRS.getInt("categoryID");
				String productName = myRS.getString("productName");
				String pictureName = myRS.getString("pictureName");
				Double price = myRS.getDouble("price");
				String manufacturer = myRS.getString("manufacturer");
				String information = myRS.getString("information");
				int quantity = myRS.getInt("quantity");

				productList.add(new Product(productCode, categoryID, productName, pictureName, price, manufacturer,
						information, quantity));
			}

			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productList;
	}
}
