define([
		'underscoreLoader',
		'backboneLoader',
        'models/todo'
	],
	function(
		_,
		Backbone,
        Todo
	) {
        "use strict";

        return Backbone.Collection.extend({

            // Reference to this collection's model.
            model: Todo,

            // Filter down the list of all todo items that are finished.
            completed: function() {
                return this.filter(function( todo ) {
                    return todo.get('completed');
                });
            },

            // Filter down the list to only todo items that are still not finished.
            remaining: function() {
                return this.without.apply( this, this.completed() );
            },

            // Todos are sorted by their original insertion order.
            comparator: function( todo ) {
                return todo.get('order');
            }
        });
	}
);
