package com.pc;

import com.session.KeySessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ec.KeyFactoryEC;
import com.dao.KeyMstDAO;
import com.dto.KeyMstTDTO;
import com.dto.KeyhistinfoTDTO;
import com.dto.StringkeypoolinfoTDTO;
import com.dto.UserInfoDTO;
import com.uc.KeyNumHandlerUC;

import java.util.ArrayList;
import java.util.List;



public class KeyMgmtPC {
	
	public String issueNewKey(String keyBizCfcd,UserInfoDTO userInfoDTO) throws Exception{
	
		KeyFactoryEC keyFactoryEC = new KeyFactoryEC();		
			
			

		String newKey = "";
	
		/*NEW KEY�߱�*/
		newKey = keyFactoryEC.getNewKey(keyBizCfcd);
	
		/*����Ű ������Ʈ*/
		keyFactoryEC.updateLstKeynum(newKey,keyBizCfcd);
	
		/*����Ű �����丮�߰�*/		

		KeyhistinfoTDTO keyhistinfoTDTO = new  KeyhistinfoTDTO();	
		keyhistinfoTDTO.setKeyBizCfcd(keyBizCfcd);
		keyhistinfoTDTO.setKeyNum(newKey);
		keyhistinfoTDTO.setSystemName(userInfoDTO.getSystemName());
		keyhistinfoTDTO.setSystemUsedIP(userInfoDTO.getSystemUsedIP());
		
		keyFactoryEC.saveNewKeyHist(keyhistinfoTDTO);
		
	return newKey;	
	}
	
	
	public void  makeStringKey(long uptoCnt) throws Exception{
		
		List<StringkeypoolinfoTDTO> stringkeypoolinfoPTDTO = new ArrayList<StringkeypoolinfoTDTO>();
	    StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO(); 
		
	    KeyFactoryEC keyFactoryEC = new KeyFactoryEC();	
		KeyNumHandlerUC keyNumHandlerUC = new KeyNumHandlerUC();
		
		stringkeypoolinfoPTDTO = keyNumHandlerUC.generateKey(uptoCnt);
		keyFactoryEC.saveNewStringKey(stringkeypoolinfoPTDTO);
		
	
	}

}
