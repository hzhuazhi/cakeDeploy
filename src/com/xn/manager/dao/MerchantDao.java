package com.xn.manager.dao;

import com.xn.common.dao.BaseDao;
import com.xn.manager.model.MerchantModel;

import java.util.List;

/**
 * @Description:卡商扩充数据表
 * @create 2018-09-11 14:42
 **/
public interface MerchantDao<T> extends BaseDao<T> {
   List<MerchantModel> queryNotAllList(MerchantModel merchantModel);
}
