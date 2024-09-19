 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Tasks List</h1>
<table border="2" width="70%" cellpadding="2">
<tr><th>Id</th><th>Name</th><th>Description</th><th>Deadline</th><th>Status</th><th>Edit</th><th>Delete</th></tr>
   <c:forEach var="task" items="${list}">
   <tr>
   <td>${task.id}</td>
   <td>${task.name}</td>
   <td>${task.description}</td>
   <td>${task.deadline}</td>
   <td>${task.status}</td>
   <td><a href="edittask/${task.id}">Edit</a></td>
   <td><a href="deletetask/${task.id}">Delete</a></td>
   </tr>
   </c:forEach>
   </table>
   <br/>
   <a href="taskform">Add New Task</a>

   <c:if test="${not empty errorMessage}">
                  <div class="error-message">
                      ${errorMessage}
                  </div>
              </c:if>