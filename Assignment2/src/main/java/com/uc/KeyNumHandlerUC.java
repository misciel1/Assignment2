package com.uc;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.app.KeyAppTest3;
import com.dto.KeyMstTDTO;
import com.dto.StringkeypoolinfoTDTO;


public class KeyNumHandlerUC {
	
	final static Logger loggoer = Logger.getLogger(KeyNumHandlerUC.class);	
	
	public String makeNewKeyNum(int keyLen,long lstKeyNum){
		
		String postKeyNum = "";
		
		
		lstKeyNum = lstKeyNum+1;  //keyNum +1증가
		postKeyNum = String.valueOf(lstKeyNum);
		
		//Formatting   ex)"00000001"
		for(int i=0;keyLen>i;i++)
		{
			postKeyNum = "0"+postKeyNum;
			
			if(postKeyNum.length()==keyLen) 
			{
				break;// keyNum의 숫자를 정의된 자리수와 일치시킴 
			}	
		}	
		
		
		
		return postKeyNum;	
	}
	

	
	public List<StringkeypoolinfoTDTO>  generateKey(long uptoCnt,String keyBizCfcd) throws Exception{

		// 영문 + 숫자를 조합한 키 생성
		// 16byte 의 랜럼 수치를 저장
	    String key = "";
	    int cnt = 0;
	    List<StringkeypoolinfoTDTO> stringkeypoolinfoPTDTO = new ArrayList<StringkeypoolinfoTDTO>();
	    
	    HashMap<String, String> uuidMap = new HashMap();
	    
	    while(true) {
	        
	    	
	    	key = "";
	    	// 랜덤 고유키 생성 
	    	UUID uuid = UUID.randomUUID(); 
	    	key = UUID.randomUUID().toString().replace("-", "");	    	
	    		    		
	    	
	    	//Key포멧팅
	    	key = key.toUpperCase();
	    	
	    	if("".equals(keyBizCfcd))
	    	{
	    		//업무구분코드에 따라 코드 포맷과 자리수 변경가능
	    	}
	    	else
	    	{	
	    		key = key.substring(0, 4)+"-"+key.substring(4, 8)+"-"+key.substring(8, 12)+"-"+key.substring(12, 16);
	    	}	
	    	
	    	
	    	
	    	//중복체크를 위해
	    	if(uuidMap.containsKey(key))
	    	{
	    		//if(loggoer.isInfoEnabled()) loggoer.info("Dup key : "+key+" cnt : "+cnt);
	    		break;	    		
	    	}	
	    	else
	    	{
	    		StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO(); 
	    		stringkeypoolinfoTDTO.setKeyNum(key);
	    		stringkeypoolinfoTDTO.setKeyBizCfcd(keyBizCfcd);
	    		stringkeypoolinfoPTDTO.add(stringkeypoolinfoTDTO);
	    		
	    		uuidMap.put(key, key);
	    		cnt++;
	    		
	    		
	    		
	    	}	
	    	
	    	
	    	if(cnt==uptoCnt)
	    	{
	    		break;
	    	}	
	        
	        
	    }
	   
	    return stringkeypoolinfoPTDTO;
	}
}
