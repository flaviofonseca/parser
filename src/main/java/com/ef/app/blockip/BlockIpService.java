package com.ef.app.blockip;

import com.ef.app.filelog.FileLogModel;
import com.ef.parser.ArgsHelper;

import java.util.List;

public interface BlockIpService {

    void saveBlockIp(BlockIpModel blockIpModel);

    void saveListBlockIp(List<FileLogModel> fileLogModelList, ArgsHelper argsHelper);
}
