let layer;
let layuiElement;
let layuiForm;
let layuiUpload;
let layPage;
let layCarousel;
let layColorpicker;
let layDate;
let loadIndex;
let basePath = $("base").attr("href");

layui.use(['layer', 'element', 'form', 'upload', 'laypage', 'carousel', 'colorpicker', 'laydate'], function () {
    layer = layui.layer;
    layuiElement = layui.element;
    layuiForm = layui.form;
    layPage = layui.laypage;
    layCarousel = layui.carousel;
    layuiUpload = layui.upload;
    layColorpicker = layui.colorpicker;
    layDate = layui.laydate;
});

let common = new Vue({
    methods: {
        tips: function (msg) {
            layer.msg(msg, { time: 2000 });
        },
        showLoading: function (tip) {
            loadIndex = layer.load(2);
        },
        closeLoading: function () {
            layer.close(loadIndex);
        },
        doFunction: function (fn, target) {
            let result = ``;
            if (!!fn) {
                try {
                    let func = eval(fn);
                    if (func && typeof (func) == "function") {
                        result = func(target);
                    }
                } catch (e) {
                    console.log(e);
                }
            }
            return result;
        },
        bindVue: function () {

        },
        warn: function (msg) {
            layer.alert(msg, { title: '提示' });
        },
        error: function (msg) {
            msg = "<span style='color:red;'>" + msg + "</span>";
            layer.alert(msg, { title: '错误' });
        },
        formatDate: function(jsonDate) {
        	if (jsonDate == null || jsonDate == undefined || $.trim(jsonDate) == "") {
				return '';
			}
			var year = jsonDate.year + 1900;
			var month = jsonDate.month + 1;
			var day = jsonDate.date;
			// 如果得到的数字小于9要在前面加'0'
			day = (day > 9) ? ("" + day) : ("0" + day);
			month = (month > 9) ? ("" + month) : ("0" + month);
			var hour = jsonDate.hours;
			var minute = jsonDate.minutes;
			var seconds = jsonDate.seconds;
			hour = (hour > 9) ? ("" + hour) : ("0" + hour);
			minute = (minute > 9) ? ("" + minute) : ("0" + minute);
			seconds = (seconds > 9) ? ("" + seconds) : ("0" + seconds);
			return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds;
		},
        formatDay: function(jsonDate) {
        	if (jsonDate == null || jsonDate == undefined || $.trim(jsonDate) == "") {
				return '';
			}
			var year = jsonDate.year + 1900;
			var month = jsonDate.month + 1;
			var day = jsonDate.date;
			// 如果得到的数字小于9要在前面加'0'
			day = (day > 9) ? ("" + day) : ("0" + day);
			month = (month > 9) ? ("" + month) : ("0" + month);
			return year + "-" + month + "-" + day;
        }
    }
});

let api = new Vue({
    methods: {
        load: function (url, method, params, fn) {
            axios({
                method: method,
                url: url,
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                },
                data: Qs.stringify(params)
            }).then(function (response) {
            	if (response.data.code == 88) {
            		window.location.href = "/";
				}
                common.doFunction(fn, response.data);
            }).catch(function (error) {
                console.log(error);
            });
        },
        ajaxLoad: function (url, method, params, fn) {
            $.ajax({
                type: method,
                url: url,
                contentType: 'application/x-www-form-urlencoded;charset=utf-8',
                data: params,
                dataType: "json",
                async: true
            }).done(function (response) {
                common.doFunction(fn, response.data);
            }).fail(function (error) {
                console.log(error);
            });
        }
    }
});
