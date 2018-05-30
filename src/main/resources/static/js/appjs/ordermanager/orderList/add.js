$().ready(function() {
    $("#add").on('click', function () {
        var _html = '<div class="a-data">'
            + '<div class="layui-row">'
            + '<div class="layui-form-item float-l">'
            + '<label class="layui-form-label">产品名称</label>'
            + '<div class="layui-input-block">'
            + '<input type="text" name="goodsName" required lay-verify="required" placeholder="" autocomplete="off" class="layui-input">'
            + '</div>'
            + '</div>'
            + '<div class="layui-form-item float-l">'
            + '<label class="layui-form-label">产品型号</label>'
            + '<div class="layui-input-block">'
            + '<input type="text" name="goosSn" required lay-verify="required" placeholder="" autocomplete="off" class="layui-input">'
            + '</div>'
            + '</div>'
            + '<div class="layui-form-item float-l">'
            + '<label class="layui-form-label">产品单价</label>'
            + '<div class="layui-input-block">'
            + '<input type="text" name="goodsPrice" required lay-verify="required" placeholder="" autocomplete="off" class="layui-input goodsPrice">'
            + '</div>'
            + '</div>'
            + '</div>'
            + '<div class="layui-row">'
            + '<div class="layui-form-item float-l">'
            + '<label class="layui-form-label">销售数量</label>'
            + '<div class="layui-input-block">'
            + '<input type="text" name="goodsNum" id="goodsNum" required lay-verify="required" placeholder="" autocomplete="off" onblur="priceFun();" class="layui-input goodsNum">'
            + '</div>'
            + '</div>'
            + '<div class="layui-form-item float-l">'
            + '<label class="layui-form-label">品牌</label>'
            + '<div class="layui-input-block">'
            + '<input type="text" name="brand" required lay-verify="required" placeholder="" autocomplete="off" class="layui-input">'
            + '</div>'
            + '</div>'
            + '<div class="layui-form-item float-l">'
            + '<label class="layui-form-label">备注</label>'
            + '<div class="layui-input-block">'
            + '<input type="text" name="prop2" required lay-verify="required" placeholder="" autocomplete="off" class="layui-input">'
            + '</div>'
            + '</div>'
            + '<div class="layui-form-item">'
            + '<button class="layui-btn  layui-btn-sm" type="button" id="del" onclick="delData(event)">删除</button> '
            + '</div>'
            + '</div>'
            + '</div>'
        $(".layui-form").append(_html);
    });


    $(".goodsNum").on("input propertychange",function(){

        priceFun();

        //var price = $("#goodsPrice").val();
        //var goodsNum = $("#goodsNum").val();
        //$("#totalAmount").text(" 总金额  $" + Math.round(price * goodsNum*100)/100);
    })

});

function priceFun() {
    var totalprice = 0;
    $("div.a-data").each(function(){
        var price = $(this).find(".goodsPrice").val();
        var goodsNum = $(this).find(".goodsNum").val();
        totalprice = totalprice + Math.round(price * goodsNum*100)/100;
    });
    $("#totalAmount").text(" 总金额  $" + number_format(totalprice, 2));
};


function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/ordermanager/orderGoods/save",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				window.location.href = document.referrer;
				/*var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);*/

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});
};

function delData(e) {
    $(e.target).parent().parent().parent().remove();
    return false;
};


function number_format(num, ext){
    if(ext < 0){
        return num;
    }
    num = Number(num);
    if(isNaN(num)){
        num = 0;
    }
    var _str = num.toString();
    var _arr = _str.split('.');
    var _int = _arr[0];
    var _flt = _arr[1];
    if(_str.indexOf('.') == -1){
        /* 找不到小数点，则添加 */
        if(ext == 0){
            return _str;
        }
        var _tmp = '';
        for(var i = 0; i < ext; i++){
            _tmp += '0';
        }
        _str = _str + '.' + _tmp;
    }else{
        if(_flt.length == ext){
            return _str;
        }
        /* 找得到小数点，则截取 */
        if(_flt.length > ext){
            _str = _str.substr(0, _str.length - (_flt.length - ext));
            if(ext == 0){
                _str = _int;
            }
        }else{
            for(var i = 0; i < ext - _flt.length; i++){
                _str += '0';
            }
        }
    }

    return _str;
};