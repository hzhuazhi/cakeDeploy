
var datatable;
var cardSiteId = 0;
var account = {
    //地址
    url:{
        list_url : ctx + '/recharge/list.do',
        dataList_url : ctx + "/recharge/dataList.do",
        add_url : ctx+ "/recharge/add.do",
        update_url : ctx+ "/recharge/update.do",
        queryId_url: ctx+ "/recharge/getId.do",
        delete_url: ctx+ "/recharge/delete.do",
        manyOperation_url: ctx+ "/recharge/manyOperation.do",
        distribution_url: ctx+ "/recharge/distribution.do",
    },
    //添加修改验证参数
    validate:{
        submitHandler : function() {
            var id = $("#show input[type='hidden']").val();
            var url = "";
            if(id){
                url = account.url.distribution_url;
            }else{
                url = account.url.add_url;
            }

            var formData = $("#newFirstStoreForm").serialize();
            $.ajax({
                url : url,
                type : 'post',
                dataType : 'json',
                data :formData,
                success : function(data) {
                    if(data.success){
                        cardSiteId = 0;
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
        {"data":"merchantName",},
        {"data":"cardSiteName",},
        {"data":"orderNo",},
        // {"data":"orderType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.orderType==1){
        //             html='<span>预付款</span>';
        //         }else if(oData.orderType==2){
        //             html='<span>平台</span>';
        //         }else if(oData.orderType==3){
        //             html='<span>下发</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"issueOrderNo",},
        {"data":"orderMoney",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>驳回</span>';
                }else if(oData.orderStatus==3){
                    // html='<span>成功</span>';
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },
        // {"data":"bankName",},
        // {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"operateStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.operateStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.operateStatus==2){
                    html='<span>系统放弃</span>';
                }else if(oData.operateStatus==3){
                    html='<span>手动放弃</span>';
                }else if(oData.operateStatus==4){
                    html='<span>锁定</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"lockAccountName",},
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.checkStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.checkStatus==2){
                    html='<span>失败</span>';
                }else if(oData.checkStatus==3){
                    // html='<span>成功</span>';
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.orderStatus != 3){
                    if (oData.checkStatus != 3){
                        if (oData.operateStatus == 1){
                            html += '<a class = "dataTableBtn dataTableDeleteBtn" id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 分配 </a>'
                        }
                    }
                } 
                if (oData.operateStatus == 1){
                    html += '<a class = "dataTableBtn dataTableOperateBtn" directkey="' + oData.id + '" directkey1="4" href = "javascript:void(0);"> 锁定 </a>'
                } else if(oData.operateStatus == 4){
                    if (oData.orderType == 3){
                        html += '<a class = "dataTableBtn dataTableOperateBtn" directkey="' + oData.id + '" directkey1="3" href = "javascript:void(0);"> 放弃 </a>'
                    }
                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/recharge/jumpInfo.do?id='+oData.id+'"> 详情 </a>';
                if (oData.operateStatus == 4){
                    if (oData.orderStatus != 3){
                        html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/recharge/jumpUpdate.do?id='+oData.id+'"> 下发 </a>';
                    }
                }
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        merchantName:null,
        cardSiteName:null,
        orderNo:null,
        orderStatus:0,
        bankName:null,
        bankCard:null,
        accountName:null,
        operateStatus:0,
        checkStatus:0,
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);


        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            // account.condJsonData['merchantName'] = $("#merchantName").val();
            account.condJsonData['cardSiteName'] = $("#cardSiteName").val();
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['operateStatus'] = $("#operateStatus").val();
            account.condJsonData['checkStatus'] = $("#checkStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();



            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){

            account.condJsonData['merchantName'] = "";
            $("#merchantName").val("");
            account.condJsonData['cardSiteName'] = "";
            $("#cardSiteName").val("");
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
            account.condJsonData['orderStatus'] = "0";
            $("#orderStatus").val("0");
            account.condJsonData['bankName'] = "";
            $("#bankName").val("");
            account.condJsonData['bankCard'] = "";
            $("#bankCard").val("");
            account.condJsonData['accountName'] = "";
            $("#accountName").val("");
            account.condJsonData['operateStatus'] = "0";
            $("#operateStatus").val("0");
            account.condJsonData['checkStatus'] = "0";
            $("#checkStatus").val("0");
            account.condJsonData['curdayStart'] = "0";
            $("#curdayStart").val("0");
            account.condJsonData['curdayEnd'] = "0";
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
        });

        //分配
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/recharge/getId.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        id = m.id;
                        cardSiteId = m.cardSiteId;
                        common.addInit(account.validate);
                        $("#divOrderNo").val(m.orderNo);
                        $("#id").val(id);
                        $("#divOrderMoney").val(m.orderMoney);
                        selectCardSite();
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


        //删除
        $(".dataTableResetBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        //启用/禁用
        $(".dataTableOperateBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var operateStatus = $(this).attr('directkey1');
            var data = {
                id:id,
                operateStatus:operateStatus
            }
            // common.manyOperation(data);
            //更多操作：提示语
            var showMsg = '';
            if (data.operateStatus == 4){
                if(!confirm("确认要锁定吗？")){
                    return;
                }
                showMsg = '锁定成功!';
            }else if(data.operateStatus == 3){
                if(!confirm("确认要放弃吗？")){
                    return;
                }
                showMsg = '放弃成功!';
            }
            // this.ajax(account.url.manyOperation_url,data,function(data){
            //     if (data.success) {
            //         promptMessage (showMsg,'success',false);
            //         common.goList();
            //     } else {
            //         art.alert(data.msg);
            //     }
            // });

            $.ajax({url : account.url.manyOperation_url,
                type : 'post',
                dataType : 'json',
                data :{
                    id:id,
                    operateStatus:operateStatus
                },
                success : function(data) {
                    if (data.success) {
                        promptMessage (showMsg,'success',false);
                        common.goList();
                    } else {
                        art.alert(data.msg);
                    }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });



        });
    }

}

$(function(){
    account.indexInit();
})


function selectCardSite(){

    $.ajax({url : ctx+ "/system/account/dataAllList.do",
        type : 'post',
        dataType : 'json',
        data :{
        },
        success : function(data) {
            if (data.success) {
                appendOption(data.data,"#cardSiteId");
                // appendOption(data,"#cardSiteId");
                $("#cardSiteId").val(cardSiteId);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}

function appendOption(list,selectId){
    var html='<option value="0">==请选择==</option>';
    for(var i=0;i<list.length;i++){
        html+='<option value="'+list[i].id+'">'+list[i].acName+'</option>';
    }
    $(selectId).html(html);


}
