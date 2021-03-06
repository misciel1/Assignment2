package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import java.util.List;

import com.dto.KeyMstTDTO;
import com.dto.ResultDTO;
import com.dto.UserInfoDTO;
import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;




public class KeyAppTest9 {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest9.class);
	
	public static void main(String[] args) throws Exception {
		
		KeyMgmtPC keyMgmtPC = new KeyMgmtPC();
		KeyMstTDTO keyMstTDTO = new KeyMstTDTO();
		ResultDTO resultDTO = new ResultDTO();
	
		
		System.out.println("#######################################################");
		System.out.println("    #UNIT TEST9 - rigster a New Key info ");
		System.out.println("    # Reinsurance Treaty - Qoata Share");
		System.out.println("#######################################################");				
		
		
		keyMstTDTO.setKeyBizCfcd("RE05");
		keyMstTDTO.setChtms("000");
		keyMstTDTO.setApplBgdt("1900-01-01");
		keyMstTDTO.setApplEnddt("2999-12-31");
		keyMstTDTO.setKeyDesc("Reinsurance Treaty - Qoata Share");
		keyMstTDTO.setKeyPrifix("QS");
		keyMstTDTO.setLstKeyNum("");
		keyMstTDTO.setKeyLen(10);
		keyMstTDTO.setType("03");
		keyMstTDTO.setGenCfcd("01");
		keyMstTDTO.setLstKeySeq(0);
		
		
		/*½Å±Ô KEY-INFOµî·Ï*/
		resultDTO  = keyMgmtPC.rigsterNewKeyInfo(keyMstTDTO);
		
		/*°á°úÃâ·Â*/
		System.out.println("Response : "+resultDTO.getResponse());
	
	
	}

}
