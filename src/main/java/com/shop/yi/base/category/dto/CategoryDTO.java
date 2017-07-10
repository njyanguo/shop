package com.shop.yi.base.category.dto;

import java.util.List;

/** 
 * @ClassName: CategoryDTO 
 * @Description: 商品类别基础类
 * @author: yanguo(****@email.com)
 * @date: 2017年6月27日 下午11:32:39  
 * @Version: 1.0.0
 */
public class CategoryDTO {
	/**
	 * 类别id
	 */
	private String categoryId;
	/**
	 * 类别名称
	 */
	private String categoryName;
	/**
	 * 子类类别
	 */
	private List<CategoryDTO> categorySubList;
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<CategoryDTO> getCategorySubList() {
		return categorySubList;
	}
	public void setCategorySubList(List<CategoryDTO> categorySubList) {
		this.categorySubList = categorySubList;
	}
	
	
}
