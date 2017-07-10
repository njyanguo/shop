package com.shop.yi.base.category.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.yi.base.category.dao.CategoryDao;
import com.shop.yi.base.category.dto.CategoryDTO;
import com.shop.yi.base.category.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Resource(name = "categoryDAO")
	private CategoryDao categoryDao;
	
	@Override
	public List<CategoryDTO> queryCategory(String id) {
		List<CategoryDTO> categorySubList = categoryDao.queryCategory(id);
		if(categorySubList!= null && !categorySubList.isEmpty()){
			for(int index = 0; index< categorySubList.size(); index++){
				CategoryDTO dto = categorySubList.get(index);
				String subId = dto.getCategoryId();
				dto.setCategorySubList(categoryDao.queryCategory(subId));
			}
		}
		return categorySubList;
	}
}
