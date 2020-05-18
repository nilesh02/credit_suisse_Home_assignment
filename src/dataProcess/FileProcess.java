package dataProcess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import dataDetails.TransactionDetails;

public class FileProcess {	
	public FileProcess() {}
	
	/*
	 * @author: Nilesh Rathi
	 * 
	 * @Description: This method is used to read file from disk and read the file in string format and return string to the user.
	 * @Parameters: FileName 'String' parameter is name of file user want to read from the file. 
	 * @Return Type: String (data read from file is return)
	 * 
	 */
	public String readFile(String strFileName){
		try {
			// Open FileInputStream object
			File file=new File(strFileName);
			FileInputStream fileInputStream = new FileInputStream(strFileName); 
			
			// reading file in bytes[] format
	        byte[] byteValues=new byte[(int) file.length()];
	        fileInputStream.read(byteValues);
	        fileInputStream.close();
	        
	        // converting bytes[] into String
	        String strFileContent= new String(byteValues);
	        return strFileContent;
		} catch(Exception e) {
			System.out.println("readFile-->"+e);
		}
		return "";
	}
}
