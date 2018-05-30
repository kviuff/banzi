$().ready(function() {


    var price = $(".goodsPrice").val();
    var goodsNum = $(".goodsNum").val();
    $("#totalAmount").text(" 总金额  $" + number_format(price*goodsNum, 2));

    $(".goodsNum").on("input propertychange",function(){
        var price = $(".goodsPrice").val();
        var goodsNum = $(".goodsNum").val();
        $("#totalAmount").text(" 总金额  $" + number_format(price*goodsNum, 2));
    })
});

function updateGoods() {
    $.ajax({
        cache : true,
        type : "POST",
        url : "/ordermanager/orderGoods/update",
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