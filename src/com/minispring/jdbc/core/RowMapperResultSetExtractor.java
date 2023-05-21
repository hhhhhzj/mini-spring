package com.minispring.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * description
 *
 * @author zhijian05.huang
 * @date 2023-05-18 11:07
 */
public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
        List<T> results = new ArrayList<>();
        int rowNum = 0;
        //对结果集，循环调用mapRow进行数据记录映射
        while (rs.next()) {
            results.add(this.rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }
}