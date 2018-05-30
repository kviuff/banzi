
var prefix = "/monitoring/lightCtr"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : prefix + "/regionList", // 服务器数据的加载地址
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
						pageSize : 100, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						queryParams : function(params) {
							return {
								//说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								limit: params.limit,
								offset:params.offset,
								id:$("#Id").val()
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
									title: '序号',//标题  可不加
									formatter: function (value, row, index) {
										return index+1;
									},
									align : 'center'
								},
																{
									field : 'id', 
									title : 'ID' ,
									align : 'center'
								},

																{
									field : 'name',
									title : '区域' ,
									align : 'center'
								},
																{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var a = '<button class="layui-btn layui-btn-sm" title="绑定区域" onclick="resetPwd(\''
											+ row.id+'\',\''+row.monitorCode+'\',\''+row.controllerAddr+'\',\''+row.lanerusername+'\',\''+row.lanerpassword+'\',\''+row.userId+'\',\''+row.userName
											+ '\')">绑定区域</button>';

										return a;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}

function resetPwd(id,monitorCode,controllerAddr,lanerusername,lanerpassword,userId,userName){
	$("#userId").val(userId);
	$("#userName").val(userName);
	$("#monitorCode").val(monitorCode);
	$("#contollerAddr").val(controllerAddr);
	$("#returnid").val(id);
	$("#lanerUserName").val(lanerusername);
	$("#lanerPassword").val(lanerpassword);
	layer.open({
		title: "绑定区域",
		type: 1,
		btn: ['确定', '取消'],
		area: ['340px', '200px'],
		content: $('#setOffTime'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
		yes: function(index,layero){
			var belongRegion = $("#belongRegion").val();
			var belongFloor = $("#belongFloor").val();
			if (belongRegion == "" || belongFloor == "") {
				layer.msg("请填写内容");
				return;
			} else {
				$.ajax({
					type : 'POST',
					data : $('#mor').serialize(),
					url : '/monitoring/lightCtr/bindRegion',
					success : function(r) {
						if (r.code == 0) {
							layer.msg(r.msg);
							window.location.reload();//刷新当前页面.
						} else {
							layer.msg(r.msg);
						}
					}
				});
			}
		}
	});
}
