package com.shop.yi.base.category.dao;

import java.util.List;

import com.shop.yi.base.category.dto.CategoryDTO;

public interface CategoryDao {
	/**
	 * 根据类别id查询类别
	 * @Title: queryCategory 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<CategoryDTO>
	 */
	public List<CategoryDTO> queryCategory(String id);
}
