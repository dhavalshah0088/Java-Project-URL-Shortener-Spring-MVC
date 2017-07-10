<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>
<a href="<c:url value="login" />">Back</a>

<h2>URL SHORTENER</h2>
<form:form method="POST" modelAttribute="publicForm" action="/RunningProject/publicForm">
   <table>
    <tr>
        <td><form:label path="shortUrl">SHORT URL</form:label></td>
        <td><form:input path="shortUrl" /></td>
    </tr>
       <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
</body>
</html>