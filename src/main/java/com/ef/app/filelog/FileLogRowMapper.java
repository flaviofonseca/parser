package com.ef.app.filelog;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FileLogRowMapper implements RowMapper<FileLogModel> {
    @Override
    public FileLogModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        FileLogModel fileLogModel = new FileLogModel();
        Optional<String> optional = getValueStringResultSet(rs, "ip");
        fileLogModel.setIp(optional.isPresent() ? optional.get() : null);
        return fileLogModel;
    }

    private Optional<String> getValueStringResultSet(ResultSet rs, String nameColumn) {
        String result = null;
        try {
            result = rs.getString(nameColumn);
        } catch (SQLException sq) {
        }

        return Optional.of(result);
    }
}
