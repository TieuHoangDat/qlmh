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
        
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include> 
        
        
        <div class="container">
            
            <!--Danh sách các môn học mà sinh viên đăng ký-->
            <select >
                <option value="apple">Tất cả</option>
                <c:forEach items="${listC}" var="o">
                    <option value="apple">${o.name}</option>
                </c:forEach>               
            </select>
            <c:forEach items="${listC}" var="o">
                <li class="list-group-item text-white ${tag == o.id ? "active" : ""}"><a href="course?id=${o.id}">${o.name}</a></li>
            </c:forEach>  
                <!--Danh sách các group theo môn học-->
		<div class="table-responsive">
			<div class="table-wrapper">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>
<!--								<span class="custom-checkbox">
									<input type="checkbox" id="selectAll">
									<label for="selectAll"></label>
								</span>-->
							</th>
							<th>Tên môn học</th>
							<th>Nhóm</th>
							<th>Thời gian</th>
							<th>Tên giảng viên</th>
                                                        <th>Phòng</th>
                                                        <th>Còn lại</th>
							
						</tr>
					</thead>
					<tbody>
                                            <c:forEach items="${listG}" var="o">										
						<tr>
							<td>
                                                            <a onclick="a()" href="addGroupRegister?grid=${o.group_id}" >
                                                                    <input class="btn btn-success ${o.register==true ? "active" : ""}" value="Add"  style="width: 60px;"> 
                                                            </a>
							</td>
							<td>${o.getCourse().name}</td>
							<td>${o.group_name}</td>
							<td>${o.time}</td>
							<td>${o.teacher_name}</td>
                                                        <td>${o.room}</td>
                                                        <td>${o.available_slot}</td>
							
						</tr> 
                                            </c:forEach>
					</tbody>
				</table>
				<div class="clearfix">
					<div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
					<ul class="pagination">
						<li class="page-item disabled"><a href="#">Previous</a></li>
						<li class="page-item"><a href="#" class="page-link">1</a></li>
						<li class="page-item"><a href="#" class="page-link">2</a></li>
						<li class="page-item active"><a href="#" class="page-link">3</a></li>
						<li class="page-item"><a href="#" class="page-link">4</a></li>
						<li class="page-item"><a href="#" class="page-link">5</a></li>
						<li class="page-item"><a href="#" class="page-link">Next</a></li>
					</ul>
				</div>
			</div>
		</div> 
                
                
                
                <!--Danh sách các group đã đăng ký-->
                <div class="table-responsive">
			<div class="table-wrapper">
				
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>
								<span class="custom-checkbox">
									<input type="checkbox" id="selectAll">
									<label for="selectAll"></label>
								</span>
							</th>
							<th>Tên môn học</th>
							<th>Nhóm</th>
							<th>Thời gian</th>
							<th>Tên giảng viên</th>
                                                        <th>Phòng</th>
                                                        <th>Thời gian đăng ký</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
                                            <c:forEach items="${listGR}" var="o">										
						<tr>
							<td>
								<span class="custom-checkbox">
									<input type="checkbox" id="checkbox5" name="options[]" value="1">
									<label for="checkbox5"></label>
								</span>
							</td>
							<td>${o.getGroup().getCourse().name}</td>
							<td>${o.getGroup().group_name}</td>
							<td>${o.getGroup().time}</td>
							<td>${o.getGroup().teacher_name}</td>
                                                        <td>${o.getGroup().room}</td>
                                                        <td>${o.time}</td>
							<td>
								<a href="deleteGroupRegister?grid=${o.getGroup().group_id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
							</td>
						</tr> 
                                            </c:forEach>
					</tbody>
				</table>
				<div class="clearfix">
					<div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
					<ul class="pagination">
						<li class="page-item disabled"><a href="#">Previous</a></li>
						<li class="page-item"><a href="#" class="page-link">1</a></li>
						<li class="page-item"><a href="#" class="page-link">2</a></li>
						<li class="page-item active"><a href="#" class="page-link">3</a></li>
						<li class="page-item"><a href="#" class="page-link">4</a></li>
						<li class="page-item"><a href="#" class="page-link">5</a></li>
						<li class="page-item"><a href="#" class="page-link">Next</a></li>
					</ul>
				</div>
			</div>
		</div> 
               
        </div>

        
                
        <!-- Thông báo  -->
        <div id="deleteEmployeeModal" class="modal fade in" style="display: ${blockAlert};padding-right: 15px;">
		<div class="modal-dialog">
			<div class="modal-content">         
                            <form action="register">
					<div class="modal-header">						
						<h4 class="modal-title">Delete Course</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="register">&times;</a></button>
					</div>
					<div class="modal-body">					
						<p>${mess}</p>
						<p class="text-warning"><small>This action cannot be done.</small></p>
					</div>
					<div class="modal-footer">
						<a href="register" type="submit" class="btn btn-danger">Ok</a>
					</div>
				</form>
			</div>
		</div>
	</div>
        
                
        
    </body>
</html>

