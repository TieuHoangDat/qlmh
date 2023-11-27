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
            <a onclick="a()" href="exportExcel" >
                    <input class="btn btn-success" value="Xuất Excel"  style="width: 110px;"> 
            </a>
            <c:forEach items="${listT}" var="i">
                
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						
                                                <h4>${i.getTerm()}</h4>
						
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
							<th>Mã môn học</th>
							<th>Tên môn học</th>
							<th>Số tín chỉ</th>
							<th>Điểm TK(10)</th>
							<th>Điểm TK(4)</th>
                                                        <th>Điểm TK(C)</th>
						</tr>
					</thead>
					<tbody>
                                            <c:forEach items="${i.getLi()}" var="o">										
						<tr>
							<td>
								<span class="custom-checkbox">
									<input type="checkbox" id="checkbox5" name="options[]" value="1">
									<label for="checkbox5"></label>
								</span>
							</td>
							<td>${o.getCourse().getId()}</td>
							<td>${o.getCourse().getName()}</td>
							<td>${o.getCourse().getNum_credit()}</td>
							<td>${o.grade_10}</td>
                                                        <td>${o.grade_4}</td>
                                                        <td>${o.grade_alpha}</td>
						</tr> 
                                            </c:forEach>
					</tbody>
				</table>
                                <div class="clearfix">
                                    <div class="col-xs-6">
                                            <h5>- Điểm trung bình học kỳ hệ 4:  ${i.avg_4}</h5>
                                            <h5>- Điểm trung bình học kỳ hệ 10: ${i.avg_10}</h5>
                                            <h5>- Số tín chỉ đạt học kỳ:        ${i.total_credit}</h5>
                                    </div>
                                    <div class="col-xs-6">
                                            <h5>- Điểm trung bình tích lũy hệ 4:  ${i.tl_4}</h5>
                                            <h5>- Điểm trung bình tích lũy hệ 10: ${i.tl_10}</h5>
                                            <h5>- Số tín chỉ tích lũy:            ${i.tl_credit}</h5>
                                    </div>
                                    
				</div>
			</div>
		</div> 
        </c:forEach>
    </div>
        
        
        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>

