<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>강의수정</h2>
		</div>
		<div class="content">
    		<form class="pure-form pure-form-aligned" id="lectureUpdateFrm" method="post" action="/lecture/update/${lectureDetail.id}">
	        	<fieldset>
		            <div class="pure-control-group">
		                <label for="category">과목구분</label>
		                <select id="category" name="lectureCategoryId" required>
			                <c:forEach items="${categoryList}" var="category">
			                	<option value="${category.id}" <c:if test="${category.id eq lectureDetail.lectureCategoryId}">selected</c:if>>
			                		${category.name}
			                	</option>
			                </c:forEach>
		                </select>
		            </div>
	
		            <div class="pure-control-group">
				    	<jsp:include page="typePartial.jsp">
							<jsp:param name="typeList" value="${typeList}"></jsp:param>
							<jsp:param name="lectureDetail" value="${lectureDetail}"></jsp:param>
						</jsp:include>
		            </div>
	
		            <div class="pure-control-group">
				    	<jsp:include page="sectionPartial.jsp">
							<jsp:param name="sectionList" value="${sectionList}"></jsp:param>
							<jsp:param name="lectureDetail" value="${lectureDetail}"></jsp:param>
						</jsp:include>
		            </div>
	
	
		            <div class="pure-control-group">
		                <label for="credit">학점</label>
		                <input id="creit" name="credit" type="text" placeholder="숫자를 입력하세요." value="${lectureDetail.credit}" required>
		            </div>
		
		            <div class="pure-control-group">
		                <label for="score">성적</label>
		                <input id="score" name="score" type="text" placeholder="받은 성적을 입력하세요" value="${lectureDetail.score}" required />
		            </div>
		
		            <div class="pure-control-group">
		                <label for="title">교과목명</label>
		                <input id="name" name="name" type="text" placeholder="교과목명을 입력하세요." value="${lectureDetail.name}" required>
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
		<script src="../resources/scripts/customValidator.js"></script>
	    <script type="text/javascript">
		    $("#lectureUpdateFrm").validate({
		        rules: {
		            credit: {
		                required: true
		            },
		            score:
		              {
		                  required: true,
		                  scoreValue:true
		              },
		            title: {
		                required: true
		            }
		        },
		        messages: {
		            credit: {
		                required: "학점을 입력해주세요"
		            },
		            title: {
		                required: "교과목명을 입력해주세요"
		            },
		            score: {
		                required: "받은 점수를 입력해주세요"
		            }
		        }
		    });
		
		    $("#updateBtn").click(function () {
		        if (!$("#lectureUpdateFrm").valid()) return;
		
		        $("#lectureUpdateFrm").ajaxForm({
		            success: function (data, statusText, xhr, $form) {
		                if (data.result == 'fail') {
		                    alert(data.message);
		                } else {
		                    $(location).attr('href', "/lecture/list");
		                }
		            },
		            dataType: 'json'
		        });
		        $("#lectureUpdateFrm").submit();
		        return false;
		    });
		    $("#cancel").click(function () {
		        var url = "/lecture/list";
		        $(location).attr('href', url);
		        return false;
		    });
		    $("#category").change(function () {
		        loadtype();
		        loadSection();
		    });
		
		    $("#type").change(function () {
		        loadSection();
		    });
		
		    function loadtype() {
		        var categoryId = $("#category").val();
		        var url = "/lecture/typePartial?categoryId=" + categoryId;
		        $("#type").load(url, {}, function () {
		            loadSection();
		        });
		    }
		
		    function loadSection() {
		        var typeId = $("#type").val();
		        var url = "/lecture/sectionPartial?typeId=" + typeId;
		        $("#section").load(url);
		    }
		</script>
	</layout:put>
</layout:extends>

