package model;

public class Product {

	private int productCode;
	private int category;
	private String productName;
	private String pictureName;
	private double price;
	private String manufacturer;
	private String information;

	public Product() {

	}

	public Product(int productCode, int category, String productName, String pictureName, double price,
			String rowExport, String information) {

		this.productCode = productCode;
		this.category = category;
		this.productName = productName;
		this.pictureName = pictureName;
		this.price = price;
		this.manufacturer = rowExport;
		this.information = information;
	}

	public int getProductCode() {
		return this.productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public int getCategory() {
		return this.category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPictureName() {
		return this.pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getInformation() {
		return this.information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

}
