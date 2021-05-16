package com.dao;

import com.dto.KeyhistinfoTDTO;

public interface KeyhistInfoDAO {
	
	/*key이력정보저장 */	
	void saveNewKeyHist (final KeyhistinfoTDTO keyhistinfoTDTO);
}
