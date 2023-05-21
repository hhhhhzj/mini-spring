package com.minispring.batis;

import com.minispring.jdbc.core.JdbcTemplate;
import com.minispring.jdbc.core.StatementCallback;

public class DefaultSqlSession implements SqlSession {

	private JdbcTemplate jdbcTemplate;

	private SqlSessionFactory sqlSessionFactory;

	@Override
	public Object selectOne(String sqlid, Object[] args, StatementCallback pstmtcallback) {
		System.out.println(sqlid);
		String sql = this.sqlSessionFactory.getMapperNode(sqlid).getSql();
		System.out.println(sql);

		return jdbcTemplate.query(sql, args, pstmtcallback);
	}

	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return this.sqlSessionFactory;
	}
	
	private void buildParameter(){
	}
	
	private Object resultSet2Obj() {
		return null;
	}

}
