package com.xn.manager.controller.merchant;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.DateUtil;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.OssUploadUtil;
import com.xn.manager.model.IssueModel;
import com.xn.manager.model.RechargeModel;
import com.xn.manager.service.IssueService;
import com.xn.manager.service.RechargeService;
import com.xn.system.entity.Account;
import com.xn.system.entity.Module;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 卡商以及站点的角色的卡商充值的Controller层
 * @Author yoko
 * @Date 2020/10/15 18:19
 * @Version 1.0
 */
@Controller
@RequestMapping("/recharge")
public class RechargeController extends BaseController {

    private static Logger log = Logger.getLogger(RechargeController.class);


    @Autowired
    private RechargeService<RechargeModel> rechargeService;

    @Autowired
    private AccountService<AccountModel> accountService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/recharge/rechargeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, RechargeModel model) throws Exception {
        List<RechargeModel> dataList = new ArrayList<RechargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() <= ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            if (account.getRoleId() > 3){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            if (account.getRoleId() == 2){
                // 卡商
                model.setAccountId(account.getId());
            }
            if (account.getRoleId() == 3){
                // 卡站点
                model.setCardSiteId(account.getId());
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
            if (account.getRoleId() <= ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            if (account.getRoleId() > 3){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            if (account.getRoleId() == 2){
                // 卡商
                model.setAccountId(account.getId());
            }
            if (account.getRoleId() == 3){
                // 卡站点
                model.setCardSiteId(account.getId());
            }
            dataList = rechargeService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     * @Description: 获取充值详情
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
    */
    @RequestMapping("/getId")
    public void getId(Long id, HttpServletResponse response) throws Exception {
        RechargeModel query = new RechargeModel();
        query.setId(id);
        RechargeModel bean = rechargeService.queryById(query);
        if (bean == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", bean);
    }


    /**
     * 分派订单给卡站点
     */
    @RequestMapping("/distribution")
    public void distribution(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() > 2){
                sendFailureMessage(response, "只有卡商可分派订单给卡站点!");
                return;
            }
            RechargeModel updateModel = new RechargeModel();
            updateModel.setId(bean.getId());
            updateModel.setCardSiteId(bean.getCardSiteId());
            rechargeService.updateCardSite(updateModel);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
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
        return "manager/recharge/rechargeEdit";
    }

    /**
     * 修改数据
     * <p>
     *     确认下发
     * </p>
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            RechargeModel query = new RechargeModel();
            query.setId(bean.getId());
            RechargeModel rechargeModel = rechargeService.queryByCondition(query);
            if (rechargeModel == null || rechargeModel.getId() <= 0){
                sendFailureMessage(response, "操作有误,请重试!");
                return;
            }
            if (rechargeModel.getLockAccountId() > 0){
                if (rechargeModel.getLockAccountId() != account.getId()){
                    sendFailureMessage(response, "不是锁定人,无法操作下发!");
                    return;
                }
            }
            RechargeModel updateModel = new RechargeModel();
            if (!files.isEmpty()){
                String pictureAds = OssUploadUtil.localMethod(files);
                if (StringUtils.isBlank(pictureAds)){
                    sendFailureMessage(response, "图片上传失败,请重试!");
                    return;
                }else {
                    updateModel.setId(bean.getId());
                    updateModel.setOrderStatus(3);
                    updateModel.setPictureAds(pictureAds);
                    updateModel.setCheckStatusStr("1");
                }
            }else {
                sendFailureMessage(response, "请上传转账凭证图片");
                return;
            }
            rechargeService.updateOrderStatus(updateModel);
            sendSuccessMessage(response, "保存成功~");
            return;
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
     * 这里是锁定、放弃
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, RechargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() == 1){
                sendFailureMessage(response, "管理员无法操作!");
                return;
            }
            RechargeModel query = new RechargeModel();
            query.setId(bean.getId());
            RechargeModel rechargeModel = rechargeService.queryByCondition(query);
            if (rechargeModel == null || rechargeModel.getId() <= 0){
                sendFailureMessage(response, "操作有误,请重试!");
                return;
            }
            if (rechargeModel.getOrderType() != 3){
                if (bean.getOperateStatus() == 3){
                    sendFailureMessage(response, "此订单无法放弃,请下发完毕!");
                    return;
                }
            }
            if (rechargeModel.getOperateStatus() == bean.getOperateStatus()){
                if (bean.getOperateStatus() == 3){
                    sendFailureMessage(response, "已经放弃,无需重复操作!");
                    return;
                }
                if (bean.getOperateStatus() == 4){
                    sendFailureMessage(response, "已经锁定,无需重复操作!");
                    return;
                }
            }
            if (bean.getOperateStatus() == 3){
                if (rechargeModel.getLockAccountId() > 0){
                    if (account.getId() != rechargeModel.getLockAccountId()){
                        sendFailureMessage(response, "操作放弃需要有点击锁定的人来操作放弃!");
                        return;
                    }
                }
            }else if(bean.getOperateStatus() == 4){
                bean.setLockAccountId(account.getId());
            }
            rechargeService.updateOperateStatus(bean);
            sendSuccessMessage(response, "状态更新成功");
            return;
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
            return;
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
        return "manager/recharge/rechargeInfo";
    }
}
