<%-- 
    Document   : change
    Created on : Nov 3, 2020, 11:10:39 AM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Page</title>
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
                        <c:set var="name" value="${sessionScope.NAME}"/>
                        <c:if test="${empty name}">
                        <li><a href="try">Login</a></li>
                        </c:if>
                        <c:if test="${not empty name}">
                        <li><a href="logout">Logout</a></li>
                        <font color ="white" id="welcome">Welcome, ${name.name}
                        </font>
                    </c:if> 
                </ul>
            </div>
            <form action="changePass" method="POST">
                <div class="title">
                    <h1>Reset password</h1>
                </div>
                <div class="form">
                    <c:set var="error" value="${sessionScope.ERROR}"/>
                    <c:if test="${not empty error}">        
                        <label><b>${sessionScope.ERROR}</b></label> <br/>
                    </c:if>
                    <label><b>Enter New Password</b></label> <br/>
                    <input type="password" placeholder="New Password.." name="txtPassword" > <br/>
                    <label><b>Enter Confirm Password</b></label> <br/>
                    <input type="password" placeholder="Confirm Password.." name="txtConfirmPassword" > <br/>
                    <input type="hidden" name="gmail" value="${requestScope.USER}" />
                    <input type="hidden" name="code" value="${requestScope.CODE}" />
                </div>
                <div class="button-login">
                    <input type="submit" class="btns" value="RESET"/>
                </div>

            </form>
        </header>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
