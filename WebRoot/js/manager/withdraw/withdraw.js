
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/withdraw/list.do',
        dataList_url : ctx + "/withdraw/dataList.do",
        add_url : ctx+ "/withdraw/add.do",
        update_url : ctx+ "/withdraw/update.do",
        queryId_url: ctx+ "/withdraw/getId.do",
        delete_url: ctx+ "/withdraw/delete.do",
        manyOperation_url: ctx+ "/withdraw/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"orderNo",},
        {"data":"orderMoney",},
        {"data":"outTradeNo",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>超时</span>';
                }else if(oData.orderStatus==3){
                    html='<span>质疑</span>';
                }else if(oData.orderStatus==4){
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"withdrawType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.withdrawType==1){
                    html='<span>归属渠道</span>';
                }else if(oData.withdrawType==2){
                    html='<span>归属子渠道</span>';
                }else if(oData.withdrawType==3){
                    html='<span>归属支付通道</span>';
                }else if(oData.withdrawType==4){
                    html='<span>归属卡商</span>';
                }else if(oData.withdrawType==5){
                    html='<span>归属利益人</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"ascriptionId",},
        {"data":"outType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.withdrawType==1){
                    html='<span>卡商</span>';
                }else if(oData.withdrawType==2){
                    html='<span>平台</span>';
                }else if(oData.withdrawType==3){
                    html='<span>中转站</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"merchantId",
            // "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
            //     var html="";
            //     if(oData.checkStatus==1){
            //         html='<span>初始化</span>';
            //     }else if(oData.checkStatus==2){
            //         // html='<span>失败</span>';
            //         html='<span><font color="red">失败</font></span>';
            //     }else if(oData.checkStatus==3){
            //         html='<span>成功</span>';
            //     }
            //     $(nTd).html(html);
            // }
        },

        {"data":"outBankName",},
        {"data":"outBankCard",},
        {"data":"outAccountName",},
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.checkStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.checkStatus==2){
                    html='<span>审核收款失败</span>';
                }else if(oData.checkStatus==3){
                    html='<span>审核收款成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"checkInfo",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdraw/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                if (oData.handleType == 1){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdraw/jumpUpdateCheck.do?id='+oData.id+'"> 审核 </a>';
                }else if(oData.handleType == 2){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/withdraw/jumpInfo.do?id='+oData.id+'"> 详情 </a>';
                }
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/withdraw/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['orderNo'] = $("#orderNo").val();

            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['orderNo'] = "";
            $("#orderNo").val("");
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
    }

}

$(function(){
    account.indexInit();
})
