<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>充值订单列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl" style="width: 100%;">
                <div class = "condQueryLabelDiv">卡商：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="merchantName" name ="merchantName">
                </div>
                <div class = "condQueryLabelDiv">卡站点：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="cardSiteName" name ="cardSiteName">
                </div>
                <div class = "condQueryLabelDiv">订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="orderNo" name ="orderNo">
                </div>
                <div class = "condQueryLabelDiv">银行：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankName" name ="bankName">
                </div>

                <div class = "condQueryLabelDiv">银行卡号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>



                <%--</br>--%>
            </div>
            <div class = "condQueryCtrl" style="width: 100%;">
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


                <div class = "condQueryLabelDiv">操作状态：</div>
                <div class="formCtrlDiv" >
                    <select id="operateStatus" name="operateStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">初始化</option>
                        <option value="2">系统放弃</option>
                        <option value="3">手动放弃</option>
                        <option value="4">锁定</option>
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
            <th width="100">卡商</th>
            <th width="110">卡站点</th>
            <th width="120">订单号</th>
            <th width="100">订单金额</th>
            <th width="100">订单状态</th>
            <%--<th width="100">银行</th>--%>
            <%--<th width="150">银行卡号</th>--%>
            <th width="100">开户人</th>
            <th width="100">操作状态</th>
            <th width="100">锁定人</th>
            <th width="100">审核状态</th>
            <th width="130">创建时间</th>
            <th width="250">操作</th>

        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>



<div id="show" style="display:none;width:400px;">
    <div class="formHeadDiv">
        <h2>
            <span><font color="red">下发订单分派卡站点</font></span>
        </h2>
    </div>
    <div class="formContentDiv" style="padding-right:0px">
        <form id="newFirstStoreForm">
            <input type="hidden" id="id" name="id" />
            <dl>

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
                        订单金额
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOrderMoney" name="divOrderMoney" disabled="disabled"/>
                    </div>
                </dd>
                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        分派卡站点
                    </div>
                    <div class="formCtrlDiv">
                        <select class="formInput" name="cardSiteId" id="cardSiteId">
                            <option value="0">==请选择==</option>
                        </select>
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
								<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />
								<%--<input type="reset" onClick="javascript :closeDialog('show')" class="formBtn" value=" 返 回 " /> --%>
							</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>


<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/recharge/recharge.js'></script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
