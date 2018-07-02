<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/loginLayout.jsp">
	<layout:put block="title">
		<title>findPassword</title>
	</layout:put>
	<layout:put block="contents">
		<div class="header">
		    <h2>비밀번호 찾기</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="frmFindPwd" action="/account/findPassword" method="post">
		        <fieldset>
		            <div class="pure-control-group">
		                <label for="email">이메일(아이디)</label>
		                <input id="email" name="email" type="email" placeholder="Email Address" required>
		            </div>
		
		            <div class="pure-controls">
		                <button type="button" class="pure-button pure-button-primary" id="findPwd">비밀번호 찾기</button>
		            </div>
		        </fieldset>
		    </form>
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">
	        $("#frmFindPwd").validate({
	            rules: {
	                email: {
	                    required: true,
	                    email: true
	                },
	            },
	            messages: {
	                email: "형식에 맞는 이메일을 입력해 주세요."
	            }
	        });

	        $("#findPwd").click(function () {
	            if (!$("#frmFindPwd").valid()) return;
	
	            $("#frmFindPwd").ajaxForm({
	                success: function (data, statusText, xhr, $form) {
	                    if (data.result == 'fail') {
	                        alert(data.message);
	                    } else {
	                        alert("비밀번호 전송 완료");
	                        $(location).attr('href', "/account/login");
	                    }
	                },
	                dataType: 'json'
	            });
	            $("#frmFindPwd").submit();
	            return false;
	        });
    	</script>
    </layout:put>
</layout:extends>