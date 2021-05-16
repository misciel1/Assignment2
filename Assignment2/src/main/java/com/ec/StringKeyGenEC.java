package com.ec;

import org.apache.ibatis.session.SqlSession;

import com.dao.StringkeypoolinfoDAO;
import com.session.KeySessionFactory;
import com.dto.StringkeypoolinfoTDTO;

public class StringKeyGenEC {
	
		public String makeGenricKey(String keyBizCfcd,String keyPrifix,long lstKeySeq){
			
			KeySessionFactory fac = new KeySessionFactory();
			SqlSession session = fac.openSession(false);	
			StringkeypoolinfoDAO mapper = session.getMapper(StringkeypoolinfoDAO.class);
			
			StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO();
			stringkeypoolinfoTDTO = mapper.getNewStringKey(lstKeySeq);
			
			//Prifix+문자키(16자리)
			String newKey = keyPrifix+"-" + stringkeypoolinfoTDTO.getStrKeyNum();
			
			return newKey;
		}
}
