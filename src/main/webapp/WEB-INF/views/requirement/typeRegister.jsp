<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>과목유형요건 등록</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="typeRegisterFrm" method="post" action="/Requirement/TypeRegister">
		        <fieldset>
		            <div class="pure-control-group">
		                <label for="category">과목구분</label>
		                <select id="category" name="lectureCategoryId" required>
		                    <c:forEach items="${categoryList}" var="category">
		                        <option value="${category.id}">${category.name}</option>
		                    </c:forEach>
		                </select>
		            </div>

		            <div class="pure-control-group">
		                <jsp:include page="../lecture/typePartial.jsp">
							<jsp:param name="typeList" value="${typeList}"></jsp:param>
						</jsp:include>
		            </div>

		            <div class="pure-control-group">
		                <label for="cutline">이수학점</label>
		                <input id="cutline" type="text" name="cutline" placeholder="숫자를 입력하세요." required>
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
		    $("#typeRegisterFrm").validate({
		        rules: {
		            cutline: {
		                required: true
		            }
		        },
		        messages: {
		            cutline: {
		                required: "학점을 입력해 주세요"
		            }
		        }
		    });

		    $("#registerBtn").click(function () {
		        if (!$("#typeRegisterFrm").valid()) return;
		
		        $("#typeRegisterFrm").ajaxForm({
		            success: function (data, statusText, xhr, $form) {
		                if (data.result == 'fail') {
		                    alert(data.message);
		                } else {
		                    $(location).attr('href', "/requirement/type");
		                }
		            },
		            dataType: 'json'
		        });
		        $("#typeRegisterFrm").submit();
		        return false;
		    });
        
		    $("#cancel").click(function () {
	            var url = "/requirement/type";
	            $(location).attr('href', url);
	            return false;
	        });

	        $("#category").change(function () {
	            var categoryId = $(this).val();
	            var url = "/lecture/typePartial?categoryId=" + categoryId;
	            $("#type").load(url);
	        });
    	</script>
	</layout:put>
</layout:extends>

