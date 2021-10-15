<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>账号列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">

                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>

                <div class = "condQueryLabelDiv">渠道订单号	：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>
                <div class = "condQueryLabelDiv">订单号	：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderNo" name ="orderNo">
                </div>
                <div class = "condQueryLabelDiv">三方订单号	：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="supplierTradeNo" name ="supplierTradeNo">
                </div>
                <div class = "condQueryLabelDiv">付款银行名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="inBankName" name ="inBankName">
                </div>

                <div class = "condQueryLabelDiv">付款银行卡账号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="intBankCard" name ="inBankCard">
                </div>
                <div class = "condQueryLabelDiv">付款开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="inAccountName" name ="inAccountName">
                </div>


            </div>


            <div class = "condQueryCtrl">


                <div class = "condQueryLabelDiv">订单金额：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderMoney" name ="orderMoney">
                </div>
                <div class = "condQueryLabelDiv">订单状态：</div>
                <div class="formCtrlDiv">
                    <select name="orderStatus" id="orderStatus">
                        <option value="0">===请选择===</option>
                        <option value="1">初始化</option>
                        <option value="2">超时</option>
                        <option value="3">质疑</option>
                        <option value="4">成功</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">订单类型：</div>
                <div class="formCtrlDiv">
                    <select name="orderType" id="orderType">
                        <option value="0">===请选择===</option>
                        <option value="1">手动转账</option>
                        <option value="2">API转账</option>
                    </select>
                </div>

                <%--<div class = "condQueryLabelDiv">操作状态：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                    <%--<select name="operateStatus" id="operateStatus">--%>
                        <%--<option value="0">===请选择===</option>--%>
                        <%--<option value="1">初始化</option>--%>
                        <%--<option value="2">锁定</option>--%>
                    <%--</select>--%>
                <%--</div>--%>

                <div class = "condQueryLabelDiv">导出状态：</div>
                <div class="formCtrlDiv">
                    <select name="isExcel" id="isExcel">
                        <option value="0">===请选择===</option>
                        <option value="1">未导出</option>
                        <option value="2">已导出</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">批次号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="batchNum" name ="batchNum">
                </div>

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>
                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}" />
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>


            <div class = "condQueryCtrl">

                <div class = "condQueryLabelDiv">导出订单条数：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="excelNum" name ="excelNum">
                </div>

                <div class = "condQueryLabelDiv">导出订单总金额：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="excelMoney" name ="excelMoney">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "butExcelExport" class = "buttonClass imginput" value = "Excel导出" />
                </div>
            </div>


        </form>


    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="130">渠道</th>
            <th width="130">渠道订单号</th>
            <th width="150">订单号</th>
            <%--<th width="120">三方订单号</th>--%>
            <%--<th width="150">公司名字</th>--%>
            <th width="150">付款银行名称</th>
            <th width="150">付款银行卡账号</th>
            <th width="150">付款开户名</th>
            <th width="150">订单金额</th>
            <%--<th width="150">手续费</th>--%>
            <th width="150">订单状态</th>
            <th width="150">订单类型</th>
            <%--<th width="150">代付订单出码状态</th>--%>
            <%--<th width="150">失效时间</th>--%>
            <%--<th width="150">失败缘由</th>--%>
            <th width="150">创建日期</th>
            <th width="150">批次号</th>
            <th width="150">导出状态</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>




<div id="show" style="display:none;width:500px;">
    <div class="formHeadDiv">
        <h2>
            <span><font color="red">代付审核</font></span>
        </h2>
    </div>
    <div class="formContentDiv" style="padding-right:0px">
        <form id="newFirstStoreForm">
            <input type="hidden" id="id" name="id" />
            <dl>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        渠道订单号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOutTradeNo" name="divOutTradeNo" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOrderNo" name="divOrderNo" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        付款银行名称
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divInBankName" name="divInBankName" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        付款银行卡账号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divInBankCard" name="divInBankCard" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        付款开户名
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divInAccountName" name="divInAccountName" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单金额
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOrderMoney" name="divOrderMoney" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        失效时间
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divInvalidTime" name="divInvalidTime" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        创建时间
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divCreateTime" name="divCreateTime" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        批次号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divBatchNum" name="divBatchNum" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        <font color="red">*</font>订单状态
                    </div>
                    <div class="formCtrlDiv">
                        <select class="formInput" name="checkOrderStatus" id="checkOrderStatus">
                            <option value="0">==请选择==</option>
                            <option value="1">初始化</option>
                            <option value="2">超时/失败</option>
                            <option value="4">成功</option>
                        </select>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        备注
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <%--<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"--%>
                        <%--id="remark" name="remark"/>--%>
                        <textarea id="remark" name="remark" cols="40" rows="5"></textarea>
                    </div>
                </dd>



                <dd style="border-top: none;">
                    <div class="formTextDiv"></div>
                    <%--<div class="formCtrlDiv">
                        -------------------------------------------------------------------------------
                    </div>--%>
                </dd>
                <dd style=" height: 60px; line-height: 58px;">
                    <div class="formCtrlDiv">
							<span style="margin-left: 100px;">
								<input type="submit" style="background-color: #767DC3" class="formBtn" value="保　存" />
								<%--<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />--%>
								<input type="reset" onClick="javascript :closeDialog('show')" style="background-color: #767DC3" class="formBtn" value=" 返 回 " />
							</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>









<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/orderout/orderout.js'>
</script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
