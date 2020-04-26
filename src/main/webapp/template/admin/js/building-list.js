function assingmentBuilding(buildingId){	
	$('#buildingId').val(buildingId);
	console.log($('#buildingId').val());	
	showStaffAssignment();
	openModelAssingmentBuilding();		
}
function openModelAssingmentBuilding() { 
	$('#assingmentBuildingModel').modal();
}
$('#btnAssignBuilding').click(function (e) { 
	e.preventDefault();
	var data = {};
	data['buildingId'] = $('#buildingId').val();			
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
	/*if (!confirm("Bạn chắc chắn muốn xóa không?")) {
	    return;
	}
	var data = {};			
	var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
		return $(this).val();
	}).get();
	data['ids'] =  buildingIds;
	if(buildingIds.length == 0){
		alert("Chưa có tòa nhà nào được chọn.")
		return;
	}
	deleteBuilding(data);*/
	
	swal({
	  title: "Bạn chắc chắn chưa?",
	  text: "Bạn sẽ không thể phục hồi lại dữ liệu khi đã xóa!",
	  type: "warning",
	  showCancelButton: true,
	  confirmButtonClass: "btn-danger",
	  confirmButtonText: "Vâng, tôi sẽ xóa!",
	  cancelButtonText: "Không, làm ơn hủy!",
	  closeOnConfirm: false,
	  closeOnCancel: false
	},
	function(isConfirm) {
	  if (isConfirm) {
		  	var data = {};			
			var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
				return $(this).val();
			}).get();
			data['ids'] =  buildingIds;
			if(buildingIds.length == 0){			
				swal("Chưa có tòa nhà nào được chọn.", "", "warning");
				return;
			}
			deleteBuilding(data);
	    
	  } else {
	    swal("Cancelled", "Your imaginary file is safe :)", "error");
	  }
	});

});
function deleteBuilding(data){
	$.ajax({
		type: "DELETE",
		url: "http://localhost:8080/api-building",
		data: JSON.stringify(data),				
		dataType: "json",
		success: function (response) {	
			//swal("Xóa thành công!!", "Your imaginary file has been deleted.", "success");
			//location.reload();
			swal({title: "Xóa thành công!", text: "Your imaginary file has been deleted.", type: "success"},
			   function(){ 
			       location.reload();
			   }
			);
		},
		error: function (response) {	
			swal("Đã có lỗi xảy ra!", ""+response+"", "success");
		}
	});
}
$('#btnSearchBuilding').click(function (e) { 
	e.preventDefault();
	 $('#page').val(1);
	 $('#limit').val(5);
	$('#formSearchBuilding').submit();
});

function removeBuilding(buildingId){
	swal({
	  title: "Bạn chắc chắn chưa?",
	  text: "Bạn sẽ không thể phục hồi lại dữ liệu khi đã xóa!",
	  type: "warning",
	  showCancelButton: true,
	  confirmButtonClass: "btn-danger",
	  confirmButtonText: "Vâng, tôi sẽ xóa!",
	  cancelButtonText: "Không, làm ơn hủy!",
	  closeOnConfirm: false,
	  closeOnCancel: false
	},
	function(isConfirm) {
	  if (isConfirm) {			  
		  	var data = {};	
			data['ids'] =  [buildingId];
			deleteBuilding(data);
	    
	  } else {
	    swal("Cancelled", "Your imaginary file is safe :)", "error");
	  }
	});	
	
}
