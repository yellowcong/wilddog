package com.yellowcong.dao;

import java.util.List;

import com.wilddog.client.rest.service.Wilddog;

public interface BaseDao<T> {

	/**
	 * 一次获取到所有的条数 , 这样，我们的数据中，不可以存在{}的字符
	 * @return
	 */
	public List<T> listAll();

	/**
	 * 获取记录的条数
	 * @return
	 */
	public int getCount();

	/**
	 * 直接删除这个表
	 * 
	 * @return
	 */
	public boolean deleteAll();
	
	/**
	 * 更细数据
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 删除数据
	 * @param t
	 * @return
	 */
	public boolean delete(T t) ;
	
	/**
	 * 直接删除数据,删除具体的数据， 不是 < >这种
	 * @param param 数据的
	 * @param val
	 * @return
	 */
	public boolean delete(String param,String val);

	/**
	 * 获取一个新的链接对象
	 * 
	 * @return
	 */
	public Wilddog getNewWilddog();
	
	/**
	 * 获取一个操作当前类节点的对象
	 * @return
	 */
	public Wilddog getWilddog();
	
	/**
	 * 获取节点的数据，通过id来获取
	 * @param id
	 * @return
	 */
	public Wilddog getWilddogById(String id);

	/**
	 * 添加单个的数据
	 * 
	 * @param paramT
	 * @return
	 */
	public T add(T paramT) ;

	/**
	 * 添加多个节点的对象
	 * @param objs
	 * @return
	 */
	public List<T> add(List<T> objs);
	
	/**
	 * 设置从第一条开始，一共返回多少条数据（节点）。
	 * @param param 查询的属性  
	 * @param count 条数
	 * @return
	 */
	public List<T> limitToFirst(String param,Integer count);
	
	/**
	 * 查询云端时间戳
	 * @return
	 */
	public String timestamp();
	
	/**
	 * 设置从最后一条开始，一共返回多少条（返回结果仍是升序，降序要自己处理）。
	 * @param param 查询的属性  
	 * @param count 条数
	 * @return
	 */
	public List<T> limitToLast(String param,Integer count);
	
	/**
	 * 返回大于或等于指定的键、值或优先级的数据，具体取决于所选的排序方法。
	 * @param param 查询的属性  
	 * @param val 大于的值
	 * @return
	 */
	public List<T> startAt(String param,Integer val);
	
	/**
	 * 返回小于或等于指定的键、值或优先级的数据，具体取决于所选的排序方法。
	 * @param param 查询的属性  
	 * @param val 大于的值
	 * @return
	 */
	public List<T> endAt(String param,Integer val);
	
	/**
	 * 查询在某个字段中间的数据  ，age >1 ,age<20
	 * @param param 参数
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
	public List<T> between(String param,Integer start,Integer end);
	
	/**
	 * 返回等于指定的键、值或优先级的数据，具体取决于所选的排序方法。可用于精确查询。
	 * @param param 
	 * @param val 
	 * @return
	 */
	public List<T> equalTo(String param,String val) ;
	
	/**
	 * 清空野狗云上的所有数据
	 */
	public void deleteAllData();
}
