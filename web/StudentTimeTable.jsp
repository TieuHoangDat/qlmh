<%-- 
    Document   : StudentTimeTable
    Created on : Oct 26, 2023, 8:52:11 PM
    Author     : Tieu_Dat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <!--<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/timetable.css" rel="stylesheet" type="text/css"/>
        
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!--<link href="css/manager.css" rel="stylesheet" type="text/css"/>-->
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include> 
        <div class="container">
                <div class="timetable-img text-center">
                    <img src="img/content/timetable.png" alt="">
                </div>
                <div class="table-responsive">
                    <table class="table table-bordered text-center">
                        <thead>
                            <tr class="bg-light-gray">
                                <th class="text-uppercase">Time
                                </th>
                                <th class="text-uppercase">Monday</th>
                                <th class="text-uppercase">Tuesday</th>
                                <th class="text-uppercase">Wednesday</th>
                                <th class="text-uppercase">Thursday</th>
                                <th class="text-uppercase">Friday</th>
                                <th class="text-uppercase">Saturday</th>
                                <th class="text-uppercase">Sunday</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="row" begin="1" end="5">
                                <tr>
                                    <c:if test="${row==1}">
                                        <td class="align-middle">07:00</td>
                                    </c:if>
                                    <c:if test="${row==2}">
                                        <td class="align-middle">09:00</td>
                                    </c:if>
                                    <c:if test="${row==3}">
                                        <td class="align-middle">12:00</td>
                                    </c:if>
                                    <c:if test="${row==4}">
                                        <td class="align-middle">14:00</td>
                                    </c:if>
                                    <c:if test="${row==5}">
                                        <td class="align-middle">16:00</td>
                                    </c:if>
                                    
                                    <c:forEach var="col" begin="1" end="7">
                                        <c:choose>
                                            <c:when test="${b[row][col+1].time == null}">
                                                <td style="width: 170px;">
                                                    
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td class="bg-light-gray" style="width: 170px;">
                                                    <span style="padding: 5px;" class="bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom text-white font-size16  xs-font-size13">${b[row][col+1].getCourse().name}</span>
                                                    <div class="font-size14">Giảng viên: ${b[row][col+1].teacher_name}</div>
                                                    <div class="font-size14">Phòng: ${b[row][col+1].room}</div>
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                        
                                    </c:forEach>
                                </tr>
                            </c:forEach>
                          
                        </tbody>
                    </table>
                </div>
            </div>
        
        
	
	
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>


