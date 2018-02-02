package com.ef.app.blockip;

import com.ef.app.filelog.FileLogModel;
import com.ef.parser.ArgsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
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

        FuncionalBlockIp funcionalBlockIp = f -> this.fileLogModelToBlockIpModel(f);
        List<BlockIpModel> blockIpModelList = fileLogModelList.stream()
                                                                .map(funcionalBlockIp)
                                                                .collect(Collectors.toList());

        blockIpDAO.saveBlockIp(blockIpModelList);

    }

    private BlockIpModel fileLogModelToBlockIpModel(FileLogModel f) {
        return null;
    }

    private interface FuncionalBlockIp extends Function<FileLogModel, BlockIpModel> {

    }

}
