package com.ef.app.filelog;

import org.springframework.jdbc.core.ColumnMapRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class FileLogRowMapper extends ColumnMapRowMapper {

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        return super.mapRow(rs, rowNum);
    }
}
