package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.mysql.jdbc.PreparedStatement;

import model.User;

public class UserDAOImpl implements UserDAO {

	private Connection mySQLConnection;

	@Override
	public void addUser(User u) {

		this.getDBConnection();
		String sqlCommand = "INSERT INTO user value(?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps;
		try {
			ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);

			ps.setInt(1, u.getUser_id());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setDate(4, u.getDateOfBirth());
			ps.setString(5, u.getGender());
			ps.setString(6, u.getEmail());
			ps.setString(7, u.getPhone());
			ps.setString(8, u.getAddress());
			ps.setString(9, u.getRole());
			ps.executeUpdate();

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkUser(String username) {
		this.getDBConnection();
		String sql = "select * from user where username='" + username + "'";
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
	public boolean login(String username, String password) {

		this.getDBConnection();

		String sql = "select * from user where username='" + username + "' and password='" + sha1(password) + "'";
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
	public void updateUser(User u) {

		this.getDBConnection();
		String sqlCommand = "UPDATE SET user_id=?, password=?, dateOfBirth=?, gender=?, email=?, phone=?, address=?, role=? WHERE username=?";

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);

			ps.setInt(1, u.getUser_id());
			ps.setString(2, u.getPassword());
			ps.setDate(3, u.getDateOfBirth());
			ps.setString(4, u.getGender());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getPhone());
			ps.setString(7, u.getAddress());
			ps.setString(8, u.getRole());
			ps.setString(9, u.getUsername());
			ps.executeUpdate();

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public User getUser(String username) {

		this.getDBConnection();
		String mySQLCommand = "SELECT * FROM user WHERE username='" + username + "'";
		User foundUser = null;

		try {
			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(mySQLCommand);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id = rs.getInt("user_id");
				String foundUsername = rs.getString("username");
				String password = rs.getString("password");
				Date dateOfBirth = rs.getDate("dateOfBirth");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String role = rs.getString("role");
				foundUser = new User(user_id, foundUsername, password, dateOfBirth, gender, email, phone, address,
						role);
			}

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return foundUser;
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
