package com.xn.manager.controller.merchant;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.manager.model.ChannelModel;
import com.xn.manager.model.MerchantModel;
import com.xn.manager.model.MerchantServiceChargeModel;
import com.xn.manager.service.ChannelService;
import com.xn.manager.service.MerchantService;
import com.xn.manager.service.MerchantServiceChargeService;
import com.xn.system.entity.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 卡商绑定渠道的手续费的Controller层
 * @Author yoko
 * @Date 2020/11/27 11:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/merchantservicecharge")
public class MerchantServiceChargeController extends BaseController {

    private static Logger log = Logger.getLogger(MerchantServiceChargeController.class);


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
        return "manager/merchantservicecharge/merchantservicechargeIndex";
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
                model.setMerchantId(account.getId());
            }
            model.setUseStatus(1);
            dataList = merchantServiceChargeService.queryByList(model);
        }
        HtmlUtil.writerJson(response, model.getPage(), dataList);
    }
}
