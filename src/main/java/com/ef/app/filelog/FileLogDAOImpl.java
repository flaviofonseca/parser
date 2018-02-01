package com.ef.app.filelog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(FileLogDAOImpl.class);

    @Autowired
    public FileLogDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels) {

        logger.info("Wait... inserting log into database");
        FunctionalFileLog functionalFileLog = f -> this.getMapSqlParameterSourceFileLogModel(f);
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[listFileLogModels.size()];

        listFileLogModels
                .stream()
                .map(functionalFileLog)
                .collect(Collectors.toList())
                .toArray(sqlParameterSources);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        namedParameterJdbcTemplate.batchUpdate(this.getSqlInsertlFileLog(), sqlParameterSources);
        logger.info("insert process finished...");
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

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        return namedParameterJdbcTemplate.queryForList(sql.toString(), sqlParameterSource);
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
        logger.info("Wait, deleting log on database...");
        this.getJdbcTemplate().execute("truncate filelog");
        logger.info("End process. log deleted");
    }
}
