package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.manager.model.InterestMerchantModel;
import com.xn.manager.model.InterestModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.InterestMerchantService;
import com.xn.manager.service.InterestService;
import com.xn.manager.service.MerchantService;
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
@RequestMapping("/interestmerchant")
public class InterestMerchantController extends BaseController {

    private static Logger log = Logger.getLogger(InterestMerchantController.class);

    @Autowired
    private InterestMerchantService<InterestMerchantModel> interestMerchantService;
    @Autowired
    private MerchantService<MerchantModel> merchantService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/interestmerchant/interestmerchantIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, InterestMerchantModel model) throws Exception {
        List<InterestMerchantModel> dataList = new ArrayList<InterestMerchantModel>();
//        model.setUseStatus(1);
//        model.setIsEnable(ManagerConstant.PUBLIC_CONSTANT.IS_ENABLE_ZC);
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAcName(account.getAcName());
//                model.setAccountNum(account.getAccountNum());
            }
        }
        dataList = interestMerchantService.queryByList(model);
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, InterestMerchantModel model) throws Exception {
        List<InterestMerchantModel> dataList = new ArrayList<InterestMerchantModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAcName(account.getAcName());
            }
        }
        dataList = interestMerchantService.queryAllList(model);
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model,Long id) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            InterestMerchantModel  interestMerchantModel = new  InterestMerchantModel();
            interestMerchantModel.setInterestId(id);
//            if (account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_MERCHANTS_VALUE){
//                mobileCardModel.setMerchantId(account.getId());
//            }else if(account.getRoleId() == ManagerConstant.PUBLIC_CONSTANT.CARD_SITE_VALUE) {
//                mobileCardModel.setMerchantSiteId(account.getId());
//            }
            model.addAttribute("interestMerchant",interestMerchantModel);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/interestmerchant/interestmerchantAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, InterestMerchantModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        InterestMerchantModel queryBean= new  InterestMerchantModel();
        queryBean.setInterestId(bean.getInterestId());
        queryBean.setMerchantId(bean.getMerchantId());
        InterestMerchantModel queryBean1 = interestMerchantService.queryByCondition(queryBean);
        if (queryBean1 != null && queryBean1.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            sendFailureMessage(response,"该卡商已有和该利益商绑定了，请查看后在进行绑定！");
        }else{
            if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
                bean.setCreateRoleId(account.getRoleId());
                bean.setCreateUserId(account.getId());
                interestMerchantService.add(bean);
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
        InterestMerchantModel atModel = new InterestMerchantModel();
        atModel.setId(id);
        model.addAttribute("account", interestMerchantService.queryById(atModel));
        return "manager/interestmerchant/interestmerchantEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void  update(HttpServletRequest request, HttpServletResponse response,InterestMerchantModel bean,Model model, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateUserId(account.getId());
            bean.setUpdateRoleId(account.getRoleId());
            interestMerchantService.update(bean);

            InterestMerchantModel  queryBean =new InterestMerchantModel();
            queryBean.setId(bean.getId());
            model.addAttribute("dl",interestMerchantService.queryByCondition(queryBean));
//            return  "manager/interestmerchant/interestmerchantIndex";
            sendSuccessMessage(response, "保存成功~");
        }else {
//            return  "登录超时,请重新登录在操作!";
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, InterestMerchantModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            interestMerchantService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, InterestMerchantModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            interestMerchantService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


}
