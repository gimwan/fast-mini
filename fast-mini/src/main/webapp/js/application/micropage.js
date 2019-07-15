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
    // 重新刷新form
    layuiForm.render();
    
    // 初始化轮播
    configCarousel();
    
    // 界面元素选中
    chooseView();
}

function configCarousel() {
	var index = 0;
	$(".carouselBox div").each(function(index) {
		let color = "";
		if (index == 0) {
			color = "red";
		} else if (index == 1) {
			color = "blue";
		} else if (index == 2) {
			color = "green";
		} else if (index == 3) {
			color = "pink";
		} else if (index == 4) {
			color = "gray";
		}
		$(this).css("bakground-color",color);
		index++;
	});
	let thisCarousel = layCarousel;
	thisCarousel.render({
		elem : '#test1',
		arrow : 'none',
		width : '100%',
		height : '160px',
		indicator : 'inside'
	});
}

function chooseView() {
	$("body").on("click", ".microPage .configureView .middlePanel .editView>div", function() {
		$(".microPage .configureView .middlePanel .editView>div").removeClass("choose-view");
		$(this).addClass("choose-view");
	});
}