package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import java.util.List;

import com.dto.UserInfoDTO;
import com.dto.ResultDTO;
import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;




public class KeyAppTest2 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest2.class);
	
	public static void main(String[] args) throws Exception {
		
		


			KeyMgmtPC keyMgmtPC = new KeyMgmtPC();		
			ResultDTO resultDTO = new ResultDTO();
			String newkey = "";
			
			System.out.println("#######################################################");
			System.out.println("    #UNIT TEST2 - get a new key ");
			System.out.println("    #issue Policy nubmer (CT02) - Mysql ");
			System.out.println("#######################################################");				
		
		
			/*사용자정보셋팅*/
		    InetAddress address = InetAddress.getLocalHost();    
		    String callerNm ="";
		    callerNm = Thread.currentThread().getStackTrace()[1].getClassName();
		    callerNm = callerNm+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			
			UserInfoDTO userInfoDTO = new UserInfoDTO();			
			userInfoDTO.setSystemName(callerNm);
			userInfoDTO.setSystemUsedIP(address.getHostAddress());		
			
			/*KEY발급*/
			resultDTO = keyMgmtPC.issueNewKey("CT02",userInfoDTO);
			
			/*결과출력*/
			System.out.println("New key is : "+resultDTO.getKeyNum());
			System.out.println("Response : "+resultDTO.getResponse());
		
		
	}

}
