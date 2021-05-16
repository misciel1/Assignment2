package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import java.util.List;


import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;



public class KeyAppTest1 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest1.class);
	
	public static void main(String[] args) {
		
		


		KeyMgmtPC keyMgmtPC = new KeyMgmtPC();
		String newkey = "";
				
		newkey = keyMgmtPC.issueNewKey("CT01");	
		
		 System.out.println("###########################################");
		 System.out.println("            청약번호 CT01 키발급");
		 System.out.println("###########################################");
		
		
        
		  System.out.println("신규키 : "+newkey);
		
	}

}
