package it.unibo.javafx;

import javafx.beans.property.ListProperty;

/**
 * An observable version of the todo application.
 */
public interface TodoAppObservable extends TodoApp {
    /**
     * Returns the list of todos as a property.
     * @return the list of todos as a property
     */
    ListProperty<Todo> todosProperty();

    /**
     * Returns the list of completed todos as a property.
     * @return the list of completed todos as a property
     */
    ListProperty<Todo> completedTodosProperty();
}
