<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/loginLayout.jsp">
	<layout:put block="contents">
		<h2>회원가입</h2>
		<form class="pure-form pure-form-aligned" id="frmJoin" action="/account/join" method="post">
		    <fieldset>
		        <div class="pure-control-group">
		            <label for="email">이메일(아이디)</label>
		            <input id="email" name="email" type="email" placeholder="Email Address" required>
		        </div>
		
		        <div class="pure-control-group">
		            <label for="password" >비밀번호</label>
		            <input id="password"name="password" type="password" placeholder="Password" required>
		        </div>
		
		        <div class="pure-control-group">
		            <label for="passwordChk">비밀번호확인</label>
		            <input id="passwordChk" name="passwordChk" type="password" placeholder="checking password.." required>
		        </div>
		
		        <div class="pure-controls">
		            <button type="button" class="pure-button pure-button-primary" id="btnJoin">등록</button>
		        </div>
		    </fieldset>
		</form>
	</layout:put>
	<layout:put block="scripts">
    	<script type="text/javascript">
        $("#frmJoin").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 8
                },
                passwordChk: {
                    required: true,
                    minlength: 8,
                    equalTo: "#password"
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
                passwordChk: {
                    required: "암호를 한 번 더 입력해 주세요",
                    minlength: "암호는 8자 이상이어야 합니다.",
                    equalTo: "암호가 일치하지 않습니다."
                },
                email: "형식에 맞는 이메일을 입력해 주세요."
            }
        });

        $("#btnJoin").click(function () {
            if (!$("#frmJoin").valid()) return;

            $("#frmJoin").ajaxForm({
                success: function (data, statusText, xhr, $form) {
                    if (data.result == 'fail') {
                        alert(data.message);
                    } else {
                    	alert(data.result);
                        $(location).attr('href', "/account/login");
                    }
                },
                dataType: 'json'
            });
            $("#frmJoin").submit();
            return false;
        });
    </script>
    </layout:put>
</layout:extends>