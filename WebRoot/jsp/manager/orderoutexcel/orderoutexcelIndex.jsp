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
        <h2><font color="red">代付结果Excel导入</font></h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm" method="POST" enctype="multipart/form-data"
              action='${ctxData}orderoutexcel/orderOutExcelIn.do'>
            <ul>



                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">导入Excel</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="file" accept="files/*" name="files" id="files"/>
                    </div>
                </li>


                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="buttonClass imginput" value="导入" />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>





<div class="col_main">
    <div class="formHeadDiv">
        <h2><font color="red">代付结果批次号录入</font></h2>
    </div>
    <div class="formContentDiv">
        <form id="newSupplierForm" >
            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>批次号：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="batchNum" name="batchNum"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>订单状态：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select name="checkOrderStatus" id="checkOrderStatus">
                            <option value="">===请选择===</option>
                            <option value="1">初始化</option>
                            <option value="2">超时/失败</option>
                            <option value="4">成功</option>
                        </select>
                    </div>
                </li>



                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">订单号：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="orderNo" name="orderNo" cols="40" rows="5"></textarea>
                        <dd>
                            <font color="red">多个订单号以英文逗号分割；根据订单状态来判断，如这里面有订单号，则相反的修改状态：例如：状态为成功：里面的订单号则会被修改成失败</font>
                        </dd>
                    </div>
                </li>


                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="buttonClass imginput" value="提交" />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>





<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#newSupplierForm").validate({
            rules:{
                batchNum:{
                    required:true,
                    maxlength:50
                },
                checkOrderStatus:{
                    required:true
                }
            },
            messages: {
                batchNum:{
                    required : "批次号不能为空!",
                    maxlength : "批次号最多是50个字符!"
                },
                checkOrderStatus:{
                    required : "请选择订单状态!"
                }
            },

            submitHandler : function() {
                var formData = $("#newSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/orderoutexcel/check.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("审核成功！！！");
                            window.location.href = ctx + "/jsp/manager/orderoutexcel/orderoutexcelIndex.jsp";
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