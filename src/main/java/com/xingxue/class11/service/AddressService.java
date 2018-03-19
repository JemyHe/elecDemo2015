package com.xingxue.class11.service;


import java.util.List;

import com.xingxue.class11.entity.Address;
import com.xingxue.class11.framework.page.PagingResult;
import com.xingxue.class11.framework.page.Search;

/**
 * AddressService
 */
public interface AddressService {


    /**
     * 保存
     * @param address
     */
    public void save(Address address);

    /**
     * 根据用户ID查询全部
     * @return
     */
    List<Address> getByUserId(Long userId);
    /**
     * 根据ID查询
     * @return
     */
    Address getById(Long id);


    /**
     * 分页查询
     * @param search
     * @return
     */
    public PagingResult<Address> getPage(Search search);

}
