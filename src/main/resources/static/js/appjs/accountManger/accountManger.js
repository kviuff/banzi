
var prefix = "account";
$(function() {
	load();
    loadButton();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
					//	showRefresh : true,
					//	showToggle : true,
					//	showColumns : true,
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
								limit: params.limit,
								offset:params.offset,
                                accountnumberType:$("#accountnumberType").val(),
                                sname:$('#sname').val()
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
                                    title: '序号',//标题  可不加
                                    formatter: function (value, row, index) {
                                        return index+1;
                                    },
                                    align : 'center'
								},
																{
									field : 'userId',
									title : 'ID' ,
									align : 'center',
									width:80
								},

																{
									field : 'username',
									title : '账号' ,
									align : 'center',
									width:200
								},
																{
									field : 'accountnumberType',
									title : '账号类型',
									align : 'center',
									width:150,
									formatter: function (value, row, index) {
										if (row['accountnumberType'] == "0") {
											return "客户账号"
										}
										if (row['accountnumberType'] == "1") {
											return "经销商账号"
										}
										if (row['accountnumberType'] == "2") {
											return "销售账号"
										}
										if (row['accountnumberType'] == "3") {
											return "客服账号"
										}
										if (row['accountnumberType'] == "4") {
											return "总监账号"
										}
										return value;
									}
								},
																{
									field : 'companyName',
									title : '公司名称',
									align : 'center',
									width:200
								},
								{
									field : 'userId',
									title : '操作',
									align : 'center',
                                    formatter : function(value, row, index) {

										var a = '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="detail" onclick="remove(\'' +
                                            + row.userId +
                                            '\')">删除账号</a>';
                                        var b ='<a class="layui-btn layui-btn-xs" lay-event="detail" onclick="look(\''+row.userId+'\',\'look\')">查看账号</a>';
                                        var c ='<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail" onclick="look(\''+row.userId+'\',\'edit\')">修改账号</a>';
                                        var d ='<a class="layui-btn layui-btn-xs layui-btn-warm" href="javascript:;" onclick="resetPwd(\''+ row.userId+ '\')">重置账号</a>';


                                        // var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看详情" onclick="look(\''
                                        //     + row.userId
                                        //     + '\')">查看详情</a> ';
                                        return a+b+c+d;
                                    }
								}
								]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}



function remove(id) {

        $.ajax({
            url : "/sys/user/remove",
            type : "post",
            data : {
                'id' : id
            },
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

function resetPwd(id) {

    $.ajax({
        cache : true,
        type : "POST",
        url : "/sys/user/adminResetPwd",
        data :{"pwdNew":"111111","userDO.userId":id},
        async : false,
        success : function(data) {
            if (data.code == 0) {
                parent.layer.msg(data.msg);
            } else {
                parent.layer.msg(data.msg);
            }

        }
    });

}

function look(id,type) {
    window.location.href = "/accountManger/account/lookPage?id="+id+"&type="+type;

}

function loadButton() {
    var accountnumberTypeValue = $("#accountnumberTypeValue").val();
    console.log(accountnumberTypeValue)
    var html = "";
    // 账号类型:0客户  1:经销商 2:销售 3:客服 4:总监
	//<a onclick="add('1')">添加客户账号</a>
    //<a onclick="add('2')">添加经销商账号</a>
    //<a onclick="add('3')">添加销售账号</a>
    //<a onclick="add('4')">添加客服账号</a>
    //<a onclick="add('5')">添加总监账号</a>

    if ("0" == accountnumberTypeValue) {

	} else if ("1" == accountnumberTypeValue) {
		html += "<a onclick=\"add('1')\">添加客户账号</a>";
		html += "<a onclick=\"add('4')\">添加客服账号</a>";
		html += "<a onclick=\"add('5')\">添加总监账号</a>";
	} else if ("2" == accountnumberTypeValue) {
        html += "<a onclick=\"add('1')\">添加客户账号</a>";
    } else if ("3" == accountnumberTypeValue) {

    } else if ("4" == accountnumberTypeValue) {
        html += "<a onclick=\"add('1')\">添加客户账号</a>";
		html += "<a onclick=\"add('3')\">添加销售账号</a>";
    } else {
    	html += "<a onclick=\"add('2')\">添加经销商账号</a>";
        html += "<a onclick=\"add('1')\">添加客户账号</a>";
        html += "<a onclick=\"add('3')\">添加销售账号</a>";
        html += "<a onclick=\"add('4')\">添加客服账号</a>";
        html += "<a onclick=\"add('5')\">添加总监账号</a>";
	}

	$(".layui-breadcrumb").append(html);
}
