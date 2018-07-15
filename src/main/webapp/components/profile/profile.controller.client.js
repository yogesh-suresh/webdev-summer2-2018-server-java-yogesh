/**
 * 
 */
(function(){
	 
	 var userServiceClient = new UserServiceClient();
	 var $username,$firstName,$lastName,$phone,$email,$role,$dob;
	 var $updateBtn,$logOutBtn;
	 var currentUser = null;
	
	init();
	
	function init(){
		
		$username 	= $("#userName");
		$firstName 	= $("#firstName");
		$lastName 	= $("#lastName")
		$phone 		= $("#phone");
		$email 		= $("#email");
		$role 		= $("#role");
		$dob 		= $("#dob");
		$updateBtn	= $("#updateBtn");
		$logOutBtn	= $("#logOutBtn");
		
		profileLoad()
	      .then(populateContent);
		
		$updateBtn.click(updateUser);
		$logOutBtn.click(logout);
	}
	
	
	function profileLoad() {
	    return userServiceClient.showUser()
	    .then(function (response) {
	      return response.json();
	    });
	  }
	
	function populateContent(user){
		currentUser = user;
		$username.val(user.username);	
		$firstName.val(user.firstname);
		$lastName.val(user.lastname);
		$phone.val(user.phoneNo);
		$email.val(user.email);
		$role.val(user.role);
		if(user.dateOfBirth)
			$dob.val(user.dateOfBirth.slice(0,10));
		else
			$dob.val(user.dateOfBirth);
	}
	
	function updateUser() {
	    var userObj = {
	    		username:$username.val(),
	    		firstname: $firstName.val(),
	    		lastname: $lastName.val(),
	    		phoneNo: $phone.val(),
	    		email: $email.val(),
	    		role: $role.val(),
	    		dateOfBirth: $dob.val()
	    };

	    userServiceClient.updateUser(userObj,currentUser.id);
	  }
	
	function logout(){;
		alert("Thanks for Visiting Site !! logging Out")
		userServiceClient.logOutUser().then(function(){
		window.location.href = "/components/login/login.template.client.html";});
	}
})();