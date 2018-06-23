<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
		    <h2>과목요건</h2>
		</div>
		<div class="content">
		    <table class="pure-table pure-table-bordered" id="categoryTbl">
		        <thead>
		            <tr style="text-align:center">
		                <th>&nbsp;</th>
		                <th>과목구분</th>
		                <th>이수학점</th>
		                <th>수정</th>
		                <th>삭제</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${list}" var="item" varStatus="status"> 
			            <tr>
			                <td>${status.count}</td>
			                <td>${item.lectureCategoryName}</td>
			                <td>${item.cutline}</td>
			                <td>
			                    <button class="pure-button button-xsmall" data-id="${item.id}" name="update">수정</button>
			                </td>
			                <td>
			                    <button class="pure-button button-xsmall" data-id="${item.id}" name="delete">삭제</button>
			                </td>
			            </tr>
		            </c:forEach>
		        </tbody>
		    </table>
		    <a class="pure-button pure-button-primary" href="/requirement/categoryRegister">과목요건등록</a>
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">      
	        $("button[name='delete']").click(function () {
	            var id = $(this).data("id");
	            var url = "/requirement/categoryDelete/"+id;
	            $.getJSON(url, function (data) {
	                if (data.result == 'fail') {
	                    alert(data.message);
	                } else {
	                    $(location).attr('href', "/requirement/category");
	                }
	            });
	        });
	
	        $("button[name='update']").click(function () {
	            var id = $(this).data("id");
	            var url = "/requirement/categoryUpdate?id=" + id;
	            $(location).attr('href', url);
	        });
    	</script>
    </layout:put>
</layout:extends>

