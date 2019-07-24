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
	}
]

let microPageItemVm;
let microPageVm;
let microPageSetVm;
let microPageEditVm;
let uploadInst;
let microPage;
let setData;
let editData;

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
                console.log(item[index]);
			}
        }
    });
	
	loadData();
	
    // 重新刷新form
    layuiForm.render();
    
    // 界面元素选中
    chooseView();
    
    // 删除图片显示
    showDeleteIcon();
}

function loadData() {
	let pageid = $(".microPage #pageid").val();
    common.showLoading();
    api.load(basePath + 'micropage/data','post',{"pageid":pageid},function (result) {
        if (result.errcode == 0) {
        	let pageData = result.data;
        	if (pageData != null) {
        		microPage = pageData.micropage;
        		setData = pageData.setdata;
        		editData = pageData.setdata;
            	microPageSetVm = new Vue({
                    el : ".phoneBox",
                    data : {
                    	setdata: setData
                    }
                });
            	microPageEditVm = new Vue({
                    el : ".editBox",
                    data : {
                    	editdata: editData
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
		layCarousel.render();
	    layuiUpload.render();
	    configUploadInst();
	    layuiForm.render();
	}, 500);
}

function configUploadInst() {
	layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/image',
	    size: 1024,
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	var item = this.item;
	    	var index = $(item).parents(".editItem").attr("data-index");
	    	var detailIndex = $(item).parents(".uploadField ").attr("data-index");
	    	setData[index].detail[detailIndex].photourl = res.data;
	    	editData[index].detail[detailIndex].photourl = res.data;
	    	configAssembly();
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    	configAssembly();
	    }
    });
}

function configCarousel() {
	$(".middlePanel .editView .layui-carousel").each(function() {
		var id = $(this).attr("id");
		layCarousel.render({
			elem : '#'+id,
			arrow : 'none',
			width : '100%',
			height : '160px',
			indicator : 'inside'
		});
	});
}

function chooseView() {
	$(".layui-layer-page").on("click", ".microPage .configureView .middlePanel .editView .setItem", function() {
		let index = $(this).attr("data-index");
		for (var i = 0; i < setData.length; i++) {
			if (i == index) {
				setData[i].choose = 1;
				editData[i].choose = 1;
			} else {
				setData[i].choose = 0;
				editData[i].choose = 0;
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
		var index = $(this).parents(".editItem").attr("data-index");
    	var detailIndex = $(this).parents(".ad").attr("data-index");
    	setData[index].detail[detailIndex].photourl = "";
    	editData[index].detail[detailIndex].photourl = "";
    	configAssembly();
		return false;
	});
	$(".layui-layer-page").on("click", ".middlePanel .phoneBox .editView .setItem .deleteIcon", function(event) {
		event.stopPropagation();
		event.preventDefault();
		let index = $(this).parents(".setItem").attr("data-index");
		setData.splice(index, 1);
		editData.splice(index, 1);
		configAssembly();
		return false;
	});
}

function spellChange(obj) {
	var index = $(obj).parents(".editItem").attr("data-index");
	var text = $(obj).val();
	setData[index].detail[0].text = text;
	editData[index].detail[0].text = text;
}

function saveTargetPath(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].targetpath = $(obj).val();
}

function saveMicropage(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].targetpath = $(obj).attr("data-id");
}

function saveGrouping(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].targetpath = $(obj).attr("data-id");
}

function saveFirst(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].first = $(obj).attr("data-id");
}

function saveSecond(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].second = $(obj).attr("data-id");
}

function saveThird(obj) {
	let pidx = $(obj).attr("data-pindex");
	let idx = $(obj).attr("data-index");
	setData[pidx].detail[idx].third = $(obj).attr("data-id");
}

layuiForm.on('select(linkType)', function(data) {
	let pidx = $(data.elem).attr("data-pindex");
	let idx = $(data.elem).attr("data-index");
	setData[pidx].detail[idx].type = data.value;
});