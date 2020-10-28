package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.SysAccountDao;
import com.xn.manager.service.SysAccountService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 卡站点管理
 * @Date 2020/9/17 15:52
 * @Version 1.0
 */
@Service("sysAccountService")
public class SysAccountServiceImpl<T> extends BaseServiceImpl<T> implements SysAccountService<T> {
    private final static Logger log = Logger.getLogger(SysAccountServiceImpl.class);

    @Autowired
    private SysAccountDao sysAccountDao;

    @Override
    public BaseDao<T> getDao() {
        return sysAccountDao;
    }
}
