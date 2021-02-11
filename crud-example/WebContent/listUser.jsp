<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.crud.model.User"%>
<%@ page import="com.crud.dao.UserDao"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All User</title>
</head>
<body>
<%
UserDao dao = new UserDao();
List<User> userList = dao.getAllUsers();
%>
<table border="1">
<tr>
<th>Id</th>
<th>First Name</th>
<th>Last Name</th>
<th>Email</th>
</tr>
<tr>
<%
for (User user : userList) {
%>
<td><%=user.getId()%></td>
<td><%=user.getfName()%></td>
<td><%=user.getlName()%></td>
<td><%=user.getEmail()%></td>
<td><a href="UserServ?action=editform&userId=<%=user.getId()%>">Update</a></td>
<td><a href="UserServ?action=delete&userId=<%=user.getId()%>">Delete</a></td>
</tr>
<%
}
%>
</table>
<p><a href="UserServ?action=insert">Add User</a></p>
</body>
</html>