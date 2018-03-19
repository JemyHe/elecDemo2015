package com.xingxue.class11.entity.mapper;

import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.entity.DealDetail;

import java.util.List;

public interface DealDetailMapper {

	public DealDetail selectByDealId(Long dealId);

	public DealDetail selectById(int id);

	public int insertDealDetail(DealDetail dealDetail);

	public int updateById(DealDetail dealDetail);

	public int deleteById(Long id);

}
