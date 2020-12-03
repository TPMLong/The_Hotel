<%-- 
    Document   : detail
    Created on : Nov 6, 2020, 4:35:24 PM
    Author     : ACER
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Page</title>
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
        </header>
        <div class="body">
            <c:set var="searchValues" value="${param.checkin}"/>
            <c:if test="${not empty searchValues}">
                <c:set var="results" value="${sessionScope.DETAILHISTORY}"/>
                <c:if test="${not empty results}">
                    <div class="row row-cols-3 row-cols-md-3">
                        <c:forEach var="dto" items="${results}" varStatus="counter">
                            <div class="col mb-4">
                                <div class="card h-100">                                       
                                    <div class="card-body">
                                        <form action="cart" method="POST">
                                            <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                            <p class="card-text">
                                                Type:${dto.cateName}<br/>
                                                Quantity:${dto.quantity}<br/>
                                                Price:${dto.price}<br/>
                                                Check in:${dto.checkin}<br/>
                                                Check out:${dto.checkout}<br/>
                                                <input type="hidden" name="room" value="${dto.bookId}" />
                                            </p>   
                                        </form>
                                        <form action="viewDetail" method="POST">
                                            <input type="hidden" name="room" value="${dto.bookId}" />
                                            <button type="button" class="btns-book">
                                                View detail
                                            </button>
                                        </form>
                                        <form action="viewDetail" method="POST">
                                            <input type="hidden" name="room" value="${dto.bookId}" />
                                            <button type="button" class="btns-book">
                                                Rating
                                            </button>
                                        </form>
                                        <form action="deleteBook" method="POST">
                                            <input type="hidden" name="checkin" value="${dto.checkin}" />                                         
                                            <button type="button" class="btns-book" data-toggle="modal" data-target="#exampleModal">
                                                Delete
                                            </button>
                                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Are you sure want to delete
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btns-book" data-dismiss="modal">Close</button>
                                                            <button type="submit" class="btns-book" value="Detele" name="btAction">Delete</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" name="removeCart" value="${dto.bookId}" />
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${empty results}">              
                    <h2>No record is matched !!!!</h2>
                </c:if>
            </c:if>
            <c:set var="searchValues" value="${param.checkin}"/>
            <c:if test="${empty searchValues}">
                <c:set var="results" value="${sessionScope.RATINGHISTORY}"/>
                <c:if test="${empty results}">
                    <c:set var="result" value="${sessionScope.DETAILHISTORY}"/>
                    <c:if test="${not empty result}">
                        <div class="row row-cols-3 row-cols-md-3">
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <div class="col mb-4">
                                    <div class="card h-100">

                                        <div class="card-body">
                                            <form action="cart" method="POST">
                                                <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                                <p class="card-text">
                                                    Type:${dto.cateName}<br/>
                                                    Quantity:${dto.quantity}<br/>
                                                    Price:${dto.price}<br/>
                                                    Check in:${dto.checkin}<br/>
                                                    Check out:${dto.checkout}<br/>
                                                    <input type="hidden" name="room" value="${dto.bookId}" />
                                                </p>   
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                        </div>
                    </c:if>  
                </c:if>
            </c:if>
            <c:set var="searchValues" value="${param.checkin}"/>
            <c:if test="${empty searchValues}">
                <c:set var="delete" value="${sessionScope.DELETERATING}"/>
                <c:if test="${empty delete}">
                    <c:if test="${not empty results}">
                        <div class="row row-cols-3 row-cols-md-3">
                            <c:forEach var="dto" items="${results}" varStatus="counter">
                                <div class="col mb-4">
                                    <div class="card h-100">
                                        <div class="card-body">
                                            <form action="rating" method="POST">
                                                <h5 class="card-title" id="myText">${dto.hotelName}</h5>
                                                <p class="card-text">
                                                    Type:${dto.cateName}<br/>
                                                    Quantity:${dto.quantity}<br/>
                                                    Price:${dto.price}<br/>
                                                    Check in:${dto.checkin}<br/>
                                                    Check out:${dto.checkout}<br/>                                                   
                                                    Rating: ${dto.cateId}/10 <br/>
                                                    <input type="hidden" name="detail" value="${dto.detaiId}" />
                                                    <input type="hidden" name="room" value="${dto.roomId}" />
                                                    Rating:
                                                    <select name="cboBook" class="check-text">   
                                                        <c:forEach begin="1" end="10" step="1" varStatus="counter">
                                                            <option value="${counter.count}">
                                                                ${counter.count}
                                                            </option>   
                                                        </c:forEach>                                        
                                                    </select>
                                                    /10
                                                    <button type="submit" class="btns-book" value="rate" name="btnRate">
                                                        Rating
                                                    </button>
                                                </p>   
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                        </div>
                    </c:if>  
                </c:if>
            </c:if>
            <c:if test="${not empty delete}">
                <font color ="white" id="welcome"> ${sessionScope.DELETERATING}
                </font>

            </c:if>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    </body>
</html>
