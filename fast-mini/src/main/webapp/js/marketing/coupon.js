let coupon = [];
let couponVm;
let btnVm;

common.bindVue = function() {
	console.log("coupon");
	refreshConfig("7001");
	
	btnVm = new Vue({
		el : ".operating",
		data : {
			config: config
        },
        methods : {
            synchronize: function() {
            	synchronize('couponlist',loadData);
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
                        api.load('./coupon/delete','post',data, function(result) {
                            if (result.errcode == 0) {
                            	coupon.splice(deleteIndex);
                                
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
	
    couponVm = new Vue({
        el : ".coupon-data",
        data : {
            coupon: coupon
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,coupon[index]);
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
    api.load(basePath + 'data/list','post',{"table":"coupon"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"coupon","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	coupon.splice(0, coupon.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	coupon.push(data[i]);
        }
    }
}

function showEditBox(idx,data) {
	let editDiv = createElement(data);
	
	let couponid = 0;
	let boxTitle = "<label style='font-weight:600;'>修改</label>";
	if (idx < 0) {
		boxTitle = "<label style='font-weight:600;'>新增</label>";
	} else {
		couponid = data.id;
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
            let suitGoodsData = catchSuitGoodsChecked();
            let suitDepartmentData = catchSuitDepartmentChecked();
            data.suitgoods = escape(suitGoodsData);
            data.suitdepartments = escape(suitDepartmentData);
            
            common.showLoading();
            api.load('./coupon/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                	if (idx < 0) {
                		coupon.push(data);
					} else {
						for (const key in data) {
	                        coupon[idx][key] = data[key];
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
        		type: 'date',
        		format: 'yyyy-MM-dd'
        	});
        	layDate.render({
        		elem: '#endDate',
        		type: 'date',
        		format: 'yyyy-MM-dd'
        	});
        	// 焦点在第一项
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
            
            var contentHeight = $(".layui-layer-page .layui-layer-content").height();
            $(".layui-layer-page .layui-layer-content .layui-tab-content").css("height", contentHeight - 41 + "px");
            
            suitTypeClick();
            timeTypeClick();
            suitGoodsClick();
            suitDepartmentClick();
            checkShowTab();
            
            loadSuitData(couponid);
        }
        
    });
}

function createElement(data) {
	let sync = 0;
//	if (config != null && config != undefined && config.value == 1) {
//		sync = 1;
//	}
	
    let d = {
		id : "",
	    code : "",
	    name : "",
	    amount : "",
	    enableamount : "",
	    limitquantity : "",
	    totalquantity : "",
	    suittype : 1,
	    timetype : 1,
	    begintime : "",
	    endtime : "",
	    effectivetime : "",
	    suitgoodstype: 0,
	    suitdepartmenttype: 0,
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
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">类型</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"suittype\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"suittype\" value=\"1\" title=\"代金券\" "+(d.suittype==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"suittype\" value=\"2\" title=\"折扣券\" "+(d.suittype!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\" follow=\"suittype\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">"+(d.suittype==1?'面值':'折扣（0~1）')+"</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"amount\">"+
				                "<input type=\"number\" value=\""+d.amount+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">启用金额</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"enableamount\">"+
				                "<input type=\"number\" value=\""+d.enableamount+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">发放总数</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"totalquantity\">"+
				                "<input type=\"number\" value=\""+d.totalquantity+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">每人限领</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"limitquantity\">"+
				                "<input type=\"number\" value=\""+d.limitquantity+"\" class=\"layui-input value\" "+(sync == 1 ? "readonly='readonly' disabled='disabled'" : "")+"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">有效期</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"timetype\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"timetype\" value=\"1\" title=\"固定日期\" "+(d.timetype==1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"timetype\" value=\"2\" title=\"有效天数\" "+(d.timetype!=1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\" follow=\"timetype\" "+(d.timetype==1?'':'hidden')+">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">生效日期</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"begintime\">"+
				                "<input type=\"text\" value=\""+common.formatDay(d.begintime)+"\" id=\"beginDate\" class=\"layui-input timechoosed value\" readonly=\"readonly\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\" follow=\"timetype\" "+(d.timetype==1?'':'hidden')+">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">失效日期</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"endtime\">"+
				                "<input type=\"text\" value=\""+common.formatDay(d.endtime)+"\" id=\"endDate\" class=\"layui-input timechoosed value\" readonly=\"readonly\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\" follow=\"timetype\" "+(d.timetype==1?'hidden':'')+">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">有效天数</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"effectivetime\">"+
				                "<input type=\"number\" value=\""+d.effectivetime+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">适用商品</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"suitgoodstype\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"suitgoodstype\" value=\"0\" title=\"全部\" "+(d.suitgoodstype!=1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"suitgoodstype\" value=\"1\" title=\"指定商品\" "+(d.suitgoodstype==1?'checked':'')+" class=\"layui-input value\">" +
				                "</div>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item layui-form\" radio=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">适用门店</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value layui-form-item\" data-field=\"suitdepartmenttype\">"+
				                "<div class=\"layui-input-block\">" +
				                	"<input type=\"radio\" name=\"suitdepartmenttype\" value=\"0\" title=\"全部\" "+(d.suitdepartmenttype!=1?'checked':'')+" class=\"layui-input value\">" +
				                	"<input type=\"radio\" name=\"suitdepartmenttype\" value=\"1\" title=\"指定门店\" "+(d.suitdepartmenttype==1?'checked':'')+" class=\"layui-input value\">" +
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
	
	let tab = "<div class='layui-tab layui-tab-brief' lay-filter='couponTab'>" +
					"<ul class='layui-tab-title'>" +
						"<li class='baseTab layui-this'>基本信息</li>" +
						"<li class='goodsTab' style='display:none;'>适用商品</li>" +
						"<li class='departmentTab' style='display:none;'>适用门店</li>" +
					"</ul>" +
					"<div class='layui-tab-content'>" +
						"<div class='layui-tab-item layui-show'>" +
							element + 
						"</div>" +
						"<div class='layui-tab-item'>" +
							"<div class='suitview suitgoodsview' id='suitgoodsview'></div>" +
						"</div>" +
						"<div class='layui-tab-item'>" +
							"<div class='suitview suitdepartmentview' id='suitdepartmentview'></div>" +
						"</div>" +
					"</div>" +
				"</div>";
	element = tab;
	return element;
}

function suitTypeClick() {
	$(".edit-view input[name='suittype']").eq(0).parent().find(".layui-form-radio").click(function() {
		var val = $(this).prev().val();
		if (val == "1") {
			$(".edit-view .edit-item[follow='suittype'] .edit-title .name").html("面值");
		} else {
			$(".edit-view .edit-item[follow='suittype'] .edit-title .name").html("折扣（0~1）");
		}
		$(".edit-view .edit-item[follow='suittype'] .edit-value .value").val("");
	});
}

function timeTypeClick() {
	$(".edit-view input[name='timetype']").eq(0).parent().find(".layui-form-radio").click(function() {
		var val = $(this).prev().val();
		if (val == "1") {
			$(".edit-view .edit-item[follow='timetype']").removeAttr("hidden","hidden");
			$(".edit-view .edit-item[follow='timetype'] .edit-value[data-field='effectivetime']").parent().attr("hidden","hidden");			
		} else {
			$(".edit-view .edit-item[follow='timetype']").attr("hidden","hidden");
			$(".edit-view .edit-item[follow='timetype'] .edit-value[data-field='effectivetime']").parent().removeAttr("hidden","hidden");			
		}
		$(".edit-view .edit-item[follow='timetype'] .edit-value .value").val("");
	});
}

function suitGoodsClick() {
	$(".edit-view input[name='suitgoodstype']").eq(0).parent().find(".layui-form-radio").click(function() {
		var val = $(this).prev().val();
		if (val == "1") {
			$(".layui-tab-title .goodsTab").css("display","inline-block");
		} else {
			$(".layui-tab-title .goodsTab").css("display","none");
		}
	});
}

function suitDepartmentClick() {
	$(".edit-view input[name='suitdepartmenttype']").eq(0).parent().find(".layui-form-radio").click(function() {
		var val = $(this).prev().val();
		if (val == "1") {
			$(".layui-tab-title .departmentTab").css("display","inline-block");
		} else {
			$(".layui-tab-title .departmentTab").css("display","none");
		}
	});
}

function checkShowTab() {
	var suitgoodstype = $(".edit-view .edit-value[data-field='suitgoodstype'] .layui-form-radioed").prev().val();
	var suitdepartmenttype = $(".edit-view .edit-value[data-field='suitdepartmenttype'] .layui-form-radioed").prev().val();
	if (suitgoodstype == "1") {
		$(".layui-tab-title .goodsTab").css("display","inline-block");
	}
	if (suitdepartmenttype == "1") {
		$(".layui-tab-title .departmentTab").css("display","inline-block");
	}
}

function loadSuitData(id) {
    api.load(basePath + 'coupon/suitgoods','post',{"id":id},function (result) {
        if (result.errcode == 0) {
        	let data = [];
        	let checkedData = [];
        	for (var i = 0; i < result.data.length; i++) {
        		let d = result.data[i];
        		if (d.checked == 1) {
        			checkedData.push(d);
				} else {
					data.push(d);
				}
			}
        	
        	layTransfer.render({
            	elem: '#suitgoodsview',
            	id: 'goodstab',
            	title: ['可选', '已选'],
            	width: 292,
            	height: 558,
            	data: data,
            	value: checkedData,
                parseData: function(res) {
                	return {
                		"value": res.id,
                    	"title": res.title,
                    	"disabled": 0,
                    	"checked": 0
                	}
                }
            });
        } else {
            common.error('数据加载失败');
        }
    });
    api.load(basePath + 'coupon/suitdepartment','post',{"id":id},function (result) {
        if (result.errcode == 0) {
        	let data = [];
        	let checkedData = [];
        	for (var i = 0; i < result.data.length; i++) {
        		let d = result.data[i];
        		if (d.checked == 1) {
        			checkedData.push(d);
				} else {
					data.push(d);
				}
			}
        	
        	layTransfer.render({
            	elem: '#suitdepartmentview',
            	id: 'departmenttab',
            	title: ['可选', '已选'],
            	width: 292,
            	height: 558,
            	data: data,
            	value: checkedData,
                parseData: function(res) {
                	return {
                		"value": res.id,
                    	"title": res.title,
                    	"disabled": 0,
                    	"checked": 0
                	}
                }
            });
        } else {
            common.error('数据加载失败');
        }
    });
}

function catchSuitGoodsChecked() {
	let data = [];
	$("#suitgoodsview .layui-transfer-box[data-index='1'] .layui-transfer-data li").each(function() {
		let id = $(this).find("input").val();
		data.push(id);
	});
	return data;
}

function catchSuitDepartmentChecked() {
	let data = [];
	$("#suitdepartmentview .layui-transfer-box[data-index='1'] .layui-transfer-data li").each(function() {
		let id = $(this).find("input").val();
		data.push(id);
	});
	return data;
}