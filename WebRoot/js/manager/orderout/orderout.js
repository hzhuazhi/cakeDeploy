
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/orderout/list.do',
        dataList_url : ctx + "/orderout/dataList.do",
        add_url : ctx+ "/orderout/add.do",
        update_url : ctx+ "/orderout/update.do",
        queryId_url: ctx+ "/orderout/getId.do",
        delete_url: ctx+ "/orderout/delete.do",
        manyOperation_url: ctx+ "/orderout/updateIsExcel.do",
        exportData_url : ctx +  "/orderout/exportData.do",
        check_url: ctx+ "/orderout/check.do"
    },


    //添加修改验证参数
    validate:{
        submitHandler : function() {
            var id = $("#show input[type='hidden']").val();
            var url = "";
            if(id){
                url = account.url.check_url;
            }

            var formData = $("#newFirstStoreForm").serialize();
            $.ajax({
                url : url,
                type : 'post',
                dataType : 'json',
                data :formData,
                success : function(data) {
                    if(data.success){
                        promptMessage ('保存成功！','success',true);
                        common.goList();
                    }else{
                        promptMessage(data.msg, 'warning', false);
                    }

                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
            return false;
            //阻止表单提交
        }
    },


    //列表显示参数
    list:[
        {"data":"channelName",},
        {"data":"outTradeNo",},
        {"data":"orderNo",},
        // {"data":"supplierTradeNo",},
        // {"data":"companyName",},
        {"data":"inBankName",},
        {"data":"inBankCard",},
        {"data":"inAccountName",},
        {"data":"orderMoney",},
        // {"data":"serviceCharge",},
        {"data":"orderStatus",
                "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                    var html="";
                    if(oData.orderStatus==1){
                        html='<span>初始化</span>';
                    }else if(oData.orderStatus==2){
                        html='<span style="color: #399d19">超时</span>';
                    }else if(oData.orderStatus==3){
                        html='<span style="color: #ffa7d7">质疑</span>';
                    }else if(oData.orderStatus==4){
                        html='<span style="color: #ff301d">成功</span>';
                    }
                    $(nTd).html(html);
                }
        },
        {"data":"orderType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderType==1){
                    html='<span>手动转账</span>';
                }else if(oData.orderType==2){
                    html='<span style="color: #2f9833">API转账</span>';
                }
                $(nTd).html(html);
            }
        },
        // {"data":"outStatus",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.outStatus==1){
        //             html='<span>初始化</span>';
        //         }else if(oData.outStatus==2){
        //             html='<span style="color: #2f9833">出码成功</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"invalidTime",},
        // {"data":"failInfo",},
        {"data":"createTime",},

        {"data":"batchNum",},
        {"data":"isExcel",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isExcel==1){
                    html='<span>未导出</span>';
                }else if(oData.isExcel==2){
                    html='<span><font color="red">已导出</font></span>';
                }
                $(nTd).html(html);
            }
        },



        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.isExcel == 1){
                    html += '<a class = "dataTableBtn dataTableExcelBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);">已导出 </a>';
                }else if(oData.isExcel == 2){
                    html += '<a class = "dataTableBtn dataTableExcelBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);">未导出</a>';
                }
                
                if (oData.orderStatus == 1){
                    html += '<a class = "dataTableBtn dataTableResetBtn" id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 未审核 </a>';
                } else {
                    html += '已审核';
                }
                
                $(nTd).html(html);
            }
        }




        // {"data":"operateStatus",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.operateStatus==1){
        //             html='<span>初始化</span>';
        //         }else if(oData.operateStatus==2){
        //             html='<span style="color: #2f9833">成功</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },

        // {"data":"id",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html = '';
        //
        //         if(oData.orderStatus!=4){
        //             html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="javascript:void(0);" onclick="orderHandle('+oData.id+')" > 处理 </a>'
        //             if(oData.operateStatus==2){
        //                 html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/orderout/jumpUpdate.do?id='+oData.id+'" > 强行处理 </a>'
        //             }
        //             html+=' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
        //         }
        //         $(nTd).html(html);
        //     }
        // }

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/orderout/jumpAdd.do";
        });


        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['supplierTradeNo'] = $("#supplierTradeNo").val();
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['inBankName'] = $("#inBankName").val();
            account.condJsonData['inBankCard'] = $("#inBankCard").val();
            account.condJsonData['inAccountName'] = $("#inAccountName").val();
            account.condJsonData['orderMoney'] = $("#orderMoney").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['orderType'] = $("#orderType").val();
            account.condJsonData['operateStatus'] = $("#operateStatus").val();
            account.condJsonData['isExcel'] = $("#isExcel").val();
            account.condJsonData['batchNum'] = $("#batchNum").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.condJsonData['excelNum'] = $("#excelNum").val();
            account.condJsonData['excelMoney'] = $("#excelMoney").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['channelName'] = "";
            account.condJsonData['orderNo'] = "";
            account.condJsonData['supplierTradeNo'] = "";
            account.condJsonData['outTradeNo'] = "";
            account.condJsonData['inBankName'] = "";
            account.condJsonData['inBankCard'] = "";
            account.condJsonData['inAccountName'] = "";
            account.condJsonData['orderMoney'] = "";
            account.condJsonData['orderStatus'] = "0";
            account.condJsonData['orderType'] = "0";
            account.condJsonData['operateStatus'] = "0";
            account.condJsonData['isExcel'] = "0";
            account.condJsonData['batchNum'] = "";
            account.condJsonData['curdayStart'] = "0";
            account.condJsonData['curdayEnd'] = "0";
            account.condJsonData['excelNum'] = "0";
            account.condJsonData['excelMoney'] = "";

            $("#channelName").val("");
            $("#orderNo").val("");
            $("#supplierTradeNo").val("");
            $("#outTradeNo").val("");
            $("#inBankName").val("");
            $("#inBankCard").val("");
            $("#inAccountName").val("");
            $("#orderMoney").val("");
            $("#orderStatus").val("0");
            $("#orderType").val("0");
            $("#operateStatus").val("0");
            $("#isExcel").val("0");
            $("#batchNum").val("");
            $("#curdayStart").val("0");
            $("#curdayEnd").val("0");
            $("#excelNum").val("0");
            $("#excelMoney").val("");
            common.showDatas(account.condJsonData,account.list);
        });


        // //删除
        // $(".dataTableResetBtn").live("click",function(){
        //     var id = $(this).attr('directkey');
        //     var data = {
        //         id:id,
        //         yn:'1'
        //     }
        //     common.updateStatus(data);
        // });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
        });


        // 数据按照Excel格式导出
        $("#butExcelExport").click(function () {
            common.dataExportExcel($("#condForm"));
        });


        //导出状态修改
        $(".dataTableExcelBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isExcel = $(this).attr('directValue');
            var data = {
                id:id,
                isExcel:isExcel
            }
            common.manyOperationIsExcel(data);
        });



        //审核
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/orderout/getId.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        id = m.id;
                        common.addInit(account.validate);
                        $("#id").val(id);
                        $("#divOutTradeNo").val(m.outTradeNo);
                        $("#divOrderNo").val(m.orderNo);
                        $("#divInBankName").val(m.inBankName);
                        $("#divInBankCard").val(m.inBankCard);
                        $("#divInAccountName").val(m.inAccountName);
                        $("#divOrderMoney").val(m.orderMoney);
                        $("#divInvalidTime").val(m.invalidTime);
                        $("#divCreateTime").val(m.createTime);
                        $("#divBatchNum").val(m.batchNum);

                        openDialog("show","");
                    } else {
                        art.alert(data.msg);
                    }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
        });


    },

}

function  orderHandle(id){
    if(confirm("确定要处理该订单，订单会因为你触发而锁住的！")){
        var data = {
            "id":id
        };
        var url = ctx + "/orderout/chechData.do?id="+id;
        common.ajax(url,data,function(data){
            if(data.type==1){
                window.location.href = ctx +data.rs;
            }else{
                alert(data.rs);
            }
        });
    }
}

function  repeat(id){
    let  data={
        "id":id
    }

    $.ajax({
        url : ctx+ "/orderOut/manyOperation.do",
        type : 'post',
        dataType : 'json',
        data :data,
        success : function(data) {
            if (data.success) {
                alert("修改成功！");
                window.location.href = ctx + "/bankstrategy/list.do";
            } else {
                alert(data.msg);
            }
        },
        error : function(data) {
            alert(data.info);
        }
    });

}



$(function(){
    account.indexInit();
})
