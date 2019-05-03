let miniprogram = [];
let miniprogramVm;

common.bindVue = function() {
    miniprogramVm = new Vue({
        el : ".miniprogram-data",
        data : {
            miniprogram: miniprogram
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    console.log(miniprogram[index].id);
                    //showEditBox(index,miniprogram[index].id,miniprogram[index].name,miniprogram[index].value);
                }
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'miniprogram/miniprogram','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    miniprogram.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}