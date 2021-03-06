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
                <div class = "condQueryLabelDiv">卡商：</div>
                <div class="formCtrlDiv">
                    <div id="divAccount">
                    </div>
                </div>
                <div class = "condQueryLabelDiv">商户：</div>
                <div class="formCtrlDiv">
                    <div id="divChannel">
                    </div>
                </div>
                <div class = "condQueryLabelDiv">银行卡号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>
                <div class = "condQueryLabelDiv">收款人：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
                <div class = "condQueryLabelDiv">日期：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="curday" name ="curday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" >
                </div>

                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="startTime" name ="startTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
                </div>

                <div class = "condQueryLabelDiv">截止时间：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="endTime" name ="endTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>

            <%--<div class = "condQueryCtrl">--%>
                <%--<div class="searchdiv">--%>
                    <%--<input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />--%>
                <%--</div>--%>
                <%--<div class="searchdiv">--%>
                    <%--<input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />--%>
                <%--</div>--%>
            <%--</div>--%>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="120">银行id</th>
            <th width="180">银行卡号</th>
            <th width="120">收款人</th>
            <th width="130">银卡类型</th>
            <th width="130">卡商</th>
            <%--<th width="150">时间</th>--%>
            <th width="150">任务数</th>
            <th width="150">成功数</th>
            <th width="150">任务成功率</th>
            <th width="150">任务金额</th>
            <th width="150">成功金额</th>
            <th width="150">金额成功率</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/successrate/successrate.js'></script>
</body>
</html>
