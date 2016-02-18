
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="baseURL"
	value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />



<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>



<html>
<head>
<title>WIMiIP B5</title>

</head>
<body>
	
	<!--  *************MENU*********************** -->
	<div class="menu-container">
		<ul>
			<li><a href="http://localhost:8080/app/">Home</a></li>
			<li><a
				href="http://localhost:8080/app/userpanel/">User
					panel</a></li>
			<li><a href="http://localhost:8080/app/logout">Logout</a></li>
		</ul>
	</div>
	
	<br>
	<br>
	
	<!--  **************************************** -->
	<div class="mybody-content">
		<jsp:doBody />
	</div>

	<div class="myfooter">
		<ul>
			
			<li>
				<a href="https://www.facebook.com/"	class="myfooter-facebook"></a> 
			</li>
			<li>
				<a href="https://twitter.com/?lang=pl" class="myfooter-twitter"></a>
			</li>
			
			<li>
				<a href="https://www.youtube.com/" class="myfooter-youtube"></a>
			</li>
			

		</ul>
	</div>
</body>
</html>