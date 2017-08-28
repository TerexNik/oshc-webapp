<%--
  User: nterekhin
  Date: 28.08.2017
  Time: 11:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="ru">
<head>
    <title>Работники</title>

    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="../css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
<nav>
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}../">Главная</a></li>
                <li><a href="${pageContext.request.contextPath}../departments">Департаменты</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}">Работники</a></li>
                <li><a href="${pageContext.request.contextPath}../auditing">Аудит</a></li>
            </ul>
        </div>

        <div class="body">
            <h1>Все работники компании:</h1>
            <c:if test="${not empty employees}">
                <ul>
                    <table>
                        <tr>
                            <td width="80">ID</td>
                            <td width="120">Имя</td>
                            <td width="120">Фамилия</td>
                            <td width="120">Отчество</td>
                            <td width="120">Дата рождения</td>
                            <td width="80">Зарплата</td>
                            <td width="120">Грейд</td>
                            <td width="80"></td>
                            <td width="60"></td>
                        </tr>
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td>${employee.id}</td>
                                <td>${employee.name}</td>
                                <td>${employee.surname}</td>
                                <td>${employee.fatherName}</td>
                                <td>${employee.birthDate}</td>
                                <td>${employee.salary}</td>
                                <td>${employee.grade}</td>
                                <td><a href="<c:url value='/edit/${employee.id}'/>">редактировать</a></td>
                                <td><a href="<c:url value='/remove/${employee.id}'/>">удалить</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </ul>
            </c:if>
        </div>
    </div>
</nav>

</body>
</html>
