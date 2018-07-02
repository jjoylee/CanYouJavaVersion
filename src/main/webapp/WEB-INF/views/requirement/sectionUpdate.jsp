<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="title">
		<title>sectionUpdate</title>
	</layout:put>
	<layout:put block="contents">
		<div class="header">
		    <h2>영역요건 수정</h2>
		</div>
		<div class="content">
		    <form class="pure-form pure-form-aligned" id="sectionUpdateFrm" method="post" action="/requirement/sectionUpdate/${requirement.id}">
		        <fieldset>
		            <div class="pure-control-group">
		                <label for="cutline">핵심교양 최소 영역 수</label>
		                <input name="cutline" id="cutline" type="text" placeholder="영역 수를 입력하세요." required value="${requirement.cutline}">
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
		    $("#sectionUpdateFrm").validate({
		        rules: {
		            cutline: {
		                required: true
		            }
		        },
		        messages: {
		            cutline: {
		                required: "영역의 개수를 입력해 주세요"
		            }
		        }
		    });

		    $("#updateBtn").click(function () {
		        if (!$("#sectionUpdateFrm").valid()) return;
		
		        $("#sectionUpdateFrm").ajaxForm({
		            success: function (data, statusText, xhr, $form) {
		                if (data.result == 'fail') {
		                    alert(data.message);
		                } else {
		                    $(location).attr('href', "/requirement/section");
		                }
		            },
		            dataType: 'json'
		        });
		        $("#sectionUpdateFrm").submit();
		        return false;
		    });
	        $("#cancel").click(function () {
	            var url = "/requirement/section";
	            $(location).attr('href', url);
	            return false;
	        });
    	</script>
	</layout:put>
</layout:extends>

