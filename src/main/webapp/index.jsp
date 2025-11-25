<%@ page import="com.mindful.model.User" %>
<%
  User u = (User) session.getAttribute("user");
%>
<html>
<head><title>Mindfulness App</title></head>
<body>
  <h2>Welcome to Mindfulness Platform</h2>
  <% if(u != null) { %>
    <p>Hello, <%= u.getName() %> (Role ID: <%= u.getRoleId() %>)</p>
    <a href="/auth/logout">Logout</a>
  <% } else { %>
    <p><a href="login.jsp">Login</a></p>
  <% } %>
</body>
</html>
