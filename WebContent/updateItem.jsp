<%@page import="org.hibernate.cache.ReadWriteCache.Item"%>
<%@page import="il.ac.hit.todolist.model.Items"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>update</title>
</head>
<body>
	<h1>Update Task</h1>
	<form action="http://localhost:8080/toDoList/router/list/updateItem">
		<%
			String id = request.getParameter("itemId");
			request.setAttribute("task", id);
		%>
		Descrption: <input type="text"
			value="<%=request.getParameter("description")%>" name="description"
			placeholder="Enter new description" required="required" /> </br> </br> Done: <select
			name="status" id="flipper" data-role="slider">
			<option value="true">Yes</option>
			<option value="false">No</option>
		</select> </br> </br> <input type="text" name="idTask" value="<%=id%>" hidden />
		DeadLine Date:<input id="deadLine" type="date"
			value="<%=request.getParameter("deadLineNew")%>" name="deadLine" />
		</br> </br>
		<script type="text/javascript">
			document.getElementById("deadLineNew").valueAsDate = new Date();
		</script>
		<input type="submit" value="Update now" /><br> <br> <a
			href="http://localhost:8080/toDoList/router/list/getList"
			data-theme="c" data-role="button" data-icon="home" data-inline="true">Back
			to list</a>
	</form>
</body>
</html>