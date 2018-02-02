package com.ef.execute;

import com.ef.exception.BusinesException;
import com.ef.app.filelog.FileLogModel;
import com.ef.app.filelog.FileLogService;
import com.ef.parser.ArgsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ParseApplication {

    @Autowired
    private FileLogService fileLogService;

    private Logger logger = LoggerFactory.getLogger(ParseApplication.class);


    public void execute(String args[]) {
        try {

            ArgsHelper argsHelper = new ArgsHelper(args);

            Collection<FileLogModel> listFileLogModels = fileLogService.readFile(argsHelper.getFilePath());
            fileLogService.saveFileLogDataBase(listFileLogModels);

            logger.info("Searching IPs...\n");
            List<FileLogModel> listIp = fileLogService.searchRequestByIp(argsHelper);

            logger.info("IPs found in the period.");
            listIp.forEach(ip -> logger.info(ip.getIp()));

        } catch (BusinesException businesException) {
            logger.error(businesException.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
