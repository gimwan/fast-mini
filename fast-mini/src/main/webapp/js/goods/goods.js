let goods = [];
let goodsVm;
let uploadInst;

common.bindVue = function() {
    goodsVm = new Vue({
        el : ".goodsPage",
        data : {
            goods: goods
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,goods[index]);
                }
            },
            add: function () {
                showEditBox(-1, null);
            },
            onsale: function() {
            	if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    let index = $(event.target).parents("tr").data("index");
                    layer.confirm('确定上架？', {
                		btn: ['确定','取消'],
                		btn1 : function(index, layero) {
            				var data = {};
            				data['id'] = id;
            				data['onsale'] = 1;
                        	common.showLoading();
                            api.load('./goods/onsale','post',data, function(result) {
                                if (result.errcode == 0) {
                                	goods[index].onsale = 1;
                                    
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
			unsale: function() {
            	if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    let index = $(event.target).parents("tr").data("index");
                    layer.confirm('确定下架？', {
                		btn: ['确定','取消'],
                		btn1 : function(index, layero) {
            				var data = {};
            				data['id'] = id;
            				data['onsale'] = 0;
                        	common.showLoading();
                            api.load('./goods/onsale','post',data, function(result) {
                                if (result.errcode == 0) {
                                	goods[index].onsale = 0;
                                    
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
			image: function(event) {
				if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    console.log(id);
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
                            api.load('./goods/delete','post',data, function(result) {
                                if (result.errcode == 0) {
                                	goods.splice(deleteIndex);
                                    
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
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'data/list','post',{"table":"goods"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"goods","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.records;
	goods.splice(0, goods.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	goods.push(data[i]);
        }
    }
}

function createElement(data) {
	let d = {
		id : "",
		code : "",
		name : "",
		brandid : "",
		brand : "",
		bigcategory : "",
		bigcategoryname : "",
		middlecategory : "",
		middlecategoryname : "",
		smallcategory : "",
		smallcategoryname : "",
		purchaseprice : 0,
		baseprice : 0,
		price : 0,
		onlyshow : 0,
		showcolor : 1,
		showpattern : 1,
		showsize : 1,
		photourl : "",
		useflag : 1,
		describe: "",
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
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">品牌</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"brandid\">"+
				                "<input type=\"text\" data-id=\""+d.brandid+"\" value=\""+d.brand+"\" " +
				                		"data-url=\"./data/page?table=brand\" class=\"layui-input value\" readonly=\"readonly\"/>" +
				                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item cascade\" cascade=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">分类</label>：</span>"+
				            "</div>"+
				            "<div class=\"cascade-value\">" +
					            "<div class=\"edit-value\" data-field=\"bigcategory\">"+
					                "<input type=\"text\" data-id=\""+d.bigcategory+"\" value=\""+d.bigcategoryname+"\" " +
					                		"data-url=\"./goodscategory/list?grade=1\" data-grade=\"1\" class=\"layui-input value\" readonly=\"readonly\"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>"+
					            "<div class=\"edit-value\" data-field=\"middlecategory\">"+
					                "<input type=\"text\" data-id=\""+d.middlecategory+"\" value=\""+d.middlecategoryname+"\" " +
					                		"data-url=\"./goodscategory/list?grade=2\" data-grade=\"2\" class=\"layui-input value\" readonly=\"readonly\"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>"+
					            "<div class=\"edit-value\" data-field=\"smallcategory\">"+
					                "<input type=\"text\" data-id=\""+d.smallcategory+"\" value=\""+d.smallcategoryname+"\" " +
					                		"data-url=\"./goodscategory/list?grade=3\" data-grade=\"3\" class=\"layui-input value\" readonly=\"readonly\"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" money=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">采购价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"purchaseprice\">"+
				                "<input type=\"text\" value=\""+d.purchaseprice+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" money=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">标准价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"baseprice\">"+
				                "<input type=\"text\" value=\""+d.baseprice+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" money=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">零售价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"price\">"+
				                "<input type=\"text\" value=\""+d.price+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" checkbox=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">规格展示</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"onlyshow\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"checkbox\" name=\"showcolor\" value=\"1\" title=\"颜色\" "+(d.showcolor==1?'checked':'')+" lay-skin=\"primary\" class=\"layui-input value\">" +
				                	"<input type=\"checkbox\" name=\"showpattern\" value=\"1\" title=\"版型\" "+(d.showpattern==1?'checked':'')+" lay-skin=\"primary\" class=\"layui-input value\">" +
				                	"<input type=\"checkbox\" name=\"showsize\" value=\"1\" title=\"尺码\" "+(d.showsize==1?'checked':'')+" lay-skin=\"primary\" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">仅限展示</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"onlyshow\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"onlyshow\" value=\"1\" title=\"是\" "+(d.onlyshow==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"onlyshow\" value=\"0\" title=\"否\" "+(d.onlyshow!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" uploadfile=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">缩略图</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"photourl\">"+
				                "<div class=\"uploadField\">" +
				                	"<div class=\"layui-upload-drag\">" +
				                		"<img src=\""+d.photourl+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
				                	"</div>" +
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
				                "<span class=\"title\"><label class=\"name\">描述</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"describe\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<textarea name=\"describe\" class=\"layui-textarea value\">"+d.describe+"</textarea>" +
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
        area: ['700px', '710px'],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./goods/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		goods.push(data);
					} else {
						for (const key in data) {
	                        goods[idx][key] = data[key];
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
        	// 初始化图片上传组件
        	configUploadInst();
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
        }
        
    });
}

function configUploadInst() {
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/goodsthumbnail',
	    size: 500,
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	var item = this.item;
	    	$(item).find("img").attr("src",res.data);
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    }
    });
}