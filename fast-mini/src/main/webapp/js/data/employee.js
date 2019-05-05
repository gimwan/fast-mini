let employee = [];
let employeeVm;

common.bindVue = function() {
    employeeVm = new Vue({
        el : ".employee-data",
        data : {
            employee: employee
        },
        methods : {
            edit: function(event) {
                if (event) {
                    let index = $(event.target).parents("tr").data("index");
                    console.log(employee[index].id);
                    //showEditBox(index,employee[index].id,employee[index].name,employee[index].value);
                }
            }
        }
    });
    loadData();
}

function loadData() {
    common.showLoading();
    api.load(basePath + 'employee/employee','post',{},function (result) {
        if (result.errcode == 0) {
            let data = result.data;
            if (data != null) {
                for (let i = 0; i < data.length; i++) {
                    employee.push(data[i]);
                }
            }
        } else {
            common.error('数据加载失败');
        }
        common.closeLoading();
    });
}