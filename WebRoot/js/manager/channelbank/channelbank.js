
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/channelbank/list.do',
        dataList_url : ctx + "/channelbank/dataList.do",
        add_url : ctx+ "/channelbank/add.do",
        update_url : ctx+ "/channelbank/update.do",
        queryId_url: ctx+ "/channelbank/getId.do",
        delete_url: ctx+ "/channelbank/delete.do",
        manyOperation_url: ctx+ "/channelbank/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"alias",},
        {"data":"bankCardInfo",},
        // {"data":"createTime", },
        {"data":"useStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.useStatus==1){
                    html+= '<span style="color: #2f9833">正常使用</span>';
                }else if(oData.useStatus==2){
                    html+= '<span style="color: #ff3710">暂停使用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                // if(oData.useStatus==1){
                //     html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelbank/manyOperation.do?useStatus=2&id='+oData.id+'" > 禁用 </a>';
                // }else if(oData.useStatus==2){
                //     html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelbank/manyOperation.do?useStatus=1&id='+oData.id+'" >  启用 </a>';
                // }
                html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelbank/jumpBankUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    // +  '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channel/jumpUpdate.do?op=1&id='+oData.id+'"> 部署 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        // accountNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/channelbank/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['secretKey'] = $("#secretKey").val();
            account.condJsonData['useStatus'] = $("#useStatus").val();
            common.showDatas(account.condJsonData,account.list);
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

$(function(){
    account.indexInit();
})
