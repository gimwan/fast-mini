let micro = [];
let microVm;
let publicplatforms = [];
let publicPlatformVm;

common.bindVue = function() {
	console.log("page");
    microVm = new Vue({
        el : ".micro-data",
        data : {
            micro: micro
        },
        methods : {
        	edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,micro[index]);
                }
            },
            set: function(event) {
            	if (event) {
            		let publicPlatformID = $(".publicPlatform .selectItem .layui-this").attr("lay-value");
                    let id = $(event.target).parents("tr").attr("data-id");
                    let name = $(event.target).parents("tr").attr("data-name");
                    let deleteIndex = $(event.target).parents("tr").data("index");
                    let url = "./micropage/micropage?id="+id+"&publicplatformid="+publicPlatformID;
                    layer.open({
                    	type: 1,
                    	title: name,
                        area: ['1320px', '800px'],
            	        success: function (layero, index) {
            	        	let data = {};
                        	data.id = id;
            	        	common.showLoading();
            	        	$(layero).find(".layui-layer-content").load(url, data,function(){
        	                    common.closeLoading();
        	                    common.bindVue();
        	                    $("#openViewIndex").val(index);
        	                });
            	        }
                	});
            	}
			},
			release: function() {
				if (event) {
					let id = $(event.target).parents("tr").attr("data-id");
					layer.confirm('确定发布？', {
                		btn: ['确定','取消'],
                		btn1 : function(index, layero) {
        					var data = {};
            				data.pageid = id;
                        	common.showLoading();
                            api.load('./micropage/release','post',data, function(result) {
                                if (result.errcode == 0) {
                                    common.tips(result.message);
                                } else {
                                    common.error(result.message);
                                }
                                common.closeLoading();
                            });
                		}
					});
				}
			},
            del: function (event) {
            	if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    let deleteIndex = $(event.target).parents("tr").data("index");
                    layer.confirm('确定删除？', {
                		btn: ['确定','取消'],
                		btn1 : function(index, layero) {
            				var data = {};
            				data['id'] = id;
                        	common.showLoading();
                            api.load('./micropage/delete','post',data, function(result) {
                                if (result.errcode == 0) {
                                	micro.splice(deleteIndex);
                                    
                                    common.tips(result.message);
                                } else {
                                    common.error(result.message);
                                }
                                common.closeLoading();
                            });
                		}
                	});
            	}
            },
            formatDate: function(jsonDate) {
            	let date = common.formatDate(jsonDate);
				return date;
            }
        }
    });
    // 加载公众号下拉选择
    loadPublicPlatform();
}

function loadPublicPlatform() {
	common.showLoading();
    api.load(basePath + 'publicplatform/publicplatform','post',{"table":"micropage"},function (result) {
    	if (result.errcode == 0) {
    		let data = result.data;
    		let publicplatformid = "";
    		if (data != null) {
    			publicplatformid = data[0].id;
			}
    		publicplatforms = data;
    		publicPlatformVm = new Vue({
    	        el : ".operating",
    	        data : {
    	        	publicplatform: publicplatforms
    	        },
    	        methods : {
    	            add: function () {
    	                showEditBox(-1, null);
    	            }
    	        }
    		});
    		// 重新刷新form
    	    layuiForm.render();
    	    // 选择公众号
    	    publicPlatformSelect();
    	    // 加载列表数据
    		loadData(publicplatformid);
    	} else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}

function loadData(publicplatformid) {
	let data = {};
	if (publicplatformid != null && publicplatformid != undefined && $.trim(publicplatformid) != "") {
		data.publicplatformid = publicplatformid;
	}
    common.showLoading();
	api.load(basePath + 'micropage/list','post', data, function (result) {
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
	let publicPlatformID = $(".publicPlatform .selectItem .layui-this").attr("lay-value");
	let data = {};
	if (publicPlatformID != null && publicPlatformID != undefined && $.trim(publicPlatformID) != "") {
		data.publicplatformid = publicPlatformID;
	}
	common.showLoading();
	api.load(basePath + 'micropage/list', 'post', data, function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	micro.splice(0, micro.length);
	if (data != null) {
		for (let i = 0; i < data.length; i++) {
        	micro.push(data[i]);
        }
    }
}

function createElement(data) {
	let publicPlatformID = $(".publicPlatform .selectItem .layui-this").attr("lay-value");
	let d = {
		id : "",
		code : "",
		name : "",
		homeflag : 0,
		publicplatformid : publicPlatformID,
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
				        "<div class=\"edit-item\" need=\"1\" key=\"0\" hidden>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">公众号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"publicplatformid\">"+
				                "<input type=\"text\" value=\""+d.publicplatformid+"\" class=\"layui-input value\"/>"+
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
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">是否首页</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"homeflag\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"homeflag\" value=\"1\" title=\"是\" "+(d.homeflag==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"homeflag\" value=\"0\" title=\"否\" "+(d.homeflag!=1?'checked':'')+" class=\"layui-input value\">" +
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
	let publicPlatformID = $(".publicPlatform .selectItem .layui-this").attr("lay-value");
	if (publicPlatformID == null || publicPlatformID == undefined || $.trim(publicPlatformID) == "") {
		common.tips("请先选择公众号");
	}
	
	let editDiv = createElement(data);
	
	let boxTitle = "<label style='font-weight:600;'>修改</label>";
	if (idx < 0) {
		boxTitle = "<label style='font-weight:600;'>新增</label>";
	}
    
    layer.open({
        type: 1,
        title: boxTitle,
        content: editDiv,
        area: ['600px', '610px'],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./micropage/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	// 同一公众号首页只有一个
                	let isHome = data.homeflag;
                	if (isHome == 1) {
						for (var i = 0; i < micro.length; i++) {
							micro[i].homeflag = 0;
						}
					}
                	if (idx < 0) {
                		micro.push(data);
					} else {
						for (const key in data) {
	                        micro[idx][key] = data[key];
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

function publicPlatformSelect() {
	layuiForm.on('select', function(data) {
		loadPageData(1);
	})
}