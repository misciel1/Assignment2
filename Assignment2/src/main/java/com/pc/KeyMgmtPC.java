package com.pc;

import com.session.KeySessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ec.KeyFactoryEC;
import com.dao.KeyMstDAO;
import com.dto.KeyMstTDTO;
import com.dto.KeyhistinfoTDTO;
import com.dto.StringkeypoolinfoTDTO;
import com.dto.ResultDTO;
import com.dto.UserInfoDTO;
import com.uc.KeyExceptionUC;
import com.uc.KeyNumHandlerUC;
import com.uc.MsgMgmtUC;

import java.util.ArrayList;
import java.util.List;



public class KeyMgmtPC {
	
	private MsgMgmtUC msgMgmtUC = null;
	
	
	public ResultDTO issueNewKey(String keyBizCfcd,UserInfoDTO userInfoDTO) throws Exception{
	
		
		KeyFactoryEC keyFactoryEC = new KeyFactoryEC();		
		ResultDTO resultDTO = new ResultDTO();		
		String newKey = "";
	
		/*NEW KEY�߱�*/
		newKey = keyFactoryEC.getNewKey(keyBizCfcd);
		
		if(newKey==null)
	    {
	    	
	    	throw new KeyExceptionUC("[ERROR] �ش� �����ڵ�("+keyBizCfcd+")�� KEY������ �����ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.");
	    	
	    }
	
		/*����Ű ������Ʈ*/
		keyFactoryEC.updateLstKeynum(newKey,keyBizCfcd);
	
		/*����Ű �����丮�߰�*/		

		KeyhistinfoTDTO keyhistinfoTDTO = new  KeyhistinfoTDTO();	
		keyhistinfoTDTO.setKeyBizCfcd(keyBizCfcd);
		keyhistinfoTDTO.setKeyNum(newKey);
		keyhistinfoTDTO.setSystemName(userInfoDTO.getSystemName());
		keyhistinfoTDTO.setSystemUsedIP(userInfoDTO.getSystemUsedIP());
		
		keyFactoryEC.saveNewKeyHist(keyhistinfoTDTO);
		
		
		/*OutDTO����*/
		resultDTO.setKeyNum(newKey);
		resultDTO.setResponse(msgMgmtUC.OK.getDescription());
		
		
	return resultDTO;	
	}
	
	
	
	
	public ResultDTO  rigsterNewKeyInfo(KeyMstTDTO keyMstTDTO) throws Exception{
		
		/*NEW KEY���*/
				
	    KeyFactoryEC keyFactoryEC = new KeyFactoryEC();			
	    KeyMstTDTO outKeyMstTDTO = new KeyMstTDTO(); 
	    ResultDTO resultDTO = new ResultDTO();
	    
	    /*���ռ�üũ*/
	    String keyBizCfcd = keyMstTDTO.getKeyBizCfcd();
	    List<KeyMstTDTO> keyMstPTDTO= keyFactoryEC.getKeyMstInfo(keyBizCfcd);
	    
	    if(keyMstPTDTO.size()>0)
	    {
	    	
	    	throw new KeyExceptionUC("[ERROR] �ش� �����ڵ�("+keyBizCfcd+")�� �̹� ��ϵǾ����ϴ�. �ٽ� Ȯ���Ͻñ� �ٶ��ϴ�.");
	    	
	    }	
	    else
	    {		    
	        /*NEW KEY���*/
	        keyFactoryEC.rigsterNewKeyInfo(keyMstTDTO);
	    }
		
	    
	    
	    /*OutDTO����*/
	    resultDTO.setResponse(msgMgmtUC.INSERTED.getDescription());
	    
	    return resultDTO;
	
	}
	
	public ResultDTO  makeStringKey(long uptoCnt) throws Exception{
		
		/*������ KEY����*/		
		List<StringkeypoolinfoTDTO> stringkeypoolinfoPTDTO = new ArrayList<StringkeypoolinfoTDTO>();
	    StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO(); 
	    ResultDTO resultDTO = new ResultDTO();
	    
	    KeyFactoryEC keyFactoryEC = new KeyFactoryEC();	
		KeyNumHandlerUC keyNumHandlerUC = new KeyNumHandlerUC();
		
		stringkeypoolinfoPTDTO = keyNumHandlerUC.generateKey(uptoCnt);
		keyFactoryEC.saveNewStringKey(stringkeypoolinfoPTDTO);
		
		
		
		/*OutDTO����*/
		resultDTO.setResponse(msgMgmtUC.INSERTED.getDescription());
	    
	    return resultDTO;
	}
	
	

}
