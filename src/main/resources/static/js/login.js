$(document).ready(function() {

/*     $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	}); */

	$('#btnLogin').click(function(){
	   var email = $('#email').val();
	   if (email == 'free@gmail.com') {
			window.location.href = 'free_member.html';
			return false;
		}
		
		if (email == 'member@gmail.com') {
			window.location.href = 'mainPage.html';
			return false;
		}
		
		if (email == 'admin@gmail.com') {
			window.location.href = './Administrator/production/index.html';
			return false;
		}
	});
});
