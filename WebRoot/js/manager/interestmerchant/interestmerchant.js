
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/interestmerchant/list.do',
        dataList_url : ctx + "/interestmerchant/dataList.do",
        add_url : ctx+ "/interestmerchant/add.do",
        update_url : ctx+ "/interestmerchant/update.do",
        queryId_url: ctx+ "/interestmerchant/getId.do",
        delete_url: ctx+ "/interestmerchant/delete.do",
        manyOperation_url: ctx+ "/interestmerchant/manyOperation.do"
    },
    //列表显示参数
    list:[
        // {"data":"alias",},
        {"data":"acName",},
        {"data":"serviceCharge",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                let  names ='serviceCharge'+iRow;
                html="<input type='text' id='"+names+"' name='"+names+"' value='"+oData.serviceCharge+"'>";
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html  = ''
                    + '<a class = "dataTableBtn dataTableDeleteBtn " href="javascript:void(0);" onclick="sava('+iRow+','+oData.id+')"> 保存 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        interestId:$("#interestId").val()
    },


    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/interestmerchant/jumpAdd.do?id="+$("#interestId").val();
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


function sava(i,id){
    let serviceCharge= $("#"+'serviceCharge'+i).val();
    var url = ctx + "/interestmerchant/update.do";
    var data = {
        serviceCharge:serviceCharge,
        id:id
    };
    common.ajax(url,data,function(data){
        var dataList=data;
       // if(dataList=="success"){
           window.location.href = ctx + "/interest/jumpAdd.do?id="+$("#interestId").val();
       // }else{
       //     alert(dataList);
       // }
    });
}

$(function(){
    account.indexInit();
})
