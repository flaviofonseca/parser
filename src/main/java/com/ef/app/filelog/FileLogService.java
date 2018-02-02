package com.ef.app.filelog;

import com.ef.parser.ArgsHelper;

import java.util.Collection;
import java.util.List;

public interface FileLogService {

    List<FileLogModel> readFile(String filePath);

    void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels);

    List<FileLogModel> searchRequestByIp(ArgsHelper argsHelper);

}
