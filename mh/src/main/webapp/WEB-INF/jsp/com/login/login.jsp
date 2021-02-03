<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	$('.form-control').on('input', function() {
		var $field = $(this).closest('.form-group');
		if (this.value) {
		  $field.addClass('field--not-empty');
		}else{
		  $field.removeClass('field--not-empty');
		}
	});
});

function userlogin() {
	if ($('#user').val() == '') {
    	alert('아이디를 입력하세요.');
        $('#user').focus();
        return false;
    }else if ($('#pwd').val() == '') {
       	alert('비밀번호를 입력하세요.');
        $('#pwd').focus();
        return false;
    }
	
	$.ajax({
		 type : "post",
	     url : "/logintry.do",
	     data : {"user":$("#user").val(), "pwd":$("#pwd").val(), "logKeep":$("#logKeep").val()},
	     datatype : "json",
	     success : function(data) {				    	 
	    	 if(data.result == "success"){
			 	 window.location.href="/main.do";
	    	 }else{
	    		 alert(data.message);
	    		 return;
	    	 }
	     },
	     error : function(){
          alert("시스템 오류입니다. 관리자에게 문의해주세요.");
       }
	});
}
</script>


<div class="content">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6 order-md-2">
	          <img src="/images/undraw_file_sync_ot38.svg" alt="Image" class="img-fluid" style="width: 100%; margin-top: 50px;">
	        </div>
			<div class="col-md-6 contents">
				<div class="row justify-content-center">
					<div class="col-md-12">
						<div class="form-block">
							<div class="mb-4">
								<h3>
									Sign In to <strong>MH</strong>
								</h3>
								<p class="mb-4">Welcome to MH!</p>
							</div>
							<form action="#" method="post">
								<div class="form-group first">
									<label for="username">Email</label> 
									<input type="text" class="form-control" id="user" name="user" onKeypress="javascript:if(event.keyCode==13) {userlogin();}">
								</div>
								<div class="form-group last mb-4">
									<label for="password">Password</label> 
									<input type="password" class="form-control" id="pwd" name="pwd" onKeypress="javascript:if(event.keyCode==13) {userlogin();}">
								</div>
	
								<div class="d-flex mb-5 align-items-center">
									<label class="control control--checkbox mb-0" style="float: left;">
										<span class="caption">Remember me</span> <input type="checkbox" checked="checked" id="logKeep" name="logKeep" value="Y" />
										<div class="control__indicator"></div> 
									</label> 
									<span class="ml-auto" style="float: right;"><a href="javascript:alert('준비중입니다.');" class="forgot-pass">Forgot Password</a></span>
								</div>
	
								<input type="button" value="Log In" class="btn btn-pill text-white btn-block btn-primary" onclick="userlogin();">
	
								<!-- <span class="d-block text-center my-4 text-muted"> or
									sign in with</span>
	
								<div class="social-login text-center">
									<a href="#" class="facebook"> <span
										class="icon-facebook mr-3"></span>
									</a> <a href="#" class="twitter"> <span
										class="icon-twitter mr-3"></span>
									</a> <a href="#" class="google"> <span class="icon-google mr-3"></span>
									</a>
								</div> -->
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

