define(['collections/todos'], function(Todos) {

    var todos = new Todos();
    todos.url = 'api/todos';

    return {
// Which filter are we using?
        TodoFilter: '', // empty, active, completed

// What is the enter key constant?
        ENTER_KEY: 13,

        Todos: todos
    };
});