<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-2"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="baseURL" value="${fn:substring(url, 0, fn:length(url) - fn:length(req.requestURI))}${req.contextPath}/" />

<html lang="en">
<head>


<meta charset="utf-8">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>



<script>
function btntest_onclick() 
{
    window.location.href = "${baseURL }userpanel"
}
</script>

<script >
function data_check()
{
var str=document.getElementById("l1").value;
if(str.length <=0){
alert("Please select one option ");
}else{
document.forms['drop_list'].submit();// working
}
}
</script>

<script>
  $(function() {
    $( "#dialog" ).dialog();
  });
  </script>


</head>
<body>



<t:basic>
Select room and date
</t:basic>
       <br>
       
     



       
<form action="reservation?${_csrf.parameterName}=${_csrf.token}" method="POST" >
<!-- <form action="reservation" method="POST" > -->

 
<c:if test="${roomNotSelected==1 }">

<div id="dialog" title="Select room!">
  <p> You have not been selected any room. <br> 
  		Click below calendar and select room.
  </p>
</div>

</c:if>

<c:if test="${addReservation==true }">

<div id="dialog" title="Booked successfully">
  <p> Your reservation has been added successfully.
  </p>
</div>

</c:if>

<c:if test="${addReservation==false }">

<div id="dialog" title="Something was wrong">
  <p> Selected room is occupied. <br> Select another time or day.
  </p>
</div>

</c:if>


<c:if test="${removedSuccessfully==true }">

<div id="dialog" title="Removed successfully">
  <p> Your reservation has been removed successfully.
  </p>
</div>

</c:if>


<br>
		Date: <input id="datepicker" name="mydate" type="hidden">
		<div id="datepicker-container"></div>
		
		<div style="text-align: center;">
		


		 <select name="room" id="l1" class="required">
<option value=''>Select room from list</option>
<option value="101a">101a</option>
<option value="102">102</option>
<option value="103a">103a</option>
<option value="103b">103b</option>
<option value="104">104</option>
<option value="105">105</option>
<option value="106b">106b</option>
<option value="113">113</option>
<option value="114">114</option>
<option value="201">201</option>
<option value="202">202</option>
<option value="203">203</option>
<option value="204">204</option>
<option value="207">207</option>
<option value="208">208</option>
<option value="209">209</option>
<option value="211">211</option>
<option value="214">214</option>
<option value="301">301</option>
<option value="305">305</option>
<option value="306">306</option>
<option value="307">307</option>
<option value="311">311</option>
<option value="313">313</option>

</select>
<br>
		<input type="submit"
			class="my-second-button" value="Search" >
		</div>
	</form>
	<script>
	$(function() {
		$("#datepicker-container").datepicker({ 
			dateFormat: 'yy-mm-dd',
			altField: '#datepicker',
			numberOfMonths: 3
		}).val();
	});
	
</script>

</body>
</html>