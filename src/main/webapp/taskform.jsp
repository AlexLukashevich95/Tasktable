<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    java.time.LocalDateTime currentDate = java.time.LocalDateTime.now();
%>

<h1>Add New Task</h1>
<form:form method="post" action="save" modelAttribute="task">
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><form:input path="description" /></td>
        </tr>
        <tr>
              <td>Deadline:</td>
              <td><form:input type="datetime-local" value="<%= currentDate %>" path="deadline" /></td>
        </tr>
        <tr>
            <td>Stat:</td>
            <td>
                <select id="statusField" name="status">
                    <c:forEach var="status" items="${status}">
                        <option value="${status.name}">
                            ${status.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save" /></td>
        </tr>
    </table>
</form:form>
