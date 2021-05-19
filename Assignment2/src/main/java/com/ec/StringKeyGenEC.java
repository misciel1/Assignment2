package com.ec;

import org.apache.ibatis.session.SqlSession;

import com.dao.StringkeypoolinfoDAO;
import com.session.KeySessionFactory;
import com.dto.KeyMstTDTO;
import com.dto.StringkeypoolinfoTDTO;

public class StringKeyGenEC {
	
		public StringkeypoolinfoTDTO makeGenricKey(KeyMstTDTO keyMstTDTO){
			
			KeySessionFactory fac = new KeySessionFactory();
			SqlSession session = fac.openSession(false);	
			StringkeypoolinfoDAO mapper = session.getMapper(StringkeypoolinfoDAO.class);
			
			String keyBizCfcd =  keyMstTDTO.getKeyBizCfcd();
			long lstKeySeq = keyMstTDTO.getLstKeySeq();
			String keyPrifix = keyMstTDTO.getKeyPrifix();
			
			
			
			
			StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO();
			
			
			
			//StringKey Pool정보에서 조회
			stringkeypoolinfoTDTO = mapper.getNewStringKey(stringkeypoolinfoTDTO);	
			
			
			//Prifix+문자키(16자리)
			String newKey = keyPrifix+"-" + stringkeypoolinfoTDTO.getKeyNum();
			
			stringkeypoolinfoTDTO.setKeySeq(lstKeySeq);
			stringkeypoolinfoTDTO.setKeyBizCfcd(keyBizCfcd);
			stringkeypoolinfoTDTO.setKeyNum(newKey);
			
			
			return stringkeypoolinfoTDTO;
		}
}
