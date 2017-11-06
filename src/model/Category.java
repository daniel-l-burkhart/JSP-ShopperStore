package model;

public class Category {

	private int categoryID;

	private String categoryName;

	public Category() {
		this.categoryID = 0;
		this.categoryName = "";
	}

	public Category(int categoryID, String categoryName) {
		if (categoryID < 0) {
			throw new IllegalArgumentException("Category ID cannot be negative");
		} else if (categoryName == null) {
			throw new IllegalArgumentException("Category name cannot be null");
		}

		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	public int getCategoryID() {
		return this.categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
