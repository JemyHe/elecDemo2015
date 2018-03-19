package com.xingxue.class11.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.entity.mapper.DealMapper;
import com.xingxue.class11.framework.page.Search;

/**
 * Created by Administrator on 2017/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class DealDAOTest {

    @Autowired
    private DealMapper mapper;

    @Test
    public void selectOnIndex() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(3L);
        ids.add(5L);
        List<Deal> deals = mapper.selectOnIndex(367L, 3,ids);
        for (Deal deal:deals){
            System.out.println(deal);
        }
    }

    @Test
    public void selectForCart() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(3L);
        ids.add(7L);
        ids.add(5L);
        List<Deal> deals = mapper.selectForCart(ids);
        for (Deal deal:deals){
            System.out.println(deal);
        }
    }
    
    @Test
    public void test3() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("dealTitle", "ip");
		Search search = new Search(1, 10, params);
        List<Deal> deals = mapper.selectPageDeals(search);
        for (Deal deal:deals){
            System.out.println(deal);
        }
    }

}