package com.xn.manager.controller.merchant;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.OssUploadUtil;
import com.xn.manager.model.ChannelModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantServiceChargeModel;
import com.xn.manager.model.MerchantServiceChargeModel;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.MerchantServiceChargeService;
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
 * @Description 管理员角色的卡商绑定渠道的手续费的Controller层
 * @Author yoko
 * @Date 2020/11/26 19:38
 * @Version 1.0
 */
@Controller
@RequestMapping("/adminmerchantservicecharge")
public class AdminMerchantServiceChargeController extends BaseController {

    private static Logger log = Logger.getLogger(AdminMerchantServiceChargeController.class);


    @Autowired
    private MerchantServiceChargeService<MerchantServiceChargeModel> merchantServiceChargeService;

    @Autowired
    private MerchantService<MerchantModel> merchantService;

    @Autowired
    private ChannelService<ChannelModel> channelService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/adminmerchantservicecharge/adminmerchantservicechargeIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantServiceChargeModel model) throws Exception {
        List<MerchantServiceChargeModel> dataList = new ArrayList<MerchantServiceChargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleType() != 1 ){
                HtmlUtil.writerJson(response, model.getPage(), dataList);
            }
            dataList = merchantServiceChargeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantServiceChargeModel model) throws Exception {
        List<MerchantServiceChargeModel> dataList = new ArrayList<MerchantServiceChargeModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleType() != 1 ){
                HtmlUtil.writerJson(response, dataList);
            }
            dataList = merchantServiceChargeService.queryAllList();
        }
        HtmlUtil.writerJson(response, dataList);
    }


    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("merchantList", merchantService.queryAllList());
        model.addAttribute("channelList", channelService.queryAllList());
        return "manager/adminmerchantservicecharge/adminmerchantservicechargeAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantServiceChargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            // check 是否有重复纪录
            MerchantServiceChargeModel merchantServiceChargeQuery = new MerchantServiceChargeModel();
            merchantServiceChargeQuery.setMerchantId(bean.getMerchantId());
            merchantServiceChargeQuery.setChannelId(bean.getChannelId());
            MerchantServiceChargeModel merchantServiceChargeModel = merchantServiceChargeService.queryByCondition(merchantServiceChargeQuery);
            if (merchantServiceChargeModel != null || merchantServiceChargeModel.getId() > 0){
                sendFailureMessage(response,"有重复纪录,添加失败!");
                return;
            }
            merchantServiceChargeService.add(bean);
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
        MerchantServiceChargeModel atModel = new MerchantServiceChargeModel();
        atModel.setId(id);
        model.addAttribute("account", merchantServiceChargeService.queryById(atModel));
        model.addAttribute("merchantList", merchantService.queryAllList());
        model.addAttribute("channelList", channelService.queryAllList());
        return "manager/adminmerchantservicecharge/adminmerchantservicechargeEdit";
    }

    /**
     * 修改数据
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response,MerchantServiceChargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            // check 是否有重复纪录
            MerchantServiceChargeModel merchantServiceChargeQuery = new MerchantServiceChargeModel();
            merchantServiceChargeQuery.setMerchantId(bean.getMerchantId());
            merchantServiceChargeQuery.setChannelId(bean.getChannelId());
            MerchantServiceChargeModel merchantServiceChargeModel = merchantServiceChargeService.queryByCondition(merchantServiceChargeQuery);
            if (merchantServiceChargeModel != null || merchantServiceChargeModel.getId() > 0){
                if (merchantServiceChargeModel.getId() != bean.getId()){
                    sendFailureMessage(response,"有重复纪录,更新失败!");
                    return;
                }

            }
            merchantServiceChargeService.update(bean);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }
    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MerchantServiceChargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantServiceChargeService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantServiceChargeModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantServiceChargeService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }
}
