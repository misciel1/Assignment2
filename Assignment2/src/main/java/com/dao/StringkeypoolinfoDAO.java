package com.dao;

import com.dto.StringkeypoolinfoTDTO;

public interface StringkeypoolinfoDAO {
		
		/*������ KEY���� */	
		void saveNewStringKey (final StringkeypoolinfoTDTO stringkeypoolinfoTDTO);
		
		/*����*/
		void clearNewStringKey();
		
		/*������Key���� �ű�Ű��ȸ*/
		StringkeypoolinfoTDTO getNewStringKey (final long keySeq);
}


