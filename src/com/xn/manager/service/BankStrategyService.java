package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.BankStrategyModel;

/**
 * @Description:银行卡放量策略的Service层
 * @create 2018-09-11 14:40
 **/
public interface BankStrategyService<T> extends BaseService<T> {

    /***
     * 将银行卡表导入到放量策略里面
     */
    public  void   importBankStrategy();

    /***
     * 查询最大的时间的放量信息
     */
    public BankStrategyModel queryMaxupdateTime();

    /**
     * 批量修改策略
     * @param bankStrategyModel
     * @return
     */
    public int  updateBatch(BankStrategyModel  bankStrategyModel);
}
