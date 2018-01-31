package com.ef.execute;

import com.ef.exception.BusinesException;
import com.ef.filelog.FileLogModel;
import com.ef.filelog.FileLogService;
import com.ef.parser.ArgsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class ParseApplication {

    @Autowired
    private FileLogService fileLogService;

    public void execute(String args[]) {
        try {

            ArgsHelper argsHelper = new ArgsHelper(args);

            Collection<FileLogModel> listFileLogModels = fileLogService.readFile(argsHelper.getFilePath());
            fileLogService.saveFileLogDataBase(listFileLogModels);

            System.out.println("looking for log in the period.");
            List<Map<String, Object>> listIp = fileLogService.searchRequestByIp(argsHelper);

            System.out.println("IPs found.");
            listIp.forEach(ip -> System.out.println(ip.get("ip")));

        } catch (BusinesException businesException) {
            System.out.println(businesException.getMessage());
        }
    }
}
