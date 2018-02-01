package com.ef.app.blockip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class BlockIpDAOImpl extends JdbcDaoSupport implements BlockIpDAO {

    private Logger logger = LoggerFactory.getLogger(BlockIpDAOImpl.class);

    @Autowired
    public BlockIpDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    @Override
    public void saveBlockIp(BlockIpModel blockIpModel) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("ip", blockIpModel.getIp());
        mapSqlParameterSource.addValue("comment", blockIpModel.getComment());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        namedParameterJdbcTemplate.update(getSqlInsertBlockIp(), mapSqlParameterSource);
    }

    private String getSqlInsertBlockIp() {
        return "insert into blockip(ip, comment) values(:ip, :comment)";
    }
}
