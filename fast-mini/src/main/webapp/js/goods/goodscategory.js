let bigcategory = [];
let bigcategoryVm;
let middlecategory = [];
let middlecategoryVm;
let smallcategory = [];
let smallcategoryVm;
let grade = 1;
let parentid = 0;
let parentcategory = [];
let chilescategory = [];
let parentcategory1Vm;
let parentcategory2Vm;
let chilescategoryVm;

common.bindVue = function() {
	console.log('category')
	bigcategoryVm = new Vue({
        el : ".big",
        data : {
        	bigcategory: bigcategory
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,bigcategory[index]);
                }
            },
            add: function () {
                showEditBox(-1, null);
            },
            del: function () {
    			let id = $(".layui-table-view .layui-table-box .layui-table-body table .selected").attr("data-id");
    			let deleteIndex = $(".layui-table-view .layui-table-box .layui-table-body table .selected").attr("data-index");
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
                        api.load('./goodscategory/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	bigcategory.splice(deleteIndex);
                                
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
	middlecategoryVm = new Vue({
        el : ".middle",
        data : {
        	middlecategory: middlecategory
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,middlecategory[index]);
                }
            },
            formatDate: function(jsonDate) {
            	let date = common.formatDate(jsonDate);
				return date;
            }
        }
    });
	smallcategoryVm = new Vue({
        el : ".small",
        data : {
        	smallcategory: smallcategory
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,smallcategory[index]);
                }
            },
            formatDate: function(jsonDate) {
            	let date = common.formatDate(jsonDate);
				return date;
            }
        }
    });
	parentcategory1Vm = new Vue({
        el : ".parentCategory1",
        data : {
        	parentcategory: parentcategory
        },
        methods : {
            add: function () {
                showEditBox(-1, null);
            },
            del: function () {
    			let id = $(".layui-table-view .layui-table-box .layui-table-body table .selected").attr("data-id");
    			let deleteIndex = $(".layui-table-view .layui-table-box .layui-table-body table .selected").attr("data-index");
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
                        api.load('./goodscategory/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	smallcategory.splice(deleteIndex);
                                
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
	parentcategory2Vm = new Vue({
        el : ".parentCategory2",
        data : {
        	parentcategory: parentcategory
        }
    });
	chilescategoryVm = new Vue({
        el : ".chilesCategory",
        data : {
        	chilescategory: chilescategory
        }
    });
    pageConfig();
    loadData();
    layuiElement.on('tab(categoryTab)', function(data) {
    	$(".layui-table-view .layui-table-box .layui-table-body table .selected").removeClass("selected");
    	grade = data.index + 1;
    	parentid = 0;
    	if (grade == 1) {
    		loadData();
		} else {
			bigPatternCategory()
		}
	});
    layuiForm.on('select', function(data) {
    	parentid = data.value;
    	if (parentid == null || parentid == undefined || $.trim(parentid) == "") {
			return;
		}
    	let type = $(data.elem).attr("data-type");
		if (type == "middle") {
			loadData();
		}
		else if (type == "small1") {
			parentid = 0;
			let childs = [];
			for (var i = 0; i < parentcategory.length; i++) {
				if (parentcategory[i].id == data.value) {
					childs = parentcategory[i].childs;
				}
			}
			chilescategory.splice(0, chilescategory.length);
			for (var j = 0; j < childs.length; j++) {
				chilescategory.push(childs[j]);
			}
			smallcategory.splice(0, smallcategory.length);
			setTimeout(() => {
				// 重新刷新form
        	    layuiForm.render();
			}, 300);
		}
		else if (type == "small2") {
			loadData();
		} else {
			loadData();
		}
	})
}

function loadData() {
    common.showLoading();
    let postData = {"grade":grade,"pagesize":100};
    if (parentid != null && parentid != undefined && $.trim(parentid) != "") {
    	postData.parentid = parentid;
	}
    api.load(basePath + 'goodscategory/list', 'post', postData, function (result) {
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
	let postData = {"grade":grade,"pageno":pageno};
    if (parentid != null && parentid != undefined && $.trim(parentid) != "") {
    	postData.parentid = parentid;
	}
	api.load(basePath + 'goodscategory/list', 'post', postData, function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.records;
	if (grade == 1) {
		bigcategory.splice(0, bigcategory.length);
	} else if (grade == 2) {
		middlecategory.splice(0, middlecategory.length);
	} else if (grade == 3) {
		smallcategory.splice(0, smallcategory.length);
	}
	
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	if (grade == 1) {
        		bigcategory.push(data[i]);
        	} else if (grade == 2) {
        		middlecategory.push(data[i]);
        	} else if (grade == 3) {
        		smallcategory.push(data[i]);
        	}        	
        }
    }
}

function createElement(data) {
	let d = {
		id : "",
		code : "",
		name : "",
		grade: grade,
		parentid: parentid,
		photourl: '',
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
				        "<div class=\"edit-item\" need=\"1\" key=\"\" hidden>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">GRADE</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"grade\">"+
				                "<input type=\"text\" value=\""+d.grade+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"\" hidden>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">PARENTID</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"parentid\">"+
				                "<input type=\"text\" value=\""+d.parentid+"\" class=\"layui-input value\"/>"+
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
				        "<div class=\"edit-item\" image=\"1\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">缩略图</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"photourl\">"+
				                "<img src=\""+d.photourl+"\" onerror=\"defaultImg(this)\" class=\"layui-square-img layui-upload-drag value\"/>"+
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
	if (grade != 1) {
		if (parentid == null || parentid == undefined || $.trim(parentid) == "" || $.trim(parentid) == 0) {
			common.warn("请先选择分类");
            return false;
		}
	}
    
    layer.open({
        type: 1,
        title: boxTitle,
        content: editDiv,
        area: ['600px', '660px'],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./goodscategory/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		goodscategory.push(data);
					} else {
						for (const key in data) {
	                        goodscategory[idx][key] = data[key];
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
        	configUpload();
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
        }
        
    });
}

function bigPatternCategory() {
	common.showLoading();
    api.load(basePath + 'goodscategory/category','post',{},function (result) {
    	if (result.errcode == 0) {
    		let data = result.data;
    		//parentid = 0;
    		parentcategory.splice(0, parentcategory.length);
    		if (data != null) {
    			//parentid = data[0].id;
    			for (let i = 0; i < data.length; i++) {
    				parentcategory.push(data[i]);
    	        }
			}
    		setTimeout(() => {
    			// 重新刷新form
        	    layuiForm.render();
			}, 300);
    	    
    	    //loadData();
    	} else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}

function smallAdd() {
	showEditBox(-1, null);
}

function smallDel() {
	let id = $(".layui-table-view .layui-table-box .layui-table-body table .selected").attr("data-id");
	let deleteIndex = $(".layui-table-view .layui-table-box .layui-table-body table .selected").attr("data-index");
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
            api.load('./goodscategory/delete','post',data, function(result) {
                if (result.errcode == 0) {
                	smallcategory.splice(deleteIndex);
                    
                    common.tips(result.message);
                } else {
                    common.error(result.message);
                }
                common.closeLoading();
            });
		}
	});
}

function configUpload() {
	layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/employee',
	    size: 1024,
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	var item = this.item;
	    	$(item).attr("src",res.data);
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    	configAssembly();
	    }
	});
}