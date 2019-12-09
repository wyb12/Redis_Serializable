package com.wuyubin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wuyubin.bean.User;

/**
 * @author 吴宇斌
 *
 * 2019年12月9日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate template;
	@SuppressWarnings("unchecked")
	//使用jdk序列化
	@Test
	public void jdkTest() {
		//设置开始时间
		long start = System.currentTimeMillis();
		//循环5w次
		for (int i = 1; i <= 50000; i++) {
			//创建user对象
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getChineseName());
			user.setGender(StringUtils.getSex());
			user.setTel(StringUtils.getPhone());
			user.setEmail(StringUtils.getEmail(3, 20));
			user.setBir(StringUtils.getBirthday());
			//使用keyvalue装载
			template.opsForValue().set("user"+i, user);
		}
		//设置结束时间
		long end = System.currentTimeMillis();
		System.out.println("共保存5w条数据");
		System.out.println("JDK使用时间为"+(end-start)+"ms");
	}
	@SuppressWarnings("unchecked")
	@Test
	//使用json序列化
	public void jsonTest() {
		//设置开始时间
		long start = System.currentTimeMillis();
		//循环5w次
		for (int i = 1; i <= 50000; i++) {
			//创建对象
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getChineseName());
			user.setGender(StringUtils.getSex());
			user.setTel(StringUtils.getPhone());
			user.setEmail(StringUtils.getEmail(3, 20));
			user.setBir(StringUtils.getBirthday());
			//使用keyvalue装载
			template.opsForValue().set("user"+i, user);
		}
		//设置结束时间
		long end = System.currentTimeMillis();
		System.out.println("共保存5w条数据");
		System.out.println("JSON使用时间为"+(end-start)+"ms");
	}
	@SuppressWarnings("unchecked")
	@Test
	//使用hash序列化
	public void hashTest() {
		//设置开始时间
		long start = System.currentTimeMillis();
		//循环5w次
		for (int i = 1; i <= 50000; i++) {
			//创建对象
			User user = new User();
			user.setId(i);
			user.setName(StringUtils.getChineseName());
			user.setGender(StringUtils.getSex());
			user.setTel(StringUtils.getPhone());
			user.setEmail(StringUtils.getEmail(3, 20));
			user.setBir(StringUtils.getBirthday());
			//使用hash装载
			template.opsForHash().put("users"+i, "user"+i, user.toString());
		}
		//设置结束时间
		long end = System.currentTimeMillis();
		System.out.println("共保存5w条数据");
		System.out.println("HASH使用时间为"+(end-start)+"ms");
	}
}
