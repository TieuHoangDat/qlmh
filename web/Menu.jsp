<%-- 
    Document   : Menu.jsp
    Created on : Oct 8, 2023, 9:38:56 AM
    Author     : Tieu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" ">
    <a class="navbar-brand" href="#">PTIT</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item ${homeactive}">
          <a class="nav-link" href="home">Trang chủ<span class="sr-only">(current)</span></a>
        </li>
        <c:if test = "${sessionScope.acc.isAdmin == 1}"> 
            <li class="nav-item ${managercourseactive}">
              <a class="nav-link" href="managercontrol">Quản lý môn học</a>
            </li>
            <li class="nav-item ${managergroupactive}">
              <a class="nav-link" href="managergroup">Quản lý nhóm học</a>
            </li>
        </c:if>
        <c:if test = "${sessionScope.acc.isAdmin == 0 && sessionScope.acc.isTeacher == 0}">
            <li class="nav-item ${registercourseactive}">
              <a class="nav-link" href="register">Đăng ký môn học</a>
            </li>
        </c:if>
        <c:if test = "${sessionScope.acc.isAdmin == 0}">
            <li class="nav-item ${tkbactive}">
              <a class="nav-link" href="studenttimetable">Xem thời khóa biểu tuần</a>
            </li>
        </c:if>
        <c:if test = "${sessionScope.acc.isAdmin == 0 && sessionScope.acc.isTeacher == 0}">
            <li class="nav-item ${gradeactive}">
              <a class="nav-link" href="show_grade">Xem điểm</a>
            </li>
        </c:if>
        <c:if test = "${sessionScope.acc == null}"> 
            <li class="nav-item ${loginactive} ">
              <a class="nav-link" href="Login.jsp">Đăng nhập</a>
            </li>
        </c:if>
        <c:if test = "${sessionScope.acc != null}"> 
            <li class="nav-item" >
              <a class="nav-link" href="#">Hello ${sessionScope.acc.name}</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="logout">Đăng xuất</a>
            </li>
        </c:if>

      </ul>

    </div>
  </nav>

<!--end of menu-->
