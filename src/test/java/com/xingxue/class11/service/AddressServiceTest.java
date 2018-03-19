package com.xingxue.class11.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xingxue.class11.entity.Address;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class AddressServiceTest {
	
	@Autowired
	private AddressService service;
	
	@Test
	public void test1() {
		Address addr = new Address();
		addr.setCreateTime(new Date());
		addr.setArea("北京");
		addr.setDetail("北京某地址");
		addr.setPhone("13511112222");
		addr.setReceiver("张先生");
		addr.setType("HOME");
		addr.setUpdateTime(new Date());
		addr.setUserId(1L);
		service.save(addr );
	}
	
	@Test
	public void test2() {
		Address addr = service.getById(2L);
		//log.debug(addr.toString());
	}
	
	@Test
	public void test3() {
		List<Address> list = service.getByUserId(1L);
		for(Address a : list) {
			//log.debug(a.toString());
		}
	}
	
	@Test
	public void test4() {
		Search search = new Search(2, 10, null);
		PagingResult<Address> page = service.getPage(search);
//		log.debug("{}", page.getTotal());
		for(Address a : page.getData()) {
			//log.debug(a.toString());
		}
	}

}
