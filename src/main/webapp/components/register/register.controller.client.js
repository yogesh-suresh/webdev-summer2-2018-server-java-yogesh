/**
 * 
 */
(function() {
	var userServiceClient = new UserServiceClient();
	var registerBtn = $('#registerBtn');
	var usernameFld = $('#username');
	var passwordFld = $('#password');
	var password2Fld = $('#password2');
	registerBtn.click(registerHandler);

	function registerHandler() {
		var usernameVal = usernameFld.val();
		var passwordVal = passwordFld.val();
		var password2Val = password2Fld.val();

		var userObj = {
			username : usernameVal,
			password : passwordVal
		};

		if (passwordVal == password2Val && passwordVal.length > 0
				&& password2Val.length > 0) {
			var userObjStr = JSON.stringify(userObj);
			userServiceClient.addUser(userObjStr).
			then(registrationSuccessful, registrationFailed)
		} else
			alert("Confirm Password mismatch. Try Again");

	}
	function registrationSuccessful() {
		window.location.href = '/components/profile/profile.template.client.html';
	}
	function registrationFailed() {
		alert("Login Failed");
	}

})();