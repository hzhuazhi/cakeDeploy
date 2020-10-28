package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.BankCollectionModel;

import java.util.List;

/**
 * @Description  查询卡成功信息service
 * @Date 2020/9/17 20:53
 * @Version 1.0
 */
public interface BankCollectionService <T> extends BaseService<T> {

    public List<BankCollectionModel> queryBankCollection(BankCollectionModel  bankCollectionModel);

    /**
     * 查询卡的汇总信息
     * @param bankCollectionModel
     * @return
     */
    public BankCollectionModel queryCountInfo(BankCollectionModel  bankCollectionModel);
}
