let menu = [
    {
        name: '系统管理 ',
        link: '',
        sub: [
            {
                name: '系统参数',
                link: 'config'
            },
            {
                name: '角色管理',
                link: 'role'
            },
            {
                name: '用户管理',
                link: 'user'
            }
        ]
    },
    {
        name: '资料管理 ',
        link: '',
        sub: [
            {
                name: '门店资料',
                link: 'department'
            },
            {
                name: '员工资料 ',
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
                name: '品牌档案',
                link: 'brand'
            },
            {
                name: '商品档案',
                link: ''
            }
        ]
    },
    {
        name: '会员管理 ',
        link: '',
        sub: [
            {
                name: '会员等级 ',
                link: 'viptype'
            },
            {
                name: '会员档案',
                link: ''
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
    		api.load(url, 'post', null, function(result) {
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
    }
}

function optionView(data) {
	let view = "<div class=\"popup-view\">" +
					"<div class=\"popup-box\">";
	for (var i = 0; i < data.length; i++) {
		view += "<div class=\"popup-data\" data-id=\""+data[i].ID+"\">" +
					"<span class=\"code\">"+data[i].Code+"</span>" +
					"<span class=\"name\">"+data[i].Name+"</span>" +
				"</div>";
	}				
	view += "</div></div>";
	return view;
}

//定义一个标志位
let isShow = true;
$('.kit-side-fold').click(function () {
    //选择出所有的span，并判断是不是hidden
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