package com.dao;

import java.util.List;

import com.dto.KeyMstTDTO;



public interface KeyMstDAO {
	
	/*Key����������ȸ*/	
	List<KeyMstTDTO> getKeyMstInfo(final String keyBizCfcd);
	
	/*����Key��ȣ ������Ʈ */	
	void updateLstKeyInfo(String lstKeyNum, String keyBizCfcd);
	
	/*key�����Է� */	
	void rigsterNewKeyInfo (final KeyMstTDTO keyMstTDTO);
	
		

}

