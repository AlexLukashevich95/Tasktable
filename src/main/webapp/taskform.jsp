<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <h1>Add New Task</h1>
       <form:form method="post" action="save">
        <table >
         <tr>
          <td>Name : </td>
          <td><form:input path="name"  /></td>
         </tr>
         <tr>
          <td>Salary :</td>
          <td><form:input path="description" /></td>
         </tr>
         <tr>
          <td>Designation :</td>
          <td><form:input path="deadline" /></td>
         </tr>
         <tr>
          <td>designation :</td>
          <td><form:input path="status" /></td>
         </tr>
         <tr>
          <td> </td>
          <td><input type="submit" value="Save" /></td>
         </tr>
        </table>
       </form:form>