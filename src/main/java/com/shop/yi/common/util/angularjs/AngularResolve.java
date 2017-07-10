package com.shop.yi.common.util.angularjs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.yi.common.util.json.JSONUtils;

/** 
 * @ClassName: AngularResolve 
 * @Description: 处理angularjs相关的问题类
 * @author: yanguo(****@email.com)
 * @date: 2017年6月21日 下午11:36:41  
 * @Version: 1.0.0
 */
public class AngularResolve {
	private static Logger logger = LoggerFactory.getLogger(AngularResolve.class);
	/**
	 * 处理angularjs使用$http时页面报错问题（406）
	 * @Title: angularHttpDeal 
	 * @Description: TODO
	 * @param response
	 * @param resMap
	 * @return: void
	 */
	public static void angularHttpDeal(HttpServletResponse response, Map<String, Object> resMap) {
		try {
			// 设置页面不缓存
			response.setContentType("application/json");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print(JSONUtils.toJSONString(resMap));
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.info("response exception:", e);
		}
	}
}
