package com.ef.app.blockip;

import com.ef.app.filelog.FileLogModel;
import com.ef.parser.ArgsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockIpServiceImpl implements BlockIpService {

    @Autowired
    private BlockIpDAO blockIpDAO;

    @Override
    public void saveBlockIp(BlockIpModel blockIpModel) {
    }

    @Transactional
    @Override
    public void saveListBlockIp(List<FileLogModel> fileLogModelList, ArgsHelper argsHelper) {

        FunctionalTransformFileLogModelToBlockIp funcionalBlockIp = f -> this.fileLogModelToBlockIpModel(f, argsHelper);
        List<BlockIpModel> blockIpModelList = fileLogModelList.stream()
                .map(funcionalBlockIp)
                .collect(Collectors.toList());

        blockIpDAO.saveBlockIp(blockIpModelList);

    }

    private BlockIpModel fileLogModelToBlockIpModel(FileLogModel f, ArgsHelper argsHelper) {
        BlockIpModel blockIpModel = new BlockIpModel();
        blockIpModel.setIp(f.getIp());
        blockIpModel.setComment(this.getComment(f.getIp(), argsHelper));
        return blockIpModel;
    }

    private String getComment(String ip, ArgsHelper argsHelper) {
        return "The ip " + ip + " was blocked due " +
                "to exceeding the number of requests " + argsHelper.getThreshold() +
                " in the period from " + argsHelper.getStartDate() +
                " to " + argsHelper.getFinalDate();
    }


}
