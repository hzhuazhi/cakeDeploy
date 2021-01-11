package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.MD5;
import com.xn.common.util.StringUtil;
import com.xn.manager.model.InterestModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantWithdrawModel;
import com.xn.manager.model.MobileCardModel;
import com.xn.manager.service.InterestService;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.MerchantWithdrawService;
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
import java.util.Date;
import java.util.List;

/**
 * @Description 利益分配者
 * @Date 2020/9/6 10:18
 * @Version 1.0
 */

@Controller
@RequestMapping("/merchantwithdraw")
public class MerchantWithdrawController extends BaseController {

    private static Logger log = Logger.getLogger(MerchantWithdrawController.class);

    @Autowired
    private MerchantWithdrawService<MerchantWithdrawModel> merchantWithdrawService;


    @Autowired
    private MerchantService<MerchantModel> merchantService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchantwithdraw/merchantwithdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantWithdrawModel model) throws Exception {
        List<MerchantWithdrawModel> dataList = new ArrayList<MerchantWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                model.setAccountNum(account.getAccountNum());
            }
        }
        dataList = merchantWithdrawService.queryByList(model);
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantWithdrawModel model) throws Exception {
        List<MerchantWithdrawModel> dataList = new ArrayList<MerchantWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
//                model.setAcName(account.getAcName());
            }
        }
        dataList = merchantWithdrawService.queryAllList(model);
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MerchantWithdrawModel  mobileCardModel = new  MerchantWithdrawModel();

            model.addAttribute("type",merchantWithdrawService.queryAllList());
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/merchantwithdraw/merchantwithdrawAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantWithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);

        MerchantModel query  = new MerchantModel();
        query.setId(bean.getMerchantId());
        MerchantModel  queryModel = merchantService.queryByCondition(query);
        List<MerchantModel>  list = new ArrayList<MerchantModel>();
        list.add(queryModel);
        list   = merchantService.queryListInfo(list);

        if(list.size()<=0||list.get(0).getAvailableMoney().equals("")){
            sendFailureMessage(response,"当前不能提现，请稍后再试！");
            return;
        }else if(list.get(0).getAvailableMoney().indexOf("-")>=0){
            sendFailureMessage(response,"没有可用余额进行提现！");
            return;
        } else if(!StringUtil.getBigDecimalSubtract(list.get(0).getAvailableMoney(),bean.getMoney())){
            sendFailureMessage(response,"可提现金额少于提现金额，请修改金额以后再试！");
            return;
        }

        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setOrderNo("TX"+ DateUtil.getNowPlusTimeMill());
//            bean.setWithdrawServiceCharge("5.00");
            bean.setCreateRoleId(account.getRoleId());
            bean.setCreateUserId(account.getId());
            bean.setCurday(Integer.parseInt(DateUtil.getNowShortDate()));
            bean.setCurhour(DateUtil.getHour(new Date()));
            bean.setCurminute(DateUtil.getCurminute(new Date()));
            merchantWithdrawService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
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
        model.addAttribute("account", merchantWithdrawService.queryById(atModel));
        return "manager/merchantwithdraw/merchantwithdrawEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,MerchantWithdrawModel bean, String op) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
//            if(bean.getPassWd()!=null&&!bean.getPassWd().equals("")){
//                bean.setPassWd(MD5.parseMD5(bean.getPassWd()));
//            }
//            if(bean.getWithdrawPassWd()!=null&&!bean.getWithdrawPassWd().equals("")){
//                bean.setWithdrawPassWd(MD5.parseMD5(bean.getWithdrawPassWd()));
//            }
            bean.setUpdateUserId(account.getId());
            bean.setUpdateRoleId(account.getRoleId());
            merchantWithdrawService.update(bean);
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
            merchantWithdrawService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantWithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantWithdrawService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


}
