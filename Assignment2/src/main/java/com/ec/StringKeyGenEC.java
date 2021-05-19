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
			
			
			//StringKey Pool�������� ��ȸ
			outStringkeypoolinfoTDTO = mapper.getNewStringKey(inStringkeypoolinfoTDTO);	
			
			
			//Prifix+����Ű(16�ڸ�)
			String newKey = keyPrifix+"-" + outStringkeypoolinfoTDTO.getKeyNum();
			

			outStringkeypoolinfoTDTO.setKeyNum(newKey);
			
			
			return outStringkeypoolinfoTDTO;
		}
}
