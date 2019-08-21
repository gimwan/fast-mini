let extsystem = [];
let extsystemVm;

common.bindVue = function() {
    extsystemVm = new Vue({
        el : ".extsystemPage",
        data : {
            extsystem: extsystem
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,extsystem[index]);
                }
            },
            add: function () {
                showEditBox(-1, null);
            },
            del: function () {
            	let id = $(".layui-table-view .layui-table-box .layui-table-body table .selected").data("id");
            	let deleteIndex = $(".layui-table-view .layui-table-box .layui-table-body table .selected").data("index");
            	if (id == null || id == undefined || $.trim(id) == "") {
            		common.warn("请选择删除项");
                    return false;
				}
            	layer.confirm('确定删除？', {
            		btn: ['确定','取消'],
            		btn1 : function(index, layero) {
        				var data = {};
        				data['id'] = id;
                    	common.showLoading();
                        api.load('./extsystem/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	extsystem.splice(deleteIndex);
                                
                                common.tips(result.message);
                            } else {
                                common.error(result.message);
                            }
                            common.closeLoading();
                        });
            			
            		}
            	});
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
    api.load(basePath + 'data/list','post',{"table":"extsystem"},function (result) {
        if (result.errcode == 0) {
        	let pageView = result.data;
        	setData(pageView);
            pageConfig(pageView, function(pageno) {
            	loadPageData(pageno);
			});
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}

function loadPageData(pageno) {
	common.showLoading();
	api.load(basePath + 'data/list','post',{"table":"extsystem","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	extsystem.splice(0, extsystem.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	extsystem.push(data[i]);
        }
    }
}

function createElement(data) {
	let d = {
		id : "",
		code : "",
		name : "",
		version : "",
		serveraddress : "",
		token : "",
		appid : "",
		secret : "",
		active : 0,
		useflag : 1,
		memo : ""
	};
    if (data != null && data != undefined && data != "") {
    	for (const key in data) {
            if (d.hasOwnProperty(key)) {
            	d[key] = data[key];
            }
        }
    }
	let element = "<div class=\"edit-view\">"+
				    "<div class=\"edit-box\">"+
				        "<div class=\"edit-item\" need=\"0\" key=\"1\" hidden>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"id\">"+
				                "<input type=\"text\" value=\""+d.id+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">编号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"code\">"+
				                "<input type=\"text\" value=\""+d.code+"\" class=\"layui-input value focus\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"name\">"+
				                "<input type=\"text\" value=\""+d.name+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">服务地址</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"serveraddress\">"+
				                "<input type=\"text\" value=\""+d.serveraddress+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">调用凭据</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"token\">"+
				                "<input type=\"text\" value=\""+d.token+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">用户凭证</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"appid\">"+
				                "<input type=\"text\" value=\""+d.appid+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">凭证密钥</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"secret\">"+
				                "<input type=\"text\" value=\""+d.secret+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">版本号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"version\">"+
				                "<input type=\"text\" value=\""+d.version+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">是否启用</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"active\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"active\" value=\"1\" title=\"是\" "+(d.active==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"active\" value=\"0\" title=\"否\" "+(d.active!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">是否使用</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"useflag\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"useflag\" value=\"1\" title=\"是\" "+(d.useflag==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"useflag\" value=\"0\" title=\"否\" "+(d.useflag!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" textarea=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">备注</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"memo\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<textarea name=\"memo\" class=\"layui-textarea value\">"+d.memo+"</textarea>" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				    "</div>"+
				"</div>";
	return element;
}

function showEditBox(idx,data) {
	let editDiv = createElement(data);
	
	let boxTitle = "<label style='font-weight:600;'>修改</label>";
	if (idx < 0) {
		boxTitle = "<label style='font-weight:600;'>新增</label>";
	}
    
    layer.open({
        type: 1,
        title: boxTitle,
        content: editDiv,
        area: ['800px', '600px'],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./extsystem/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		extsystem.push(data);
					} else {
						for (const key in data) {
	                        extsystem[idx][key] = data[key];
	                    }
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
        	// 重新刷新form
        	layuiForm.render();
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
        }
        
    });
}
