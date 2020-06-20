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
						<a href="<c:url value='/admin-building?action=LIST' />">Home</a>
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
								<label class="col-sm-2 control-label no-padding-right" for="buildingArea"> Diện tích sàn </label>

								<div class="col-sm-10">
									<input type="number" id="numberOfBasement" name="buildingArea" placeholder="buildingArea" class="form-control" value="${model.buildingArea }"/>
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
								<label class="col-sm-2 control-label no-padding-right" for="district"> Quận</label>

								<div class="col-sm-10">
									<select class="form-control" id="district" name="district">
									    <option value="">---Chọn quận---</option>									  
									    <c:forEach var="item" items="${districts }">
									    	<option value="${item.key}" ${item.key == model.district ? 'selected' : '' }>${item.value}</option>
									    </c:forEach>
									  </select>			
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="ward"> Phường</label>

								<div class="col-sm-10">
									<input type="text" id="ward" name="ward" placeholder="ward" class="form-control" value="${model.ward }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="street"> Đường</label>

								<div class="col-sm-10">
									<input type="text" id="street" name="street" placeholder="street" class="form-control" value="${model.street }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="structure"> Cấu trúc</label>

								<div class="col-sm-10">
									<input type="text" id="structure" name="structure" placeholder="structure" class="form-control" value="${model.structure }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="costRent"> Giá thuê </label>

								<div class="col-sm-10">
									<input type="number" id="costRent" name="costRent" placeholder="costRent" class="form-control" value="${model.costRent }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="costDescription"> Giá mô tả</label>

								<div class="col-sm-10">
									<input type="text" id="costDescription" name="costDescription" placeholder="costDescription" class="form-control" value="${model.costDescription }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="serviceCost"> Giá dịch vụ</label>

								<div class="col-sm-10">
									<input type="text" id="serviceCost" name="serviceCost" placeholder="serviceCost" class="form-control" value="${model.serviceCost }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="carCost"> carCost</label>

								<div class="col-sm-10">
									<input type="text" id="carCost" name="carCost" placeholder="carCost" class="form-control" value="${model.carCost }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="motorbikeCost"> motorbikeCost</label>

								<div class="col-sm-10">
									<input type="text" id="motorbikeCost" name="motorbikeCost" placeholder="motorbikeCost" class="form-control" value="${model.motorbikeCost }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="overtimeCost"> overtimeCost</label>

								<div class="col-sm-10">
									<input type="text" id="overtimeCost" name="overtimeCost" placeholder="overtimeCost" class="form-control" value="${model.overtimeCost }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="electricityCost"> electricityCost</label>

								<div class="col-sm-10">
									<input type="text" id="electricityCost" name="electricityCost" placeholder="electricityCost" class="form-control" value="${model.electricityCost }"/>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="deposit"> deposit</label>

								<div class="col-sm-10">
									<input type="text" id="deposit" name="deposit" placeholder="deposit" class="form-control" value="${model.deposit }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="payment"> payment</label>

								<div class="col-sm-10">
									<input type="text" id="payment" name="payment" placeholder="payment" class="form-control" value="${model.payment }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="timeRent"> timeRent</label>

								<div class="col-sm-10">
									<input type="text" id="timeRent" name="timeRent" placeholder="timeRent" class="form-control" value="${model.timeRent }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="timeDecorator"> timeDecorator</label>

								<div class="col-sm-10">
									<input type="text" id="timeDecorator" name="timeDecorator" placeholder="timeDecorator" class="form-control" value="${model.timeDecorator }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="managerName"> Tên quản lý</label>

								<div class="col-sm-10">
									<input type="text" id="managerName" name="managerName" placeholder="managerName" class="form-control" value="${model.managerName }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label no-padding-right" for="managerPhone"> Số điện thoại quản lý</label>

								<div class="col-sm-10">
									<input type="text" id="managerPhone" name="managerPhone" placeholder="managerPhone" class="form-control" value="${model.managerPhone }"/>
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
									<c:choose>									
									    <c:when test="${empty param.id}">
									        <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm tòa nhà</button>
									    </c:when>    
									    <c:otherwise>
									        <button type="button" class="btn btn-primary" id="btnEditBuilding">Cập nhật tòa nhà</button>
									    </c:otherwise>
									</c:choose>
									<button type="button" class="btn btn-primary" id="btnCancel">Hủy</button>
								</div>
							</div>
							
						</form>
						<input type="hidden" id="buildingId" name="buildingId" value="${param.id}">
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.col -->							
				</div><!-- /.row -->					
			</div><!-- /.page-content -->
		</div>
	</div><!-- /.main-content -->
</body>
</html>