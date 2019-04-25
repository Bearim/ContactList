<%--
  Created by IntelliJ IDEA.
  User: kirill
  Date: 10.3.19
  Time: 13.23
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>navigation-with-button</title>
    <link rel="stylesheet" href="/WEB-INF/css/header_style.css">
</head>

<body>
    <nav class="navbar navbar-dark bg-dark">
        <a href="${pageContext.request.contextPath}/contactList" class="navbar-brand">Contact List</a>
        <span class="navbar-brand">|</span>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

            <li class="nav-item active">
                <a href="${pageContext.request.contextPath}/createContact" class="navbar-brand">Create Contact</a>
            </li>
        </ul>
        <form action="${pageContext.request.contextPath}/search" class="form-inline">
            <div class="form-group mx-sm-3 mb-2">
                <input type="search" class="form-control" name="search" value="${querry}" placeholder="search"/>
            </div>
            <button type="submit" class="btn btn-primary mb-2">Search</button>
        </form>
    </nav>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

</html>