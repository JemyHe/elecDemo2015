package com.xingxue.class11.entity.mapper;

import java.util.List;

import com.xingxue.class11.entity.OrderDetail;

public interface OrderDetailMapper {
	
	public List<OrderDetail> selectOrderDetailByOrderId(Long orderId);
	
	
	public List<OrderDetail> selectOrderDetailsByOrderIds(
			List<Long> orderId);
	/**
     * 批量保存订单详细信息
     * @param orderDetails	订单详情集合
     */
	public void batchInsertOrderDetails(List<OrderDetail> orderDetails);

}
