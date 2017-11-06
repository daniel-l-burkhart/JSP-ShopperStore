package model;

import dao.ProductDAOImpl;

public class CartItem {

	private ProductDAOImpl productImpl;
	private Product myProduct;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.myProduct = product;
		this.quantity = quantity;
	}

	public CartItem(int productCode, int quantity) {

		this.myProduct = this.productImpl.getSingleProductFromID(productCode);
		this.quantity = quantity;
	}

	public Product getProduct() {
		return this.myProduct;
	}

	public void setProduct(Product product) {
		this.myProduct = product;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
