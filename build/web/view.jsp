<%-- 
    Document   : view
    Created on : Oct 29, 2020, 9:03:35 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
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
            <div class="main">
                <form action="searchHotel" method="POST">
                    <font color ="white" class="check-text">Check in:
                    </font>
                    <input type="date" class="check-date" name="checkin" value="${requestScope.IN}">
                    <font color ="white" class="check-text">Check out:
                    </font>
                    <input type="date" class="check-date" name="checkout" value="${requestScope.OUT}">
                    <input type="text" name="txtQuantity" value="${param.txtQuantity}" class="search-bar" placeholder="Quantity..." >
                    <input type="text" name="txtSearch" value="${param.txtSearch}" class="search-bar" placeholder="Search..." >            
                    <select name="cboBook" class="check-text">                                                 
                        <option value="name">
                            name                     
                        </option>   
                        <option value="area">
                            area                              
                        </option>   
                    </select>
                    <input type="submit" class="btns" value="SEARCH" name="txtLogin" onclick="getDate()"/>
                </form>
            </div>             
        </header>
        <div class="body">
            <c:set var="searchValues" value="${param.txtSearch}"/>
            <c:if test="${not empty searchValues}">
                <c:set var="results" value="${requestScope.NAMESEARCH}"/>
                <c:if test="${not empty results}">
                    <div class="row row-cols-3 row-cols-md-3">
                        <c:forEach var="dto" items="${results}" varStatus="counter">
                            <div class="col mb-4">
                                <div class="card h-100">
                                    <img src="img/king.jpeg" class="card-img-top" style="height: 300px" alt="...">
                                    <div class="card-body">
                                        <form action="cart" method="POST">
                                            <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                            <p class="card-text">
                                                Location:${dto.location}<br/>
                                                Price:$${dto.price}<br/>
                                                Type:${dto.cateName}<br/>
                                                Room:${dto.quantity}
                                                <input type="hidden" name="room" value="${dto.roomId}" />
                                                <input type="hidden" name="checkout" value="${requestScope.OUT}" />
                                                <input type="hidden" name="checkin" value="${requestScope.IN}" />                                            
                                            </p>
                                            <p>
                                                <button class="btns-book" type="button" data-toggle="collapse" data-target="#a${counter.count}" aria-expanded="false" aria-controls="collapseExample">
                                                    Book
                                                </button>
                                            </p>
                                            <div class="collapse" id="a${counter.count}">
                                                <div class="card card-body">
                                                    <font>Quantity:
                                                    </font>
                                                    <input type="text" name="txtQuantity" value="" />                                   
                                                    <input type="submit" class="btns-book" value="BOOK" name="txtBook"/>
                                                </div>
                                            </div>  
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty results}">
                    <c:set var="error" value="${requestScope.ERROR}"/>
                    <c:if test="${empty error}">
                        <h2>No record is matched !!!!</h2>
                    </c:if>
                    <c:if test="${not empty error}">
                        <h2>${error}!</h2>
                    </c:if>
                </c:if>
            </c:if>
            <c:set var="searchValues" value="${param.txtSearch}"/>
            <c:if test="${empty searchValues}">
                <c:set var="result" value="${requestScope.SHOWRESULT}"/>
                <c:if test="${not empty result}">
                    <div class="row row-cols-3 row-cols-md-3">
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <div class="col mb-4">
                                <div class="card h-100">
                                    <img src="img/king.jpeg" class="card-img-top" style="height: 300px" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                        <p class="card-text">
                                            Location:${dto.location}<br/>
                                            Price:$${dto.price}<br/>
                                            Type:${dto.cateName}<br/>
                                            Room:${dto.quantity}<br/>
                                        </p>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>
                    </div>
                </c:if>  
            </c:if>
            <c:set var="searchValues" value="${param.txtSearch}"/>
            <c:if test="${empty searchValues}">
                <c:set var="result" value="${requestScope.SHOWRESULTS}"/>
                <c:if test="${not empty result}">
                    <div class="row row-cols-3 row-cols-md-3">
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <div class="col mb-4">
                                <div class="card h-100">
                                    <img src="img/king.jpeg" class="card-img-top" style="height: 300px" alt="...">
                                    <div class="card-body">
                                        <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                        <p class="card-text">
                                            Location:${dto.location}<br/>
                                            Room:${dto.quantity}<br/>
                                        <form action="showRoom" method="POST">
                                            <input type="hidden" name="hotelId" value="${dto.hotelID}" />
                                            <button class="btns-book" type="submit" >
                                                View
                                            </button>
                                        </form>
                                        </p>
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
