package com.ef.app.filelog;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface FileLogDAO {
    void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels);

    List<FileLogModel> searchRequestByIp(Integer threshold,
                                         LocalDateTime startDate,
                                         LocalDateTime finalDate);

    void deleteFileLog();
}
