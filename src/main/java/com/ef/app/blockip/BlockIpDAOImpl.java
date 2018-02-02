package com.ef.app.blockip;

import com.ef.app.filelog.FunctionalFileLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockIpDAOImpl extends JdbcDaoSupport implements BlockIpDAO {

    private Logger logger = LoggerFactory.getLogger(BlockIpDAOImpl.class);

    @Autowired
    public BlockIpDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void saveBlockIp(List<BlockIpModel> blockIpModel) {

        FunctionalTransformBlockIpToSqlParameterSource functionalFileLog = f -> this.getMapSqlParameterSourceFileLogModel(f);
        SqlParameterSource[] sqlParameterSources = new SqlParameterSource[blockIpModel.size()];

        blockIpModel
                .stream()
                .map(functionalFileLog)
                .collect(Collectors.toList())
                .toArray(sqlParameterSources);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        namedParameterJdbcTemplate.batchUpdate(getSqlInsertBlockIp(), sqlParameterSources);
    }

    private MapSqlParameterSource getMapSqlParameterSourceFileLogModel(BlockIpModel blockIpModel) {
        return new MapSqlParameterSource()
                .addValue("ip", blockIpModel.getIp())
                .addValue("comment", blockIpModel.getComment());
    }

    private String getSqlInsertBlockIp() {
        return "insert into blockip(ip, comment) values(:ip, :comment)";
    }

}
