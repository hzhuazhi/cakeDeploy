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
                <div class = "condQueryLabelDiv">银行名称	：</div>
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

                <%--<div class = "condQueryLabelDiv">大于金额：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                    <%--<input type ="text" class ="inputCommonSty" id="money" name ="money">--%>
                <%--</div>--%>

                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <div class="formCtrlDiv">
                        <input type="text" class ="inputCommonSty" name="beginTime" id="beginTime" size="17" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  />
                    </div>
                    <%--<input type ="text" class ="inputCommonSty" id="createTime" name ="createTime">--%>
                </div>
                <div class = "condQueryLabelDiv">结束时间：</div>
                <div class="formCtrlDiv">
                    <div class="formCtrlDiv">
                        <input type="text" class ="inputCommonSty" name="endTime" id="endTime" size="17" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"  />
                    </div>
                    <%--<input type ="text" class ="inputCommonSty" id="createTime" name ="createTime">--%>
                </div>



                <%--<c:if test="${ACCOUNT.roleId==1}">--%>
                    <%--<div class = "searchdiv">--%>
                        <%--<input type="button" class = "buttonClass imginput addbtn" value="新增账号" style="margin-left: 30px;" >--%>
                    <%--</div>--%>
                <%--</c:if>--%>
            </div>

            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">卡商：</div>
                <div class="formCtrlDiv">
                    <div id="divAccount">

                    </div>
                    <%--<input type ="text" class ="inputCommonSty" id="accountIdName" name ="accountIdName">--%>
                </div>

                <div class = "condQueryLabelDiv">卡站点：</div>
                <div class="formCtrlDiv">
                    <div id="divCardSite">

                    </div>
                    <%--<input type ="text" class ="inputCommonSty" id="cardSiteIdName" name ="cardSiteIdName">--%>
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

    <div style="width: 100%">
        <strong style="font-size: 20px">汇总金额:</strong>  <strong style="color: #bb0000;font-size: 20px" id="countMoney">0</strong>
    </div>
    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">银行ID</th>
            <th width="150">银行名称</th>
            <th width="150">银行卡号</th>
            <th width="150">开户人</th>
            <th width="150">成功金额</th>
            <th width="150">卡商</th>
            <th width="150">卡站点</th>
            <th width="150">时间</th>
            <%--<th width="380">操作</th>--%>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/bankcollectioncount/bankcollectioncount.js'>
</script>
</body>
</html>
