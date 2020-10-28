package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.BankModel;

import java.util.List;

/**
 * @Description:银行卡
 * @create 2018-09-11 14:42
 **/
public interface BankDao<T> extends BaseDao<T> {
   public List<BankModel> queryAllListImport(BankModel bankModel);
}
