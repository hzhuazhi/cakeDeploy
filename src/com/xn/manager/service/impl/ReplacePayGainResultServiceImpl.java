package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ReplacePayGainResultDao;
import com.xn.manager.model.replacepay.ReplacePayGainResultModel;
import com.xn.manager.service.ReplacePayGainResultService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:
 * @Description: 第三方代付主动拉取结果返回的订单结果的Service层的实现层
 * @Author: yoko
 * @Date: $
 * @Version: 1.0
 **/
@Service("replacePayGainResultService")
public class ReplacePayGainResultServiceImpl<T> extends BaseServiceImpl<T> implements ReplacePayGainResultService<T> {
    private static Logger log=Logger.getLogger(StrategyServiceImpl.class);

    @Autowired
    private ReplacePayGainResultDao replacePayGainResultDao;

    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return replacePayGainResultDao;
    }

    @Override
    public int addBatchResult(List<ReplacePayGainResultModel> list) {
        return replacePayGainResultDao.addBatchResult(list);
    }
}
