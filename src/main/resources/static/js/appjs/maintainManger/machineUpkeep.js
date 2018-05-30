
$(function() {
    load();
    if($("#machineType").val()==1 || $("#machineType").val()==4){
        $('#exampleTable').bootstrapTable('hideColumn', 'flow1');
        $('#exampleTable').bootstrapTable('hideColumn', 'flow2');
        $('#exampleTable').bootstrapTable('hideColumn', 'flow3');
        $('#exampleTable').bootstrapTable('hideColumn', 'flow4');
        $('#exampleTable').bootstrapTable('hideColumn', 'flow5');
        $('#exampleTable').bootstrapTable('hideColumn', 'userId');
        $('#exampleTable').bootstrapTable('showColumn', 'prop6');
    }else{
        $('#exampleTable').bootstrapTable('showColumn', 'flow1');
        $('#exampleTable').bootstrapTable('showColumn', 'flow2');
        $('#exampleTable').bootstrapTable('showColumn', 'flow3');
        $('#exampleTable').bootstrapTable('showColumn', 'flow4');
        $('#exampleTable').bootstrapTable('showColumn', 'flow5');
        $('#exampleTable').bootstrapTable('hideColumn', 'prop6');
        $('#exampleTable').bootstrapTable('showColumn', 'userId');
    }

});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method : 'get', // 服务器数据的请求方式 get or post
                url : "/maintain/keepList", // 服务器数据的加载地址
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
                //发送到服务器的数据编码类型
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
                        machineType:$("#machineType").val(),
                        userId:$("#userId").val()
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
                        field : 'id',
                        title : 'ID',
                        align : 'center'
                    },
                    {
                        field : 'userName',
                        title : '用户',
                        align : 'center'
                    },
                    {
                        field : 'monitorCode',
                        title : '机器编号',
                        align : 'center'
                    },
                    {
                        field : 'monitorModeCode',
                        title : '机器型号',
                        align : 'center'
                    },
                    {
                        field : 'belongRegion',
                        title : '机器位置',
                        align : 'center'
                    },
                    {
                        field : 'belongFloor',
                        title : '所属楼层',
                        align : 'center'
                    },
                    {
                        field : 'flow1',
                        title : '一级滤芯',
                        align : 'center'
                    },
                    {
                        field : 'flow2',
                        title : '二级滤芯',
                        align : 'center'
                    },
                    {
                        field : 'flow3',
                        title : '三级滤芯',
                        align : 'center'
                    },
                    {
                        field : 'flow4',
                        title : '四级滤芯',
                        align : 'center'
                    },
                    {
                        field : 'flow5',
                        title : '五级滤芯',
                        align : 'center'
                    },
                    {
                        field : 'prop6',
                        title : '到期维护时间',
                        align : 'center'
                    },
                    {
                        title : '操作',
                        field : 'userId',
                        align : 'center',
                        formatter : function(value, row, index) {
                            if($("#machineType").val()==2){
                                var e = '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访" onclick="look(\''
                                    + row.monitorCode+'\',\''+1
                                    + '\')">一级恢复</a> ';
                                e += '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访" onclick="look(\''
                                    + row.monitorCode+'\',\''+2
                                    + '\')">二级恢复</a> ';
                                e += '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访" onclick="look(\''
                                    + row.monitorCode+'\',\''+3
                                    + '\')">三级恢复</a> ';
                                e += '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访" onclick="look(\''
                                    + row.monitorCode+'\',\''+4
                                    + '\')">四级恢复</a> ';
                                e += '<a class="btn btn-primary btn-sm " href="#" mce_href="#" title="回访" onclick="look(\''
                                    + row.monitorCode+'\',\''+5
                                    + '\')">五级恢复</a> ';
                                return e  ;
                            }

                        }
                    }
                ]

            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}

function look(monitorCode,type) {
    $.ajax({
        url : "/monitoring/monitoring/reset",
        type : "post",
        data : {
            'monitorCode' : monitorCode,
            'type':type
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
}
function look1() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
    if (rows.length == 0) {
        layer.msg("请选择要保养的机器");
        return;
    }
    var ids = new Array();
    // 遍历所有选择的行数据，取每条数据对应的ID
    $.each(rows, function(i, row) {
        ids[i] = row['id'];
    });
    var machineType =$("#machineType").val();
    var userId = $("#userId").val();
    window.location.href="/maintain/upkeepDetail1?userId="+userId+"&machineType="+machineType+"&ids="+ids;
}


