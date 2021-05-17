package com.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.dto.UserInfoDTO;
import com.pc.KeyMgmtPC;
import com.session.KeySessionFactory;
import java.net.InetAddress;
import java.lang.Thread; 






public class KeyAppTest2 implements Runnable {
	
	final static Logger loggoer = Logger.getLogger(KeyAppTest2.class);

	
	
	public static void main(String[] args) throws Exception {
		
			System.out.println("#######################################################");
			System.out.println("    #UNIT TEST2 - Multi thread TEST ");
			System.out.println("    #Mulitple Key ");
			System.out.println("#######################################################");					
			
			
			Runnable task = new KeyAppTest2();
			ArrayList<Thread> threadList = new ArrayList<Thread>(); // 쓰레드들을 담을 객체
			
			for(char i = 0; 1000>i; i++) 
			{
				
				
				Thread subTread = new Thread(task);				
				subTread.start();
				
				threadList.add(subTread);
			}	
			
			for(Thread t : threadList){
				t.join(); // 쓰레드의 처리가 끝날때까지 기다립니다.
			}
			
			System.out.println("Compeleted");
		
	}
	
	
	
	public void run() {
		
		System.out.println( Thread.currentThread() );
		
		Random r = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
	    r.setSeed(System.currentTimeMillis());
	    
	    
	    
	    if(r.nextLong()%3>1) {
	    	issueNewKey("CT01");
	    }
	    else
	    {
	    	issueNewKey("CT02");
	    }	
	    
		
		
				
	}
	
	
	public synchronized void issueNewKey(String keyBizCfcd) {
			
				/*변수정의*/
				KeyMgmtPC keyMgmtPC = new KeyMgmtPC();		
				String newkey = "";
				
			
			
				try {
						/*사용자정보셋팅*/
					    InetAddress address = InetAddress.getLocalHost();    
					    String callerNm ="";
					    callerNm = Thread.currentThread().getStackTrace()[1].getClassName();
					    callerNm = callerNm+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
						
						UserInfoDTO userInfoDTO = new UserInfoDTO();			
						userInfoDTO.setSystemName(callerNm);
						userInfoDTO.setSystemUsedIP(address.getHostAddress());		
						
						/*KEY발급*/
						newkey = keyMgmtPC.issueNewKey(keyBizCfcd,userInfoDTO);
						
						/*결과출력*/
						System.out.println("New key is : "+newkey);
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}

	

}
