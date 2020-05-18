package dataProcess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataDetails.TransactionDetails;

public class DataProcessTransactionDetails {

	private int TransactionDetails_TransactionId=0;
	private int TransactionDetails_TransactionDate=1;
	private int TransactionDetails_FromAccount=2;
	private int TransactionDetails_ToAccount=3;
	private int TransactionDetails_Amount=4;
	
	
	
	public int getTransactionDetails_TransactionId() {
		return TransactionDetails_TransactionId;
	}

	public int getTransactionDetails_TransactionDate() {
		return TransactionDetails_TransactionDate;
	}

	public int getTransactionDetails_FromAccount() {
		return TransactionDetails_FromAccount;
	}

	public int getTransactionDetails_ToAccount() {
		return TransactionDetails_ToAccount;
	}

	public int getTransactionDetails_Amount() {
		return TransactionDetails_Amount;
	}

	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to parse the given String data into HashMap
	 * @Parameters: FileData 'String' parameter is data gathered from file and needs to be processed. 
	 * @Return Type: HashMap with Months as key and TransactionDetails as value.
	 * 
	 */
	public HashMap<String,List<TransactionDetails>> processDataTransactionDetails(String strFileData) throws ParseException {
		
		HashMap<String,List<TransactionDetails>> mapMonthData = new HashMap<String,List<TransactionDetails>>();
		// creating date formats
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("MM");
		
		// converting File Data into String array
		String[] strArrayFileData =strFileData.split("\n");
		
		// checking if file contains some data, if not then return
		if(strArrayFileData.length==0) {
			return mapMonthData;
		}
				
		// extracting and setting of position of header tags
		setHeadersForTransactionDetails(strArrayFileData[0]);
		
		// looping over the Array
		for(int index=1; index < strArrayFileData.length; index++) {
			try {
				String[] strArrayTransactionRowDetail = strArrayFileData[index].toString().trim().replaceAll("\\s", "").split("\\|");
				String strMonth=simpleDateFormat1.format(simpleDateFormat.parse(strArrayTransactionRowDetail[TransactionDetails_TransactionDate]));
				TransactionDetails transactionDetails=new TransactionDetails(
						strArrayTransactionRowDetail[TransactionDetails_TransactionId], 
						simpleDateFormat.parse(strArrayTransactionRowDetail[TransactionDetails_TransactionDate]),
						Long.parseLong(strArrayTransactionRowDetail[TransactionDetails_FromAccount]),
						Long.parseLong(strArrayTransactionRowDetail[TransactionDetails_ToAccount]), 
						Double.parseDouble(strArrayTransactionRowDetail[TransactionDetails_Amount]));
				
				if(mapMonthData.containsKey(strMonth)) {
					mapMonthData.get(strMonth).add(transactionDetails);
				} else {
					List<TransactionDetails> listTransactionDetails=new ArrayList<TransactionDetails>();
					listTransactionDetails.add(transactionDetails);
					mapMonthData.put(strMonth, listTransactionDetails);
				}
				
			} catch(Exception e) {
				System.out.println("processDataTransactionDetails-->"+e);
			}
		}
		return mapMonthData;
	}
	
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to map the given Header data with Header index
	 * @Parameters: HeaderData 'String' parameter is header data gathered from file and needs to be indexed. 
	 * @Return Type: void
	 * 
	 */
	
	public void setHeadersForTransactionDetails(String strHeaderData) {
		// converting Header Data into String array
		String[] strArrayHeaderData=strHeaderData.trim().replaceAll("\\s", "").split("\\|");
		for(int index=0; index < strArrayHeaderData.length; index++) {
			switch(strArrayHeaderData[index].toLowerCase()) {
				case "transactionid":TransactionDetails_TransactionId=index;break;
				case "transactiondate":TransactionDetails_TransactionDate=index;break;
				case "fromaccount":TransactionDetails_FromAccount=index;break;
				case "toaccount":TransactionDetails_ToAccount=index;break;
				case "amount":TransactionDetails_Amount=index;break;
			}
		}
	}
}
