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
                name: '员工资料 ',
                link: 'employee'
            },
            {
                name: '门店资料',
                link: 'department'
            },
            {
                name: '商品资料',
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
        
    }
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