function assingmentBuilding(buildingId){
	openModelAssingmentBuilding();		
	$('#buildingId').val(buildingId);
	console.log($('#buildingId').val());	
	showStaffAssignment();
}
function openModelAssingmentBuilding() { 
	$('#assingmentBuildingModel').modal();
}
$('#btnAssignBuilding').click(function (e) { 
	e.preventDefault();
	var data = {};
	data['buildingId'] = $('#buildingId').val();			
	//$('#staffList').find('tbody input[type=checkbox]');
	var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
		return $(this).val();
	}).get();
	data['staffs'] =  staffs;
	assignStaff(data);
	
});
function assignStaff(data){
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/api-user-assignment",
		data: JSON.stringify(data),				
		dataType: "application/json",
		success: function (response) {
			console.log('success!');
			console.log(response);
		},
		error: function (response) {
			console.log('failed!');
			console.log(response);
		}
	});
}
function showStaffAssignment(){
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/api-user?type=SHOW_STAFF_ASSIGNMENT",		
		dataType: "json",
		success: function (response) {
			console.log('success!');
			console.log(response);
			var html = '';
			$.each(response, function (index, staffOutput) { 
				html += '<tr>';
				html += '<td><input type="checkbox" value="'+staffOutput.staffId+'" id="checkbox_'+staffOutput.staffId+'" '+staffOutput.checked+'></td>';
				html += '<td>'+staffOutput.fullName+'</td>';
				html += '</tr>';
			});			
			$('#staffList tbody').html(html);
		},
		error: function (response) {
			console.log('failed!');
			console.log(response);
		}
	});
}
$('#btnDeleteBuilding').click(function (e) { 
	e.preventDefault();
	if (!confirm("Bạn chắc chắn muốn xóa không?")) {
	    return;
	}
	var data = {};			
	//$('#staffList').find('tbody input[type=checkbox]');
	var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
		return $(this).val();
	}).get();
	data['ids'] =  buildingIds;
	if(buildingIds.length == 0){
		//showModal("", "Chưa có tòa nhà nào được chọn.")
		alert("Chưa có tòa nhà nào được chọn.")
		return;
	}
	deleteBuilding(data);
});
function deleteBuilding(data){
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/api-building",
		data: JSON.stringify(data),				
		dataType: "json",
		success: function (response) {
			console.log('success!');
			console.log(response);	
			alert("Xóa thành công!");
			location.reload();
		},
		error: function (response) {
			console.log('failed!');
			alert("Đã có lỗi xảy ra!" + response);
			console.log(response);
		}
	});
}
$('#btnSearchBuilding').click(function (e) { 
	e.preventDefault();
	$('#formSearchBuilding').submit();
});

function removeBuilding(buildingId){
	if (!confirm("Bạn chắc chắn muốn xóa không?")) {
	    return;
	}
	var data = {};	
	data['ids'] =  [buildingId];
	deleteBuilding(data);
}