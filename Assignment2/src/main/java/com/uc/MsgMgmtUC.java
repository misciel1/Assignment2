package com.uc;


public enum MsgMgmtUC {
	
	    OK(200, "200 OK"),
	    INSERTED(300, "300 INSERTED");
	  

		private final int code;
	   
		private final String description;
    
	    public int getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

	

	    MsgMgmtUC(int code, String description) {
	        this.code = code;
	        this.description = description;
	    }
}
