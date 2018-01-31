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

    private FileLogService readFileLogService;

    @Autowired
    public ParseApplication(FileLogService readFileLogService) {
        this.readFileLogService = readFileLogService;
    }

    public void execute(String args[]) {
        try {

            ArgsHelper argsHelper = new ArgsHelper(args);

            Collection<FileLogModel> listFileLogModels = readFileLogService.readFile(argsHelper.getFilePath());
            readFileLogService.saveFileLogDataBase(listFileLogModels);
            List<Map<String, Object>> listIp = readFileLogService.searchRequestByIp(argsHelper);

            System.out.println("IP find.");
            listIp.forEach(ip -> System.out.println(ip.get("ip")));

        } catch (BusinesException businesException) {
            System.out.println(businesException.getMessage());
        }
    }
}
