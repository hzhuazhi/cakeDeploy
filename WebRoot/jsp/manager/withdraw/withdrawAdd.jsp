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
        <h2>新增账号</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="merchantId" name="merchantId" value="${dl.id}">

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outBankName" name="outBankName"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>银行卡账号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outBankCard" name="outBankCard"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>开户名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outAccountName" name="outAccountName"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>提现金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="money" name="money"	maxlength="240" />
                    </div>
                </li>

                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require" ><font color="red">*</font>提现手续费（元）</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<input type="text" class="formInput" disabled id="withdrawServiceCharge" name="withdrawServiceCharge" value="5"	maxlength="240" />--%>
                    <%--</div>--%>
                <%--</li>--%>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>指派类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select name="outType" id="outType" disabled>
                            <option value="1" selected>卡商</option>
                            <option value="2">平台</option>
                            <option value="3">中转站</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>订单状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select name="orderStatus" id="orderStatus" disabled>
                            <option value="1" selected>初始化</option>
                            <option value="2">超时</option>
                            <option value="3">质疑</option>
                            <option value="4">成功</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="dataExplain" name="dataExplain"	maxlength="240" />
                    </div>
                </li>

                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="提现" style="background-color: #54D8FE;"/> <span>
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;" />
                       </span>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                merchantId:{
                    required:true,
                    maxlength:10
                },
                outBankName:{
                    required:true,
                    maxlength:36
                },
                outBankCard:{
                    required:false,
                    maxlength:36
                },
                outAccountName:{
                    required:false,
                    maxlength:36
                },
                orderMoney:{
                    required:false,
                    maxlength:10
                }
            },
            messages: {
                merchantId:{
                    required : "'卡商不能为空!",
                    maxlength : "'卡商最多是10个字符!"
                },
                outBankName:{
                    required:"银行名称不能为空!",
                    number:"银行名称最多是36个字符!"
                },
                outBankCard:{
                    required:"银行卡号不能为空!",
                    number:"银行卡号最多是36个字符!"
                },
                outAccountName:{
                    required:"'开户人不能为空!",
                    number:"开户人最多是36个字符!"
                },
                orderMoney:{
                    required:"提现金额不能为空!",
                    number:"提现金额最多是10个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/merchantwithdraw/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
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