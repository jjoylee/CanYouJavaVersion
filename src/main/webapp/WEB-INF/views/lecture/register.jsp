<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
    		<h2>강의등록</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="frmLectureRegister" method="post" action="/lecture/register">
		        <fieldset>
		            <div class="pure-control-group">
		                <label for="category">과목구분</label>
		                <select id="category" name="category" required>
		                    <c:forEach items="${categoryList}" var="category">
		                        <option value="${category.id}">${category.name}</option>
		                    </c:forEach>
		                </select>
		            </div>
		
		            <div class="pure-control-group">
		                <label for="type">과목유형구분</label>
		                <select id="type" name="type">
		                	<c:choose>
			                    <c:when test="${typeList eq null}">
			                          <option value="0">구분없음</option>
			                    </c:when>
			                    <c:otherwise>
				                    <c:forEach items="${typeList}" var="type">
			                            <option value="${type.id}">${type.name}</option>
			                        </c:forEach>
		                        </c:otherwise>
		                	</c:choose>
		                </select>
		            </div>
		
		            <div class="pure-control-group">
		                <label for="section">영역</label>
		                <select id="section" name="section">
		                    <c:choose>
			                    <c:when test="${empty sectionList}">
		                        	<option value="0">구분없음</option>
		                    	</c:when>
			                    <c:otherwise>
			                    	<c:forEach items="${sectionList}" var="section">
		                            	<option value="${section.id}">${section.name}</option>
		                            </c:forEach>
		                        </c:otherwise>
		                    </c:choose>
		                </select>
		            </div>
		
		
		            <div class="pure-control-group">
		                <label for="credit">학점</label>
		                <input id="creit" name="credit" type="text" placeholder="숫자를 입력하세요." required>
		            </div>
		
		            <div class="pure-control-group">
		                <label for="score">성적</label>
		                <input id="score" name="score" type="text" placeholder="받은 성적을 입력하세요" required />
		            </div>
		
		            <div class="pure-control-group">
		                <label for="title">교과목명</label>
		                <input id="title" name="title" type="text" placeholder="교과목명을 입력하세요." required>
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
		    $("#frmLectureRegister").validate({
		        rules: {
		            credit: {
		                required: true,
		                range: [1, 3]
		            },
		            score:
		              {
		                  required: true,
		                  scoreValue: true
		                },
		            title: {
		                required: true
		            }
		        },
		        messages: 
		        {
		            credit: {
		                required: "학점을 입력해주세요",
		                range: "1이상 3이하의 수를 입력해주세요."
		            },
		            title:{
		                required:"교과목명을 입력해주세요"
		            },
		            score: {
		                required:"받은 성적을 입력해주세요"
		            }
		        }
		    });
		
		    $("#registerBtn").click(function () {
		        if (!$("#frmLectureRegister").valid()) return;
		
		        $("#frmLectureRegister").ajaxForm({
		            success: function (data, statusText, xhr, $form) {
		                if (data.result == 'fail') {
		                    alert(data.message);
		                } else {
		                    $(location).attr('href', "/lecture/list");
		                }
		            },
		            dataType: 'json'
		        });
		        $("#frmLectureRegister").submit();
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
		        console.log("type");
		        var categoryId = $("#category").val();
		        var url = "/requirement/lectureTypePartial?categoryId=" + categoryId;
		        $("#type").load(url, {},function(){
		            loadSection();
		        });
		    }
		
		    function loadSection() {
		        console.log("section");
		        var typeId = $("#type").val();
		        var url = "/lecture/sectionPartial?typeId=" + typeId;
		        $("#section").load(url);
		    }
		</script>
	</layout:put>
</layout:extends>