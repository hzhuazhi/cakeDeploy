var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/cardsite/list.do',
        dataList_url : ctx + "/cardsite/dataList.do",
        add_url : ctx+ "/cardsite/add.do",
        update_url : ctx+ "/cardsite/update.do",
        queryId_url: ctx+ "/cardsite/getId.do",
        delete_url: ctx+ "/cardsite/delete.do",
        manyOperation_url: ctx+ "/cardsite/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"id",},
        {"data":"acName",},
        {"data":"accountNum",},
        {"data":"passWd",},
        {"data":"acContacts",},
        {"data":"acPhone",},
        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.isEnable==0){
                    html+= '<span style="color: #ff3710">===请选择===</span>';
                }else if(oData.isEnable==1){
                    html+= '<span style="color: #ff3710">暂停使用</span>';
                }else if(oData.isEnable==2){
                    html+= '<span style="color: #2f9833">正常状态</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html  = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/cardsite/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
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
            window.location.href = ctx + "/cardsite/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['acName'] = $("#acName").val();
            account.condJsonData['accountNum'] = $("#accountNum").val();
            account.condJsonData['isEnable'] = $("#isEnable").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['acName'] = "";
            account.condJsonData['accountNum'] = "";
            account.condJsonData['isEnable'] = "";
            $("#aacNamelias").val("");
            $("#accountNum").val("");
            $("#isEnable").val("0");
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
