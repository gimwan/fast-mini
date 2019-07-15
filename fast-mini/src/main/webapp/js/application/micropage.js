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
let uploadInst ;

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
	uploadInst = layuiUpload.render({
	    elem: '.layui-upload-drag',
	    url: './upload/field/image',
	    size: 1024,
	    multiple: false,
	    done: function(res, index, upload){
	    	// 上传完毕回调
	    	console.log(res);
	    	console.log(index);
	    	var item = this.item;
	    	$(item).html('<img alt="" src="'+res.data+'" class="dragImg">');
	    	console.log(item);
	    },
	    error: function(res, index){
	    	// 请求异常回调
	    	console.log(res);
	    	console.log(index);
	    }
    });
	
	loadData();
	
    // 重新刷新form
    layuiForm.render();
    
    // 界面元素选中
    chooseView();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'micropage/data','post',{"pageid":"1"},function (result) {
        if (result.errcode == 0) {
        	let pageData = result.data;
        	if (pageData != null) {
        		let microPage = pageData.micropage;
        		let setData = pageData.setdata;
            	console.log(microPage);
            	console.log(setData);
            	microPageSetVm = new Vue({
                    el : ".phoneBox",
                    data : {
                    	setdata: setData
                    }
                });
			}
        	
        } else {
            common.error('数据加载失败');
        }
        
        // 初始化轮播
        configCarousel();
        common.closeLoading();
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
	$("body").on("click", ".microPage .configureView .middlePanel .editView>div", function() {
		$(".microPage .configureView .middlePanel .editView>div").removeClass("choose-view");
		$(this).addClass("choose-view");
	});
}