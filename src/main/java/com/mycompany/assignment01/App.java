package com.mycompany.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    
    //value of the key pressed
    private Label keyValueLabel;
    
    // to show "not hqandled" later in red
    private Label notHandledLabel;
    
    // will show the good keys being pressed
    private Label correctLabel;
    
    // will show the incorrect keys being pressed
    private Label incorrectLabel;
    
    // index of the current text that will be shown
    private int currentIndex = 0;
    
    // to keep track of the count of good keys tha were pressed
    private int correctCount = 0;
    
    // to keep track of the incorrect keys that were pressed
    private int incorrectCount = 0;

    @Override
    public void start(Stage stage) {
        
        // OUR ROOT LAYOUT THATS GONNA BE NEEDED THROUGHOUT THE PROJECT
        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.setAlignment(Pos.CENTER);
        
        
        Label instructionLabel = new Label("Type the text below:");
        
        promptLabel = new Label(textLines[currentIndex]);
        
        typedField = new TextField();
        typedField.setPrefWidth(500);
        
        counterLabel = new Label((currentIndex + 1) + " of " + textLines.length);
        
        // labels for info
        
        keyValueLabel = new Label("Key: ");
        
        notHandledLabel = new Label(); // will need to make like red
        
        correctLabel = new Label("Correct: 0");
        incorrectLabel = new Label("Incorrect: 0");
        
    }

    public static void main(String[] args) {
        launch();
    }

}