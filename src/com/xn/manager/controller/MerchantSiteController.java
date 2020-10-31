package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantSiteModel;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.MerchantSiteService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/merchantsite")
public class MerchantSiteController extends BaseController {
    private static Logger log = Logger.getLogger(MerchantSiteController.class);

    @Autowired
    private MerchantSiteService<MerchantSiteModel> merchantSiteService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchantsite/merchantsiteIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantSiteModel model) throws Exception {
        List<MerchantSiteModel> dataList = new ArrayList<MerchantSiteModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            dataList = merchantSiteService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantSiteModel model) throws Exception {
        List<MerchantSiteModel> dataList = new ArrayList<MerchantSiteModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            dataList = merchantSiteService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
//                sendFailureMessage(response,"只允许管理员操作!");
//            }else {
//                model.addAttribute("agent", agentService.queryAllList());
//            }
            model.addAttribute("op", account);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/merchantsite/merchantsiteAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantSiteModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            check是否有重复的账号
            MerchantSiteModel queryBean = new MerchantSiteModel();
            queryBean.setAccountNum(bean.getAccountNum());
            MerchantSiteModel queryBean1 = merchantSiteService.queryByCondition(queryBean);
            if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response,"有重复账户了，请重新填写！");
            }else{
                bean.setMerchantId(account.getId());
                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                bean.setWithdrawPassWd(MD5.parseMD5(bean.getWithdrawPassWd()));
                bean.setCreateRoleId(account.getRoleId());
                bean.setCreateUserId(account.getId());
                bean.setRoleId(3);
                merchantSiteService.add(bean);
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
        model.addAttribute("account", merchantSiteService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/merchantsite/merchantsiteEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,MerchantSiteModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(bean.getPassWd()!=null&&!bean.getPassWd().equals("")){
                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
            }
            if(bean.getWithdrawPassWd()!=null&&!bean.getWithdrawPassWd().equals("")){
                bean.setWithdrawPassWd(MD5.parseMD5(bean.getPassWd()));
            }
            bean.setUpdateUserId(account.getId());
            bean.setUpdateRoleId(account.getRoleId());
            merchantSiteService.update(bean);
            sendSuccessMessage(response, "修改成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MerchantSiteModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantSiteService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantSiteModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantSiteService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

}
