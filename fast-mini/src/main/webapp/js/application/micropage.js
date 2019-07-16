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
let uploadInst ;
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
        		let index = $(event.currentTarget).data("index");
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
    common.showLoading();
    api.load(basePath + 'micropage/data','post',{"pageid":"1"},function (result) {
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
                    	editdata: setData
                    }
                });
			}
        	
        } else {
            common.error('数据加载失败');
        }
        
        // 初始化轮播
        configCarousel();
        // 初始化文件上传
        configUploadInst();
        // 重新刷新form
        layuiForm.render();
        
        common.closeLoading();
    });
}

function configUploadInst() {
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/image',
	    size: 1024,
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	var item = this.item;
	    	var index = $(item).parents(".editItem").data("index");
	    	var detailIndex = $(item).parent().data("index");
	    	setData[index].detail[detailIndex].photourl = res.data;
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    }
    });
}

function configCarousel() {
	$(".middlePanel .editView .layui-carousel").each(function() {
		var id = $(this).attr("id");
		let thisCarousel = layCarousel;
		thisCarousel.render({
			elem : '#'+id,
			arrow : 'none',
			width : '100%',
			height : '160px',
			indicator : 'inside'
		});
	});
}

function chooseView() {
	$("body").on("click", ".microPage .configureView .middlePanel .editView .setItem", function() {
		let index = $(this).data("index");
		for (var i = 0; i < setData.length; i++) {
			if (i == index) {
				setData[i].choose = 1;
			} else {
				setData[i].choose = 0;
			}
		}
	});
}

function showDeleteIcon() {
	$("body").unbind("mouseover").on("mouseover", ".microPage .configureView .rightPanel .editBox .editItem .layui-upload-drag", function() {
		$(this).children('.layui-icon-delete').show();
	});
	$("body").unbind("mouseout").on("mouseout", ".microPage .configureView .rightPanel .editBox .editItem .layui-upload-drag", function() {
		$(this).children('.layui-icon-delete').hide();
	});
	$("body").on("click", ".microPage .configureView .rightPanel .editBox .editItem .layui-upload-drag .layui-icon-delete", function() {
		console.log('click');
		return;
	});
}

function spellChange(obj) {
	var index = $(obj).parents(".editItem").data("index");
	var text = $(obj).val();
	setData[index].detail[0].text = text;
}