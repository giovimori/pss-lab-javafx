package it.unibo.javafx.layouts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TodoAppImpl implements TodoAppObservable {

    // Il cuore del modello: una lista che notifica i cambiamenti
    private final ObservableList<String> taskList = FXCollections.observableArrayList();

    /**
     * Il costruttore può inizializzare la lista con dati di esempio.
     */
    public TodoAppImpl() {
        taskList.add("Configurare FXML e Controller");
        taskList.add("Aggiungere i metodi in TodoAppImpl");
    }

    /**
     * @return La lista osservabile dei task, come richiesto dall'interfaccia.
     */
    @Override
    public ObservableList<String> getTasks() {
        return taskList;
    }

    /**
     * Aggiunge un task se non è vuoto.
     */
    @Override
    public void addTask(String description) {
        if (description != null && !description.trim().isEmpty()) {
            taskList.add(description.trim());
        }
    }

    /**
     * Rimuove un task.
     */
    @Override
    public void removeTask(String description) {
        taskList.remove(description);
    }
}