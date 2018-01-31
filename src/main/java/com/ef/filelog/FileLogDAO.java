package com.ef.filelog;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FileLogDAO {
    void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels);

    List<Map<String, Object>> searchRequestByIp(Integer threshold,
                                                LocalDateTime startDate,
                                                LocalDateTime finalDate);

    void deleteFileLog();
}
