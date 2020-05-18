import java.util.HashMap;
import java.util.List;

import dataDetails.AccountDetails;
import dataDetails.TransactionDetails;
import dataProcess.DataProcessAccountDetails;
import dataProcess.DataProcessTransactionDetails;
import dataProcess.FileProcess;
import dataProcess.FindSuspiciousAccounts;

public class SuspiciousActivity {

	// variables storing file path
	public static String filePathTransactionDetails=System.getProperty("user.dir")+"\\Files\\TransactionDetails.txt";
	public static String filePathAccountDetails=System.getProperty("user.dir")+"\\Files\\AccountDetails.txt";
	static FileProcess fileProcess=new FileProcess();
	static DataProcessTransactionDetails dataProcessTransactionDetails=new DataProcessTransactionDetails();
	static DataProcessAccountDetails dataProcessAccountDetails =new DataProcessAccountDetails();
	static FindSuspiciousAccounts findSuspiciousAccounts=new FindSuspiciousAccounts();
	
	public static void main(String[] args) {
		try {
			HashMap<String,List<TransactionDetails>> mapMonthData=dataProcessTransactionDetails.processDataTransactionDetails(fileProcess.readFile(filePathTransactionDetails));
			HashMap<Long,AccountDetails> mapAccountData=dataProcessAccountDetails.processDataAccountDetails(fileProcess.readFile(filePathAccountDetails));
			findSuspiciousAccounts.findSuspiciousAccounts(mapAccountData, mapMonthData);
			System.out.print(findSuspiciousAccounts.displaySuspiciousTransactionId());
			System.out.print(findSuspiciousAccounts.displaySuspiciousAccounts());
		} catch(Exception e) {
			System.out.println("main-->"+e);
		}
	}

}
