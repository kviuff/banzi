
var prefix = "/monitoring/monitoring"
$(function() {
    load();
    $('#exampleTable').bootstrapTable('hideColumn', 'cumulativeFlow');
    $('#exampleTable').bootstrapTable('hideColumn', 'machineName');
    $('#exampleTable').bootstrapTable('showColumn', 'length');

});

function load(type) {

        $('#exampleTable')
            .bootstrapTable(
                {
                    method: 'get', // 服务器数据的请求方式 get or post
                    url: prefix + "/listForUnbound", // 服务器数据的加载地址
                    //	showRefresh : true,
                    //	showToggle : true,
                    //	showColumns : true,
                    iconSize: 'outline',
                    toolbar: '#exampleToolbar',
                    striped: true, // 设置为true会有隔行变色效果
                    dataType: "json", // 服务器返回的数据类型
                    pagination: true, // 设置为true会在底部显示分页条
                    // queryParamsType : "limit",
                    // //设置为limit则会发送符合RESTFull格式的参数
                    singleSelect: false, // 设置为true将禁止多选
                    // contentType : "application/x-www-form-urlencoded",
                    // //发送到服务器的数据编码类型
                    pageSize: 10, // 如果设置了分页，每页数据条数
                    pageNumber: 1, // 如果设置了分布，首页页码
                    //search : true, // 是否显示搜索框
                    showColumns: false, // 是否显示内容下拉框（选择显示的列）
                    sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                    queryParams: function (params) {
                        return {
                            //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                            limit: params.limit,
                            offset: params.offset,
                            userId: $('#userId').val(),
                            machineType: $('#selectList').val(),
                            prop10 : $("#prop10").val()
                        };
                    },
                    // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                    // queryParamsType = 'limit' ,返回参数必须包含
                    // limit, offset, search, sort, order 否则, 需要包含:
                    // pageSize, pageNumber, searchText, sortName,
                    // sortOrder.
                    // 返回false将会终止请求
                    columns: [
                        {
                            checkbox: true
                        },
                        {
                            field: 'id',
                            title: 'ID',
                            align: 'center'
                        },
                        {
                            field: 'monitorCode',
                            title: '机器编号',
                            align: 'center'
                        },
                        {
                            field: 'prop10',
                            title: '机器IP',
                            align: 'center'
                        },
                        {
                            title: '测试',
                            field: 'id',
                            align: 'center',
                            formatter: function (value, row, index) {
								var re = $('#selectList').val();
								var result;
								if(re == '1'){
                                    var a = '<a class="btn btn-primary btn-sm" href="#" title="关机"  mce_href="#" ';
                                    a += 'onclick="editCloseStatusForXinfeng(\'' +  row.id + "," + row.monitorCode + '\')">关机</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="开机"  mce_href="#" ';
                                    a += 'onclick="editOpenStatusForXinfeng(\'' +  row.id + "," + row.monitorCode + '\')">开机</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="1档"  mce_href="#" onclick="editXinfengStall(\''
                                        + row.monitorCode + ",1"
                                        + '\')">1档</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="2档"  mce_href="#" onclick="editXinfengStall(\''
                                        + row.monitorCode + ",2"
                                        + '\')">2档</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="3档"  mce_href="#" onclick="editXinfengStall(\''
                                        + row.monitorCode + ",3"
                                        + '\')">3档</a> ';
                                    result=a;
                                }else if(re == '2'){
                                    var a ='';
                                        a = '<a class="btn btn-primary btn-sm" href="#" title="开机"  mce_href="#" onclick="resetPwd(\''+ row.id +','+0+','+ row.monitorCode+ '\')">开机</a> ';
                                        a += '<a class="btn btn-primary btn-sm" href="#" title="关机"  mce_href="#" onclick="resetPwd(\'' + row.id +','+1+','+ row.monitorCode + '\')">关机</a> ';

                                    result=a;
                                }else if(re == '3'){
                                    var a;
                                    if (row['status'] == '1') {
                                        a = '<button class="layui-btn layui-btn-sm" title="关机" onclick="editOpenStatus(\''
                                            + row.id;
                                        a += '\')">关闭</button> ';
                                    }
                                    if (row['status'] == '2') {
                                        a = '<button class="layui-btn layui-btn-sm" title="关机" onclick="editCloseStatus(\''
                                            + row.id;
                                        a += '\')">开启</a> ';
                                    }

                                    a += '<button class="layui-btn layui-btn-sm" title="关机" onclick="editScene(1)">上课模式</button>';

                                    a += '<button class="layui-btn layui-btn-sm" title="关机" onclick="editScene(2)">下课模式</button>';

                                    a += '<button class="layui-btn layui-btn-sm" title="关机" onclick="editScene(3)">自习模式</button>';

                                    a += '<button class="layui-btn layui-btn-sm" title="关机" onclick="editScene(4)">投影模式</button>';
                                    result=a;
                                }
                                return result;
                            }
                        }]
                });
}
function reLoad() {
    var type = $('#selectList').val();
    console.log(type);


    var opt = {
        query : {
            type : $('.chosen-select').val(),
        }
    }
    $('#exampleTable').bootstrapTable('refresh', opt);

	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}
// 修改新风档位
function editXinfengStall(monitorCode) {
    var str = monitorCode.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "monitorCode" : str[0],
            "stall" : str[1]
        },
        url : prefix + '/editXinfengStall',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
};

function resetPwd(id) {
    var str = id.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "id" : str[0],
            "status" : str[1],
            "monitorCode":str[2]
        },
        url : prefix + '/editWaterStatus',
        success : function(r) {
            console.log(r);
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
}

function editOpenStatus(id) {
    $.ajax({
        type : 'POST',
        data : {
            "id" : id,
            "status" : "2"
        },
        url : prefix + '/updatelampopenorclose',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
}

function editCloseStatus(id) {
    $.ajax({
        type : 'POST',
        data : {
            "id" : id,
            "status" : "1"
        },
        url : prefix + '/updatelampopenorclose',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
}

function editScene(type) {
    $.ajax({
        type : 'POST',
        data : {
            "id" : type
        },
        url : prefix + '/editScene',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
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

// 修改新风开机状态
function editOpenStatusForXinfeng(id) {
    var str = id.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "id" : str[0],
            "monitorCode" : str[1],
            "status" : "1"
        },
        url : prefix + '/editXinfengStatus',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
};

// 修改新风关机状态
function editCloseStatusForXinfeng(id) {
    console.log(id)
    var str = id.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "id" : str[0],
            "monitorCode" : str[1],
            "status" : "0"
        },
        url : prefix + '/editXinfengStatus',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
};

// 修改新风档位
function editXinfengStall(monitorCode) {
    var str = monitorCode.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "monitorCode" : str[0],
            "stall" : str[1]
        },
        url : prefix + '/editXinfengStall',
        success : function(r) {
            if (r.code == 0) {
                layer.msg(r.msg);
                reLoad();
            } else {
                layer.msg(r.msg);
            }
        }
    });
};
