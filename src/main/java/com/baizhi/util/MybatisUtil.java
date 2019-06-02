package com.baizhi.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
	private  static  SqlSessionFactory sessionFactory;
	private  static  ThreadLocal<SqlSession> tol = new ThreadLocal<SqlSession>(); 
	static {
		 InputStream is =null;
		  try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
			 //2 . 创建SqlSessionFactory 
			sessionFactory = new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	//返回值为 SqlSession 的方法  获得sqlSession 对象
	public static SqlSession getSqlSession(){
		
	 SqlSession sqlSession = tol.get();
	 if(sqlSession == null){
	 try{
		  //3.  创建SqlSession 
		  sqlSession = sessionFactory.openSession();
		  tol.set(sqlSession);
		  return sqlSession;
		 }catch(Exception e){
		  e.printStackTrace();
		 } 
	 }
		return sqlSession;
	}
	
	// 关闭资源
	public static void close(){
		SqlSession sqlSession=getSqlSession();
		tol.remove();
		sqlSession.close();
	}    
}
