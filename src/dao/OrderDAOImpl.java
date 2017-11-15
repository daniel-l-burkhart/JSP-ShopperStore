package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.Order;

public class OrderDAOImpl implements OrderDAO {

	private Connection mySQLConnection;

	public OrderDAOImpl() {
		this.getDBConnection();
	}

	@Override
	public void addHistory(Order newHistory) {
		this.getDBConnection();
		String sqlCommand = "INSERT INTO `store`.`order`"
				+ "(`orderID`,`userID`,`productCode`,`timestamp`,`quantity`,`price`) " + " VALUES (?,?,?,?,?,?)";

		PreparedStatement myPS;

		try {
			myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);

			myPS.setInt(1, newHistory.getOrderID());
			myPS.setInt(2, newHistory.getUserID());
			myPS.setInt(3, newHistory.getProductCode());
			myPS.setTimestamp(4, newHistory.getTimeStamp());
			myPS.setInt(5, newHistory.getNumber());
			myPS.setDouble(6, newHistory.getMoneyPaid());

			myPS.executeUpdate();

			this.mySQLConnection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<Order> getHistoriesForUser(int userID) {
		this.getDBConnection();

		String sql = "SELECT * FROM `order` WHERE userID='" + userID + "'";
		ArrayList<Order> orderHistory = new ArrayList<Order>();

		try {

			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int historyID = rs.getInt("orderID");
				int foundUserID = rs.getInt("userID");
				int productCode = rs.getInt("productCode");
				Timestamp timeStamp = rs.getTimestamp("timeStamp");
				int number = rs.getInt("quantity");
				double moneyPaid = rs.getDouble("price");
				orderHistory.add(new Order(historyID, foundUserID, productCode, timeStamp, number, moneyPaid));
			}

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderHistory;
	}

	@Override
	public ArrayList<Order> getHistoriesForAdmin() {
		this.getDBConnection();
		String command = "SELECT * FROM `order`";
		ArrayList<Order> sales = new ArrayList<Order>();

		try {

			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(command);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int historyID = rs.getInt("orderID");
				int foundUserID = rs.getInt("userID");
				int productCode = rs.getInt("productCode");
				Timestamp timeStamp = rs.getTimestamp("timeStamp");
				int number = rs.getInt("quantity");
				double moneyPaid = rs.getDouble("price");
				sales.add(new Order(historyID, foundUserID, productCode, timeStamp, number, moneyPaid));
			}

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sales;
	}

	private void getDBConnection() {
		this.mySQLConnection = DBConnect.getConnecttion();
	}

}
