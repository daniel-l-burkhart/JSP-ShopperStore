package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import model.History;

public class HistoryDAOImpl implements HistoryDAO {

	private Connection mySQLConnection;

	public HistoryDAOImpl() {
		this.getDBConnection();
	}

	@Override
	public void addHistory(History newHistory) {
		this.getDBConnection();
		String sqlCommand = "INSER INTO history value(?,?,?,?,?,?)";

		PreparedStatement myPS;

		try {
			myPS = (PreparedStatement) this.mySQLConnection.prepareStatement(sqlCommand);

			myPS.setInt(1, newHistory.getHistoryID());
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
	public ArrayList<History> getHistoriesForUser(int userID) {
		this.getDBConnection();

		String sql = "SELECT * FROM history WHERE userID='" + userID + "'";
		ArrayList<History> orderHistory = new ArrayList<History>();

		try {

			PreparedStatement ps = (PreparedStatement) this.mySQLConnection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int historyID = rs.getInt("historyID");
				int foundUserID = rs.getInt("userID");
				int productCode = rs.getInt("productCode");
				Timestamp timeStamp = rs.getTimestamp("timeStamp");
				int number = rs.getInt("number");
				double moneyPaid = rs.getDouble("moneyPaid");
				orderHistory.add(new History(historyID, foundUserID, productCode, timeStamp, number, moneyPaid));
			}

			this.mySQLConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderHistory;
	}

	private void getDBConnection() {
		this.mySQLConnection = DBConnect.getConnecttion();
	}

}
