
$(function() {
    load();
    if($("#prop1").val() == "1"){
        $('#exampleTable').bootstrapTable('hideColumn', 'prop3');
        $('#exampleTable').bootstrapTable('showColumn', 'prop2');
    }else if($("#prop1").val() == "2"){
        $('#exampleTable').bootstrapTable('hideColumn', 'prop2');
        $('#exampleTable').bootstrapTable('showColumn', 'prop3');
    }else{
        $('#exampleTable').bootstrapTable('hideColumn', 'prop3');
        $('#exampleTable').bootstrapTable('hideColumn', 'prop2');
    }
});

function load() {
    $('#exampleTable')
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
               // search : true, // 是否显示搜索框
               // showColumns : true, // 是否显示内容下拉框（选择显示的列）
                sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams : function(params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset:params.offset,
                        userId:$("#userId").val(),
                        prop1 : $("#prop1").val(),
                        machineType:$("#selectList").val()
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
                        field : 'id',
                        title : 'ID',
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
                                return "新风机"
                            }
                            return value;
                        }
                    },
                    {
                        field : 'monitorModeCode',
                        title : '机器型号',
                        align : 'center'
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
                        field : 'prop3',
                        title : '派单时间',
                        align : 'center'
                    },
                    {
                        title : '操作',
                        field : 'id',
                        align : 'center',
                        formatter : function(value, row, index) {
                            var e = "";
                            if($("#prop1").val() == "1"){
                                e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="派单" onclick="look1(\''
                                    + row.id
                                    + '\')">派单</a> ';
                            }else if($("#prop1").val() == "2"){
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
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function look0(id) {
    window.location.href="/maintain/v1?id="+id;
}

function look1(id) {
    window.location.href="/maintainManger/repairLog/v2?id="+id;
}
function look2(id) {
    window.location.href="/maintainManger/repairLog/v3?id="+id;
}

