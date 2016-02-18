<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="menu-container">
	<ul>
		<li>
			<a href="${baseURL }">Home</a>
		</li>
		<li>
			<a href="${baseURL}userpanel/${pageContext.request.userPrincipal.name}">User panel</a>
		</li>
		<li>
			<a href="${baseURL }/logout">Logout</a>
		</li>
	</ul>
</div>


logout page
</body>
</html>