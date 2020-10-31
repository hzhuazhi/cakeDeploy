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
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>订单金额</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="orderMoney" name="orderMoney"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >中转站订单号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outTradeNo" name="outTradeNo"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>订单状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select name="orderStatus" id="orderStatus">
                            <option value="1">初始化</option>
                            <option value="2">超时</option>
                            <option value="3">质疑</option>
                            <option value="4">成功</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>提现类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <div class="formCtrlDiv">
                            <select name="withdrawType" id="withdrawType">
                                <option value="1">归属渠道</option>
                                <option value="2">归属子渠道</option>
                                <option value="3">归属支付通道</option>
                                <option value="4">归属卡商</option>
                                <option value="5">归属利益人</option>
                            </select>
                        </div>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>提现归属人账户ID</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="ascriptionId" name="ascriptionId"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>指派类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select name="outType" id="outType">
                            <option value="1">卡商</option>
                            <option value="2">平台</option>
                            <option value="3">中转站</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>下发卡商id</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="merchantId" name="merchantId"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>付款银行名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outBankName" name="outBankName"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>付款银行卡账号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outBankCard" name="outBankCard"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>付款开户名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outAccountName" name="outAccountName"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>审核状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select name="checkStatus" id="checkStatus">
                            <option value="1">初始化</option>
                            <option value="2">审核收款失败</option>
                            <option value="3">审核收款成功</option>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >数据说明</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="dataExplain" name="dataExplain"	maxlength="240" />
                    </div>
                </li>

                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="添  加" style="background-color: #54D8FE;"/> <span>
						</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>
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
                orderMoney:{
                    required:true,
                    maxlength:10
                },
                orderStatus:{
                    required:true,
                    maxlength:1
                },
                withdrawType:{
                    required:false,
                    maxlength:1
                },
                outType:{
                    required:false,
                    maxlength:20
                },
                merchantId:{
                    required:false,
                    maxlength:20
                },
                outBankName:{
                    required:false,
                    maxlength:20
                },
                outBankCard:{
                    required:false,
                    maxlength:36
                },
                outAccountName:{
                    required:false,
                    maxlength:20
                }
            },
            messages: {
                orderMoney:{
                    required : "'类型名称不能为空!",
                    maxlength : "'类型名称最多是20个字符!"
                },
                orderStatus:{
                    required:"订单状态不能为空!"
                },
                withdrawType:{
                    required:"提现类型不能为空!"
                },
                outType:{
                    required:"'指派类型不能为空!"
                },
                merchantId:{
                    required:"下发卡商id称不能为空!",
                    number:"下发卡商id最多是20个字符!"
                },
                outBankName:{
                    required:"付款银行卡账号不能为空!",
                    number:"付款银行卡账号最多是36个字符!"
                },
                outBankCard:{
                    required:"付款开户名不能为空!",
                    number:"付款开户名长度最多是20个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                alert(ctx+ "/withdraw/add.do");
                $.ajax({
                    url : ctx+ "/withdraw/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/withdraw/list.do";
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