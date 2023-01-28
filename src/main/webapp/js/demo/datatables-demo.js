// Call the dataTables jQuery plugin
$(document).ready(function() {
	$('#dataTable').DataTable({
		"columnDefs": [
			{ "orderable": false, "targets": [1, 2] }
		],
		"order": [0, "desc"]
	});
});

$(document).ready(function() {
	$('#dataTables').DataTable({
		"columnDefs": [
			{ "orderable": false, "targets": [1] }
		],
		"order": [0, "desc"]
	});
});
$(document).ready(function() {
	$('#dataTable_manageMem').DataTable({
		"columnDefs": [
			{ "orderable": false, "targets": [0,1,2] }
		],
		"order": [0, "desc"]
	});
});
