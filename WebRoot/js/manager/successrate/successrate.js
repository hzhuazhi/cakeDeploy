
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/order/dataListBankCard.do',
        dataList_url : ctx + '/order/dataListBankCard.do'
    },
    //列表显示参数
    list:[
        {"data":"bankId",},
        {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"bankName",},
        {"data":"acName",},
        {"data":"curday",},
        {"data":"taskNum",},
        {"data":"successNum",},
        {"data":"successRateTask",},
        {"data":"taskMoney",},
        {"data":"numMoney", },
        {"data":"successRateMoney", }
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
            window.location.href = ctx + "/supplementsms/jumpAdd.do";
        });

        // 初始化列表数据
        // common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['accountId'] = $("#accountId").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['curday'] = $("#curday").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['accountId'] = "";
            account.condJsonData['bankCard'] = "";
            account.condJsonData['accountName'] = "";
            account.condJsonData['curday'] = "";
            account.condJsonData['curhour'] = "";
            account.condJsonData['curminute'] = "";
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


//下拉框数据填充
//查询所有代理-无分页-下拉框选项:
//卡商-无分页-下拉框选项:
function queryAccountAll(){
    var url = ctx + "/bankcollection/queryAccount.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        var shtml="";
        shtml += "<select id='accountId' name='accountId'  class='text-input medium-input'>";
        shtml +="<option value=''>===请选择===</option>";
        for (var i=0;i<dataList.length>0;i++) {
            shtml +="<option value="+dataList[i].id+">"+dataList[i].acName+"</option>";
        }
        shtml +="</select>";
        $("#divAccount").html(shtml);
    });
}
queryAccountAll();
$(function(){
    account.indexInit();
})
