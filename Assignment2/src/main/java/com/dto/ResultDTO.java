package com.dto;

import com.uc.MsgMgmtUC;

public class ResultDTO {
	
	public void setResponse(String response) {
		this.response = response;
	}
	private String keyNum;
	private String response;
	
	public String getKeyNum() {
		return keyNum;
	}
	public void setKeyNum(String keyNum) {
		this.keyNum = keyNum;
	}
	public String getResponse() {
		return response;
	}


	
	
}
