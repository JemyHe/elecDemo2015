package com.xingxue.class11.entity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xingxue.class11.entity.Order;
import com.xingxue.class11.framework.page.Search;

public interface OrderMapper {
	
	/**
     * 分页查询订单信息
     * @param search
     * @return
     */
	public List<Order> selectPageOrders(Search search);
	
	
	public Long countPageOrders(Search search);
	
	/**
     * 根据用户ID查询该用户的订单列表
     * @param userId	用户ID
     * @return	订单集合
     */
	public List<Order> selectOrders(
			@Param("userId") Long userId,
			@Param("orderStatus") Integer orderStatus);
	
	/**
     * 根据ID查询订单基本信息
     * @param id	订单ID
     * @return	订单信息
     */
	public Order selectOrderById(int id);
	
	public Long selectOrderCountStatusByUserId(
			@Param("userId") Long userId,
			@Param("orderStatus") Integer orderStatus);
	
	 /**
     * 保存订单信息
     * @param order	订单实体
     */
	public int insertOrderSelective(Order order);
	
	/**
     * 更新订单状态
     * @param orderId
     * @param orderStatus
     */
	public int updateStatusById(
			@Param("orderId") long orderId, 
			@Param("orderStatus") int orderStatus);
	
	

}
