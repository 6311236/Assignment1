package com.mycompany.assignment01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
    
    private String[] textLines = {
        
        "Try typing this text. Do it as quickly and accurately as you can.",
        "Next type another line of input data.",
        "The quick brown fox jumps over the lazy dog.",
        "Five big quacking zephyrs jolt my wax bed.",
        "Sympathizing would fix Quaker objectives.",
        "A large fawn jumped quickly over white zinc boxes."
    };
    
    
    //text the user must type
    private Label promptLabel;      
    
    //what the user has typed
    private TextField typedField;
    
    // shows the "1 of 6" count
    private Label counterLabel;
    
    

    @Override
    public void start(Stage stage) {
        
    }

    public static void main(String[] args) {
        launch();
    }

}