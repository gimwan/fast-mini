let viptype = [];
let viptypeVm;
let btnVm;

common.bindVue = function() {
	refreshConfig("7001");
	
	btnVm = new Vue({
		el : ".operating",
		data : {
			config: config
        },
        methods : {
            synchronize: function() {
            	synchronize('viptypelist',loadData);
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
                        api.load('./viptype/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	viptype.splice(deleteIndex);
                                
                                common.tips(result.message);
                            } else {
                                common.error(result.message);
                            }
                            common.closeLoading();
                        });
            		}
            	});
            }
        }
	});
	
    viptypeVm = new Vue({
        el : ".viptype-data",
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
    api.load(basePath + 'data/list','post',{"table":"viptype"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"viptype","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	viptype.splice(0, viptype.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	viptype.push(data[i]);
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
        area: ['700px', '720px'],
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
                	// 默认会员等级只能有一个
                	let isDefault = data.defaultflag;
                	if (isDefault == 1) {
						for (var i = 0; i < viptype.length; i++) {
							viptype[i].defaultflag = 0;
						}
					}
                	if (idx < 0) {
                		viptype.push(data);
					} else {
						for (const key in data) {
	                        viptype[idx][key] = data[key];
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
	let sync = 0;
	if (config != null && config != undefined && config.value == 1) {
		sync = 1;
	}
	
    let d = {
		id : "",
	    code : "",
	    name : "",
	    grade : 1,
	    pointrate : 0,
	    discount : 1,
	    birthdaydiscount : 1,
	    defaultflag : 0,
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
				                "<input type=\"text\" value=\""+d.code+"\" class=\"layui-input value focus\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"name\">"+
				                "<input type=\"text\" value=\""+d.name+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" select=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">级别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"grade\">"+
				                "<form class=\"layui-form\" action=\"\">" +
				                	"<div class=\"layui-form-item\">" +
				                		"<div class=\"selectItem\">" +
				                			"<div class=\"layui-input-inline selectBox\">" +
				                				"<select "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+">" +
				                					"<option value=\"1\" "+(d.grade == 1 ? 'selected' : '')+">1</option>" +
				                					"<option value=\"2\" "+(d.grade == 2 ? 'selected' : '')+">2</option>" +
				                					"<option value=\"3\" "+(d.grade == 3 ? 'selected' : '')+">3</option>" +
				                					"<option value=\"4\" "+(d.grade == 4 ? 'selected' : '')+">4</option>" +
				                					"<option value=\"5\" "+(d.grade == 5 ? 'selected' : '')+">5</option>" +
				                					"<option value=\"6\" "+(d.grade == 6 ? 'selected' : '')+">6</option>" +
				                					"<option value=\"7\" "+(d.grade == 7 ? 'selected' : '')+">7</option>" +
				                					"<option value=\"8\" "+(d.grade == 8 ? 'selected' : '')+">8</option>" +
				                					"<option value=\"9\" "+(d.grade == 9 ? 'selected' : '')+">9</option>" +
				                				"</select>" +
				                				"<div class=\"layui-unselect layui-form-select\">" +
				                					"<div class=\"layui-select-title\">" +
				                						"<input type=\"text\" placeholder=\"请选择\" value=\""+d.grade+"\" class=\"layui-input layui-unselect value\"> " +
				                						"<i class=\"layui-edge\"></i>" +
				                					"</div>" +
				                					"<dl class=\"layui-anim layui-anim-upbit\">" +
					                					"<dd lay-value=\"1\" class=\"\">1</dd>" +
					                					"<dd lay-value=\"2\" class=\"\">2</dd>" +
					                					"<dd lay-value=\"3\" class=\"\">3</dd>" +
					                					"<dd lay-value=\"4\" class=\"\">4</dd>" +
					                					"<dd lay-value=\"5\" class=\"\">5</dd>" +
					                					"<dd lay-value=\"6\" class=\"\">6</dd>" +
					                					"<dd lay-value=\"7\" class=\"\">7</dd>" +
					                					"<dd lay-value=\"8\" class=\"\">8</dd>" +
					                					"<dd lay-value=\"9\" class=\"\">9</dd>" +
				                					"</dl>" +
				                				"</div>" +
				                			"</div>" +
				                		"</div>"+
				                	"</div>"+
				                "</form>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">是否默认等级</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"defaultflag\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"defaultflag\" value=\"1\" title=\"是\" "+(d.defaultflag==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"defaultflag\" value=\"0\" title=\"否\" "+(d.defaultflag!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">折扣</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"discount\">"+
				                "<input type=\"number\" value=\""+d.discount+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">生日折扣</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"birthdaydiscount\">"+
				                "<input type=\"number\" value=\""+d.birthdaydiscount+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">积分转换比例(多少积分抵1元)</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"pointrate\">"+
				                "<input type=\"number\" value=\""+d.pointrate+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
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
