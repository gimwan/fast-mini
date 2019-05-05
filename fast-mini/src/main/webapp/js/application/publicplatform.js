let publicplatform = [];
let publicplatformVm;

common.bindVue = function() {
    publicplatformVm = new Vue({
        el : ".publicplatform-data",
        data : {
            publicplatform: publicplatform
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    console.log(publicplatform[index].id);
                    //showEditBox(index,publicplatform[index].id,publicplatform[index].name,publicplatform[index].value);
                }
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'publicplatform/publicplatform','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    publicplatform.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}