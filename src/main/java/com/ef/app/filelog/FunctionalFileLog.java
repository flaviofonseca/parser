package com.ef.app.filelog;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionalFileLog extends Function<FileLogModel, SqlParameterSource> {

    @Override
    SqlParameterSource apply(FileLogModel fileLogModel);
}
