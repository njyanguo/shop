package com.shop.yi.base.advert.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.yi.base.advert.dao.AdvertDao;
import com.shop.yi.base.advert.dto.AdvertDTO;
import com.shop.yi.base.advert.service.AdvertService;

/**
 * @ClassName: AdvertServiceImpl
 * @Description: 广告公告基础服务类
 * @author: yanguo(****@email.com)
 * @date: 2017年6月21日 上午12:08:36
 * @Version: 1.0.0
 */
@Service("advertService")
public class AdvertServiceImpl implements AdvertService {
	/**公告广告DAO层*/
	@Resource(name = "advertDAO")
	private AdvertDao advertDao;
	/**
	 * 查询所有广告
	 * @Title: queryAllAdvert 
	 * @Description: TODO
	 * @return
	 * @return: List<AdvertDTO>
	 */
	@Override
	public List<AdvertDTO> queryAllAdvert() {
		String type = "A";
		return advertDao.queryAllAdvert(type, 4);
	}
	/**
	 * 查询所有的公告
	 * @Title: queryAllNotice 
	 * @Description: TODO
	 * @return
	 * @return: List<AdvertDTO>
	 */
	@Override
	public List<AdvertDTO> queryAllNotice() {
		String type = "N";
		return advertDao.queryAllAdvert(type, 5);
	}
}
