define([
        'common',
        'jqueryLoader',
		'underscoreLoader',
		'backboneLoader',
        'views/todos',
        'text!templates/stats.jsp'
	],
	function(
        Common,
        $,
		_,
		Backbone,
        TodoView,
        statsTemplate
	) {
		"use strict";
		
		return Backbone.View.extend({

            // Instead of generating a new element, bind to the existing skeleton of
            // the App already present in the HTML.
            //el: $('#todoapp'),

            // Compile our stats template
            template: _.template( statsTemplate ),

            // Delegated events for creating new items, and clearing completed ones.
            events: {
                'keypress #new-todo':	'createOnEnter',
                'click #clear-completed':	'clearCompleted',
                'click #toggle-all':	'toggleAllComplete'
            },

            // At initialization we bind to the relevant events on the `Todos`
            // collection, when items are added or changed. Kick things off by
            // loading any preexisting todos that might be saved in *localStorage*.
            initialize: function() {
                this.input = this.$('#new-todo');
                this.allCheckbox = this.$('#toggle-all')[0];
                this.$footer = $('#footer');
                this.$main = $('#main');

                Common.Todos.on( 'add', this.addOne, this );
                Common.Todos.on( 'reset', this.addAll, this );
                Common.Todos.on( 'all', this.render, this );
                Common.Todos.fetch();
            },

            // Re-rendering the App just means refreshing the statistics -- the rest
            // of the app doesn't change.
            render: function() {
                var completed = Common.Todos.completed().length;
                var remaining = Common.Todos.remaining().length;

                if ( Common.Todos.length ) {
                    this.$main.show();
                    this.$footer.show();

                    this.$footer.html(this.template({
                        completed: completed,
                        remaining: remaining
                    }));

                    this.$('#filters li a')
                        .removeClass('selected')
                        .filter( '[href="#/' + ( Common.TodoFilter || '' ) + '"]' )
                        .addClass('selected');
                } else {
                    this.$main.hide();
                    this.$footer.hide();
                }

                this.allCheckbox.checked = !remaining;

                return this.$el;
            },

            // Add a single todo item to the list by creating a view for it, and
            // appending its element to the `<ul>`.
            addOne: function( todo ) {
                var view = new TodoView({ model: todo });
                $('#todo-list').append( view.render() );
            },

            // Add all items in the **Todos** collection at once.
            addAll: function() {
                this.$('#todo-list').html('');

                switch( Common.TodoFilter ) {
                    case 'active':
                        _.each( Common.Todos.remaining(), this.addOne );
                        break;
                    case 'completed':
                        _.each( Common.Todos.completed(), this.addOne );
                        break;
                    default:
                        Common.Todos.each( this.addOne, this );
                        break;
                }
            },

            // Generate the attributes for a new Todo item.
            newAttributes: function() {
                return {
                    title: this.input.val().trim(),
                    completed: false
                };
            },

            // If you hit return in the main input field, create new **Todo** model,
            // persisting it to *localStorage*.
            createOnEnter: function( e ) {
                if ( e.which !== Common.ENTER_KEY || !this.input.val().trim() ) {
                    return;
                }

                Common.Todos.create( this.newAttributes() );
                this.input.val('');
            },

            // Clear all completed todo items, destroying their models.
            clearCompleted: function() {
                _.each( Common.Todos.completed(), function( todo ) {
                    todo.clear();
                });

                return false;
            },

            toggleAllComplete: function() {
                var completed = this.allCheckbox.checked;

                Common.Todos.each(function( todo ) {
                    todo.save({
                        'completed': completed
                    });
                });
            }
        });
	}
);
