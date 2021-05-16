package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import java.util.List;

import com.dto.UserInfoDTO;
import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;




public class KeyAppTest3 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest3.class);
	
	public static void main(String[] args) throws Exception {
		
		


		KeyMgmtPC keyMgmtPC = new KeyMgmtPC();		
		String newkey = "";
		
		System.out.println("#######################################################");
		System.out.println("    #UNIT TEST3 - make Stringkey set ");
		System.out.println("     ");
		System.out.println("#######################################################");				
		
		
			
			/*문자키KEY생성*/
			keyMgmtPC.makeStringKey(500000);
			
			/*결과출력*/
			System.out.println("Completed");
		
		
	}

}
