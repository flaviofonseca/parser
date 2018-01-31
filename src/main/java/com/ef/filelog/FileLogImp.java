package com.ef.filelog;

import com.ef.parser.ArgsHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FileLogImp {
    public void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels);
    List<Map<String, Object>> searchRequestByIp(ArgsHelper argsHelper);
}
