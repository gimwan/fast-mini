var loginData = {
	code : '',
	password : ''
}

window.onload = function() {
	new Vue({
		el : ".lowin-box-inner",
		data : loginData,
		methods : {
			loginCheck : function() {
				console.log(loginData);
			}
		}
	});
}