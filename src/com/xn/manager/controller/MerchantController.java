package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.enums.ManagerEnum;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.*;
import com.xn.manager.service.MerchantProfitService;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.MerchantWithdrawService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private MerchantWithdrawService<MerchantWithdrawModel> merchantWithdrawService;


    @Autowired
    private MerchantProfitService<MerchantProfitModel> merchantProfitService;

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
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                model.setAccountNum(account.getAccountNum());
            }else if (account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                sendFailureMessage(response,"你没有权限查看数据！");
            }else if (account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.DF_CARD_SITE_VALUE){
                model.setAccountNum(account.getAccountNum());
            }
//            dataList = merchantService.queryByList(model);
//            dataList = merchantService.queryListInfo(dataList);

            List<MerchantModel> resList = new ArrayList<MerchantModel>();
            resList = merchantService.queryByList(model);
            resList = merchantService.queryListInfo(resList);

            if (resList != null && resList.size() > 0){
                for (MerchantModel merchantModel : resList){
                    // 查询今日收益
                    MerchantProfitModel merchantProfitQuery = new MerchantProfitModel();
                    merchantProfitQuery.setCurdayStart(DateUtil.getDayNumber(new Date()));
                    merchantProfitQuery.setCurdayEnd(DateUtil.getDayNumber(new Date()));
                    merchantProfitQuery.setMerchantId(merchantModel.getId());
                    MerchantProfitModel merchantProfitModel = merchantProfitService.getTotalData(merchantProfitQuery);
                    if (merchantProfitModel != null){
                        if (!StringUtils.isBlank(merchantProfitModel.getTotalProfit())){
                            merchantModel.setTodayProfit(merchantProfitModel.getTotalProfit());
                        }
                    }
                    dataList.add(merchantModel);
                }

            }


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
                model.setId(account.getId());
            }
            dataList = merchantService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataJsonAllList")
    public void dataJsonAllList(HttpServletRequest request, HttpServletResponse response, MerchantModel model) throws Exception {
        List<MerchantModel> dataList = new ArrayList<MerchantModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setId(account.getId());
            }
            dataList = merchantService.queryAllList(model);
        }
//        HtmlUtil.writerJson(response, dataList);
        sendSuccessMessage(response, "", dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            model.addAttribute("op", account);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/merchant/merchantAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            check是否有重复的账号
            MerchantModel queryBean = new MerchantModel();
            queryBean.setAccountNum(bean.getAccountNum());
            MerchantModel queryBean1 = merchantService.queryByCondition(queryBean);
            if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response,"有重复账户了，请重新填写！");
            }else{
                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                bean.setWithdrawPassWd(MD5.parseMD5(bean.getWithdrawPassWd()));
                bean.setCreateRoleId(account.getRoleId());
                bean.setCreateUserId(account.getId());
                bean.setRoleId(2);
                merchantService.add(bean);
                sendSuccessMessage(response, "保存成功~");
            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        MerchantModel atModel = new MerchantModel();
        atModel.setId(id);
        model.addAttribute("account", merchantService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/merchant/merchantEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,MerchantModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(bean.getPassWd()!=null&&!bean.getPassWd().equals("")){
                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
            }
            if(bean.getWithdrawPassWd()!=null&&!bean.getWithdrawPassWd().equals("")){
                bean.setWithdrawPassWd(MD5.parseMD5(bean.getWithdrawPassWd()));
            }
            bean.setUpdateUserId(account.getId());
            bean.setUpdateRoleId(account.getRoleId());
            merchantService.update(bean);
            sendSuccessMessage(response, "修改成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MerchantModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

}
