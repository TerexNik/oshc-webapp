<%--
  User: nterekhin
  Date: 28.08.2017
  Time: 11:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ru">
<head>
    <title>Работники</title>
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
                    <table border="1px">
                        <tr>
                            <td width="35">ID</td>
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
                                <td><a href="<c:url value='${pageContext.request.contextPath}edit/${employee.id}'/>">редактировать</a></td>
                                <td><a href="<c:url value='${pageContext.request.contextPath}remove/${employee.id}'/>">удалить</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </ul>
            </c:if>

            <a href="${pageContext.request.contextPath}add">Добавить сотрудника</a>
        </div>
    </div>
</nav>

</body>
</html>
