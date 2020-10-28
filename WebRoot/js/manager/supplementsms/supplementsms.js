
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/bankshortmsg/dataListMsg.do',
        dataList_url : ctx + '/bankshortmsg/dataListMsg.do'
    },
    //列表显示参数
    list:[
        {"data":"orderNo",},
        {"data":"alias",},
        {"data":"bankName",},
        {"data":"bankCard",},
        {"data":"accountName",},
        {"data":"smsContent",},
        {"data":"money",},
        {"data":"curday",},
        {"data":"curhour",},
        {"data":"curminute", }
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
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['curday'] = $("#curday").val();
            account.condJsonData['curhour'] = $("#curhour").val();
            account.condJsonData['curminute'] = $("#curminute").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['bankName'] = "";
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
