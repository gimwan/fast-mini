let goods = [];
let goodsVm;
let uploadInst;

/**
 * vue初始页面
 */
common.bindVue = function() {
    goodsVm = new Vue({
        el : ".goodsPage",
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
             * 新增
             */
            add: function () {
                showEditBox(-1, null);
            },
            /**
             * 上架
             */
            onsale: function() {
            	if (event) {
                    let id = $(event.target).parents("tr").data("id");
                    let index = $(event.target).parents("tr").data("index");
                    onSale(id, index, 1);
                }
			},
			/**
			 * 下架
			 */
			unsale: function() {
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
					console.log(id);
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
	let data = pageView.records;
	goods.splice(0, goods.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	goods.push(data[i]);
        }
    }
}

/**
 * 新增/编辑页元素
 * @param data 商品数据
 * @returns
 */
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
    api.load('./goods/images','post', da, function(result) {
    	if (result.errcode == 0) {
    		let list = result.data;
    		let imagesDiv = createImagesElement(list);
    	    
    	    layer.open({
    	        type: 1,
    	        title: "<label style='font-weight:600;'>图片</label>",
    	        content: imagesDiv,
    	        area: ['700px', '710px'],
    	        btn: ['保存','取消'],
    	        btn1: function (index, layero) {
    	        	let images = catchImages(id);
    	        	images = JSON.stringify(images);
    	            let data = {};
    	            data.images = escape(images);
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

function createImagesElement(list) {
	let mainImages = "";
	let subImages = "";
	if (list != null) {
		for (var i = 0; i < list.length; i++) {
			if (list[i].type == 1) {
				mainImages = mainImages + "<div class=\"layui-upload-drag image-item\" data-id=\""+list[i].id+"\">" +
								        		"<img src=\""+list[i].photourl+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
								        	"</div>";
			} else if (list[i].type == 2) {
				subImages = subImages + "<div class=\"layui-upload-drag image-item\" data-id=\""+list[i].id+"\">" +
							        		"<img src=\""+list[i].photourl+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
							        	"</div>";
			}
		}
	}
    let element = "<div class=\"images-view\">" +
		    		"<div class=\"mainView\">" +
		    			"<div class=\"titleView\"><span class=\"title\"><label class=\"name\">主图<label>：</span></div>" +
		    			"<div class=\"imageView layui-form\" uploadfile=\"1\" key=\"0\">" +
				            "<div class=\"imagesItem layui-form-item\" data-field=\"photourl\">" +
				                "<div class=\"uploadField\">" +
				                	mainImages + 
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
				                "<div class=\"uploadField\">" +
				                	subImages + 
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
 * 上传缩略图
 * @returns
 */
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

/**
 * 上传主图/细节图
 * @returns
 */
function configImageUploadInst() {
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/goodsdtl',
	    size: 500,
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	var item = this.item;
	    	let view = "<div class=\"layui-upload-drag image-item\" data-id=\"\">" +
			    			"<img src=\""+res.data+"\" onerror=\"defaultImg(this)\" class=\"value\">" +
			        	"</div>";
	    	$(item).before(view);
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    }
    });
}