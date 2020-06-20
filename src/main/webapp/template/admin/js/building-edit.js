$("#btnAddBuilding").click(function() {				
	console.log('----START----');
	if(!checkInputData()){
		return;
	}
	var data = {};
	var buildingTypes = [];
	var formData = $('#formEdit').serializeArray();
	$.each(formData, function (index, item) { 
		 if(item.name == "buildingTypes"){
			buildingTypes.push(item.value);
		 }else{
			data[""+item.name+""] = item.value;
		 }
	});
	data["buildingTypes"] = buildingTypes;
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/api-building", 
		data: JSON.stringify(data),				
		dataType: "json",
		contentType: "application/json",
		success: function (response) {
			//console.log('success!');
			console.log(response);
			swal("Thêm thành công!", "", "success");
			clearForm();
			$('#name').focus();
		},
		error: function (response) {
			//console.log('failed!');
			console.log(response);
			swal("Đã có lỗi xảy ra!" + response);
		}
	});
	console.log('----END----');
});
$("#btnEditBuilding").click(function() {				
	console.log('----START----');
	if(!checkInputData()){
		return;
	}
	var data = {};
	var buildingTypes = [];
	var formData = $('#formEdit').serializeArray();
	$.each(formData, function (index, item) { 
		 if(item.name == "buildingTypes"){
			buildingTypes.push(item.value);
		 }else{
			data[""+item.name+""] = item.value;
		 }
	});
	data["buildingTypes"] = buildingTypes;
	data["id"] = $('#buildingId').val();
	$.ajax({
		type: "PUT",
		url: "http://localhost:8080/api-building", 
		data: JSON.stringify(data),				
		dataType: "json",
		contenType: "application/json",
		success: function (response) {
			console.log(response);
			swal("Cập nhật thành công!", "", "success");
		},
		error: function (response) {
			//console.log('failed!');
			console.log(response);
			swal("Đã có lỗi xảy ra!" + response);
		}
	});
	console.log('----END----');
});

function clearForm(){
	$('#formEdit')[0].reset();
}
function checkInputData(){
	var flag = true;
	var checked = false;
	if (!$('#name').val()){
		$('#name').css('background-color', '#FEEFB3');
		if(!checked){
			$('#name').focus();
			checked = true;
		}		
		flag = false;
	}else{
		$('#name').css('background-color', '');
	}
	if (!$('#rentArea').val()){
		$('#rentArea').css('background-color', '#FEEFB3');	
		if(!checked){
			$('#rentArea').focus();
			checked = true;
		}
		flag = false;
	}else{	
		var data = $("#rentArea").val().split(/,/);
		var error = true;
		for (var index = 0; index < data.length; ++index) {
		    if(!$.isNumeric(data[index])){
		    	$('#rentArea').css('background-color', '#FEEFB3');	
		    	if(!checked){
	    			$('#rentArea').focus();
	    			checked = true;
	    		}
	    		flag = false;
	    		error = false;
		    	break;
		    }
		}       
		if(error){
			$('#rentArea').css('background-color', '');	
		}
	}
	return flag;
}