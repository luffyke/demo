$(document).ready(function() {
	$('tr:first').css("background","#EAEAEA");
	$('tr').not(':first').hover(
		function () {
			$(this).css("background","YELLOW");
		}, 
		function () {
			$(this).css("background","");
		}
	);
});