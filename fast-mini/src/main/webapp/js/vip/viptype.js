let viptype = [];
let viptypeVm;

common.bindVue = function() {
    viptypeVm = new Vue({
        el : ".viptype-data",
        data : {
            viptype: viptype
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    console.log(viptype[index].id);
                    //showEditBox(index,viptype[index].id,viptype[index].name,viptype[index].value);
                }
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'viptype/viptype','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    viptype.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}