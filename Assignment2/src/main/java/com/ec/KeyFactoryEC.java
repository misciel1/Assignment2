package com.ec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ec.NumericKeyGenEC;
import com.dao.KeyMstDAO;
import com.dao.KeyhistInfoDAO;
import com.dao.StringkeypoolinfoDAO;
import com.dto.KeyMstTDTO;
import com.dto.KeyhistinfoTDTO;
import com.dto.StringkeypoolinfoTDTO;
import com.session.KeySessionFactory;
import com.uc.KeyExceptionUC;
import com.uc.KeyNumHandlerUC;

public class KeyFactoryEC {

	final static Logger loggoer = Logger.getLogger(KeyFactoryEC.class);	

	/*
	 * Key발급정보 조회
	 * 
	 *
	 */	
	public String getNewKey(String keyBizCfcd) throws KeyExceptionUC{
		
			
	
		    KeySessionFactory fac = new KeySessionFactory();
			SqlSession session = fac.openSession(false);	
			KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
			
			NumericKeyGenEC numericKeyGenEC = new NumericKeyGenEC();
			StringKeyGenEC StringKeyGenEC = new StringKeyGenEC();
			
			String newKey = "";
			String type = "";
	
			
			
			
			/*Key발급정보 조회*/
			List<KeyMstTDTO> keyMstPTDTO = mapper.getKeyMstInfo(keyBizCfcd);
 
			 
			KeyMstTDTO keyMstTDTO = new KeyMstTDTO(); 
			int cnt = keyMstPTDTO.size();
		     
				 
			 if(cnt>0) 
			 {
				 	 type = keyMstPTDTO.get(0).getType();;			    	 
			    	 keyMstTDTO = keyMstPTDTO.get(0);

			 } 
			 else
			 {
				 throw new KeyExceptionUC("[ERROR] 등록된 Key정보가 없습니다. 먼저 KEY정보를 입력하세요.");
			 }
			 
			 			 
			 //KEY발급방식
			 if("01".equals(type)) //문자형 
			 {
				 newKey = StringKeyGenEC.makeGenricKey(keyMstTDTO);
			 }	 
			 else if("02".equals(type))//숫자형+MysqlKeyGenerator
			 {
				 newKey = numericKeyGenEC.makeNumericKey(keyMstTDTO);
			 }	
			 else if("03".equals(type))//숫자형+GnericKeyGenerator
			 {				 
				 newKey = numericKeyGenEC.makeNumericKey(keyMstTDTO);
				
			 }
			 
			 			 
				
		
			 return newKey;	
	}
	
	
	
	/*
	 * 최종키번호 업데이트
	 * 
	 *
	 */	
	
	public void updateLstKeynum(String lstKeyNum,String keyBizCfcd){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
			
		
		mapper.updateLstKeyInfo(lstKeyNum,keyBizCfcd);
		session.commit();
	
		
	}
	
	
	
	
	/*
	 * Key원부조회
	 * 
	 *
	 */	
	public List<KeyMstTDTO>  getKeyMstInfo(String keyBizCfcd) throws Exception{
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
		
		
		KeyMstTDTO keyMstTDTO = new KeyMstTDTO();
		List<KeyMstTDTO> keyMstPTDTO = mapper.getKeyMstInfo(keyBizCfcd);

		return keyMstPTDTO;
	}
	
	
	/*
	 * 신규Key정보 등록
	 * 
	 *
	 */	
	public  void rigsterNewKeyInfo(KeyMstTDTO keyMstTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
		
		mapper.rigsterNewKeyInfo (keyMstTDTO);
		session.commit();
		session.close();
	
		
	}
	
	
	
	
	/*
	 * Key히스터리 저장
	 * 
	 *
	 */	
	public  void saveNewKeyHist(KeyhistinfoTDTO keyhistinfoTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		KeyhistInfoDAO mapper = session.getMapper(KeyhistInfoDAO.class);
		
		mapper.saveNewKeyHist (keyhistinfoTDTO);
		session.commit();
		session.close();
	
		
	}

	
	
	/*
	 * 문자형키 생성
	 * 
	 *
	 */	
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
