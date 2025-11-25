package it.unibo.javafx.layouts;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10)); // Un po' di padding intorno a tutto il BorderPane

        // -----------------------------------------------------------
        // A. LEFT PANE (Form di input)
        // -----------------------------------------------------------
        VBox leftPane = new VBox();
        leftPane.setSpacing(10);
        leftPane.setPadding(new Insets(10, 20, 10, 10)); // Padding: top, right, bottom, left
        leftPane.setAlignment(Pos.TOP_CENTER);
        leftPane.setMinWidth(200);

        final Label titoloLeft = new Label("Todo App");

        final TextField taskInput = new TextField();
        taskInput.setPromptText(""); // Rimosso prompt text per abbinare immagine

        final Button addButton = new Button("Add");

        // Assemblaggio del Left Pane
        leftPane.getChildren().addAll(titoloLeft, taskInput, addButton);


        // -----------------------------------------------------------
        // B. CENTER PANE (Contenitore per le liste)
        // -----------------------------------------------------------
        VBox centerPane = new VBox(); // Rinominato da rightPane a centerPane per chiarezza con BorderPane.setCenter
        centerPane.setSpacing(15);
        centerPane.setPadding(new Insets(10, 10, 10, 20)); // Padding: top, right, bottom, left

        // 1. Intestazione "Tasks"
        final Label tasksHeader = new Label("Tasks");

        // 2. Contenitore orizzontale per le due colonne (HBox)
        HBox columnsContainer = new HBox(40); // Aumento spaziatura tra le colonne per abbinare immagine

        // --- Colonna "Todo" (VBox) ---
        VBox todoColumn = new VBox(5);
        final Label todoLabel = new Label("Todo");
        todoLabel.setStyle("-fx-font-weight: bold;");

        // Aggiunta dei task 'Todo' (non completati)
        todoColumn.getChildren().addAll(
                todoLabel,
                createTaskSection("Task1", false), // Esempi con "Task1"
                createTaskSection("Task2", false)
        );
        HBox.setHgrow(todoColumn, Priority.ALWAYS);

        // --- Colonna "Done" (VBox) ---
        VBox doneColumn = new VBox(5);
        final Label doneLabel = new Label("Done");
        doneLabel.setStyle("-fx-font-weight: bold;");

        // Aggiunta dei task 'Done' (completati)
        doneColumn.getChildren().addAll(
                doneLabel,
                createTaskSection("Completed Task", true) // Esempio con un solo task completato
        );
        HBox.setHgrow(doneColumn, Priority.ALWAYS);

        // Assemblaggio delle Colonne
        columnsContainer.getChildren().addAll(todoColumn, doneColumn);
        centerPane.getChildren().addAll(tasksHeader, columnsContainer);


        // -----------------------------------------------------------
        // C. Setup del Root
        // -----------------------------------------------------------
        root.setLeft(leftPane);
        root.setCenter(centerPane); // Usiamo centerPane qui

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("ToDo List App"); // Titolo della finestra
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // -----------------------------------------------------------
    // METODO HELPER (DA AGGIUNGERE ALLA CLASSE App)
    // -----------------------------------------------------------
    /**
     * Crea un singolo elemento Task (Label + Button "X") racchiuso in un HBox.
     * @param content La descrizione del task.
     * @param isCompleted Se TRUE, il task è considerato completato.
     * @return Il Node (HBox) che rappresenta il singolo task.
     */
    private static Node createTaskSection(String content, boolean isCompleted) {
        final Button markCompletedButton = new Button("X");
        markCompletedButton.setMinWidth(25);
        markCompletedButton.setMaxWidth(25);
        markCompletedButton.setMaxHeight(25);
        markCompletedButton.setStyle("-fx-font-weight: bold; -fx-padding: 0 0 0 0;");

        final Label taskLabel = new Label(content);
        taskLabel.setPadding(new Insets(2, 0, 2, 0));
        HBox.setHgrow(taskLabel, Priority.ALWAYS);

        final HBox taskContainer = new HBox(5); // Spaziatura tra bottone X e label
        taskContainer.setAlignment(Pos.CENTER_LEFT);
        taskContainer.getChildren().addAll(markCompletedButton, taskLabel);

        if (isCompleted) {
            markCompletedButton.setVisible(false);
            markCompletedButton.setManaged(false);
            taskLabel.setStyle("-fx-text-fill: gray;"); // Testo grigio per abbinare immagine
        }

        return taskContainer;
    }

    public static class Main {
        static void main(String... args) {
            Application.launch(App.class, args);
        }
    }
}