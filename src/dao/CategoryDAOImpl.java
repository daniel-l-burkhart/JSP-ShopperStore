package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.Category;

public class CategoryDAOImpl implements CategoryDAO {

	private Connection mySQLConnection;
	private ArrayList<Category> categoriesList;

	public CategoryDAOImpl() {
		this.mySQLConnection = DBConnect.getConnecttion();
		this.categoriesList = new ArrayList<Category>();
	}

	@Override
	public void addCategory(Category c) {

		this.getDBConnection();
		String sqlCommand = "INSERT INTO category value(?, ?)";
		PreparedStatement ps;

		try {
			ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);
			ps.setInt(1, c.getCategoryID());
			ps.setString(2, c.getCategoryName());
			ps.executeUpdate();
			this.mySQLConnection.close();

		} catch (SQLException exception) {
			exception.printStackTrace();
		}

	}

	@Override
	public void delCategory(int categoryID) {
		this.getDBConnection();
		String sqlCommand = "DELETE FROM category WHERE categoryID='" + categoryID + "'";

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);
			ps.executeUpdate();
			this.mySQLConnection.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public ArrayList<Category> getCategories() throws SQLException {
		this.getDBConnection();
		String sql = "select * from category";
		ResultSet resultSet = this.getResultSetFromQuery(sql);

		while (resultSet.next()) {
			int categoryID = resultSet.getInt("categoryID");
			String categoryName = resultSet.getString("categoryName");
			this.categoriesList.add(new Category(categoryID, categoryName));
		}
		this.mySQLConnection.close();

		return this.categoriesList;
	}

	@Override
	public Category getCategory(int categoryID) throws SQLException {
		this.getDBConnection();
		String sql = "SELECT * FROM category WHERE categoryID = '" + categoryID + "'";
		Category myCategory = new Category();

		ResultSet myRS = this.getResultSetFromQuery(sql);

		while (myRS.next()) {
			int dbCategoryID = myRS.getInt("categoryID");
			String categoryName = myRS.getString("categoryName");
			myCategory = new Category(dbCategoryID, categoryName);
		}
		this.mySQLConnection.close();

		return myCategory;
	}

	@Override
	public void updateCategory(Category c) {
		this.getDBConnection();
		String sqlCommand = "UPDATE category SET categoryName=? WHERE categoryID=?";

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);
			ps.setString(1, c.getCategoryName());
			ps.setInt(2, c.getCategoryID());
			ps.executeUpdate();
			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private ResultSet getResultSetFromQuery(String sqlCommand) {
		PreparedStatement myPS = null;
		ResultSet myRS = null;

		try {
			myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);
			myRS = myPS.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myRS;
	}

	private void getDBConnection() {
		this.mySQLConnection = DBConnect.getConnecttion();
	}

}
