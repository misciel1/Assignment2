package com.pc;

import com.session.KeySessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ec.KeyFactoryEC;
import com.dao.KeyMstDAO;
import com.dto.KeyMstTDTO;

import java.util.List;



public class KeyMgmtPC {
	
	public String issueNewKey(String keyBizCfcd){
	
		KeyFactoryEC keyFactoryEC = new KeyFactoryEC();
		String newKey = "";
		
	
		/*NEW KEY발급*/
		newKey = keyFactoryEC.getNewKey(keyBizCfcd);
	
		/*최종키 업데이트*/
		keyFactoryEC.updateLstKeynum(keyBizCfcd);
	
	return newKey;	
	}

}
