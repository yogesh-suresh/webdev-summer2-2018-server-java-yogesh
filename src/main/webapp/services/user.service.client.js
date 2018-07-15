/**
 * Ref - Jose
 */

function UserServiceClient() {

	this.findAllUsers = findAllUsers;
	this.deleteUser = deleteUser;
	this.addUser = addUser;
	this.showUser = showUser;
	this.loginUser = loginUser;
	this.logOutUser = logOutUser;
	this.updateUser = updateUser;
	
	
	function deleteUser(id) {
		var url = "/api/user/" + id;

		return fetch(url, {
			method : 'delete'
		});
	}

	function findAllUsers() {
		var url = "/api/user";
		return fetch(url).then(function(response) {
			return response.json();
		});
	}
	
	function addUser(userObjStr) {
		return fetch('/api/register', {
			method : 'post',
			body : userObjStr,
			headers : {
				'Content-Type' : 'application/json'
			},
			'credentials' : 'include'
		});
	}
	
	    
	function loginUser(userObjStr) {
		return fetch('/api/login', {
			method : 'post',
			body : userObjStr,
			headers : {
				'Content-Type' : 'application/json'
			},
			'credentials' : 'include'
		});
	}
	
	function showUser() {
		return fetch('/api/profile', {
	      'credentials': 'include'
	    });
	}
	
	function logOutUser() {
		return fetch("/api/logout");
	}
	
	
	function updateUser(userObj,id) {
		return fetch("/api/user/" + id, {
		      method: 'put',
		      body: JSON.stringify(userObj),
		      'credentials': 'include',
		      headers: {
		        'content-type': 'application/json'
		      }
		});
	}
}

