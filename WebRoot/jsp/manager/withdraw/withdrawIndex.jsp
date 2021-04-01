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
            <div class = "condQueryCtrl" style="width: 100%">

                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>

                <div class = "condQueryLabelDiv">订单号：</div>
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
            <div class = "condQueryCtrl" style="width: 100%">
                <div class = "condQueryLabelDiv">卡商名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="acName" name ="acName">
                </div>

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="beginCurday" id="beginCurday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" />
                </div>

                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="endCurday" id="endCurday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"  />
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
            <th width="110">渠道</th>
            <th width="150">订单号</th>
            <th width="130">订单金额</th>
            <th width="150">中转站订单号</th>

            <th width="120">提现类型</th>
            <th width="150">提现归属人账户ID</th>
            <th width="150">指派类型</th>
            <th width="150">指派卡商</th>
            <th width="150">付款银行名称</th>
            <th width="150">付款银行卡账号</th>
            <th width="150">付款开户名</th>
            <th width="120">订单状态</th>
            <%--<th width="150">审核状态</th>--%>
            <th width="120">数据说明</th>
            <th width="200">转账凭证</th>
            <th width="150">创建日期</th>
            <th width="300">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/withdraw/withdraw.js'></script>
</body>
</html>
