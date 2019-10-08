let goods = [];
let goodsVm;
let uploadInst;
let btnVm;

/**
 * vue初始页面
 */
common.bindVue = function() {
	refreshConfig("7001");
	
	btnVm = new Vue({
		el : ".operating",
		data : {
			config: config
        },
        methods : {
            synchronize: function() {
            	//synchronize('goods',loadData());
            	showEditBox(-1, null);
			},
			add: function () {
                showEditBox(-1, null);
            }
        }
	});
	
    goodsVm = new Vue({
        el : ".goods-data",
        data : {
            goods: goods
        },
        methods : {
        	/**
        	 * 编辑
        	 */
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,goods[index]);
                }
            },
            /**
             * 上架
             */
            onsale: function(event) {
            	if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    let index = $(event.target).parents("tr").data("index");
                    onSale(id, index, 1);
                }
			},
			/**
			 * 下架
			 */
			unsale: function(event) {
            	if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    let index = $(event.target).parents("tr").data("index");
                    onSale(id, index, 0);
                }
			},
			/**
			 * 图片
			 */
			images: function(event) {
				if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    showImagesBox(id);
                }
			},
			/**
			 * sku
			 */
			sku: function(event) {
				if (event) {
					let id = $(event.target).parents("tr").data("id");
					showSKUBox(id);
				}
			},
			/**
			 * 删除
			 */
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
    bindEven();
}

function bindEven() {
	$("body").on("mouseover", ".images-view .imageView .uploadField", function() {
		$(this).children('.layui-icon-delete').show();
	});
	$("body").on("mouseout", ".images-view .imageView .uploadField", function() {
		$(this).children('.layui-icon-delete').hide();
	});
}

/**
 * 上下架
 * @param id 商品id
 * @param index 列表索引
 * @param saleFlag 上下级标志
 * @returns
 */
function onSale(id, idx, saleFlag) {
	let msg = "确定下架？";
	if (saleFlag == 1) {
		msg = "确定上架？";
	}
	layer.confirm(msg, {
		btn: ['确定','取消'],
		btn1 : function(index, layero) {
			var data = {};
			data['id'] = id;
			data['onsale'] = saleFlag;
        	common.showLoading();
            api.load('./goods/onsale','post',data, function(result) {
                if (result.errcode == 0) {
                	goods[idx].onsale = saleFlag;
                    
                    common.tips(result.message);
                } else {
                    common.error(result.message);
                }
                common.closeLoading();
            });
			
		}
	});
}

/**
 * 加载数据
 * @returns
 */
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

/**
 * 分页数据
 * @param pageno 页码
 * @returns
 */
function loadPageData(pageno) {
	common.showLoading();
	api.load(basePath + 'data/list','post',{"table":"goods","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

/**
 * 设置数据
 * @param pageView 分页类
 * @returns
 */
function setData(pageView) {
	let data = pageView.data;
	goods.splice(0, goods.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	goods.push(data[i]);
        }
    }
}

/**
 * 获取商品信息
 * @param obj
 * @returns
 */
function syncGoods(obj) {
	let id = $(".edit-view .edit-box .edit-item .edit-value[data-field='id'] .value").val();
	if (id != null && id != undefined && $.trim(id) != "") {
		return;
	}
	console.log(goods);
	let data = {};
	data.code = $(obj).val();
	
	common.showLoading();
    api.load('./ext/sync/goods','post',data, function(result) {
        if (result.errcode == 0) {
        	var d = result.data;
        	replaceData(d);
        } else {
            common.error(result.message);
        }
        common.closeLoading();
    });
}

/**
 * 替换数据
 * @param data
 * @returns
 */
function replaceData(data) {
	for (const key in data) {
		if (data[key] != null && $.trim(data[key]) != null) {
			if (key == "brandid") {
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("data-id",data.brandid);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("value",data.brand);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").val(data.brand);
			} else if (key == "bigcategory") {
				$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("data-id",data.bigcategory);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("value",data.bigcategoryname);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").val(data.bigcategoryname);
			} else if (key == "middlecategory") {
				$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("data-id",data.middlecategory);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("value",data.middlecategoryname);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").val(data.middlecategoryname);
			} else if (key == "smallcategory") {
				$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("data-id",data.smallcategory);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("value",data.smallcategoryname);
	        	$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").val(data.smallcategoryname);
			} else {
				$(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").val(data[key]);
		        $(".edit-view .edit-box .edit-item .edit-value[data-field='"+key+"'] .value").attr("value",data[key]);
			}
		}
    }
}

/**
 * 新增/编辑页元素
 * @param data 商品数据
 * @returns
 */
function createElement(data) {
	let sync = 0;
	if (config != null && config != undefined && config.value == 1) {
		sync = 1;
	}
	
	let d = {
		id : "",
		extid: "",
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
				        "<div class=\"edit-item\" need=\"0\" key=\"0\" hidden>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">EXTID</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"extid\">"+
				                "<input type=\"text\" value=\""+d.extid+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">编号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"code\">"+
				                "<input type=\"text\" value=\""+d.code+"\" class=\"layui-input value focus\" "+(sync == 1 ? "onblur='syncGoods(this)'" : "")+"/>"+
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
				                		"data-url=\"./data/page?table=brand\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
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
					                		"data-url=\"./goodscategory/list?grade=1&pagesize=100\" data-grade=\"1\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>"+
					            "<div class=\"edit-value\" data-field=\"middlecategory\">"+
					                "<input type=\"text\" data-id=\""+d.middlecategory+"\" value=\""+d.middlecategoryname+"\" " +
					                		"data-url=\"./goodscategory/list?grade=2&pagesize=100\" data-grade=\"2\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>"+
					            "<div class=\"edit-value\" data-field=\"smallcategory\">"+
					                "<input type=\"text\" data-id=\""+d.smallcategory+"\" value=\""+d.smallcategoryname+"\" " +
					                		"data-url=\"./goodscategory/list?grade=3&pagesize=100\" data-grade=\"3\" class=\"layui-input value\" readonly=\"readonly\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>" +
					                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
					            "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" money=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">采购价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"purchaseprice\">"+
				                "<input type=\"number\" value=\""+d.purchaseprice+"\" class=\"layui-input value\" "+(sync == 1 ? "disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" money=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">标准价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"baseprice\">"+
				                "<input type=\"number\" value=\""+d.baseprice+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" money=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">零售价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"price\">"+
				                "<input type=\"number\" value=\""+d.price+"\" class=\"layui-input value\"/>"+
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

/**
 * 新增/编辑框
 * @param idx
 * @param data
 * @returns
 */
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
        	// 初始化图片上传
        	configUploadInst();
        	// 焦点定焦
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
        }
    });
}

function showImagesBox(id) {
	common.showLoading();
	let da = {};
	da.goodsid = id;
    api.load('./goods/info','post', da, function(result) {
    	if (result.errcode == 0) {
    		let datas = result.data;
    		let imagesDiv = createImagesElement(datas);
    	    
    	    layer.open({
    	        type: 1,
    	        title: "<label style='font-weight:600;'>更多</label>",
    	        content: imagesDiv,
    	        area: ['700px', '710px'],
    	        btn: ['保存','取消'],
    	        btn1: function (index, layero) {
    	        	let images = catchImages(id);
    	        	let groups = catchGroups(id);
    	        	images = JSON.stringify(images);
    	        	groups = JSON.stringify(groups);
    	            let data = {};
    	            data.goodsid = id;
    	            data.images = escape(images);
    	            data.groups = escape(groups);
    	            common.showLoading();
    	            api.load('./goods/images/save','post',data, function(result) {
    	                if (result.errcode == 0) {
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
    	        	// 初始化图片上传
    	        	configImageUploadInst();
    	        }
    	    });
        } else {
            common.error(result.message);
        }
        common.closeLoading();
    });
}

function createImagesElement(data) {
	let list = data.images;
	let groups = data.groups;
	let mainImages = "";
	let subImages = "";
	let groupItem = "";
	if (list != null) {
		for (var i = 0; i < list.length; i++) {
			if (list[i].type == 1) {
				mainImages = mainImages + "<div class=\"uploadField\">" +
												"<div class=\"layui-upload-drag image-item\" data-id=\""+list[i].id+"\">" +
									        		"<img src=\""+list[i].photourl+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
									        	"</div>" +
									        	"<i class=\"layui-icon layui-icon-delete\" onclick=\"removeImage(this)\"></i>" +
								        	"</div>";
			} else if (list[i].type == 2) {
				subImages = subImages + "<div class=\"uploadField\">" +
											"<div class=\"layui-upload-drag image-item\" data-id=\""+list[i].id+"\">" +
								        		"<img src=\""+list[i].photourl+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
								        	"</div>" +
								        	"<i class=\"layui-icon layui-icon-delete\" onclick=\"removeImage(this)\"></i>" +
							        	"</div>";
			}
		}
	}
	
	if (groups != null) {
		for (var i = 0; i < groups.length; i++) {
			groupItem = groupItem
					+ "<input type=\"checkbox\" name=\"groups\" value=\""
					+ groups[i].id + "\" data-id=\"" + groups[i].id
					+ "\" data-checkedid=\"" + groups[i].checkedid
					+ "\" title=\"" + groups[i].name + "\" "
					+ (groups[i].checked == 1 ? 'checked' : '')
					+ " lay-skin=\"primary\" class=\"layui-input value\">";
		}
	}
    let element = "<div class=\"images-view\">" +
    				"<div class=\"groupView layui-form\">" +
    					"<div class=\"titleView\"><span class=\"title\"><label class=\"name\">分组<label>：</span></div>" +
    					"<div class=\"layui-form-item\">" +
    						groupItem +
		                "</div>" +
    				"</div>" +
		    		"<div class=\"mainView\">" +
		    			"<div class=\"titleView\"><span class=\"title\"><label class=\"name\">主图<label>：</span></div>" +
		    			"<div class=\"imageView layui-form\" uploadfile=\"1\" key=\"0\">" +
				            "<div class=\"imagesItem layui-form-item\" data-field=\"photourl\">" +
				            	mainImages + 
				                "<div class=\"uploadField\">" +
				                	"<div class=\"layui-upload-drag addIcon\">" +
				                		"<i class=\"layui-icon\">&#xe608;</i>" +
				                	"</div>" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
		    		"</div>" +
		    		"<div class=\"subView\">" +
		    			"<div class=\"titleView\"><span  class=\"title\"><label class=\"name\">细节图<label>：</span></div>" +
		    			"<div class=\"imageView layui-form\" uploadfile=\"1\" key=\"0\">" +
				            "<div class=\"imagesItem layui-form-item\" data-field=\"photourl\">" +
				            	subImages + 
				                "<div class=\"uploadField\">" +
				                	"<div class=\"layui-upload-drag addIcon\">" +
				                		"<i class=\"layui-icon\">&#xe608;</i>" +
				                	"</div>" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
		    		"</div>" +
	    		"</div>";
    
    return element;
}

/**
 * 抓取上传图片
 * @param goodsid
 * @returns
 */
function catchImages(goodsid) {
	let list = [];
	// 主图
	$(".images-view .mainView .imageView .image-item").each(function(i) {
		let id = $(this).attr("data-id");
		let imageUrl = $(this).find(".value").attr("src");
		let data = {
			id : id,
			goodsid: goodsid,
			type: 1,
			photourl: imageUrl,
			showindex: i
		};
		list.push(data);
	});
	// 细节图
	$(".images-view .subView .imageView .image-item").each(function(i) {
		let id = $(this).attr("data-id");
		let imageUrl = $(this).find(".value").attr("src");
		let data = {
			id : id,
			goodsid: goodsid,
			type: 2,
			photourl: imageUrl,
			showindex: i
		};
		list.push(data);
	});
	return list;
}

/**
 * 抓取选择分组
 * @param goodsid
 * @returns
 */
function catchGroups(goodsid) {
	let list = [];
	$(".images-view .groupView input[type='checkbox']").each(function(i) {
		if ($(this).is(":checked")) {
			let id = $(this).attr("data-checkedid");
			if (id == null || id == undefined || $.trim(id) == "" || $.trim(id) == 0) {
				id = "";
			}
			let groupingid = $(this).attr("data-id")
			let data = {
				id : id,
				goodsid: goodsid,
				groupingid: groupingid
			};
			list.push(data);
		}
	});
	return list;
}

/**
 * 删除图片
 * @param obj
 * @returns
 */
function removeImage(obj) {
	event.stopPropagation();
	event.preventDefault();
    layer.confirm('确定删除？', {
		btn: ['确定','取消'],
		btn1 : function(index, layero) {
			$(obj).parent().remove();
			layer.close(index);
		}
	});
}

/**
 * 上传缩略图
 * @returns
 */
function configUploadInst() {
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/goodsthumbnail',
	    accept: "images",
	    acceptMime: "image/*",
	    size: 100,
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

/**
 * 上传主图/细节图
 * @returns
 */
function configImageUploadInst() {
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/goodsdtl',
	    size: 100,
	    accept: "images",
	    acceptMime: "image/*",
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	var item = this.item;
	    	let isAdd = this.item.hasClass("addIcon");
	    	if (isAdd) {
	    		let view = "<div class=\"uploadField\">" +
		    					"<div class=\"layui-upload-drag image-item\" data-id=\"\">" +
					    			"<img src=\""+res.data+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
					        	"</div>" +
					        	"<i class=\"layui-icon layui-icon-delete\" onclick=\"removeImage(this)\"></i>" +
					        "<div>";
				$(item).parent().before(view);
			} else {
				$(item).find("img").attr("src",res.data);
			}
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    }
    });
}

function showSKUBox(id) {
	common.showLoading();
	let da = {};
	da.goodsid = id;
    api.load('./goods/sku','post', da, function(result) {
    	if (result.errcode == 0) {
    		let datas = result.data;
    		let skuDiv = createSKUElement(datas);
    		
    		let sync = 0;
    		if (config != null && config != undefined && config.value == 1) {
    			sync = 1;
    		}
    	    let btns = ['保存','取消','添加'];
    	    if (sync == 1) {
    	    	btns = ['保存','取消','同步'];
			}
    	    layer.open({
    	        type: 1,
    	        title: "<label style='font-weight:600;'>库存</label>",
    	        content: skuDiv,
    	        area: ['900px', '600px'],
    	        btn: btns,
    	        btn1: function (index, layero) {
    	        	let skudatas = catchSkuData(id);
    	        	if ("error" == skudatas) {
						return false;
					}
    	        	skudatas = JSON.stringify(skudatas);
    	            let data = {};
    	            data.goodsid = id;
    	            data.skus = escape(skudatas);
    	            common.showLoading();
    	            api.load('./goods/sku/save','post',data, function(result) {
    	                if (result.errcode == 0) {
    	                    layer.close(index);
    	                    common.tips(result.message);
    	                } else {
    	                    common.error(result.message);
    	                }
    	                common.closeLoading();
    	            });
    	        },
    	        btn3: function() {
    	        	if (sync == 1) {
    	        		syncSKU();
					} else {
						addSKU();
					}
    	        	
    	        	return false;
				},
    	        success: function (layero, index) {
    	        	// 重新刷新form
    	        	layuiForm.render();
    	        	$(layero).find(".layui-layer-btn2").css({
    	        		"float": "left",
    	        		"background-color": "#009688",
    	        		"border-color": "#009688",
    	        		"color": "#FFFFFF"
    	        	});
    	        	$(layero).find("tbody").attr("data-goodsid",id);
    	        }
    	    });
        } else {
            common.error(result.message);
        }
        common.closeLoading();
    });
}

function createSKUElement(data) {
	let tr = "";
	
	let sync = 0;
	if (config != null && config != undefined && config.value == 1) {
		sync = 1;
	}
	
	if (data != null) {
		for (var i = 0; i < data.length; i++) {
			tr = tr + "<tr data-id='"+data[i].id+"'>" +
						"<td>" +
							"<div class=\"popup\">" +
								"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">颜色<span></div>" +
								"<input type=\"text\" class=\"layui-input value color\" data-id=\""+data[i].colorid+"\" value=\""+data[i].color+"\" data-url=\"./data/page?table=color\" readonly=\"readonly\" "+(sync==1?"disabled='disabled;'":"")+"/>" +
							"</div>" +
						"</td>" +
						"<td>" +
							"<div class=\"popup\">" +
								"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">版型<span></div>" +
								"<input type=\"text\" class=\"layui-input value pattern\" data-id=\""+data[i].patternid+"\" value=\""+data[i].pattern+"\" data-url=\"./data/page?table=pattern\" readonly=\"readonly\" "+(sync==1?"disabled='disabled;'":"")+"/>" +
							"</div>" +
						"</td>" +
						"<td>" +
							"<div class=\"popup\">" +
								"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">尺码<span></div>" +
								"<input type=\"text\" class=\"layui-input value size\" data-id=\""+data[i].sizeid+"\" value=\""+data[i].size+"\" data-url=\"./data/page?table=size\" readonly=\"readonly\" "+(sync==1?"disabled='disabled;'":"")+"/>" +
							"</div>" +
						"</td>" +
						"<td><input type=\"text\" class=\"layui-input value barcode\" value=\""+(data[i].barcode==null?'':data[i].barcode)+"\" "+(sync==1?"disabled='disabled;'":"")+"/></td>" +
						"<td><input type=\"number\" class=\"layui-input value quantity\" value=\""+(data[i].quantity==null?'':data[i].quantity)+"\" "+(sync==1?"disabled='disabled;'":"")+"/></td>" +
						"<td class=\"operationBtn\"><i class=\"layui-icon layui-icon-delete\" onclick='deleteTr(this)'></i></td>" +
					"</tr>";
		}
	}
	
	let element = "<div class=\"sku-view\">" +
					"<div class=\"sku-box\">" +
						"<div class=\"sku-table\">" +
							"<div class=\"sku-table-header\">" +
								"<table class=\"layui-table\">" +
									"<thead>" +
										"<tr>" +
											"<th>颜色</th>" +
											"<th>版型</th>" +
											"<th>尺码</th>" +
											"<th>条码</th>" +
											"<th>库存</th>" +
											"<th>操作</th>" +
										"</tr>" +
									"</thead>" +
									"<tbody>" +
										tr +
									"</tbody>" +
								"</table>" +
							"</div>" +
						"</div>" +
					"</div>" +
				"</div>";
	
	return element;
}

function syncSKU() {
	let id = $(".sku-view .sku-table .layui-table tbody").attr("data-goodsid");
	common.showLoading();
	let da = {};
	da.id = id;
    api.load('./ext/sync/sku','post',da, function(result) {
        if (result.errcode == 0) {
        	let msg = result.message;
        	da = {};
        	da.goodsid = id;
            api.load('./goods/sku','post', da, function(result) {
            	if (result.errcode == 0) {
            		let datas = result.data;
            		let skuDiv = createSKUElement(datas);
            		$(".layui-layer .layui-layer-content .sku-view").remove();
            		$(".layui-layer .layui-layer-content").append(skuDiv);
            		$(".sku-view .sku-table .layui-table tbody").attr("data-goodsid", id);
            		common.tips(msg);
            	} else {
            		common.error(result.message);
				}
            	common.closeLoading();
            });            
        } else {
            common.error(result.message);
        }
    });
}

function addSKU() {
	let tr = "<tr data-id=''>" +
				"<td>" +
					"<div class=\"popup\">" +
						"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">颜色<span></div>" +
						"<input type=\"text\" class=\"layui-input value color\" data-id=\"\" value=\"\" data-url=\"./data/page?table=color&pagesize=100\" readonly=\"readonly\"/>" +
					"</div>" +
				"</td>" +
				"<td>" +
					"<div class=\"popup\">" +
						"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">版型<span></div>" +
						"<input type=\"text\" class=\"layui-input value pattern\" data-id=\"\" value=\"\" data-url=\"./data/page?table=pattern&pagesize=100\" readonly=\"readonly\"/>" +
					"</div>" +
				"</td>" +
				"<td>" +
					"<div class=\"popup\">" +
						"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">尺码<span></div>" +
						"<input type=\"text\" class=\"layui-input value size\" data-id=\"\" value=\"\" data-url=\"./data/page?table=size&pagesize=100\" readonly=\"readonly\"/>" +
					"</div>" +
				"</td>" +
				"<td><input type=\"text\" class=\"layui-input value barcode\"/></td>" +
				"<td><input type=\"number\" class=\"layui-input value quantity\"/></td>" +
				"<td class=\"operationBtn\"><i class=\"layui-icon layui-icon-delete\" onclick='deleteTr(this)'></i></td>" +
			"</tr>";
	$(".sku-view .sku-table tbody").append(tr);
}

function deleteTr(obj) {
	layer.confirm('确定删除？', {
		btn: ['确定','取消'],
		btn1 : function(index, layero) {
			$(obj).parents("tr").remove();
			layer.close(index);
		}
	});
}

function catchSkuData(goodsid) {
	let list = [];
	let errorMsg = "";
	$(".sku-view .sku-table tbody tr").each(function(i) {		
		let id = $(this).attr("data-id");
		if (id == null || id == undefined || $.trim(id) == "" || $.trim(id) == 0) {
			id = "";
		}
		
		let colorid = $(this).find(".color").attr("data-id");
		if (colorid == null || colorid == undefined || $.trim(colorid) == "" || $.trim(colorid) == 0) {
			errorMsg = "请选择颜色";
			return false;
		}
		let patternid = $(this).find(".pattern").attr("data-id");
		if (patternid == null || patternid == undefined || $.trim(patternid) == "" || $.trim(patternid) == 0) {
			errorMsg = "请选择版型";
			return false;
		}
		let sizeid = $(this).find(".size").attr("data-id");
		if (sizeid == null || sizeid == undefined || $.trim(sizeid) == "" || $.trim(sizeid) == 0) {
			errorMsg = "请选择尺码";
			return false;
		}
		let barcode = $(this).find(".barcode").val();
		if (barcode == null || barcode == undefined || $.trim(barcode) == "" || $.trim(barcode) == 0) {
			barcode = "";
		}
		let quantity = $(this).find(".quantity").val();
		if (quantity == null || quantity == undefined || $.trim(quantity) == "" || $.trim(quantity) == 0) {
			quantity = 0;
		}
		
		let data = {
			id : id,
			goodsid: goodsid,
			colorid: colorid,
			patternid: patternid,
			sizeid: sizeid,
			barcode: barcode,
			quantity: quantity,
		};
		list.push(data);
	});
	if (errorMsg != null && errorMsg != undefined && $.trim(errorMsg) != "") {
		common.warn(errorMsg);
		return "error";
	}
	return list;
}