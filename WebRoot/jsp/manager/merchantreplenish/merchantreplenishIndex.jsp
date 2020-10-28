<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>商户补单申请列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl" style="width: 100%;">
                <div class = "condQueryLabelDiv">订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderNo" name ="orderNo">
                </div>

                <div class = "condQueryLabelDiv">商家订单：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>

                <div class = "condQueryLabelDiv">卡商：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="merchantName" name ="merchantName">
                </div>

                <div class = "condQueryLabelDiv">卡站点：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="cardSiteName" name ="cardSiteName">
                </div>

                <div class = "condQueryLabelDiv">商户：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>
                <%--</br>--%>
            </div>

            <div class = "condQueryCtrl" style="width: 100%;">

                <div class = "condQueryLabelDiv">处理人：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="handlePeopleName" name ="handlePeopleName">
                </div>

                <div class = "condQueryLabelDiv">审核状态：</div>
                <div class="formCtrlDiv">
                    <select id="checkStatus" name="checkStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">初始化</option>
                        <option value="2">失败</option>
                        <option value="3">成功</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">处理状态：</div>
                <div class="formCtrlDiv" >
                    <select id="handleType" name="handleType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">未处理</option>
                        <option value="2">已处理</option>
                    </select>
                </div>


                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>
        </form>
    </div>
</div>

<table class="datatable tables">

    <thead>
    <tr>
        <th width="140">订单号</th>
        <th width="140">商家订单</th>
        <th width="100">订单金额</th>
        <th width="100">派单金额</th>
        <th width="110">卡商</th>
        <th width="120">卡站点</th>
        <th width="110">商户</th>
        <th width="100">审核状态</th>
        <th width="100">处理状态</th>
        <th width="100">处理人</th>
        <th width="160">创建时间</th>
        <th width="220">操作</th>

    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/merchantreplenish/merchantreplenish.js'></script>
</body>
</html>
