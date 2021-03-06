$(function(){
$.fn.getPromotionProduct = function(options){
	
	var tr;
	
    this.bind("click", function(e){
    	tr = $(this).closest("tr");
        chooseProduct();
    });
    
    //默认传递参数
    var defaults = {
    	//orderType:"SPECIAL",	//订单类型：SPECIAL特价促销单，GIVING：满额赠送单
        dataTableId: "#product_table",//页面上的table
        dialogTableId: '#choose_pmpro_table', // dialog对应的出现结果 TABLE的ID
        artDialogReferId: "choose_pmpro_con"// 对应的弹出对话框的内容
    };
    
    var options = $.extend(defaults, options);//如有参数将覆盖掉默认传递参数
    
    var dialogTableId = options.dialogTableId, 
    	dataTableId = options.dataTableId, 
    	artDialogReferId = options.artDialogReferId, 
    	dialogTbody = $(dialogTableId +">tbody"),
    	orderType = options.orderType, 
    	datatable = null;
   
    //初始化查询条件
	 var condJsonData = {
		type0Id:null,
		type1Id:null,
		type2Id:null,
		name:null,
		aoData:null
     };
	 //选择框改变事件 
	 $("#btnQuery").click(function(){
		 condJsonData['name'] = $("#dsearchInput").val();
		 getProducts(condJsonData);
	 });
	 //选择框改变事件 
	 $("#type0Id").change(function(){
			if($("#tycpe0Id").val()==0) {
				condJsonData['type0Id']=null;
			} else{
				condJsonData['type0Id'] = $("#type0Id").val();
			}
			condJsonData['type1Id']=null;
			condJsonData['type2Id']=null;
			condJsonData['name'] = $("#dsearchInput").val();
			//替换二级子类别
			$('#type1Id').html('<option value = "0">　请选择　</option>' ).append($('#select-type1 option[parentId="'+$(this).val()+'"]').clone());  
			$('#type2Id').html('<option value = "0">　请选择　</option>');
			getProducts(condJsonData);
	});
	//选择框改变事件
	 $("#type1Id").change(function(){
			if($("#type1Id").val()==0)  {
				condJsonData['type1Id']=null;
			}
			else{ 
			condJsonData['type1Id'] = $("#type1Id").val();
			}
			condJsonData['type2Id']=null; 
			condJsonData['name'] = $("#dsearchInput").val();
			//替换二级子类别
			$('#type2Id').html('<option value = "0">　请选择　</option>').append($('#select-type2 option[parentId="'+$(this).val()+'"]').clone());  
			getProducts(condJsonData);
	});
	//选择框改变事件
	 $("#type2Id").change(function(){
			if($("#type2Id").val()==0)
				{condJsonData['type2Id']=null;}
			else{
			condJsonData['type2Id'] = $("#type2Id").val();
			}
			condJsonData['name'] = $("#dsearchInput").val();
			getProducts(condJsonData);
	});
	//重置按钮事件
	 $("#condForm").find("input:reset").click(function(){
		 condJsonData['name']=null;
		 condJsonData['type0Id']=null;
		 condJsonData['type1Id']=null;
		 condJsonData['type2Id']=null;
		 getProducts(condJsonData);
	 });
    //弹出框
    var chooseProduct = function(){
        var dialog = art.dialog.get('promotion_products_dialog');
        if (dialog) {
            dialog.close();
        }
        getProducts(condJsonData);
        art.dialog({
            id: 'promotion_products_dialog',
            title: "产品选择",
            content: document.getElementById(artDialogReferId),
            lock: true,
            okValue: "确认提交",
            cancelValue: "取消",
            ok: function(){
            	submitGiving();
            	return false;
            },
            cancel:function(){}
        });
    };
    // -----------------------------------------------------获取商品列表----------------------------------------------------------
    function getProducts(condJsonData){
		if(datatable){
			datatable.fnDestroy();
		}
    	datatable = $(dialogTableId).dataTable({
			"sDom": "<'dtTop'<'dtShowPer'l>><'dtTables't><'dtBottom'<'dtInfo'i><'dtPagination'p>>",
	   	    "sPaginationType": "full_numbers",
	   	    "fnInitComplete": function(){
	   	    	$(".dtFilter input").addClass("simple_field").css({
		   	    	"width": "auto",
		   	    	"margin-left": "15px"
	   	   		});
	   	    },
		    "aaSorting": [[0,'desc']], //默认按第2列排序
	    	"iDisplayLength":10, // 页面大小
	    	"bPaginate" : true,
	    	"bFilter" : false,
	    	"bSort" : false,
	    	"bRetrieve":true,
	    	"bServerSide" : true,
	    	"sAjaxSource" : ctx + '/admin/list-product',
	    	//根据对应列名显示值 
			"aoColumns": [
                {
					"sName" : "id",
					"fnRender" : function(oObj){
						var html = '<input type="checkbox" name="checks" value = "' + oObj.aData[0] + '">';
							html+='<input type="hidden" name="name" value = "' + oObj.aData[1] + '">';
							html+='<input type="hidden" name="fullTypeName" value = "' + oObj.aData[2] + '">';
							html+='<input type="hidden" name="price" value = "' + oObj.aData[3] + '">';
							html+='<input type="hidden" name="shopname" value = "' + oObj.aData[4] + '">';
							html+='<input type="hidden" name="pic" value = "' + oObj.aData[5] + '">';
		    	    	return html;
		    	    }
				},
	            {"sName" : "name"},
	            {"sName" : "fullTypeName"},
	            {"sName" : "price"},
	            {"sName" : "shopname"}
	         ],
			 "fnServerData" : function(sSource, aoData, fnCallback) {
				condJsonData['aoData'] = JSON.stringify(aoData);  
				 $.ajax({
						type : 'post',
						dataType: 'json',
						url : sSource,
						data : condJsonData, // 以json格式传递
						success : function(resp) {
							fnCallback(resp);
						}
				});
			 }
		});
    	$(dialogTableId).css('width','100%');
    }

    var submitGiving = function(){
    	var checkInput = $(dialogTbody).find("tr input:checked");
    	var length = $(dialogTbody).find("tr input:checked").length;
    	for(var i = 0 ;i<length ;i++){
    		var curTr = $(checkInput[i]).closest("tr");
    		var productId = $(curTr).find("input:checked").val();
    		if(!isExist(productId)){
    			var name = $(curTr).find("input[name='name']").val();
    			var fullTypeName = $(curTr).find("input[name='fullTypeName']").val();
    			var pic = $(curTr).find("input[name='pic']").val();
    			
    			var n;
    			$(".productId").each(function(i){
    				n=i;
    				if($(this).val() == productId){
    					alert('楼层中已经有该商品，请重新选择');
    					return false;
    				}
    			});
    			if(n+1 == $(".productId").length){
    				tr.find('td.productPic img').attr("src", ctx+"/file/"+pic);
        			tr.find('.productId').val(productId);
        			tr.find(".productName").val(name);
        			tr.find(".productName2").html(name);
        			tr.find(".productPid").val(pic);
    			}
    		}
    	}
    	var dialog = art.dialog.get('promotion_products_dialog');
    	if (dialog) {
    		dialog.close();
    	}
    };
    
  //是否存在
    function isExist(productId){
    	var productIds = $(dataTableId + " tbody input[name='productId']");
    	for(var i = 0;i<productIds.length;i++){
    		if($(productIds[i]).val() == productId){
    			return true;
    		}
    	}
    	return false;
    }
};
});