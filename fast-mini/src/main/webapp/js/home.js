let menu = [];
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
    	$("body").on("click", ".popup input", function () {
    		let region = $(this).attr("region");
    		let goods = $(this).attr("goods");
    		$(".popup .popuped").removeClass("popuped");
    		$(this).addClass("popuped");
    		let url = $(this).data("url");
    		let title = $(this).parent().parent().find(".edit-title .name").html();
    		common.showLoading();
    		api.load(url, 'post', {}, function(result) {
    			if (result.errcode == 0) {
            		let selectOption = optionView(result.data.data,region,goods);;
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
            	        		if (goods == "1") {
            	        			let goods = (layero).find(".layui-layer-content .selected").attr("data-datas");
            	        			$(".popup .popuped").attr("data-goods", goods);
            	        			name = goods.photourl;
    							}
            	        		$(".popup .popuped").attr("data-id",id);
            	        		$(".popup .popuped").val(name).change();
            	        		$(".popup .popuped").removeClass("popuped");
        					}
            	        	layer.close(index);
            	        },
            	        btn2: function() {
							this.cancel();
						},
            	        success: function (layero, index) {
            	        	$(layero).find(".layui-layer-content").addClass("isPopup");
            	        	$(layero).find(".layui-layer-content").append(selectOption);
            	        },
            	        cancel: function(){
            	        	$(".popup .popuped").removeClass("popuped");
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
    		let isPopup = $(this).parents(".layui-layer-content").hasClass("isPopup");
    		if (isPopup) {
    			let id = $(this).data("id");
        		let name = $(this).find(".name").html();
        		let goods = $(this).parent().attr("goods");
        		if (goods == "1") {
        			let goods = $(this).attr("data-datas");
        			$(".popup .popuped").attr("data-goods", goods);
        			name = goods.photourl;
				}
        		$(".popup .popuped").attr("data-id",id);
        		$(".popup .popuped").val(name).change();
        		$(this).parents(".layui-layer").find(".layui-layer-btn .layui-layer-btn1").click();
        		$(".popup .popuped").removeClass("popuped");
    		}
    	});
    	
    	// 弹窗选择（级联）
    	$("body").on("click", ".cascade input", function () {
    		let grade = $(this).attr("data-grade");
    		let region = $(this).attr("region");
    		let goods = $(this).attr("goods");
    		let title = "大类";
    		if (grade == 2) {
    			title = "中类";
			} else if (grade == 3) {
				title = "小类";
			}
    		if (region == 1) {
    			title = "省份";
        		if (grade == 2) {
        			title = "城市";
    			} else if (grade == 3) {
    				title = "区县";
    			}
			}
    		let parentMsg = checkParentChoose($(this));
    		if (parentMsg != null && parentMsg != undefined && $.trim(parentMsg) != "") {
				common.warn(parentMsg);
				return false;
			}
    		$(".cascade .cascadeed").removeClass("cascadeed");
    		$(this).addClass("cascadeed");
    		let url = $(this).attr("data-url");
    		common.showLoading();
    		api.load(url, 'post', {}, function(result) {
    			if (result.errcode == 0) {
            		let selectOption = optionView(result.data.data,region,goods);;
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
            	        		$(".cascade .cascadeed").attr("data-id",id);
            	        		$(".cascade .cascadeed").val(name).change();
            	        		checkCascade($(".cascade .cascadeed"));
        					}
            	        	$(".cascade .cascadeed").removeClass("cascadeed");
            	        	changeCascadeUrl();
            	        	layer.close(index);
            	        },
            	        btn2: function() {
							this.cancel();
						},
            	        success: function (layero, index) {
            	        	$(layero).find(".layui-layer-content").addClass("isCascade");
            	        	$(layero).find(".layui-layer-content").append(selectOption);
            	        },
            	        cancel: function(){
            	        	$(".cascade .cascadeed").removeClass("cascadeed");
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
    		let isCascade = $(this).parents(".layui-layer-content").hasClass("isCascade");
    		if (isCascade) {
    			let id = $(this).data("id");
        		let name = $(this).find(".name").html();
        		$(".cascade .cascadeed").attr("data-id",id);
        		$(".cascade .cascadeed").val(name).change();
        		checkCascade($(".cascade .cascadeed"));
        		$(this).parents(".layui-layer").find(".layui-layer-btn .layui-layer-btn1").click();
        		$(".cascade .cascadeed").removeClass("cascadeed");
        		changeCascadeUrl();
			}
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
    	
    	api.load(basePath + 'home/menu','post',{},function (result) {
        	if (result.errcode == 0) {
        		let data = result.data;
        		for (let i = 0; i < data.length; i++) {
        			menu.push(data[i]);
                }
        	} else {
                common.error('数据加载失败');
            }
        	setTimeout(() => {
            	layuiElement.render();
			}, 100);
        });
    }
}

function checkCascade(obj) {
	let grade = $(obj).attr("data-grade");
	$(obj).parents(".cascade-value").find(".edit-value").each(function() {
		let thisGrade = $(this).find(".value").attr("data-grade");
		if (thisGrade > grade) {
			$(this).find(".value").attr("data-id", "");
			$(this).find(".value").val("").change();
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
			if (id == null || id == undefined || $.trim(id) == "" || $.trim(id) == "0") {
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
	$(".cascade input").each(function() {
		let grade = $(this).attr("data-grade");
		if (grade == 1) {
			bigCategoryID = $(this).attr("data-id");
		} else if (grade == 2) {
			middleCategoryID = $(this).attr("data-id");
		} else if (grade == 3) {
			smallCategoryID = $(this).attr("data-id");
		}
	});
	
	$(".cascade input").each(function() {
		let grade = $(this).attr("data-grade");
		let region = $(this).attr("region");
		if (grade == 2) {
			if (bigCategoryID != null && bigCategoryID != undefined && $.trim(bigCategoryID) != "") {
				$(this).attr("data-url","./goodscategory/list?grade=2&pagesize=100&parentid="+$.trim(bigCategoryID));
				if (region == 1) {
					$(this).attr("data-url","./region/list?grade=2&pagesize=100&parentid="+$.trim(bigCategoryID));
				}
			} else {
				$(this).attr("data-url","./goodscategory/list?grade=2&pagesize=100");
				if (region == 1) {
					$(this).attr("data-url","./region/list?grade=2&pagesize=100");
				}
			}
		} else if (grade == 3) {
			if (middleCategoryID != null && middleCategoryID != undefined && $.trim(middleCategoryID) != "") {
				$(this).attr("data-url","./goodscategory/list?grade=3&pagesize=100&parentid="+$.trim(middleCategoryID));
				if (region == 1) {
					$(this).attr("data-url","./region/list?grade=3&pagesize=100&parentid="+$.trim(middleCategoryID));
				}				
			} else {
				$(this).attr("data-url","./goodscategory/list?grade=3&pagesize=100");
				if (region == 1) {
					$(this).attr("data-url","./region/list?grade=3&pagesize=100");
				}
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

function optionView(data,region,goods) {
	let view = "<div class=\"popup-view\">" +
					"<div class=\"popup-box\" goods='"+goods+"' region='"+region+"'>";
	for (var i = 0; i < data.length; i++) {
		view += "<div class=\"popup-data\" data-id=\""+data[i].id+"\" data-datas='"+JSON.stringify(data[i])+"'>";
		if (region == 1) {
			view += "<span class=\"code\">"+data[i].id+"</span>";
		} else {
			view += "<span class=\"code\">"+data[i].code+"</span>";
		}
		view += "<span class=\"name\">"+data[i].name+"</span>" +
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
		count = page.totalCount;
		pageNow = page.pageNo;
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

/**
 * 登出
 * @returns
 */
function logout() {
	layer.confirm('确定退出？', {
		btn: ['确定','取消'],
		btn1 : function(index, layero) {
			common.showLoading();
		    api.load(basePath + 'logout','post',{},function (result) {
		    	window.location.reload();
		    });
		}
	});
}