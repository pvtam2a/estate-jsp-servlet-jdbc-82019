<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp" %>
<c:url var="buldingUrl" value="/admin-building" />
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
						<a href="#">Home</a>
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
														<input type="text" id="name" class="form-control" name="name">
													</div>
												</div>
												<div class="col-sm-4">
													<div>
														<label for="buildingArea">Diện tích sàn</label>
														<input type="number" id="buildingArea" class="form-control" name="buildingArea">
													</div>
												</div>	
												<div class="col-sm-4">
													<div>
														<label for="numberOfBasement">Số tầng hầm</label>
														<input type="number" id="numberOfBasement" class="form-control" name="numberOfBasement">
													</div>
												</div>															
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<div class="form-group">													
													  <select class="form-control" id="district" name="district">
													    <option value="">Chọn quận</option>
													    <option value="QUAN_1">Quận 1</option>
													    <option value="QUAN_2">Quận 2</option>
													    <option value="QUAN_3">Quận 3</option>
													    <option value="QUAN_4">Quận 4</option>
													  </select>
													</div>
												</div>
												<div class="col-sm-4">
													<div>
														<label for="ward">Phường</label>
														<input type="text" id="ward" class="form-control" name="ward">
													</div>
												</div>
												<div class="col-sm-4">
													<div>
														<label for="street">Đường</label>
														<input type="text" id="street" class="form-control" name="street">
													</div>
												</div>
											</div>
											<div class="form-group">													
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Diện tích thuê từ</label>
														<input type="number" id="rentAreaFrom" class="form-control" name="rentAreaFrom" >
													</div>
												</div>	
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Diện tích thuê đến</label>
														<input type="number" id="rentAreaTo" class="form-control" name="rentAreaTo" >
													</div>
												</div>		
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Giá thuê từ</label>
														<input type="number" id="costRentFrom" class="form-control" name="costRentFrom" >
													</div>
												</div>	
												<div class="col-sm-3">
													<div>
														<label for="buildingArea">Giá thuê đến</label>
														<input type="number" id="costRentTo" class="form-control" name="costRentTo" >
													</div>
												</div>															
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label no-padding-right"> Loại tòa nhà </label>
												<div class="col-sm-10 border">
													<label class="checkbox-inline"><input type="checkbox" value="TANG_TRET" id="buildingTypes" name="buildingTypes">Tầng trệt</label>
													<label class="checkbox-inline"><input type="checkbox" value="NGUYEN_CAN" id="buildingTypes" name="buildingTypes">Nguyên căn</label>
													<label class="checkbox-inline"><input type="checkbox" value="NOI_THAT" id="buildingTypes" name="buildingTypes">Nội thất</label>
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
									<th>Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${buildings}">
									<tr>
										<td><input type="checkbox" value="${item.id }" id="checkbox_1"></td>
										<td>${item.name}</td>
										<td></td>
										<td>${item.managerName}</td>
										<td>${item.managerPhone}</td>
										<td>${item.buildingArea}</td>
										<td>${item.costRent}</td>
										<td>${item.serviceCost}</td>
										<td>
											<button class="btn btn-xs btn-success">
												<i class="ace-icon fa fa-check bigger-120"></i>
											</button>
		
											<button class="btn btn-xs btn-info"  data-toggle="tooltip" title="Giao tòa nhà" onclick="assingmentBuilding(${item.id })">
												<i class="fa fa-bars"></i>
											</button>
		
											<button class="btn btn-xs btn-danger">
												<i class="ace-icon fa fa-trash-o bigger-120"></i>
											</button>
		
											<button class="btn btn-xs btn-warning">
												<i class="ace-icon fa fa-flag bigger-120"></i>
											</button>
										</td>
									</tr>
								</c:forEach>								
							</tbody>
						</table>
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
					<tr>
						<td><input type="checkbox" value="1" id="checkbox_1"></td>
						<td>Nguyễn Văn A</td>							
					</tr>
					<tr>
						<td><input type="checkbox" value="2" id="checkbox_2" checked></td>
						<td>Nguyễn Văn B</td>						
					</tr>
					<tr>
						<td><input type="checkbox" value="3" id="checkbox_3"></td>
						<td>Nguyễn Văn C</td>							
					</tr>
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
</body>
</html>