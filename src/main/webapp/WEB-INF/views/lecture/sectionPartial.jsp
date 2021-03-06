<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="section">영역</label>
<select id="section" name="sectionId">
	<c:choose>
		<c:when test="${empty sectionList}">
			<option value="0">구분없음</option>
		</c:when>
		<c:otherwise>
			<c:forEach items="${sectionList}" var="section">
		    	<option value="${section.id}" <c:if test="${section.id eq lectureDetail.sectionId}">selected</c:if>>
		    		${section.name}
		    	</option>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</select>
                    