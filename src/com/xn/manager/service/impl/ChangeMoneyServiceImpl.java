package com.xn.manager.service.impl;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.manager.dao.ChangeMoneyDao;
import com.xn.manager.dao.ChannelDao;
import com.xn.manager.dao.InterestDao;
import com.xn.manager.dao.MerchantDao;
import com.xn.manager.model.ChangeMoneyModel;
import com.xn.manager.model.InterestModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.service.ChangeMoneyService;
import com.xn.manager.service.ChannelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:添加利益实现层
 * @create 2018-09-11 14:41
 **/
@Service("changeMoneyService")
public class ChangeMoneyServiceImpl<T> extends BaseServiceImpl<T> implements ChangeMoneyService<T> {
    private static Logger log   =  Logger.getLogger(ChangeMoneyServiceImpl.class);

    @Autowired
    private ChangeMoneyDao changeMoneyDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private InterestDao interestDao;


    @Override
    public BaseDao<T> getDao() {
        // TODO Auto-generated method stub
        return changeMoneyDao;
    }


    /**
     * 根据类型查询信息
     * @param type
     * @return
     */
    @Override
    public List<ChangeMoneyModel> queryAscriptionInfo(Integer type) {
        List<ChangeMoneyModel>      changeMoneyModelsList = new ArrayList<>();
        if(type==1){
            List <MerchantModel>   merchantList = merchantDao.queryAllList();
            for(MerchantModel  merchantModel:merchantList){
                ChangeMoneyModel  changeMoneyModel = new ChangeMoneyModel();
                changeMoneyModel.setAscriptionId(merchantModel.getId());
                changeMoneyModel.setAscriptionName(merchantModel.getAcName());
                changeMoneyModelsList.add(changeMoneyModel);
            }
        }else if(type==2){

        }else if(type==3){
            List <InterestModel>   interestList = interestDao.queryAllList();
            for(InterestModel  interestModel:interestList){
                ChangeMoneyModel  changeMoneyModel = new ChangeMoneyModel();
                changeMoneyModel.setAscriptionId(interestModel.getId());
                changeMoneyModel.setAscriptionName(interestModel.getAcName());
                changeMoneyModelsList.add(changeMoneyModel);
            }
        }

        return changeMoneyModelsList;
    }

    @Override
    public String queryAscriptionName(Integer type, Integer id, List<MerchantModel> merchantModelList, List<InterestModel> interestModels) {
        String ascriptionName   ="";
        if(type==1){
            for(MerchantModel merchantModel:merchantModelList){
                if(merchantModel.getId()==id){
                    ascriptionName=merchantModel.getAcName();
                    break;
                }
            }
        }else if(type==3){
            for(InterestModel interestModel:interestModels){
                if(interestModel.getId().longValue()==id){
                    ascriptionName=interestModel.getAcName();
                    break;
                }
            }
        }
        return ascriptionName;
    }
}
