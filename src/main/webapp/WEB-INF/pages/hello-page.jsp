<%--
  Created by IntelliJ IDEA.
  User: nterekhin
  Date: 23.08.2017
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<head>
    <title>Главная</title>
</head>
<body>
<nav>
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/">Главная</a></li>
                <li><a href="${pageContext.request.contextPath}/departments/">Департаменты</a></li>
                <li><a href="${pageContext.request.contextPath}/employees/">Работники</a></li>
                <li><a href="${pageContext.request.contextPath}/auditing/">Аудит</a></li>
            </ul>
        </div>

        <div class="body">
            <h1>Добро пожаловать в нашу систему OSHC</h1>
            <p>Чтобы приступить к работе выберите нужный пункт в меню сверху</p>
        </div>
    </div>
</nav>
</body>
</html>