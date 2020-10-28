package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.BankModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.BankService;
import com.xn.manager.service.MobileCardService;
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
 * @Description 手机卡 Controller
 * @Date 2020/9/11 13:47
 * @Version 1.0
 */

@Controller
@RequestMapping("/mobilecard")
public class MobileCardController extends BaseController {
    private static Logger log = Logger.getLogger(MobileCardController.class);


    @Autowired
    private MobileCardService<MobileCardModel> mobileCardService;

    @Autowired
    private BankService<BankModel> bankService;
    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/mobilecard/mobilecardIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MobileCardModel model) throws Exception {
        List<MobileCardModel> dataList = new ArrayList<MobileCardModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                model.setAccountId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setAccountId(account.getCreateUser());
                model.setCardSiteId(account.getId());
            }
            dataList = mobileCardService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MobileCardModel model) throws Exception {
        List<MobileCardModel> dataList = new ArrayList<MobileCardModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                model.setAccountId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                model.setAccountId(account.getCreateUser());
                model.setCardSiteId(account.getId());
            }
            dataList = mobileCardService.queryAllList(model);
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
////                model.addAttribute("agent", agentService.queryAllList());
//            }
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/mobilecard/mobilecardAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MobileCardModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            //check是否有重复的账号
            MobileCardModel mobileCardModel = new MobileCardModel();
            mobileCardModel.setPhoneNum(bean.getPhoneNum());
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                bean.setAccountId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                bean.setAccountId(account.getCreateUser());
                bean.setCardSiteId(account.getId());
            }
            MobileCardModel queryBean = mobileCardService.queryByCondition(mobileCardModel);
            if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                sendFailureMessage(response,"有重复的手机号了,请重新输入其它手机号!");
            }else{
                mobileCardService.add(bean);
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
        MobileCardModel atModel = new MobileCardModel();
        atModel.setId(id);
        model.addAttribute("account", mobileCardService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/mobilecard/mobilecardEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,MobileCardModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MobileCardModel mobileCardModel = new MobileCardModel();
            mobileCardModel.setPhoneNum(bean.getPhoneNum());
            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
                bean.setAccountId(account.getId());
            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
                bean.setAccountId(account.getCreateUser());
                bean.setCardSiteId(account.getId());
            }
            MobileCardModel queryBean = mobileCardService.queryByCondition(mobileCardModel);

            if (queryBean != null && queryBean.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                if(bean.getId()!=queryBean.getId()){
                    sendFailureMessage(response,"有重复的手机号了,请重新输入其它手机号!");
                }
            }
            mobileCardService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MobileCardModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            mobileCardService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MobileCardModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            mobileCardService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
