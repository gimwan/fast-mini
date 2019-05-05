let config = [];
let configVm;

common.bindVue = function() {
    configVm = new Vue({
        el : ".config-data",
        data : {
            config: config
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    console.log(config[index].id);
                    showEditBox(index,config[index].id,config[index].name,config[index].value);
                }
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'config/config','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    config.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}

function showEditBox(idx,id,name,val) {
    let editDiv = "<div class=\"edit-view\">"+
                    "<div class=\"edit-box\">"+
                        "<div class=\"edit-item\" need=\"0\" key=\"1\" hidden>"+
                            "<div class=\"edit-title\">"+
                                "<span class=\"title\"><label class=\"name\">ID</label>：</span>"+
                            "</div>"+
                            "<div class=\"edit-value\" data-field=\"id\">"+
                                "<input type=\"text\" value=\""+id+"\" class=\"layui-input value\"/>"+
                            "</div>"+
                        "</div>"+
                        "<div class=\"edit-item\" need=\"1\" key=\"0\">"+
                            "<div class=\"edit-title\">"+
                                "<span class=\"title\"><label class=\"name\">"+name+"</label>：</span>"+
                            "</div>"+
                            "<div class=\"edit-value\" data-field=\"value\">"+
                                "<input type=\"text\" value=\""+val+"\" class=\"layui-input value focus\"/>"+
                            "</div>"+
                        "</div>"+
                    "</div>"+
                "</div>";
    
    layer.open({
        type: 1,
        title: "<label style='font-weight:600;'>修改</label>",
        content: editDiv,
        area: ['600px', '230px'],
        btn: ['保存','取消'],
        btn1: function (index, layero) {
            let data = catchBoxValue();
            if (data == '') {
                return;
            }

            common.showLoading();
            api.load('./config/change','post',data, function(result) {
                if (result.errcode == 0) {
                	data = result.data;
                    for (const key in data) {
                        if (config[idx].hasOwnProperty(key)) {
                            config[idx][key] = data[key];
                        }
                    }
                    layer.close(index);
                    common.tips(result.message);
                } else {
                    common.error(result.message);
                }
                common.closeLoading();
            });
        },
        success: function () {
            var val = $(".edit-view .focus").val();
            $(".edit-view .focus").val("").focus().val(val);
        }
        
    });
}

function catchBoxValue() {
    let data = {};
    let error = false;
    $(".edit-view .edit-box .edit-item").each(function() {
        let need = $(this).attr("need");
        let title = $(this).find(".name").html();
        let value = $(this).find(".value").val();
        let field = $(this).find(".edit-value").data("field");

        let errorMsg;
        if (need == 1) {
            if (value == null || value == undefined || $.trim(value) == "") {
                errorMsg = title + "不能为空";
            }
        }
        if (errorMsg != null && errorMsg != undefined && $.trim(errorMsg) != "") {
            error = true;
            common.warn(errorMsg);
            return false;
        }
        data[field] = value;
    });
    if (error) {
        data = '';
    }
    return data;
}