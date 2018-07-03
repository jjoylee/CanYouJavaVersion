<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<layout:extends name="../shared/layout.jsp">
	<layout:put block="title">
		<title>result</title>
	</layout:put>
	<layout:put block="contents">
		<div class="header">
    		<h2>결과보기</h2>
		</div>
		<div class="content">
		    <table class="pure-table pure-table-bordered">
		        <thead>
		            <tr style="text-align:center">
		                <th>No</th>
		                <th>요건명</th>
		                <th>요건</th>
		                <th>현재 상태</th>
		                <th>결과</th>
		                <th>&nbsp;</th>
		            </tr>
		        </thead>
		        <tbody>
		        	<c:forEach items="${results}" var="item" varStatus="status"> 
		                <tr>
		                    <td>${status.count}</td>
		                    <td>${item.name}</td>
		                    <td>${item.requirement}</td>
		                    <td>${item.score}</td>
		                    <td>${item.getResult()}</td>
			                <c:choose>
							    <c:when test="${item.isPassed()}">
							        <td style="width:100px; background-color:#00FF00;"></td>
							    </c:when>
							    <c:otherwise>
							    	<td style="width:100px; background-color:#FF0000;"></td>
							    </c:otherwise>
							</c:choose>
		                </tr>
		        	</c:forEach>
		        </tbody>
		    </table>
			
			<c:if test="${isAllPassed}">
				<img style="width:50%;" src="../resources/img/congratulation.jpg" />
			</c:if>
		</div>
	</layout:put>
</layout:extends>