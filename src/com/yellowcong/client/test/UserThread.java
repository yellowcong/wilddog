package com.yellowcong.client.test;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import com.yellowcong.dao.UserDao;
import com.yellowcong.dao.impl.UserDaoImpl;
import com.yellowcong.model.User;

public class UserThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			UserDao dao = new UserDaoImpl();
			Random rand = new Random();
			StringBuffer buff = new StringBuffer();
			for(int i=0;i<2000;i++){
				buff.append(UUID.randomUUID().toString());
				
			}
			User user = new User();
			user.setUsername(buff.toString());
			user.setEmail(buff.toString());
			user.setAge(rand.nextInt(40000));
			user.setNickname(buff.toString());
			dao.add(user);
				
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"); 
			String rs = format.format(System.currentTimeMillis());
			System.out.println(rs+" "+Thread.currentThread().getName());
		}
	}

}
