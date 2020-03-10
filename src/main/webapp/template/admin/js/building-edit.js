$("#btnAddBuilding").click(function() {				
	console.log('----START----');
	
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
		contenType: "application/json",
		success: function (response) {
			console.log('success!');
			console.log(response);
		},
		error: function (response) {
			console.log('failed!');
			console.log(response);
		}
	});
	console.log('----END----');
});