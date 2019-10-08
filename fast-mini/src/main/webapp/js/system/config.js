let config = [];
let configVm;

common.bindVue = function() {
	console.log('config');
    configVm = new Vue({
        el : ".config-data",
        data : {
            config: config
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index);
                }
            },
            formatDate: function(jsonDate) {
            	let date = common.formatDate(jsonDate);
				return date;
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'config/config','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    config.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}

function showEditBox(idx) {
    let editDiv = "<div class=\"edit-view\">"+
                    "<div class=\"edit-box\">"+
                        "<div class=\"edit-item\" need=\"0\" key=\"1\" hidden>"+
                            "<div class=\"edit-title\">"+
                                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
                            "</div>"+
                            "<div class=\"edit-value\" data-field=\"id\">"+
                                "<input type=\"text\" value=\""+config[idx].id+"\" class=\"layui-input value\"/>"+
                            "</div>"+
                        "</div>";
    let height = '230px';
    if (config[idx].type == 1) {
    	editDiv += "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				        "<div class=\"edit-title\">"+
					        "<span class=\"title\"><label class=\"name\">"+config[idx].name+"</label>：</span>"+
					    "</div>"+
					    "<div class=\"edit-value\" data-field=\"value\">"+
					        "<input "+((config[idx].code=='4001' || config[idx].code=='7002')?'type=\"number\"':'type=\"text\"')+" value=\""+config[idx].value+"\" class=\"layui-input value focus\"/>"+
					    "</div>"+
					"</div>";
	} else if (config[idx].type == 2) {
		editDiv += "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				        "<div class=\"edit-title\">"+
					        "<span class=\"title\"><label class=\"name\">"+config[idx].name+"</label>：</span>"+
					    "</div>"+
					    "<div class=\"edit-value\" data-field=\"value\">"+
					        "<input type=\"text\" value=\""+config[idx].value+"\" class=\"layui-input value focus\" disabled/>"+
					    "</div>"+
					"</div>" +
					"<div class=\"edit-item\">"+
					    "<div class=\"colorpicker\" id=\"colorbox\">"+
					    "</div>"+
					"</div>";
		height = "330px";
    } else if (config[idx].type == 3) {
    	editDiv += "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				        "<div class=\"edit-title\">"+
					        "<span class=\"title\"><label class=\"name\">"+config[idx].name+"</label>：</span>"+
					    "</div>"+
					    "<div class=\"edit-value\" data-field=\"value\">"+
					        "<input type=\"text\" data-id=\""+config[idx].value+"\" value=\""+(config[idx].code=='6001'?config[idx].department:config[idx].value)+"\" " +
					        		"data-url=\"./data/page?table=department\" class=\"layui-input value\" readonly=\"readonly\"/>" +
					        "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					    "</div>"+
					"</div>";
	} else if (config[idx].type == 4) {
		editDiv += "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
						"<div class=\"edit-title\">"+
					        "<span class=\"title\"><label class=\"name\">"+config[idx].name+"</label>：</span>"+
					    "</div>"+
				        "<div class=\"edit-value layui-form-item\" data-field=\"value\">"+
				            "<div class=\"layui-input-block\">" +
				            	"<input type=\"radio\" name=\"useflag\" value=\"1\" title=\"是\" "+(config[idx].value==1?'checked':'')+" class=\"layui-input value\">" +
				            	"<input type=\"radio\" name=\"useflag\" value=\"0\" title=\"否\" "+(config[idx].value!=1?'checked':'')+" class=\"layui-input value\">" +
				            "</div>" +
				        "</div>"+
				    "</div>";
	} else if (config[idx].type == 5) {
		editDiv += "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				        "<div class=\"edit-title\">"+
					        "<span class=\"title\"><label class=\"name\">"+config[idx].name+"</label>：</span>"+
					    "</div>"+
					    "<div class=\"edit-value\" data-field=\"value\">"+
					        "<input type=\"text\" value=\""+config[idx].value+"\" class=\"layui-input value focus\" disabled/>"+
					    "</div>"+
					"</div>" +
					"<div class=\"edit-item\">"+
					    "<img src=\""+config[idx].value+"\" onerror=\"defaultImg(this)\" class=\"imagepicker\" id=\"imagebox\">"+
					"</div>";
		height = "330px";
	}
    
    editDiv += "</div></div>";
    
    layer.open({
        type: 1,
        title: "<label style='font-weight:600;'>修改</label>",
        content: editDiv,
        area: ['600px', height],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }

            common.showLoading();
            api.load('./config/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                    for (const key in data) {
                    	config[idx][key] = data[key];
                    }
                    layer.close(index);
                    common.tips(result.message);
                } else {
                    common.error(result.message);
                }
                common.closeLoading();
            });
        },
        success: function () {
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
            
            if (config[idx].type == 2) {
            	layColorpicker.render({
            		elem: '#colorbox',
            		color: config[idx].value,
            		done: function (color) {
            			$(".edit-view .focus").val(color);
            	    }
            	});
			} else if (config[idx].type == 4) {
				layuiForm.render();
			} else if (config[idx].type == 5) {
				layuiUpload.render({
				    elem: '#imagebox',
				    url: './upload/field/config',
				    accept: "images",
				    acceptMime: "image/*",
				    size: 100,
				    multiple: false,
				    done: function(res, index, upload){
				    	// 上传完毕回调
				    	var item = this.item;
				    	$(item).attr("src",res.data);
				    	$(".edit-view .focus").val(res.data);
				    },
				    error: function(res, index){
				    	// 请求异常回调
				    	console.log(res);
				    	console.log(index);
				    	configAssembly();
				    }
				});
			}
        }
        
    });
}
