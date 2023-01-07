

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

public class Model {

//    INSTANCE VARIABLES
    private String startrekCharacters[];
    private String characterImages[];
    private String chosenString;
    private int numberOfTries;
    private boolean flagGameStatus;
    private int randomIndex;
    private int score;


//    CONSTRUCTOR METHOD FOR MODEL
    public Model(String[] startrekCharacters, String[] characterImages) {

        this.startrekCharacters = startrekCharacters;
        this.characterImages=characterImages;

        this.chosenString="";
        this.numberOfTries=0;
        this.flagGameStatus=false;
        this.randomIndex=0;
        this.score=0;


    }

    //GETTER METHODS

    // GET THE NUMBER OF TRIES VALUE
    public int getNumberOfTries(){
        return this.numberOfTries;
    }
    // GET THE FLAG VALUE
    public boolean getFlagValue(){
        return this.flagGameStatus;
    }
    // GET THE SCORE
    public int getScore(){
        return this.score;
    }

    // SETTER METHODS

    //SET THE FLAG VALUE
    public void setFlagValue(boolean value){
        this.flagGameStatus=value;
    }

    public void setNumberOfTries(int value){
        this.numberOfTries=value;
    }


//    METHOD TO CHOOSE A RANDOM CHARACTER FROM THE ARRAY
    public String chooseRandomCharacter(){

        // GENERATE A RANDOM INT VALUE TO CHOOSE FROM THE INDEX
        this.randomIndex= (int) (Math.floor(Math.random()*this.startrekCharacters.length)); //get index

        String chosenCharacter= startrekCharacters[randomIndex]; //choose string

        this.chosenString=chosenCharacter;

        return chosenCharacter; //return string
    }

    public String chosenCharacterImage(){
        return characterImages[randomIndex];
    }


    //    METHOD TO VALIDATE THE GUESS
    public String validateTheGuess(String userGuess){

        // INCREASE THE NUMBER OF TRIES BY 1
        this.numberOfTries ++;

        if(userGuess.equals(this.chosenString)){
            this.flagGameStatus=true;
            this.score++;
            return "Success"+", you guessed the right answer in: "+numberOfTries;

        }
        else{

            return "Sorry Wrong Answer, Please Try Again!."+" No. of Tries so far: "+numberOfTries+" guesses";

        }
    }



}

