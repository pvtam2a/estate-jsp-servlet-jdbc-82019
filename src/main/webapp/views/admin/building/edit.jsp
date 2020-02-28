<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sửa thông tin tòa nhà</title>
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
						<form class="form-horizontal" role="form" id="formEdit">
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="name"> Tên tòa nhà </label>

								<div class="col-sm-10">
									<input type="text" id="name" name="name" placeholder="name" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="numberofbasement"> Số tầng hầm </label>

								<div class="col-sm-10">
									<input type="number" id="numberofbasement" name="numberofbasement" placeholder="numberofbasement" class="form-control" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="rentArea"> Diện tích thuê</label>

								<div class="col-sm-10">
									<input type="text" id="rentArea" name="rentArea" placeholder="rentArea" class="form-control" />
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
								<label class="col-sm-2 control-label no-padding-right"></label>
								<div class="col-sm-10">
									<button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm tòa nhà</button>
									<button type="button" class="btn btn-primary" id="btnCancel">Hủy</button>
								</div>
							</div>
							
						</form>
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->							
				</div><!-- /.row -->					
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
</body>
</html>