let loginData = {
	code : '',
	password : ''
}

let errMessage = {
	message: ''
}

let loginVm;
let errVm;

document.onreadystatechange = function() {
	// DOM构建了就执行
	if(document.readyState == "interactive"){
		errVm = new Vue({
			el : ".text-foot",
			data : errMessage
		});
		
		loginVm = new Vue({
			el : ".loginBox",
			data : loginData,
			methods: {
				login: function() {
					errMessage.message = '';
					common.showLoading();
					api.load('./login','post',loginData, function(result) {
					    if (result.errcode == 0) {
					    	window.location.href = $("base").attr("href") + "/home";
						} else {
                            common.closeLoading();
							errMessage.message = result.message;
						}
					});
				}
			}
		});
	}
}