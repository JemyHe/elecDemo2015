package com.xingxue.class11.entity.mapper;

import java.util.List;

import com.xingxue.class11.entity.DealCategory;
import com.xingxue.class11.framework.page.Search;

public interface DealCategoryMapper {

	public List<DealCategory> selectAllWithoutDeleted();

	public List<DealCategory> selectDirectSubs(Long parentId);

	public DealCategory selectByPrimaryKey(Long id);

	public DealCategory selectByUrlName(String urlName);

	public List<DealCategory> selectPageCategories(Search search);

	public Long countPageCategories(Search search);

	public int insertSelective(DealCategory category);

	public int updateByPrimaryKeySelective(DealCategory category);

	public int deleteByPrimaryKey(Long id);

	public int selectMaxOrderByParentId(Long parentId);

	List<DealCategory> testCategories();
}
