package com.ec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ec.KeyGenricGenEC;
import com.dao.KeyMstDAO;
import com.dto.KeyMstTDTO;
import com.session.KeySessionFactory;

public class KeyFactoryEC {

	

	public String getNewKey(String keyBizCfcd){
			
			
		
			KeyGenricGenEC keyGenricGenEC = new KeyGenricGenEC();
			KeySessionFactory fac = new KeySessionFactory();
			SqlSession session = fac.openSession(false);	
			KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
			
			String newKey = "";
			String type = "";
			String genCfcd = "";
			String keyPrifix = "";
			int keyLen = 0;
			long lstKeyNum = 0;
			
			
			
			
			/*Key�߱����� ��ȸ*/
			List<KeyMstTDTO> keyMstPTDTO = mapper.getKeyMstInfo(keyBizCfcd);
			session.close();
			 
			int cnt = keyMstPTDTO.size();
		     
				 
			 if(cnt>0) 
			 {
				 for(int i=0; i<cnt; i++) 
			     {
					 type = keyMstPTDTO.get(0).getType();
					 genCfcd = keyMstPTDTO.get(0).getGenCfcd();
					 keyLen =  keyMstPTDTO.get(0).getKeyLen();
			    	 keyPrifix = keyMstPTDTO.get(0).getKeyPrifix();
			    	 lstKeyNum = keyMstPTDTO.get(0).getLstKeyNum();
			    	 
				 }
			 } 
			 else
			 {
				 //THROW EXCEPTION
			 }
			 
			 			 
			 //KEY�߱޹��
			 if("01".equals(type)) //������ 
			 {
				 
			 }	 
			 else if("02".equals(type))//������+MysqlKeyGenerator
			 {
				 
			 }	
			 else if("03".equals(type))//������+GnericKeyGenerator
			 {				 
				 newKey = keyGenricGenEC.makeGenricKey(keyBizCfcd,genCfcd,keyPrifix,keyLen,lstKeyNum);
				
			 }
			 
			 
			 
				
		
			 return newKey;	
	}
	
	
	public void updateLstKeynum(String keyBizCfcd){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
		
		mapper.updateLstKeyNum(keyBizCfcd);
		session.commit();
		session.close();
		
	}

	
}
