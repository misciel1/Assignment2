package com.dao;

import java.util.List;

import com.dto.KeyMstTDTO;



public interface KeyMstDAO {
	
	/*Key����������ȸ*/	
	List<KeyMstTDTO> getKeyMstInfo(final String keyBizCfcd);
	
	/*����Key��ȣ ������Ʈ */	
	void updateLstKeyInfo(final String lstKeyNum, final String keyBizCfcd);
	
	/*key�����Է� */	
	void rigsterNewKeyInfo (final KeyMstTDTO keyMstTDTO);
	
	/*Next Key��ȸ MySql ONly*/	
	String getNewKeyForMysql(final String keyBizCfcd);
	
		

}

