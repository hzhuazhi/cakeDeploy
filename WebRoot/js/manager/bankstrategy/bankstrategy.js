
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/bankstrategy/list.do',
        dataList_url : ctx + "/bankstrategy/dataList.do",
        add_url : ctx+ "/bankstrategy/add.do",
        update_url : ctx+ "/bankstrategy/update.do",
        queryId_url: ctx+ "/bankstrategy/getId.do",
        delete_url: ctx+ "/bankstrategy/delete.do",
        manyOperation_url: ctx+ "/bankstrategy/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = "<input type='checkbox' name='ckbx' value="+oData.id+" />";
                $(nTd).html(html);
            }
        },
        {"data":"alias",},
        {"data":"bankId",},
        {"data":"bankCard",},
        {"data":"bankName",},
        {"data":"accountName",},
        {"data":"zfbInDayMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
               let  names ='zfbInDayMoney'+iRow;
               var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.zfbInDayMoney+" />";
               $(nTd).html(html);
            }
        },
        {"data":"zfbInMonthMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='zfbInMonthMoney'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.zfbInMonthMoney+" />";
                $(nTd).html(html);
            }
        },
        {"data":"zfbInDayNum",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='zfbInDayNum'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.zfbInDayNum+" />";
                $(nTd).html(html);
            }
        },
        {"data":"cardInDayMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='cardInDayMoney'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.cardInDayMoney+" />";
                $(nTd).html(html);
            }
        },
        {"data":"cardInMonthMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='cardInMonthMoney'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.cardInMonthMoney+" />";
                $(nTd).html(html);
            }
        },
        {"data":"cardInDayNum",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='cardInDayNum'+iRow;
                var html = "<input type='text' size='12' id="+names+" name="+names+"  value="+oData.cardInDayNum+" />";
                $(nTd).html(html);
            }
        },
        // {"data":"priority",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         let  names ='priority'+iRow;
        //         var html = "<input type='text' size='2' id="+names+" name="+names+"  value="+oData.priority+" />";
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"openTimeSlot",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='openTimeSlot'+iRow;
                var html = "<input type='text' size='20' id="+names+" name="+names+"  value="+oData.openTimeSlot+" />";
                $(nTd).html(html);
            }
        },
        {"data":"useStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.useStatus==1){
                    html+= '<span style="color: #2f9833">正在使用</span>';
                }else if(oData.useStatus==2){
                    html+= '<span style="color: #ff3710">暂停使用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';

                if(oData.useStatus==1){
                    html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="javascript:void(0);" onclick="uqdateUseStatus('+oData.id+',2)"> 禁用 </a>';
                }else if(oData.useStatus==2){
                    html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="javascript:void(0);" onclick="uqdateUseStatus('+oData.id+',1)">  启用 </a>';
                }
                // html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/bank/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="#" onclick="queryValue('+iRow+','+oData.id+')"> 保存 </a>'
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
            // window.location.href = ctx + "/bankstrategy/add.do";
            $.ajax({
                url : ctx+ "/bankstrategy/add.do",
                type : 'post',
                dataType : 'json',
                // data :data,
                success : function(data) {
                    if (data.success) {
                        alert("导入成功");
                        window.location.href = ctx + "/bankstrategy/list.do";
                    } else {
                        alert(data.msg);
                    }
                },
                error : function(data) {
                    alert(data.info);
                }
            });
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['priority'] = $("#priority").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['bankName'] = $("#bankName").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['useStatus'] = $("#useStatus").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['alias'] = "";
            account.condJsonData['priority'] = "";
            account.condJsonData['bankCard'] = "";
            account.condJsonData['bankName'] = "";
            account.condJsonData['accountName'] = "";
            account.condJsonData['useStatus'] = "";
            $("#alias").val("");
            $("#priority").val("");
            $("#bankCard").val("");
            $("#bankName").val("");
            $("#accountName").val("");
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


        $('#butUqdate').click(function() {
            var str = "";
            $("input[name='ckbx']:checked").each(function (index, item) {
                if ($("input[name='ckbx']:checked").length - 1 == index) {
                    str += $(this).val();
                } else {
                    str += $(this).val() + ",";
                }
            });
            var   zfbInDayMoney =$("#zfbInDayMoney").val();
            var   zfbInMonthMoney =$("#zfbInMonthMoney").val();
            var   zfbInDayNum =$("#zfbInDayNum").val();
            var   cardInDayMoney =$("#cardInDayMoney").val();
            var   cardInMonthMoney =$("#cardInMonthMoney").val();
            var   cardInDayNum =$("#cardInDayNum").val();
            var   openTimeSlot =$("#openTimeSlot").val();
            if(zfbInDayMoney==""&&zfbInMonthMoney==""&&
                zfbInDayNum==""&&cardInDayMoney==""&&
                cardInMonthMoney==""&&cardInDayNum==""&&
                openTimeSlot==""){
                alert("请填写需要修改的字段不能为空");
                return;
            }

            if (str=="") {
                alert("请选择需要修改的数据，再进行批量修改！");
                return;
            }
            var  data={
                "zfbInDayMoney":zfbInDayMoney,
                "zfbInMonthMoney":zfbInMonthMoney,
                "zfbInDayNum":zfbInDayNum,
                "cardInDayMoney":cardInDayMoney,
                "cardInMonthMoney":cardInMonthMoney,
                "cardInDayNum":cardInDayNum,
                "openTimeSlot":openTimeSlot,
                "ids":str
            }

            $.ajax({
                url : ctx+ "/bankstrategy/updateBatch.do",
                type : 'post',
                dataType : 'json',
                data :data,
                success : function(data) {
                    if (data.success) {
                        alert("修改成功！");
                        window.location.href = ctx + "/bankstrategy/list.do";
                    } else {
                        alert(data.msg);
                    }
                },
                error : function(data) {
                    alert(data.info);
                }
            });

        });
    },

    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    queryBankAll:function(){
        var url = basePath + "bankstrategy/dataAllList.do";
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


function  queryValue(i,id){
    let zfbInDayMoney= $("#"+'zfbInDayMoney'+i).val();
    let zfbInMonthMoney= $("#"+'zfbInMonthMoney'+i).val();
    let zfbInDayNum= $("#"+'zfbInDayNum'+i).val();
    let cardInDayMoney= $("#"+'cardInDayMoney'+i).val();
    let cardInMonthMoney= $("#"+'cardInMonthMoney'+i).val();
    let cardInDayNum= $("#"+'cardInDayNum'+i).val();
    let priority= $("#"+'priority'+i).val();
    let openTimeSlot= $("#"+'openTimeSlot'+i).val();

    let  data={
        "zfbInDayMoney":zfbInDayMoney,
        "zfbInMonthMoney":zfbInMonthMoney,
        "zfbInDayNum":zfbInDayNum,
        "cardInDayMoney":cardInDayMoney,
        "cardInMonthMoney":cardInMonthMoney,
        "cardInDayNum":cardInDayNum,
        "priority":priority,
        "openTimeSlot":openTimeSlot,
        "id":id
    }

    $.ajax({
        url : ctx+ "/bankstrategy/update.do",
        type : 'post',
        dataType : 'json',
        data :data,
        success : function(data) {
            if (data.success) {
                alert("修改成功！");
                window.location.href = ctx + "/bankstrategy/list.do";
            } else {
                alert(data.msg);
            }
        },
        error : function(data) {
            alert(data.info);
        }
    });


}


function  uqdateUseStatus(id,useStatus){
    let  data={
        "useStatus":useStatus,
        "id":id
    }

    $.ajax({
        url : ctx+ "/bankstrategy/manyOperation.do",
        type : 'post',
        dataType : 'json',
        data :data,
        success : function(data) {
            if (data.success) {
                alert("修改成功！");
                window.location.href = ctx + "/bankstrategy/list.do";
            } else {
                alert(data.msg);
            }
        },
        error : function(data) {
            alert(data.info);
        }
    });


}


$(function(){
    window.onload = function() {
        var btn = document.getElementById("all");
        btn.onclick = function() {
            var flag = this.checked;
            var items = document.getElementsByName("ckbx");
            for (var i = 0; i < items.length; i++) {
                items[i].checked = flag;//将所有item的状态设为全选按钮的状态
            }
        }


        var items = document.getElementsByName("ckbx");
        for (var i = 0; i < items.length; i++) {
            items[i].onclick = function() {//对每个item设置点击
                var number = 0;//记录选中的个数
                for (var j = 0; j < items.length; j++) {
                    if (items[j].checked) {
                        number++;
                    }
                }
                document.getElementById("all").checked = (items.length == number);
            }
        }
    }
    account.indexInit();
})
