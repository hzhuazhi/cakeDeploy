package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.AdminWithdrawDao;
import com.xn.manager.model.AdminWithdrawModel;
import com.xn.manager.service.AdminWithdrawService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 管理员：提现记录的Service层的实现层
 * @Author yoko
 * @Date 2020/12/19 12:33
 * @Version 1.0
 */
@Service("adminWithdrawService")
public class AdminWithdrawServiceImpl<T> extends BaseServiceImpl<T> implements AdminWithdrawService<T> {
    private static Logger log=Logger.getLogger(IssueServiceImpl.class);

    @Autowired
    private AdminWithdrawDao adminWithdrawDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return adminWithdrawDao;
    }

    @Override
    public int updateOutType(AdminWithdrawModel model) {
        return adminWithdrawDao.updateOutType(model);
    }

    @Override
    public String sumMoney(AdminWithdrawModel model) {
        return adminWithdrawDao.sumMoney(model);
    }
}
