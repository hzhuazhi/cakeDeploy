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
        <h2>充值审核</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="id" name="id" value="${dl.id}">
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >卡商</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="merchantName" name="merchantName" value="${dl.merchantName}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >卡站点</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="cardSiteName" name="cardSiteName" value="${dl.cardSiteName}" 	maxlength="240" />
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
                        <span class="require" >订单类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="orderType" name="orderType" disabled="disabled">
                            <option value="">===请选择===</option>
                            <c:if test="${dl.orderType == 1}">
                                <option value="1" selected="selected">预付款</option>
                            </c:if>
                            <c:if test="${dl.orderType == 2}">
                                <option value="2" selected="selected">平台</option>
                            </c:if>
                            <c:if test="${dl.orderType == 3}">
                                <option value="3" selected="selected">下发</option>
                            </c:if>
                        </select>

                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >下发订单号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="issueOrderNo" name="issueOrderNo" value="${dl.issueOrderNo}" 	maxlength="240" />
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
                        <span class="require" >订单状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="orderStatus" name="orderStatus" disabled="disabled">
                            <option value="">===请选择===</option>
                            <c:if test="${dl.orderStatus == 1}">
                                <option value="1" selected="selected">初始化</option>
                            </c:if>
                            <c:if test="${dl.orderStatus == 2}">
                                <option value="2" selected="selected">驳回</option>
                            </c:if>
                            <c:if test="${dl.orderStatus == 3}">
                                <option value="3" selected="selected">成功</option>
                            </c:if>
                        </select>

                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">银行</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankName" name="bankName" value="${dl.bankName}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">银行卡号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="bankCard" name="bankCard" value="${dl.bankCard}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">开户人</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="accountName" name="accountName" value="${dl.accountName}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">转账凭证</span>
                    </div>
                    <div class="formCtrlDiv">
                        <img id="pictureAds" name="pictureAds" src="${dl.pictureAds}" alt="">
                    </div>
                </li>


                <div class="formHeadDiv">
                    <h2><font color="red">充值审核操作</font></h2>
                </div>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span><font color="red">*</font>审核状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="checkStatus" name="checkStatus">
                            <option value="0" selected="selected">===请选择===</option>
                            <%--<option value="1">初始化</option>--%>
                            <option value="2">失败</option>
                            <option value="3">成功</option>
                        </select>
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
                        <input type="submit" class="buttonClass imginput" value="审 核" /> <span>
						</span> <input type="reset" class="buttonClass imginput" value="重  置" />
                        <input type="button" onClick="javascript :history.back(-1);" class="buttonClass imginput" value=" 返 回 " />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                checkStatus:{
                    required:true
                }
            },
            messages: {
                checkStatus:{
                    required : "审核状态不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/adminrecharge/check.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("审核完毕！");
                            window.location.href = ctx + "/adminrecharge/list.do";
                        } else {
                            art.alert(data.msg);
                        }
                    },
                    error : function(data) {
                        art.alert(data.info);
                    }
                });
                return false;
                //阻止表单提交
            }
        });
    });
</script>
</body>
</html>