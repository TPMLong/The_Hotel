<%-- 
    Document   : discount
    Created on : Nov 5, 2020, 10:39:04 AM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Discount Page</title>
        <link rel="stylesheet" type="text/css" href="css/Body.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>      
        <header>
            <div class="main">
                <ul>
                    <li><a href="show">Home</a></li>
                    <li><a href="showCart">Room</a></li>
                    <li class="active"><a href="register">Register</a></li>
                    <li><a href="history">History</a></li>
                        <c:set var="name" value="${sessionScope.NAME}"/>
                        <c:if test="${empty name}">
                        <li><a href="try">Login</a></li>
                    </c:if>
                </ul>
            </div>
        </header>
        <div class="title">
            <h1>CREATE DISCOUNT</h1>
        </div>
        <form action="createDiscount" method="POST">
            <div class="form">
                <label><b>Name</b></label> <br/>
                <input type="text" placeholder="Name.." name="txtName" > <br/>
                <label><b>Discount percent</b></label> <br/>
                <input type="text" placeholder="Percent.." name="txtPercent" > <br/>
                <label><b>Expire date</b></label> <br/>
                <input type="date" placeholder="Expire day..." name="txtExpire" > 
            </div>
            <div class="button">
                <input type="submit" class="btns" value="CREATE" />
                <input type="reset" class="btns" value="RESET" />
            </div>
        </form>
        
                        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
