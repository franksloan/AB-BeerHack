$(document).ready(function(){
	console.log("ready");
	//click on a beer event handler
	$('.beer').on('click', function() {
	var beerBrand = $(this).attr("data-beer");
	console.log(beerBrand);
		$.ajax({
			url: "backend.java",
			data: beerBrand,
			type: "GET",
			dataType: "json",
			success: function(response) {
				$('.HERE').html('')
			},
			error: function() {
				$('.HERE').html('<p>Error fetching beer data</p>');
			},
			timeout: 3000
		});
	})
})

