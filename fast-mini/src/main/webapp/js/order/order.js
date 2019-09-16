let order = [];
let orderVm;

common.bindVue = function() {
	console.log('order');
    orderVm = new Vue({
        el : ".orderPage",
        data : {
            order: order
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,order[index]);
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
    api.load(basePath + 'order/list','post',{},function (result) {
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
	api.load(basePath + 'order/list','post',{"pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	order.splice(0, order.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	order.push(data[i]);
        }
    }
}

function createElement(data) {
	let d = {
		id : '',
		no : '',
		status : 1,
		vip : '',
		vipphone : '',
		kind : 1,
		createtime: '',
		paytime: '',
		deliverertime: '',
		deliverer: '',
		deliverytype: 1,
		receiver: '',
		receiverphone: '',
		receiverprovince: '',
		receivercity: '',
		receivercounty: '',
		receiveraddress: '',
		amount: 0,
		freight: 0,
		paymoney: 0,
		deposit: 0,
		point: 0,
		pointmoney: 0,
		couponmoney: 0,
		discountmoney: 0,
		logistics: '',
		logisticsno: '',
		marketing: '',
		delivererdepartment: '',
		remark: '',
		memo : '',
		details: []
	};
    if (data != null && data != undefined && data != "") {
    	for (const key in data) {
            if (d.hasOwnProperty(key)) {
            	d[key] = data[key];
            }
        }
    }
    let tr = "";
    for (var i = 0; i < d.details.length; i++) {
		tr = tr + "<tr>" +
					"<td>" +
						"<div class='goodsimage'><img src='"+d.details[i].photourl+"' onerror=\"defaultImg(this)\"/></div>" +
						"<span>" +
							"<label class='goodscode'>"+d.details[i].code+"</label> " +
							"<label class='goodsname'>"+d.details[i].name+"</label> " +
							"<label class='goodscolor'>"+d.details[i].color+"</label> " +
							"<label class='goodspattern'>"+d.details[i].pattern+"</label> " +
							"<label class='goodssize'>"+d.details[i].size+"</label>" +
						"</span>" +
					"</td>" +
					"<td><span><label class='goodsprice'>"+d.details[i].price+"</label></span></td>" +
					"<td><span><label class='goodsquantity'>"+d.details[i].quantity+"</label></span></td>" +
					"<td><span><label class='goodsamount'>"+d.details[i].amount+"</label></span></td>" +
				"</tr>"
	}
    
	let element = "<div class=\"edit-view\">"+
				    "<div class=\"edit-box\">"+
				        "<div class=\"edit-item\" style='display:none;'>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">"+d.id+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">单号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.no+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">订单状态</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+(d.status==0?'已取消':(d.status==1?'待付款':(d.status==2?'待发货':(d.status==3?'待收货':(d.status==4?'已完成':'')))))+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">性质</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+(d.kind==2?'兑换':(d.kind==3?'拼团':(d.kind==4?'秒杀':'销售')))+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">会员</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+(d.vip + ' ' + d.vipphone)+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">配送方式</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+(d.deliverytype==2?'自提':'快递')+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item address\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">收货地址</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+(d.receiver+' '+d.receiverphone + ' '+d.receiverprovince+d.receivercity+d.receivercounty+d.receiveraddress)+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">下单时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+common.formatDate(d.createtime)+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">付款时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+common.formatDate(d.paytime)+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">发货门店</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.delivererdepartment+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">发货人</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.deliverer+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">发货时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+common.formatDate(d.deliverertime)+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">物流公司</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.logistics+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">物流单号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.logisticsno+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">活动名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.marketing+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item memo\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">买家备注</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\">"+d.remark+"</span>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item memo\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">卖家备注</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				            	"<span class=\"value\"></span>"+
				            "</div>"+
				        "</div>"+
				    "</div>" +
				    "<div class=\"goods-table\">" +
					    "<table class=\"layui-table\">" +
							"<thead>" +
								"<tr>" +
									"<th class=\"goods\">商品</th>" +
									"<th class=\"price\">售价</th>" +
									"<th class=\"quantity\">数量</th>" +
									"<th class=\"amount\">金额</th>" +
								"</tr>" +
							"</thead>" +
							"<tbody>" +
								tr +
							"</tbody>" +
						"</table>" +
				    "</div>" +
				    "<div class=\"goods-pay\">"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">商品总价</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.amount+"</span>"+
				            "</div>"+
				        "</div>" +
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">-优惠</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.discountmoney+"</span>"+
				            "</div>"+
				        "</div>" +
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">+运费</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.freight+"</span>"+
				            "</div>"+
				        "</div>" +
				        "<div class=\"edit-item bold\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">应收总额</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.amount+"</span>"+
				            "</div>"+
				        "</div>" +
				    "</div>"+
				    "<div class=\"edit-box pay-info\">"+
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">微信支付</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.paymoney+"</span>"+
				            "</div>"+
				        "</div>" +
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">储值支付</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.deposit+"</span>"+
				            "</div>"+
				        "</div>" +
				        "<div class=\"edit-item\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">积分支付</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.pointmoney+'（'+d.point+'）'+"</span>"+
				            "</div>"+
				        "</div>" +
				        "<div class=\"edit-item bold\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">优惠券支付</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\">"+
				                "<span class=\"value key\">¥"+d.couponmoney+"</span>"+
				            "</div>"+
				        "</div>" +
				    "</div>"+
				"</div>";
	return element;
}

function showEditBox(idx,data) {
	let editDiv = createElement(data);
	
	let boxTitle = "<label style='font-weight:600;'>明细</label>";
	
	var btnOperation = ['关闭'];
	if (data.status == 2 && data.distributionflag != 1) {
		btnOperation = ['配货','关闭'];
	} else if (data.status == 2 && data.distributionflag == 1) {
		btnOperation = ['发货','关闭'];
	}
    
    layer.open({
        type: 1,
        title: boxTitle,
        content: editDiv,
        area: ['1100px', '800px'],
        btn: btnOperation,
        btn1: function (index, layero) {
            if (data.status == 2 && data.distributionflag != 1) {
            	chooseDelivererDepartment(data.id, index);
			} else if (data.status == 2 && data.distributionflag == 1) {
				chooseLogistics(data.id, index);
            	return false;
			} else {
				layer.close(index);
			}
        },
        success: function () {
        	// 重新刷新form
        	layuiForm.render();
        }
        
    });
}

function chooseLogistics(orderid, idx) {
	var logisticsView = "<div class=\"edit-view\">"+
						    "<div class=\"edit-box\">"+
							    "<div class=\"edit-item\" need=\"1\" key=\"1\" style='width: 100%;display:none;'>"+
						            "<div class=\"edit-title\">"+
						                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
						            "</div>"+
						            "<div class=\"edit-value\" data-field=\"id\" style=\"display: block;\">"+
						                "<input type=\"text\" value=\""+orderid+"\" class=\"layui-input value\"/>"+
						            "</div>"+
						        "</div>"+
							    "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\" style=\"width: 100%;\">"+
						            "<div class=\"edit-title\" style=\"text-align:left;\">"+
						                "<span class=\"title\"><label class=\"name\">物流公司</label>：</span>"+
						            "</div>"+
						            "<div class=\"edit-value\" data-field=\"logisticsid\" style=\"display: block;\">"+
						                "<input type=\"text\" data-id=\"\" value=\"\" " +
						                		"data-url=\"./data/page?table=logistics\" class=\"layui-input value\" readonly=\"readonly\"/>" +
						                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
						            "</div>"+
						        "</div>"+
							    "<div class=\"edit-item\" style=\"width: 100%;\">"+
							        "<div class=\"edit-title\" style=\"text-align:left;\">"+
							            "<span class=\"title\"><label class=\"name\">物流单号</label>：</span>"+
							        "</div>"+
							        "<div class=\"edit-value\" data-field=\"logisticsno\" style=\"display: block;\">"+
						                "<input type=\"text\" value=\"\" class=\"layui-input value\"/>"+
						            "</div>"+
							    "</div>" +
							 "</div>" +
						"</div>";
	layer.open({
        type: 1,
        title: "物流",
        content: logisticsView,
        area: ['500px', '350px'],
        btn: ["确定","关闭"],
        btn1: function (index, layero) {
        	let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./order/deliver','post',data, function(result) {
                if (result.errcode == 0) {
                	layer.close(index);
                	layer.close(idx);
                	common.tips(result.message);
                	loadData();
                } else {
                    common.error(result.message);
                }
                common.closeLoading();
            });
            
        },
        success: function () {
        	// 重新刷新form
        	layuiForm.render();
        }
        
    });
}

function chooseDelivererDepartment(orderid, idx) {
	var delivererDepartmentView = "<div class=\"edit-view\">"+
						    "<div class=\"edit-box\">"+
							    "<div class=\"edit-item\" need=\"1\" key=\"1\" style='width: 100%;display:none;'>"+
						            "<div class=\"edit-title\">"+
						                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
						            "</div>"+
						            "<div class=\"edit-value\" data-field=\"id\" style=\"display: block;\">"+
						                "<input type=\"text\" value=\""+orderid+"\" class=\"layui-input value\"/>"+
						            "</div>"+
						        "</div>"+
							    "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\" style=\"width: 100%;\">"+
						            "<div class=\"edit-title\" style=\"text-align:left;\">"+
						                "<span class=\"title\"><label class=\"name\">发货门店</label>：</span>"+
						            "</div>"+
						            "<div class=\"edit-value\" data-field=\"delivererdepartmentid\" style=\"display: block;\">"+
						                "<input type=\"text\" data-id=\"\" value=\"\" " +
						                		"data-url=\"./data/page?table=department\" class=\"layui-input value\" readonly=\"readonly\"/>" +
						                "<i class=\"layui-icon layui-icon-layer\"> </i>"+
						            "</div>"+
						        "</div>"+
							 "</div>" +
						"</div>";
	layer.open({
        type: 1,
        title: "发货门店",
        content: delivererDepartmentView,
        area: ['500px', '250px'],
        btn: ["确定","关闭"],
        btn1: function (index, layero) {
        	let data = catchBoxValue();
            if (data == '') {
                return;
            }
            common.showLoading();
            api.load('./order/distribution','post',data, function(result) {
                if (result.errcode == 0) {
                	layer.close(index);
                	layer.close(idx);
                	common.tips(result.message);
                	loadData();
                } else {
                    common.error(result.message);
                }
                common.closeLoading();
            });
            
        },
        success: function () {
        	// 重新刷新form
        	layuiForm.render();
        }
        
    });
}