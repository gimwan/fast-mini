let groupbuy = [];
let groupbuyVm;
let btnVm;

common.bindVue = function() {
	console.log('groupbuy');
	btnVm = new Vue({
		el : ".operating",
		data : {
			config: config
        },
        methods : {
            add: function () {
                showEditBox(-1, null);
            }/*,
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
                        api.load('./groupbuy/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	groupbuy.splice(deleteIndex);
                                
                                common.tips(result.message);
                            } else {
                                common.error(result.message);
                            }
                            common.closeLoading();
                        });
            		}
            	});
            }*/
        }
	});
	
    groupbuyVm = new Vue({
        el : ".groupbuy-data",
        data : {
            groupbuy: groupbuy
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,groupbuy[index]);
                }
            },
            goods: function(event) {
            	if (event) {
					let id = $(event.target).parents("tr").attr("data-id");
					let index = $(event.target).parents("tr").attr("data-index");
					showGoodsBox(id,index);
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
                            api.load('./groupbuy/delete','post',data, function(result) {
                                if (result.errcode == 0) {
                                	groupbuy.splice(deleteIndex);
                                    
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
    api.load(basePath + 'data/list','post',{"table":"groupbuy"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"groupbuy","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	groupbuy.splice(0, groupbuy.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	groupbuy.push(data[i]);
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
            api.load('./groupbuy/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		groupbuy.push(data);
					} else {
						for (const key in data) {
	                        groupbuy[idx][key] = data[key];
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
        	// 初始化时间控件
        	layDate.render({
        		elem: '#beginDate',
        		type: 'datetime',
        		format: 'yyyy-MM-dd HH:mm:ss'
        	});
        	layDate.render({
        		elem: '#endDate',
        		type: 'datetime',
        		format: 'yyyy-MM-dd HH:mm:ss'
        	});
        	// 初始化图片上传
        	configImageUploadInst();
        	// 焦点在第一项
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
	    minimum : "",
	    begintime : "",
	    endtime : "",
	    photourl : "",
	    useflag : "1",
	    publicplatformid : "",
	    publicplatform : "",
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
				                "<input type=\"text\" value=\""+d.code+"\" class=\"layui-input value focus\" />"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"name\">"+
				                "<input type=\"text\" value=\""+d.name+"\" class=\"layui-input value\" />"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">生效时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"begintime\">"+
				                "<input type=\"text\" value=\""+common.formatDate(d.begintime)+"\" id=\"beginDate\" class=\"layui-input timechoosed value\" readonly=\"readonly\" />"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">失效时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"endtime\">"+
				                "<input type=\"text\" value=\""+common.formatDate(d.endtime)+"\" id=\"endDate\" class=\"layui-input timechoosed value\" readonly=\"readonly\" />"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">成团人数</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"minimum\">"+
				                "<input type=\"number\" value=\""+d.minimum+"\" class=\"layui-input value\" />"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">公众号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"publicplatformid\">"+
				                "<input type=\"text\" data-id=\""+d.publicplatformid+"\" value=\""+d.publicplatform+"\" " +
				                		"data-url=\"./data/page?table=publicplatform\" class=\"layui-input value\" readonly=\"readonly\" />" +
				                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" uploadfile=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">封面图</label>：</span>"+
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
 * 上传主图/细节图
 * @returns
 */
function configImageUploadInst() {
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/marketing',
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

function showGoodsBox(id,index) {
	common.showLoading();	
	let da = {};
	da.groupbuyid = id;
    api.load('./groupbuy/detail','post', da, function(result) {
    	if (result.errcode == 0) {
    		let datas = result.data;
    		let goodsDiv = createGoodsElement(datas);    		
    	    let btns = ['保存','取消','添加'];
    	    layer.open({
    	        type: 1,
    	        title: "<label style='font-weight:600;'>商品</label>",
    	        content: goodsDiv,
    	        area: ['900px', '600px'],
    	        btn: btns,
    	        btn1: function (index, layero) {
    	        	let goodsdatas = catchGoodsData(id);
    	        	if ("error" == goodsdatas) {
						return false;
					}
    	        	goodsdatas = JSON.stringify(goodsdatas);
    	            let data = {};
    	            data.groupbuyid = id;
    	            data.goodsdatas = escape(goodsdatas);
    	            common.showLoading();
    	            api.load('./groupbuy/detail/save','post',data, function(result) {
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
    	        	addGoods();
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

function createGoodsElement(data) {
	let tr = "";
	
	if (data != null) {
		for (var i = 0; i < data.length; i++) {
			tr = tr + "<tr data-id='"+data[i].id+"'>" +
						"<td class=\"d-photourl\"><img class=\"value photourl\" src=\""+data[i].photourl+"\" onerror=\"defaultImg(this)\"/></td>" +						
						"<td class=\"d-code\">" +
							"<div class=\"popup\">" +
								"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">编号<span></div>" +
								"<input type=\"text\" class=\"layui-input value code\" goods=\"1\" data-id=\""+data[i].goodsid+"\" value=\""+data[i].code+"\" data-url=\"./data/page?table=color\" onChange=\"chooseGoods(this)\" readonly=\"readonly\"/>" +
							"</div>" +
						"</td>" +
						"<td class=\"d-name\"><input type=\"text\" class=\"layui-input value name\" value=\""+(data[i].name==null?'':data[i].name)+"\" readonly=\"readonly\" /></td>" +
						"<td class=\"d-baseprice\"><input type=\"text\" class=\"layui-input value baseprice\" value=\""+(data[i].baseprice==null?'':data[i].baseprice)+"\" readonly=\"readonly\"/></td>" +
						"<td class=\"d-saleprice\"><input type=\"text\" class=\"layui-input value saleprice\" value=\""+(data[i].saleprice==null?'':data[i].saleprice)+"\" readonly=\"readonly\" /></td>" +
						"<td class=\"d-price\"><input type=\"number\" class=\"layui-input value price\" value=\""+(data[i].price==null?'':data[i].price)+"\" /></td>" +
						"<td class=\"d-operation operationBtn\"><i class=\"layui-icon layui-icon-delete\" onclick='deleteTr(this)'></i></td>" +
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
											"<th class='d-photourl'>图片</th>" +
											"<th class='d-code'>编号</th>" +
											"<th class='d-name'>名称</th>" +
											"<th class='d-baseprice'>标准价</th>" +
											"<th class='d-saleprice'>零售价</th>" +
											"<th class='d-price'>拼团价</th>" +
											"<th class='d-operation'>操作</th>" +
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

function addGoods() {
	let tr = "<tr data-id=''>" +
				"<td class=\"d-photourl\"><img class=\"value photourl\" src='' onerror=\"defaultImg(this)\"/></td>" +
				"<td class=\"d-code\">" +
					"<div class=\"popup\">" +
						"<div class=\"edit-title\" style=\"display:none;\"><span class=\"name\">编号<span></div>" +
						"<input type=\"text\" class=\"layui-input value code\" goods=\"1\" data-id=\"\" value=\"\" data-url=\"./data/page?table=goods&pagesize=100\" onChange=\"chooseGoods(this)\" readonly=\"readonly\"/>" +
					"</div>" +
				"</td>" +
				"<td class=\"d-name\"><input type=\"text\" class=\"layui-input value name\" readonly=\"readonly\" /></td>" +
				"<td class=\"d-baseprice\"><input type=\"text\" class=\"layui-input value baseprice\" readonly=\"readonly\" /></td>" +
				"<td class=\"d-saleprice\"><input type=\"number\" class=\"layui-input value saleprice\" readonly=\"readonly\" /></td>" +
				"<td class=\"d-price\"><input type=\"number\" class=\"layui-input value price\"/></td>" +
				"<td class=\"d-operation operationBtn\"><i class=\"layui-icon layui-icon-delete\" onclick='deleteTr(this)'></i></td>" +
			"</tr>";
	$(".sku-view .sku-table tbody").append(tr);
}

function chooseGoods(obj) {
	var goods = $(obj).attr("data-goods");
	goods = JSON.parse(goods);
	$(obj).val(goods.code);
	$(obj).parent().parent().parent().find(".d-photourl .photourl").attr("src",goods.photourl);
	$(obj).parent().parent().parent().find(".d-name .name").val(goods.name);
	$(obj).parent().parent().parent().find(".d-baseprice .baseprice").val(goods.baseprice);
	$(obj).parent().parent().parent().find(".d-saleprice .saleprice").val(goods.price);
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

function catchGoodsData(groupbuyid) {
	let list = [];
	let errorMsg = "";
	$(".sku-view .sku-table tbody tr").each(function(i) {		
		let id = $(this).attr("data-id");
		if (id == null || id == undefined || $.trim(id) == "" || $.trim(id) == 0) {
			id = "";
		}
		let goodsid = $(this).find(".code").attr("data-id");
		if (goodsid == null || goodsid == undefined || $.trim(goodsid) == "" || $.trim(goodsid) == 0) {
			errorMsg = "请选择商品";
			return false;
		}
		let price = $(this).find(".price").val();
		if (price == null || price == undefined || $.trim(price) == "" || $.trim(price) == 0) {
			errorMsg = "请输入拼团价";
			return false;
		}
		
		let data = {
			id : id,
			goodsid: goodsid,
			groupbuyid: groupbuyid,
			price: price
		};
		list.push(data);
	});
	if (errorMsg != null && errorMsg != undefined && $.trim(errorMsg) != "") {
		common.warn(errorMsg);
		return "error";
	}
	return list;
}