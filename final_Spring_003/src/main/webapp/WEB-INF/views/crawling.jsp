<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/js/util.js?ver=1"></script>
<script type="text/javascript" src="./resources/js/crawling.js?ver=1"></script>
</head>
<link rel="stylesheet" href="./resources/css/crawling.css?ver=1"
	type="text/css" />
<body>
<<<<<<< HEAD
	<div class="search" id="search">
		<input type="text" id="keyword" onkeyup="enterKey()"/>
=======
	<div class="search">
		<input type="text" class="keyword" />
>>>>>>> 9e3442acd1cc365989de2f66fb3958623e070e38
		<img src="./resources/images/crwl_2.png" onclick="crawling()"/>
		<img src="./resources/images/crwl_1.png" onclick="crawling()"/>
	</div>
	
	<div class="container" data-keyword></div>
</body>
</html>