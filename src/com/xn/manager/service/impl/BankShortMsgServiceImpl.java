package com.xn.manager.service.impl;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankDao;
import com.xn.manager.dao.BankShortMsgDao;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.MobileCardDao;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankShortMsgModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.BankService;
import com.xn.manager.service.BankShortMsgService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:未处理短信的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("bankShortMsgService")
public class BankShortMsgServiceImpl<T> extends BaseServiceImpl<T> implements BankShortMsgService<T> {
    private static Logger log=Logger.getLogger(BankShortMsgServiceImpl.class);

    @Autowired
    private BankShortMsgDao bankShortMsgDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankShortMsgDao;
    }

    @Override
    public List<BankShortMsgModel> querySms(BankShortMsgModel bankShortMsgModel) {
        return bankShortMsgDao.querySmsOrderNo(bankShortMsgModel);
    }
}
