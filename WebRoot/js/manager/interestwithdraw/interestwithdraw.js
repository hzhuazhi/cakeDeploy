
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/interestwithdraw/list.do',
        dataList_url : ctx + "/interestwithdraw/dataList.do",
        add_url : ctx+ "/interestwithdraw/add.do",
        update_url : ctx+ "/interestwithdraw/update.do",
        queryId_url: ctx+ "/interestwithdraw/getId.do",
        delete_url: ctx+ "/interestwithdraw/delete.do",
        manyOperation_url: ctx+ "/interestwithdraw/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"orderNo",},
        {"data":"acName",},
        {"data":"outBankName",},
        {"data":"outBankCard",},
        {"data":"outAccountName",},
        {"data":"money",},
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.orderStatus==1){
                    html="<span>初始化</span>";
                }else if(oData.orderStatus==2){
                    html="<span>失败</span>";
                }else if(oData.orderStatus==3){
                    html="<span>失败</span>";
                }else if(oData.orderStatus==4){
                    html="<span style='color: #bb0000'>成功</span>";
                }
                $(nTd).html(html);
            }
        },
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.checkStatus==1){
                    html="<span>初始化</span>";
                }else if(oData.checkStatus==2){
                    html="<span>失败</span>";
                }else if(oData.checkStatus==3){
                    html="<span>失败</span>";
                }else if(oData.checkStatus==4){
                    html="<span style='color: #bb0000'>成功</span>";
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.checkStatus!=4&&oData.orderStatus!=4){
                    html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/interestwithdraw/jumpUpdate.do?id='+oData.id+'"> 审核 </a>';
                }else{
                    html="完成";
                }

                // if($('#roleId').val()==1){
                //     html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/merchant/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                //     html+=' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                // }
                $(nTd).html(html);
            }
        }

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/merchantwithdraw/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['accountNum'] = $("#accountNum").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['alias'] = "";
            account.condJsonData['accountNum'] = "";
            $("#alias").val("");
            $("#accountNum").val("");
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
        var url = basePath + "merchantrecharge/dataAllList.do";
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

function  orderHandle(id){
    if(confirm("确定要处理该订单，订单会因为你触发而锁住的！")){
        var data = {
            "id":id,
            "op":1
        };
        var url = ctx + "/merchantrecharge/chechData.do";
        common.ajax(url,data,function(data){
            if(data.type==1){
                window.location.href = ctx +data.rs;
            }else{
                alert(data.rs);
            }
            // if(data==""){
            //     alert("该订单已经被人在处理了，请重新选择订单");
            //     return;
            // }else{
            //     window.location.href = data;
            // }
        });
    }
}

$(function(){
    account.indexInit();
})
