package com.uc;

public class KeyNumHandlerUC {
	
	public String makeNewKeyNum(int keyLen,long lstKeyNum){
		
		String postKeyNum = "";
		
		
		lstKeyNum = lstKeyNum+1;  //keyNum +1����
		postKeyNum = String.valueOf(lstKeyNum);
		
		//Formatting   ex)"00000001"
		for(int i=0;keyLen>i;i++)
		{
			postKeyNum = "0"+postKeyNum;
			
			if(postKeyNum.length()==keyLen) 
			{
				break;// keyNum�� ���ڸ� ���ǵ� �ڸ����� ��ġ��Ŵ 
			}	
		}	
		
		
		
		return postKeyNum;	
	}
}
