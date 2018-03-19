package com.xingxue.class11.entity.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xingxue.class11.entity.Deal;
import com.xingxue.class11.framework.page.Search;

public interface DealMapper {
	
	/**
     *  根据地区，一组商品类别，状态查询商品
     * @param categoryIds
     * @param areaId
     * @param publishStatus
     * @return
     */
	public List<Deal> selectOnIndex(
			@Param("areaId") Long areaId,
			@Param("publishStatus") int publishStatus,
			@Param("categoryIds") List<Long> categoryIds);

	/**
     *  根据一组商品id查询
     * @param ids
     * @return
     */
	public List<Deal> selectForCart(@Param("ids") List<Long> ids);
	
	/**
     * 根据ID查询商品信息
     * @param id
     * @return
     */
	public Deal selectByPrimaryKey(Long id);
	
	/**
     * 根据SkuId获取存在的商品记录
     * @param skuId
     * @return
     */
	public Deal selectBySkuId(Long skuId);
	
	/**
     * 根据SkuId获取存在的商品ID
     * @param skuId
     * @return
     */
	public Long selectIdBySkuId(Long skuId);
	
	/**
     * 根据skuId查询可以显示在前台的商品
     * @param skuId
     * @return
     */
	public Deal selectBySkuIdForShowOnView(Long skuId);
	
	/**
     * 根据类别查询商品
     * @param categoryId
     * @return
     */
	public List<Deal> selectByCategoryId(Long categoryId);
	
	/**
     * 根据类别查询商品数量
     * @param categoryId
     * @return
     */
	public Long countByCategoryId(Long categoryId);
	
	/**
     * 根据商家编码查询商品
     * @param merchantCode
     * @return
     */
	public Deal selectByMerchantCode(String merchantCode);
	
	/**
     * 查询最新发布的商品
     * @return
     */
	public Deal selectLatestPublishedDeal();
	
	public List<Deal> selectPageDeals(Search search);
	
	public Long countPageDeals(Search search);
	
	/**
     * 更新商品信息
     * @param deal
     * @return
     */
	public int updateById(Deal deal);
	
	/**
     * 设置已卖光和可售
     * @param deal
     * @return
     */
	public int modifyOosStatusById(@Param("id") Long id,
			@Param("oosStatus") int oosStatus,
			@Param("updateTime") Date updateTime);
	
	/**
     * 下单减库存
     * @param id
     * @param inventoryAmount 更新库存数量
     * @param vendibilityAmount 更新可购买数量
     * @return
     */
	public int updateForPlaceOrder(
			@Param("id") Long id,
			@Param("inventoryAmount") int inventoryAmount,
			@Param("vendibilityAmount") int vendibilityAmount);
	
	/**
     * 保存商品信息
     * @param deal
     * @return
     */
	public void insertDeal(Deal deal);
	
	/**
     * 根据一组SkuId获取存在的商品记录
     * @param skuIds
     * @return
     */
	public List<Deal> selectBySkuIds(@Param("skuIds") List<Long> skuIds);

	/**
     * 查询SkuId最大的商品
     * @return
     */
	public Deal selectMaxSkuId();
	
	/**
     * 获取要下单的商品信息
     * @param id
     * @return
     */
	public Deal selectForPlaceOrder(Long id);
}
