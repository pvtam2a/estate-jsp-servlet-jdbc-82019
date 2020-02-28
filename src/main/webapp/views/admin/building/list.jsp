<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp" %>
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
									<form class="form-horizontal" action="/index.html"> 												
										<div class="form-group">
											<div class="col-sm-6">
												<div>
													<label for="name">Tên tòa nhà</label>
													<input type="text" id="name" class="form-control">
												</div>
											</div>
											<div class="col-sm-6">
												<div>
													<label for="buildingArea">Diện tích sàn</label>
													<input type="number" id="buildingArea" class="form-control">
												</div>
											</div>														
										</div>
										<div class="form-group">													
											<div class="col-sm-3">
												<div>
													<label for="buildingArea">Diện tích từ</label>
													<input type="number" id="rentAreaFrom" class="form-control" name="rentAreaFrom" >
												</div>
											</div>	
											<div class="col-sm-3">
												<div>
													<label for="buildingArea">Diện tích đến</label>
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
									</form>
								</div>
							</div>
						</div>								
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->
					<div class="col-xs-12">
						<div class="pull-right">
							<button class="btn btn-white" data-toggle="tooltip" title="Thêm tòa nhà">
								<i class="fa fa-plus-circle"></i>
							</button>	
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
								<tr>
									<td><input type="checkbox" value="1" id="checkbox_1"></td>
									<td>Nam Giao Building</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>
										<button class="btn btn-xs btn-success">
											<i class="ace-icon fa fa-check bigger-120"></i>
										</button>
	
										<button class="btn btn-xs btn-info"  data-toggle="tooltip" title="Giao tòa nhà" onclick="assingmentBuilding(1)">
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
								<tr>
									<td><input type="checkbox" value="2" id="checkbox_2"></td>
									<td>CMC Building</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>xxx</td>
									<td>
										<button class="btn btn-xs btn-success">
											<i class="ace-icon fa fa-check bigger-120"></i>
										</button>
	
										<button class="btn btn-xs btn-info"  data-toggle="tooltip" title="Giao tòa nhà" onclick="assingmentBuilding(2)">
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