<%--
  Created by IntelliJ IDEA.
  User: Liwei
  Date: 3/11/19
  Time: 9:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h:outputStylesheet library="css" name="bootstrap.min.css"/>
    <title>Login</title>
</head>
<body>
    <form action="login.login(username, password)">
        <div class="form-group">
            <label for="usernameInputField">Username</label>
            <input id="usernameInputField" type="text" class="form-control" aria-describedby="usernameHelp" placeholder="Enter username">
        </div>
        <div class="form-group">
            <label for="passwordField">Password</label>
            <input id="passwordField" type="password" class="form-control" aria-describedby="passwordHelp" placeholder="Password">
            <small id="passwordHelp" class="text-muted form-text">Do not share your password with anyone.</small>
        </div>
        <button class="btn btn-primary" type="submit">Submit</button>
    </form>
</body>
</html>
