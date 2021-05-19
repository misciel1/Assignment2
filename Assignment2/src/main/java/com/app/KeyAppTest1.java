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




public class KeyAppTest1 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest1.class);
	
	public static void main(String[] args) throws Exception {
		
		


			KeyMgmtPC keyMgmtPC = new KeyMgmtPC();		
			ResultDTO resultDTO = new ResultDTO();
			String newkey = "";
			
			System.out.println("#######################################################");
			System.out.println("    #UNIT TEST1 - get a new key ");
			System.out.println("    #issue Quote nubmer (CT01) -Generic");
			System.out.println("#######################################################");				
		
		
			/*�������������*/
		    InetAddress address = InetAddress.getLocalHost();    
		    String callerNm ="";
		    callerNm = Thread.currentThread().getStackTrace()[1].getClassName();
		    callerNm = callerNm+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
			
			UserInfoDTO userInfoDTO = new UserInfoDTO();			
			userInfoDTO.setSystemName(callerNm);
			userInfoDTO.setSystemUsedIP(address.getHostAddress());		
			
			/*KEY�߱�*/
			resultDTO = keyMgmtPC.issueNewKey("CT01",userInfoDTO);
			
			/*������*/
			System.out.println("New key is : "+resultDTO.getKeyNum());
			System.out.println("Response : "+resultDTO.getResponse());
		
		
	}

}
