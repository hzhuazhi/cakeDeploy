package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.CardsBankModel;
import com.xn.system.entity.Account;

import java.util.Map;

/**
 * @Description:手机卡所有短信内容数据Service层
 * @create 2018-09-11 14:40
 **/
public interface CardsBankService<T> extends BaseService<T> {
    public Map<String, Object> isCheckCardsBank(CardsBankModel  cardsBankModel, Account account);
}
