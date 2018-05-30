function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/cluemanger/manager/update",
		data : $('#editClueForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
            if (data.code == 0) {
                layer.msg("操作成功");
                window.location.href = "/cluemanger/manager";
            } else {
                layer.msg(data.msg);
            }

		}
	});

};
