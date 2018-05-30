
$(function() {
    $("#provinceId").change(function(){
        queryCity()
    });
    $("#cityId").change(function(){
        queryCity1()
    });


});

function queryCity(){
	console.log("1111");
    $.ajax({
        cache : true,
        type : "POST",
        url : "/system/area/getChildArea",
        data :{"id":$("#provinceId").val()},
        async : false,
        success : function(data) {
        	console.log(data);
            if (data.success == "true") {
                $("#cityId").empty();
                if(data.result.length > 0){
                    var cityId =data.result[0].areaId;
                    for(var a = 0;a<data.result.length;a++){
						var op = '<option value="'+data.result[a].areaId+'">'+data.result[a].areaName+'</option>'
                        $("#cityId").append(op);
                    }

                    $.ajax({
                        cache: true,
                        type: "POST",
                        url: "/system/area/getChildArea",
                        data: {"id": cityId},
                        async: false,
                        success: function (c) {
                            if (c.success == "true") {
                                $("#countyId").empty();
                                if (c.result.length > 0) {
                                    for (var a = 0; a < c.result.length; a++) {
                                        var op = '<option value="' + c.result[a].areaId + '">' + c.result[a].areaName + '</option>'
                                        $("#countyId").append(op);
                                    }
                                }else{
                                    var op = '<option value="">请选择县/区</option>';
                                    $("#countyId").append(op);
								}
                            }else{
                                parent.layer.msg("查询失败");
							}
                        }
                    })
				}else{
                    var op = '<option value="">请选择市</option>';
                    $("#cityId").append(op);
				}

            } else {
                parent.layer.msg("查询失败");
            }

        }
    });
}
function queryCity1(){
	$.ajax({
		cache: true,
		type: "POST",
		url: "/system/area/getChildArea",
		data: {"id": $("#cityId").val()},
		async: false,
		success: function (c) {
		    console.log(c);
			if (c.success == "true") {
				$("#countyId").empty();
				if (c.result.length > 0) {
					for (var a = 0; a < c.result.length; a++) {
						var op = '<option value="' + c.result[a].areaId + '">' + c.result[a].areaName + '</option>'
						$("#countyId").append(op);
					}
				}else{
					var op = '<option value="">请选择县/区</option>';
					$("#countyId").append(op);
				}
			}else{
				parent.layer.msg("查询失败");
			}
		}
	})
}
