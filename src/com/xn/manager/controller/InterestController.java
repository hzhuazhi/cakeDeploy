package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.*;
import com.xn.manager.model.*;
import com.xn.manager.service.InterestService;
import com.xn.manager.service.InterestWithdrawService;
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
 * @Description 利益分配者
 * @Date 2020/9/6 10:18
 * @Version 1.0
 */

@Controller
@RequestMapping("/interest")
public class InterestController extends BaseController {

    private static Logger log = Logger.getLogger(InterestController.class);

    @Autowired
    private InterestService<InterestModel> interestService;


    @Autowired
    private InterestWithdrawService<InterestWithdrawModel> interestWithdrawService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/interest/interestIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, InterestModel model) throws Exception {
        List<InterestModel> dataList = new ArrayList<InterestModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAcName(account.getAcName());
                model.setAccountNum(account.getAccountNum());
            }
        }
        dataList = interestService.queryByList(model);
        dataList = interestService.queryListInfo(dataList);
//        interestWithdrawService

        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, InterestModel model) throws Exception {
        List<InterestModel> dataList = new ArrayList<InterestModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setAcName(account.getAcName());
            }
        }
        dataList = interestService.queryAllList(model);
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model,Long id) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()!=1){
                sendFailureMessage(response,"该用户没有权限进行操作!");
            }
            InterestModel  interestModel = new  InterestModel();
            interestModel.setId(id);
            model.addAttribute("dl",interestModel);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/interestmerchant/interestmerchantIndex";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, InterestModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        InterestModel   queryBean= new  InterestModel();
        queryBean.setAccountNum(bean.getAccountNum());
        InterestModel queryBean1 = interestService.queryByCondition(queryBean);
        if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            sendFailureMessage(response,"有重复账户了，请重新填写！");
        }else{
            if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                bean.setRoleId(2L);
                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
                bean.setWithdrawPassWd(MD5.parseMD5(bean.getWithdrawPassWd()));
                bean.setCreateRoleId(account.getRoleId());
                bean.setCreateUserId(account.getId());
                interestService.add(bean);
                sendSuccessMessage(response, "保存成功~");
            }else {
                sendFailureMessage(response,"登录超时,请重新登录在操作!");
            }
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(HttpServletRequest request, HttpServletResponse response, Model model, long id, Integer op) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        InterestModel atModel = new InterestModel();
        atModel.setId(id);
        model.addAttribute("account", interestService.queryById(atModel));
        return "manager/interest/interestEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,InterestModel bean, String op) throws Exception {
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
            interestService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, InterestModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            interestService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, InterestModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            interestService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


}
