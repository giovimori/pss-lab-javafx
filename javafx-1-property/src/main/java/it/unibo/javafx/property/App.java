package it.unibo.javafx.property;

import javafx.application.Application;
import javafx.geometry.Pos; // Importante per centrare gli elementi
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Controls Exercise!");

        // 1. Setup del layout principale (VBox)
        final VBox mainPane = new VBox();
        mainPane.setAlignment(Pos.CENTER); // Centra tutto verticalmente e orizzontalmente

        final Counter counter = new Counter();

        // 2. Creazione della GUI
        final Label titoloLeft = new Label("Contatore:");

        final Label display = new Label();
        final Button incrementBtn = new Button("+");
        final Button decrementBtn = new Button("-");
        final Button resetBtn = new Button("reset");


        // 3. Impostare la larghezza minima come richiesto
        incrementBtn.setMinWidth(100);
        decrementBtn.setMinWidth(100);
        resetBtn.setMinWidth(100);

        // 4. Creazione di un sotto-layout orizzontale per i bottoni
        final HBox buttonsPane = new HBox(10);
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setSpacing(10);
        buttonsPane.getChildren().addAll(incrementBtn, decrementBtn);

        // 5. Aggiunta degli elementi al pannello principale
        mainPane.getChildren().addAll(titoloLeft,display, buttonsPane, resetBtn);

        // 6. Logica dei pulsanti (Event Handling)
        incrementBtn.setOnAction(e -> counter.increment());
        decrementBtn.setOnAction(e -> counter.decrement());
        resetBtn.setOnAction(e -> counter.reset());

        //data binding
        display.textProperty().bind(counter.counterProperty().asString());

        primaryStage.setScene(new Scene(mainPane, 300, 200));
        primaryStage.show();
    }

    public static class Main {
        public static void main(final String... args) {
            Application.launch(App.class, args);
        }
    }
}


