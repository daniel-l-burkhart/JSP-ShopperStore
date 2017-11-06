package dao;

import java.util.ArrayList;

import model.History;

public interface HistoryDAO {

	/**
	 * Adds new purchase history to database
	 * 
	 * @param newHistory
	 *            The object represention of the purchase.
	 */
	public void addHistory(History newHistory);

	/**
	 * Gets all purchases for a user
	 * 
	 * @param userID
	 *            The user's ID
	 * @return An arraylist of all of their purchases
	 */
	public ArrayList<History> getHistoriesForUser(int userID);

}
