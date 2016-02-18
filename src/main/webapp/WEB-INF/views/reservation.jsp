<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="baseURL"
	value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>


  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  
  
</head>
<body>

 <script>
  $(function() {
    $( "#slider-range" ).slider({
      range: true,
      min: 0,
      max: 1440,
      step: 5,
      values: [ 720, 840 ],
      slide: function( event, ui ) {
    	  
    	  var hour1 = Math.floor(ui.values[0] / 60);
          var minute1 = ui.values[0] - (hour1 * 60);
          
          var hour2 = Math.floor(ui.values[1] / 60);
          var minute2 = ui.values[1] - (hour2 * 60);
          

          if(hour1.toString().length == 1) hour1 = '0' + hour1;
          if(minute1.toString().length == 1) minute1 = '0' + minute1;
          
          if(hour2.toString().length == 1) hour2 = '0' + hour2;
          if(minute2.toString().length == 1) minute2 = '0' + minute2;
          
    	  
          $( "#amount" ).val( hour1+":"+minute1 + "-" + hour2+":"+minute2 );
          
          $('#amount').html( hour1+":"+minute1 + "-" + hour2+":"+minute2 );
      }
  });
    
    $( "#amount" ).val(  "12:00" +
    	      "-" + "14:00" );
    
});
  </script>
  



<t:basic>

	<p class="mybody-content">
<!-- 		Reservation: <br>  -->
		Room: ${room } <br>
		Date: ${selectedDate }

	</p>

</t:basic>






	<form  class="mycenter"  action="reserved?${_csrf.parameterName}=${_csrf.token}"
		method="POST">
		
<!-- 		<form  class="mycenter"  action="reserved" method="POST"> -->


<p class="mybody-content"  >
Currently reserved: <br>
<c:forEach items="${timeList}" var="time">
			${time }
			</br>
			</c:forEach>
	</p>
		
		


 

<p>
  <label for="amount" > <font color="#f6931f" >Time range:   </font> </label>
  <input type="text" name="selectedTime" id="amount" readonly style="border:0; color:#f6931f; font-weight:bold;">
</p>

<div id="slider-range"></div>




		<input type="hidden" name="mydate" value=${selectedDate }> <input
			type="hidden" name="indexNumber"
			value=${pageContext.request.userPrincipal.name }> <input
			type="hidden" name="room" value=${room }>




						

<br>
<!-- <INPUT TYPE=submit name=reservationButton Value="Reservation" -->
<!-- 		class="btn btn-block btn-primary btn-default"> -->
<INPUT TYPE=submit name=reservationButton Value="Reservation"
		class="mybutton">
	</form>
	
	<br>
	<br>
	<br>
	<br>




</body>
</html>