let vip = [];
let vipVm;

common.bindVue = function() {
    vipVm = new Vue({
        el : ".vipPage",
        data : {
            vip: vip
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,vip[index]);
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
    api.load(basePath + 'data/list','post',{"table":"vip"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"vip","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.records;
	vip.length = 0;
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	vip.push(data[i]);
        }
    }
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
        btn: ['关闭'],
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
		sex : 0,
		mobilephone : "",
		photourl : "",
		birthday : "",
		province : "",
		provinceid : "",
		city : "",
		cityid : "",
		county : "",
		countyid : "",
		department : "",
		departmentid : "",
		type : "",
		typeid : "",
		recommender : "",
		recommenderid : "",
		source : "",
		registtime : "",
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
				        "<div class=\"edit-item\" image=\"1\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">头像</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"photourl\">"+
				                "<img src=\""+d.photourl+"\" onerror=\"defaultImg(this)\" class=\"layui-circle-img circular value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">编号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"code\">"+
				                "<input type=\"text\" value=\""+d.code+"\" class=\"layui-input value focus\" disabled=\"disabled\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"name\">"+
				                "<input type=\"text\" value=\""+d.name+"\" class=\"layui-input value\" disabled=\"disabled\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">手机号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"mobilephone\">"+
				                "<input type=\"text\" value=\""+d.mobilephone+"\" class=\"layui-input value\" disabled=\"disabled\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">所属门店</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"departmentid\">"+
				                "<input type=\"text\" data-id=\""+d.departmentid+"\" value=\""+d.department+"\" " +
				                		"data-url=\"./data/page?table=department\" class=\"layui-input value\" readonly=\"readonly\" disabled=\"disabled\"/>" +
				                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">类别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"typeid\">"+
				                "<input type=\"text\" data-id=\""+d.typeid+"\" value=\""+d.type+"\" " +
				                		"data-url=\"./data/page?table=viptype\" class=\"layui-input value\" readonly=\"readonly\" disabled=\"disabled\"/>" +
				                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" select=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">性别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"sex\">"+
				                "<form class=\"layui-form\" action=\"\">" +
				                	"<div class=\"layui-form-item\">" +
				                		"<div class=\"selectItem\">" +
				                			"<div class=\"layui-input-inline selectBox\">" +
				                				"<select>" +
				                					"<option value=\"0\" "+(d.sex == 0 ? 'selected' : '')+">未知</option>" +
				                					"<option value=\"1\" "+(d.sex == 1 ? 'selected' : '')+">男</option>" +
				                					"<option value=\"2\" "+(d.sex == 2 ? 'selected' : '')+">女</option>" +
				                				"</select>" +
				                				"<div class=\"layui-unselect layui-form-select\">" +
				                					"<div class=\"layui-select-title\">" +
				                						"<input type=\"text\" placeholder=\"请选择\" value=\""+d.sex+"\" class=\"layui-input layui-unselect value\"/> " +
				                						"<i class=\"layui-edge\"></i>" +
				                					"</div>" +
				                					"<dl class=\"layui-anim layui-anim-upbit\">" +
					                					"<dd lay-value=\"0\" class=\"\">未知</dd>" +
					                					"<dd lay-value=\"1\" class=\"\">男</dd>" +
					                					"<dd lay-value=\"2\" class=\"\">女</dd>" +
				                					"</dl>" +
				                				"</div>" +
				                			"</div>" +
				                		"</div>"+
				                	"</div>"+
				                "</form>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">生日</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"birthday\">"+
				                "<input type=\"text\" value=\""+common.formatDay(d.birthday)+"\" class=\"layui-input value\" disabled=\"disabled\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">注册时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"registtime\">"+
				                "<input type=\"text\" value=\""+common.formatDate(d.registtime)+"\" class=\"layui-input value\" disabled=\"disabled\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" textarea=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">备注</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"memo\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<textarea name=\"memo\" class=\"layui-textarea value\" disabled=\"disabled\">"+d.memo+"</textarea>" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				    "</div>"+
				"</div>";
	return element;
}
