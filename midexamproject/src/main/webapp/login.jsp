<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

 <link href="login.css" rel="stylesheet"> 
</head>
<body>
<section class="home">
    <div class="open-overlay">
   
        <form action="login" method="post" >
          <h2>Login</h2>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required value="">
            <br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required value="">
            <br>

            <button class="btn" type="submit" value="Login">Login</button>
            <p>Don't have an account?<a href="createAcc.jsp">Signup</a></p>
        </form>
    </div>
</section>
</body>
</html>