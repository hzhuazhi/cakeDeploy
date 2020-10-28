package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.IssueDao;
import com.xn.manager.model.IssueModel;
import com.xn.manager.service.IssueService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 下发的Service层的实现层
 * @Author yoko
 * @Date 2020/9/27 15:00
 * @Version 1.0
 */
@Service("issueService")
public class IssueServiceImpl<T> extends BaseServiceImpl<T> implements IssueService<T> {
    private static Logger log=Logger.getLogger(IssueServiceImpl.class);

    @Autowired
    private IssueDao issueDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return issueDao;
    }

    @Override
    public int updateOrderStatus(IssueModel model) {
        return issueDao.updateOrderStatus(model);
    }
}
