<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>


<body>

	<t:basic>

	User's panel: 
		<c:out value="${pageContext.request.userPrincipal.name}" />

	</t:basic>
<br>

		
<c:if test="${!empty reservationList}">
    <table  border=3>
    <tr>
        <th width="80">Room</th>
        <th width="120">Date</th>
        <th width="90">Delete</th>
<!--         <th width="60">Edit</th> -->
<!--         <th width="60">Delete</th> -->
    </tr>
    <c:forEach items="${reservationList}" var="reservation">
        <tr>
            <td>${reservation.room}</td>
            <td width="250" >${reservation.dateStart} <br> ${reservation.dateFinish}</td>
            
            <td><a href="<c:url value='/remove_reservation/${reservation.room}/${reservation.dateStart }/${pageContext.request.userPrincipal.name}' />
            " >Click to remove</a></td> 
<%--             <td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td> --%>
<%--             <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td> --%>
        </tr>
    </c:forEach>
    </table>
</c:if>

<br><br><br><br><br>

</body>
</html>