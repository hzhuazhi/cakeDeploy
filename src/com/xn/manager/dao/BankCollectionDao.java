package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.BankCollectionModel;

import java.util.List;

/**
 * @Description 查询卡的成功数据
 * @Date 2020/9/17 20:49
 * @Version 1.0
 */
public interface BankCollectionDao <T> extends BaseDao<T> {

    /**
     * 查询当天卡的成功信息
     * @param bankCollectionModel
     * @return
     */
    public List<BankCollectionModel> queryBankCardCountInfo(BankCollectionModel  bankCollectionModel);

    /**
     * 根据条件查询汇总消息
     * @param bankCollectionModel
     * @return
     */
    public   BankCollectionModel queryCountInfo(BankCollectionModel  bankCollectionModel);
}
