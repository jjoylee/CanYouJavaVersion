<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
    		<h2>과목요건 등록</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="categoryRegisterFrm" method="post" action="/requirement/categoryRegister">
		        <fieldset>
		            <div class="pure-control-group">
		                <label for="category">과목구분</label>
		                <select id="category" name="lectureCategoryId" required>
		                    <c:forEach items="${list}" var="item" varStatus="status">
		                        <option value="${item.id}">${item.name}</option>
		                    </c:forEach>
		                </select>
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
		<script src="../resources/scripts/customValidator.js"></script>
	    <script type="text/javascript">   
		    $("#categoryRegisterFrm").validate({
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
		
		    $("#registerBtn").click(function () {
		        if (!$("#categoryRegisterFrm").valid()) return;
		
		        $("#categoryRegisterFrm").ajaxForm({
		            success: function (data, statusText, xhr, $form) {
		                if (data.result == 'fail') {
		                    alert(data.message);
		                } else {
		                    $(location).attr('href', "/requirement/category");
		                }
		            },
		            dataType: 'json'
		        });
		        $("#categoryRegisterFrm").submit();
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


