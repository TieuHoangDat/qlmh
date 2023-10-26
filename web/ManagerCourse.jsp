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
                                                    <a href="managercontrol">
                                                        <h2>Manage <b>Course</b></h2>
                                                    </a>
							
						</div>
                                            
                                                <div class="col-xs-4">
                                                    <form class="form-inline" method="post" action="searchcourse">
                                                        <input name="searchCourse" class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
                                                        <button class="btn btn-outline-info my-2 my-sm-0" type="submit">Search</button>
                                                    </form>
						</div>
                                                
						<div class="col-xs-4">
                                                        
							<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
							<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
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
							<th>Mã môn học</th>
							<th>Số tín chỉ</th>
							<th>
                                                            Học kì
                                                            <!--<a href="searchcourse?">Lọc</a>-->
                                                            <form method="post" action="filterBySemester">
                                                                <select id ="mySelect" name="category" class="form-select" aria-label="Default select example">
                                                                        <option >Tất cả</option>    
                                                                    <c:forEach begin="1" end="10" var="o">
                                                                        <option value=${o}><a href="searchcourse?semester=${o}">${o}</a></option>
                                                                    </c:forEach>
                                                                </select>
                                                                
                                                            </form>
                                                        </th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
                                            <c:forEach items="${listC}" var="o">										
						<tr>
							<td>
								<span class="custom-checkbox">
									<input type="checkbox" id="checkbox5" name="options[]" value="1">
									<label for="checkbox5"></label>
								</span>
							</td>
							<td>${o.name}</td>
							<td>${o.id}</td>
							<td>${o.num_credit}</td>
							<td>${o.term}</td>
							<td>
								<a href="editcourse?idEdit=${o.id}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
								<a href="deletecourse?idDelete=${o.id}" class="delete" ><i  class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
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
    </div>
	 <!--Add Modal HTML hdh--> 
	<div id="addEmployeeModal" class="modal fade in" style="display: ${block};padding-right: 15px;">
		<div class="modal-dialog">
			<div class="modal-content">
                            <form action="add" method = "post">
					<div class="modal-header">						
						<h4 class="modal-title">Thêm môn học</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Tên môn học</label>
							<input type="text" class="form-control" name ="name" required >
						</div>
						<div class="form-group">
							<label>Mã hôn học</label>
							<input type="text" class="form-control"  name ="id" required>
						</div>
						<div class="form-group">
							<label>Số tín chỉ</label>
							<input type="text" class="form-control" name="numberOfCredit" required>
						</div>
						<div class="form-group">
							<label>Học kì</label>
							<input type="text" class="form-control" name  ="semester" required>
						</div>					
					</div>
					<div class="modal-footer">
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-success" value="Add">   
					</div>
				</form>
			</div>
		</div>
	</div>
        <div  id="deleteEmployeeModal" class="modal fade in" style="display: ${blockDelete};padding-right: 15px;">
		<div class="modal-dialog">
			<div class="modal-content">         
                            <form action="delete">
					<div class="modal-header">						
						<h4 class="modal-title">Delete Course</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="managercontrol?blockDelete=none">&times;</a></button>
					</div>
					<div class="modal-body">					
						<p>Are you sure you want to delete ${idDelete}</p>
						<p class="text-warning"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<a href="managercontrol?blockDelete=none type="button" class="btn btn-default" data-dismiss="modal">Cancel<a/>
						<a href="deletecourse?idDelete=${idDelete}&delete=1" type="submit" class="btn btn-danger">Delete</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Edit Modal HTML -->
	<div id="editEmployeeModal" class="modal fade in" style="display: ${blockEdit};padding-right: 15px;">
		<div class="modal-dialog">
			<div class="modal-content">
                            <form action="editcourse?idEdit=${courseEdit.id}&edit=1" method = "post">
					<div class="modal-header">						
						<h4 class="modal-title">Chỉnh sửa</h4>
						<button  type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="managercontrol?blockEdit=none">&times;</a></button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label>Tên môn học</label>
                                                        <input value="${courseEdit.name}" type="text" class="form-control" name="nameEdit" required>
						</div>
						<div class="form-group">
							<label>Mã môn học</label>
                                                        <input value="${courseEdit.id}" type="text" class="form-control" required>
						</div>
						<div class="form-group">
							<label>Số tín chỉ</label>
                                                        <input  value="${courseEdit.num_credit}" type="text" class="form-control" name="numberOfCreditEdit" required>
						</div>
						<div class="form-group">
							<label>Học kì</label>
                                                        <input value="${courseEdit.term}" type="text" class="form-control" name="semesterEdit"  required>
						</div>					
					</div>
					<div class="modal-footer">
						<a href="managercontrol?blockDelete=none type="button" class="btn btn-default" data-dismiss="modal">Cancel<a/>
                                                <input type="submit" class="btn btn-success" value="Add">  
                                                <!--<a href="editcourse?idEdit=${courseEdit.id}&edit=1" type="submit" class="btn btn-info" value="Save">Submit</a>-->
					</div>
				</form>
			</div>
		</div>
        </div>
        <!--ngu-->
	<!-- Delete Modal HTML -->
        <div class="modal fade in" style="display: ${blockDelete};padding-right: 15px;">
		<div class="modal-dialog">
			<div class="modal-content">         
                            <form action="delete">
					<div class="modal-header">						
						<h4 class="modal-title">Delete Course</h4>
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="managercontrol?blockDelete=none">&times;</a></button>
					</div>
					<div class="modal-body">					
						<p>Are you sure you want to delete ${idDelete}</p>
						<p class="text-warning"><small>This action cannot be undone.</small></p>
					</div>
					<div class="modal-footer">
						<a href="managercontrol?blockDelete=none type="button" class="btn btn-default" data-dismiss="modal">Cancel<a/>
						<a href="deletecourse?idDelete=${idDelete}&delete=1" type="submit" class="btn btn-danger">Delete</a>
					</div>
				</form>
			</div>
		</div>
	</div>
        <!-- Footer -->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-lg-4 col-xl-3">
                        <h5>About</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <p class="mb-0">
                            Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression.
                        </p>
                    </div>

                    <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                        <h5>Informations</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                        </ul>
                    </div>

                    <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
                        <h5>Others links</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                        </ul>
                    </div>

                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Contact</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-home mr-2"></i> My company</li>
                            <li><i class="fa fa-envelope mr-2"></i> email@example.com</li>
                            <li><i class="fa fa-phone mr-2"></i> + 33 12 14 15 16</li>
                            <li><i class="fa fa-print mr-2"></i> + 33 12 14 15 16</li>
                        </ul>
                    </div>
                    <div class="col-12 copyright mt-3">
                        <p class="float-left">
                            <a href="#">Back to top</a>
                        </p>
                        <p class="text-right text-muted">created with <i class="fa fa-heart"></i> by <a href="https://t-php.fr/43-theme-ecommerce-bootstrap-4.html"><i>t-php</i></a> | <span>v. 1.0</span></p>
                    </div>
                </div>
            </div>
        </footer>
    </body>
</html>

