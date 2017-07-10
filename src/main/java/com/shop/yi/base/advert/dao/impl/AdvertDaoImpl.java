package com.shop.yi.base.advert.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shop.yi.base.advert.dao.AdvertDao;
import com.shop.yi.base.advert.dto.AdvertDTO;
import com.shop.yi.common.dao.ibatis.CommonDAO;
import com.shop.yi.common.util.bean2map.BeanUtils;

/** 
 * @ClassName: AdvertDaoImpl 
 * @Description: TODO
 * @author: yanguo(****@email.com)
 * @date: 2017年6月21日 上午12:20:34  
 * @Version: 1.0.0
 */
@Repository("advertDAO")
public class AdvertDaoImpl extends CommonDAO implements AdvertDao {
	/**
	 * 通过类别查询出公告还是广告
	 * @Title: queryAllAdvert
	 * @Description: TODO
	 * @param type 公告/广告  类别
	 * @param queryNum 查询条数
	 * @return
	 * @return: List<AdvertDTO>
	 */
	@Override
	public List<AdvertDTO> queryAllAdvert(String type, int queryNum) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AdvType", type);
		param.put("QueryNum", queryNum);
		List<Map<String, Object>> advertList = this.queryForList("advert.queryAllAdvertByType", param);
		return BeanUtils.listMap2ListBean(advertList, AdvertDTO.class);
	}
}
