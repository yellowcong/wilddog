package com.yellowcong.client.test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.wilddog.client.rest.error.JacksonUtilityException;
import com.wilddog.client.rest.error.WilddogException;
import com.wilddog.client.rest.model.WilddogResponse;
import com.wilddog.client.rest.service.Wilddog;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public class Demo {

	public static void main(String[] args) throws WilddogException, JsonParseException, JsonMappingException, IOException, JacksonUtilityException {
		
		//https://yellowcong.wilddogio.com
		//https://<appId>.wilddogio.com/rest
		String wilddog_baseUrl = "https://yellowcong.wilddogio.com/rest";
		
		// 创建节点
		Wilddog wilddog = new Wilddog( wilddog_baseUrl );
		
		//添加节点，如果这个节点中不存在数据，就会添加这个节点
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put( "age", "21" );
		dataMap.put( "name", "黄聪" );
		dataMap.put( "email", "717350389@qq.com" );
		dataMap.put( "nickname", "yellowcong" );
		
		WilddogResponse response = wilddog.put( dataMap );
		System.out.println(response.getSuccess()?"success":"fails");
		
		//POST提交数据 ,post和  put的方法数据是不一样的
		WilddogResponse response2 = wilddog.post("post", "doubi");
		System.out.println("xx");
		
		
		// "DELETE"  删除这个主节点
		//WilddogResponse response = wilddog.delete();
		
		//删除某个具体的节点
		//wilddog.delete("nickname");
		
		
		//直接获取所有节点数据
		WilddogResponse response3= wilddog.get();
		System.out.println(response3.getRawBody());
		
		//获取某个具体的节点
		WilddogResponse response4= wilddog.get("age");
		System.out.println(response4.getRawBody());
		
		
		/*
		
		// "PUT" (test-map into a sub-node off of the wd4jDemo-root)
		dataMap = new LinkedHashMap<String, Object>();
		dataMap.put( "Key_1", "This is the first value" );
		dataMap.put( "Key_2", "This is value #2" );
		Map<String, Object> dataMap2 = new LinkedHashMap<String, Object>();
		dataMap2.put( "Sub-Key1", "This is the first sub-value" );
		dataMap.put( "Key_3", dataMap2 );
		response = wilddog.put( "test-PUT", dataMap );
		System.out.println( "\n\nResult of PUT (for the test-PUT):\n" + response );
		System.out.println("\n");
		
		
		// "POST" (test-map into a sub-node off of the wd4jDemo-root)
		response = wilddog.post( "test-POST", dataMap );
		System.out.println( "\n\nResult of POST (for the test-POST):\n" + response );
		System.out.println("\n");
		
		
		// "DELETE" (it's own test-node)
		dataMap = new LinkedHashMap<String, Object>();
		dataMap.put( "DELETE", "This should not appear; should have been DELETED" );
		response = wilddog.put( "test-DELETE", dataMap );
		System.out.println( "\n\nResult of PUT (for the test-DELETE):\n" + response );
		response = wilddog.delete( "test-DELETE");
		System.out.println( "\n\nResult of DELETE (for the test-DELETE):\n" + response );
		response = wilddog.get( "test-DELETE" );
		System.out.println( "\n\nResult of GET (for the test-DELETE):\n" + response );
		*/
	}
}




