let department = [];
let departmentVm;
let btnVm;

common.bindVue = function() {
	refreshConfig("7001");
	
	btnVm = new Vue({
		el : ".operating",
		data : {
			config: config
        },
        methods : {
        	add: function () {
                showEditBox(-1, null);
            },
            synchronize: function() {
            	synchronize('departmentlist',loadData);
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
                        api.load('./department/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	department.splice(deleteIndex);
                                
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
    departmentVm = new Vue({
        el : ".department-data",
        data : {
            department: department
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,department[index]);
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
    api.load(basePath + 'data/list','post',{"table":"department"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"color","department":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	department.splice(0, department.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	department.push(data[i]);
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
            console.log(data);
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./department/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		department.push(data);
					} else {
						for (const key in data) {
	                        department[idx][key] = data[key];
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
	    contacts : "",
	    provinceid : "",
	    province : "",
	    cityid : "",
	    city : "",
	    countyid : "",
	    county : "",
	    phone : "",
	    address : "",
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
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">联系人</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"contacts\">"+
				                "<input type=\"text\" value=\""+d.contacts+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">联系人电话</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"phone\">"+
				                "<input type=\"text\" value=\""+d.phone+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item cascade\" cascade=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">地区</label>：</span>"+
				            "</div>"+
				            "<div class=\"cascade-value\">" +
					            "<div class=\"edit-value\" data-field=\"provinceid\">"+
					                "<input type=\"text\" data-id=\""+d.provinceid+"\" value=\""+d.province+"\" " +
					                		"data-url=\"./region/list?grade=1&pagesize=100\" data-grade=\"1\" region=\"1\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>"+
					            "<div class=\"edit-value\" data-field=\"cityid\">"+
					                "<input type=\"text\" data-id=\""+d.cityid+"\" value=\""+d.city+"\" " +
					                		"data-url=\"./region/list?grade=2&pagesize=100"+(d.provinceid==null?'':'&parentid='+d.provinceid)+"\" data-grade=\"2\" region=\"1\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>"+
					            "<div class=\"edit-value\" data-field=\"countyid\">"+
					                "<input type=\"text\" data-id=\""+d.countyid+"\" value=\""+d.county+"\" " +
					                		"data-url=\"./region/list?grade=3&pagesize=100"+(d.cityid==null?'':'&parentid='+d.cityid)+"\" data-grade=\"3\" region=\"1\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">详细地址</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"address\">"+
				                "<input type=\"text\" value=\""+d.address+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
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
