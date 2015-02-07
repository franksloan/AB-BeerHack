(function () { 'use strict';

  // html elements
  var wrapElm = $( '.wrap' ),
      // page elms
      beerSelectPage = $( '.page.beer-select' ),
      beerInfoPage = $( '.page.beer-info' ),
      byobMapPage = $( '.page.byob-map' ),
      byobInfoPage = $( '.page.byob-info' ),
      // beer items
      beerItemElm = $( '.beer-select-item' ),
      // btn elms
      beerInfoFindBtn = $( '.beer-info-find' ),
      beerInfoBackBtn = $( '.beer-info-back' ),
      byobInfoLaunchBtn = $( '.byob-info-launch' ),
      byobInfoBackBtn = $( '.byob-info-back' );

  // dirty page switching
  beerItemElm.click( function() {
    wrapElm.addClass( 'pos-2' );
    wrapElm.removeClass( 'pos-1' );
  });

  beerInfoFindBtn.click( function() {
    wrapElm.addClass( 'pos-3' );
    wrapElm.removeClass( 'pos-2' );
  });

  beerInfoBackBtn.click( function() {
    wrapElm.addClass( 'pos-1' );
    wrapElm.removeClass( 'pos-2' );
  });

  byobInfoBackBtn.click( function() {
    wrapElm.addClass( 'pos-3' );
    wrapElm.removeClass( 'pos-4' );
  });

  var mapColors = [
    {
      "stylers": [
        { "color": "#ecd68d" }
      ]
    },{
      "featureType": "water",
      "stylers": [
        { "color": "#ecd68d" },
        { "hue": "#ff3300" }
      ]
    },{
      "featureType": "landscape.man_made",
      "stylers": [
        { "color": "#ecd68d" },
        { "hue": "#ffff00" },
        { "lightness": 17 },
        { "saturation": 70 }
      ]
    },{
      "featureType": "road",
      "elementType": "labels.text.fill",
      "stylers": [
        { "color": "#ecd68d" },
        { "hue": "#ff4d00" },
        { "saturation": 1 },
        { "lightness": -32 }
      ]
    },{
      "featureType": "transit.station.rail",
      "elementType": "labels.text"  },{
    }
  ];

  // G maps
  function initialize() {
      var mapOptions = {
        center: { lat: 51.5079, lng: -0.131782 },
        zoom: 14,
        disableDefaultUI: true,
        styles: mapColors
      };
      var map = new google.maps.Map(document.getElementById('map-canvas'),
          mapOptions);
    }
    google.maps.event.addDomListener(window, 'load', initialize);

}()); // end 'use strict'