<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 17.3.19
  Time: 19.21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Email</title>
    <style><%@include file='/WEB-INF/css/form_style.css' %></style>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>

<p style="color: red;">${errorString}</p>
<c:set var="IDs" value="${IDs}" scope="session"></c:set>
<div class="container">
    <div class="row">
        <form class="contact_form" method="post" name="contact_form" action="${pageContext.request.contextPath}/sendEmail">
        <ul>
            <c:forEach items="${list}" var="email" >
                <li>
                    <label >Recipient adress:</label>
                    <input type="text" name="to" size="50" value="${email}" readonly/>
                </li>
            </c:forEach>

            <li>
                <label>Subject:</label>
                <input type="text" name="subject" placeholder="Task" />
            </li>
            <li>
                <label>Template:</label>
                <select onchange="getSelectedText('select')" id="select" name="select">
                    <option value=""></option>
                    <option>Hello &lt;name&gt;!</option>
                    <option>Goodbye &lt;name&gt;</option>
                </select>
            </li>
            <li>
                <label>Message text:</label>
                <textarea id="textarea" name="messageText" cols="40" rows="6" ></textarea>
            </li>
            <li>
                <button class="submit" type="submit">Send</button>
            </li>
        </ul>
        </form>
    </div>
</div>

<script>
    function getSelectedText(elementId) {
        var elt = document.getElementById(elementId);

        if (elt.selectedIndex == -1)
            return null;

        var input = document.getElementById("textarea");

        input.value = elt.options[elt.selectedIndex].text;

    }
</script>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
