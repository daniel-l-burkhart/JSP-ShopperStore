package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.User;

public class UserDAOImpl implements UserDAO {

	private Connection mySQLConnection;

	@Override
	public boolean addUser(User u) {

		this.getDBConnection();
		String sqlCommand = "INSERT INTO user value(?,?,?,?,?,?,?)";

		PreparedStatement ps;
		try {
			ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);

			ps.setString(1, "0");
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getPassword());
			ps.setString(4, u.getPhone());
			ps.setString(5, u.getAddress());
			ps.setString(6, u.getRole());
			ps.setString(7, u.getFullName());

			ps.executeUpdate();

			this.mySQLConnection.close();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean checkUser(String email) {
		this.getDBConnection();
		String sql = "select * from user where email='" + email + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				this.mySQLConnection.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public User login(String email, String password) {

		User resultUser = new User();

		this.getDBConnection();

		String sql = "select * from user where email='" + email + "' and password='" + sha1(password) + "'";
		PreparedStatement ps;

		try {
			ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				resultUser = this.getUser(email);
				this.mySQLConnection.close();

				return resultUser;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateUser(User u) {

		this.getDBConnection();
		String sqlCommand = "UPDATE SET email=?, phone=?, address=?, role=?, password=? WHERE user_id=?";

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);

			ps.setString(1, u.getEmail());

			ps.setString(2, u.getPhone());
			ps.setString(3, u.getAddress());
			ps.setString(4, u.getRole());
			ps.setString(5, sha1(u.getPassword()));

			ps.setInt(6, u.getUser_id());

			ps.executeUpdate();

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User getUser(String email) {

		this.getDBConnection();
		String mySQLCommand = "SELECT * FROM user WHERE email='" + email + "'";
		User foundUser = null;

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(mySQLCommand);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("userID");
				String password = rs.getString("password");
				String foundEmail = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");
				String fullName = rs.getString("fullName");
				foundUser = new User(user_id, foundEmail, password, fullName, phone, address, role);
			}
			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundUser;
	}

	@Override
	public User getUserFromID(String id) {

		this.getDBConnection();
		String mySQLCommand = "SELECT * FROM user WHERE userID='" + id + "' LIMIT 1";
		User foundUser = null;

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(mySQLCommand);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("userID");
				String password = rs.getString("password");
				String foundEmail = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");
				String fullName = rs.getString("fullName");
				foundUser = new User(user_id, foundEmail, password, fullName, phone, address, role);
			}
			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundUser;
	}

	@Override
	public ArrayList<User> getUsersForAdminView() {

		this.getDBConnection();
		String mySQLCommand = "SELECT userID, email, fullName, phone, address, role FROM user";
		ArrayList<User> resultList = new ArrayList<User>();

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(mySQLCommand);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User currUser = null;
				int user_id = rs.getInt("userID");
				String foundEmail = rs.getString("email");
				String fullName = rs.getString("fullName");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");
				currUser = new User(user_id, foundEmail, "", fullName, phone, address, role);
				resultList.add(currUser);
			}
			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	@Override
	public void deleteUserByID(String id) {

		this.getDBConnection();

		String command = "DELETE FROM user WHERE userID=?";

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(command);
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();

		} catch (SQLException e) {

		}

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

	private void getDBConnection() {
		this.mySQLConnection = DBConnect.getConnecttion();
	}

}
