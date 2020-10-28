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
                <div class = "condQueryLabelDiv">商家订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>
                <div class = "condQueryLabelDiv">订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderNo" name ="orderNo">
                </div>
                <div class = "condQueryLabelDiv">银行名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankName" name ="bankName">
                </div>
                <div class = "condQueryLabelDiv">银行卡账号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>
                <div class = "condQueryLabelDiv">开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
            </div>

            <div class = "condQueryCtrl" style="width: 100%">
                <div class = "condQueryLabelDiv">派发金额：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="distributionMoney" name ="distributionMoney">
                </div>

                <div class = "condQueryLabelDiv">卡商名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="merchantName" name ="merchantName">
                </div>

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="beginCurday" id="beginCurday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" />
                </div>

                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="endCurday" id="endCurday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"  />
                </div>

                <div class = "condQueryLabelDiv">订单类型：</div>
                <div class="formCtrlDiv">
                    <select id="orderType" name="orderType">
                        <option value="0">===请选择===</option>
                        <option value="1">支付宝转卡</option>
                        <option value="2">卡转卡</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">订单状态：</div>
                <div class="formCtrlDiv">
                    <select id="orderStatus" name="orderStatus">
                        <option value="0">===请选择===</option>
                        <option value="1">初始化</option>
                        <option value="2">超时/失败</option>
                        <option value="3">有质疑</option>
                        <option value="4">成功</option>
                    </select>
                </div>

            </div>
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">补单状态：</div>
                <div class="formCtrlDiv">
                    <select id="replenishType" name="replenishType">
                        <option value="0">===请选择===</option>
                        <option value="1">不是补单</option>
                        <option value="2">补单</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">补单类型：</div>
                <div class="formCtrlDiv">
                    <select id="workType" name="workType">
                        <option value="0">===请选择===</option>
                        <option value="1">初始化</option>
                        <option value="2">补充失败</option>
                        <option value="3">补充成功</option>
                    </select>
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
            <th width="150">商家订单号</th>
            <th width="150">订单号</th>
            <th width="150">银行名称</th>
            <th width="150">银行卡账号</th>
            <th width="150">开户名</th>
            <th width="150">订单类型</th>
            <th width="150">订单金额</th>
            <th width="150">派发金额</th>
            <th width="150">订单状态</th>
            <th width="150">失效时间</th>
            <th width="150">卡商名称</th>
            <th width="150">补单状态</th>
            <th width="150">发送状态</th>
            <th width="150">创建时间</th>
            <th width="380">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/order/order.js'></script>
</body>
</html>
