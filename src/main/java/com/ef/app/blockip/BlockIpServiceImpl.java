package com.ef.app.blockip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockIpServiceImpl implements BlockIpService {

    @Autowired
    private BlockIpDAO blockIpDAO;

    @Override
    public void saveBlockIp(BlockIpModel blockIpModel) {
        blockIpDAO.saveBlockIp(blockIpModel);
    }
}
