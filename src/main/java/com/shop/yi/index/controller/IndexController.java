package com.shop.yi.index.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.yi.base.advert.dto.AdvertDTO;
import com.shop.yi.base.advert.service.AdvertService;
import com.shop.yi.base.category.dto.CategoryDTO;
import com.shop.yi.base.category.service.CategoryService;
import com.shop.yi.common.util.angularjs.AngularResolve;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @author: yanguo(****@email.com)
 * @date: 2017年5月30日 上午10:52:49
 * @Version: 1.0.0
 */
@Controller
@RequestMapping("/index/")
public class IndexController {
	private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private AdvertService advertService;

	@Autowired
	private CategoryService categoryService;
	/**
	 * @Title: queryIndexData
	 * @Description: TODO
	 * @return: void
	 */
	@RequestMapping("index.do")
	@ResponseBody
	public void queryIndexData(HttpServletRequest request, HttpServletResponse response) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("-----------------------------query index data start-------------------------------------");
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<AdvertDTO> advertList = advertService.queryAllAdvert();
		List<AdvertDTO> noticeList = advertService.queryAllNotice();
		List<CategoryDTO> categoryList = categoryService.queryCategory("0");
		resMap.put("AdvertList", advertList);
		resMap.put("NoticeList", noticeList);
		resMap.put("CategoryList", categoryList);
		AngularResolve.angularHttpDeal(response, resMap);
	}
	/**
	 * @Title: queryIndexData
	 * @Description: TODO
	 * @return: void
	 */
	@RequestMapping("queryCategorySubList.do")
	@ResponseBody
	public void queryCategorySubList(@RequestParam String categoryId, 
			HttpServletRequest request, HttpServletResponse response) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(categoryId+"-----------------------------query queryCategorySubList data start-------------------------------------");
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		List<CategoryDTO> categorySubList = categoryService.queryCategory(categoryId);
		resMap.put("CategorySubList", categorySubList);
		AngularResolve.angularHttpDeal(response, resMap);
	}
}
