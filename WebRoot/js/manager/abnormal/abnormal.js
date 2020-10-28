var datatable;
function  queryList(){
    var date = new Date();
    // var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year+ month + strDate;

    let  data={
        "curday":currentdate
    }
    $.ajax({
        url: ctx+ '/abnormal/dataList.do',
        type: 'post',
        dataType: 'json',
        contentType:"application/json",
        data:data,
        // 成功执行
        success (data) {
            if(data.phoneNum==0){
                $('#div_phone').attr("class","h_item");
            }else if(data.phoneNum!=0){
                $('#div_phone').attr("class","h_item h_item1");
            }

            if(data.bankNum==0){
                $('#div_bank').attr("class","h_item");
            }else if(data.bankNum!=0){
                $('#div_bank').attr("class","h_item h_item1");
            }

            if(data.smsMessageNum==0){
                $('#div_sms').attr("class","h_item");
            }else if(data.smsMessageNum!=0){
                $('#div_sms').attr("class","h_item h_item1");
            }

            if(data.paymentNum==0){
                $('#div_paymentnum').attr("class","h_item");
            }else if(data.paymentNum!=0){
                $('#div_paymentnum').attr("class","h_item h_item1");
            }

            $("#sphone").text("手机号异常数:"+data.phoneNum);
            $("#sbank").text("失效的银行卡:"+data.bankNum);
            $("#ssms").text("短信未匹配数:"+data.smsMessageNum);
            $("#spaymentnum").text("未处理下发条数:"+data.paymentNum);

        }
    })
}


/***
 * 查询手机号码异常
 */
function  queryPhone(){

    var condJsonData={
        "heartbeatStatus":1
    }
    let  table='';
    $.ajax({
        url: ctx+ '/mobilecard/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            debugger;
            // alert(data.rows.length);

            table+='<table class="datatable tables">';
            table+='<thead>';
                table+='<tr>';
                    table+='<td>手机卡名称</td>';
                    table+='<td>归属人的名称</td>';
                    table+='<td>手机号码</td>';
                    table+='<td>是否欠费</td>';
                    table+='<td>心跳状态</td>';
                table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                    table+='<td>'+data.rows[i].cardName+'</td>';
                    table+='<td>'+data.rows[i].useName+'</td>';
                    table+='<td>'+data.rows[i].phoneNum+'</td>';
                    if(data.rows[i].isArrears==1){
                        table+='<td>未欠费</td>';
                    }else{
                        table+='<td>欠费</td>';
                    }
                    // table+='<td>'+data.rows[i].isArrears+'</td>';

                    if(data.rows[i].heartbeatStatus==1){
                        table+='<td style="color: #ff301d">异常</td>';
                    }else{
                        table+='<td>正常</td>';
                    }
                    // table+='<td>'+data.rows[i].heartbeatStatus+'</td>';
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}


/***
 * 查询未打款的
 */
function  queryPaymentnum(){
    var condJsonData={
        "orderStatus":3
    }
    let  table='';
    $.ajax({
        url: ctx+ '/merchantrecharge/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            debugger;
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td>下发表的订单号</td>';
            table+='<td>银行名称</td>';
            table+='<td>银行卡账号</td>';
            table+='<td>开户名</td>';
            table+='<td>订单金额</td>';
            table+='<td>订单状态</td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].issueOrderNo+'</td>';
                table+='<td>'+data.rows[i].bankName+'</td>';
                table+='<td>'+data.rows[i].bankCard+'</td>';
                table+='<td>'+data.rows[i].accountName+'</td>';
                table+='<td>'+data.rows[i].orderMoney+'</td>';
                if(data.rows[i].order_status==1){
                    table+='<td>初始化</td>';
                }else{
                    table+='<td>超时/失败/审核驳回</td>';
                }
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}

/***
 * 查询银行卡异常
 */
function  queryBank(){
     var condJsonData={
        "checkStatus":2
    }
    let  table='';
    $.ajax({
        url: ctx+ '/bank/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td>手机卡别名</td>';
            table+='<td>手机号</td>';
            table+='<td>银行卡账号</td>';
            table+='<td>开户名</td>';
            table+='<td>检测状态</td>';
            table+='<td>检测内容</td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].alias+'</td>';
                table+='<td>'+data.rows[i].phoneNum+'</td>';
                table+='<td>'+data.rows[i].bankCard+'</td>';
                table+='<td>'+data.rows[i].accountName+'</td>';
                table+='<td style="color: #ff301d">不正常</td>';
                table+='<td>'+data.rows[i].dataExplain+'</td>';
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}


/***
 * 查询短信解析异常
 */
function  querySmsMessageNum(){
    var date = new Date();
    // var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year+""+ month+"" + strDate;

    var condJsonData={
        "workType":2,
        "handleType":1,
        "curday":currentdate
    }
    let  table='';
    $.ajax({
        url: ctx+ '/bankshortmsg/dataList.do',
        type: 'post',
        data:condJsonData,
        // 成功执行
        success (data) {
            // alert(data.rows.length);
            table+='<table class="datatable tables">';
            table+='<thead>';
            table+='<tr>';
            table+='<td>id</td>';
            table+='<td>手机号</td>';
            table+='<td>银行卡类型</td>';
            table+='<td>银行卡号</td>';
            table+='<td>端口号</td>';
            table+='<td>短信内容</td>';
            table+='<td>状态</td>';
            table+='</tr>';
            table+='</thead>';
            for (var i=0;i<data.rows.length;i++){
                table+='<tr>';
                table+='<td>'+data.rows[i].id+'</td>';
                table+='<td>'+data.rows[i].phoneNum+'</td>';
                table+='<td>'+data.rows[i].bankTypeId+'</td>';
                table+='<td>'+data.rows[i].bankId+'</td>';
                table+='<td>'+data.rows[i].smsNum+'</td>';
                table+='<td>'+data.rows[i].smsContent+'</td>';
                table+='<td><a class = "dataTableBtn dataTableDeleteBtn " href="#" onclick="updatahandleType('+data.rows[i].id+')"> 已处理 </a></td>';
                table+='</tr>';
            }

            table+='</table>';
            $("#tables").html(table);

        }
    })
}

function  updatahandleType(id){
    if(confirm("确定修改状态吗？")){
        var condJsonData={
            "id":id,
            "handleType":2
        }
        let  table='';
        $.ajax({
            url: ctx+ '/bankshortmsg/updateHandleType.do',
            type: 'post',
            data:condJsonData,
            // 成功执行
            success (data) {
                alert(data.msg);
                queryList();
                querySmsMessageNum();
            }
        })
    }
}

queryList();
$(function(){
    self.setInterval("queryList()", 1000*10);
})
