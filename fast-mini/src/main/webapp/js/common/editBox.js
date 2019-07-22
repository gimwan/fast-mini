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
        let isCheckbox = $(this).attr("checkbox");
        let isSelect = $(this).attr("select");
        let isPopup = $(this).attr("popup");
        let isCascade = $(this).attr("cascade");
        let isUploadFile = $(this).attr("uploadfile");
        
        value = $(this).find(".value").val();
        if (isRadio == "1") {
        	value = $(this).find('input[type="radio"]:checked').val();
		}
        if (isCheckbox == "1") {
        	$(this).find("input[type='checkbox']").each(function() {
        		let key = $(this).attr("name");
            	let isChecked = 0;
            	if ($(this).is(":checked")) {
            		isChecked = 1;
				}
            	data[key] = isChecked;
			});
		}
        if (isImage == "1") {
        	value = $(this).find('img').attr("src");
        }
        if (isSelect == "1") {
        	value = $(this).find('.selectItem .layui-this').attr("lay-value");
        }
        if (isPopup == "1") {
        	value = $(this).find('.value').attr("data-id");
        }
        if (isUploadFile == "1") {
        	value = $(this).find('.value').attr("src");
        }
        if (isCascade == "1") {
        	var isNull = false;
        	$(this).find('.cascade-value .edit-value').each(function() {
        		let key = $(this).data("field");
				let val = $(this).find(".value").attr("data-id");
				let grade = $(this).find(".value").attr("data-grade");
				if (val == null || val == undefined || $.trim(val) == "") {
					val = "";
					if (grade == 1) {
						isNull = true;
						return false;
					}
	            }
				data[key] = val;
			});
        	
            let errorMsg;
            if (need == 1) {
                if (isNull) {
                    errorMsg = title + "不能为空";
                }
            }
            if (errorMsg != null && errorMsg != undefined && $.trim(errorMsg) != "") {
                error = true;
                common.warn(errorMsg);
                return false;
            }
        } else {
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
		}
    });
    if (error) {
        data = '';
    }
    return data;
}