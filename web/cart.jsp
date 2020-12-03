<%-- 
    Document   : cart
    Created on : Nov 1, 2020, 10:16:48 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
        <link rel="stylesheet" type="text/css" href="css/Body.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    </head>
    <body>      
        <header>
            <div class="main">
                <c:set var="result" value="${sessionScope.ROOMDETAIL}"/>
                <ul> 
                    <li><a href="show">Home</a></li>
                    <li class="active"><a href="showCart">Room</a></li>
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
                        <c:set var="message" value="${sessionScope.NULLDETAIL}"/>
                        <c:if test="${not empty result}">
                            <font style="margin-left: 20px " color ="white" id="welcome"> Price: ${sessionScope.PRICE}    
                            </font>
                        </c:if>
                    </c:if> 
                </ul>
                <c:if test="${not empty result}">
                    <form action="confirm" method="POST" >
                        <button type="submit" class="confirm-button" name="btAction">Confirm</button>
                        <div class="form">
                            <label><b>${sessionScope.ERRORCONFIRM}   </b></label> <br/>
                        </div>
                    </form>
                    <form action="code" method="POST" >
                        <font color ="white" class="check-text">${sessionScope.ERRORCODE}
                        </font> 
                        <font color ="white" class="check-text">Code:
                        </font>                  
                        <input type="text" class="search-bar" name="code" value="">
                        <button type="submit" class="use-button" name="btAction">USE</button>
                    </form>
                </c:if>
            </div>
            <div class="main">
            </div>             
        </header>
        <div class="body">
            <c:set var="message" value="${sessionScope.NULLDETAIL}"/>
            <c:if test="${empty message}">
                <c:if test="${not empty result}">
                    <div class="row row-cols-3 row-cols-md-3">
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <div class="col mb-4">
                                <div class="card h-100">
                                    <img src="img/king.jpeg" class="card-img-top" style="height: 300px" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                        <p class="card-text">
                                            Price:$${dto.price}<br/>
                                            Type:${dto.cateName}<br/>
                                            Room:${dto.quantity}<br/>
                                            Total:${dto.quantity * dto.price}
                                        </p>
                                        <form action="quantityCart" method="POST">
                                            <input type="hidden" name="checkin" value="${dto.checkin}" />
                                            <input type="hidden" name="checkout" value="${dto.checkout}" />
                                            <input type="hidden" name="txtRoom" value="${dto.roomId}" />
                                            <c:set var="error" value="${sessionScope.ERROR}"/>
                                            <c:if test="${not empty error}">
                                                ${error}<br/>
                                            </c:if>                  
                                            <p>
                                                <button class="btns-book" type="button" data-toggle="collapse" data-target="#a${counter.count}" aria-expanded="false" aria-controls="collapseExample">
                                                    Update
                                                </button>
                                            </p>
                                            <div class="collapse" id="a${counter.count}">
                                                <div class="card card-body">
                                                    <input type="text" name="txtQuantity" value="" />
                                                    <input type="submit" class="btns-book" value="UPDATE" name="txtBook"/>
                                                </div>
                                            </div>                                               
                                        </form>
                                        <form action="delete" method="POST">
                                            <input type="hidden" name="remove" value="${dto.roomId}" />
                                            <input type="hidden" name="checkin" value="${dto.checkin}" />
                                            <input type="hidden" name="checkout" value="${dto.checkout}" />
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
