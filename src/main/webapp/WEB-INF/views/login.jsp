<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WIMiIP B5</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>

<body>

	<div id="mainWrapper">
		<div class="login-container">
			<div class="login-card">
				<div class="login-form">
					<p class="mybody-login-text">Welcome to the reservation system.</p>
					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post" class="form-horizontal">
						
<!-- ------------------------------------------------------------------ -->

						<c:if test="${ empty pageContext.request.userPrincipal}">
							<div class="input-group input-sm">
								<label class="input-group-addon" for="username"><i
									class="fa fa-user"></i></label> <input type="text" class="form-control"
									id="username" name="indexNumber"
									placeholder="Enter Index Number" required>
							</div>
							<div class="input-group input-sm">
								<label class="input-group-addon" for="password"><i
									class="fa fa-lock"></i></label> <input type="password"
									class="form-control" id="password" name="password"
									placeholder="Enter Password" required>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

							<div class="form-actions">
								<input type="submit"
									class="btn btn-block btn-primary btn-default" value="Log in">
<!-- 										class="btn btn-block btn-primary btn-default" value="Log in"> -->
							</div>
						</c:if>
<!-- ------------------------------------------------------------------ -->
						<br>
						<c:if test="${param.error != null}">
							<div class="alert alert-danger">
								<p>Invalid index number or password.</p>
							</div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success">
								<p>You have been logged out successfully.</p>
							</div>
						</c:if>
						
						<!-- Jesli ktos jest juz zalogowany  -->
	
						<c:if test="${not empty pageContext.request.userPrincipal}">
  						  You are logged in as: <c:out value="${pageContext.request.userPrincipal.name}" />
  						 
  						  <br> <a href="<c:url value="/logout" />">Logout</a>
						</c:if>

					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>