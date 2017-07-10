package com.shop.yi.base.category.service;

import java.util.List;

import com.shop.yi.base.category.dto.CategoryDTO;

/** 
 * @ClassName: CategoryService 
 * @Description: 
 * @author: yanguo(****@email.com)
 * @date: 2017年6月27日 下午11:44:03  
 * @Version: 1.0.0
 */
public interface CategoryService {
	/**
	 * 
	 * @Title: queryCategory 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<CategoryDTO>
	 */
	public List<CategoryDTO> queryCategory(String id);
}
