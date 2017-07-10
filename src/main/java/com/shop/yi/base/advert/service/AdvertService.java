package com.shop.yi.base.advert.service;

import java.util.List;

import com.shop.yi.base.advert.dto.AdvertDTO;

/** 
 * @ClassName: AdvertService 
 * @Description: 广告公告基础服务
 * @author: yanguo(****@email.com)
 * @date: 2017年6月21日 上午12:10:33  
 * @Version: 1.0.0
 */
public interface AdvertService {
	
	/**
	 * 查询所有广告
	 * @Title: queryAllAdvert 
	 * @Description: TODO
	 * @return
	 * @return: List<AdvertDTO>
	 */
	public List<AdvertDTO> queryAllAdvert();
	
	/**
	 * 查询所有的公告
	 * @Title: queryAllNotice 
	 * @Description: TODO
	 * @return
	 * @return: List<AdvertDTO>
	 */
	public List<AdvertDTO> queryAllNotice();
}
