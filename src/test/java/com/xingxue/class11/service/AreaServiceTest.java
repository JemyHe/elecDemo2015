package com.xingxue.class11.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xingxue.class11.entity.Area;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Administrator on 2017/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class AreaServiceTest {

	@Autowired
	private AreaService service;

	@Test
	public void getByParentId() throws Exception {
		List<Area> list = service.getByParentId(0L);
		for (Area a : list) {
			//
		}
	}

	@Test
	public void test2() {
		List<Area> root = service.getAll();
		for (Area a1 : root) {
			//log.debug(a1.toString());
			if (a1.getChildren() != null)
				for (Area a2 : a1.getChildren()) {
					//log.debug("\t{}", a2);
					if (a2.getChildren() != null)
						for (Area a3 : a2.getChildren()) {
							//log.debug("\t{}", a3);
						}
				}
		}
	}

}