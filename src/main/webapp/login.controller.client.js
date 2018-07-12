/**
 * 
 */

(function() {
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
			console.log(userObjStr);
			var promise=fetch('/login', {
				method : 'post',
				body : userObjStr,
				headers : {
					'Content-Type' : 'application/json'
				},
				'credentials' : 'include'
			});
			promise.then(navigateToProfile,loginfailed);
		}
		else
			alert("Enter the User Credentials. New User Please Sign up")
	}

	function navigateToProfile(response) {
		console.log(response);
		//window.location.href = "/profile.template.client.html";
	}
	function loginfailed() {
		alert("Enter Correct Credentials");
	}
})();