<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--외부 라이브러리 -->
<script type="text/javascript" src="/dashboard/resources/js/sockjs.min.js"></script>
<script type="text/javascript" src="/dashboard/resources/js/stomp.min.js"></script>

<!-- 커스텀 유틸  -->
<link rel="stylesheet" href="/dashboard/resources/css/defaultstyle.css" type="text/css"/>
<script type="text/javascript" src="/dashboard/resources/js/xhr.js"></script>
<script type="text/javascript" src="/dashboard/resources/js/util.js"></script>


<!-- 페이지 js, css -->
<link rel="stylesheet" href="/dashboard/resources/css/header.css" type="text/css">
<link rel="stylesheet" href="/dashboard/resources/css/board.css" type="text/css">
<link rel="stylesheet" href="/dashboard/resources/css/crawling.css" type="text/css">
<link rel="stylesheet" href="/dashboard/resources/css/wmemoAndwchat.css" type="text/css">

<script type="text/javascript" src="/dashboard/resources/js/board.js"></script>

<script type="text/javascript" src="/dashboard/resources/js/crawling.js"></script>
<script type="text/javascript" src="/dashboard/resources/js/wmemo.js"></script>
<script type="text/javascript" src="/dashboard/resources/js/wchat.js"></script>

<script type="text/javascript" src="/dashboard/resources/js/widgetaddandmodify.js"></script>
<script type="text/javascript" src="/dashboard/resources/js/widgetload.js"></script>


</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<script type="text/javascript" src="/dashboard/resources/js/header.js"></script>
	
	<section>
		<article id="content">
		</article>
	</section>
			
</body>
</html>