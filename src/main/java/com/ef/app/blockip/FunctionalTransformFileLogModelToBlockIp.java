package com.ef.app.blockip;

import com.ef.app.filelog.FileLogModel;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionalTransformFileLogModelToBlockIp extends Function<FileLogModel, BlockIpModel> {

}