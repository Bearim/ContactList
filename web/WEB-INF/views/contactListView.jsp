<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 10.3.19
  Time: 13.28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact List</title>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
 <form id="deleteForm" method="get">
     <div>
        <table class="table table-dark" cellspacing='0'>
        <tr>
            <th scope="col"> </th>
            <th scope="col">Name</th>
            <th scope="col">BirthDate</th>
            <th scope="col">Adress</th>
            <th scope="col">Company</th>>
            <th scope="col">Delete</th>
        </tr>
        <c:forEach items="${contactList}" var="contact" >
            <tr>
                <td><input type="checkbox" name="check" value= "${contact.id}" /> </td>
                <td><a href="editContact?id=${contact.id}">${contact.name}</a></td>
                <td>${contact.birthDate}</td>
                <td>${contact.adress}</td>
                <td>${contact.placeOfWork}</td>
                <td>
                    <a href="deleteContact?id=${contact.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
         </div>
    <button type="submit" formaction="${pageContext.request.contextPath}/sendEmail" class="btn btn-primary btn-lg">Send Email</button>
    <span>|</span>
    <button type="submit" formaction="${pageContext.request.contextPath}/newDelete" class="btn btn-secondary btn-lg">Delete</button>
</form>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
