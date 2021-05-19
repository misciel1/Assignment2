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
	 * Key�߱����� ��ȸ
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
	
			
			
			
			/*Key�߱����� ��ȸ*/
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
				 throw new KeyExceptionUC("[ERROR] ��ϵ� Key������ �����ϴ�. ���� KEY������ �Է��ϼ���.");
			 }
			 
			 			 
			 //KEY�߱޹��
			 if("01".equals(type)) //������ 
			 {
				 newKey = StringKeyGenEC.makeGenricKey(keyMstTDTO);
			 }	 
			 else if("02".equals(type))//������+MysqlKeyGenerator
			 {
				 newKey = numericKeyGenEC.makeNumericKey(keyMstTDTO);
			 }	
			 else if("03".equals(type))//������+GnericKeyGenerator
			 {				 
				 newKey = numericKeyGenEC.makeNumericKey(keyMstTDTO);
				
			 }
			 
			 			 
				
		
			 return newKey;	
	}
	
	
	
	/*
	 * ����Ű��ȣ ������Ʈ
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
	 * Key������ȸ
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
	 * �ű�Key���� ���
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
	 * Key�����͸� ����
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
	 * ������Ű ����
	 * 
	 *
	 */	
	public  void saveNewStringKey(List<StringkeypoolinfoTDTO> stringkeypoolinfoPTDTO){
		
		KeySessionFactory fac = new KeySessionFactory();
		SqlSession session = fac.openSession(false);	
		StringkeypoolinfoDAO mapper = session.getMapper(StringkeypoolinfoDAO.class);
		
		/*�ʱ�ȭ*/
		mapper.clearNewStringKey();
	
		int cnt = stringkeypoolinfoPTDTO.size();
		
		/*����*/
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
