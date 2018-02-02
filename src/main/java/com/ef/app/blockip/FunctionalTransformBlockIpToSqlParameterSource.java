package com.ef.app.blockip;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionalTransformBlockIpToSqlParameterSource extends Function<BlockIpModel, SqlParameterSource> {

    @Override
    SqlParameterSource apply(BlockIpModel fileLogModel);
}
