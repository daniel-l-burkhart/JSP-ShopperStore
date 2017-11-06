package model;

import java.util.ArrayList;

/**
 * Cart class that contains CartItem's
 * 
 * @author daniel
 */
public class Cart {

	private ArrayList<CartItem> lineItems;

	/**
	 * The Cart Constructor.
	 */
	public Cart() {
		this.lineItems = new ArrayList<CartItem>();
	}

	/**
	 * Returns the list of line items
	 * 
	 * @return The list of line items.
	 */
	public ArrayList<CartItem> getLineItems() {
		return this.lineItems;
	}

	/**
	 * Adds the line item to the cart
	 * 
	 * @param item
	 *            The Cart item
	 */
	public void addLineItemToCart(CartItem item) {
		this.lineItems.add(item);
	}

	public boolean removeCartItem(CartItem itemToBeRemoved) {

		for (CartItem item : this.lineItems) {

			if (item.getProduct().getProductCode() == itemToBeRemoved.getProduct().getProductCode()) {
				this.lineItems.remove(item);
				return true;
			}
		}

		return false;
	}

	public void clearCart() {
		this.lineItems.clear();
	}

}
