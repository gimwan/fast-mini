let viptype = [];
let viptypeVm;

common.bindVue = function() {
    viptypeVm = new Vue({
        el : ".viptypePage",
        data : {
            viptype: viptype
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,viptype[index]);
                }
            },
            add: function () {
                showEditBox(-1, null);
            },
            del: function () {
            	var id = $(".layui-table-view .layui-table-box .layui-table-body table .selected").data("id");
            	var deleteIndex = $(".layui-table-view .layui-table-box .layui-table-body table .selected").data("index");
            	if (id == null || id == undefined || $.trim(id) == "") {
            		common.warn("请先选择要删除项");
                    return false;
				}
				var data = {};
				data['id'] = id;
            	common.showLoading();
                api.load('./viptype/delete','post',data, function(result) {
                    if (result.errcode == 0) {
                    	viptype.splice(deleteIndex);
                        
                        common.tips(result.message);
                    } else {
                        common.error(result.message);
                    }
                    common.closeLoading();
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
    api.load(basePath + 'viptype/viptype','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    viptype.push(data[i]);
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
            api.load('./viptype/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		viptype.push(data);
					} else {
						for (const key in data) {
	                        if (viptype[idx].hasOwnProperty(key)) {
	                        	viptype[idx][key] = data[key];
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
    let grade = 1;
    let defaultflag = 0;
    let useflag = 1;
    let memo = "";
    if (data != null && data != undefined && data != "") {
        id = data.id;
        code = data.code;
        name = data.name;
        grade = data.grade;
        defaultflag = data.defaultflag;
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
				                "<span class=\"title\"><label class=\"name\">级别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"grade\">"+
				                "<input type=\"text\" value=\""+grade+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">是否默认等级</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"defaultflag\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"defaultflag\" value=\"1\" title=\"是\" "+(defaultflag==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"defaultflag\" value=\"0\" title=\"否\" "+(defaultflag!=1?'checked':'')+" class=\"layui-input value\">" +
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

function catchBoxValue() {
    let data = {};
    let error = false;
    $(".edit-view .edit-box .edit-item").each(function() {
        let need = $(this).attr("need");
        let title = $(this).find(".name").html();
        let field = $(this).find(".edit-value").data("field");
        let value = "";
        let isImage = $(this).attr("image");
        let isRadio = $(this).attr("radio");
        
        value = $(this).find(".value").val();
        if (isRadio == "1") {
        	value = $(this).find('input[type="radio"]:checked').val();
		}
        if (isImage == "1") {
        	value = $(this).find('img').attr("src");
        }
        
        if (value == null || value == undefined || $.trim(value) == "") {
        	value = "";
        }

        let errorMsg;
        if (need == 1) {
            if (value == null || value == undefined || $.trim(value) == "") {
                errorMsg = title + "不能为空";
            }
        }
        if (errorMsg != null && errorMsg != undefined && $.trim(errorMsg) != "") {
            error = true;
            common.warn(errorMsg);
            return false;
        }
        data[field] = value;
    });
    if (error) {
        data = '';
    }
    return data;
}