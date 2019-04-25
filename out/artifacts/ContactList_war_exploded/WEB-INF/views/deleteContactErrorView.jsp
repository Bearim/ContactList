<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 11.3.19
  Time: 1.38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Product</title>
</head>

<body>

<jsp:include page="_header.jsp"></jsp:include>

<h3>Delete Contact</h3>

<p style="color: red;">${errorString}</p>
<a href="contactList">Contact List</a>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
