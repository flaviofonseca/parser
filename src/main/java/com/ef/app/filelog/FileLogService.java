package com.ef.app.filelog;

import com.ef.parser.ArgsHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FileLogService {

    Collection<FileLogModel> readFile(String filePath);

    void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels);

    List<Map<String,Object>> searchRequestByIp(ArgsHelper argsHelper);
}