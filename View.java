

// IMPORT CLASSES
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.*;

/**
 * Date of Creation: March 10, 2022.
 * In this game, one character out of the given StarTrek characters is chosen at random.
 * To score (i.e. increase your score by 1), you must correctly guess which character was chosen at random.
 * You will get unlimited number of tries and your number of tries will be counted
 * You can quit the game anytime you want
 * upon making the right guess, the button to guess again is enabled. You can choose to play the game again and add on to your score.
 * The Arrays used for characters here is only 5 characters long, although we can use an API to get a large number of startrek characters in the array.
 *
 *
 * **/

/**
 * Guess the Character Game
 *
 * @author Nishkarsh Dubb
 */
public class View extends Application {

    //ARRAY OF CHARACTERS (STRINGS), AND COMPONENTS THAT INTERACT WITH MODEL CLASS
    String startrekCharacters[]={"Admiral James T. Kirk", "Spock", "Nyota Uhura", "Leonard McCoy", "Scotty", "Hikaru Sulu"};
    String characterImages[]={"https://upload.wikimedia.org/wikipedia/commons/a/a5/Star_Trek_William_Shatner.JPG", "https://cdn.vox-cdn.com/thumbor/tX8SfXtkzsnKFyRRq2PvWOJigOw=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/12004249/Spock__2265.jpg", "https://static.wikia.nocookie.net/memoryalpha/images/4/49/Nyota_Uhura%2C_2266_%28operations%29.jpg/revision/latest/scale-to-width-down/700?cb=20110417164842&path-prefix=en", "https://upload.wikimedia.org/wikipedia/commons/9/90/DeForest_Kelley%2C_Dr._McCoy%2C_Star_Trek.jpg", "https://www.gtplanet.net/forum/media/scotty.11710/full", "https://upload.wikimedia.org/wikipedia/commons/f/f8/George_Takei_Sulu_Star_Trek.JPG"};

    // 1. CREATE AN OBJECT OF THE MODEL CLASS
    Model game= new Model(startrekCharacters, characterImages);

    // INSTANCE VARIABLES FOR VIEW COMPONENTS AND MODEL ( I HAVE USED 4 VIEW COMPONENTS IN TOTAL)
    ComboBox characterList; //COMBOBOX
    Label output; //LABEL
    Button submitButton, playAgain, closeButton; //BUTTON
    Image characterToGuess; //IMAGE
    ImageView imageView; //IMAGE VIEW


    //  PRIVATE EVENT HANDLER

    //METHOD THAT FIRES OFF WHEN THE SUBMIT BUTTON IS CLICKED
    private void submitHandler(ActionEvent e) {
        String msg = "";

        //SET MESSAGE ON THE LABEL OUTPUT
        msg += game.validateTheGuess(characterList.getValue().toString()) +"\n \t\t\t\t\t\tTotal Score: "+game.getScore();
        output.setText(msg);

        // IF THE GUESS IS RIGHT
        if(game.getFlagValue()){

            //DISABLE SUBMIT BUTTON, THE COMBOBOX
            submitButton.setDisable(true);
            characterList.setDisable(true);

            //ENABLE THE PLAYAGAIN BUTTON
            playAgain.setDisable(false);

            //SET THE FLAG VALUE TO FALSE, i.e. guess is not right
            game.setFlagValue(false);
        }



    }

    // METHOD THAT FIRES OFF WHEN CLOSE BUTTON IS CLICKED
    private void closeButtonHandler(ActionEvent e){

        // I WAS A BIT CONFUSED ON HOW TO CLOSE THE WINDOW, I USED this post to understand how to close the window on a button click
        //https://stackoverflow.com/questions/25037724/how-to-close-a-java-window-with-a-button-click-javafx-project

            //TRAVERSE AND VET THE SCENE AND WINDOW
            Stage stage = (Stage) submitButton.getScene().getWindow();
            //CLOSE THE WINDOW
            stage.close();

    }

    // METHOD THAT FIRES OFF WHEN PLAYAGAIN BUTTON IS CLICKED
    private void playAgainHandler(ActionEvent e){

        //ENABLE THE SUBMIT BUTTON AND THE COMBO BOX
        submitButton.setDisable(false);
        characterList.setDisable(false);

        // DISABLE THE PLAY AGAIN BUTTON
        playAgain.setDisable(true);

        //CHOOSE A RANDOM CHARACTER AGAIN
        game.chooseRandomCharacter();

        characterToGuess= new Image(game.chosenCharacterImage());//Create Image Object

        imageView.setImage(characterToGuess); //Setting image to the image view

        //SET THE NO OF TRIES TO ZERO
        game.setNumberOfTries(0);

        //SET THE OUTPUT TEXT TO INITIAL TEXT
        output.setText("What StarTrek Character am I?");
    }


    // PUBLIC METHOD TO STYLE BUTTONS, // IT TAKES BUTTON AS A PARAMETER, the XCOORD AND YCOORD OF THE BUTTON
    public void setButtonStyle(Button button, int xLocation, int yLocation){
        button.relocate(xLocation,yLocation);
        button.setAlignment(Pos.CENTER);
        button.setStyle("-fx-background-color: #fff; -fx-background-radius: 3,3,3,3; -fx-font-weight: bold; -fx-font: 16px \"Comic Sans MS\";");

    }





    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, 900, 600); // set the size here
        stage.setTitle("Guess The Star Trek Character"); // set the window title here
        stage.setScene(scene);
        stage.setResizable(false); //don't allow user to resize the window

        // Choose a Random Character from the array of characters
        game.chooseRandomCharacter();


        //GUI-building code

        // Set Background
        Image backgroundImg=new Image("https://media.wired.com/photos/5b7f64cbbe2f8d3a624b77b2/16:9/w_2000,h_1125,c_limit/SPoW_82318_01.jpg");
        BackgroundImage background= new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(background);
        root.setBackground(bg);


        //The GUI components

        //1. Image and ImageView

//        I have used this resource to further understand how to add images in javafx
//        https://www.tutorialspoint.com/how-to-display-an-image-in-javafx

        characterToGuess= new Image(game.chosenCharacterImage());//Create Image Object
        imageView = new ImageView(); //create Image View
        imageView.setImage(characterToGuess); //Setting image to the image view
        //Setting the image view parameters
        imageView.setX(50);
        imageView.setY(200);
        imageView.setFitWidth(450);
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(true);

        //2. ComboBox()
        characterList = new ComboBox();
        characterList.getItems().addAll(
                "Admiral James T. Kirk",
                "Spock",
                "Nyota Uhura",
                "Leonard McCoy",
                "Scotty",
                "Hikaru Sulu"
        );
        // SELECT FIRST ELEMENT BY DEFAULT:->
        characterList.getSelectionModel().selectFirst();

        //2. Buttons
        submitButton = new Button("Submit Answer");
        closeButton = new Button("Quit Game");
        playAgain = new Button("Play Again");

        //3. Label
        output = new Label("What StarTrek Character am I?");


        // ADD COMPONENTS TO THE ROOT
        root.getChildren().addAll( characterList, submitButton, playAgain, closeButton, output, imageView);

        // CONFIGURE THE COMPONENTS (colors, fonts, size, location)
        characterList.relocate(500,300);
        characterList.setStyle("-fx-background-color:#fff; -fx-background-radius: 3,3,3,3; -fx-font-weight: bold; -fx-font: 16px \"Comic Sans MS\";");

        setButtonStyle(submitButton, 530, 350);
        setButtonStyle(closeButton, 780, 550);
        setButtonStyle(playAgain, 650, 550);
        playAgain.setDisable(true);

        output.setAlignment(Pos.CENTER);
        output.setPrefWidth(900);
        output.setPrefHeight(150);
        output.setFont(new Font("Comic Sans MS", 25));
        output.setStyle("-fx-background-color: red;-fx-text-fill:yellow;");


        // ADD LISTENERS, click of submit,close and playAgain button
        submitButton.setOnAction(this::submitHandler);
        closeButton.setOnAction(this::closeButtonHandler);
        playAgain.setOnAction(this::playAgainHandler);

        // SHOW THE STAGE
        stage.show();

    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
