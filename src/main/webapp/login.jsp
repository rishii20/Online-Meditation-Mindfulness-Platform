<html>
<head><title>Login</title></head>
<body>
  <h3>Login</h3>
  <form method="post" action="auth/login">
    Email: <input type="email" name="email" required /><br/>
    Password: <input type="password" name="password" required /><br/>
    <button type="submit">Login</button>
  </form>
  <c:if test="${not empty error}">
    <div style="color:red">${error}</div>
  </c:if>
</body>
</html>
