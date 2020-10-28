package com.xn.manager.controller.merchant;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.common.util.HttpSendUtils;
import com.xn.common.util.OssUploadUtil;
import com.xn.common.util.StringUtil;
import com.xn.manager.model.ChannelReplenishModel;
import com.xn.manager.model.MerchantReplenishModel;
import com.xn.manager.model.OrderModel;
import com.xn.manager.service.MerchantReplenishService;
import com.xn.manager.service.OrderService;
import com.xn.system.entity.Account;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 卡商审核补单申请的Controller层
 * @Author yoko
 * @Date 2020/10/12 19:26
 * @Version 1.0
 */
@Controller
@RequestMapping("/merchantreplenish")
public class MerchantReplenishController extends BaseController {

    private static Logger log = Logger.getLogger(MerchantReplenishController.class);

    public static String fruitUrl = "http://localhost:8002/payDeploy/channelreplenish/actionUpdateCheck.do?";
//public static String fruitUrl = "http://192.168.1.52:8080/channelreplenish/actionUpdateCheck.do?";
    @Autowired
    private MerchantReplenishService<MerchantReplenishModel> merchantReplenishService;

    @Autowired
    private OrderService<OrderModel> orderService;


    /**
     * 获取页面
     */
    @RequestMapping("/list")
    public String list() {
        return "manager/merchantreplenish/merchantreplenishIndex";
    }


    /**
     *
     * 获取表格数据列表
     */
    @RequestMapping("/dataList")
    public void dataList(HttpServletRequest request, HttpServletResponse response, MerchantReplenishModel model) throws Exception {
        List<MerchantReplenishModel> dataList = new ArrayList<MerchantReplenishModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (account.getRoleId() == 2){
                    // 卡商
                    model.setAccountId(account.getId());
                }else if (account.getRoleId() == 3){
                    // 卡站点
                    model.setCardSiteId(account.getId());
                }
            }
            dataList = merchantReplenishService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }


    /**
     *
     * 获取表格数据列表-无分页
     */
    @RequestMapping("/dataAllList")
    public void dataAllList(HttpServletRequest request, HttpServletResponse response, MerchantReplenishModel model) throws Exception {
        List<MerchantReplenishModel> dataList = new ArrayList<MerchantReplenishModel>();
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            if (account.getRoleId() != ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ONE){
                if (account.getRoleId() == 2){
                    // 卡商
                    model.setAccountId(account.getId());
                    log.info("");
                }else if (account.getRoleId() == 3){
                    // 卡站点
                    model.setCardSiteId(account.getId());
                }
            }
            dataList = merchantReplenishService.queryAllList(model);
        }
        HtmlUtil.writerJson(response, dataList);
    }

    /**
     * 获取新增页面
     */
    @RequestMapping("/jumpAdd")
    public String jumpAdd(HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("rloeMenu", roleService.queryList());
        return "manager/merchantreplenish/merchantreplenishAdd";
    }

    /**
     * 添加数据
     */
    @RequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, MerchantReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantReplenishService.add(bean);
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
        MerchantReplenishModel atModel = new MerchantReplenishModel();
        atModel.setId(id);
        model.addAttribute("account", merchantReplenishService.queryById(atModel));
        return "manager/merchantreplenish/merchantreplenishEdit";
    }

    /**
     * 修改数据-只能修改备注
     */
    @RequestMapping("/update")
    public void update(HttpServletRequest request, HttpServletResponse response, MerchantReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MerchantReplenishModel update = new MerchantReplenishModel();
            update.setId(bean.getId());
            if (!StringUtils.isBlank(bean.getRemark())){
                update.setRemark(bean.getRemark());
            }
            merchantReplenishService.update(update);
            sendSuccessMessage(response, "保存成功~");
        }else {
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }

    /**
     * 删除数据
     */
    @RequestMapping("/delete")
    public void delete(HttpServletRequest request, HttpServletResponse response, MerchantReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantReplenishService.delete(bean);
            sendSuccessMessage(response, "删除成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }

    }

    /**
     * 启用/禁用
     */
    @RequestMapping("/manyOperation")
    public void manyOperation(HttpServletRequest request, HttpServletResponse response, MerchantReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            merchantReplenishService.manyOperation(bean);
            sendSuccessMessage(response, "状态更新成功");
        }else{
            sendFailureMessage(response, "登录超时,请重新登录在操作!");
        }
    }


    /**
     * 获取修改页面-审核处理
     */
    @RequestMapping("/jumpUpdateCheck")
    public String jumpUpdateCheck(Model model, long id) {
        MerchantReplenishModel atModel = new MerchantReplenishModel();
        atModel.setId(id);
        model.addAttribute("account", merchantReplenishService.queryById(atModel));
        return "manager/merchantreplenish/merchantreplenishCheck";
    }

    /**
     * 修改数据-修改审核
     */
    @RequestMapping("/updateCheck")
    public void updateCheck(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile files, MerchantReplenishModel bean) throws Exception {
        Account account = (Account) WebUtils.getSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
        if(account !=null && account.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
            MerchantReplenishModel update = new MerchantReplenishModel();
            update.setId(bean.getId());
            String pictureAds = "";
            if (!files.isEmpty()){
                pictureAds = OssUploadUtil.localMethod(files);
                if (StringUtils.isBlank(pictureAds)){
                    sendFailureMessage(response, "图片上传失败,请重试!");
                    return;
                }
                update.setCheckPictureAds(pictureAds);
            }

            update.setCheckStatus(bean.getCheckStatus());
            String checkInfo = "";
            if (!StringUtils.isBlank(bean.getCheckInfo())){
                update.setCheckInfo(bean.getCheckInfo());
//                checkInfo = StringUtil.mergeCodeBase64(bean.getCheckInfo());
                checkInfo = URLEncoder.encode( bean.getCheckInfo(), "UTF-8" );

            }
            update.setHandleType(2);
            update.setHandlePeople(account.getId());
            if (!StringUtils.isBlank(bean.getRemark())){
                update.setRemark(bean.getRemark());
            }
            merchantReplenishService.updateCheck(update);

            // 把审核结果反馈给支付平台
            String sendUrl = fruitUrl;
//            String sendData = "linkId=" + bean.getId() + "&outTradeNo=" + bean.getMyTradeNo() + "&pictureAds=" + pictureAds;
//            String urlData = URLEncoder.encode( "你ad好牛￥逼11的33#@呢gsjs来补咯？", "UTF-8" );
//            String sendData = "id=" + bean.getLinkId() + "&checkMoney=" + bean.getChannelMoney() + "&checkPictureAds=" + pictureAds + "&checkStatus=" + bean.getCheckStatus() + "&checkInfo=" + checkInfo +
//                    "&temp=你好的哦哈哈" + "&urlData=" + urlData;
//            String sendData = "id=" + bean.getLinkId() + "&checkMoney=" + bean.getChannelMoney() + "&checkPictureAds=" + pictureAds + "&checkStatus=" + bean.getCheckStatus() + "&checkInfo=" + checkInfo;
//            String resp = HttpSendUtils.sendGet(sendUrl + sendData, null, null);
            ChannelReplenishModel channelReplenishUpdate = new ChannelReplenishModel();
            channelReplenishUpdate.setId(bean.getLinkId());
            if (!StringUtils.isBlank(bean.getChannelMoney())){
                channelReplenishUpdate.setChannelMoney(bean.getChannelMoney());
            }
            if (!StringUtils.isBlank(pictureAds)){
                channelReplenishUpdate.setCheckPictureAds(pictureAds);
            }
            channelReplenishUpdate.setCheckStatus(bean.getCheckStatus());
            if (!StringUtils.isBlank(bean.getCheckInfo())){
                channelReplenishUpdate.setCheckInfo(bean.getCheckInfo());
            }
            merchantReplenishService.updateChannelCheck(channelReplenishUpdate);
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
        MerchantReplenishModel atModel = new MerchantReplenishModel();
        atModel.setId(id);
        model.addAttribute("account", merchantReplenishService.queryById(atModel));
        return "manager/merchantreplenish/merchantreplenishInfo";
    }


    /**
     *
     * 新增商户补单申请数据-对外接口
     */
    @RequestMapping(value = "/actionAdd", method = {RequestMethod.GET})
    public void actionAdd(HttpServletRequest request, HttpServletResponse response,MerchantReplenishModel merchantReplenishModel) throws Exception{
        if (merchantReplenishModel != null && merchantReplenishModel.getLinkId() > 0){
            if (!StringUtils.isBlank(merchantReplenishModel.getOutTradeNo())){
                 // 根据支付平台订单号查询订单信息
                OrderModel orderQuery = new OrderModel();
                orderQuery.setOutTradeNo(merchantReplenishModel.getOutTradeNo());
                OrderModel orderModel = orderService.queryByCondition(orderQuery);
                if (orderModel != null && orderModel.getId() > 0){
                    merchantReplenishModel.setAccountId(orderModel.getAccountId());
                    if (orderModel.getCardSiteId() != null && orderModel.getCardSiteId() > 0){
                        merchantReplenishModel.setCardSiteId(orderModel.getCardSiteId());
                    }
                    merchantReplenishModel.setChannelId(orderModel.getChannelId());
                    merchantReplenishModel.setOrderNo(orderModel.getOrderNo());
                    merchantReplenishModel.setOrderMoney(orderModel.getOrderMoney());
                    merchantReplenishModel.setDistributionMoney(orderModel.getDistributionMoney());
                    merchantReplenishService.add(merchantReplenishModel);
                }else {
                    // 返回数据给客户端
                    PrintWriter out = response.getWriter();
                    out.print("on");
                    log.info("");
                    out.flush();
                    out.close();
                    return;
                }
            }else{
                // 返回数据给客户端
                PrintWriter out = response.getWriter();
                log.info("");
                out.print("on");
                out.flush();
                out.close();
                return;
            }
            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print("ok");
            out.flush();
            out.close();
            return;
        }else {
            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print("on");
            out.flush();
            out.close();
            return;
        }

    }

}
