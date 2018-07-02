<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="title">
		<title>scoreRegister</title>
	</layout:put>
	<layout:put block="contents">
		<div class="header">
		    <h2>총학점요건 등록</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="scoreRegisterFrm" method="post" action="/requirement/scoreRegister">
		        <fieldset>
		
		            <div class="pure-control-group">
		                <label for="cutline">총학점 요건</label>
		                <input name="cutline" id="cutline" type="text" placeholder="총학점을 입력하세요." required>
		            </div>
		
		            <div class="pure-controls">
		                <button class="pure-button pure-button-primary" id="registerBtn">등록</button>
		                <button class="pure-button pure-button-primary" id="cancel">취소</button>
		            </div>
		        </fieldset>
		    </form>
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">
	        $("#scoreRegisterFrm").validate({
	            rules: {
	                cutline: {
	                    required: true
	                }
	            },
	            messages: {
	                cutline: {
	                    required: "총학점 요건을 입력해 주세요"
	                }
	            }
	        });
	
	        $("#registerBtn").click(function () {
	            if (!$("#scoreRegisterFrm").valid()) return;
	
	            $("#scoreRegisterFrm").ajaxForm({
	                success: function (data, statusText, xhr, $form) {
	                    if (data.result == 'fail') {
	                        alert(data.message);
	                    } else {
	                        $(location).attr('href', "/requirement/score");
	                    }
	                },
	                dataType: 'json'
	            });
	            $("#scoreRegisterFrm").submit();
	            return false;
	        });
	        $("#cancel").click(function () {
	            var url = "/requirement/score";
	            $(location).attr('href', url);
	            return false;
	        });
    	</script>
	</layout:put>
</layout:extends>
