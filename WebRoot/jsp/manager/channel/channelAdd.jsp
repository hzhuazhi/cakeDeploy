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
                        <span class="require" ><font color="red">*</font>商户名称 </span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="alias" name="alias"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>商户秘钥</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="secretKey" name="secretKey"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>绑定类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="bankBindingType" name="bankBindingType">
                            <option value="1" selected>无需绑定银行卡</option>
                            <option value="2">需要绑定银行卡</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>商户类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="channelType" name="channelType">
                            <option value="" selected="selected">===请选择===</option>
                            <option value="1">代收</option>
                            <option value="2">大包</option>
                            <option value="3">代付</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <font color="red">
                    代收金额订单范围：金额范围以“-”进行分割的
                    </font>
                    <div class="formTextDiv">
                        <span class="require" >代收金额范围</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="inMoneyRange" name="inMoneyRange"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <font color="red">
                        代付金额订单范围：金额范围以“-”进行分割的
                    </font>
                    <div class="formTextDiv">
                        <span class="require" >代付金额范围</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="outMoneyRange" name="outMoneyRange"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >订单支付时间</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="invalidTimeNum" name="invalidTimeNum"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >银行卡锁定时间</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="moneyLockTime" name="moneyLockTime"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">代付方式</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="replacePayType" name="replacePayType">
                            <option value="0" selected="selected">===请选择===</option>
                            <option value="1">直接转账</option>
                            <option value="2">预备转账</option>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="remark" name="remark"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>使用状态</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="useStatus" name="useStatus">
                            <option value="1">正常使用</option>
                            <option value="2">暂停使用</option>
                        </select>
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
                alias:{
                    required:true,
                    maxlength:20
                },
                secretKey:{
                    required:true,
                    maxlength:64
                },
                bankBindingType:{
                    required:true,
                    maxlength:2
                },
                channelType:{
                    required:true
                },
                useStatus:{
                    required:true,
                    maxlength:1
                }
            },
            messages: {
                alias:{
                    required : "商户名称为空!",
                    maxlength : "商户名称最多是20个字符!"
                },
                secretKey:{
                    required : "商户秘钥不能为空!",
                    maxlength : "商户秘钥最多是20个字符!"
                },
                bankBindingType:{
                    required : "绑定类型不能为空!"
                },
                channelType:{
                    required : "商户类型不能为空!"
                },
                useStatus:{
                    required:"使用状态不能为空!",
                    number:"使用状态最多是20个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/channel/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/channel/list.do";
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