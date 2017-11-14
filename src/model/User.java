package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * User class - OOP representation of user record in DB
 * 
 * @author daniel
 *
 */
public class User {

	private int user_id;
	private String email;
	private String password;
	private String fullName;
	private String phone;
	private String address;
	private String role;

	/**
	 * Empty constructor to satisfy "bean" rules.
	 */
	public User() {
		this.user_id = 0;
		this.email = "";
		this.password = "";
		this.fullName = "";
		this.phone = "";
		this.address = "";
		this.role = "";
	}

	/**
	 * Parameterized constructor used in controller classes
	 * 
	 * @param user_id
	 *            The user ID
	 * @param email
	 *            The user's email
	 * @param password
	 *            The user's password
	 * @param fullName
	 *            The user's full name
	 * @param phone
	 *            The user's phone number
	 * @param address
	 *            The user's address
	 * @param role
	 *            The user's role.
	 */
	public User(int user_id, String email, String password, String fullName, String phone, String address,
			String role) {

		if (user_id < 0 || email == null || password == null || fullName == null || phone == null || address == null
				|| role == null) {
			throw new IllegalArgumentException("Something is null. Figure it out.");
		}

		this.email = email;
		this.user_id = user_id;
		this.fullName = fullName;
		this.phone = phone;
		this.address = address;
		this.role = role;

		String shaPassword = sha1(password);
		this.password = shaPassword;
	}

	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = sha1(password);
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	private static String sha1(String input) {
		MessageDigest mDigest = null;
		StringBuffer sb = null;

		try {
			mDigest = MessageDigest.getInstance("SHA1");
			byte[] result = mDigest.digest(input.getBytes());
			sb = new StringBuffer();
			for (int i = 0; i < result.length; i++) {
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
