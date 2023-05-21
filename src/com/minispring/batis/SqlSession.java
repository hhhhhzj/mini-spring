package com.minispring.batis;

import com.minispring.jdbc.core.JdbcTemplate;
import com.minispring.jdbc.core.StatementCallback;

public interface SqlSession {
	void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
	Object selectOne(String sqlid, Object[] args, StatementCallback pstmtcallback);
}
