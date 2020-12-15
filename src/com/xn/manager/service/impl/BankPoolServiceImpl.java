package com.xn.manager.service.impl;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.BankDao;
import com.xn.manager.dao.BankPoolDao;
import com.xn.manager.dao.BankTypeDao;
import com.xn.manager.dao.MobileCardDao;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.BankTypeModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.BankPoolService;
import com.xn.manager.service.BankService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:银行卡池子的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("bankPoolService")
public class BankPoolServiceImpl<T> extends BaseServiceImpl<T> implements BankPoolService<T> {
    private static Logger log=Logger.getLogger(BankPoolServiceImpl.class);

    @Autowired
    private BankPoolDao bankPoolDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return bankPoolDao;
    }

}
