/**
 * 
 */

(function() {
	
	var userServiceClient = new UserServiceClient();
	var loginBtn = $('#loginBtn');
	var usernameFld = $('#userName');
	var passwordFld = $('#password');

	loginBtn.click(loginHandler);

	function loginHandler() {
		var usernameVal = usernameFld.val();
		var passwordVal = passwordFld.val();

		var userObj = {
			username : usernameVal,
			password : passwordVal
		};

		if (usernameVal.length> 0 && passwordVal.length> 0) {

			var userObjStr = JSON.stringify(userObj);
			userServiceClient.loginUser(userObjStr)
			.then(function (response){
				if(response.status == 200){
					navigateToProfile();}
				else
					loginfailed();
			});
			
		}
		else
			alert("Enter the User Credentials. New User Please Sign up")
	}

	function navigateToProfile(response) {
		window.location.href = "/components/profile/profile.template.client.html";
	}
	function loginfailed() {
		alert("Enter Correct Credentials");
	}
})();