
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/bankpool/list.do',
        dataList_url : ctx + "/bankpool/dataList.do",
        add_url : ctx+ "/bankpool/add.do",
        update_url : ctx+ "/bankpool/update.do",
        queryId_url: ctx+ "/bankpool/getId.do",
        delete_url: ctx+ "/bankpool/delete.do",
        manyOperation_url: ctx+ "/bankpool/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"acName",},
        {"data":"bankName",},
        {"data":"bankCard", },
        {"data":"accountName", },
        {"data":"openTimeSlot", },
        {"data":"smoney", },
        {"data":"snum", },
        {"data":"isOk",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.isOk==1){
                    html='<span style="color: #bb0000">未通过</span>';
                }else if(oData.isOk==2){
                    html='<span>通过</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"isArrears",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.isArrears==2){
                    html='<span style="color: #bb0000">欠费</span>';
                }else if(oData.isArrears==1){
                    html='<span>未欠费</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"heartbeatStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.heartbeatStatus==1){
                    html='<span style="color: #bb0000">异常</span>';
                }else if(oData.heartbeatStatus==2){
                    html='<span>正常</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"mbUseStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.mbUseStatus==2){
                    html='<span style="color: #bb0000">未使用</span>';
                }else if(oData.mbUseStatus==1){
                    html='<span>正常</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"checkStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.checkStatus==2){
                    html='<span style="color: #bb0000">不正常</span>';
                }else if(oData.checkStatus==1){
                    html='<span>正常</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"bkUseStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.bkUseStatus==2){
                    html='<span style="color: #bb0000">未使用</span>';
                }else if(oData.bkUseStatus==1){
                    html='<span>正常</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';

                html+= ''
                // html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelbank/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    // +  '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channel/jumpUpdate.do?op=1&id='+oData.id+'"> 部署 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        channelId:$("#channelId").val()
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/bankpool/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件

    },



    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    queryBankAll:function(){
        var url = ctx + "/bankpool/queryNotChannelBankList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            for(let i =0;i<dataList.rows.length;i++){
                if(i!=0&&i%6===0){
                    shtml +="<br>";
                }
                shtml += "<input type='checkbox' name='bankId' id='bankId' value="+dataList.rows[i].bankId+"> "+dataList.rows[i].bankName+"="+ dataList.rows[i].accountName +"="+ dataList.rows[i].acName +"&nbsp;&nbsp;";
            }
            $("#cardId").html(shtml);
        });
    }
}


// function  addBank(){
//     obj = document.getElementsByName("bankId");
//     check_val = [];
//     for (k in obj) {
//         //判断复选框是否被选中
//         if (obj[k].checked)
//         //获取被选中的复选框的值
//             check_val.push(obj[k].value);
//     }
//     alert(check_val);
// }
$('#btnQuery').click(function() {
    // account.condJsonData['alias'] = $("#alias").val();
    // account.condJsonData['secretKey'] = $("#secretKey").val();
    // account.condJsonData['useStatus'] = $("#useStatus").val();
    // common.showDatas(account.condJsonData,account.list);
    var str = "";
    $("input[name='bankId']:checked").each(function (index, item) {
        if ($("input[name='bankId']:checked").length - 1 == index) {
            str += $(this).val();
        } else {
            str += $(this).val() + ",";
        }
    });

    var url = ctx + "/bankpool/add.do";
    var data = {
        "channelId":$("#channelId").val(),
        "bankIds":str
    };
    common.ajax(url,data,function(data){
        // debugger;
        var dataList=data;
        // var shtml="";
        account.indexInit();
        account.queryBankAll();
        str="";
    });

});

// 重置
$("#butReset").click(function(){
    account.condJsonData['alias'] = "";
    account.condJsonData['secretKey'] = "";
    account.condJsonData['useStatus'] = "";
    $("#alias").val("");
    $("#secretKey").val("");
    $("#useStatus").val("0");
    common.showDatas(account.condJsonData,account.list);
});
//删除
$(".dataTableResetBtn").live("click",function(){
    var url = ctx + "/bankpool/delete.do";
    var id = $(this).attr('directkey');
    var data = {
        id:id,
        yn:'1'
    }
    common.ajax(url,data,function(data){
        // debugger;
        // var dataList=data;
        // var shtml="";
        account.indexInit();
        account.queryBankAll();
        str="";
    });
    // common.updateStatus(data);
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

$(function(){
    account.indexInit();
    account.queryBankAll();
})
