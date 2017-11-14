package dao;

import java.util.ArrayList;

import model.Order;

public interface OrderDAO {

	/**
	 * Adds new purchase history to database
	 * 
	 * @param newHistory
	 *            The object represention of the purchase.
	 */
	public void addHistory(Order newHistory);

	/**
	 * Gets all purchases for a user
	 * 
	 * @param userID
	 *            The user's ID
	 * @return An arraylist of all of their purchases
	 */
	public ArrayList<Order> getHistoriesForUser(int userID);

	/**
	 * Gets all sales orders for Admin view
	 * 
	 * @return A list of sales orders
	 */
	public ArrayList<Order> getHistoriesForAdmin();

}
