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
			ArrayList<Thread> threadList = new ArrayList<Thread>(); // ��������� ���� ��ü
			
			for(char i = 0; 1000>i; i++) 
			{
				
				
				Thread subTread = new Thread(task);				
				subTread.start();
				
				threadList.add(subTread);
			}	
			
			for(Thread t : threadList){
				t.join(); // �������� ó���� ���������� ��ٸ��ϴ�.
			}
			
			System.out.println("Compeleted");
		
	}
	
	
	
	public void run() {
		
		System.out.println( Thread.currentThread() );
		
		Random r = new Random(); //���� ��ü ����(����Ʈ �õ尪 : ����ð�)
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
			
				/*��������*/
				KeyMgmtPC keyMgmtPC = new KeyMgmtPC();		
				String newkey = "";
				
			
			
				try {
						/*�������������*/
					    InetAddress address = InetAddress.getLocalHost();    
					    String callerNm ="";
					    callerNm = Thread.currentThread().getStackTrace()[1].getClassName();
					    callerNm = callerNm+"."+Thread.currentThread().getStackTrace()[1].getMethodName();
						
						UserInfoDTO userInfoDTO = new UserInfoDTO();			
						userInfoDTO.setSystemName(callerNm);
						userInfoDTO.setSystemUsedIP(address.getHostAddress());		
						
						/*KEY�߱�*/
						newkey = keyMgmtPC.issueNewKey(keyBizCfcd,userInfoDTO);
						
						/*������*/
						System.out.println("New key is : "+newkey);
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	}

	

}
