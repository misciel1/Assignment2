package com.ec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ec.KeyGenricGenEC;
import com.dao.KeyMstDAO;
import com.dao.KeyhistInfoDAO;
import com.dao.StringkeypoolinfoDAO;
import com.dto.KeyMstTDTO;
import com.dto.KeyhistinfoTDTO;
import com.dto.StringkeypoolinfoTDTO;
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
			long lstKeySeq = 0;
			String lstKeyNum = "";
			
			
			
			
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
			    	 lstKeySeq = keyMstPTDTO.get(0).getLstKeySeq();
			    	 
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
				 newKey = keyGenricGenEC.makeGenricKey(keyBizCfcd,genCfcd,keyPrifix,keyLen,lstKeySeq);
			 }	
			 else if("03".equals(type))//������+GnericKeyGenerator
			 {				 
				 newKey = keyGenricGenEC.makeGenricKey(keyBizCfcd,genCfcd,keyPrifix,keyLen,lstKeySeq);
				
			 }
			 
			 			 
				
		
			 return newKey;	
	}
	
	
	public void updateLstKeynum(String lstKeyNum,String keyBizCfcd){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
		
		mapper.updateLstKeyInfo(lstKeyNum,keyBizCfcd);
		session.commit();
	
		
	}
	
	public  void saveNewKeyHist(KeyhistinfoTDTO keyhistinfoTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyhistInfoDAO mapper = session.getMapper(KeyhistInfoDAO.class);
		
		mapper.saveNewKeyHist (keyhistinfoTDTO);
		session.commit();
		session.close();
	
		
	}

	
	public  void saveNewStringKey(StringkeypoolinfoTDTO stringkeypoolinfoTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		StringkeypoolinfoDAO mapper = session.getMapper(StringkeypoolinfoDAO.class);
		
		mapper.saveNewStringKey (stringkeypoolinfoTDTO);
		session.commit();
		session.close();
	
		
	}
	
}
