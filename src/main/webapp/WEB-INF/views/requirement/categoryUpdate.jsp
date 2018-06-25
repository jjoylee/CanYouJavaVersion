<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>과목요건 수정</h2>
		</div>
		<div class="content">
    		<form class="pure-form pure-form-aligned" id="categoryUpdateFrm" method="post" action="/requirement/categoryUpdate/${requirement.id}">
		        <fieldset>
		            <div class="pure-control-group">
		                <label for="category">과목구분</label>
		                <select id="category" name="lectureCategoryId" required>
		                    <c:forEach items="${categoryList}" var="category">                    
		                        <option value="${category.id}" <c:if test="${category.id eq requirement.lectureCategoryId}">selected</c:if>>
		                        	${category.name}
		                        </option>
		                    </c:forEach>
		                </select>
		            </div>
		            <div class="pure-control-group">
		                <label for="cutline">이수학점</label>
		                <input id="cutline" name="cutline" type="text" placeholder="숫자를 입력하세요." required value="${requirement.cutline}">
		            </div>
		            <div class="pure-controls">
		                <button class="pure-button pure-button-primary" id="updateBtn">수정</button>
		                <button class="pure-button pure-button-primary" id="cancel">취소</button>
		            </div>
		        </fieldset>
    		</form>
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript"> 
		    $("#categoryUpdateFrm").validate({
		        rules: {
		            category: {
		                required: true
		            },
		            cutline: {
		                required: true
		            }
		        },
		        messages: {
		            category: {
		                required: "과목구분을 선택해주세요"
		            },
		            cutline: {
		                required: "학점을 입력해주세요"
		            }
		        }
		    });
		
		    $("#updateBtn").click(function () {
		        if (!$("#categoryUpdateFrm").valid()) return;
		
		        $("#categoryUpdateFrm").ajaxForm({
		            success: function (data, statusText, xhr, $form) {
		                if (data.result == 'fail') {
		                    alert(data.message);
		                } else {
		                    $(location).attr('href', "/requirement/category");
		                }
		            },
		            dataType: 'json'
		        });
		        $("#categoryUpdateFrm").submit();
		        return false;
		    });
		
		    $("#cancel").click(function () {
		        var url = "/requirement/category";
		        $(location).attr('href', url);
		        return false;
		    });
		</script>
	</layout:put>
</layout:extends>
