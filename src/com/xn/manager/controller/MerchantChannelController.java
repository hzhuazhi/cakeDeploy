package com.xn.manager.controller;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.OssUploadUtil;
import com.xn.manager.model.*;
import com.xn.manager.service.*;
import com.xn.system.entity.Account;
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
import java.util.*;

/**
 * @Description TODO
 * @Date 2020/9/21 18:17
 * @Version 1.0
 */

@Controller
@RequestMapping("/merchantchannel")
public class MerchantChannelController extends BaseController {
    private static Logger log = Logger.getLogger(MerchantChannelController.class);
    @Autowired
    private MerchantChannelService<MerchantChannelModel> merchantChannelService;

    @Autowired
    private MerchantService<MerchantModel> merchantlService;


    @Autowired
    private ChannelService<ChannelModel> channelService;
    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchantchannel/merchantchannelIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, ChannelModel model) throws Exception {
        List<ChannelModel> dataList = new ArrayList<ChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            dataList = channelService.queryByList(model);

            for(ChannelModel merchantChannelModel:dataList ){
                String bankCardInfo  =  merchantChannelService.byIdQueryMerchant(merchantChannelModel.getId());
                merchantChannelModel.setMerchantStr(bankCardInfo);
            }
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel model) throws Exception {
        List<MerchantChannelModel> dataList = new ArrayList<MerchantChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                //不是管理员，只能查询自己的数据
                model.setMerchantId(account.getId());
            }
            dataList = merchantChannelService.queryAllList(model);
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
            model.addAttribute("op", account);
        }else {
            sendFailureMessage(response,"登录超时,请重新登录在操作!");
        }
        return "manager/merchantchannel/merchantchannelAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            String  []  merchantIdStr = bean.getMerchantStr().split(",");
            for(String merchantId:merchantIdStr){
                MerchantChannelModel  merchantChannelModel  = new MerchantChannelModel();
                merchantChannelModel.setMerchantId(Long.parseLong(merchantId));
                merchantChannelModel.setChannelId(bean.getChannelId());
                merchantChannelModel.setCreateRoleId(account.getRoleId());
                merchantChannelModel.setCreateUserId(account.getId());
                merchantChannelService.add(merchantChannelModel);
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
        MerchantChannelModel atModel = new MerchantChannelModel();
        atModel.setId(id);
        model.addAttribute("account", merchantChannelService.queryById(atModel));
        model.addAttribute("op", op);
        return "manager/merchantchannel/merchantchannelEdit";
    }



    @RequestMapping("/chechData")
    public void chechData(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel model) throws Exception {
        Map<String,String> map  = new HashMap<>() ;
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            OrderOutModel atModel = new OrderOutModel();
            atModel.setId(model.getId());

            map.put("type","1");
            map.put("rs","/merchantchannel/jumpUpdate.do?op=1&id="+model.getId());
            HtmlUtil.writerJson(response, map);
        }else{
            map.put("type","2");
            map.put("rs","登录超时,请重新登录在操作！");
            HtmlUtil.writerJson(response, map);
        }

    }


    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            bean.setUpdateRoleId(account.getRoleId());
            bean.setUpdateUserId(account.getId());
            merchantChannelService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantChannelService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    @RequestMapping("/jumpBankUpdate")
    public String jumpBankUpdate(Model model, long id, Integer op) {
        MerchantChannelModel atModel = new MerchantChannelModel();
        atModel.setChannelId(id);
        model.addAttribute("account", atModel);
        return "manager/merchantchannel/merchantchannelQuery";
    }



    /**
     * 根据不再条件查询 该条件下的银行卡信息
     */
//    @RequestMapping("/queryNotChannelBankList")
//    public void queryNotChannelBankList(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel model) throws Exception {
//        List<MerchantChannelModel> dataList = new ArrayList<MerchantChannelModel>();
//        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
//        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
////            queryBean.setId(model.getId());
//            MerchantChannelModel  queryBean = new MerchantChannelModel();
//            dataList = merchantChannelService.queryByAll(queryBean);
//            List<Long>   bankIdList = new ArrayList<>();
//            for(MerchantChannelModel merchantChannelModel:dataList){
//                bankIdList.add(merchantChannelModel.getMerchantId());
//            }
//            queryBean.setBankIdList(bankIdList);
//            dataList = merchantChannelService.queryNotChannelBankAll(queryBean);
//        }
//        HtmlUtil.writerJson(response, model.getPage(), dataList);
//    }



    /**
     *
     * 查询不再列表里面的数据，绑定过的卡商信息
     */
    @RequestMapping("/queryNotChannelBankList")
    public void queryNotChannelBankList(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel model) throws Exception {
        List<MerchantModel> rsList= new ArrayList<MerchantModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            //查询已经绑定的卡商信息
            List<MerchantChannelModel> bindDataList = merchantChannelService.queryisAllList();

            List<Long>    merchantIdList   =   new ArrayList<>();
            for(MerchantChannelModel merchantChannelModel:bindDataList){
                merchantIdList.add(merchantChannelModel.getMerchantId());
            }

            MerchantModel  merchantModel  = new  MerchantModel();
            merchantModel.setMerchantIdList(merchantIdList);
            //去除查询出来的卡商显示
            rsList  = merchantlService.queryNotAllList(merchantModel);

        }
        HtmlUtil.writerJson(response, model.getPage(), rsList);
    }




    /**
     * 根据 条件查询 该条件下的银行卡信息
     */
    @RequestMapping("/queryIdList")
    public void queryIdList(HttpServletRequest request, HttpServletResponse response, MerchantChannelModel model) throws Exception {
        List<MerchantChannelModel> dataList = new ArrayList<MerchantChannelModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MerchantChannelModel  queryBean = new MerchantChannelModel();
            queryBean.setChannelId(model.getChannelId());
            dataList = merchantChannelService.queryByAll(queryBean);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


}
