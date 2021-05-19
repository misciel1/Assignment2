package com.dao;

import java.util.List;

import com.dto.KeyMstTDTO;



public interface KeyMstDAO {
	
	/*Key원장정보조회*/	
	List<KeyMstTDTO> getKeyMstInfo(final String keyBizCfcd);
	
	/*최종Key번호 업데이트 */	
	void updateLstKeyInfo(final String lstKeyNum, final String keyBizCfcd);
	
	/*key정보입력 */	
	void rigsterNewKeyInfo (final KeyMstTDTO keyMstTDTO);
	
	/*Next Key조회 MySql ONly*/	
	String getNewKeyForMysql(final String keyBizCfcd);
	
		

}

