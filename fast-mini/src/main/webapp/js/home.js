let menu = [
    {
        name: '系统管理 ',
        link: '',
        sub: [
            {
                name: '参数',
                link: 'config'
            },
            {
                name: '角色',
                link: 'role'
            },
            {
                name: '用户',
                link: 'user'
            }
        ]
    },
    {
        name: '资料管理 ',
        link: '',
        sub: [
            {
                name: '门店',
                link: 'department'
            },
            {
                name: '员工 ',
                link: 'employee'
            }
        ]
    },
    {
    	name: '商品管理 ',
        link: '',
        sub: [
        	{
                name: '颜色',
                link: 'color'
            },
            {
                name: '版型',
                link: 'pattern'
            },
            {
                name: '尺码',
                link: 'size'
            },
            {
                name: '品牌',
                link: 'brand'
            },
            {
                name: '分类',
                link: 'category'
            },
            {
                name: '分组',
                link: 'grouping'
            },
            {
                name: '商品',
                link: 'goods'
            }
        ]
    },
    {
        name: '会员管理 ',
        link: '',
        sub: [
            {
                name: '等级',
                link: 'viptype'
            },
            {
                name: '会员',
                link: 'vip'
            },
            {
                name: '优惠券',
                link: 'coupon'
            }
        ]
    },
    {
        name: '应用管理 ',
        link: '',
        sub: [
            {
                name: '公众号 ',
                link: 'publicplatform'
            },
            {
                name: '小程序',
                link: 'miniprogram'
            },
            {
                name: '微页面',
                link: 'micropage'
            }
        ]
    }
]

let menuVm;
document.onreadystatechange = function() {
    if(document.readyState == "interactive"){
        menuVm = new Vue({
			el : ".layui-nav-tree",
			data : {
                menu: menu
            },
            methods : {
                loadPage: function(url) {
                    if (url.length < 1) {
                        return;
                    }
                    if(url.indexOf('http') < 0) {
                        url = $("base").attr("href") + url;
                    }
                    try {
                        common.showLoading();
                        $(".layui-body").load(url, {},function(){
                            common.closeLoading();
                            common.bindVue();
                        });
                    } catch (error) {
                        console.log(error);
                    }
                }
            }
		});
    }
    if(document.readyState == "complete"){
    	// 表格选中
    	$("body").on("click", ".layui-table-view .layui-table-box .layui-table-body table tr", function () {
    		let isSelected = $(this).hasClass("selected");
    		$(".layui-table-view .layui-table-box .layui-table-body table .selected").removeClass("selected");
    		if (!isSelected) {
    			$(this).addClass("selected");
			}
    	});
    	
    	// 弹窗选择
    	$("body").on("click", ".edit-view .edit-box .popup input", function () {
    		$(".edit-view .edit-box .popup .popuped").removeClass("popuped");
    		$(this).addClass("popuped");
    		let url = $(this).data("url");
    		let title = $(this).parent().parent().find(".edit-title .title .name").html();
    		common.showLoading();
    		api.load(url, 'post', {}, function(result) {
    			if (result.errcode == 0) {
            		let selectOption = optionView(result.data.records);;
            		layer.open({
            	        type: 1,
            	        title: "<label style='font-weight:600;'>"+title+"</label>",
            	        //content: selectOption,
            	        area: ['500px', '400px'],
            	        btn: ['确定','取消'],
            	        btn1: function (index, layero) {
            	        	let id = $(layero).find(".layui-layer-content .selected").data("id");
            	        	let name = $(layero).find(".layui-layer-content .selected .name").html();
            	        	if (id != null && id != undefined && $.trim(id) != "") {
            	        		$(".edit-view .edit-box .popup .popuped").attr("data-id",id);
            	        		$(".edit-view .edit-box .popup .popuped").val(name);
            	        		$(".edit-view .edit-box .popup .popuped").removeClass("popuped");
        					}
            	        	layer.close(index);
            	        },
            	        success: function (layero, index) {
            	        	$(layero).find(".layui-layer-content").append(selectOption);
            	        }
            		});
    			} else {
    				common.error(result.message);
				}
    			
        		common.closeLoading();
    		});
    	});
    	
    	// 弹窗数据选中
    	$("body").on("click", ".popup-view .popup-box .popup-data", function () {
    		let isSelected = $(this).hasClass("selected");
    		$(this).parent().find(".selected").removeClass("selected");
    		if (!isSelected) {
    			$(this).addClass("selected");
			}
    	});
    	
    	// 弹窗双击选择数据
    	$("body").on("dblclick", ".popup-view .popup-box .popup-data", function () {
    		let id = $(this).data("id");
    		let name = $(this).find(".name").html();
    		$(this).parents(".layui-layer").find(".layui-layer-btn .layui-layer-btn1").click();
    		$(".edit-view .edit-box .popup .popuped").attr("data-id",id);
    		$(".edit-view .edit-box .popup .popuped").val(name);
    		$(".edit-view .edit-box .popup .popuped").removeClass("popuped");
    	});
    	
    	// 弹窗选择（级联）
    	$("body").on("click", ".edit-view .edit-box .cascade input", function () {
    		let grade = $(this).data("grade");
    		let title = "大类";
    		if (grade == 2) {
    			title = "中类";
			} else if (grade == 3) {
				title = "小类";
			}
    		let parentMsg = checkParentChoose($(this));
    		if (parentMsg != null && parentMsg != undefined && $.trim(parentMsg) != "") {
				common.warn(parentMsg);
				return false;
			}
    		$(".edit-view .edit-box .cascade .cascadeed").removeClass("cascadeed");
    		$(this).addClass("cascadeed");
    		let url = $(this).attr("data-url");
    		common.showLoading();
    		api.load(url, 'post', {}, function(result) {
    			if (result.errcode == 0) {
            		let selectOption = optionView(result.data.records);;
            		layer.open({
            	        type: 1,
            	        title: "<label style='font-weight:600;'>"+title+"</label>",
            	        //content: selectOption,
            	        area: ['500px', '400px'],
            	        btn: ['确定','取消'],
            	        btn1: function (index, layero) {
            	        	let id = $(layero).find(".layui-layer-content .selected").attr("data-id");
            	        	let name = $(layero).find(".layui-layer-content .selected .name").html();
            	        	if (id != null && id != undefined && $.trim(id) != "") {
            	        		$(".edit-view .edit-box .cascade .cascadeed").attr("data-id",id);
            	        		$(".edit-view .edit-box .cascade .cascadeed").val(name);
            	        		checkCascade($(".edit-view .edit-box .cascade .cascadeed"));            	        		
            	        		$(".edit-view .edit-box .cascade .cascadeed").removeClass("cascadeed");
        					}
            	        	changeCascadeUrl();
            	        	layer.close(index);
            	        },
            	        success: function (layero, index) {
            	        	$(layero).find(".layui-layer-content").append(selectOption);
            	        }
            		});
    			} else {
    				common.error(result.message);
				}
    			
        		common.closeLoading();
    		});
    	});
    	
    	// 弹窗双击选择数据（级联）
    	$("body").on("dblclick", ".popup-view .popup-box .popup-data", function () {
    		let id = $(this).data("id");
    		let name = $(this).find(".name").html();
    		$(this).parents(".layui-layer").find(".layui-layer-btn .layui-layer-btn1").click();
    		$(".edit-view .edit-box .cascade .cascadeed").attr("data-id",id);
    		$(".edit-view .edit-box .cascade .cascadeed").val(name);
    		checkCascade($(".edit-view .edit-box .cascade .cascadeed"));
    		$(".edit-view .edit-box .cascade .cascadeed").removeClass("cascadeed");
    		changeCascadeUrl();
    	});
    	
    	// 图片加载失败显示默认图
    	$("body").on("error", "img", function () {
    		if ($(this).hasClass("circular")) {
    			$(this).attr("src", "images/head.png");
			} else {
				$(this).attr("src", "images/default.png");
			}
    	});
    	$("img").bind("error",function () {
    		console.log("img error");
    	});
    }
}

function checkCascade(obj) {
	let grade = $(obj).attr("data-grade");
	$(obj).parents(".cascade-value").find(".edit-value").each(function() {
		let thisGrade = $(this).find(".value").attr("data-grade");
		if (thisGrade > grade) {
			$(this).find(".value").attr("data-id", "");
			$(this).find(".value").val("");
		}
	});
}

function checkParentChoose(obj) {
	var msg = "";
	let grade = $(obj).data("grade");
	$(obj).parents(".cascade-value").find(".edit-value").each(function() {
		let thisGrade = $(this).find(".value").attr("data-grade");
		if (thisGrade < grade) {
			var id = $(this).find(".value").attr("data-id");
			if (id == null || id == undefined || $.trim(id) == "") {
				if (thisGrade == 1) {
					msg = "请先选择大类";
				} else if (thisGrade == 2) {
					msg = "请先选择中类";
				}
				return false;
			}
		}
	});
	return msg;
}

function changeCascadeUrl() {
	let bigCategoryID = "";
	let middleCategoryID = "";
	let smallCategoryID = "";
	$(".edit-view .edit-box .cascade input").each(function() {
		let grade = $(this).attr("data-grade");
		if (grade == 1) {
			bigCategoryID = $(this).attr("data-id");
		} else if (grade == 2) {
			middleCategoryID = $(this).attr("data-id");
		} else if (grade == 3) {
			smallCategoryID = $(this).attr("data-id");
		}
	});
	
	$(".edit-view .edit-box .cascade input").each(function() {
		let grade = $(this).attr("data-grade");
		if (grade == 2) {
			if (bigCategoryID != null && bigCategoryID != undefined && $.trim(bigCategoryID) != "") {
				$(this).attr("data-url","./goodscategory/list?grade=2&parentid="+$.trim(bigCategoryID));
			} else {
				$(this).attr("data-url","./goodscategory/list?grade=2");
			}
		} else if (grade == 3) {
			if (middleCategoryID != null && middleCategoryID != undefined && $.trim(middleCategoryID) != "") {
				$(this).attr("data-url","./goodscategory/list?grade=3&parentid="+$.trim(middleCategoryID));
			} else {
				$(this).attr("data-url","./goodscategory/list?grade=3");
			}
		}
	});
}

function defaultImg(obj) {
	if ($(obj).hasClass("circular")) {
		$(obj).attr("src", "images/head.png");
	} else {
		$(obj).attr("src", "images/default.png");
	}
}

function optionView(data) {
	let view = "<div class=\"popup-view\">" +
					"<div class=\"popup-box\">";
	for (var i = 0; i < data.length; i++) {
		view += "<div class=\"popup-data\" data-id=\""+data[i].id+"\">" +
					"<span class=\"code\">"+data[i].code+"</span>" +
					"<span class=\"name\">"+data[i].name+"</span>" +
				"</div>";
	}				
	view += "</div></div>";
	return view;
}

let dataPage;
function pageConfig(page, fn) {
	let count = 1;
	let limit = 15;
	let pageNow = 1;
	if (page != null && page != undefined) {
		count = page.rowCount;
		pageNow = page.pageNow;
	}
	if (pageNow == 1) {
		dataPage = layPage;
		dataPage.render({
			elem : 'layPage',
			count : count,
			limit : limit,
			jump : function(obj, first) {
				if(!first){
					if (!!fn) {
						try {
							let func = eval(fn);
							if (func && typeof (func) == "function") {
								func(obj.curr);
							}
						} catch (e) {
							console.log(e);
						}
					}
				}
			}
		});
	}
}

// 定义一个标志位
let isShow = true;
$('.kit-side-fold').click(function () {
    // 选择出所有的span，并判断是不是hidden
    $('.layui-nav-item span').each(function () {
        if ($(this).is(':hidden')) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
    //判断isshow的状态
    if (isShow) {
        $('.layui-side.layui-bg-black').width(60); //设置宽度
        $('.kit-side-fold i').css('margin-right', '70%');  //修改图标的位置
        //将footer和body的宽度修改
        $('.layui-body').css('left', 60 + 'px');
        $('.layui-footer').css('left', 60 + 'px');
        //将二级导航栏隐藏
        $('dd span').each(function () {
            $(this).hide();
        });
        //修改标志位
        isShow = false;
    } else {
        $('.layui-side.layui-bg-black').width(200);
        $('.kit-side-fold i').css('margin-right', '10%');
        $('.layui-body').css('left', 200 + 'px');
        $('.layui-footer').css('left', 200 + 'px');
        $('dd span').each(function () {
            $(this).show();
        });
        isShow = true;
    }
});