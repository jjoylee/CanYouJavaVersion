<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="../resources/css/pure-min.css">
    <link rel="stylesheet" href="../resources/css/marketing.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <layout:block name="title">
    </layout:block>	
    <style>
        .pure-form
        {
            width :80%;
        }
        body
        {
            margin-left:20%;
        }
        label
        {
            text-align:left !important; 
        }
    </style>
</head>
<body>
    <div>
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
</body>
</html>
