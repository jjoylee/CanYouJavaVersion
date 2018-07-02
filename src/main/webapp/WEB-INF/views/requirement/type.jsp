<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="title">
		<title>type</title>
	</layout:put>
	<layout:put block="contents">
		<div class="header">
		    <h2>과목유형요건</h2>
		</div>
		<div class="content">
		    <p style="color:red; font-weight:bold">*과목유형요건이 존재하지 않는다면 입력하지 않으면 됩니다.</p>
		    <table class="pure-table pure-table-bordered" id="typeTbl">
		        <thead>
		            <tr style="text-align:center">
		                <th>&nbsp;</th>
		                <th>과목구분</th>
		                <th>과목유형구분</th>
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
			                <td>${item.lectureTypeName}</td>
			                <td>${item.cutline}</td>
			                <td>
			                    <button class="pure-button button-xsmall" name="update" data-id="${item.id}">수정</button>
			                </td>
			                <td>
			                    <button class="pure-button button-xsmall delete" name="delete" data-id="${item.id}">삭제</button>
			                </td>
			            </tr>
					</c:forEach>
        		</tbody>
    		</table>
		    <p></p>
		    <p></p>
		    <a class="pure-button pure-button-primary" href="/requirement/typeRegister">과목유형등록</a> 
		</div>
	</layout:put>
	<layout:put block="scripts">
		<script type="text/javascript">
            $("button[name='delete']").click(function () {
                var id = $(this).data("id");
                var url = "/requirement/typeDelete/" + id;
                $.getJSON(url, function (data) {
                    if (data.result == 'fail') {
                        alert(data.message);
                    } else {
                        $(location).attr('href', "/requirement/type");
                    }
                });
            });

            $("button[name='update']").click(function () {
                var id = $(this).data("id");
                var url = "/requirement/typeUpdate?id=" + id;
                $(location).attr('href', url);
            });
    	</script>
	</layout:put>
</layout:extends>

