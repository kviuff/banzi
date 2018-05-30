
$(function() {


});

function save(){
    $.ajax({
        type : "POST",
        dataType:"JSON",
        url : "/maintainManger/repairLog/save",
        data : $('#logForm').serialize(),// 你的formid
        success : function(data) {
            console.log(data);
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                //window.location.href="/accountManger/account/";
                window.location.href=document.referrer;
            } else {
                parent.layer.alert(data.msg)
            }

        }
    });
}