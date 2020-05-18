package dataProcess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dataDetails.AccountDetails;
import dataDetails.TransactionDetails;

public class FindSuspiciousAccounts {

	private HashMap<String,List<String>> mapSuspiciousTransactionId=new HashMap<String, List<String>>();
	private Set<Set<Long>> setSuspiciousAccounts=new HashSet<Set<Long>>();	
	
	public HashMap<String, List<String>> getMapSuspiciousTransactionId() {
		return mapSuspiciousTransactionId;
	}
	
	public Set<Set<Long>> getSetSuspiciousAccounts() {
		return setSuspiciousAccounts;
	}
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to compare data for transactions and store data in Hashmap's
	 * @Parameters: mapAccountData 'HashMap<Long,AccountDetails>' &  mapMonthData 'HashMap<String,List<TransactionDetails>>' parameter is data gathered from file and are processed in required way. 
	 * @Return Type: void
	 * 
	 */
	
	public void findSuspiciousAccounts(HashMap<Long,AccountDetails> mapAccountData, HashMap<String,List<TransactionDetails>> mapMonthData) {		
		// sorting the key sets
//		ArrayList<String> listMonths=new ArrayList<String>();
//		listMonths.addAll(mapMonthData.keySet());
//		Collections.sort(listMonths);
		
		// looping over listMonths
		for(String strMonth: mapMonthData.keySet() ) {
			for(TransactionDetails transactionDetail: mapMonthData.get(strMonth)) {
				//getting all data
				long longFromAccount=transactionDetail.getLongFromAccount();
				long longToAccount=transactionDetail.getLongToAccount();
				
				String strFromAddress=mapAccountData.get(longFromAccount).getStrAddress().trim().replaceAll("\\s", "").toLowerCase();
				long longFromPhoneNumber=mapAccountData.get(longFromAccount).getLongPhoneNumber();
				
				String strToAddress=mapAccountData.get(longToAccount).getStrAddress().trim().replaceAll("\\s", "").toLowerCase();
				long longToPhoneNumber=mapAccountData.get(longToAccount).getLongPhoneNumber();
				
				// checking if account is suspicious
				if(strFromAddress.equals(strToAddress) && longFromPhoneNumber==longToPhoneNumber) {
					// add transaction id details in mapSuspiciousTransactionId
					if(mapSuspiciousTransactionId.containsKey(strMonth)) {
						mapSuspiciousTransactionId.get(strMonth).add(transactionDetail.getStrTransactionId());
					} else {
						List<String> listSuspiciousAccounts=new ArrayList<String>();
						listSuspiciousAccounts.add(transactionDetail.getStrTransactionId());
						mapSuspiciousTransactionId.put(strMonth,listSuspiciousAccounts);
					}
					// add Account Number details set in setSuspiciousAccounts
					Set<Long> setAccountNumber=new HashSet<Long>();
					setAccountNumber.add(longFromAccount);
					setAccountNumber.add(longToAccount);
					setSuspiciousAccounts.add(setAccountNumber);
				}
			}
		}
	}
	
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to display suspicious transaction's month wise
	 * @Parameters: global variables
	 * @Return Type: void
	 * 
	 */
	
	public String displaySuspiciousTransactionId() throws ParseException {
		// creating date format
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("MM");
		SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("MMM");
		// sorting the key sets
//		ArrayList<String> listMonths=new ArrayList<String>();
//		listMonths.addAll(mapSuspiciousTransactionId.keySet());
//		Collections.sort(listMonths);
		String strFinalOutput="";
		// looping over HashMap and displaying Suspicious Transaction on screen according to month.
		for(String strMonth: mapSuspiciousTransactionId.keySet()) {
			strFinalOutput+=("For The Month of "+simpleDateFormat1.format(simpleDateFormat.parse(strMonth))+":")+"\n";
			strFinalOutput+=("Suspicious Transactions :")+"\n";
			// can store Transaction Id directly into String but prefer to store in List
			for(String strTransactionId: mapSuspiciousTransactionId.get(strMonth)) {
				strFinalOutput+=(strTransactionId)+"\n";
			}
			strFinalOutput+="\n";
		}
		return strFinalOutput;
	}
	
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to display suspicious account numbers
	 * @Parameters: global variables
	 * @Return Type: void
	 * 
	 */
	
	public String displaySuspiciousAccounts() {
		String strFinalOutput="";
		if(setSuspiciousAccounts.size()>0)
			strFinalOutput+=("Suspicious Accounts")+"\n";
		// looping over set and displaying it on screen
		for(Set<Long> setSuspiciousAccountNumber:setSuspiciousAccounts) {
			strFinalOutput+=(setSuspiciousAccountNumber)+"\n";
		}
		return strFinalOutput;
	}
}
