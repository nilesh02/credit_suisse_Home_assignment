package dataDetails;

public class AccountDetails {

	private Long longAccount;
	private String strName;
	private String strAddress;
	private Long longPhoneNumber;
	
	public AccountDetails(Long longAccount, String strName, String strAddress, Long longPhoneNumber) {
		this.longAccount = longAccount;
		this.strName = strName;
		this.strAddress = strAddress;
		this.longPhoneNumber = longPhoneNumber;
	}

	public Long getLongAccount() {
		return longAccount;
	}

	public void setLongAccount(Long longAccount) {
		this.longAccount = longAccount;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}

	public Long getLongPhoneNumber() {
		return longPhoneNumber;
	}

	public void setLongPhoneNumber(Long longPhoneNumber) {
		this.longPhoneNumber = longPhoneNumber;
	}

	@Override
	public String toString() {
		return "AccountDetails [longAccount=" + longAccount + ", strName=" + strName + ", strAddress=" + strAddress
				+ ", longPhoneNumber=" + longPhoneNumber + "]";
	}
	
	
}
