package it.unibo.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Controller for the `JavaFXAppWithFXML` application.
 */
public class TodoAppHandler {
    private TodoAppObservable model;
    private TextField todoText;
    private VBox tasksTodo;
    private VBox tasksDone;
    public TodoAppHandler(
        TodoAppObservable model,
        TextField todoText,
        VBox tasksTodo,
        VBox tasksDone
    ) {
        this.model = model;
        this.model = new TodoAppImpl(); // creo il modello, la logica dell'applicazione
        // aggiungo un listener sulla lista dei todos
        this.model.todosProperty().addListener((observable, oldValue, newValue) -> updateTodos(newValue));
        // aggiungo un listener sulla lista dei completedTodos
        this.model.completedTodosProperty().addListener((observable, oldValue, newValue) -> updateDone(newValue));
    }

    // Handler da eseguire quando si preme il bottone "Add"
    @FXML
    public final void addTodo(final MouseEvent evt) {
        // aggiungi un nuovo todo solo se il testo non e vuoto
    }

    // Aggiornamento dei todos, vengono tolti tutti quelli presenti e aggiunti quelli nuovi
    private void updateTodos(List<Todo> todos) {
        tasksTodo.getChildren().clear();
        todos.forEach(this::insertTodoGraphic);
    }

    // Aggiornamento dei completedTodos, vengono tolti tutti quelli presenti e aggiunti quelli nuovi
    private void updateDone(List<Todo> todos) {
        // Simile a quello di prima
    }

    // Inserisce un nuovo todo nella lista dei todos
    // Un todo e composta da un testo e da un bottone per eliminarlo
    // il bottone viene associato ad un evento che chiama il metodo completeTodo del modello!!
    private void insertTodoGraphic(Todo todo) {
        var todoGraphic = new HBox();
        // TODO
    }

    // Inserisce un nuovo todo nella lista dei completedTodos
    // qui ci sarà solo il testo del todo
    private void insertCompleted(Todo todo) {
        var todoGraphic = new HBox();
        // TODO
    }
}
