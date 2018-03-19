package com.xingxue.class11.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingxue.class11.entity.Address;
import com.xingxue.class11.entity.mapper.AddressMapper;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;
import com.xingxue.class11.service.AddressService;

/**
 * AddressService
 */
@Service
public class AddressServiceImpl implements AddressService{

    @Autowired private AddressMapper mapper;

    /**********************网站**************************/



    /**********************后台**************************/



    /**********************混用**************************/
    /**
     * 保存
     * @param address
     */
    public void save(Address address) {
        Date now = new Date();
        address.setCreateTime(now);
        address.setUpdateTime(now);
        this.mapper.insertSelective(address);
    }

    /**
     * 根据用户ID查询全部
     * @return
     */
    public List<Address> getByUserId(Long userId) {
        return mapper.selectByUserId(userId);
    }

    /**
     * 根据ID查询
     * @return
     */
    public Address getById(Long id) {
        return this.mapper.selectById(id);
    }


    /**
     * 分页查询
     * @param search
     * @return
     */
    public PagingResult<Address> getPage(Search search) {
        Long total = this.mapper.countPage();
		List<Address> data = this.mapper.selectPage(search);
		return new PagingResult<>(search.getRows(), search.getPage(), total, data);
    }



}
