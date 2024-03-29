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
                <div class = "condQueryLabelDiv">手机号码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="phoneNum" name ="phoneNum">
                </div>
                <div class = "condQueryLabelDiv">打款人：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="transferUser" name ="transferUser"  >
                </div>
            </div>

            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">日期：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="curday" name ="curday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" >
                </div>
                <div class = "condQueryLabelDiv">小时：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="curhour" name ="curhour">
                </div>
                <div class = "condQueryLabelDiv">分钟：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="curminute" name ="curminute">
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

            <th width="130">订单号</th>
            <th width="130">卡商</th>
            <th width="110">银行名称</th>
            <th width="150">银行卡账号</th>
            <th width="110">开户名</th>
            <th width="110">打款人</th>
            <th width="130">短信来源</th>
            <th width="220">短信内容</th>
            <th width="120">订单金额</th>
            <th width="120">日期</th>
            <th width="70">小时</th>
            <th width="70">分钟</th>
            <th width="120">处理状态</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/supplementsms/supplementsms.js'></script>
</body>
</html>
