<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.canyou.model.Account.AccountVO" %>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<%
	AccountVO user = (AccountVO)session.getAttribute("signedUser");
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Title</title>
	<link rel="stylesheet" href="../resources/css/pure-min.css">
	<link rel="stylesheet" href="../resources/css/side-menu.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .pure-table
        {
            margin-top : 5%;
            margin-bottom:5%;
            text-align:center;
        }
        .pure-selected
        {
            background-color :white;
            color:black;
        }
	</style>
</head>
<body>
    <div id="layout"> 
        <div id="menu"> 
            <div class="pure-menu custom-restricted-width">
                <a class="pure-menu-heading" href="/Account/UseInfo">사용법</a>
                <ul class="pure-menu-list">
                    <li class="pure-menu-item">
                        <a href="/Lecture/List" class="pure-menu-link">강의관리</a>
                    </li>

                    <li class="pure-menu-item">
                        <a class="pure-menu-link">졸업요건관리</a>
                        <ul>
                            <li class="pure-menu-item"><a href="/Requirement/Category" class="pure-menu-link">과목요건</a></li>
                            <li class="pure-menu-item"><a href="/Requirement/Type" class="pure-menu-link">과목유형요건</a></li>
                            <li class="pure-menu-item"><a href="/Requirement/Section" class="pure-menu-link">영역요건</a></li>
                            <li class="pure-menu-item"><a href="/Requirement/Score" class="pure-menu-link">총학점요건</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="/Result/Index" class="pure-menu-link">결과보기</a>
                    </li>

                    <li>
                        <a href="/Account/Update" class="pure-menu-link">회원관리</a>
                    </li>
                    <c:if test="${empty user}">
                        <li>
                            <a href="##" id="logout" class="pure-menu-heading">logout</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
     </div>
     <div id="main">
     	<layout:block name="contents">
    	</layout:block>	
   	</div>
        
    <script src="../resources/scripts/jquery-1.10.2.min.js"></script>
    <script src="../resources/scripts/jquery.validate.min.js"></script>
    <script src="../resources/scripts/jquery.form.js"></script>
    <script>
        $.ajaxSetup({ async: false });
    </script>
    <layout:block name="scripts">
    </layout:block>	
    <script type="text/javascript">
        $("#logout").click(function () {
            var url = "/Account/Logout";
            $(location).attr('href', url);
            return false;
        });
        $("a[href='@ViewBag.Href']").addClass("pure-selected");
    </script>
</body>
</html>