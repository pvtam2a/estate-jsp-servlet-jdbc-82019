<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp" %>
<c:url var="buldingUrl" value="/admin-building" />
<c:url var="userAPI" value="/api-user" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách tòa nhà</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				</script>
	
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="<c:url value='/admin-building?action=LIST' />">Home</a>
					</li>
					<li class="active">Dashboard</li>
				</ul><!-- /.breadcrumb -->
			
			</div>
	
			<div class="page-content">	
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="widget-box">
							<div class="widget-header">
								<h4 class="widget-title">Tìm kiếm</h4>
	
								<div class="widget-toolbar">
									<a href="#" data-action="collapse">
										<i class="ace-icon fa fa-chevron-up"></i>
									</a>
	
									<!-- <a href="#" data-action="close">
										<i class="ace-icon fa fa-times"></i>
									</a> -->
								</div>
							</div>
							<div class="widget-body">
								<div class="widget-main">
									<div class="form-horizontal"> 	
										<form action="${buldingUrl }" method="get" id="formSearchBuilding">											
											<div class="form-group">
												<div class="col-sm-4">
													<div>
														<label for="name">Tên tòa nhà</label>
														<input type="text" id="name" class="form-control" name="name" value="${model.name}">
													</div>
												</div>
												<div class="col-sm-4">
													<div>
														<label for="buildingArea">Diện tích sàn</label>
														<input type="number" id="buildingArea" class="form-control" name="buildingArea" value="${model.buildingArea}">
													</div>
												</div>	
												<div class="col-sm-4">
													<div>
														<label for="numberOfBasement">Số tầng hầm</label>
														<input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement" value="${model.numberOfBasement}">
													</div>
												</div>															
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<div>														
													  <label for="district">Quận</label>													
													  <select class="form-control" id="district" name="district">
													    <option value="">---Chọn quận---</option>
													    <!-- <option value="QUAN_1">Quận 1</option>
													    <option value="QUAN_2">Quận 2</option>
													    <option value="QUAN_3">Quận 3</option>
													    <option value="QUAN_4">Quận 4</option> -->
													    <c:forEach var="item" items="${districts }">
													    	<option value="${item.key}" ${item.key == model.district ? 'selected' : '' }>${item.value}</option>
													    </c:forEach>
													  </select>														
													</div>
												</div>
												<div class="col-sm-4">
													<div>
														<label for="ward">Phường</label>
														<input type="text" id="ward" class="form-control" name="ward" value="${model.ward}">
													</div>
												</div>
												<div class="col-sm-4">
													<div>
														<label for="street">Đường</label>
														<input type="text" id="street" class="form-control" name="street" value="${model.street}">
													</div>
												</div>
											</div>
											<div class="form-group">													
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Diện tích thuê từ</label>
														<input type="number" id="rentAreaFrom" class="form-control" name="rentAreaFrom" value="${model.rentAreaFrom}">
													</div>
												</div>	
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Diện tích thuê đến</label>
														<input type="number" id="rentAreaTo" class="form-control" name="rentAreaTo" value="${model.rentAreaTo}">
													</div>
												</div>		
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Giá thuê từ</label>
														<input type="number" id="costRentFrom" class="form-control" name="costRentFrom" value="${model.costRentFrom}">
													</div>
												</div>	
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Giá thuê đến</label>
														<input type="number" id="costRentTo" class="form-control" name="costRentTo" value="${model.costRentTo}">
													</div>
												</div>															
											</div>
											<div class="form-group">													
												<div class="col-sm-4">
													<div>
														<label for="managerName">Tên quản lý</label>
														<input type="text" id="managerName" class="form-control" name="managerName" value="${model.managerName}">
													</div>
												</div>	
												<div class="col-sm-4">
													<div>
														<label for="managerPhone">Điện thoại quản lý</label>
														<input type="number" id="managerPhone" class="form-control" name="managerPhone" value="${model.managerPhone}">
													</div>
												</div>		
												<div class="col-sm-4">
													<div>
														<label for="staffId">Chọn nhân viên phụ trách</label>
														<input type="text" id="staffId" class="form-control" name="staffId" value="${model.staffId}">
													</div>
												</div>																								
											</div>
											<div class="form-group">
												<div class="col-sm-12">
													<div>
														<!-- <label class="checkbox-inline"> Loại tòa nhà: </label>	 -->
														<c:forEach var="item" items="${buildingTypes }">
															<label class="checkbox-inline"><input type="checkbox" value="${item.key }" 
															 name="buildingTypes" ${fn:contains(fn:join(model.buildingTypes, ','), item.key) ? 'checked' : '' }>${item.value }															
															</label>
														</c:forEach>														
													</div>
												</div>																				
											</div>
											<div class="form-group">
												<div class="col-sm-3">
													<button type="button" id="btnSearchBuilding" class="btn btn-success">Tìm kiếm</button>
												</div>
												<div class="col-sm-9">
												</div>
											</div>
											<input type="hidden" value="LIST" name="action" />
											<input type="hidden" id="page" value="" name="page" />
											<input type="hidden" id="limit" value="" name="limit" />
										</form>
									</div>
								</div>
							</div>
						</div>								
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
					<div class="col-xs-12">
						<div class="pull-right">							
							<a href="/admin-building?action=EDIT" class="btn btn-white" data-toggle="tooltip" title="Thêm tòa nhà">
								<i class="fa fa-plus-circle"></i>
							</a>
							<button class="btn btn-white btn-warning btn-bold" id="btnDeleteBuilding" data-toggle="tooltip" title="Xóa tòa nhà">								
								<i class="fa fa-trash"></i>
							</button>
						</div>
					</div>
				</div><!-- /.row -->
				<br>
				<div class="row">	
					<div class="col-xs-12">
						<table id="buildingList" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th></th>											
									<th>Tên sản phẩm</th>
									<th>Địa chỉ</th>
									<th>Tên quản lý</th>
									<th>Số điện thoại</th>
									<th>Diện tích sàn</th>
									<th>Giá thuê</th>
									<th>Phí dịch vụ</th>
									<th>Loại tòa nhà</th>
									<th>Diện tích thuê</th>
									<th>Số tầng hầm</th>
									<th>Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${buildings}">
									<tr>
										<td><input type="checkbox" value="${item.id }" id="checkbox_1"></td>
										<td>${item.name}</td>
										<td>${item.address}</td>
										<td>${item.managerName}</td>
										<td>${item.managerPhone}</td>
										<td>${item.buildingArea}</td>
										<td>${item.costRent}</td>
										<td>${item.serviceCost}</td>
										<td>${item.type}</td>
										<td>${item.rentArea}</td>
										<td>${item.numberOfBasement}</td>
										<td>	
											<button class="btn btn-xs btn-info"  data-toggle="tooltip" title="Giao tòa nhà" 
													onclick="assingmentBuilding(${item.id })">
												<i class="fa fa-bars" aria-hidden="true"></i>
											</button>
											<c:url var="editBuilding" value="/admin-building">
												<c:param name="action" value="EDIT"></c:param>
												<c:param name="id" value="${item.id }"></c:param>
											</c:url>
											<a href="${editBuilding }" class="btn btn-xs btn-info" data-toggle="tooltip"  title="Cập nhật tòa nhà" >
												<i class="fa fa-pencil" aria-hidden="true"></i>
											</a>		
											<button class="btn btn-xs btn-info"  data-toggle="tooltip" title="Xóa tòa nhà" 
													onclick="removeBuilding(${item.id })">
												<i class="fa fa-trash" aria-hidden="true"></i>
											</button>									
										</td>
									</tr>
								</c:forEach>								
							</tbody>
						</table>						
					</div>
					<div class="col-xs-12" align="center">
						<!-- paging -->
						<nav aria-label="Page navigation">
					        <ul class="pagination" id="pagination"></ul>
					    </nav>
					</div>
				</div><!-- /.row -->				
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
	
	
	
	<!-- Modal -->
	<div id="assingmentBuildingModel" class="modal fade" role="dialog">
	  <div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h4 class="modal-title">Danh sách nhân viên</h4>
		  </div>
		  <div class="modal-body">
				<table id="staffList" class="table">
					<thead>
					<tr>
						<th>Chọn nhân viên</th>
						<th>Tên nhân viên</th>						
					</tr>
					</thead>
					<tbody>
						<!-- content -->					
					</tbody>
			   </table>
			   <input type="hidden" id="buildingId" name="buildingId" value="">
		  </div>
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
		  </div>
		</div>

	  </div>
	</div>	
	<script>
		var totalPage = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = ${model.limit};
		$(function () {
		    window.pagObj = $('#pagination').twbsPagination({
		        totalPages: totalPage,
		        visiblePages: 10,
		        startPage: currentPage,
		        onPageClick: function (event, page) {
		        	if(currentPage != page){
		        		console.info(page + ' (from options)');
			            $('#page').val(page);
			            $('#limit').val(5);
			            $('#formSearchBuilding').submit();
		        	}		           
		        }
		    }).on('page', function (event, page) {
		        console.info(page + ' (from event listening)');
		    });
		});
	</script>
</body>
</html>