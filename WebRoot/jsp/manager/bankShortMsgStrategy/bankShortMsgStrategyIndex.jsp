<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>银行短信解析列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">银行名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankName" name ="bankName">
                </div>

                <div class = "condQueryLabelDiv">解析别名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>

                <div class = "condQueryLabelDiv">短信端口号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="smsNum" name ="smsNum">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增银行短信解析" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">银行名称</th>
            <th width="150">解析别名</th>
            <th width="120">短信端口号</th>
            <th width="200">金额截取开始</th>
            <th width="200">金额截取结束</th>
            <th width="180">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/bankShortMsgStrategy/bankShortMsgStrategy.js'></script>
</body>
</html>
