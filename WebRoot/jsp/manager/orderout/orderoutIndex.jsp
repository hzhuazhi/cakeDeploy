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
                <div class = "condQueryLabelDiv">订单号	：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderNo" name ="orderNo">
                </div>
                <div class = "condQueryLabelDiv">付款银行名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outBankName" name ="outBankName">
                </div>

                <div class = "condQueryLabelDiv">付款银行卡账号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outBankCard" name ="outBankCard">
                </div>

                <div class = "condQueryLabelDiv">付款开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outAccountName" name ="outAccountName">
                </div>

                <div class = "condQueryLabelDiv">订单金额：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderMoney" name ="orderMoney">
                </div>

            </div>

            <div class = "condQueryCtrl">
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

                <div class = "condQueryLabelDiv">操作状态：</div>
                <div class="formCtrlDiv">
                    <select name="operateStatus" id="operateStatus">
                        <option value="0">===请选择===</option>
                        <option value="1">初始化</option>
                        <option value="2">锁定</option>
                    </select>
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
        </form>


    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">订单号</th>
            <th width="150">付款银行名称</th>
            <th width="150">付款银行卡账号</th>
            <th width="150">付款开户名</th>
            <th width="150">订单金额</th>
            <th width="150">订单状态</th>
            <th width="150">订单类型</th>
            <th width="150">失效时间</th>
            <th width="150">操作状态</th>
            <th width="150">数据说明</th>
            <th width="150">创建日期</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/orderout/orderout.js'>
</script>
</body>
</html>
