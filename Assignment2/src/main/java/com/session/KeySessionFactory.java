package com.session;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class KeySessionFactory {

	private SqlSessionFactory sessionFactory;
	
	public KeySessionFactory(){
		String resource = "com/session/Mybatis.xml"; //데이터베이스의 설정정보를 입력해준 xml 파일
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public SqlSession openSession() {
		return this.openSession(false);
	}
	
	public SqlSession openSession(boolean autoCommit) {
		SqlSession session = null;
		session = sessionFactory.openSession(autoCommit);
		return session;
	}
	
}
