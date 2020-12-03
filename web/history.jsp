<%-- 
    Document   : history
    Created on : Nov 2, 2020, 2:48:05 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
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
                <div class="main">
                    <form action="searchDate" method="POST">
                        <input type="text" class="check-date" name="checkin" value="">
                        <select name="cboBook" class="check-text">                                                 
                            <option value="name">
                                name                     
                            </option>   
                            <option value="checkin">
                                date
                            </option>   
                        </select>
                        <input type="submit" class="btns" value="SEARCH"/>
                    </form>
                </div>  
            </div>         
        </header>
        <div class="body">
            <c:set var="searchValues" value="${param.checkin}"/>
            <c:if test="${empty searchValues}">
                <c:set var="format" value="${sessionScope.INPUTDATE}"/>
                <c:if test="${not empty format}">
                    ${format}
                </c:if>
                <c:set var="result" value="${sessionScope.HISTORY}"/>
                <c:if test="${not empty result}">
                    <div class="row row-cols-3 row-cols-md-3">
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <div class="col mb-4">
                                <div class="card h-100">

                                    <div class="card-body">
                                        <form action="cart" method="POST">
                                            <h5 class="card-title" id="myText">${dto.bookId}</h5>
                                            <p class="card-text">
                                                Date:${dto.checkin}<br/>
                                                <input type="hidden" name="room" value="${dto.bookId}" />
                                            </p>   
                                        </form>
                                        <form action="viewDetail" method="POST">
                                            <input type="hidden" name="room" value="${dto.bookId}" />
                                            <button type="submit" class="btns-book">
                                                View detail
                                            </button>
                                            <button type="submit" class="btns-book" name="btnRate" value="rating">
                                                Rating
                                            </button>
                                        </form>

                                        <form action="deleteBook" method="POST">
                                            <p>
                                                <button class="btns-book" type="button" data-toggle="collapse" data-target="#r${counter.count}" aria-expanded="false" aria-controls="collapseExample">
                                                    Delete
                                                </button>
                                            </p>
                                            <div class="collapse" id="r${counter.count}">
                                                <div class="card card-body">
                                                    <p>Are you sure want to remove this room?</p>
                                                    <input type="submit" class="btns-book" value="REMOVE" name="txtBook"/>
                                                </div>
                                            </div>  
                                            <input type="hidden" name="checkin" value="${dto.checkin}" />
                                            <input type="hidden" name="removeCart" value="${dto.bookId}" />
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </c:if>  
            </c:if>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
