package it.unibo.javafx.layouts;

import javafx.collections.ObservableList;
import java.util.List; // O il tipo di dato che userai per i task

// Assumiamo che i task siano rappresentati da semplici Stringhe per ora.
public interface TodoAppObservable {

    /** Restituisce la lista osservabile di tutti i task. */
    ObservableList<String> getTasks();

    /** Aggiunge un nuovo task. */
    void addTask(String description);

    /** Rimuove un task dalla lista. */
    void removeTask(String description);

}