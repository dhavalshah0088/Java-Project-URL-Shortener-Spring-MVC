<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>URL SHORTENER</h2>

<a href="<c:url value="logout" />">Log Out</a>
<form:form method="POST" modelAttribute="urlForm" action="/RunningProject/shortener">
   <table>
    <tr>
        <td><form:label path="longUrl">LongUrl</form:label></td>
        <td><form:input path="longUrl" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
<c:if test="${empty listoflinks}">
        <b>No list of links in the DataBase</b><br /><br />
    </c:if>
        <c:forEach items="${listoflinks}" var="element"> 
   <h6>
  <tr>
    <td>Short URL: ${element.shortUrl}</td><br>
    <td>Long URL: ${element.longUrl}</td><br>
    <td>Clicks: ${element.clicks}</td><br>
    <br/><br/>
     </tr>
     </h6>
     
   
</c:forEach>


</body>
</html>