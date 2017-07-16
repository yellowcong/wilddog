package com.yellowcong.client.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.wilddog.client.rest.error.WilddogException;
import com.wilddog.client.rest.model.WilddogResponse;
import com.wilddog.client.rest.service.Wilddog;
import com.yellowcong.dao.UserDao;
import com.yellowcong.dao.impl.UserDaoImpl;
import com.yellowcong.model.User;
import com.yellowcong.utils.JsonUtils;

public class UserDaoTest {
	public static void main(String[] args) {
		
		UserThread thread = new UserThread();
		int threadCount = 20 ;
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		
		for(int i=0;i<threadCount;i++){
			pool.submit(thread);
		}
	}
	
	@Test
	public void testDelete() throws IOException, WilddogException{
		UserDao dao = new UserDaoImpl();
		Wilddog dog = dao.getNewWilddog();
		dog.equalTo("username", "yellowcong");
		dog.equalTo("age", "12");
		
		String str = dog.get().getRawBody();
		System.out.println(str);
	}
	@Test
	public void testEqual(){
		UserDao dao = new UserDaoImpl();
//		List<User> users = dao.between("age",12,18);
		List<User> users = dao.equalTo("age", "50");
		
		//System.out.println(users.size());
		for(User user:users){
			System.out.println(user.getAge()+":"+user.getNickname());
		}
	}
	
	@Test
	public void testStartAt(){
		UserDao dao = new UserDaoImpl();
		 
		List<User> users = dao.startAt("age",2);
		//System.out.println(users.size());
		if(users.size()>0){
			for(User user:users){
				System.out.println(user.getAge()+":"+user.getNickname());
			}
		}
	}
	
	@Test
	public void test2() throws Exception, Throwable{
		UserDao dao = new UserDaoImpl();
		
		Wilddog dog = dao.getNewWilddog();
		//数据查询,格式化JSON
		dog.addQuery("print", "pretty");
		WilddogResponse resp = dog.get();
		
		System.out.println(resp.getRawBody());
		
		//查询云端时间戳
		dog = dao.getNewWilddog();
		dog= dog.addQuery("sv", "timestamp");
		resp = dog.get();
		System.out.println(resp.getRawBody());
		
		//其中必须用orderBy 的字段， orderBy需要建立索引
		//排序  精确查询  orderBy 和equalTo ，判断等于的情况 
		dog = dao.getNewWilddog();
		dog.addQuery("orderBy", "\"age\""); //对于查询的参数需要添加 \"\"
		dog.addQuery("equalTo", "12");
		
		resp = dog.get();
		System.out.println("等于 orderBy&equalTo");
		System.out.println(resp.getRawBody());
		
		//排序 精确查询 大于 orderBy&startAt
		dog = dao.getNewWilddog();
		dog.addQuery("orderBy", "\"age\"");
		dog.addQuery("startAt", "18");
		
		resp = dog.get();
		System.out.println("大于 orderBy&startAt");
		System.out.println(resp.getRawBody());
		//排序 精确查询  小于
		//orderBy&endAt
		
		
		//查询两条数据  limitToLast 最后几条
		dog = dao.getNewWilddog();
		dog.addQuery("limitToLast", "2");
		dog.addQuery("orderBy", "\"age\"");
		resp = dog.get();
		System.out.println(resp.getRawBody());
		
		//limitToFirst 最前面几条
		dog = dao.getNewWilddog();
		dog.addQuery("limitToFirst", "2");
		dog.addQuery("orderBy", "\"age\"");
		resp = dog.get();
		System.out.println(resp.getRawBody());
		
		/*Random rand = new Random();
		StringBuffer buff = new StringBuffer();
		for(int i=0;i<2000;i++){
			buff.append(UUID.randomUUID().toString());
			
		}
		while(true){
			User user = new User();
			user.setUsername(buff.toString());
			user.setEmail(buff.toString());
			user.setAge(rand.nextInt(40000));
			user.setNickname(buff.toString());
			dao.add(user);
			
		}*/
		/*User user = new User();
		user.setUsername("yellowcong");
		user.setEmail("717350389@qq.com");
		user.setAge(12);
		user.setNickname("doubi");
		dao.add(user);*/
//		Wilddog dog = dao.getNewWilddog();
		
//		dog.addQuery("age", "12");
		
//		WilddogResponse resp = dog.get();
//		System.out.println(resp.toString());
		
		/*List<User> users = dao.listAll();
		System.out.println(users.size());
		for(User user:users){
			System.out.println(user.getUsername());
		}*/
		
		//String str = "de6C\":\\{\"age\":1";
		//String [] ars =str.split("\":\\{\"");
		
		/*for(int i=0;i<10;i++){
			User user = new User();
			user.setUsername("yellowcong"+i);
			user.setEmail("717350389@qq.com");
			user.setAge(12+i);
			user.setNickname("doubi");
			dao.add(user);
		}*/
		//System.out.println(dao.getCount());
		//删除表
		/*
		 * dao.deleteAll();
		*/
		
		//Wilddog dog = dao.getWilddog();
		
		/*
		 WilddogTable  tab=  Class.forName("com.yellowcong.model.User").getAnnotation(WilddogTable.class);
		 System.out.println(tab.value());*/
	}
	
	@Test
	public void test() throws UnsupportedEncodingException, WilddogException{
		UserDao dao = new UserDaoImpl();
		//排序  精确查询  orderBy 和equalTo ，判断等于的情况 
		Wilddog dog = dao.getNewWilddog();
		dog.addQuery("orderBy", "\"age\""); //对于查询的参数需要添加 \"\"
		dog.addQuery("startAt", "18");
		//dog.addQuery("endAt", "30");
		WilddogResponse resp = dog.get();
		System.out.println(resp.getRawBody());
		
	}
	@Test
	public void testAdd() throws UnsupportedEncodingException, WilddogException{
		UserDao dao = new UserDaoImpl();
		Wilddog dog = dao.getWilddog();
		User user = new User();
		user.setUsername("yellowcong2");
		user.setEmail("yellowcong@qq.com");
		user.setAge(50);
		user.setNickname("yellowcong2");
		dog.post(JsonUtils.object2Json(user));
		
		dao.add(user);
		
	}
}
