var map;
var mapColors;
var GeoMarker;
var query;
var infoWindows = [];
var endpoint = 'http://54.148.62.37:8081';

$('button.beer-info-find').click(function () {
	query = $(this).data('beer');
	getLocation();
});

window.onload = function () {

	var randomBeer = new Shake({
		threshold: 15
	});

	randomBeer.start();

	window.addEventListener('shake', randomBeerEvent, false);

	function randomBeerEvent() {
		beers = $('.beer-select-item');
		$(beers[Math.floor(Math.random() * beers.length)]).click();
	}
};

function getLocation() {

	navigator.geolocation.getCurrentPosition(function (position) {

		var panPoint = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
		map.panTo(panPoint)

		var GeoMarker = new GeolocationMarker(map);
		search(query, position.coords.latitude, position.coords.longitude);

	});

}

function search(name, locationLat, locationLong) {

	var data = {
		name: name,
		location: locationLat + ',' + locationLong
	};

	$.getJSON(endpoint, data, function (json) {

		$.each(json.places, function (key, listing) {

			var position = new google.maps.LatLng(listing.latitude, listing.longitude);
			var infowindow = new google.maps.InfoWindow({
				content: '<p><strong>Name: </strong>' + listing.name + '<br /><strong>Address: </strong><a href="http://maps.apple.com/?daddr=' + encodeURIComponent(listing.address) + '">' + listing.address + '</a><br /><strong>Phone Number: </strong><a href="tel:' + listing.phone + '">' + listing.phone + '</a></p>'
			});

			var marker = new google.maps.Marker({
				position: position,
				map: map,
				title: listing.name
			});

			google.maps.event.addListener(marker, 'click', function () {

				for (var i = 0; i < infoWindows.length; i++) {
					infoWindows[i].close();
				}

				infoWindows.push(infowindow);
				infowindow.open(map, marker);
			});


		});

	});

}