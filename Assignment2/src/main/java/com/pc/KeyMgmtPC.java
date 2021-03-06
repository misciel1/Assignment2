package com.pc;

import com.session.KeySessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ec.KeyFactoryEC;
import com.app.KeyAppTest1;
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
	
	final static Logger loggoer = Logger.getLogger(KeyMgmtPC.class);
	
	private MsgMgmtUC msgMgmtUC = null;	
	
	public ResultDTO issueNewKey(String keyBizCfcd,UserInfoDTO userInfoDTO) throws Exception{
	
		
		KeyFactoryEC keyFactoryEC = new KeyFactoryEC();		
		ResultDTO resultDTO = new ResultDTO();				
	
		/*NEW KEY발급*/
		KeyMstTDTO keyMstTDTO = new KeyMstTDTO();
		keyMstTDTO = keyFactoryEC.getNewKey(keyBizCfcd);
		
		String newKey = keyMstTDTO.getLstKeyNum();
		String chtms = keyMstTDTO.getChtms();
		
		
		if(newKey==null)
	    {
	    	
	    	throw new KeyExceptionUC("[ERROR] 해당 업무코드("+keyBizCfcd+")의 KEY정보가 없습니다. 다시 확인하시기 바랍니다.");
	    	
	    }
	
		/*최종키 업데이트*/
		keyFactoryEC.updateLstKeynum(newKey,keyBizCfcd);
	
		/*최종키 히스토리추가*/				
		KeyhistinfoTDTO keyhistinfoTDTO = new  KeyhistinfoTDTO();	
		keyhistinfoTDTO.setKeyBizCfcd(keyBizCfcd);
		keyhistinfoTDTO.setChtms(chtms);
		keyhistinfoTDTO.setKeyNum(newKey);
		keyhistinfoTDTO.setKeySeq(keyMstTDTO.getLstKeySeq());
		keyhistinfoTDTO.setSystemName(userInfoDTO.getSystemName());
		keyhistinfoTDTO.setSystemUsedIP(userInfoDTO.getSystemUsedIP());
		
		keyFactoryEC.saveNewKeyHist(keyhistinfoTDTO);
		
		
		/*OutDTO세팅*/
		resultDTO.setKeyNum(newKey);
		resultDTO.setResponse(msgMgmtUC.OK.getDescription());
		
		
	return resultDTO;	
	}
	
	
	
	
	public ResultDTO  rigsterNewKeyInfo(KeyMstTDTO keyMstTDTO) throws Exception{
		
		/*NEW KEY등록*/
				
	    KeyFactoryEC keyFactoryEC = new KeyFactoryEC();			
	    KeyMstTDTO outKeyMstTDTO = new KeyMstTDTO(); 
	    ResultDTO resultDTO = new ResultDTO();
	    
	    /*정합성체크*/
	    String keyBizCfcd = keyMstTDTO.getKeyBizCfcd();
	    List<KeyMstTDTO> keyMstPTDTO= keyFactoryEC.getKeyMstInfo(keyBizCfcd);
	    
	    if(keyMstPTDTO.size()>0)
	    {
	    	
	    	throw new KeyExceptionUC("[ERROR] 해당 업무코드("+keyBizCfcd+")는 이미 등록되었습니다. 다시 확인하시기 바랍니다.");
	    	
	    }	
	    else
	    {		    
	        /*NEW KEY등록*/
	        keyFactoryEC.rigsterNewKeyInfo(keyMstTDTO);
	    }
		
	    
	    
	    /*OutDTO세팅*/
	    resultDTO.setResponse(msgMgmtUC.INSERTED.getDescription());
	    
	    return resultDTO;
	
	}
	
	public ResultDTO  makeStringKey(long uptoCnt,String keyBizCfcd) throws Exception{
		
		/*KEY정보 정합성체크*/
		KeyFactoryEC keyFactoryEC = new KeyFactoryEC();	
		
		KeyMstTDTO keyMstTDTO = new KeyMstTDTO();
		List<KeyMstTDTO> keyMstPTDTO = keyFactoryEC.getKeyMstInfo(keyBizCfcd);
			
		
		if(keyMstPTDTO.size()==0)
	    {
	    	
	    	throw new KeyExceptionUC("[ERROR] 해당 업무코드("+keyBizCfcd+")의 KEY정보가 없습니다. 다시 확인하시기 바랍니다.");
	    	
	    }
		
		
		/*문자형 KEY생성*/		
		List<StringkeypoolinfoTDTO> stringkeypoolinfoPTDTO = new ArrayList<StringkeypoolinfoTDTO>();
	    StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO(); 
	    ResultDTO resultDTO = new ResultDTO();
	    
		KeyNumHandlerUC keyNumHandlerUC = new KeyNumHandlerUC();
		
		stringkeypoolinfoPTDTO = keyNumHandlerUC.generateKey(uptoCnt,keyBizCfcd);
		keyFactoryEC.saveNewStringKey(stringkeypoolinfoPTDTO);
		
		
		
		/*OutDTO세팅*/
		resultDTO.setResponse(msgMgmtUC.INSERTED.getDescription());
	    
	    return resultDTO;
	}
	
	

}
