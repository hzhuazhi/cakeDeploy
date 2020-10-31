package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.MerchantChannelDao;
import com.xn.manager.dao.OrderOutDao;
import com.xn.manager.model.MerchantChannelModel;
import com.xn.manager.service.MerchantChannelService;
import com.xn.manager.service.OrderOutService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:代付订单的Service层的实现层
 * @create 2018-09-11 14:41
 **/
@Service("merchantChannelService")
public class MerchantChannelServiceImpl<T> extends BaseServiceImpl<T> implements MerchantChannelService<T> {
    private static Logger log   =  Logger.getLogger(MerchantChannelServiceImpl.class);

    @Autowired
    private MerchantChannelDao merchantChannelDao;
    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return merchantChannelDao;
    }


    @Override
    public String byIdQueryMerchant(Long id) {
        String    rsString = "";
        MerchantChannelModel channelModel =new MerchantChannelModel();
        channelModel.setChannelId(id);
        List<MerchantChannelModel> list = merchantChannelDao.queryByAll(channelModel);
        if(list.size()==0){
            return rsString;
        }else{
//            List<Long>     idList = new ArrayList<>();
            for(MerchantChannelModel merchantChannelModel:list){
//                idList.add(channelBankModel1.getBankId());
                rsString+=merchantChannelModel.getAlias()+"#";
            }
//
        }
        return rsString;
    }

    @Override
    public List<MerchantChannelModel> queryisAllList() {
        return merchantChannelDao.queryisAllList();
    }

    @Override
    public List<MerchantChannelModel> queryByAll(MerchantChannelModel  queryBean) {
        return merchantChannelDao.queryByAll(queryBean);
    }
}
