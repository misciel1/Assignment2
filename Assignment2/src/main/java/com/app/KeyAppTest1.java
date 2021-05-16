package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import java.util.List;

import com.dto.UserInfoDTO;
import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;




public class KeyAppTest1 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest1.class);
	
	public static void main(String[] args) throws Exception {
		
		


		KeyMgmtPC keyMgmtPC = new KeyMgmtPC();		
		String newkey = "";
		
		System.out.println("#######################################################");
		System.out.println("    #UNIT TEST1 - get a new key ");
		System.out.println("    #issue Quote nubmer (CT01) ");
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
			newkey = keyMgmtPC.issueNewKey("CT02",userInfoDTO);
			
			/*결과출력*/
			System.out.println("New key is : "+newkey);
		
		
	}

}
