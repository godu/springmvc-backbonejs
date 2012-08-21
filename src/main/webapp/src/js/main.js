(function() {
	"use strict";
	
	var libsPath = '../libs/js/'; //libsPath relative to 'basePath'
	
	require.config({
		paths : {
			jquery : libsPath + 'jquery',		//return module but also keeps it in the global scope
			jqueryLoader : libsPath + 'jquery-loader',	//loads module and removes it from the global scope
			underscore : libsPath + 'underscore',
			underscoreLoader: libsPath + 'underscore-loader',
			backbone : libsPath + 'backbone',
			backboneLoader : libsPath + 'backbone-loader',
            backboneLocalstorage : libsPath + 'backbone.localStorage'
		},
		baseUrl : 'src/js',
		urlArgs: "bust=" +  (new Date()).getTime()	//cache-busting for development
	});

    require(['underscoreLoader'],
        function(_) {
            _.templateSettings = {
                interpolate: /<\@\=(.+?)\@\>/gim,
                evaluate: /<\@(.+?)\@\>/gim,
                escape: /<\@-(.+?)\@\>/gim
            };
        }
    );

	require(['jqueryLoader', 'backboneLoader', 'routers/main'],
		function($, Backbone, App) {

			window.app = new App();

            Backbone.history.start();
		}
	);
})();
