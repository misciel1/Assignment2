package com.ec;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.app.KeyAppTest2;
import com.uc.KeyNumHandlerUC;

public class KeyGenricGenEC {
	
	final static Logger loggoer = Logger.getLogger(KeyGenricGenEC.class);
	
	public String makeGenricKey(String keyBizCfcd,String genCfcd,String keyPrifix,int keyLen,long lstKeySeq){
	
		KeyNumHandlerUC keyNumHandlerUC = new KeyNumHandlerUC();
		
		String newKey = "";
		String postKeyNum = "";
		String prifixKeyNum = "";
		
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		
		
		if("01".equals(genCfcd))  //Prifix+년도(두자리)+KeyNum
		{
			
			 prifixKeyNum = keyPrifix+String.valueOf(thisYear).substring(2, 4);			 
			 postKeyNum = keyNumHandlerUC.makeNewKeyNum(keyLen,lstKeySeq);
			 
			 newKey = prifixKeyNum + postKeyNum;
			 
			 
		}
		else if("02".equals(genCfcd))  //Prifix+년도(YYYY)+KeyNum
		{
			
			 prifixKeyNum = keyPrifix+String.valueOf(thisYear);			 
			 postKeyNum = keyNumHandlerUC.makeNewKeyNum(keyLen,lstKeySeq);
			 
			 newKey = prifixKeyNum + postKeyNum;
			 
			 
		}
		else
		{
			 prifixKeyNum = keyPrifix;			 
			 postKeyNum = keyNumHandlerUC.makeNewKeyNum(keyLen,lstKeySeq);
			 
			 newKey = prifixKeyNum + postKeyNum;
			 
			 //if(loggoer.isInfoEnabled()) loggoer.info("prifixKeyNum : "+prifixKeyNum);
			 //if(loggoer.isInfoEnabled()) loggoer.info("postKeyNum : "+postKeyNum);
		}	
	
		
		
		return newKey;	
	}
}
