package com.dao;

import com.dto.StringkeypoolinfoTDTO;

public interface StringkeypoolinfoDAO {
		
		/*문자형 KEY저장 */	
		void saveNewStringKey (final StringkeypoolinfoTDTO stringkeypoolinfoTDTO);
		
		/*삭제*/
		void clearNewStringKey(String keyBizCfcd);
		
		/*문자형Key에서 신규키조회*/
		StringkeypoolinfoTDTO getNewStringKey (final StringkeypoolinfoTDTO stringkeypoolinfoTDTO);
}


