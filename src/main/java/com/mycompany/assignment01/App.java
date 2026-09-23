package com.mycompany.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
        typedField.setPrefWidth(200);
        
        counterLabel = new Label((currentIndex + 1) + " of " + textLines.length);
        
        // labels for info
        
        keyValueLabel = new Label("Key: ");
        
        notHandledLabel = new Label(); // will need to make like red
        
        correctLabel = new Label("Correct: 0");
        incorrectLabel = new Label("Incorrect: 0");
        
        
        // NEXT BUTTON TO MOVE ON TP NEXT TEXT LINE
        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {
            
            if (currentIndex < textLines.length - 1) {
                
                currentIndex++;
                promptLabel.setText(textLines[currentIndex]);
                typedField.setText("");
                counterLabel.setText((currentIndex + 1) + " of " + textLines.length);
            }
          
            root.requestFocus(); // chsnce of glitching if the focus isnt set back to base layout as itll stay focused on button and nothing else will work
        });

        // RESET BUTTON TO SET EVERYTHING BACK TO 0 IF NOT PROGRM GLITCH AND RESET TEXT LINES TO BEGINNING
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            
            currentIndex = 0;
            promptLabel.setText(textLines[currentIndex]);
            typedField.setText("");
            counterLabel.setText((currentIndex + 1) + " of " + textLines.length);
            correctCount = 0;
            incorrectCount = 0;
            correctLabel.setText("Correct: 0");
            incorrectLabel.setText("Incorrect: 0");
            keyValueLabel.setText("Key: ");
            notHandledLabel.setText("");
            
            root.requestFocus(); // same reason as above, need to make sure focus is set back
        });
        
        // Adding next button, reset button and the counter label for the text lines into an hbox
        HBox controlBox = new HBox(10, nextButton, resetButton, counterLabel);
        controlBox.setAlignment(Pos.CENTER);

        // Adding key value label, correct and incorrect labels and the exception not handled label into an hbox for display
        HBox statsBox = new HBox(20, keyValueLabel, correctLabel, incorrectLabel, notHandledLabel);
        statsBox.setAlignment(Pos.CENTER);

        // Adding the main instruction label, the prompt label for the text lines and the type field into vbox for display
        VBox topBox = new VBox(10, instructionLabel, promptLabel, typedField, controlBox, statsBox);
        topBox.setPadding(new Insets(15));
        topBox.setAlignment(Pos.CENTER);

        
    }

    public static void main(String[] args) {
        launch();
    }

}
