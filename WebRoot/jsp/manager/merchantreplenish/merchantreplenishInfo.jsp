<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
    <script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
    <link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
    <style type="text/css">
        .manage-wrap{background-color: #E2E0DB;display: inline-block;vertical-align: top; font-size: 12px;padding: 0;width: 140px;height: 30px;line-height: 30px;margin: 0 20px 10px 0;}
        .manage-wrap > input[type='checkbox']{margin: 0 10px;vertical-align: middle;-webkit-appearance: none;appearance: none;width: 13px;height: 13px;cursor: pointer;background: #fff;border: 1px solid B9BBBE;-webkit-border-radius: 1px;-moz-border-radius: 1px;border-radius: 1px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;}
        .manage-wrap > input[type=checkbox]:active{border-color: #c6c6c6;background: #ebebeb;}
        .manage-wrap > input[type=checkbox]:checked::after {content: url(${ctxData}images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
        .manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
        .borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
    </style>
</head>
<body>
<div class="col_main">
    <div class="formHeadDiv">
        <h2>商户补单处理详情</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="id" name="id" value="${dl.id}">

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span><font color="red">处理状态</font></span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="handleType" name="handleType" disabled="disabled">
                            <option value="">===请选择===</option>
                            <c:if test="${dl.handleType == 1}">
                                <option value="1" selected="selected">未处理</option>
                            </c:if>
                            <c:if test="${dl.handleType == 2}">
                                <option value="2" selected="selected">已处理</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">处理人</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="handlePeople" name="handlePeople" value="${dl.handlePeople}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >订单号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="orderNo" name="orderNo" value="${dl.orderNo}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >商家订单</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outTradeNo" name="outTradeNo" value="${dl.outTradeNo}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">订单金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="orderMoney" name="orderMoney" value="${dl.orderMoney}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">派单金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="distributionMoney" name="distributionMoney" value="${dl.distributionMoney}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">卡商</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="merchantName" name="merchantName" value="${dl.merchantName}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">卡站点</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="cardSiteName" name="cardSiteName" value="${dl.cardSiteName}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">商户</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="channelName" name="channelName" value="${dl.channelName}" 	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">商户转账凭证</span>
                    </div>
                    <div class="formCtrlDiv">
                        <img id="pictureAds" name="pictureAds" src="${dl.pictureAds}" alt="">
                    </div>
                </li>


                <div class="formHeadDiv">
                    <h2><font color="red">卡商补单审核操作</font></h2>
                </div>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span><font color="red">审核状态</font></span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="checkStatus" name="checkStatus" disabled="disabled">
                            <option value="">===请选择===</option>
                            <c:if test="${dl.checkStatus == 1}">
                                <option value="1" selected="selected">初始化</option>
                            </c:if>
                            <c:if test="${dl.checkStatus == 2}">
                                <option value="2" selected="selected">失败</option>
                            </c:if>
                            <c:if test="${dl.checkStatus == 3}">
                                <option value="3" selected="selected">成功</option>
                            </c:if>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">卡商反馈凭证</span>
                    </div>
                    <div class="formCtrlDiv">
                        <img id="checkPictureAds" name="checkPictureAds" src="${dl.checkPictureAds}" alt="">
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">审核失败说明</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="checkInfo" name="checkInfo" cols="70" rows="9">${dl.checkInfo}</textarea>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="remark" name="remark" cols="70" rows="9">${dl.remark}</textarea>
                    </div>
                </li>



                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="button" onClick="javascript :history.back(-1);" class="dataTableBtn dataTableDeleteBtn" value=" 返 回 " />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">

</script>
</body>
</html>