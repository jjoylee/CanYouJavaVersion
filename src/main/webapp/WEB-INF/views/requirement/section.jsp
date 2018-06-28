<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>영역요건</h2>
		</div>
		
		<div class="content">
		    <table class="pure-table pure-table-bordered" id="sectionyTbl">
		        <thead>
		            <tr style="text-align:center">
		                <th>&nbsp;</th>
		                <th>개수</th>
		                <th>수정</th>
		                <th>삭제</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${list}" var="item">
		            	<tr>
			                <td>핵심교양 최소 영역 수</td>
			                <td>${item.cutline}</td>
			                <td>
			                    <button class="pure-button button-xsmall" name="update" data-id="${item.id}">수정</button>
			                </td>
			                <td>
			                    <button class="pure-button button-xsmall" name="delete" data-id="${item.id}">삭제</button>
			                </td>
			            </tr>
		        	</c:forEach>
		        </tbody>
		    </table>
		    <p></p>
		    <p></p>
		    <a class="pure-button pure-button-primary" href="/requirement/sectionRegister">영역요건등록</a>
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">
	        $("button[name='delete']").click(function () {
	            var id = $(this).data("id");
	            var url = "/requirement/sectionDelete/" + id;
	            $.getJSON(url, function (data) {
	                if (data.result == 'fail') {
	                    alert(data.message);
	                } else {
	                    $(location).attr('href', "/requirement/section");
	                }
	            });
	        });
	        $("button[name='update']").click(function () {
	            var id = $(this).data("id");
	            var url = "/requirement/sectionUpdate/" + id;
	            $(location).attr('href', url);
	        });
    	</script>
    </layout:put> 
</layout:extends>