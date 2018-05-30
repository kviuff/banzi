var prefix = "";
var prefix1 = "/ordermanager/orderGoods"
$(function () {
    load();
    load1();
    load2();
    load3();
    load4();
    load5();
    load12();
    load13();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/accountManger/account/customManagerList", // 服务器数据的加载地址
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
                        sname: $('#sname').val(),
                        accountnumberType: 0,
                        userId: $("#userId").val()
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
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        },
                        align: 'center',
                        width: 50
                    }, {
                        field: 'userId',
                        title: 'ID',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'username',
                        title: '客户名称',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'companyUser',
                        title: '负责人',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'personPhone',
                        title: '负责人电话',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'area',
                        title: '位置',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'customerType',
                        title: '客户类型',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'customerLevel',
                        title: '客户等级',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'responsibleForPhone',
                        title: '负责经销商',
                        align: 'center',
                        width: 100
                    }, {
                        field: 'userId',
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看详情" onclick="addDevice(\''
                                + row.userId
                                + '\')">添加机器</a> ';
                            return e;
                        }
                    }
                ]
            });
}


function load12() {
    $('#exampleTable12')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/ordermanager/orderList/customerorderlist", // 服务器数据的加载地址
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
                        accountnumberType : 0,
                        prop1 : 1
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
                        field: 'ordercount',
                        title: '订单总数',
                        align: 'center'
                    }, {
                        field: 'ordertotalprice',
                        title: '订单总额',
                        align: 'center'
                    }, {
                        field: 'userId',
                        title: '操作',
                        align: 'center',
                        width: 120,
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="查看详情" onclick="addOrder(\''
                                + row.userId
                                + '\')">添加订单</a> ';
                            return e ;
                        }
                    }
                ]
            });
}


function load13() {
    $('#exampleTable13')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : prefix1 + "/list", // 服务器数据的加载地址
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
                        prop1: $('#userId').val(),
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
                    },{
                        field : 'id',
                        title : '操作',
                        align : 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="修改" onclick="editOrderGoods(\''
                                + row.id
                                + '\')">修改</a> ';
                            return e ;
                        }
                    }]
            });
}



//机器列表
function load1() {

    $('#machineList')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: "/maintain/list", // 服务器数据的加载地址
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
                        prop1 : '0'
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
                        title: '序号',//标题  可不加
                        formatter: function (value, row, index) {
                            return index + 1;
                        },
                        align: 'center'
                    },
                    {
                        field : 'machineType',
                        title : '机器类型',
                        align : 'center',
                        formatter: function (value, row, index) {
                            if (row['machineType'] == "1") {
                                return "新风机"
                            }
                            if (row['machineType'] == "2") {
                                return "饮水机"
                            }
                            if (row['machineType'] == "3") {
                                return "智能灯"
                            }
                            if (row['machineType'] == "4") {
                                return "老饮水机"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'monitorModeCode',
                        title: '机器型号',
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
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm" href="#" title="1档"  mce_href="#" onclick="look0(\''
                                + row.id + ",1"
                                + '\')">报修</a> ';
                            return e;
                        }
                    }]
            });
}

// 报修列表
function load2() {
    $('#repairedList')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/maintain/list", // 服务器数据的加载地址
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
                        userId:$("#userId").val(),
                        prop1 : '1'
                        //userName:$("#title").val()
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
                        field : 'machineType',
                        title : '机器类型',
                        align : 'center',
                        formatter: function (value, row, index) {
                            if (row['machineType'] == "1") {
                                return "新风机"
                            }
                            if (row['machineType'] == "2") {
                                return "饮水机"
                            }
                            if (row['machineType'] == "3") {
                                return "智能灯"
                            }
                            if (row['machineType'] == "4") {
                                return "老饮水机"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'monitorModeCode',
                        title: '机器型号',
                        align: 'center'
                    },
                    {
                        field : 'belongRegion',
                        title : '所属区域',
                        align : 'center'
                    },
                    {
                        field : 'belongFloor',
                        title : '所属楼层',
                        align : 'center'
                    },
                    {
                        field : 'prop2',
                        title : '报修时间',
                        align : 'center'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = "";
                            if(row.prop1 == "1"){
                                e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="派单" onclick="look1(\''
                                    + row.id
                                    + '\')">派单</a> ';
                            }else if(row.prop1 == "2"){
                                e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访" onclick="look2(\''
                                    + row.id
                                    + '\')">回访</a> ';
                            }else{
                                e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="报修" onclick="look0(\''
                                    + row.id
                                    + '\')">报修</a> ';
                            }
                            return e  ;
                        }
                    }
                ]
            }
        );
}

// 维修记录
function load3() {
    $('#wxList')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/maintainManger/repairLog/repairList", // 服务器数据的加载地址
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
                        userId:$("#userId").val()
                        //userName:$("#title").val()
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
                        field : 'prop4',
                        title : '机器类型',
                        align : 'center',
                        formatter: function (value, row, index) {
                            if (row['prop4'] == "1") {
                                return "新风机"
                            }
                            if (row['prop4'] == "2") {
                                return "饮水机"
                            }
                            if (row['prop4'] == "3") {
                                return "智能灯"
                            }
                            if (row['prop4'] == "4") {
                                return "老饮水机"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'monitorModeCode',
                        title: '机器型号',
                        align: 'center'
                    },
                    {
                        field : 'belongRegion',
                        title : '所属区域',
                        align : 'center'
                    },
                    {
                        field : 'belongFloor',
                        title : '所属楼层',
                        align : 'center'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访详情" onclick="look3(\''
                                + row.repairId
                                + '\')">回访详情</a> ';

                            return e  ;
                        }
                    }
                ]
            }
        );
}

// 保养记录
function load4() {
    $('#upKeepList')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/maintainManger/keepLog/list", // 服务器数据的加载地址
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
                        userId:$("#userId").val()
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
                        field : 'machineType',
                        title : '机器类型',
                        align : 'center',
                        formatter: function (value, row, index) {
                            if (row['machineType'] == "1") {
                                return "新风机"
                            }
                            if (row['machineType'] == "2") {
                                return "饮水机"
                            }
                            if (row['machineType'] == "3") {
                                return "智能灯"
                            }
                            if (row['machineType'] == "4") {
                                return "老饮水机"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'monitorModeCode',
                        title: '机器型号',
                        align: 'center'
                    },
                    {
                        field : 'monitorModeCode',
                        title : '机器型号',
                        align : 'center'
                    },
                    {
                        field : 'keepTime',
                        title : '机器保养时间',
                        align : 'center'
                    }
                ]

            });
}
//保养回访情况
function load5() {
    $('#upKeepReturnList')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/maintainManger/keepLog/list", // 服务器数据的加载地址
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
                        userId:$("#userId").val()
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
                        field : 'machineType',
                        title : '机器类型',
                        align : 'center',
                        formatter: function (value, row, index) {
                            if (row['machineType'] == "1") {
                                return "新风机"
                            }
                            if (row['machineType'] == "2") {
                                return "饮水机"
                            }
                            if (row['machineType'] == "3") {
                                return "智能灯"
                            }
                            if (row['machineType'] == "4") {
                                return "老饮水机"
                            }
                            return value;
                        }
                    },
                    {
                        field: 'monitorModeCode',
                        title: '机器型号',
                        align: 'center'
                    },
                    {
                        field : 'createTime',
                        title : '回访时间',
                        align : 'center'
                    },
                    {
                        field : 'monitorCode',
                        title : '机器编号',
                        align : 'center'
                    },
                    {
                        field : 'returnVisit',
                        title : '回访情况',
                        align : 'center'
                    }

                ]

            });
}


function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
};

function addDevice(id) {
    window.location.href = "/accountManger/account/addDevice?userId=" + id;
};

// 报修
function look0(id) {
    window.location.href="/maintain/v1?id="+id;
}

// 派单
function look1(id) {
    window.location.href="/maintainManger/repairLog/v2?id="+id;
}

// 回访
function look2(id) {
    window.location.href="/maintainManger/repairLog/v3?id="+id;
}

// 回访详情
function look3(id) {
    window.location.href="/maintainManger/repairLog/repairDetail?id="+id;
}


function addOrder(id) {
    window.location.href = "/ordermanager/orderList/add?userId=" + id;
}

// 修改订单商品信息
function editOrderGoods(id) {
    window.location.href = "/ordermanager/orderGoods/edit/" + id;
}