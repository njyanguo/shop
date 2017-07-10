package com.shop.yi.common.dao.ibatis;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("deprecation")
@Repository
public class CommonDAO extends SqlMapClientDaoSupport {
	private static Logger LOGGER = LoggerFactory.getLogger(CommonDAO.class);
	
	@Resource(name = "sqlMapClient")
	private SqlMapClient sqlMapClient;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClient(sqlMapClient);
	}
	
	/**
	 * 查询列表
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForList(String sql, Object param){
		List<Map<String, Object>> resultList = null;
		try {
			resultList = getSqlMapClient().queryForList(sql, param);
		} catch (SQLException e) {
			logDebug("queryForList is exception:", e);
		}
		return resultList;
	}
	
	/**
	 * 查询列表
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForList(String sql){
		List<Map<String, Object>> resultList = null;
		try {
			resultList = getSqlMapClient().queryForList(sql);
		} catch (SQLException e) {
			logDebug("queryForList is exception:", e);
		}
		return resultList;
	}
	
	/**
	 * 查询集合
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryForMap(String sql, Object param){
		Map<String, Object> sqlMap = null;
		try {
			sqlMap = (Map<String, Object>) getSqlMapClient().queryForObject(sql, param);
		} catch (SQLException e) {
			logDebug("", e);
		}
		return sqlMap;
	}

	
	
	/**
	 * 查询对象
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public Object queryForObject(String sql, Object param) {
		Object sqlMap = null;
		try {
			sqlMap = getSqlMapClient().queryForObject(sql, param);
		} catch (SQLException e) {
			logDebug("", e);
		}
		return sqlMap;
	}
	/**
	 * 插入
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	public int insert(String sql, Object param){
		int num = 0;
		try {
			num = (Integer) this.getSqlMapClient().insert(sql, param);
		} catch (SQLException e) {
			logDebug("", e);
		}
		return num;
	}
	/**
	 * 更新
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	public int update(String sql, Object param){
		int num = 0;
		try {
			num =  this.getSqlMapClient().update(sql, param);
		} catch (SQLException e) {
			logDebug("", e);
		}
		return num;
	}
	
	/**
	 * 删除
	 * @param sql
	 * @param param
	 * @return
	 * @throws SQLException 
	 */
	public int delete(String sql, Object param){
		int num = 0;
		try {
			num = this.getSqlMapClient().delete(sql, param);
		} catch (SQLException e) {
			logDebug("", e);
		}
		return num;
	}
	/**
	 * 
	 * @Title: logDebug 
	 * @Description: TODO
	 * @param e
	 * @return: void
	 */
	private void logDebug(String info, Exception e) {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug(info, e);
		}
	}
}
