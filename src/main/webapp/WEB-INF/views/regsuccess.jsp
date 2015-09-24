<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<%@ include file="header.jsp"%>	
	<h2 align="center">Registration Successful!! Welcome <c:out value="${userAccount.email}" />. Please login to continue</h2>
</body>
</html>