package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import java.util.List;

import com.dto.ResultDTO;
import com.dto.UserInfoDTO;
import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;




public class KeyAppTest8 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest8.class);
	
	public static void main(String[] args) throws Exception {
		
		


		KeyMgmtPC keyMgmtPC = new KeyMgmtPC();	
		ResultDTO resultDTO = new ResultDTO();
		
		System.out.println("#######################################################");
		System.out.println("    #UNIT TEST8 - make Stringkey set ");
		System.out.println("     ");
		System.out.println("#######################################################");				
		
		
			
			/*문자키KEY생성*/
		    resultDTO = keyMgmtPC.makeStringKey(3000000);
			
			/*결과출력*/
			System.out.println("Response : "+resultDTO.getResponse());
		
		
	}

}
