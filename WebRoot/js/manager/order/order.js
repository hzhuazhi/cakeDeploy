
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/order/list.do',
        dataList_url : ctx + "/order/dataList.do",
        add_url : ctx+ "/order/add.do",
        update_url : ctx+ "/order/update.do",
        queryId_url: ctx+ "/order/getId.do",
        delete_url: ctx+ "/order/delete.do",
        manyOperation_url: ctx+ "/order/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"outTradeNo",},
        {"data":"orderNo",},
        {"data":"bankName",},
        {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"orderType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.orderType==2){
                    html+= '<span>支付宝转卡</span>';
                }else if(oData.orderType==3){
                    html+= '<span>卡转卡</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"orderMoney",},
        {"data":"distributionMoney",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.orderStatus==1){
                    html+= '<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html+= '<span>超时/失败</span>';
                }else if(oData.orderStatus==3){
                    html+= '<span>有质疑</span>';
                }else if(oData.orderStatus==4){
                    html+= '<span style="color: #ff3710">成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"invalidTime",},
        {"data":"merchantName",},
        {"data":"replenishType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.replenishType==1){
                    html+= '<span>不是补单</span>';
                }else if(oData.replenishType==2){
                    html+= '<span style="color: #ff3710">补单</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"sendStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.sendStatus==0){
                    html+= '<span>初始化</span>';
                }else if(oData.sendStatus==1){
                    html+= '<span>锁定</span>';
                }else if(oData.sendStatus==2){
                    html+= '<span>失败</span>';
                }else if(oData.sendStatus==3){
                    html+= '<span style="color: #ff3710">成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                let  orderNo =oData.outTradeNo+'';

                if(oData.orderStatus!=4){
                    html = '<a class = "dataTableBtn dataTableDeleteBtn " href = "javascript:void(0);" onclick="repairOrder('+oData.id+',\''+orderNo+'\')"> 确定补单 </a>';
                }
                // html  = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/order/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'

                    // +  '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channel/jumpUpdate.do?op=1&id='+oData.id+'"> 部署 </a>'
                    // +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null,
        beginCurday:0,
        endCurday:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/order/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['orderNo'] = $("#orderNo").val();
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['merchantName'] = $("#merchantName").val();
            account.condJsonData['orderType'] = $("#orderType").val();
            account.condJsonData['replenishType'] = $("#replenishType").val();
            account.condJsonData['workType'] = $("#workType").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['distributionMoney'] = $("#distributionMoney").val();
            if($("#endCurday").val()==undefined||$("#endCurday").val()==""){
                account.condJsonData['endCurday'] =0;
            }else{
                account.condJsonData['endCurday'] = $("#endCurday").val();
            }

            if($("#beginCurday").val()==undefined||$("#beginCurday").val()==""){
                account.condJsonData['beginCurday'] =0;
            }else{
                account.condJsonData['beginCurday'] = $("#beginCurday").val();
            }
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['outTradeNo'] = "";
            account.condJsonData['orderNo'] = "";
            account.condJsonData['bankName'] = "";
            account.condJsonData['bankCard'] = "";
            account.condJsonData['accountName'] = "";
            account.condJsonData['merchantName'] = "";
            account.condJsonData['distributionMoney'] = "";
            account.condJsonData['endCurday'] = "0";
            account.condJsonData['beginCurday'] = "0";
            $("#orderType").val("0");
            $("#replenishType").val("0");
            $("#workType").val("0");
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

    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    queryBankAll:function(){
        var url = basePath + "order/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentId' name='agentId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">==="+dataList[i].agentName+"==="+dataList[i].accountNum+"</option>";
            }
            shtml +="</select>";
            $("#bankDiv").html(shtml);
        });
    }

}

function  repairOrder(id,outTradeNo){
    if(confirm("确定补单吗？")){
        let  data={
            "id":id,
            "outTradeNo":outTradeNo+''
        }
        $.ajax({
            url : ctx+ "/order/addRepairOrder.do",
            type : 'post',
            dataType : 'json',
            data :data,
            success : function(data) {
                if (data.success) {
                    alert("补单成功！");
                    // window.location.href = ctx + "/order/list.do";
                } else {
                    alert(data.msg);
                }
            },
            error : function(data) {
                alert(data.info);
            }
        });


    }
}


$(function(){
    account.indexInit();
})
