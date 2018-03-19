package com.xingxue.class11.entity.mapper;

import com.xingxue.class11.entity.DealCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class DealCategoryMapperTest {

    @Autowired
    private DealCategoryMapper mapper;

    @Test
    public void testCategories() throws Exception {
        List<DealCategory> dealCategories = mapper.testCategories();
        for(DealCategory dealCategory:dealCategories){
            System.out.println(dealCategory);
        }
    }

}