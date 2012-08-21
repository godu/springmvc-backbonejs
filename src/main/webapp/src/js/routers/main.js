define([
        'jqueryLoader',
        'backboneLoader',
        'views/main',
        'common'
	],
	function(
        $,
        Backbone,
        Main,
        Common
	) {
		"use strict";

		return Backbone.Router.extend({

            initialize: function() {
                this.views = {
                    app : new Main({
                        el : $('#todoapp')
                    }).render()
                };
            },

            routes : {
                "*filter" : "setFilter"
            },

            setFilter : function( param ) {

                // Set the current filter to be used
                Common.TodoFilter = param.trim() || '';

                // Trigger a collection reset/addAll
                Common.Todos.trigger('reset');

            }
		});
	}
);