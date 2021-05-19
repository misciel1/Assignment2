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
			
			
			
			
			StringkeypoolinfoTDTO inStringkeypoolinfoTDTO = new StringkeypoolinfoTDTO();
			StringkeypoolinfoTDTO outStringkeypoolinfoTDTO = new StringkeypoolinfoTDTO();
			inStringkeypoolinfoTDTO.setKeySeq(lstKeySeq);
			inStringkeypoolinfoTDTO.setKeyBizCfcd(keyBizCfcd);
			
			
			//StringKey Pool정보에서 조회
			outStringkeypoolinfoTDTO = mapper.getNewStringKey(inStringkeypoolinfoTDTO);	
			
			
			//Prifix+문자키(16자리)
			String newKey = keyPrifix+"-" + outStringkeypoolinfoTDTO.getKeyNum();
			

			outStringkeypoolinfoTDTO.setKeyNum(newKey);
			
			
			return outStringkeypoolinfoTDTO;
		}
}
