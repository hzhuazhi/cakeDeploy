
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
        manyOperation_url: ctx+ "/orderout/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"orderNo",},
        {"data":"inBankName",},
        {"data":"inBankCard",},
        {"data":"inAccountName",},
        {"data":"merchantName",},
        {"data":"orderMoney",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>失败</span>';
                }else if(oData.orderStatus==3){
                    html='<span>失败</span>';
                }else if(oData.orderStatus==4){
                    html='<span style="color: #bb0000">成功</span>';
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
                    html='<span>API转账</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"invalidTime",},
        {"data":"operateStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.operateStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.operateStatus==2){
                    html='<span style="color: #2f9833">成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"failInfo",},
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';

                if(oData.orderStatus!=4){
                    html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="javascript:void(0);" onclick="orderHandle('+oData.id+')" > 处理 </a>'
                    if(oData.operateStatus==2){
                        html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/orderout/jumpUpdate.do?id='+oData.id+'" > 强行处理 </a>'
                    }
                    html+=' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                }
                $(nTd).html(html);
            }
        }

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
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['outBankName'] = $("#outBankName").val();
            account.condJsonData['outBankCard'] = $("#outBankCard").val();
            account.condJsonData['outAccountName'] = $("#outAccountName").val();
            account.condJsonData['orderMoney'] = $("#orderMoney").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['orderType'] = $("#orderType").val();
            account.condJsonData['operateStatus'] = $("#operateStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['orderNo'] = "";
            account.condJsonData['outBankName'] = "";
            account.condJsonData['outBankCard'] = "";
            account.condJsonData['outAccountName'] = "";
            account.condJsonData['orderMoney'] = "";
            account.condJsonData['orderStatus'] = "0";
            account.condJsonData['orderType'] = "0";
            account.condJsonData['operateStatus'] = "0";
            account.condJsonData['curdayStart'] = "0";
            account.condJsonData['curdayEnd'] = "0";
            $("#orderNo").val("");
            $("#outBankName").val("");
            $("#outBankCard").val("");
            $("#outAccountName").val("");
            $("#orderMoney").val("");
            $("#orderStatus").val("0");
            $("#orderType").val("0");
            $("#operateStatus").val("0");
            $("#curdayStart").val("0");
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
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
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
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
