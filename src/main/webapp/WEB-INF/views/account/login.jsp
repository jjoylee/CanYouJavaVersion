<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/loginLayout.jsp">
	<layout:put block="contents">
		<h2>Login</h2>
		<form class="pure-form" id="frmLogin" method="post" action="/account/login">
		    <fieldset>
		        <legend>Login</legend>
		        <input type="email" name ="email" placeholder="Email">
		        <p></p>
		        <input type="password" placeholder="Password" name="password">
		        <button class="pure-button" id="btnLogin">Login</button>
		    </fieldset>
		</form>
		<p>
		    <button class="pure-button pure-button-primary" id="join">회원가입</button>
		    <button class="pure-button pure-button-primary" id="findPwd">비밀번호 찾기</button>
		</p>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">
	        $("#frmLogin").validate({
	            rules: {
	                password: {
	                    required: true,
	                    minlength: 8
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	            },
	            messages: {
	                password: {
	                    required: "암호를 입력해 주세요",
	                    minlength: "암호는 8자 이상이어야 합니다."
	                },
	                email: "형식에 맞는 이메일을 입력해 주세요."
	            }
	        });
	
	        $("#btnLogin").click(function () {
	            if (!$("#frmLogin").valid()) return;
	
	            $("#frmLogin").ajaxForm({
	                success: function (data, statusText, xhr, $form) {
	                    if (data.result == 'fail') {
	                        alert(data.message);
	                    } else {
	                        $(location).attr('href', "/lecture/list");
	                    }
	                },
	                dataType: 'json'
	            });
	            $("#frmLogin").submit();
	            return false;
	        });
	        
	        $("#join").click(function () {
	            var url = "/account/join";
	            $(location).attr('href', url);
	            return false;
	        });
	        
	        $("#findPwd").click(function () {
	            var url = "/account/findPassword";
	            $(location).attr('href', url);
	            return false;
	        });
		</script>
	</layout:put>
</layout:extends>