package com.xingxue.class11.entity.mapper;

import java.util.List;

import com.xingxue.class11.entity.User;
import com.xingxue.class11.framework.page.Search;

public interface UserMapper {

	/**
     * 通过ID获取用户
     * @param id
     * @return
     */
	public User selectByPrimaryKey(Long id);
	
	 /**
     * 保存用户信息
     * @param user 用户
     * @return id
     */
	public int insertSelective(User user);
	
	/**
     * 更新用户信息
     * @param user
     * @return
     */
	public int updateByPrimaryKeySelective(User user);
	
	/**
     * 通过用户名查询用户
     * @param name 用户名
     * @return
     */
	public User selectByName(String name);
	
	public void deleteById(Long id);
	
	public List<User> selectPageUsers(Search search);
	
	public Long countPageUsers(Search search);
	
}
