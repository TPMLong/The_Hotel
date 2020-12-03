<%-- 
    Document   : login
    Created on : Oct 29, 2020, 3:51:43 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/Body.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>      
        <header>
            <div class="main">
                <ul>
                    <li><a href="show">Home</a></li>
                    <li><a href="showCart">Room</a></li>
                    <li><a href="register">Register</a></li>
                    <li><a href="history">History</a></li>
                    <li class="active"><a href="try">Login</a></li>
                </ul>
            </div>
        </header>
        <div class="title">
            <h1>LOGIN</h1>
        </div>
        <form action="login" method="POST">
            <div class="form">
                <label><b>Username</b></label> <br/>
                <input type="text" placeholder="Username.." name="txtEmail" > <br/>
                <label><b>Password</b></label> <br/>
                <input type="password" placeholder="Password..." name="txtPassword" ><br/>
                
                <c:set var="pass" value="${sessionScope.WRONGPASS}"/>
                <c:if test="${not empty pass}">   
                    <label><b> ${sessionScope.WRONGPASS}</b></label> <br/>
                </c:if>
                <c:set var="error" value="${sessionScope.CAPTCHA}"/>
                <c:if test="${not empty error}">   
                    <label><b> ${sessionScope.CAPTCHA}</b></label> <br/>
                </c:if>
            </div>
            <div class="g-recaptcha"
                 data-sitekey="6LdhJd4ZAAAAAFigLd3ISK8DRVGyjVzWzKz4hfX5">                      
            </div>
            <div class="button-login">
                <input type="submit" class="btns" value="LOGIN" name="txtLogin"/>
                <input type="submit" class="btns" value="REGISTER" name="txtRes" /><br/>
                <a href="reset" style="margin-left: 40px">Forgot password</a>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
