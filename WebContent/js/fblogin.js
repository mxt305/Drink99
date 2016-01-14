FB.init({
	appId : '1016184181753221',
	status : true,
	cookie : true,
	xfbml : true,
	oauth : true
});
$(function() {
	$("#FBLogin").click(function() {
		fb_login();
	});
	function app_login(id, name, token) {
		$.post(BASE_URL + "login/fb", {
			fb_id : id,
			fb_name : name,
			token : token
		}, function(data) {
			/*if (data.success == 1) {
				// 登入成功
			} else {
				// 登入失敗
			}*/
			window.location.href = BASE_URL;
		});
	}
	function fb_login() {
		FB.login(function(response) {
			FB.getLoginStatus(function(response) {
				if (response.status === 'connected') { // 程式有連結到 Facebook 帳號
					var uid = response.authResponse.userID; // 取得 UID
					var accessToken = response.authResponse.accessToken; // 取得 accessToken
					FB.api('/me', function(response) {
						var name = response.name;
						app_login(uid, name, accessToken);
					});
				} else if (response.status === 'not_authorized') { // 帳號沒有連結到程式
					alert("請允許授權！");
				} else { // 帳號沒有登入
					alert("請登入！")
				}
			});
		}, {
			scope : "email"
		});
	}

});
