<%--
  User: nterekhin
  Date: 29.08.2017
  Time: 13:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="ru">
<head>
    <title>Добавление новго сотрудника</title>
</head>
<body>
<nav>
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Spring Boot</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}../../">Главная</a></li>
                <li><a href="${pageContext.request.contextPath}../../departments">Департаменты</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}../">Работники</a></li>
                <li><a href="${pageContext.request.contextPath}../../auditing">Аудит</a></li>
            </ul>
        </div>

        <div class="body">

            <h1>Добавить сотрудника</h1>
            <%--@elvariable id="employee" type="ru.OSHC.entity.Employee"--%>
            <form:form method="POST" action="add" modelAttribute="employee">
                <table>
                    <tr>
                        <td><form:label path="name">Имя</form:label></td>
                        <td><form:input path="name"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="surname">Фамилия</form:label></td>
                        <td><form:input path="surname"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="fatherName">Отчество</form:label></td>
                        <td><form:input path="fatherName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="birthDate">Дата рождения</form:label></td>
                        <td><form:input path="birthDate"/> </td>
                    </tr>
                    <tr>
                        <td><form:label path="salary">Зарплата</form:label></td>
                        <td><form:input path="salary"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="grade">Грейд</form:label></td>
                        <td><form:input path="grade"/></td>
                    </tr>
                    <tr>
                        <fmt:parseDate value="${employee.birthDate}" pattern="dd/MM/yyyy"/>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </table>
            </form:form>

            <a href="../employee/">Назад к списку сотрудников</a>
        </div>
    </div>
</nav>
</body>
</html>
