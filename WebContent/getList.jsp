<%@page import="java.net.URLEncoder"%>
<%@page import="il.ac.hit.todolist.model.Items"%>
<%@page import="il.ac.hit.todolist.model.Users, java.util.*"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<style>
td {
	white-space: -o-pre-wrap;
	word-wrap: break-word;
	white-space: pre-wrap;
	white-space: -moz-pre-wrap;
	white-space: -pre-wrap;
}

table {
	table-layout: fixed;
	width: 100%
}	
	}
	</style>
</head>
<title>Item List</title>
<body>
	<%
		Users user = (Users) request.getSession().getAttribute("userLogged");
		String username = user.getUsername();
		int listId = user.getListId();
	%>
	<table class="table table-dark">
		<div class="row">
			<div class="col-md-12 d-flex">
				<h1>
					<%=username + "'s"%>
					List
				</h1>
				<a href="http://localhost:8080/toDoList/router/user/logout"
					type="button" class="btn btn-primary ml-auto">LogOut </a>
			</div>
		</div>
		<tr>
			<td>ID</td>
			<td>DESCRIPTION</td>
			<td>DEADLINE</td>
			<td>DONE</td>
			<td>UPDADE</td>
			<td>DELETE</td>
		</tr>
		<%
			int itemNumber = 0;
			Iterator<Items> iterator = (Iterator<Items>) request.getSession().getAttribute("items");
			while (iterator.hasNext()) {
				Items currItem = (Items) iterator.next();
				itemNumber++;
				String des = currItem.getDescription();
				String dead = currItem.getDeadLine();
				boolean done = currItem.getDone();
				int id = currItem.getId();
		%>
		<tr>
			<td><%=itemNumber%></td>
			<td><%=des%></td>
			<td><%=dead%></td>
			<td><%=String.valueOf(done)%></td>
			<td><a
				href=http://localhost:8080/toDoList/updateItem.jsp?itemId=<%=id%>&description=<%=URLEncoder.encode(des, "UTF-8")%>&deadLineNew=<%=dead%>>update</a></td>
			<td><a
				href=http://localhost:8080/toDoList/router/list/deleteItem?itemId=<%=id%>>delete</a></td>
		</tr>
		<%
			}
		%>
	</table>

	<h1 align="center">Add new task:</h1>
	<div
		class="d-flex justify-content-center align-items-center container ">
		<div class="row ">
			<form method="POST"
				action="http://localhost:8080/toDoList/router/list/addItem?listId=<%=listId%>">
				Task Description:<input id="taskDsctiption" type="text"
					name="taskDsctiption" required="required" /> <br> DeadLine
				Date:<input id="deadLine" type="date" name="deadLine" /> <br>
				<input type="submit" class="btn btn-success" value="add task" />
			</form>
		</div>
	</div>
	<script type="text/javascript">
		document.getElementById("deadLine").valueAsDate = new Date();
	</script>
</body>
</html>