package com.shop.yi.base.advert.dao;

import java.util.List;

import com.shop.yi.base.advert.dto.AdvertDTO;

/**
 * @ClassName: AdvertDao
 * @Description: 广告公告数据持久层
 * @author: yanguo(****@email.com)
 * @date: 2017年6月21日 上午12:15:54
 * @Version: 1.0.0
 */
public interface AdvertDao {
	/**
	 * 通过类别查询出公告还是广告
	 * @Title: queryAllAdvert
	 * @Description: TODO
	 * @param type 公告/广告  类别
	 * @param queryNum 查询条数
	 * @return
	 * @return: List<AdvertDTO>
	 */
	public List<AdvertDTO> queryAllAdvert(String type, int queryNum);
}
