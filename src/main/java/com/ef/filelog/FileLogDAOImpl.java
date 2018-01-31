package com.ef.filelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FileLogDAOImpl extends JdbcDaoSupport implements FileLogDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public FileLogDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels) {

        System.out.println("Waiting... insert log into database");
        FunctionalFileLog functionalFileLog = f -> this.getMapSqlParameterSourceFileLogModel(f);
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[listFileLogModels.size()];

        listFileLogModels
                .stream()
                .map(functionalFileLog)
                .collect(Collectors.toList())
                .toArray(sqlParameterSources);

        this.namedParameterJdbcTemplate.batchUpdate(this.getSqlInsertlFileLog(), sqlParameterSources);
        System.out.println("end process insert...");
    }

    public List<Map<String, Object>> searchRequestByIp(Integer threshold,
                                                       LocalDateTime startDate,
                                                       LocalDateTime finalDate) {

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("startDate", startDate);
        sqlParameterSource.addValue("finalDate", finalDate);
        sqlParameterSource.addValue("threshold", threshold);

        StringBuilder sql = new StringBuilder();
        sql.append("select ip from filelog ");
        sql.append("where date_request between :startDate and :finalDate ");
        sql.append("group by ip ");
        sql.append("having count(*) >= :threshold ");

        return this.namedParameterJdbcTemplate.queryForList(sql.toString(), sqlParameterSource);
    }

    private MapSqlParameterSource getMapSqlParameterSourceFileLogModel(FileLogModel f) {
        return new MapSqlParameterSource()
                .addValue("data", f.getDateLog())
                .addValue("ip", f.getIp())
                .addValue("status", f.getStatus())
                .addValue("request", f.getRequest())
                .addValue("userAgent", f.getUserAgent());
    }

    private String getSqlInsertlFileLog() {
        return new StringBuilder("insert into filelog (date_request, ip, status, request, user_agent) ")
                .append("values (:data, :ip, :status, :request, :userAgent)").toString();
    }

    public void deleteFileLog() {
        System.out.println("Waiting... delete log into database");
        this.getJdbcTemplate().execute("delete from filelog");
        System.out.println("End process... delete log into database");
    }
}
