package com.xn.manager.service;

import com.xn.common.service.BaseService;
import com.xn.manager.model.MerchantModel;

import java.util.List;

/**
 * @Description:卡商扩充数据表的Service层
 * @create 2018-09-11 14:40
 **/
public interface MerchantService<T> extends BaseService<T> {
   List<MerchantModel> queryNotAllList(MerchantModel  merchantModel);

   /**
    * 处理金额
    * @param dateList
    * @return
    */
   List<MerchantModel> queryListInfo(List<MerchantModel> dateList);

}
