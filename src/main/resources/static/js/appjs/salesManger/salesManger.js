
$(function() {
	load();
});

function load() {
    console.log($("#accountnumberType").val())
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // 服务器数据的请求方式 get or post
						url : "/salesMange/sale/list", // 服务器数据的加载地址
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
								checkbox: true
							},
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
								align : 'center'
							},

															{
								field : 'name',
								title : '销售姓名' ,
								align : 'center'
							},
															{
								field : 'username',
								title : '账号',
								align : 'center'
							},
															{
								field : 'mobile',
								title : '电话',
								align : 'center'
							},
															{
								field : 'position',
								title : '职位',
								align : 'center'
							},
							{
								field : 'userNameCreate',
								title : '隶属总监',
								align : 'center'
							},
							{
								field : 'gmtCreate',
								title : '创建时间',
								align : 'center',
								formatter : function(value, row, index) {
									var jsonObj =  getMyDate(value);
									return jsonObj  ;
								}
							},
                            {
                                title : '操作',
                                field : 'userIdCreate',
                                align : 'center',
                                formatter : function(value, row, index) {
                                    var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看客户" onclick="look(\''
                                        + row.userId
                                        + '\')">查看客户</a> ';
                                    if(row.accountnumberType =="4"){
                                    	e="";
									}
                                    return e  ;
                                }
                            }
						]
					});
}
function reLoad() {

	$('#exampleTable').bootstrapTable('refresh');
}

function qie(type) {
	$("#accountnumberType").val(type);
	if(2 == type){
		$('#exampleTable').bootstrapTable('showColumn', 'userIdCreate');
	}else{
		$('#exampleTable').bootstrapTable('hideColumn', 'userIdCreate');
	}
	reLoad()
}
function look(id) {
	window.location.href="/accountManger/account/customManagerPage?userId="+id;
}
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};
//补0操作
function getzf(num){
    if(parseInt(num) < 10){
        num = '0'+num;
    }
    return num;
}

function resetPwd(id) {
}
function getMyDate(str){
    var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth()+1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间
    return oTime;
};

//转让销售
function transfer(chiefId){
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要转让的数据");
		return;
	}
	var ids = new Array();
	// 遍历所有选择的行数据，取每条数据对应的ID
	$.each(rows, function(i, row) {
		ids[i] = row['userId'];
	});


}