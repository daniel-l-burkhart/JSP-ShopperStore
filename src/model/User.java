package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class User {

	private int user_id;
	private String password;

	private String fullName;

	private String email;
	private String phone;
	private String address;
	private String role;

	public User() {

	}

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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = sha1(password);
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String newName) {
		this.fullName = newName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

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
