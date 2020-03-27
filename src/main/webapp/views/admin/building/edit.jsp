<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp" %>
<c:url var="buildingAPI" value="/api-building" />
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
									<input type="text" id="name" name="name" placeholder="name" class="form-control" value="${model.name }" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="numberofbasement"> Số tầng hầm </label>

								<div class="col-sm-10">
									<input type="number" id="numberOfBasement" name="numberOfBasement" placeholder="numberofbasement" class="form-control" value="${model.numberOfBasement }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="rentArea"> Diện tích thuê</label>

								<div class="col-sm-10">
									<input type="text" id="rentArea" name="rentArea" placeholder="rentArea" class="form-control" value="${model.rentArea }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right"> Loại tòa nhà </label>
								<div class="col-sm-10 border">
									<c:forEach var="item" items="${buildingTypes }">
										<label class="checkbox-inline"><input type="checkbox" value="${item.key }" 
											id="buildingTypes" name="buildingTypes" ${fn:contains(fn:join(model.buildingTypes, ','), item.key) ? 'checked' : '' }>${item.value }										
										</label>
									</c:forEach>	
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