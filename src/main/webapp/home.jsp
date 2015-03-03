<%-- 
    Document   : home
    Created on : Feb 12, 2015, 1:19:03 PM
    Author     : Mitch
--%>
<%@page import="hotel.web.controller.HotelController"%>
<%@page import="hotel.web.model.hotel.Hotel"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/site.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-3 h2 text-center">
                    Hotel Database
                    <hr>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-4 h2 text-center" id="title">
                    Edit/Delete Records
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <table class="table table-bordered table-hover bg-info table-striped header-fixed" id="hotelTable">
                        <thead>
                            <tr>
                                <th class="idWidth">Hotel ID</th>
                                <th class="nameWidth">Hotel Name</th>
                                <th class="cityWidth">City</th>
                            </tr>
                        </thead>

                        <tbody class="clickable" class="scrollableTable">
                            <c:forEach var="hotel" items="${filteredHotels}" >
                            <form id="${hotel.hotelId}" class="idForm" method="POST" action="hotel">
                                <input type="hidden" name="hotelId" id="hotelId" value="${hotel.hotelId}">
                            </form>
                            <tr onclick="clicked(${hotel.hotelId});">
                                <td class="idWidth">${hotel.hotelId}</td>
                                <td class="nameWidth">${hotel.hotelName}</td>
                                <td class="cityWidth">${hotel.city}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="row">
                        <div class="col-md-12">
                            <input class="btn btn-info text-right" type="submit" name="newEntry" id="newEntry" value="New Entry" onclick="newEntry();">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12 h2 text-center">
                            Filter Results
                            <hr>
                        </div>
                    </div>
                    <form name="filterForm" id="filterForm" method="POST" action="hotel">
                        <div class="row">
                            <div id="stateDropDown" class="col-md-6 dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
                                    State <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                    <c:forEach var="state" items="${states}" >
                                        <li role="presentation"><a role="menuitem" tabindex="-1" href="#">${state}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>
                            <div class="col-md-6">

                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-6" id="editFormDiv">
                    <form id="editForm" name="editForm" action="hotel" method="POST">
                        <div class="form-group row ">
                            <div class="col-md-3 text-right">
                                <label for="name">Hotel Name</label>
                            </div>
                            <div class="col-md-4">
                                <input type="hidden" name="hotelId" id="hiddenId" value="${hotelId}">
                                <input class="form-control" type="text" name="name" id="name" value="${name}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="address">Hotel Address</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="address" id="address" value="${address}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="city">City</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="city" id="city" value="${city}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="state">State</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="state" id="state" value="${state}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="postalCode">Postal Code</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="postalCode" id="postalCode" value="${postalCode}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="notes">Notes</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="notes" id="notes" value="${notes}">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-2">
                                <input class="btn btn-info" type="submit" name="submit" id="save" value="Save">
                            </div>
                            <div class="col-md-1">
                                <input class="btn btn-info" type="submit" name="submit" id="delete" value="Delete" onmouseover="delete();">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-6" id="newFormDiv">
                    <form id="newForm" name="newForm" action="hotel" method="POST">
                        <div class="form-group row ">
                            <div class="col-md-3 text-right">
                                <label for="name">Hotel Name</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="name" id="name" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="address">Hotel Address</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="address" id="address" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="city">City</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="city" id="city" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="state">State</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="state" id="state" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="postalCode">Postal Code</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="postalCode" id="postalCode" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3 text-right">
                                <label for="notes">Notes</label>
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="text" name="notes" id="notes">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-md-3"></div>
                            <div class="col-md-1">
                                <input class="btn btn-info" type="submit" name="submit" id="addHotel" value="Add Hotel">
                            </div>
                            <div class="col-md-1">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/site.js" type="text/javascript"></script>
</html>
