var loginData = {
	code : '',
	password : ''
}

window.onload = function() {
	new Vue({
		el : ".login-box-inner",
		data : loginData,
		methods: {
			login: function() {
				console.log(loginData);
				axios.post('./config', {
					
				})
				.then(function (response) {
				    console.log(response);
				})
				.catch(function (error) {
				    console.log(error);
				});
			}
		}
	});
}