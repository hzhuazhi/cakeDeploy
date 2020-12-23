package com.xn.manager.controller.withdraw;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.AdminWithdrawModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.service.AdminWithdrawService;
import com.xn.manager.service.MerchantService;
import com.xn.manager.util.PublicMethod;
import com.xn.system.entity.Account;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
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
import java.util.List;

/**
 * @Description 管理员：提现记录的Controller层
 * @Author yoko
 * @Date 2020/12/19 12:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/adminwithdraw")
public class AdminWithdrawController extends BaseController {

    private static Logger log = Logger.getLogger(AdminWithdrawController.class);


    @Autowired
    private AdminWithdrawService<AdminWithdrawModel> adminWithdrawService;


    @Autowired
    private MerchantService<MerchantModel> merchantService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminwithdraw/adminwithdrawIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, AdminWithdrawModel model) throws Exception {
        List<AdminWithdrawModel> dataList = new ArrayList<AdminWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }

            dataList = adminWithdrawService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, AdminWithdrawModel model) throws Exception {
        List<AdminWithdrawModel> dataList = new ArrayList<AdminWithdrawModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
                return;
            }
            dataList = adminWithdrawService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     * @Description: 获取提现详情
     * @param id
     * @return
     * @author yoko
     * @date 2020/10/16 16:02
     */
    @RequestMapping("/getId")
    public void getId(Long id, HttpServletResponse response) throws Exception {
        AdminWithdrawModel query = new AdminWithdrawModel();
        query.setId(id);
        AdminWithdrawModel bean = adminWithdrawService.queryById(query);
        if (bean == null) {
            sendFailureMessage(response, "没有找到对应的记录!");
            return;
        }
        sendSuccessMessage(response, "", bean);
    }


    /**
     * 分配提现订单
     */
    @RequestMapping("/distribution")
    public void distribution(HttpServletRequest request, HttpServletResponse response, AdminWithdrawModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                sendFailureMessage(response, "您无权分派订单提现订单!");
                return;
            }

            bean.setOutType(1);// 默认赋值卡商

            AdminWithdrawModel updateModel = new AdminWithdrawModel();
            updateModel.setId(bean.getId());
            updateModel.setOutType(bean.getOutType());
            if (bean.getOutType() == 1){
                // check卡商是否有充足的余额来进行下发

                // 首先获取卡商的余额
                MerchantModel merchantQuery = new MerchantModel();
                merchantQuery.setId(bean.getMerchantId());
                MerchantModel merchantModel = merchantService.queryByCondition(merchantQuery);

                // 获取卡商未下发完成的订单总额
                AdminWithdrawModel withdrawQuery = PublicMethod.assembleWithdrawQuery(0,null,null,1,0,0,0,
                        1,merchantModel.getId(),0,0,0);
                String withdrawMoney = adminWithdrawService.sumMoney(withdrawQuery);

                // 查询此次要分配的信息提现订单
                AdminWithdrawModel adminWithdrawQuery = new AdminWithdrawModel();
                adminWithdrawQuery.setId(bean.getId());
                AdminWithdrawModel adminWithdrawModel = adminWithdrawService.queryByCondition(adminWithdrawQuery);
                if (adminWithdrawModel == null || adminWithdrawModel.getId() <= 0){
                    sendFailureMessage(response, "错误,请重试!");
                    return;
                }

                // check卡商余额是否充足
                boolean flag_money = PublicMethod.checkMerchantMoney(merchantModel.getBalance(), adminWithdrawModel.getOrderMoney(), withdrawMoney);
                if (flag_money){
                    updateModel.setMerchantId(bean.getMerchantId());
                }else{
                    sendFailureMessage(response, "您选的卡商余额不足,请重新选择其它卡商!");
                    return;
                }



            }
            updateModel.setWorkType(3);
            adminWithdrawService.updateOutType(updateModel);
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
        AdminWithdrawModel atModel = new AdminWithdrawModel();
        atModel.setId(id);
        model.addAttribute("account", adminWithdrawService.queryById(atModel));
        return "manager/adminwithdraw/adminwithdrawEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files, AdminWithdrawModel bean) throws Exception {
    }


    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, AdminWithdrawModel bean) throws Exception {

    }


    /**
     * 获取数据的详情
     */
    @RequestMapping("/jumpInfo")
    public String jumpInfo(Model model, long id) {
        AdminWithdrawModel atModel = new AdminWithdrawModel();
        atModel.setId(id);
        model.addAttribute("account", adminWithdrawService.queryById(atModel));
        return "manager/adminwithdraw/adminwithdrawInfo";
    }
}
