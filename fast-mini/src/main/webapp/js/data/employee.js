let employee = [];
let employeeVm;

common.bindVue = function() {
    employeeVm = new Vue({
        el : ".employeePage",
        data : {
            employee: employee
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,employee[index]);
                }
            },
            add: function () {
                showEditBox(-1, null);
            },
            del: function () {
            	layer.confirm('确定删除？', {
            		btn: ['确定','取消'],
            		btn1 : function(index, layero) {
            			let id = $(".layui-table-view .layui-table-box .layui-table-body table .selected").data("id");
            			let deleteIndex = $(".layui-table-view .layui-table-box .layui-table-body table .selected").data("index");
                    	if (id == null || id == undefined || $.trim(id) == "") {
                    		common.warn("请先选择要删除项");
                            return false;
        				}
        				var data = {};
        				data['id'] = id;
                    	common.showLoading();
                        api.load('./employee/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	employee.splice(deleteIndex);
                                
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
    api.load(basePath + 'data/list','post',{"table":"employee"},function (result) {
        if (result.errcode == 0) {
            let data = result.data.records;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    employee.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
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
        area: ['700px', '710px'],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./employee/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		employee.push(data);
					} else {
						for (const key in data) {
	                        if (employee[idx].hasOwnProperty(key)) {
	                        	employee[idx][key] = data[key];
	                        }
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

function createElement(data) {
	let d = {
		id : "",
	    code : "",
	    name : "",
	    sex : 1,
	    mobilephone : "",
	    departmentid : "",
	    department : "",
	    photourl : "",
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
				                "<input type=\"text\" value=\""+d.d+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" image=\"1\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">头像</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"photourl\">"+
				                "<img src=\""+d.photourl+"\" class=\"layui-circle-img value\"/>"+
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
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">所属门店</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"departmentid\">"+
				                "<input type=\"text\" data-id=\""+d.departmentid+"\" value=\""+d.department+"\" " +
				                		"data-url=\"./data/page?table=department\" class=\"layui-input value\" readonly=\"readonly\"/>" +
				                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">手机号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"mobilephone\">"+
				                "<input type=\"text\" value=\""+d.mobilephone+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">性别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"sex\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"sex\" value=\"1\" title=\"男\" "+(d.sex==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"sex\" value=\"2\" title=\"女\" "+(d.sex!=1?'checked':'')+" class=\"layui-input value\">" +
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
