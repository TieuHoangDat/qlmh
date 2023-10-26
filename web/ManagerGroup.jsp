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
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-xs-4">
                                                    <a href="managergroup">
                                                        <h2>Manage <b>Course</b></h2>
                                                    </a>
							
						</div>
                                            
                                                <div class="col-xs-4">
                                                    <form action="searchgroup" method="get" class="form-inline">
                                                        <input name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                                                            <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
                                                    </form>
						</div>
                                                
						<div class="col-xs-4">
                                                        
							<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
							<!--<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>-->						
						</div>
					</div>
				</div>
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
                                                        <th>Còn lại</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
                                            <c:forEach items="${listG}" var="o">										
						<tr>
							<td>
								<span class="custom-checkbox">
									<input type="checkbox" id="checkbox5" name="options[]" value="1">
									<label for="checkbox5"></label>
								</span>
							</td>
							<td>${o.getCourse().name}</td>
							<td>${o.group_name}</td>
							<td>${o.time}</td>
							<td>${o.teacher_name}</td>
                                                        <td>${o.room}</td>
                                                        <td>${o.available_slot}</td>
							<td>
                                                            <a href="editgroup?idEdit=${o.group_id}&blockEdit=block" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                                            <a href="deletegroup?idDelete=${o.group_id}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
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
	<!-- Add Modal HTML -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="addgroup" method = "get">
					<div class="modal-header">						
						<h4 class="modal-title">Add Group</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Tên nhóm</label>
							<input name="name_group" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Mã môn học</label>
                                                        <input name="course_id" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Thứ</label>
                                                        <input name="day" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Kíp</label>
                                                        <input name="time" type="text" class="form-control" required>
						</div>	
                                                <div class="form-group">
							<label>Tên giáo viên</label>
                                                        <input name="teacher_name" type="text" class="form-control" required>
						</div>
                                                <div class="form-group">
							<label>Mã giáo viên</label>
							<input name="teacher_id" type="text" class="form-control" required>
						</div>
                                                <div class="form-group">
							<label>Phòng học</label>
                                                        <input name="room" type="text" class="form-control" required>
						</div>
                                                <div class="form-group">
							<label>Số lượng sinh viên</label>
                                                        <input name="quantity_student" type="text" class="form-control" required>
						</div>
					</div>
					<div class="modal-footer">
                                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                            <input type="submit" class="btn btn-success" value="Add">
				</form>
			</div>
		</div>
	</div>
    </div>
	<!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade in" style="display: ${blockEdit};">
            <div class="modal-dialog">
                <div class="modal-content"">
				<form action="editgroup?idEdit=${group.group_id}&edit=1" method = "post">
					<div class="modal-header">						
						<h4 class="modal-title">Edit Group</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body" style="max-height: 400px; overflow-y: auto;">					
						<div class="form-group">
							<label>Tên nhóm</label>
                                                        <input value="${group.group_name}" name="name_group_edit" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Mã môn học</label>
                                                        <input value="${group.course.id}" name="course_id_edit" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Thứ</label>
                                                        <input value ="${group.time.substring(4,5)}" name="day_edit" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Kíp</label>
                                                        <input value="${group.time.substring(11)}" name="time_edit" type="text" class="form-control" required>
						</div>	
                                                <div class="form-group">
							<label>Tên giáo viên</label>
                                                        <input value="${group.teacher_name}" name="teacher_name_edit" type="text" class="form-control" required>
						</div>
                                                <div class="form-group">
							<label>Mã giáo viên</label>
                                                        <input value="${group.teacher_id}" name="teacher_id_edit" type="text" class="form-control" required>
						</div>
                                                <div class="form-group">
							<label>Phòng học</label>
                                                        <input value="${group.room}" name="room_edit" type="text" class="form-control" required>
						</div>
                                                <div class="form-group">
							<label>Số lượng sinh viên</label>
                                                        <input value="${group.max_students}" name="quantity_student_edit" type="text" class="form-control" required>
						</div>
					</div>
					<div class="modal-footer">
                                            <a href="managergroup" type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">Cancel</a>
                                            <input type="submit" class="btn btn-success" value="Add">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Delete Modal HTML -->
        <div id="deleteEmployeeModal" class="modal fade in " style="display:${blockDelete} ">
		<div class="modal-dialog">
			<div class="modal-content">
				<form>
					<div class="modal-header">						
						<h4 class="modal-title">Delete Employee</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<p>Are you sure you want to delete these Records?</p>
						<p class="text-warning"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
                                            <a href="managergroup" class="btn btn-default" data-dismiss="modal" value="Cancel"> Cancel </a>
                                            <a href="deletegroup?idDelete=${idDelete}&check=1" class="btn btn-danger" value="Delete"> Delete </a>
					</div>
				</form>
			</div>
		</div>
	</div>
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

