package com.ef.app.filelog;

import com.ef.app.blockip.BlockIpService;
import com.ef.exception.BusinesException;
import com.ef.parser.ArgsHelper;
import com.ef.parser.DurationType;
import com.ef.util.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileLogServiceImpl implements FileLogService {

    @Autowired
    private FileLogDAO fileLogDAO;

    @Autowired
    private BlockIpService blockIpService;

    public List<FileLogModel> readFile(String filePath) {

        List<FileLogModel> listFileLogModels = new LinkedList<>();
        FileLogModel fileLogModel;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (br.ready()) {
                String line = br.readLine();
                String parts[] = line.split("\\|");

                loadFileLogModel(listFileLogModels, parts);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new BusinesException("File " + filePath + " not found");
        } catch (IOException e) {
            try {
                throw e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return listFileLogModels;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveFileLogDataBase(Collection<FileLogModel> listFileLogModels) {
        fileLogDAO.deleteFileLog();
        fileLogDAO.saveFileLogDataBase(listFileLogModels);
    }

    private void loadFileLogModel(Collection<FileLogModel> listFileLogModels, String[] parts) {
        FileLogModel fileLogModel = new FileLogModel();
        fileLogModel.setDateLog(UtilDate.converteStringToLocalDateTime(parts[0]));
        fileLogModel.setIp(parts[1]);
        fileLogModel.setRequest(parts[2].replaceAll("\"", ""));
        fileLogModel.setStatus(parts[3]);
        fileLogModel.setUserAgent(parts[4].replaceAll("\"", ""));

        listFileLogModels.add(fileLogModel);
    }

    @Transactional
    @Override
    public void searchAndSaveIPsBlock(ArgsHelper argsHelper) {
        List<FileLogModel> fileLogModelList = this.searchRequestByIp(argsHelper);
        blockIpService.saveListBlockIp(fileLogModelList, argsHelper);
    }

    public List<FileLogModel> searchRequestByIp(ArgsHelper argsHelper) {

        LocalDateTime finalDate = null;
        LocalDateTime startDate = argsHelper.getStartDate();

        if (DurationType.HOURLY.equals(argsHelper.getDuration())) {
            finalDate = UtilDate.addHourToDate(startDate);
        } else if (DurationType.DAILY.equals(argsHelper.getDuration())) {
            finalDate = UtilDate.addDayToDate(startDate);
        }

        return this.fileLogDAO.searchRequestByIp(argsHelper.getThreshold(), startDate, finalDate);
    }

    private String getComment(String ip, String treshold, String startDate) {
        return "The IP " + ip + " was blocked by performing more than "
                + treshold + " access attempts within the period from "
                + startDate + " to ";
    }
}
