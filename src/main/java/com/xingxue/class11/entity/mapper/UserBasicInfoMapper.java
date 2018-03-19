package com.xingxue.class11.entity.mapper;

import com.xingxue.class11.entity.UserBasicInfo;

public interface UserBasicInfoMapper {

	public UserBasicInfo selectByPrimaryKey(Long id);

	public int insertSelective(UserBasicInfo info);

	public int updateByPrimaryKeySelective(UserBasicInfo info);

	public int deleteById(Long id);

}
