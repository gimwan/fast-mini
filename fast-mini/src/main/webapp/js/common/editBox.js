function catchBoxValue() {
    let data = {};
    let error = false;
    $(".edit-view .edit-box .edit-item").each(function() {
        let need = $(this).attr("need");
        let title = $(this).find(".name").html();
        let field = $(this).find(".edit-value").data("field");
        let value = "";
        let isImage = $(this).attr("image");
        let isRadio = $(this).attr("radio");
        let isSelect = $(this).attr("select");
        let isPopup = $(this).attr("popup");
        
        value = $(this).find(".value").val();
        if (isRadio == "1") {
        	value = $(this).find('input[type="radio"]:checked').val();
		}
        if (isImage == "1") {
        	value = $(this).find('img').attr("src");
        }
        if (isSelect == "1") {
        	value = $(this).find('.selectItem .layui-this').attr("lay-value");
        }
        if (isPopup == "1") {
        	value = $(this).find('.value').data("id");
        }
        
        if (value == null || value == undefined || $.trim(value) == "") {
        	value = "";
        }

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