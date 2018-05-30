
var prefix = "/ordermanager/orderGoods"
$(function() {
	load();
    $("#ordercount").text($("#ordertotalcount").val());
    $("#orderAmount").text($("#ordertotalamount").val());
});

function load() {
	$('#exampleTable1')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/OrderGoods1List", // 服务器数据的加载地址
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: 200,
								offset:params.offset,
                                orderSn : $('#orderSn').val()
					           // name:$('#searchName').val(),
					           // username:$('#searchName').val()
							};
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
								{
									field : 'goodsName',
									title : '产品名称'
								},
								{
									field : 'goosSn',
									title : '产品型号'
								},
								{
									field : 'goodsPrice',
									title : '产品单价'
								},
								{
									field : 'goodsNum',
									title : '销售数量'
								},
								{
									field : 'prop2',
									title : '备注'
								}]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['id'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}


function addOrder(id) {
    window.location.href = "/ordermanager/orderList/add?userId=" + id;
}

function audit() {
    $.ajax({
        type : 'POST',
        data : {
            "id" : $("#orderId").val()
        },
        url : '/ordermanager/orderList/orderAudit',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                window.location.href = "/ordermanager/orderList";
            } else {
                layer.msg(r.msg);
            }
        }
    });
};

function commitAuth() {
    $.ajax({
        type : 'POST',
        data : {
            "id" : $("#orderId").val()
        },
        url : '/ordermanager/orderList/remove',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                window.location.href = "/ordermanager/orderList";
            } else {
                layer.msg(r.msg);
            }
        }
    });
}