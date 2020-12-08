package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.TransferDao;
import com.xn.manager.model.TransferModel;
import com.xn.manager.service.TransferService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 中转站的
 * @Author yoko
 * @Date 2020/12/8 17:01
 * @Version 1.0
 */
@Service("transferService")
public class TransferServiceImpl<T> extends BaseServiceImpl<T> implements TransferService<T> {
    private static Logger log = Logger.getLogger(TransferServiceImpl.class);

    @Autowired
    private TransferDao transferDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return transferDao;
    }

    @Override
    public int updatePassWd(TransferModel model) {
        return transferDao.updatePassWd(model);
    }
}
