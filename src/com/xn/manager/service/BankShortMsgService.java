package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.BankShortMsgModel;

import java.util.List;

/**
 * @Description:未处理短信Service层
 * @create 2018-09-11 14:40
 **/
public interface BankShortMsgService<T> extends BaseService<T> {
    public List<BankShortMsgModel> querySms(BankShortMsgModel bankShortMsgModel);
}
