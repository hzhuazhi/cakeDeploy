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
                            <span class="require" ><font color="red">*</font>名称/别名 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="alias" name="alias"	value="${dl.alias}" maxlength="240" />
                        </div>
                    </li>
                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>账号 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="accountNum" name="accountNum"	value="${dl.accountNum}"  disabled maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >账号名称 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="acName" name="acName"	value="${dl.acName}"  disabled maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >登陆密码 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="passWd" name="passWd"	 maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >提现密码 </span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="withdrawPassWd" name="withdrawPassWd"	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >保底金额</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="leastMoney" name="leastMoney" value="${dl.leastMoney}"	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >手续比例</span>
                        </div>
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="serviceCharge" name="serviceCharge" value="${dl.serviceCharge}"	maxlength="240" />
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" >卡商类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="merchantType" name="merchantType">
                                <c:if test="${dl.merchantType == 1}">
                                    <option value="1" selected="selected">我方卡商</option>
                                    <option value="2">第三方卡商</option>
                                </c:if>
                                <c:if test="${dl.merchantType == 2}">
                                    <option value="1" >我方卡商</option>
                                    <option value="2" selected="selected">第三方卡商</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>卡商运营类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="operateType" name="operateType">
                                <c:if test="${dl.operateType == 1}">
                                    <option value="1" selected="selected">代收</option>
                                    <option value="2">代付</option>
                                </c:if>
                                <c:if test="${dl.operateType == 2}">
                                    <option value="1" >代收</option>
                                    <option value="2" selected="selected">代付</option>
                                </c:if>
                            </select>
                        </div>
                    </li>

                    <li style="border-top: none;">
                        <div class="formTextDiv">
                            <span class="require" ><font color="red">*</font>代付款类型</span>
                        </div>
                        <div class="formCtrlDiv">
                            <select id="payType" name="payType">
                                <c:if test="${dl.payType == 1}">
                                    <option value="1" selected="selected">手动付款</option>
                                    <option value="2">API自动付款</option>
                                </c:if>
                                <c:if test="${dl.payType == 2}">
                                    <option value="1" >手动付款</option>
                                    <option value="2" selected="selected">API自动付款</option>
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
            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/merchant/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/merchant/list.do";
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