<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<a href="<c:url value="login" />">Back</a>

<h2>URL SHORTENER</h2>
<c:if test="${ not empty longLink}">
	<h3>SHORT URL :=</h3>
	${longLink.shortUrl }
	<h3>LONG URL:=</h3>
	${longLink.longUrl }
    </c:if>
    <c:if test="${CheckNull}">
        <b>NO SUCH SHORT URL EXIST.</b><br /><br />
    </c:if></body>
</html>