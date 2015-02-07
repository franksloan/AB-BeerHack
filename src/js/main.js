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

  // replace with actuall items
  byobMapPage.click( function() {
    wrapElm.addClass( 'pos-4' );
    wrapElm.removeClass( 'pos-3' );
  });

  byobInfoBackBtn.click( function() {
    wrapElm.addClass( 'pos-3' );
    wrapElm.removeClass( 'pos-4' );
  });

}()); // end 'use strict'