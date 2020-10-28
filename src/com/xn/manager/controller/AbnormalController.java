package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.*;
import com.xn.manager.service.BankService;
import com.xn.manager.service.BankShortMsgService;
import com.xn.manager.service.MerchantRechargeService;
import com.xn.manager.service.MobileCardService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Date 2020/9/22 15:29
 * @Version 1.0
 */

@Controller
@RequestMapping("/abnormal")
public class AbnormalController extends BaseController {
    private static Logger log = Logger.getLogger(AbnormalController.class);

    @Autowired
    private MobileCardService<MobileCardModel> mobileCardService;

    @Autowired
    private BankService<BankModel> bankService;

    @Autowired
    private BankShortMsgService<BankShortMsgModel> bankShortMsgService;

    @Autowired
    private MerchantRechargeService<MerchantRechargeModel> merchantRechargeService;

    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        AbnormalModel abnormalModel = new AbnormalModel();

        MobileCardModel  mobileCardModel = new MobileCardModel();
        BankModel  bankModel = new BankModel();
        MerchantRechargeModel  merchantRechargeModel = new MerchantRechargeModel();
        BankShortMsgModel  bankShortMsgModel = new BankShortMsgModel();

        List<MobileCardModel>  mobileCardModelList  = new ArrayList<>();
        List<BankModel>  bankModelList  = new ArrayList<>();
        List<MerchantRechargeModel>  merchantRechargeModelList  = new ArrayList<>();
        List<BankShortMsgModel>  bankShortMsgModelList  = new ArrayList<>();
        mobileCardModel.setHeartbeatStatus(1);
        bankModel.setCheckStatus(2);
        bankShortMsgModel.setWorkType(2);
        bankShortMsgModel.setHandleType(1);
        bankShortMsgModel.setCurday(Integer.parseInt(DateUtil.getNowShortDate()));
        merchantRechargeModel.setOrderStatus(3);
        List<BankCollectionModel> dataList = new ArrayList<BankCollectionModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                    mobileCardModel.setCardSiteId(account.getId());
                    bankModel.setCardSiteId(account.getId());
                    merchantRechargeModel.setCardSiteId(account.getId());
                }else{
                    mobileCardModel.setAccountId(account.getId());
                    bankModel.setAccountId(account.getId());
                    merchantRechargeModel.setAccountId(account.getId());
                }
                mobileCardModelList  = mobileCardService.queryAllList(mobileCardModel);
                bankModelList  = bankService.queryAllList(bankModel);
                merchantRechargeModelList   = merchantRechargeService.queryAllList(merchantRechargeModel);
            }else{
                mobileCardModelList  = mobileCardService.queryAllList(mobileCardModel);
                bankModelList  = bankService.queryAllList(bankModel);
                bankShortMsgModelList = bankShortMsgService.queryByList(bankShortMsgModel);
                merchantRechargeModelList   = merchantRechargeService.queryAllList(merchantRechargeModel);
            }
            abnormalModel.setPhoneNum(mobileCardModelList.size());
            abnormalModel.setBankNum(bankModelList.size());
            abnormalModel.setSmsMessageNum(bankShortMsgModelList.size());
            abnormalModel.setPaymentNum(merchantRechargeModelList.size());
        }
        HtmlUtil.writerJson(response, abnormalModel);
    }

}
