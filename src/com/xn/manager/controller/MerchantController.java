package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantRechargeModel;
import com.xn.manager.service.MerchantService;
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
 * @Date 2020/9/21 18:17
 * @Version 1.0
 */

@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {
    private static Logger log = Logger.getLogger(MerchantController.class);

    @Autowired
    private MerchantService<MerchantModel> merchantService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchant/merchantIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantModel model) throws Exception {
        List<MerchantModel> dataList = new ArrayList<MerchantModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setAccountId(account.getId());
            }
//            else if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
//                model.getAccountId(account.getId());
//            }
            dataList = merchantService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantModel model) throws Exception {
        List<MerchantModel> dataList = new ArrayList<MerchantModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setAccountId(account.getId());
            }
            dataList = merchantService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

}
