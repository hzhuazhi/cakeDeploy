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
            <h2>编辑账号</h2>
        </div>
        <div class="formContentDiv">
            <form id="addSupplierForm">
                <ul>
                    <c:set var="dl" value="${account}"/>
                    <input type="hidden" id="id" name="id" value="${dl.id}">
                    <input type="hidden" id="op" name="op" value="1">
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>手机卡别名</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="alias" name="alias" value="${dl.alias}"	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>手机号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="mobileCardId" name="mobileCardId" class='text-input medium-input' >
                                <option value="">===请选择===</option>
                                <c:forEach items="${mobile}" var="dataList">
                                    <c:choose>
                                        <c:when test="${dl.mobileCardId == dataList.id}">
                                            <option selected="selected" value="${dataList.id}">${dataList.phoneNum}</option>
                                        </c:when>
                                        <c:when test="${dl.mobileCardId != dataList.id}">
                                            <option value="${dataList.id}">${dataList.phoneNum}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>

                            <%--<input type="text" class="formInput" id="phoneNum" name="phoneNum" value="${dl.phoneNum}"	maxlength="240" />--%>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>银行卡账号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="bankCard" name="bankCard" value="${dl.bankCard}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>银行名称</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="bankTypeId" name="bankTypeId" class='text-input medium-input' >
                                <option value="">===请选择===</option>
                                <c:forEach items="${type}" var="dataList">
                                    <c:choose>
                                        <c:when test="${dl.bankTypeId == dataList.id}">
                                            <option selected="selected" value="${dataList.id}">${dataList.bankName}</option>
                                        </c:when>
                                        <c:when test="${dl.bankTypeId != dataList.id}">
                                            <option value="${dataList.id}">${dataList.bankName}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <%--<input type="text" class="formInput" id="bankName" name="bankName" value="${dl.bankName}" disabled maxlength="240" />--%>
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>开户名</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="accountName" name="accountName" value="${dl.accountName}" 	maxlength="240" />
                        </div>
                    </li>



                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>凭证短信</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="smsNum" name="smsNum" value="${dl.smsNum}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>凭证尾号</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="lastNum" name="lastNum" value="${dl.lastNum}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>银行码</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="bankCode" name="bankCode" value="${dl.bankCode}" 	maxlength="500" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require"><font color="red">*</font>收款日限金额</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="inDayMoney" name="inDayMoney" value="${dl.inDayMoney}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>收款月限金额</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="inMonthMoney" name="inMonthMoney" value="${dl.inMonthMoney}" 	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>卡状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="isArrears" name="isArrears">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.isArrears == 1}">
                                    <option value="1" selected="selected">未欠费</option>
                                    <option value="2">欠费</option>
                                </c:if>
                                <c:if test="${dl.isArrears == 2}">
                                    <option value="1" >未欠费</option>
                                    <option value="2" selected="selected">欠费</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>检测状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="checkStatus" name="checkStatus">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.checkStatus == 1}">
                                    <option value="1" selected="selected">初始化</option>
                                    <option value="2">不正常</option>
                                </c:if>
                                <c:if test="${dl.checkStatus == 2}">
                                    <option value="1" >初始化</option>
                                    <option value="2" selected="selected">不正常</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red"></font>限制的原因</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="dataExplain" name="dataExplain" value="${dl.dataExplain}" 	maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>测试状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="isOk" name="isOk">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.isOk == 1}">
                                    <option value="1" selected="selected">未通过</option>
                                    <option value="2">通过</option>
                                </c:if>
                                <c:if test="${dl.isOk == 2}">
                                    <option value="1" >未通过</option>
                                    <option value="2" selected="selected">通过</option>
                                </c:if>
                            </select>
                        </div>
                    </li>


                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>使用状态</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="useStatus" name="useStatus">
                                <option value="">===请选择===</option>
                                <c:if test="${dl.useStatus == 1}">
                                    <option value="1" selected="selected">正常使用</option>
                                    <option value="2">暂停使用</option>
                                </c:if>
                                <c:if test="${dl.useStatus == 2}">
                                    <option value="1" >正常使用</option>
                                    <option value="2" selected="selected">暂停使用</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li>
                        <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                            <input type="submit" class="formBtn" style="background-color: #54D8FE" value="修 改" /> <span>
						</span>
                            <input type="button" onClick="javascript :history.back(-1);" style="background-color: #54D8FE" class="formBtn" value=" 返 回 " />
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
                phoneNum:{
                    required:true,
                    maxlength:20
                },
                bankCard:{
                    required:true,
                    maxlength:20
                },
                bankTypeId:{
                    required:true,
                    maxlength:2,
                },
                accountName:{
                    required:true,
                    maxlength:20,
                },
                smsNum:{
                    required:true,
                    maxlength:20,
                },
                lastNum:{
                    required:true,
                    maxlength:4,
                },
                bankCode:{
                    required:true,
                    maxlength:10,
                },
                inDayMoney:{
                    required:true,
                    maxlength:12,
                },
                inMonthMoney:{
                    required:true,
                    maxlength:12,
                },
                isArrears:{
                    required:true,
                    maxlength:2,
                },
                checkStatus:{
                    required:true,
                    maxlength:2,
                },
                isOk:{
                    required:true,
                    maxlength:2,
                },
                useStatus:{
                    required:true,
                    maxlength:2,
                }
            },
            messages: {
                phoneNum:{
                    required : "手机号不能为空!",
                    maxlength : "手机号长度最多是20个字符!"
                },
                bankCard:{
                    required : "银行卡账号不能为空!",
                    maxlength : "银行卡账号长度最多是20个字符!"
                },
                bankTypeId:{
                    required : "银行名称不能为空!",
                    maxlength : "银行名称长度最多是2个字符!",
                },
                accountName:{
                    required : "开户名不能为空!",
                    maxlength : "开户名长度最多是20个字符!"
                },
                smsNum:{
                    required : "凭证短信不能为空!",
                    maxlength : "凭证短信长度最多是20个字符!",
                },
                lastNum:{
                    required : "凭证尾号不能为空!",
                    maxlength : "凭证尾号长度最多是10个字符!"
                },
                bankCode:{
                    required : "银行码不能为空!",
                    maxlength : "银行码长度最多是20个字符!",
                },
                inDayMoney:{
                    required : "收款日限金额不能为空!",
                    maxlength : "收款日限金额长度最多是12个字符!"
                },
                inMonthMoney:{
                    required : "收款月限金额不能为空!",
                    maxlength : "收款月限金额长度最多是12个字符!",
                },
                isArrears:{
                    required : "卡状态不能为空!",
                    maxlength : "卡状态长度最多是2个字符!"
                },
                checkStatus:{
                    required : "检测状态不能为空!",
                    maxlength : "检测状态长度最多是2个字符!",
                },
                isOk:{
                    required : "测试状态不能为空!",
                    maxlength : "测试状态长度最多是2个字符!"
                },
                useStatus:{
                    required : "使用状态不能为空!",
                    maxlength : "使用状态长度最多是2个字符!",
                },

            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/bank/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/bank/list.do";
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