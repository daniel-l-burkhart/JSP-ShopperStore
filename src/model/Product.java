package model;

/**
 * Product class - model object representation of database record for product
 * 
 * @author daniel
 */
public class Product {

	private int productCode;
	private int category;
	private String productName;
	private String pictureName;
	private double price;
	private String manufacturer;
	private String information;
	private int quantity;

	/**
	 * Product constructor
	 */
	public Product() {

	}

	/**
	 * Product constructor
	 * 
	 * @param productCode
	 *            The product's uniquely identifiable product code.
	 * @param category
	 *            The product's category
	 * @param productName
	 *            The product's name
	 * @param pictureName
	 *            The name of the image that goes with this product
	 * @param price
	 *            The price of the product
	 * @param manufacturer
	 *            The product's manufacturer
	 * @param information
	 *            Description of the product
	 */
	public Product(int productCode, int category, String productName, String pictureName, double price,
			String manufacturer, String information, int quantity) {

		this();
		this.productCode = productCode;
		this.category = category;
		this.productName = productName;
		this.pictureName = pictureName;
		this.price = price;
		this.manufacturer = manufacturer;
		this.information = information;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the productCode
	 */
	public int getProductCode() {
		return productCode;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return the pictureName
	 */
	public String getPictureName() {
		return pictureName;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @return the information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @param pictureName
	 *            the pictureName to set
	 */
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @param information
	 *            the information to set
	 */
	public void setInformation(String information) {
		this.information = information;
	}

}
