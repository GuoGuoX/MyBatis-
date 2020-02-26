<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<table>
        <tr>
            <td>id</td>
            <td>userName</td>
            <td>age</td>
            <td>gender</td>
        </tr>
    <c:forEach items="${usersList}" var="u">
        <tr>
                <td>${u.id}</td>
                <td>${u.userName}</td>
                <td>${u.age}</td>
                <td>${u.gender}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
