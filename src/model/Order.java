package model;

import java.sql.Timestamp;

/**
 * Order history class - purchase object
 * 
 * @author daniel
 */
public class Order {

	private int orderID;
	private int userID;
	private int productCode;
	private Timestamp timeStamp;
	private int number;
	private double moneyPaid;

	public Order() {

	}

	/**
	 * Order constructor
	 * 
	 * @param orderID
	 *            The ID for this row in the history table
	 * @param userID
	 *            The user's ID
	 * @param productCode
	 *            The product's code
	 * @param timestamp
	 *            The timestamp that this order was placed
	 * @param number
	 *            The quantity
	 * @param moneyTendered
	 *            The order total
	 */
	public Order(int historyID, int userID, int productCode, Timestamp timestamp, int number, double moneyTendered) {
		if (historyID < 0 || userID < 0 || productCode < 0 || number < 0) {
			throw new IllegalArgumentException("Negative number found.");
		} else if (timestamp == null) {
			throw new IllegalArgumentException("timestamp null.");
		} else if (moneyTendered < 0.0) {
			throw new IllegalArgumentException("Negative money???");
		}
		this.orderID = historyID;
		this.userID = userID;
		this.productCode = productCode;
		this.timeStamp = timestamp;
		this.number = number;
		this.moneyPaid = moneyTendered;
	}

	/**
	 * @return the orderID
	 */
	public int getOrderID() {
		return orderID;
	}

	/**
	 * @param orderID
	 *            the orderID to set
	 */
	public void setOrderID(int historyID) {
		this.orderID = historyID;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return this.userID;
	}

	/**
	 * @param userID
	 *            the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the productCode
	 */
	public int getProductCode() {
		return this.productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the moneyPaid
	 */
	public double getMoneyPaid() {
		return this.moneyPaid;
	}

	/**
	 * @param moneyPaid
	 *            the moneyPaid to set
	 */
	public void setMoneyPaid(double moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

}
