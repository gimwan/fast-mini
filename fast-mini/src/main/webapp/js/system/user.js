let user = [];
let userVm;

common.bindVue = function() {
    userVm = new Vue({
        el : ".userPage",
        data : {
            user: user
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    console.log(user[index].id);
                    showEditBox(index,user[index]);
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
                        api.load('./user/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	user.splice(deleteIndex);
                                
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
            	if (jsonDate == null || jsonDate == undefined || $.trim(jsonDate) == "") {
					return '';
				}
				var year = jsonDate.year + 1900;
				var month = jsonDate.month + 1;
				var day = jsonDate.date;
				// 如果得到的数字小于9要在前面加'0'
				day = (day > 9) ? ("" + day) : ("0" + day);
				month = (month > 9) ? ("" + month) : ("0" + month);
				var hour = jsonDate.hours;
				var minute = jsonDate.minutes;
				var seconds = jsonDate.seconds;
				hour = (hour > 9) ? ("" + hour) : ("0" + hour);
				minute = (minute > 9) ? ("" + minute) : ("0" + minute);
				seconds = (seconds > 9) ? ("" + seconds) : ("0" + seconds);
				return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds;
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'user/user','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    user.push(data[i]);
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
            api.load('./user/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		user.push(data);
					} else {
						for (const key in data) {
	                        if (user[idx].hasOwnProperty(key)) {
	                        	user[idx][key] = data[key];
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
    let id = "";
    let code = "";
    let name = "";
    let password = "";
    let sex = 1;
    let mobilephone = "";
    let photourl = "";
    let useflag = 1;
    let memo = "";
    if (data != null && data != undefined && data != "") {
        id = data.id;
        code = data.code;
        name = data.name;
        mobilephone = data.mobilephone;
        password = data.password;
        sex = data.sex;
        photourl = data.photourl;
        useflag = data.useflag;
        memo = data.memo;
    }
	let element = "<div class=\"edit-view\">"+
				    "<div class=\"edit-box\">"+
				        "<div class=\"edit-item\" need=\"0\" key=\"1\" hidden>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"id\">"+
				                "<input type=\"text\" value=\""+id+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" image=\"1\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">头像</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"photourl\">"+
				                "<img src=\""+photourl+"\" class=\"layui-circle-img value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">编号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"code\">"+
				                "<input type=\"text\" value=\""+code+"\" class=\"layui-input value focus\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"name\">"+
				                "<input type=\"text\" value=\""+name+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">手机号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"mobilephone\">"+
				                "<input type=\"text\" value=\""+mobilephone+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">密码</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"password\">"+
				                "<input type=\"password\" value=\""+password+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">性别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"sex\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"sex\" value=\"1\" title=\"男\" "+(sex==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"sex\" value=\"2\" title=\"女\" "+(sex!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">是否使用</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"useflag\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"useflag\" value=\"1\" title=\"是\" "+(useflag==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"useflag\" value=\"0\" title=\"否\" "+(useflag!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" textarea=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">备注</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"memo\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<textarea name=\"memo\" class=\"layui-textarea value\">"+memo+"</textarea>" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				    "</div>"+
				"</div>";
	return element;
}
