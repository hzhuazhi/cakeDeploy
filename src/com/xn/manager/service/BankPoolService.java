package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankPoolModel;
import com.xn.manager.model.ChannelBankModel;
import com.xn.system.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * @Description:银行卡池子Service层
 * @create 2018-09-11 14:40
 **/
public interface BankPoolService<T> extends BaseService<T> {
    public List<BankPoolModel> byIdQueryBank(BankPoolModel bankPoolModel);
}
