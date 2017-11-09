package dao;

import model.User;

public interface UserDAO {

	/**
	 * Adds user to db
	 * 
	 * @param u
	 *            The user object from the controller
	 */
	public boolean addUser(User u);

	/**
	 * Checks that the username is in the DB
	 * 
	 * @param username
	 *            The username
	 * @return True if in DB, false otherwise
	 */
	public boolean checkUser(String username);

	/**
	 * Compare the username and password to the user's input
	 * 
	 * @param username
	 *            The user's username
	 * @param password
	 *            The password
	 * @return True if successful, false otherwise
	 */
	public User login(String username, String password);

	/**
	 * Updates the user
	 * 
	 * @param u
	 *            The user object
	 */
	public void updateUser(User u);

	/**
	 * Get a user from their username
	 * 
	 * @param username
	 *            The username
	 * @return The object representation of the user
	 */
	public User getUser(String username);

}
