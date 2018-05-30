
var prefix = "/monitoring/monitoring"
$(function() {
    load();
    $('#exampleTable').bootstrapTable('hideColumn', 'cumulativeFlow');
    $('#exampleTable').bootstrapTable('showColumn', 'length');

});

function load(type) {

        $('#exampleTable')
            .bootstrapTable(
                {
                    method: 'get', // 服务器数据的请求方式 get or post
                    url: prefix + "/list", // 服务器数据的加载地址
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
							status:$("#status").val(),
                            belongRegion:$("#belongRegion").val(),
                            belongFloor:$("#belongFloor").val()
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
                            field: 'belongRegion',
                            title: '所属区域',
                            align: 'center'
                        },
                        {
                            field: 'belongFloor',
                            title: '所属楼层',
                            align: 'center'
                        },
                        {
                            field: 'status',
                            title: '机器运行情况',
                            align: 'center',
                            formatter: function (value, row, index) {
                                if (row['status'] == "1") {
                                    return "机器运行"
                                }
                                if (row['status'] == "2") {
                                    return "机器关闭"
                                }
                                return value;
                            }
                        },
                        {
                            field: 'length',
                            title: '时长',
                            align: 'center'
                        },
						{
							field: 'cumulativeFlow',
							title: '累计流量',
							align: 'center'
						},
                        {
                            title: '操作',
                            field: 'id',
                            align: 'center',
                            formatter: function (value, row, index) {
								var re = $('#selectList').val();
								var result;
								if(re == '1'){
                                    var a = '<a class="btn btn-primary btn-sm" href="#" title="开机"  mce_href="#" ';
                                    if (row['status'] == '1') {
                                        a += 'onclick=" editCloseStatusForXinfeng(\'' +  row.id + "," + row.monitorCode + '\')">关机</a> ';
                                    }
                                    if (row['status'] == '0') {
                                        a += 'onclick="editOpenStatusForXinfeng(\'' +  row.id + "," + row.monitorCode + '\')">开机</a> ';
                                    }
                                    var e = '<a class="btn btn-primary btn-sm" href="#" title="1档"  mce_href="#" onclick="editXinfengStall(\''
                                        + row.monitorCode + ",1"
                                        + '\')">1档</a> ';
                                    var d = '<a class="btn btn-primary btn-sm" href="#" title="2档"  mce_href="#" onclick="editXinfengStall(\''
                                        + row.monitorCode + ",2"
                                        + '\')">2档</a> ';
                                    var f = '<a class="btn btn-primary btn-sm" href="#" title="3档"  mce_href="#" onclick="editXinfengStall(\''
                                        + row.monitorCode + ",3"
                                        + '\')">3档</a> ';
                                        result=a + e + d + f;
                                }else if(re == '2'){
                                    var a ='';
                                    a = '<a class="btn btn-primary btn-sm" href="#" title="开机"  mce_href="#" onclick="resetPwd(\''+ row.id +','+0+','+ row.monitorCode+ '\')">开机</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="关机"  mce_href="#" onclick="resetPwd(\'' + row.id +','+1+','+ row.monitorCode + '\')">关机</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="更改流量套餐"  mce_href="#" onclick="bind(\''+ row.monitorCode + '\')">更改套餐</a> ';
                                    a += '<a class="btn btn-primary btn-sm" href="#" title="更改计时套餐"  mce_href="#" onclick="changeTime(\''+ row.monitorCode + '\','+row.prop7+')">更改计时套餐</a> ';

                                    result=a;
                                }else if(re == '3'){
                                    var a;
                                        a = '<button class="layui-btn layui-btn-sm" title="查看详情" onclick="lamp(\''+ row.id
                                        a += '\')">查看详情</button> ';
                                    if(row.paramJson != null && row.paramJson!= "") {
                                        var jsonObj =  JSON.parse(row.paramJson);
                                        var jsonArr = [];
                                        for(var i =0 ;i < jsonObj.length;i++){
                                            jsonArr[i] = jsonObj[i];
                                            a += '<button class="layui-btn layui-btn-sm" title="上课模式" onclick="editScene('+jsonArr[i].id+',' + row.id + ')">'+jsonArr[i].name+'</button>';
                                        }
                                    }

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
//更改时间套餐
function changeTime(code,prop7) {
    $.ajax({
        type : 'POST',
        data : {
            "monitorCode":code,
            "prop7":prop7
        },
        url : prefix + '/changeTime',
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
//更改流量套餐
function bind(code) {
    layer.open({
        title: "更改绑定套餐",
        type: 1,
        btn: ['确定', '取消'],
        area: ['340px', '200px'],
        content: $('#setMeal'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        yes: function(index,layero){
            var prop8 = $("#prop8").val();
            if (prop8 == "") {
                layer.msg("请选择绑定套餐");
                return;
            } else {
                $.ajax({
                    type : 'POST',
                    data : {
                        "monitorCode":code,
                        "prop8":prop8
                    },
                    url : prefix + '/bind',
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
        }
    });

}


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
function lamp(id){
    window.location.href="/monitoring/lamp/page?id="+id;
}

function editScene(code,type) {

    $.ajax({
        type : 'POST',
        data : {
            "id" : type,
            "scene" : code
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

// 修改新风开关机状态
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

// 修改新风开关机状态
function editCloseStatusForXinfeng(id) {
    var str = id.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "status" : "0",
            "id" : str[0],
            "monitorCode" : str[1]
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

// 水板子关机
function closeWaterStatus(id) {
    var str = id.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "id" : str[0],
            "monitorCode" : str[1],
            "status" : "0"
        },
        url : prefix + '/editWaterStatus',
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

// 水板子开机
function openWaterStatus(id) {
    var str = id.split(",");
    $.ajax({
        type : 'POST',
        data : {
            "status" : "1",
            "id" : str[0],
            "monitorCode" : str[1]
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

