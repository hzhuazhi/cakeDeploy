package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankShortMsgModel;

import java.util.List;

/**
 * @Description:未处理短信模板的结果
 * @create 2018-09-11 14:42
 **/
public interface BankShortMsgDao<T> extends BaseDao<T> {
    List <BankShortMsgModel> querySmsOrderNo(BankShortMsgModel bankShortMsgModel);
}
