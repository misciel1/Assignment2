package com.ec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ec.KeyGenricGenEC;
import com.dao.KeyMstDAO;
import com.dao.KeyhistInfoDAO;
import com.dao.StringkeypoolinfoDAO;
import com.dto.KeyMstTDTO;
import com.dto.KeyhistinfoTDTO;
import com.dto.StringkeypoolinfoTDTO;
import com.session.KeySessionFactory;
import com.uc.KeyNumHandlerUC;

public class KeyFactoryEC {

	final static Logger loggoer = Logger.getLogger(KeyFactoryEC.class);	

	public String getNewKey(String keyBizCfcd){
			
			
		
			
			KeySessionFactory fac = new KeySessionFactory();
			SqlSession session = fac.openSession(false);	
			KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
			
			KeyGenricGenEC keyGenricGenEC = new KeyGenricGenEC();
			StringKeyGenEC StringKeyGenEC = new StringKeyGenEC();
			
			String newKey = "";
			String type = "";
			String genCfcd = "";
			String keyPrifix = "";
			String lstKeyNum = "";
			int keyLen = 0;
			long lstKeySeq = 0;
			
			
			
			
			
			/*Key발급정보 조회*/
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
			 
			 			 
			 //KEY발급방식
			 if("01".equals(type)) //문자형 
			 {
				 newKey = StringKeyGenEC.makeGenricKey(keyBizCfcd,keyPrifix, lstKeySeq);
			 }	 
			 else if("02".equals(type))//숫자형+MysqlKeyGenerator
			 {
				 newKey = keyGenricGenEC.makeGenricKey(keyBizCfcd,genCfcd,keyPrifix,keyLen,lstKeySeq);
			 }	
			 else if("03".equals(type))//숫자형+GnericKeyGenerator
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
	
	public  void rigsterNewKeyInfo(KeyMstTDTO keyMstTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
		
		mapper.rigsterNewKeyInfo (keyMstTDTO);
		session.commit();
		session.close();
	
		
	}
		
	public  void saveNewKeyHist(KeyhistinfoTDTO keyhistinfoTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyhistInfoDAO mapper = session.getMapper(KeyhistInfoDAO.class);
		
		mapper.saveNewKeyHist (keyhistinfoTDTO);
		session.commit();
		session.close();
	
		
	}

	
	public  void saveNewStringKey(List<StringkeypoolinfoTDTO> stringkeypoolinfoPTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		StringkeypoolinfoDAO mapper = session.getMapper(StringkeypoolinfoDAO.class);
		
		/*초기화*/
		mapper.clearNewStringKey();
	
		int cnt = stringkeypoolinfoPTDTO.size();
		
		/*저장*/
		for(int i = 0;cnt>i;i++)
		{	
			StringkeypoolinfoTDTO stringkeypoolinfoTDTO = new StringkeypoolinfoTDTO();
			stringkeypoolinfoTDTO = stringkeypoolinfoPTDTO.get(i);
			
						
			mapper.saveNewStringKey (stringkeypoolinfoTDTO);						
			
			if(i%5000==0)
			{
				if(loggoer.isInfoEnabled()) loggoer.info("StrKeyNum : "+stringkeypoolinfoTDTO.getStrKeyNum()+"   cnt : "+i);
				session.commit();
			}	
		}
		session.commit();
		session.close();
	}
	
}
