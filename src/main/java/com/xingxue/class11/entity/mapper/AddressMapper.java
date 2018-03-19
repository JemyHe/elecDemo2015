package com.xingxue.class11.entity.mapper;

import java.util.List;

import com.xingxue.class11.entity.Address;
import com.xingxue.class11.framework.page.Search;

public interface AddressMapper {

	public void insertSelective(Address address);

	public void deleteById(Long id);

	public List<Address> selectByUserId(Long userId);

	public Address selectById(Long id);

	public List<Address> selectPage(Search search);

	public Long countPage();


}
