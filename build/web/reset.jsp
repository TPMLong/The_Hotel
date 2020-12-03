<%-- 
    Document   : reset
    Created on : Nov 3, 2020, 5:11:14 AM
    Author     : ACER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Page</title>
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
                        <li><a href="try">Logout</a></li>
                        <font color ="white" id="welcome">Welcome, ${name.name}
                        </font>
                    </c:if> 
                </ul>
            </div>
            <c:set var="mess" value="${requestScope.MESS}"/>
            <c:set var="code" value="${sessionScope.CODES}"/>
                <c:if test="${empty mess}">
                    <form action="send" method="POST">
                        <div class="title">
                            <h1>Reset password</h1>
                        </div>
                        <c:if test="${empty code}">
                            <div class="form">
                                <label><b>Enter Gmail</b></label> <br/>
                                <input type="text" placeholder="Gmail.." name="txtEmail" > <br/>
                            </div>
                            <div class="button-login">
                                <input type="submit" class="btns" value="RESET" name="txtLogin"/>
                            </div>
                        </c:if>
                    </form>
                </c:if>
                <c:if test="${not empty mess}">
                    <div class="form">
                        <label><b>${mess}</b></label> <br/>
                    </div>
                </c:if>
            <c:if test="${not empty code}">
                <form action="check" method="POST">
                    <div class="form">
                        <label><b>Enter Code</b></label> <br/>
                        <input type="text" placeholder="Code..." name="txtCode" > <br/>
                    </div>
                    <div class="button-login">
                        <input type="submit" class="btns"/>
                    </div>
                </form>
            </c:if>
        </header>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
