let layer;
let layuiElement;
let layuiForm;
let layuiUpload;
let loadIndex;
let basePath = $("base").attr("href");

layui.use(['layer', 'element', 'form', 'upload'], function () {
    layer = layui.layer;
    layuiElement = layui.element;
    layuiForm = layui.form;
    layuiUpload = layui.upload;
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
            })
                .then(function (response) {
                    common.doFunction(fn, response.data);
                })
                .catch(function (error) {
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
