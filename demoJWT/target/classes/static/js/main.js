$(document).ready(function() {
	// --- Signup ---
	$('#signup').click(function() {
		var fullName = $('#fullname').val();
		var email = $('#reg_email').val();
		var password = $('#reg_password').val();

		var signupData = JSON.stringify({
			fullName: fullName,
			email: email,
			password: password
		});

		$.ajax({
			type: 'POST',
			url: '/auth/signup',
			contentType: 'application/json; charset=utf-8',
			data: signupData,
			success: function(data) {
				alert("🎉 Đăng ký thành công! Vui lòng đăng nhập.");
				window.location.href = "/login";
			},
			error: function(e) {
				alert("❌ Đăng ký thất bại!");
			}
		});
	});

	// --- Login ---
	$('#login').click(function() {
		var email = $('#email').val();
		var password = $('#password').val();

		var loginData = JSON.stringify({
			email: email,
			password: password
		});

		$.ajax({
			type: 'POST',
			url: '/auth/login',
			contentType: 'application/json; charset=utf-8',
			data: loginData,
			success: function(data) {
				localStorage.token = data.token;
				alert("🎉 Đăng nhập thành công!");
				window.location.href = "/user/profile";
			},
			error: function(e) {
				alert("❌ Đăng nhập thất bại!");
			}
		});
	});

	// --- Profile page ---
	if (window.location.pathname === "/user/profile") {
		$.ajax({
			type: 'GET',
			url: '/users/me',
			contentType: 'application/json; charset=utf-8',
			beforeSend: function(xhr) {
				if (localStorage.token) {
					xhr.setRequestHeader("Authorization", "Bearer " + localStorage.token);
				}
			},
			success: function(data) {
				$('#profile').html(`
                    <ul class="list-group">
                        <li class="list-group-item"><b>Email:</b> ${data.email}</li>
                        <li class="list-group-item"><b>Họ tên:</b> ${data.fullName}</li>
                    </ul>
                `);
			},
			error: function(e) {
				$('#profile').html("<div class='alert alert-danger'>Không lấy được thông tin user.</div>");
			}
		});
	}

	// --- Logout ---
	$('#logout').click(function() {
		localStorage.clear();
		alert("Bạn đã đăng xuất!");
		window.location.href = "/login";
	});
});
