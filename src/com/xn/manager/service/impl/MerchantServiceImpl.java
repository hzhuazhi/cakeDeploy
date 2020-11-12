package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.common.util.StringUtil;
import com.xn.manager.dao.MerchantDao;
import com.xn.manager.dao.MerchantWithdrawDao;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantWithdrawModel;
import com.xn.manager.service.MerchantService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:卡商扩充数据表的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("merchantService")
public class MerchantServiceImpl<T> extends BaseServiceImpl<T> implements MerchantService<T> {
    private static Logger log   =  Logger.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantDao merchantDao;


    @Autowired
    private MerchantWithdrawDao merchantWithdrawDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantDao;
    }

    @Override
    public List<MerchantModel> queryNotAllList(MerchantModel merchantModel) {
        return merchantDao.queryNotAllList(merchantModel);
    }

    @Override
    public List<MerchantModel> queryListInfo(List<MerchantModel> dataList) {
        for(MerchantModel merchantModel:dataList){
            MerchantWithdrawModel queryBean = new MerchantWithdrawModel();
            queryBean.setMerchantId(merchantModel.getId());
            queryBean.setCheckStatus(3);
            MerchantWithdrawModel merchantWithdrawModel = merchantWithdrawDao.queryByCountMoney(queryBean);
            if(merchantWithdrawModel==null||merchantWithdrawModel.getMoney().equals("null")||merchantWithdrawModel.getMoney().equals("0")){
                merchantModel.setAvailableMoney(merchantModel.getProfit());
            }else{
               String profit = merchantModel.getProfit().equals("") ? "0":merchantModel.getProfit();
               String availableMoney = StringUtil.getBigDecimalSubtractStr(profit,merchantWithdrawModel.getMoney());
               merchantModel.setAvailableMoney(availableMoney);
            }
        }
        return dataList;
    }
}
