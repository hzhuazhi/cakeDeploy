package com.xn.manager.service.impl;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.common.util.StringUtil;
import com.xn.manager.dao.*;
import com.xn.manager.model.*;
import com.xn.manager.service.BankService;
import com.xn.manager.service.InterestService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:利益分配者的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("interestService")
public class InterestServiceImpl<T> extends BaseServiceImpl<T> implements InterestService<T> {
    private static Logger log=Logger.getLogger(InterestServiceImpl.class);

    @Autowired
    private InterestDao interestDao;

    @Autowired
    private InterestWithdrawDao interestWithdrawDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return interestDao;
    }

    @Override
    public List<InterestModel> queryListInfo(List<InterestModel> dateList) {
        for(InterestModel interestModel:dateList){
            InterestWithdrawModel queryBean = new InterestWithdrawModel();
            queryBean.setInterestId(interestModel.getId());
            queryBean.setCheckStatus(3);
            InterestWithdrawModel merchantWithdrawModel = interestWithdrawDao.queryByCountMoney(queryBean);
            if(merchantWithdrawModel==null||merchantWithdrawModel.getMoney().equals("null")||merchantWithdrawModel.getMoney().equals("0")){
                interestModel.setAvailableMoney(interestModel.getBalance());
            }else{
                String profit = interestModel.getBalance().equals("") ? "0":interestModel.getBalance();
                String availableMoney = StringUtil.getBigDecimalSubtractStr(profit,merchantWithdrawModel.getMoney());
                interestModel.setAvailableMoney(availableMoney);
            }
        }

        return dateList;
    }
}
