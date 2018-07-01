<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>비밀번호 변경</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="updatePwd" action="/account/update" method="post">
		        <fieldset>
		            <legend>비밀번호 변경</legend>
		
		            <div class="pure-control-group">
		                <label for="currentPwd">현재 비밀번호</label>
		                <input id="currentPwd" name="currentPwd" type="password" required>
		            </div> 
		
		            <div class="pure-control-group">
		                <label for="newPassword">새 비밀번호</label>
		                <input id="newPassword" name="newPassword" type="password" required>
		            </div>
		
		            <div class="pure-control-group">
		                <label for="newPasswordChk">새 비밀번호확인</label>
		                <input id="newPasswordChk" name="newPasswordChk" type="password" required />
		            </div>
		
		            <div class="pure-controls">
		                <button class="pure-button pure-button-primary" id="update">수정</button>
		                <button class="pure-button pure-button-primary" id="withdraw">탈퇴</button>
		            </div>
		        </fieldset>
		    </form>
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">
	        $("#updatePwd").validate({
	            rules: {
	                currentPwd: {
	                    required: true,
	                    minlength: 8
	                },
	                newPassword: {
	                    required: true,
	                    minlength: 8
	                },
	                newPasswordChk: {
	                    minlength: 8,
	                    required: true,
	                    equalTo: "#newPassword"
	                },
	            },
	            messages: {
	                currentPwd: {
	                    required: "현재 암호를 입력해 주세요",
	                    minlength: "암호는 8자 이상이어야 합니다."
	                },
	                newPassword: {
	                    required: "새 암호를 입력해 주세요",
	                    minlength: "암호는 8자 이상이어야 합니다.",
	                    equalTo: "암호가 일치하지 않습니다."
	                },
	                newPasswordChk: {
	                    required: "새 암호를 한 번 더 입력해 주세요",
	                    minlength: "암호는 8자 이상이어야 합니다.",
	                    equalTo: "암호가 일치하지 않습니다."
	                }
	            }
	        });

	        $("#update").click(function () {
	            if (!$("#updatePwd").valid()) return;
	            $("#updatePwd").ajaxForm({
	                success: function (data, statusText, xhr, $form) {
	                    if (data.result == 'fail') {
	                        alert(data.message);
	                    } else {
	                        var url = "/account/login";
	                        $(location).attr('href', url);
	                    }
	                },
	                dataType: 'json'
	            });
	            $("#updatePwd").submit();
	            return false;
	        });
	        $("#withdraw").click(function () {
	            var txt = confirm("정말 탈퇴하시겠습니까?");
	            if (txt == true) {
	                var url = "/account/withdraw";
	                $.getJSON(url, function (data) {
	                    if (data.result == 'fail') {
	                        alert(data.message);
	                    } else {
	                        $(location).attr('href', "/account/login");
	                    }
	                });
	            } else {
	                var url = "/lecture/list";
	                $(location).attr('href', url);
	            }
	        });
		</script>
	</layout:put>
</layout:extends>

