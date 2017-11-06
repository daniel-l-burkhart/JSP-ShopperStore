package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Category;

public interface CategoryDAO {

	public void addCategory(Category c);

	public void delCategory(int categoryID);

	public ArrayList<Category> getCategories() throws SQLException;

	public Category getCategory(int categoryID) throws SQLException;

	public void updateCategory(Category c);

}