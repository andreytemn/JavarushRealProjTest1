<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 20.05.2016
  Time: 4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list - andreytemn</title>
</head>
<body>
<h2>List of users</h2>
<div>
    <c:if test="${!empty users}">
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Is Admin</th>
                <th>Created</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.admin}</td>
                    <td>${user.createdDate}</td>
                    <td><a href="<c:url value='/edit/${user.id}'/>">Edit</a></td>
                    <td><a href="<c:url value='/remove/${user.id}'/>">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <div id="pagination">

            <c:url value="list" var="prev">
                <c:param name="page" value="${page-1}"/>
            </c:url>
            <c:if test="${page>1}">
                <a href="<c:out value="${prev}"/>">Prev</a>
            </c:if>

            <c:forEach begin="1" end="${pageCount}" step="1" varStatus="i">

                <c:choose>

                    <c:when test="${page==i.index}">
                        <span>${i.index}</span>
                    </c:when>

                    <c:otherwise>
                        <c:url value="list" var="url">
                            <c:param name="page" value="${i.index}"/>
                        </c:url>
                        <a href="<c:out value="${url}"/>">${i.index}</a>
                    </c:otherwise>

                </c:choose>

            </c:forEach>

            <c:url value="list" var="next">
                <c:param name="page" value="${page+1}"/>
            </c:url>

            <c:if test="${page+1<=pageCount}">
                <a href="<c:out value="${next}"/>">Next</a>
            </c:if>

        </div>
    </c:if>
</div>
<h2>Add new user</h2>
<div>
    <form:form action="/list/add" commandName="user">
        <table>
            <c:if test="${!empty user.name}">
                <tr>
                    <td>
                        <form:label path="id">
                            <spring:message text="ID"/>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="age">
                        <spring:message text="Age"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="age"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="admin">
                        <spring:message text="Admin"/>
                    </form:label>
                </td>
                <td>
                    <form:checkbox path="admin"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${!empty user.name}">
                        <br>
                        <input type="submit" value="<spring:message text="Edit User"/>"/>
                    </c:if>
                    <c:if test="${empty user.name}">
                        <br>
                        <input type="submit" value="<spring:message text="Add User"/>"/>
                    </c:if>
                </td>
            </tr>
        </table>
    </form:form>
</div>
<br>
<h2>Find User by ID</h2>
<form action="/list/{id}" class="form-inline">
    <div class="form-group">
        <input type="search" name="id">
    </div>
    <br>
    <input type="submit" value="Find"/>
</form>
<h2>Create test data</h2>
<a href="<c:url value="/testData"/>">Click here to create test data</a>
</body>
</html>
