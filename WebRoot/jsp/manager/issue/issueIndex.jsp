<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>订单列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl" style="width: 100%;">
                <%--<div class = "condQueryLabelDiv">平台订单：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                <%--<input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">--%>
                <%--</div>--%>
                <div class = "condQueryLabelDiv">订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderNo" name ="orderNo">
                </div>

                <div class = "condQueryLabelDiv">商家订单：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>

                <div class = "condQueryLabelDiv">银行：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankName" name ="bankName">
                </div>

                <div class = "condQueryLabelDiv">银行卡号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>

                <div class = "condQueryLabelDiv">开户人：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
                <div class = "condQueryLabelDiv">订单状态：</div>
                <div class="formCtrlDiv">
                    <select id="orderStatus" name="orderStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">初始化</option>
                        <option value="2">驳回</option>
                        <option value="3">成功</option>
                    </select>
                </div>
                <%--</br>--%>
            </div>
            <div class = "condQueryCtrl" style="width: 100%;">
                <div class = "condQueryLabelDiv">归属类型：</div>
                <div class="formCtrlDiv" >
                    <select id="ascriptionType" name="ascriptionType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">卡商</option>
                        <option value="2">平台</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">是否分配：</div>
                <div class="formCtrlDiv" >
                    <select id="isDistribution" name="isDistribution" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">未分配</option>
                        <option value="2">已分配</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">是否归集完毕：</div>
                <div class="formCtrlDiv">
                    <select id="isComplete" name="isComplete" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">未归集完毕</option>
                        <option value="2">已归集完毕</option>
                    </select>
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

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>

                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}" />
                </div>
            </div>
            <div class = "condQueryCtrl" style="width: 100%;" align="right" >
                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
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
            <th width="150">商家订单</th>
            <th width="100">订单金额</th>
            <%--<th width="100">银行</th>--%>
            <%--<th width="150">银行卡号</th>--%>
            <th width="100">开户人</th>
            <th width="100">订单状态</th>
            <th width="100">归属类型</th>
            <th width="100">是否分配</th>
            <th width="130">是否归集完毕</th>
            <th width="100">审核状态</th>
            <th width="150">创建时间</th>
            <th width="200">操作</th>

        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/issue/issue.js'></script>
</body>
</html>
