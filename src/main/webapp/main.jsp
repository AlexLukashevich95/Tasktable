<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task Manager</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            // Получение списка тасков при загрузке страницы
            loadTasks();

            // Функция загрузки всех задач с помощью GET запроса
            function loadTasks() {
                $.ajax({
                    url: "http://localhost:8080/main",
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        let taskList = $('#taskList');
                        taskList.empty(); // Очищаем список перед добавлением новых задач
                        $.each(data, function (index, task) {
                            console.log(task); // Проверьте, что task действительно содержит данные
                            taskList.append(
                                `<li id="task-${task.id}">
                                    <span>${task.name}</span> -
                                    <span>${task.description}</span> -
                                    <span>${task.deadline}</span> -
                                    <span>${task.status}</span>
                                    <button onclick="deleteTask(${task.id})">Удалить</button>
                                    <button onclick="showEditTask(${task.id}, '${task.name}', '${task.description}', '${task.deadline}', '${task.status}')">Изменить</button>
                                </li>`
                            );
                        });
                    },
                    error: function (err) {
                        alert("Ошибка при загрузке задач");
                    }
                });
            }

            // Добавление новой задачи с помощью POST запроса
            $('#addTaskForm').submit(function (e) {
                e.preventDefault();
                let name = $('#name').val();
                let description = $('#description').val();
                let deadline = $('#deadline').val();
                let status = $('#status').val();

                $.ajax({
                    url: "http://localhost:8080/main",
                    type: "POST",
                    contentType: "application/json",
                    data: JSON.stringify({ name: name, description: description, deadline: deadline, status: status }),
                    success: function () {
                        loadTasks();
                        $('#name').val();
                        $('#description').val();
                        $('#deadline').val();
                        $('#status').val();
                    },
                    error: function (err) {
                        alert("Ошибка при добавлении задачи");
                    }
                });
            });

            // Обновление задачи с помощью PUT запроса
            $('#editTaskForm').submit(function (e) {
                e.preventDefault();
                let taskId = $('#editTaskId').val();
               let name = $('#name').val();
               let description = $('#description').val();
               let deadline = $('#deadline').val();
               let status = $('#status').val();

                $.ajax({
                    url: "http://localhost:8080/main/${taskId}",
                    type: "PUT",
                    contentType: "application/json",
                    data: JSON.stringify({ name: name, description: description, deadline: deadline, status: status  }),
                    success: function () {
                        loadTasks();
                        $('#editTaskModal').hide();
                    },
                    error: function (err) {
                        alert("Ошибка при обновлении задачи");
                    }
                });
            });

            // Удаление задачи с помощью DELETE запроса
            window.deleteTask = function (taskId) {
                $.ajax({
                    url: "http://localhost:8080/main/${taskId}",
                    type: "DELETE",
                    success: function () {
                        loadTasks();
                    },
                    error: function (err) {
                        alert("Ошибка при удалении задачи");
                    }
                });
            };

            // Показ формы редактирования
          window.showEditTask = function (taskId, title, description) {
              $('#editTaskId').val(taskId);
              $('#editName').val(name);
              $('#editDescription').val(description);
              $('#editDeadline').val(deadline);
              $('#editStatus').val(status);
              $('#editTaskModal').show();
          };

          // Скрытие формы редактирования
          $('#editTaskModalClose').click(function () {
              $('#editTaskModal').hide();
          });
          });
    </script>
</head>
<body>
<h1>Task Manager</h1>

<h2>Добавить новую задачу</h2>
<form id="addTaskForm" action="/main" method="post">
    <label for="title">Название задачи:</label>
    <input type="text" id="name" name="name" required>

    <label for="description">Описание задачи:</label>
    <input type="text" id="description" name="description" required>

    <label for="deadline">Дедлайн задачи:</label>
    <input type="datetime-local" id="deadline" name="deadline" required>

    <label for="status">Статус:</label>
        <select id="status" name="status" required>
            <c:forEach var="status" items="${taskStatuses}">
                <option value="${status}">${status}</option>
            </c:forEach>
    </select>

    <button type="submit">Добавить задачу</button>
</form>

<h2>Список задач</h2>
<ul id="taskList">
    <!-- Задачи будут загружены сюда динамически -->
</ul>

<!-- Модальное окно для редактирования задачи -->
<div id="editTaskModal" style="display:none;">
    <h2>Редактировать задачу</h2>
    <form id="editTaskForm">
        <input type="hidden" id="editTaskId" name="taskId">
        <label for="editTitle">Название задачи:</label>
        <input type="text" id="editTitle" name="title" required>
        <label for="editDescription">Описание задачи:</label>
        <input type="text" id="editDescription" name="description" required>
        <button type="submit">Сохранить изменения</button>
    </form>
    <button id="editTaskModalClose">Закрыть</button>
</div>
</body>
</html>
