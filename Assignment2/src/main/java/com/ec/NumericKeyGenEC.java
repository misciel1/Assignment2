package com.ec;

import java.util.Calendar;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.dto.KeyMstTDTO;
import com.session.KeySessionFactory;
import com.app.KeyAppTest2;
import com.dao.KeyMstDAO;
import com.uc.KeyNumHandlerUC;

public class NumericKeyGenEC {
	
	final static Logger loggoer = Logger.getLogger(NumericKeyGenEC.class);
	
	
	
	public String makeNumericKey(KeyMstTDTO keyMstTDTO){
	//public String makeNumericKey(String keyBizCfcd,String genCfcd,String keyPrifix,int keyLen,long lstKeySeq){
	
	
		KeyNumHandlerUC keyNumHandlerUC = new KeyNumHandlerUC();
		
		String keyBizCfcd = keyMstTDTO.getKeyBizCfcd();
		String type = keyMstTDTO.getType(); //Ű�߱�����  String,Mysql,Genric
		String genCfcd = keyMstTDTO.getGenCfcd(); //������ prefix�߻�����
		String keyPrifix = keyMstTDTO.getKeyPrifix();
		int keyLen = keyMstTDTO.getKeyLen();
		long lstKeySeq = keyMstTDTO.getLstKeySeq();
		String newKey = "";
		String postKeyNum = "";
		String prifixKeyNum = "";
		
		
		int thisYear = Calendar.getInstance().get(Calendar.YEAR);
		
		
		/*������ Preipix����*/
		if(genCfcd!=null&&"01".equals(genCfcd))  //Prifix+�⵵(���ڸ�)+KeyNum
		{			
			 prifixKeyNum = keyPrifix+String.valueOf(thisYear).substring(2, 4);			 
		}
		else if(genCfcd!=null&&"02".equals(genCfcd))  //Prifix+�⵵(YYYY)+KeyNum
		{			
			 prifixKeyNum = keyPrifix+String.valueOf(thisYear);			 				 
		}
		else //Prifix+KeyNum
		{
			 prifixKeyNum = keyPrifix;			 	 
		}	
		
		
		
		/*Ű�߱�����*/
		if(type!=null&&"03".equals(type)) //GnericKeyGenerator
		{
			 postKeyNum = keyNumHandlerUC.makeNewKeyNum(keyLen,lstKeySeq);
		}
		else if(type!=null&&"02".equals(type)) //MysqlKeyGenerator
		{
			KeySessionFactory fac = new KeySessionFactory();
			SqlSession session = fac.openSession(false);	
			KeyMstDAO mapper = session.getMapper(KeyMstDAO.class);
			
			postKeyNum = mapper.getNewKeyForMysql(keyBizCfcd);
		}
		
		
		//if(loggoer.isInfoEnabled()) loggoer.info("prifixKeyNum : "+prifixKeyNum);
		 //if(loggoer.isInfoEnabled()) loggoer.info("postKeyNum : "+postKeyNum);
	
		/*KEY����*/
		newKey = prifixKeyNum + postKeyNum;
		
		
		return newKey;	
	}
	
	
	
}
