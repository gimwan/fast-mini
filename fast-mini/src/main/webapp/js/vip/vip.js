let vip = [];
let vipVm;

common.bindVue = function() {
    vipVm = new Vue({
        el : ".vipPage",
        data : {
            vip: vip
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    showEditBox(index,vip[index]);
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
    api.load(basePath + 'data/list','post',{"table":"vip"},function (result) {
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
	api.load(basePath + 'data/list','post',{"table":"vip","pageno":pageno},function (result) {
		let pageView = result.data;
		setData(pageView);
		common.closeLoading();
	});
}

function setData(pageView) {
	let data = pageView.data;
	vip.splice(0, vip.length);
	if (data != null) {
        for (let i = 0; i < data.length; i++) {
        	vip.push(data[i]);
        }
    }
}

function showEditBox(idx,data) {
	let editDiv = createElement(data);
	
	let boxTitle = "<label style='font-weight:600;'>详情</label>";
    
    layer.open({
        type: 1,
        title: boxTitle,
        content: editDiv,
        area: ['1000px', '600px'],
        btn: ['关闭'],
        success: function () {
        	// 重新刷新form
        	layuiForm.render();
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
		sex : 0,
		mobilephone : "",
		photourl : "",
		birthday : "",
		province : "",
		provinceid : "",
		city : "",
		cityid : "",
		county : "",
		countyid : "",
		department : "",
		departmentid : "",
		type : "",
		typeid : "",
		recommender : "",
		recommenderid : "",
		source : "",
		registtime : "",
		deposit : 0,
		point : 0,
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
				        "<div class=\"edit-item\" need=\"0\" key=\"1\" style='display:none;'>"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"id\">"+
				                "<input type=\"text\" value=\""+d.id+"\" class=\"layui-input value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" image=\"1\" need=\"0\" key=\"0\">"+
				            "<div class=\"edit-value\" data-field=\"photourl\">"+
				                "<img src=\""+d.photourl+"\" onerror=\"defaultImg(this)\" class=\"layui-circle-img circular value\"/>"+
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">编号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"code\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.code+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">名称</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"name\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.name+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">手机号</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"mobilephone\">"+
				            	"<span class=\"value\" disabled=\"disabled\" >"+d.mobilephone+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">等级</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"typeid\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.type+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" select=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">性别</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"sex\">"+
				            	"<span class=\"value\" disabled=\"disabled\" >"+(d.sex==1?'男':(d.sex==2?'女':'未知'))+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item popup\" popup=\"1\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">所属门店</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"departmentid\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.department+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">积分</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"point\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.point+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">储值</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"deposit\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.deposit+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">生日</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"birthday\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+common.formatDay(d.birthday)+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">地区</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"birthday\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.province+' '+d.city+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">注册时间</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"registtime\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+common.formatDay(d.registtime)+"</span>" +
				            "</div>"+
				        "</div>"+
				        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">推荐人</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"recommender\">"+
				                "<span class=\"value\" disabled=\"disabled\" >"+d.recommender+"</span>" +
				            "</div>"+
				        "</div>"+
				        /*"<div class=\"edit-item\" textarea=\"1\" key=\"0\">"+
				            "<div class=\"edit-title\">"+
				                "<span class=\"title\"><label class=\"name\">备注</label>：</span>"+
				            "</div>"+
				            "<div class=\"edit-value\" data-field=\"memo\">"+
				            	"<span class=\"value\" disabled=\"disabled\" >"+d.memo+"</span>" +
				            "</div>"+
				        "</div>"+*/
				    "</div>"+
				"</div>";
	return element;
}
