package it.unibo.javafx.layouts;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

// Assumiamo che il nome sia TodoAppHandler e non TodoAppController
public class TodoAppHandler {

    // I riferimenti agli elementi FXML (quelli che devono gestire un'azione)
    @FXML private TextField taskInput;
    @FXML private Button addButton;
    @FXML private VBox todoColumn;
    @FXML private VBox doneColumn; // Se la vogliamo usare

    // Il riferimento al modello (non viene caricato da FXML, va iniettato)
    private TodoAppObservable model;

    // --- Metodi di Inizializzazione ---

    /**
     * Metodo chiamato dopo che tutti gli elementi FXML sono stati caricati.
     */
    @FXML
    public void initialize() {
        // La lista sarà visualizzata una volta che il modello sarà iniettato
    }

    /**
     * Metodo da chiamare dopo aver caricato il controller per iniettare il modello.
     */
    public void setModel(TodoAppObservable model) {
        this.model = model;
        // Collega il listener per aggiornare la GUI
        this.model.getTasks().addListener(this::refreshView);
        // Esegui il primo aggiornamento
        this.refreshView(null);
    }

    // --- Metodi Gestione Eventi (Actions) ---

    /**
     * Gestisce il click sul bottone 'Add'.
     */
    @FXML
    public void handleAddTask() {
        String description = taskInput.getText();
        if (!description.isEmpty()) {
            model.addTask(description);
            taskInput.clear();
        }
    }

    /**
     * Rimuove il task dalla lista.
     * Questo metodo sarà chiamato dai bottoni "X" creati dinamicamente.
     */
    private void handleRemoveTask(String description) {
        model.removeTask(description);
    }

    // --- Metodo di Aggiornamento della View (Core Logic) ---

    /**
     * Aggiorna l'intera colonna dei task ogni volta che la lista nel Model cambia.
     * NOTA: Implementa il requisito di "rimuovere tutti gli elementi presenti e aggiungerli nuovamente".
     */
    private void refreshView(Object o) {
        // Rimuovi tutti i task esistenti dalla colonna Todo (meno l'etichetta "Todo")
        todoColumn.getChildren().remove(1, todoColumn.getChildren().size());

        // Aggiungi tutti i task dal modello alla colonna
        for (String task : model.getTasks()) {
            todoColumn.getChildren().add(createTaskNode(task));
        }
    }

    // --- Metodo per creare dinamicamente il nodo Task (come nel tuo esercizio precedente) ---
    private Node createTaskNode(String content) {
        final Button removeButton = new Button("X");
        removeButton.setMinWidth(25);
        removeButton.setMaxWidth(25);
        removeButton.setMaxHeight(25);
        removeButton.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 0 0;");

        final Label taskLabel = new Label(content);
        taskLabel.setPadding(new Insets(2, 0, 2, 0));
        HBox.setHgrow(taskLabel, Priority.ALWAYS);

        final HBox taskContainer = new HBox(5);
        taskContainer.setAlignment(Pos.CENTER_LEFT);
        taskContainer.getChildren().addAll(removeButton, taskLabel);

        // Collega l'azione di rimozione: quando clicchi "X", chiama handleRemoveTask
        removeButton.setOnAction(e -> handleRemoveTask(content));

        return taskContainer;
    }
}