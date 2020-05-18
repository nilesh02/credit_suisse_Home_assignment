package dataProcess;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataDetails.AccountDetails;
import dataDetails.TransactionDetails;

public class DataProcessAccountDetails {

	private int AccountDetails_Account=0;
	private int AccountDetails_Name=1;
	private int AccountDetails_Address=2;
	private int AccountDetails_PhoneNumber=3;
	
	public int getAccountDetails_Account() {
		return AccountDetails_Account;
	}

	public int getAccountDetails_Name() {
		return AccountDetails_Name;
	}

	public int getAccountDetails_Address() {
		return AccountDetails_Address;
	}

	public int getAccountDetails_PhoneNumber() {
		return AccountDetails_PhoneNumber;
	}
	
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to parse the given String data into HashMap
	 * @Parameters: FileData 'String' parameter is data gathered from file and needs to be processed. 
	 * @Return Type: HashMap with Account Number as key and AccountDetails as value.
	 * 
	 */
	public HashMap<Long,AccountDetails> processDataAccountDetails(String strFileData) {
		HashMap<Long,AccountDetails> mapAccountData = new HashMap<Long,AccountDetails>();
		
		// converting File Data into String array
		String[] strArrayFileData =strFileData.split("\n");
		
		// checking if file contains some data, if not then return
		if(strArrayFileData.length==0) {
			return mapAccountData;
		}
		// extracting and setting of position of header tags
		setHeadersForAccountDetails(strArrayFileData[0]);
		
		// looping over the Array
		for(int index=1; index < strArrayFileData.length; index++) {
			try {
				String[] strArrayAccountRowDetail = strArrayFileData[index].toString().trim().replaceAll("\\s+", " ").split("\\|");
				Long longAccountNumber=Long.parseLong(strArrayAccountRowDetail[AccountDetails_Account].trim());
				AccountDetails accountDetails=new AccountDetails(
						longAccountNumber,
						strArrayAccountRowDetail[AccountDetails_Name].trim(),
						strArrayAccountRowDetail[AccountDetails_Address].trim(),
						Long.parseLong(strArrayAccountRowDetail[AccountDetails_PhoneNumber].trim()));
//				if(mapAccountData.containsKey(longAccountNumber)) {
//					System.out.println("Repeated Entry Found For Account Number-"+longAccountNumber+ ". Replacing the previous record.");
//				}
					mapAccountData.put(longAccountNumber, accountDetails);	
			} catch(Exception e) {
				System.out.println("processDataAccountDetails-->"+e);
			}
		}
		return mapAccountData;
	}
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to map the given Header data with Header index
	 * @Parameters: HeaderData 'String' parameter is header data gathered from file and needs to be indexed. 
	 * @Return Type: void
	 * 
	 */
	
	public void setHeadersForAccountDetails(String strHeaderData) {
		// converting Header Data into String array
		String[] strArrayHeaderData=strHeaderData.trim().replaceAll("\\s", "").split("\\|");
		for(int index=0; index < strArrayHeaderData.length; index++) {
			switch(strArrayHeaderData[index].toLowerCase()) {
				case "account":AccountDetails_Account=index;break;
				case "name":AccountDetails_Name=index;break;
				case "address":AccountDetails_Address=index;break;
				case "phonenumber":AccountDetails_PhoneNumber=index;break;
			}
		}
	}
}
