package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.SysAccountModel;
import com.xn.system.entity.Account;
import com.xn.manager.service.SysAccountService;
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
import java.util.List;

/**
 * @Description 卡站点管理信息
 * @Date 2020/9/17 15:33
 * @Version 1.0
 */

@Controller
@RequestMapping("/cardsite")
public class CardSiteController extends BaseController {
    private static Logger log = Logger.getLogger(CardSiteController.class);


    @Autowired
    private SysAccountService<SysAccountModel> sysAccountService;//管理员

    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/cardsite/cardsiteIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, SysAccountModel model) throws Exception {
        List<SysAccountModel> dataList = new ArrayList<SysAccountModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if(account.getRoleId()==2){
//                sendFailureMessage(response,"不是卡商权限，无法进行操作！");
//            }
//            model.setId(account.getId());
//            model.setRoleId(account.getRoleId());
            model.setCreateUser(account.getId());
            dataList = sysAccountService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, SysAccountModel model) throws Exception {
        List<SysAccountModel> dataList = new ArrayList<SysAccountModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
//                model.setAccountId(account.getId());
//            }else if(account.getRoleId()==ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE){
//                model.setCardSiteId(account.getId());
//            }

            dataList = sysAccountService.queryAllList(model);
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
        return "manager/cardsite/cardsiteAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, SysAccountModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setCreateUser(account.getId());
            bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
            bean.setRoleId(3L);
            sysAccountService.add(bean);
            sendSuccessMessage(response, "添加成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id, Integer op) {
        SysAccountModel atModel = new SysAccountModel();
        atModel.setId(id);
        model.addAttribute("account", sysAccountService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/cardsite/cardsiteEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,SysAccountModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if ("2".equals(op)) {
//                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
//            }
            if(!StringUtils.isBlank(bean.getPassWd())){
                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
            }
            if(bean.getPassWd()==""){
                bean.setPassWd(null);
            }
            sysAccountService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, SysAccountModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            sysAccountService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, SysAccountModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            sysAccountService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
