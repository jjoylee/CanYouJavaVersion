<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="contents">
		<div class="header">
    		<h2>강의목록</h2>
		</div>
		<div class="content">
		    <p style="color:red; font-weight:bold">*성적이 F, NP인 경우는 등록이 불가합니다.</p>
		    <table class="pure-table pure-table-bordered" id="lectureList">
		        <thead>
		            <tr style="text-align:center">
		                <th>&nbsp;</th>
		                <th>과목구분</th>
		                <th>유형구분</th>
		                <th>영역</th>
		                <th>학점</th>
		                <th>성적</th>
		                <th>교과목명</th>
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
		                	<td>${item.sectionName}</td>
		                	<td>${item.credit}</td>
		                	<td>${item.score}</td>
		                	<td>${item.name}</td>
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
    		<a class="pure-button pure-button-primary" href="/lecture/register">강의등록</a> 
		</div>
	</layout:put>
	<layout:put block="scripts">
	    <script type="text/javascript">
	    $(document).ready(function () {
	        $('#lectureList').each(function () {
	            var currentPage = 0;
	            var numPerPage = 10;
	            var $table = $(this);
	            $table.bind('repaginate', function () {
	                $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
	            });
	            $table.trigger('repaginate');
	            var numRows = $table.find('tbody tr').length;
	            var numPages = Math.ceil(numRows / numPerPage);
	            var $pager = $('<div class="pager"></div>');
	            for (var page = 0; page < numPages; page++) {
	                $('<button class="pure-button"></button>').text(page + 1).bind('click', {
	                    newPage: page
	                }, function (event) {
	                    currentPage = event.data['newPage'];
	                    $table.trigger('repaginate');
	                    $(this).addClass('pure-button-active').siblings().removeClass('pure-button-active');
	                }).appendTo($pager)
	            }
	            $pager.insertAfter($table).find('span.page-number:first').addClass('pure-button-active');
	        });

	        $("button[name='delete']").click(function () {
	            var id = $(this).data("id");
	            var url = "/lecture/delete/" + id;
	            $.getJSON(url, function (data) {
	                if (data.result == 'fail') {
	                    alert(data.message);
	                } else {
	                    $(location).attr('href', "/lecture/list");
	                }
	            });
	        });

	        $("button[name='update']").click(function () {
	            var id = $(this).data("id");
	            var url = "/lecture/update/" + id;
	            $(location).attr('href', url);
	        });
	    });
		</script>
	</layout:put>
</layout:extends>