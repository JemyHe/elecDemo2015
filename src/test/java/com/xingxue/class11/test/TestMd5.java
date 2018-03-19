package com.xingxue.class11.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestMd5 {
	
	// 加盐混淆
	String prefix = "&$#*";
	String suffix = "()@&";
	@Test
	public void test() {
		String pass = DigestUtils.md5DigestAsHex((prefix+"123456"+suffix).getBytes());
		System.out.println(pass);
	}
	
	// &$#*123456()@&
	// 多次签名迭代
	@Test
	public void test2() {
		String pass = DigestUtils.md5DigestAsHex(("123456").getBytes());
		for(int i = 0; i < 1000; i++){
			pass = DigestUtils.md5DigestAsHex(pass.getBytes());
		}
		System.out.println(pass);		
	}
	
	@Test
	public void test3() {
		try {
			int i = 1/0;
		} catch (Exception e) {

		}
		
	}

}
