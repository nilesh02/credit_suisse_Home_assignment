package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import dataDetails.AccountDetails;
import dataDetails.TransactionDetails;
import dataProcess.DataProcessAccountDetails;
import dataProcess.DataProcessTransactionDetails;
import dataProcess.FileProcess;
import dataProcess.FindSuspiciousAccounts;

public class TestCases {

	@Test
	public void testReadFile() {
		String strExpectedOutput=
				"TransactionId|TransactionDate|FromAccount|ToAccount|Amount\r\n" + 
				"T0175896345|10-Jan-20|10010589|80074567|100000\r\n" + 
				"T0175896346|15-Jan-20|68748963|30045721|500\r\n" + 
				"T0175896347|21-Jan-20|78524169|30045721|6500\r\n" + 
				"T0175896348|26-Jan-20|80074567|68748963|8700\r\n" + 
				"T0175896349|31-Jan-20|30045721|78524169|4200\r\n" + 
				"T0175896350|4-Feb-20|80074567|10010589|8950\r\n" + 
				"T0175896351|11-Feb-20|78524169|68748963|20000\r\n" + 
				"T0175896352|15-Feb-20|68748963|10010589|50000\r\n" + 
				"T0175896353|18-Feb-20|30045721|78524169|28000\r\n" + 
				"T0175896354|23-Feb-20|10010589|78524169|34000\r\n" + 
				"T0175896355|3-Mar-20|68748963|10010589|75000\r\n" + 
				"T0175896356|9-Mar-20|78524169|30045721|64000\r\n" + 
				"T0175896357|19-Mar-20|78524169|10010589|23000\r\n" + 
				"T0175896358|25-Mar-20|10010589|80074567|89000";
		
		FileProcess fileProcess=new FileProcess();
		assertEquals(strExpectedOutput,fileProcess.readFile(System.getProperty("user.dir")+"\\Files\\TransactionDetails.txt"));
	}
	
	@Test
	public void testHeaderdataIndexingTransactionDetails() {

		// format
		String strHeaderData="TransactionId|TransactionDate|FromAccount|ToAccount|Amount";
		DataProcessTransactionDetails dataProcessTransactionDetails=new DataProcessTransactionDetails();
		dataProcessTransactionDetails.setHeadersForTransactionDetails(strHeaderData);
		assertEquals(0,dataProcessTransactionDetails.getTransactionDetails_TransactionId());
		assertEquals(1,dataProcessTransactionDetails.getTransactionDetails_TransactionDate());
		assertEquals(2,dataProcessTransactionDetails.getTransactionDetails_FromAccount());
		assertEquals(3,dataProcessTransactionDetails.getTransactionDetails_ToAccount());
		assertEquals(4,dataProcessTransactionDetails.getTransactionDetails_Amount());
		
		// changed format mix of uppercase and lowercase
		String strHeaderData1="TransactionDate|fRoMaCcOuNt|ToAccount|Amount|TransactionId";
		DataProcessTransactionDetails dataProcessTransactionDetails1=new DataProcessTransactionDetails();
		dataProcessTransactionDetails1.setHeadersForTransactionDetails(strHeaderData1);
		assertEquals(4,dataProcessTransactionDetails1.getTransactionDetails_TransactionId());
		assertEquals(0,dataProcessTransactionDetails1.getTransactionDetails_TransactionDate());
		assertEquals(1,dataProcessTransactionDetails1.getTransactionDetails_FromAccount());
		assertEquals(2,dataProcessTransactionDetails1.getTransactionDetails_ToAccount());
		assertEquals(3,dataProcessTransactionDetails1.getTransactionDetails_Amount());
		
		// default format
		String strHeaderData2="";
		DataProcessTransactionDetails dataProcessTransactionDetails2=new DataProcessTransactionDetails();
		dataProcessTransactionDetails2.setHeadersForTransactionDetails(strHeaderData2);
		assertEquals(0,dataProcessTransactionDetails2.getTransactionDetails_TransactionId());
		assertEquals(1,dataProcessTransactionDetails2.getTransactionDetails_TransactionDate());
		assertEquals(2,dataProcessTransactionDetails2.getTransactionDetails_FromAccount());
		assertEquals(3,dataProcessTransactionDetails2.getTransactionDetails_ToAccount());
		assertEquals(4,dataProcessTransactionDetails2.getTransactionDetails_Amount());
	}
	
	@Test
	public void testHeaderdataIndexingAccountDetails() {
		// format
		String strHeaderData="Account|Name|Address|PhoneNumber";
		DataProcessAccountDetails dataProcessAccountDetails=new DataProcessAccountDetails();
		dataProcessAccountDetails.setHeadersForAccountDetails(strHeaderData);
		assertEquals(0,dataProcessAccountDetails.getAccountDetails_Account());
		assertEquals(1,dataProcessAccountDetails.getAccountDetails_Name());
		assertEquals(2,dataProcessAccountDetails.getAccountDetails_Address());
		assertEquals(3,dataProcessAccountDetails.getAccountDetails_PhoneNumber());
		
		// changed format mix of uppercase and lowercase
		String strHeaderData1="Name|ADdreSS|PhoneNumber|Account";
		DataProcessAccountDetails dataProcessAccountDetails1=new DataProcessAccountDetails();
		dataProcessAccountDetails1.setHeadersForAccountDetails(strHeaderData1);
		assertEquals(3,dataProcessAccountDetails1.getAccountDetails_Account());
		assertEquals(0,dataProcessAccountDetails1.getAccountDetails_Name());
		assertEquals(1,dataProcessAccountDetails1.getAccountDetails_Address());
		assertEquals(2,dataProcessAccountDetails1.getAccountDetails_PhoneNumber());
		
		// default format
		String strHeaderData2="";
		DataProcessAccountDetails dataProcessAccountDetails2=new DataProcessAccountDetails();
		dataProcessAccountDetails2.setHeadersForAccountDetails(strHeaderData2);
		assertEquals(0,dataProcessAccountDetails2.getAccountDetails_Account());
		assertEquals(1,dataProcessAccountDetails2.getAccountDetails_Name());
		assertEquals(2,dataProcessAccountDetails2.getAccountDetails_Address());
		assertEquals(3,dataProcessAccountDetails2.getAccountDetails_PhoneNumber());
	}
	
	@Test
	public void testHandleWhiteSpaces() {
		String strFileName=System.getProperty("user.dir")+"\\Files\\AccountDetailsWhiteSpaceTestCase.txt";
		String strExpected=
				"{80074567=AccountDetails [longAccount=80074567, strName=Mr. O Vilas, strAddress=Flat no. 501, Green View Park,Hinjewandi, Pune, longPhoneNumber=9001045238], 30045721=AccountDetails [longAccount=30045721, strName=Mr. A Gupta, strAddress=Flat no. 103, Ganaga nagari, Kharadi, Pune, longPhoneNumber=7521457896], 10010589=AccountDetails [longAccount=10010589, strName=Mr. S Kumar, strAddress=Flat no. 501, Green View Park,Hinjewandi, Pune, longPhoneNumber=9001045238], 78524169=AccountDetails [longAccount=78524169, strName=Mrs. N Akira, strAddress=Flat no. 103, Ganaga nagari, Kharadi, Pune, longPhoneNumber=7521457896], 68748963=AccountDetails [longAccount=68748963, strName=Miss K Rao, strAddress=Flat no. 703, Platinum Plazza, Dhanori, Pune, longPhoneNumber=8201475689]}";
		FileProcess fileProcess=new FileProcess();
		DataProcessAccountDetails dataProcessAccountDetails=new DataProcessAccountDetails();
		HashMap<Long,AccountDetails> mapAccountData=dataProcessAccountDetails.processDataAccountDetails(fileProcess.readFile(strFileName));
		assertEquals(strExpected, mapAccountData.toString());
	}
	
	@Test
	public void testProcessDataTransactionDetails() throws ParseException {
		String filePathTransactionDetails=System.getProperty("user.dir")+"\\Files\\TransactionDetails.txt";
		String strExpected=
				"{01=[TransactionDetails [strTransactionId=T0175896345, strTransactionDate=Fri Jan 10 00:00:00 IST 2020, longFromAccount=10010589, longToAccount=80074567, longAmount=100000.0], TransactionDetails [strTransactionId=T0175896346, strTransactionDate=Wed Jan 15 00:00:00 IST 2020, longFromAccount=68748963, longToAccount=30045721, longAmount=500.0], TransactionDetails [strTransactionId=T0175896347, strTransactionDate=Tue Jan 21 00:00:00 IST 2020, longFromAccount=78524169, longToAccount=30045721, longAmount=6500.0], TransactionDetails [strTransactionId=T0175896348, strTransactionDate=Sun Jan 26 00:00:00 IST 2020, longFromAccount=80074567, longToAccount=68748963, longAmount=8700.0], TransactionDetails [strTransactionId=T0175896349, strTransactionDate=Fri Jan 31 00:00:00 IST 2020, longFromAccount=30045721, longToAccount=78524169, longAmount=4200.0]], 02=[TransactionDetails [strTransactionId=T0175896350, strTransactionDate=Tue Feb 04 00:00:00 IST 2020, longFromAccount=80074567, longToAccount=10010589, longAmount=8950.0], TransactionDetails [strTransactionId=T0175896351, strTransactionDate=Tue Feb 11 00:00:00 IST 2020, longFromAccount=78524169, longToAccount=68748963, longAmount=20000.0], TransactionDetails [strTransactionId=T0175896352, strTransactionDate=Sat Feb 15 00:00:00 IST 2020, longFromAccount=68748963, longToAccount=10010589, longAmount=50000.0], TransactionDetails [strTransactionId=T0175896353, strTransactionDate=Tue Feb 18 00:00:00 IST 2020, longFromAccount=30045721, longToAccount=78524169, longAmount=28000.0], TransactionDetails [strTransactionId=T0175896354, strTransactionDate=Sun Feb 23 00:00:00 IST 2020, longFromAccount=10010589, longToAccount=78524169, longAmount=34000.0]], 03=[TransactionDetails [strTransactionId=T0175896355, strTransactionDate=Tue Mar 03 00:00:00 IST 2020, longFromAccount=68748963, longToAccount=10010589, longAmount=75000.0], TransactionDetails [strTransactionId=T0175896356, strTransactionDate=Mon Mar 09 00:00:00 IST 2020, longFromAccount=78524169, longToAccount=30045721, longAmount=64000.0], TransactionDetails [strTransactionId=T0175896357, strTransactionDate=Thu Mar 19 00:00:00 IST 2020, longFromAccount=78524169, longToAccount=10010589, longAmount=23000.0], TransactionDetails [strTransactionId=T0175896358, strTransactionDate=Wed Mar 25 00:00:00 IST 2020, longFromAccount=10010589, longToAccount=80074567, longAmount=89000.0]]}";
		FileProcess fileProcess=new FileProcess();
		DataProcessTransactionDetails dataProcessTransactionDetails=new DataProcessTransactionDetails();
		HashMap<String,List<TransactionDetails>> mapMonthData=dataProcessTransactionDetails.processDataTransactionDetails(fileProcess.readFile(filePathTransactionDetails));
		assertEquals(strExpected, mapMonthData.toString());
	}
	
	@Test
	public void testProcessDataAccountDetails() {
		String filePathAccountDetails=System.getProperty("user.dir")+"\\Files\\AccountDetails.txt";
		String strExpected=
				"{80074567=AccountDetails [longAccount=80074567, strName=Mr. O Vilas, strAddress=Flat no. 501, Green View Park,Hinjewandi, Pune, longPhoneNumber=9001045238], 30045721=AccountDetails [longAccount=30045721, strName=Mr. A Gupta, strAddress=Flat no. 103, Ganaga nagari, Kharadi, Pune, longPhoneNumber=7521457896], 10010589=AccountDetails [longAccount=10010589, strName=Mr. S Kumar, strAddress=Flat no. 501, Green View Park,Hinjewandi, Pune, longPhoneNumber=9001045238], 78524169=AccountDetails [longAccount=78524169, strName=Mrs. N Akira, strAddress=Flat no. 103, Ganaga nagari, Kharadi, Pune, longPhoneNumber=7521457896], 68748963=AccountDetails [longAccount=68748963, strName=Miss K Rao, strAddress=Flat no. 703, Platinum Plazza, Dhanori, Pune, longPhoneNumber=8201475689]}";
		FileProcess fileProcess=new FileProcess();
		DataProcessAccountDetails dataProcessAccountDetails=new DataProcessAccountDetails();
		HashMap<Long,AccountDetails> mapAccountData=dataProcessAccountDetails.processDataAccountDetails(fileProcess.readFile(filePathAccountDetails));
		assertEquals(strExpected, mapAccountData.toString());
	}
	
	@Test
	public void testDisplaySuspiciousTransactions() throws ParseException {
		
		String strExpected=
				"For The Month of Jan:\n" + 
				"Suspicious Transactions :\n" + 
				"T0175896345\n" + 
				"T0175896347\n" + 
				"T0175896349\n" + 
				"\n" + 
				"For The Month of Feb:\n" + 
				"Suspicious Transactions :\n" + 
				"T0175896350\n" + 
				"T0175896353\n" + 
				"\n" + 
				"For The Month of Mar:\n" + 
				"Suspicious Transactions :\n" + 
				"T0175896356\n" + 
				"T0175896358\n" + 
				"\n";
		
		String filePathTransactionDetails=System.getProperty("user.dir")+"\\Files\\TransactionDetails.txt";
		String filePathAccountDetails=System.getProperty("user.dir")+"\\Files\\AccountDetails.txt";
		FileProcess fileProcess=new FileProcess();
		DataProcessTransactionDetails dataProcessTransactionDetails=new DataProcessTransactionDetails();
		DataProcessAccountDetails dataProcessAccountDetails =new DataProcessAccountDetails();
		FindSuspiciousAccounts findSuspiciousAccounts=new FindSuspiciousAccounts();
		
		HashMap<String,List<TransactionDetails>> mapMonthData=dataProcessTransactionDetails.processDataTransactionDetails(fileProcess.readFile(filePathTransactionDetails));
		HashMap<Long,AccountDetails> mapAccountData=dataProcessAccountDetails.processDataAccountDetails(fileProcess.readFile(filePathAccountDetails));
		findSuspiciousAccounts.findSuspiciousAccounts(mapAccountData, mapMonthData);
		assertEquals(strExpected, findSuspiciousAccounts.displaySuspiciousTransactionId());
	}
	
	@Test
	public void testDisplaySuspiciousAccounts() throws ParseException {
		
		String strExpected=
				"Suspicious Accounts\n" + 
				"[80074567, 10010589]\n" + 
				"[30045721, 78524169]\n" + 
				"";
		
		String filePathTransactionDetails=System.getProperty("user.dir")+"\\Files\\TransactionDetails.txt";
		String filePathAccountDetails=System.getProperty("user.dir")+"\\Files\\AccountDetails.txt";
		FileProcess fileProcess=new FileProcess();
		DataProcessTransactionDetails dataProcessTransactionDetails=new DataProcessTransactionDetails();
		DataProcessAccountDetails dataProcessAccountDetails =new DataProcessAccountDetails();
		FindSuspiciousAccounts findSuspiciousAccounts=new FindSuspiciousAccounts();
		
		HashMap<String,List<TransactionDetails>> mapMonthData=dataProcessTransactionDetails.processDataTransactionDetails(fileProcess.readFile(filePathTransactionDetails));
		HashMap<Long,AccountDetails> mapAccountData=dataProcessAccountDetails.processDataAccountDetails(fileProcess.readFile(filePathAccountDetails));
		findSuspiciousAccounts.findSuspiciousAccounts(mapAccountData, mapMonthData);
		assertEquals(strExpected, findSuspiciousAccounts.displaySuspiciousAccounts());
	}
	
}
