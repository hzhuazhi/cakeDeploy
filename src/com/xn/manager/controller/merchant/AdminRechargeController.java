package com.xn.manager.controller.merchant;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.IssueModel;
import com.xn.manager.model.RechargeModel;
import com.xn.manager.service.IssueService;
import com.xn.manager.service.RechargeService;
import com.xn.system.entity.Account;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
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
 * @Description 管理员的角色的卡商充值的Controller层
 * @Author yoko
 * @Date 2020/9/29 16:21
 * @Version 1.0
 */
@Controller
@RequestMapping("/adminrecharge")
public class AdminRechargeController extends BaseController {

    private static Logger log = Logger.getLogger(AdminRechargeController.class);


    @Autowired
    private RechargeService<RechargeModel> rechargeService;

    @Autowired
    private AccountService<AccountModel> accountService;

    @Autowired
    private IssueService<IssueModel> issueService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminrecharge/adminrechargeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, RechargeModel model) throws Exception {
        List<RechargeModel> dataList = new ArrayList<RechargeModel>();
        if (model.getCurdayStart() ==0 || model.getCurdayEnd() == 0){
            model.setCurdayStart(DateUtil.getDayNumber(new Date()));
            model.setCurdayEnd(DateUtil.getDayNumber(new Date()));
        }
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = rechargeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, RechargeModel model) throws Exception {
        List<RechargeModel> dataList = new ArrayList<RechargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = rechargeService.queryAllList(model);
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
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.ROLE_SYS){
                sendFailureMessage(response,"无法操作,请登录其它账号角色!");
                return null;
            }else {
                model.addAttribute("ac", accountService.queryAllList());
            }
//            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/adminrecharge/adminrechargeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (bean.getOrderType() == 1){
                if (!StringUtils.isBlank(bean.getIssueOrderNo())){
                    sendFailureMessage(response,"预付类型订单,无需填写下发订单!");
                    return;
                }
            }else if (bean.getOrderType() == 2){
                if (StringUtils.isBlank(bean.getIssueOrderNo())){
                    sendFailureMessage(response,"平台类型订单,请填写下发订单号!");
                    return;
                }else {
                    // check下发订单号是否存在于下发表
                    IssueModel issueQuery = new IssueModel();
                    issueQuery.setOrderNo(bean.getIssueOrderNo());
                    IssueModel issueModel = issueService.queryByCondition(issueQuery);
                    if (issueModel == null || issueModel.getId() <= 0){
                        sendFailureMessage(response,"无此下发订单号,请核实!");
                        return;
                    }
                    if (issueModel.getAscriptionType() != 2){
                        sendFailureMessage(response,"下发订单号,不归属平台,请核实!");
                        return;
                    }
                }
            }
            String orderNo = DateUtil.getNowPlusTimeMill();
            bean.setOrderNo(orderNo);
            bean.setCurday(DateUtil.getDayNumber(new Date()));
            bean.setCurhour(DateUtil.getHour(new Date()));
            bean.setCurminute(DateUtil.getCurminute(new Date()));
            rechargeService.add(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
    }

    /**
     * 获取修改页面
     */
    @RequestMapping("/jumpUpdate")
    public String jumpUpdate(Model model, long id) {
        RechargeModel atModel = new RechargeModel();
        atModel.setId(id);
        model.addAttribute("account", rechargeService.queryById(atModel));
        model.addAttribute("ac", accountService.queryAllList());
        return "manager/adminrecharge/adminrechargeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            RechargeModel query = new RechargeModel();
            query.setId(bean.getId());
            RechargeModel rechargeModel = rechargeService.queryByCondition(query);
            if (rechargeModel.getOperateStatus() == 4){
                sendFailureMessage(response, "订单处于锁定状态,无法编辑更新!");
                return;
            }
            if (bean.getOrderType() == 1){
                if (!StringUtils.isBlank(bean.getIssueOrderNo())){
                    sendFailureMessage(response,"预付类型订单,无需填写下发订单!");
                    return;
                }
            }else if (bean.getOrderType() == 2){
                if (StringUtils.isBlank(bean.getIssueOrderNo())){
                    sendFailureMessage(response,"平台类型订单,请填写下发订单号!");
                    return;
                }else {
                    // check下发订单号是否存在于下发表
                    IssueModel issueQuery = new IssueModel();
                    issueQuery.setOrderNo(bean.getIssueOrderNo());
                    IssueModel issueModel = issueService.queryByCondition(issueQuery);
                    log.info("");
                    if (issueModel == null || issueModel.getId() <= 0){
                        sendFailureMessage(response,"无此下发订单号,请核实!");
                        return;
                    }
                    if (issueModel.getAscriptionType() != 2){
                        sendFailureMessage(response,"下发订单号,不归属平台,请核实!");
                        return;
                    }
                }
            }

            if (rechargeModel.getAccountId() != bean.getAccountId()){
                bean.setCardSiteId(0);
            }else {
                bean.setCardSiteId(rechargeModel.getCardSiteId());
            }
            rechargeService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            rechargeService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            rechargeService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 审核的跳转页
     */
    @RequestMapping("/jumpCheck")
    public String jumpCheck(Model model, long id) {
        RechargeModel atModel = new RechargeModel();
        atModel.setId(id);
        model.addAttribute("account", rechargeService.queryById(atModel));
        return "manager/adminrecharge/adminrechargeCheck";
    }


    /**
     * 正式审核
     */
    @RequestMapping("/check")
    public void check(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            RechargeModel model = new RechargeModel();
            model.setId(bean.getId());
            model.setCheckStatus(bean.getCheckStatus());
            if (bean.getCheckStatus() != 3){
                model.setOrderStatus(2);
            }
            if (!StringUtils.isBlank(bean.getCheckInfo())){
                model.setCheckInfo(bean.getCheckInfo());
            }
            if (!StringUtils.isBlank(bean.getRemark())){
                model.setRemark(bean.getRemark());
            }
            rechargeService.updateCheck(model);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }


    /**
     * 获取数据的详情
     */
    @RequestMapping("/jumpInfo")
    public String jumpInfo(Model model, long id) {
        RechargeModel atModel = new RechargeModel();
        atModel.setId(id);
        model.addAttribute("account", rechargeService.queryById(atModel));
        return "manager/adminrecharge/adminrechargeInfo";
    }
}
