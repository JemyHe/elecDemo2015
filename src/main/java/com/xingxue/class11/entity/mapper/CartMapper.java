package com.xingxue.class11.entity.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xingxue.class11.entity.Cart;

public interface CartMapper {

	public List<Cart> selectByUserId(Long userId);

	public Cart selectByUserIdAndSkuId(@Param("userId") Long userId, @Param("skuId") Long skuId);

	public List<Cart> selectByIds(List<Long> ids);

	public Long countCartSize(Long userId);

	public void updateCartDealCount(@Param("cartId") Long chartId,@Param("step") int step);

	public void insert(Cart cart);

	public void updateCountById(@Param("count") int count, @Param("updateTime") Date updateTime);

	public void deleteById(Long cartId);
}
