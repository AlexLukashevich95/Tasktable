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
          <td><form:input type="datetime-local" path="deadline" /></td>
         </tr>
         <tr>
             <td>Stat:</td>
             <td>
                 <select id="statusField" name="status">
                     <c:forEach var="status" items="${status}">
                         <option value="${status.name}"
                             <c:if test="${status.name == selectedStatus}">
                                 selected="selected"
                             </c:if>>
                             ${status.name}
                         </option>
                     </c:forEach>
                 </select>
             </td>
         </tr>
         <tr>
          <td> </td>
          <td><input type="submit" value="Edit Save" /></td>
         </tr>
        </table>
       </form:form>

       <c:if test="${not empty errorMessage}">
               <div class="error-message">
                   ${errorMessage}
               </div>
           </c:if>