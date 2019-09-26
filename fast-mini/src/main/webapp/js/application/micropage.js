let item = [
	{
		name: "广告图片",
		image: "./images/search.png",
		target: "ad"
	},
	{
		name: "商品搜索",
		image: "./images/search.png",
		target: "search"
	},
	{
		name: "导航图片",
		image: "./images/navigation.png",
		target: "navigation"
	},
	{
		name: "公告",
		image: "./images/notice.png",
		target: "notice"
	},
	{
		name: "栏目标题",
		image: "./images/columntitle.png",
		target: "columntitle"
	},
	{
		name: "辅助空白",
		image: "./images/blank.png",
		target: "blank"
	},
	{
		name: "商品分组",
		image: "./images/group.png",
		target: "group"
	},
	{
		name: "商品分类",
		image: "./images/group.png",
		target: "category"
	},
	{
		name: "商品列表",
		image: "./images/list.png",
		target: "list"
	},
	{
		name: "优惠券",
		image: "./images/coupon.png",
		target: "coupon"
	}
]

let microPageItemVm;
let microPageVm;
let microPageSetVm;
let microPageEditVm;
let uploadInst;
let microPage;
let setData;
//let editData;

common.bindVue = function() {
	console.log('micropage');
	microPageItemVm = new Vue({
        el : ".itemBox",
        data : {
        	item: item
        },
        methods : {
        	addItem: function(event) {
        		let index = $(event.currentTarget).attr("data-index");
                addItemToPhone(Number(index)+1);
			}
        }
    });
	
	loadSetData();
	
    // 重新刷新form
    layuiForm.render();
    
    // 界面元素选中
    chooseView();
    
    // 删除图片显示
    showDeleteIcon();
}

function loadSetData() {
	let pageid = $(".microPage #pageid").val();
    common.showLoading();
    api.load(basePath + 'micropage/data','post',{"pageid":pageid},function (result) {
        if (result.errcode == 0) {
        	let pageData = result.data;
        	if (pageData != null) {
        		microPage = pageData.micropage;
        		setData = pageData.setdata;
            	microPageSetVm = new Vue({
                    el : ".phoneBox",
                    data : {
                    	setdata: setData
                    }
                });
            	microPageEditVm = new Vue({
                    el : ".editBox",
                    data : {
                    	editdata: setData
                    },
                    methods : {
                    	addItem : function(event) {
							let kind = $(event.target).attr("data-kind");
							let index = $(event.target).attr("data-index");
							let id = "add" + new Date().getTime();
							let item = {
				                id: id,
				                micropagesetid: setData[index].id,
				                showindex: 1,
				                first: 0,
				                second: 0,
				                third: 0,
				                text: '',
				                targetpath: '',
				                photourl: '',
				                type: (kind==9?1:0),
				                grouping: '',
				                category: '',
				                goodsname: (kind==9?'商品名称':''),
				                price: (kind==9?888.88:0),
				                point: 0,
				                kind: 1,
				                list: [],
				                categoryone: '',
				                categorytwo: '',
				                categorythree: ''
				            };
							item.showindex = setData[index].detail.length;
							
							// 广告
							if (kind == 1) {
								if (setData[index].detail.length < 5) {
									setData[index].detail.push(item);
								}
							}
							// 导航
							else if (kind == 3) {
								if (setData[index].detail.length < 4) {
									setData[index].detail.push(item);
								}
							}
							// 分组
							else if (kind == 7) {
								if (setData[index].detail.length < 4) {
									setData[index].detail.push(item);
								}
							}
							// 分类
							else if (kind == 8) {
								if (setData[index].detail.length < 4) {
									setData[index].detail.push(item);
								}
							}
							// 商品
							else if (kind == 9) {
								if (setData[index].detail.length < 10) {
									setData[index].detail.push(item);
								}
							} else if (kind == 10) {
								if (setData[index].detail.length < 4) {
									setData[index].detail.push(item);
								}
							}
							
							configAssembly();
							/*setTimeout(() => {
								layuiForm.render();
								configUploadInst();
							}, 300);*/
						}
                    }
                });
			}
        	setTimeout(() => {
        		$(".middlePanel .phoneBox .editView").css("display","inline-block");
        		$(".rightPanel .editBox").css("display","inline-block");
			}, 300);
        } else {
            common.error('数据加载失败');
        }
        
        configCarousel();
        configUploadInst();
        layuiForm.render();
        
        common.closeLoading();
    });
}

function configAssembly() {
	setTimeout(() => {
		configCarousel();
	    configUploadInst();
	    layuiForm.render();
	}, 500);
}

function configUploadInst() {
	$(".rightPanel .layui-upload-drag").each(function() {
		var id = $(this).attr("id");
		layuiUpload.render({
		    elem: '#'+id,
		    url: './upload/field/image',
		    size: 200,
		    multiple: false,
		    done: function(res, index, upload){
		    	// 上传完毕回调
		    	var item = this.item;
		    	var index = $(item).parents(".uploadField").attr("data-index");
		    	var detailIndex = $(item).parents(".uploadField").attr("data-idx");
		    	setData[index].detail[detailIndex].photourl = res.data;
		    	configAssembly();
		    },
		    error: function(res, index){
		    	// 请求异常回调
		    	console.log(res);
		    	console.log(index);
		    	configAssembly();
		    }
	    });
	});
}

var carousel = {};
function configCarousel() {
	$(".middlePanel .editView .layui-carousel").each(function() {
		var length = $(this).find(".carouselBox .layui-this").length;
		var id = $(this).attr("id");		
		var options = {
			elem : '#'+id,
			arrow : 'none',
			width : '100%',
			//height : 'auto',
			indicator : 'inside'
		};
		var maxHeight = 0;
		$(this).find(".carousel-img img").each(function() {
			var thisHeight = $(this).height();
			if (thisHeight > maxHeight) {
				maxHeight = thisHeight;
			}
		});
		if (maxHeight > 0) {
			options.height = maxHeight + "px";
		}
		if (length > 0) {
			var thisCarousel = carousel[id];
			thisCarousel.reload(options);
		} else {
			var thisCarousel = layCarousel.render(options);
			carousel[id] = thisCarousel;
		}
	});
	
	setTimeout(() => {
		$(".middlePanel .editView .layui-carousel").each(function() {
			var maxHeight = 0;
			$(this).find(".carousel-img img").each(function() {
				var thisHeight = $(this).height();
				if (thisHeight > maxHeight) {
					maxHeight = thisHeight;
				}
			});
			$(this).css("height", maxHeight+"px");
		})
	}, 500);	
}

function chooseView() {
	$(".layui-layer-page").on("click", ".microPage .configureView .middlePanel .editView .setItem", function() {
		let index = $(this).attr("data-index");
		for (var i = 0; i < setData.length; i++) {
			if (i == index) {
				setData[i].choose = 1;
			} else {
				setData[i].choose = 0;
			}
		}
		
		configAssembly();
	});
}

function showDeleteIcon() {
	$(".layui-layer-page").on("mouseover", ".microPage .configureView .rightPanel .editBox .editItem .uploadBox,.middlePanel .phoneBox .editView .setItem", function() {
		$(this).children('.layui-icon-delete').show();
	});
	$(".layui-layer-page").on("mouseout", ".microPage .configureView .rightPanel .editBox .editItem .uploadBox,.middlePanel .phoneBox .editView .setItem", function() {
		$(this).children('.layui-icon-delete').hide();
	});
	$(".layui-layer-page").on("click", ".microPage .configureView .rightPanel .editBox .editItem .uploadBox .layui-icon-delete", function(event) {
		event.stopPropagation();
		event.preventDefault();
		var index = $(this).parents(".uploadBox").attr("data-index");
    	var detailIndex = $(this).parents(".uploadBox").attr("data-idx");
    	setData[index].detail.splice(detailIndex, 1);
    	configAssembly();
		return false;
	});
	$(".layui-layer-page").on("click", ".middlePanel .phoneBox .editView .setItem .deleteIcon", function(event) {
		event.stopPropagation();
		event.preventDefault();
		let index = $(this).parents(".setItem").attr("data-index");
		setData.splice(index, 1);
		configAssembly();
		return false;
	});
}

function addItemToPhone(kind) {
	let micropageid = $("#pageid").val();
	let lastIndex = $(".middlePanel .editView .setItem:last").attr("data-index");
	let id = "add" + new Date().getTime();
	let item = {
        id: id,
        micropageid: micropageid,
        kind: kind,
        showindex: lastIndex+1,
        showname: 1,
        showprice: 1,
        imagestyle: 0,
        orderby: 0,
        choose: 1
    }
	let detail = [];
	if (kind != 2 && kind != 6) {
		let detailid = "add" + new Date().getTime();
		detail = [
            {
                id: detailid,
                micropagesetid: id,
                showindex: 1,
                first: 0,
                second: 0,
                third: 0,
                text: '',
                targetpath: '',
                photourl: "",
                type: (kind==9?1:0),
                grouping: '',
                category: '',
                goodsname: (kind==9?'商品名称':''),
                price: (kind==9?888.88:0),
                point: 0,
                kind: 1,
                list: [],
                categoryone: '',
                categorytwo: '',
                categorythree: ''
            }
        ]
	}
	item.detail = detail;
	for (var i = 0; i < setData.length; i++) {
		setData[i].choose = 0;
	}
	setData.push(item);
	configAssembly();
}

function spellChange(obj) {
	var index = $(obj).parents(".editItem").attr("data-index");
	var text = $(obj).val();
	setData[index].detail[0].text = text;
}

function saveTargetPath(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].targetpath = $(obj).val();
}

function saveMicropage(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].first = $(obj).attr("data-id");
	setData[pidx].detail[idx].grouping = $(obj).val();
}

function saveGrouping(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].first = $(obj).attr("data-id");
	setData[pidx].detail[idx].grouping = $(obj).val();
}

function saveCoupon(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].first = $(obj).attr("data-id");
	setData[pidx].detail[idx].grouping = $(obj).val();
}

function saveFirst(obj) {
	event.stopPropagation();
	event.preventDefault();
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	var val = $(obj).val();
	var id = $(obj).attr("data-id");
	setData[pidx].detail[idx].first = id;
	setData[pidx].detail[idx].categoryone = val;
	setData[pidx].detail[idx].category = val;
	return false;
}

function saveSecond(obj) {
	event.stopPropagation();
	event.preventDefault();
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	var val = $(obj).val();
	var id = $(obj).attr("data-id");
	setData[pidx].detail[idx].second = id;
	setData[pidx].detail[idx].categorytwo = val;
	if (val == null || val == undefined || $.trim(val) == "") {
		val = setData[pidx].detail[idx].categoryone;
	}
	setData[pidx].detail[idx].category = val;
	return false;
}

function saveThird(obj) {
	event.stopPropagation();
	event.preventDefault();
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	var val = $(obj).val();
	var id = $(obj).attr("data-id");
	setData[pidx].detail[idx].third = id;
	setData[pidx].detail[idx].categorythree = val;
	if (val == null || val == undefined || $.trim(val) == "") {
		val = setData[pidx].detail[idx].categorytwo;
		if (val == null || val == undefined || $.trim(val) == "") {
			val = setData[pidx].detail[idx].categoryone;
		}
	}
	setData[pidx].detail[idx].category = val;
	return false;
}

function changeGoods(obj) {
	let goods = $(obj).attr("data-goods");
	goods = JSON.parse(goods);
	let pidx = $(obj).parents(".selectGoods").attr("data-index");
	let idx = $(obj).parents(".selectGoods").attr("data-idx");
	$(obj).parents(".selectGoods").find(".goodsPhoto img").attr("src", goods.photourl==null?"":goods.photourl);
	setData[pidx].detail[idx].first = goods.id;
	setData[pidx].detail[idx].photourl = goods.photourl==null?"":goods.photourl;
	setData[pidx].detail[idx].goodsname = goods.name==null?"":goods.name;
	setData[pidx].detail[idx].price = goods.price==null?0:goods.price;
	setData[pidx].detail[idx].point = goods.point==null?0:goods.point;
	setData[pidx].detail[idx].kind = goods.kind;
}

layuiForm.on('select(linkType)', function(data) {
	let pidx = $(data.elem).attr("data-pindex");
	let idx = $(data.elem).attr("data-index");
	setData[pidx].detail[idx].type = data.value;
});

function saveMicroset() {
	let pageid = $(".microPage #pageid").val();
	var setdata = setData;
	for (var i = 0; i < setdata.length; i++) {
		var id = setdata[i].id;
		if (String(id).indexOf("add") == 0) {
			setdata[i].id = 0;
		}
		var detail = setdata[i].detail;
		for (var j = 0; j < detail.length; j++) {
			var detailid = detail[j].id;
			var micropagesetid = detail[j].micropagesetid;
			if (String(detailid).indexOf("add") == 0) {
				setdata[i].detail[j].id = 0;
			}
			if (String(micropagesetid).indexOf("add") == 0) {
				setdata[i].detail[j].micropagesetid = 0;
			}
		}
	}
	setdata = JSON.stringify(setdata);
	let data = {};
    data.pageid = pageid;
    data.setdata = escape(setdata);
	common.showLoading();
    api.load('./micropage/save','post',data, function(result) {
        if (result.errcode == 0) {
        	common.tips(result.message);
        	var openViewIndex = $("#openViewIndex").val();
        	layer.close(openViewIndex);
        	let publicplatformid = $("#publicplatformid").val();
        	loadData(publicplatformid);
        } else {
            common.error(result.message);
        }
        common.closeLoading();
    });
}