<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>Edit Task</h1>
       <form:form method="POST" action="editsave" modelAttribute="task">
        <table >
        <tr>
        <td></td>
         <td><form:hidden path="id" /></td>
         </tr>
         <tr>
          <td>Name : </td>
          <td><form:input path="name" /></td>
         </tr>
         <tr>
          <td>Description :</td>
          <td><form:input path="description" /></td>
         </tr>
         <tr>
          <td>Deadline:</td>
          <td><form:input type="date" value="2024-03-08" path="deadline" /></td>
         </tr>
         <tr>
          <td>Status:</td>
          <td><form:input path="status" /></td>
         </tr>
         <tr>
          <td> </td>
          <td><input type="submit" value="Edit Save" /></td>
         </tr>
        </table>
       </form:form>