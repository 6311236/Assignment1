package com.mycompany.assignment01;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    // Button array for all of they key buttons (will be needed after to loop through fpr each key so having in one array to loop is best)
    private Button[] allKeyButtons;

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
        
        // making the actual virtual keyboard 
        
        VBox keyboard = buildKeyboard();

        root.getChildren().addAll(topBox, keyboard);

        Scene scene = new Scene(root, 800, 800);
        
        stage.setScene(scene);
        
        stage.show();
    }
    
     private void handleKeyPressed(KeyEvent event) {
         
        KeyCode code = event.getCode();

        // to disiply a name for the key being pressed
        
        if (code == KeyCode.SPACE) {
            
            keyValueLabel.setText("Key: SPACE");
            
        } else if (code == KeyCode.BACK_SPACE) {
            
            keyValueLabel.setText("Key: BACKSPACE");
            
            // for the user to use backspace to remove errors (IF NOT WOLL CAUSE GLITCH AND NOT ABLE TO FIX SENTENCE)
            
            String currentTyped = typedField.getText();
            
            if (currentTyped.length() > 0) {
                
                typedField.setText(currentTyped.substring(0, currentTyped.length() - 1));
            }
            
        } else if (code == KeyCode.ENTER) {
            
            keyValueLabel.setText("Key: ENTER");
            
        } else if (code == KeyCode.SHIFT) {
            
            keyValueLabel.setText("Key: SHIFT");
            
        } else {
            
            keyValueLabel.setText("Key: " + code.getName());
        }

        
    }
    
    private VBox buildKeyboard() {
        String[][] rows = {
            
            {"`", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "=", "Backspace"},
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L", ";", "'", "Enter"},
            {"Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/"},
            {"Space"}
        };

        // needed to count how many key buttons there will be so that we can have a size for the array
        
        int totalKeys = 0;
        
        for (String[] row : rows) {
            
            totalKeys = totalKeys + row.length;
        }
        allKeyButtons = new Button[totalKeys];
        
        int nextIndex = 0;

        VBox keyboardBox = new VBox(5);
        keyboardBox.setAlignment(Pos.CENTER);

        for (String[] row : rows) {
            
            HBox rowBox = new HBox(5);
            rowBox.setAlignment(Pos.CENTER);

            for (String label : row) {
                
                Button keyButton = new Button(label);
                keyButton.setPrefSize(getKeyWidth(label), 40);
                
                // attaching the matching keyCode to the real button itself so it can be found later when actually physicially pressed down
                 
                KeyCode code = labelToKeyCode(label);
                keyButton.setUserData(code);

                allKeyButtons[nextIndex] = keyButton;
                nextIndex++;

            }
            keyboardBox.getChildren().add(rowBox);
        }
        
        return keyboardBox;
    }
    
    //to return a wider width for the larger keys to match a irl keyboard and if not other keys normal (USED PROPORTIONS OF MY ALIENWARE LAPTOP FOR PROPORTIONS)
    
    private double getKeyWidth(String label) {
        
        if (label.equals("Space")) {
            
            return 300;
            
        } else if (label.equals("Backspace") || label.equals("Enter") || label.equals("Shift")) {
            
            return 80;
            
        } else {
            
            return 40;
        }
    }
    
    // to map a virtual keyboard button label to the physical actual keyCode that it represents by comparing directly against keyCode constants (https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html keep here for quick reference back if needed)
     
    private KeyCode labelToKeyCode(String label) {
        
        switch (label) {
            
            case "`": return KeyCode.BACK_QUOTE;
            case "1": return KeyCode.DIGIT1;
            case "2": return KeyCode.DIGIT2;
            case "3": return KeyCode.DIGIT3;
            case "4": return KeyCode.DIGIT4;
            case "5": return KeyCode.DIGIT5;
            case "6": return KeyCode.DIGIT6;
            case "7": return KeyCode.DIGIT7;
            case "8": return KeyCode.DIGIT8;
            case "9": return KeyCode.DIGIT9;
            case "0": return KeyCode.DIGIT0;
            case "-": return KeyCode.MINUS;
            case "=": return KeyCode.EQUALS;
            case "Backspace": return KeyCode.BACK_SPACE;
            case "Q": return KeyCode.Q;
            case "W": return KeyCode.W;
            case "E": return KeyCode.E;
            case "R": return KeyCode.R;
            case "T": return KeyCode.T;
            case "Y": return KeyCode.Y;
            case "U": return KeyCode.U;
            case "I": return KeyCode.I;
            case "O": return KeyCode.O;
            case "P": return KeyCode.P;
            case "[": return KeyCode.OPEN_BRACKET;
            case "]": return KeyCode.CLOSE_BRACKET;
            case "\\": return KeyCode.BACK_SLASH;
            case "A": return KeyCode.A;
            case "S": return KeyCode.S;
            case "D": return KeyCode.D;
            case "F": return KeyCode.F;
            case "G": return KeyCode.G;
            case "H": return KeyCode.H;
            case "J": return KeyCode.J;
            case "K": return KeyCode.K;
            case "L": return KeyCode.L;
            case ";": return KeyCode.SEMICOLON;
            case "'": return KeyCode.QUOTE;
            case "Enter": return KeyCode.ENTER;
            case "Shift": return KeyCode.SHIFT;
            case "Z": return KeyCode.Z;
            case "X": return KeyCode.X;
            case "C": return KeyCode.C;
            case "V": return KeyCode.V;
            case "B": return KeyCode.B;
            case "N": return KeyCode.N;
            case "M": return KeyCode.M;
            case ",": return KeyCode.COMMA;
            case ".": return KeyCode.PERIOD;
            case "/": return KeyCode.SLASH;
            case "Space": return KeyCode.SPACE;
            
            default: return null;
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
