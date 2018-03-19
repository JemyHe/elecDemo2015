package com.xingxue.class11.entity.mapper;

import java.util.List;

import com.xingxue.class11.entity.Area;

public interface AreaMapper {
	
	public List<Area> selectByParentId(Long parentId);
	
	public Area selectByName(String name);
	
	public void insertSelective(Area area);
	
	public void updateByPrimaryKeySelective(Area area);
	
	public void deleteById(Long id);
	
	public List<Area> selectAll();

}
