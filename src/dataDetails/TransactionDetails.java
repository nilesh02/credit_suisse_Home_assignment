package dataDetails;

import java.util.Date;

public class TransactionDetails {

	private String strTransactionId;
	private Date strTransactionDate;
	private Long longFromAccount;
	private Long longToAccount;
	private double longAmount;
	
	public TransactionDetails(String strTransactionId, Date strTransactionDate, Long longFromAccount,Long longToAccount, double longAmount) {
		this.strTransactionId = strTransactionId;
		this.strTransactionDate = strTransactionDate;
		this.longFromAccount = longFromAccount;
		this.longToAccount = longToAccount;
		this.longAmount = longAmount;
	}

	public String getStrTransactionId() {
		return strTransactionId;
	}

	public void setStrTransactionId(String strTransactionId) {
		this.strTransactionId = strTransactionId;
	}

	public Date getStrTransactionDate() {
		return strTransactionDate;
	}

	public void setStrTransactionDate(Date strTransactionDate) {
		this.strTransactionDate = strTransactionDate;
	}

	public Long getLongFromAccount() {
		return longFromAccount;
	}

	public void setLongFromAccount(Long longFromAccount) {
		this.longFromAccount = longFromAccount;
	}

	public Long getLongToAccount() {
		return longToAccount;
	}

	public void setLongToAccount(Long longToAccount) {
		this.longToAccount = longToAccount;
	}

	public double getLongAmount() {
		return longAmount;
	}

	public void setLongAmount(double longAmount) {
		this.longAmount = longAmount;
	}

	@Override
	public String toString() {
		return "TransactionDetails [strTransactionId=" + strTransactionId + ", strTransactionDate=" + strTransactionDate
				+ ", longFromAccount=" + longFromAccount + ", longToAccount=" + longToAccount + ", longAmount="
				+ longAmount + "]";
	}
	
	
}
