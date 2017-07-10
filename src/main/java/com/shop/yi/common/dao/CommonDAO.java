package com.shop.yi.common.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

//@Repository
public class CommonDAO extends SqlSessionDaoSupport {
	
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	/**
	 * 查询列表
	 * @param sql
	 * @param param
	 * @return
	 */
	public List<Map<String, Object>> queryForList(String sql, Object param){
		return this.getSqlSession().selectList(sql, param);
	}
	
	/**
	 * 查询集合
	 * @param sql
	 * @param param
	 * @return
	 */
	public Map<String, Object> queryForMap(String sql, Object param){
		return this.getSqlSession().selectOne(sql, param);
	}
	
	/**
	 * 查询对象
	 * @param sql
	 * @param param
	 * @return
	 */
	public Map<String, Object> queryForObject(String sql, Object param){
		return this.getSqlSession().selectOne(sql, param);
	}
	/**
	 * 插入
	 * @param sql
	 * @param param
	 * @return
	 */
	public int insert(String sql, Object param){
		return this.getSqlSession().insert(sql, param);
	}
	/**
	 * 更新
	 * @param sql
	 * @param param
	 * @return
	 */
	public int update(String sql, Object param){
		return this.getSqlSession().update(sql, param);
	}
	
	/**
	 * 删除
	 * @param sql
	 * @param param
	 * @return
	 */
	public int delete(String sql, Object param){
		return this.getSqlSession().delete(sql, param);
	}
}
