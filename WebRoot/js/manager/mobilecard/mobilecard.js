
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/mobilecard/list.do',
        dataList_url : ctx + "/mobilecard/dataList.do",
        add_url : ctx+ "/mobilecard/add.do",
        update_url : ctx+ "/mobilecard/update.do",
        queryId_url: ctx+ "/mobilecard/getId.do",
        delete_url: ctx+ "/mobilecard/delete.do",
        manyOperation_url: ctx+ "/mobilecard/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"cardName",},
        {"data":"useName",},
        {"data":"phoneNum",},
        {"data":"isArrears",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
              if(oData.isArrears==1){
                  html="<span style='color: #29ff20'>未欠费</span>"
              }else{
                  html="<span style='color: #ff301d'>欠费</span>"
              }
              $(nTd).html(html);
            }
        },
        {"data":"heartbeatStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.heartbeatStatus==2){
                    html="<span style='color: #29ff20'>正常</span>"
                }else{
                    html="<span style='color: #ff301d'>异常</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"remark",},
        {"data":"useStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.useStatus==1){
                    html="<span style='color: #29ff20'>正常使用</span>"
                }else if(oData.useStatus==2){
                    html="<span style='color: #ff301d'>暂停使用</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/mobilecard/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
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
            window.location.href = ctx + "/mobilecard/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['useName'] = $("#useName").val();
            account.condJsonData['phoneNum'] = $("#phoneNum").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['useName'] = "";
            account.condJsonData['phoneNum'] = "";
            $("#useName").val("");
            $("#phoneNum").val("");
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
        var url = basePath + "mobilecard/dataAllList.do";
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

$(function(){
    account.indexInit();
})
