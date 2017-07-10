package com.shop.yi.base.category.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shop.yi.base.category.dao.CategoryDao;
import com.shop.yi.base.category.dto.CategoryDTO;
import com.shop.yi.common.dao.ibatis.CommonDAO;
import com.shop.yi.common.util.bean2map.BeanUtils;

/** 
 * @ClassName: CategoryDaoImpl 
 * @Description: 
 * @author: yanguo(****@email.com)
 * @date: 2017年6月27日 下午11:42:35  
 * @Version: 1.0.0
 */
@Repository("categoryDAO")
public class CategoryDaoImpl extends CommonDAO implements CategoryDao{
	
	@Override
	public List<CategoryDTO> queryCategory(String id) {
		String sql = "category.queryAllCategoryById";
		List<Map<String, Object>> categoryList = this.queryForList(sql, id);
		return BeanUtils.listMap2ListBean(categoryList, CategoryDTO.class);
	}
}
