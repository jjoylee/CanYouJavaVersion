<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="type">과목유형구분</label>
<select id="type" name="lectureTypeId">
	<c:choose>
		<c:when test="${empty typeList}">
			<option value="0">구분없음</option>
		</c:when>
		<c:otherwise>
			<c:forEach items="${typeList}" var="type">
					<option value="${type.id}">${type.name}</option>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</select>