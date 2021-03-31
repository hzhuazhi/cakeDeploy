package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.*;
import com.xn.manager.service.*;
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
    private MerchantReplenishService<MerchantReplenishModel> modelMerchantReplenishService;

    @Autowired
    private MerchantRechargeService<MerchantRechargeModel> merchantRechargeService;

    @Autowired
    private WithdrawService<WithdrawModel> withdrawService;



    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        AbnormalModel abnormalModel = new AbnormalModel();
        MobileCardModel  mobileCardModel = new MobileCardModel(); //手机监听
        WithdrawModel    withdrawModel =new  WithdrawModel();   //下发信息
        BankModel    bankModel =new  BankModel();   //需要换的银行卡信息

        MerchantReplenishModel  merchantReplenishModel = new  MerchantReplenishModel();// 补单信息

        List<MobileCardModel>  mobileCardModelList  = new ArrayList<>();
        List<MerchantRechargeModel>  merchantRechargeModelList  = new ArrayList<>();
        mobileCardModel.setHeartbeatStatus(1);
        withdrawModel.setOrderStatus(1);
        merchantReplenishModel.setCheckStatus(1);
        bankModel.setStatusTwo(2);
        bankModel.setStatusThree(3);
        bankModel.setChangeTime(DateUtil.getNowPlusTime());

        List<BankCollectionModel> dataList = new ArrayList<BankCollectionModel>();
        List<WithdrawModel> withdrawModelList  = new ArrayList<WithdrawModel>();
        List<MerchantReplenishModel> merchantReplenishlList  = new ArrayList<MerchantReplenishModel>();
        List<BankModel> bankList  = new ArrayList<BankModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                mobileCardModel.setMerchantId(account.getId());
                withdrawModel.setMerchantId(account.getId());
                merchantReplenishModel.setMerchantId(account.getId());
                bankModel.setMerchantId(account.getId());
            }
//            merchantReplenishlList = modelMerchantReplenishService.queryAllList(merchantReplenishModel);
            mobileCardModelList  = mobileCardService.queryAllList(mobileCardModel);
            withdrawModelList = withdrawService.queryAllList(withdrawModel);
            bankList = bankService.queryAllList(bankModel);

            abnormalModel.setPhoneNum(mobileCardModelList.size());
            abnormalModel.setWithdrawNum(withdrawModelList.size());
//            abnormalModel.setMerchantReplenishNum(merchantReplenishlList.size());
            abnormalModel.setBankNum(bankList.size());
        }
        HtmlUtil.writerJson(response, abnormalModel);
    }

}
