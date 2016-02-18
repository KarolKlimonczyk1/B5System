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
    </tr>
    <c:forEach items="${reservationList}" var="reservation">
        <tr>
            <td>${reservation.room}</td>
            <td width="250" >${reservation.dateStart} <br> ${reservation.dateFinish}</td>
            
            
            <td> <form action="remove_reservation?${_csrf.parameterName}=${_csrf.token}" method="POST" >
            		 <input id="room" name="room" value="${reservation.room}" type="hidden">
            		 <input id="dateStart" name="dateStart" value="${reservation.dateStart }" type="hidden">
            		 
            		 <input type="submit"
						class="my-second-button" value="Click to remove" >
            			</form>
            	</td>
        </tr>
    </c:forEach>
    </table>
</c:if>

<br><br><br><br><br>

</body>
</html>