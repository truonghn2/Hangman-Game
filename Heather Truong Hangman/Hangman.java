/*
 * Heather Truong
 * 
 * 8/27/24
 */

import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};


    /**
     * Function name: randomWord
     *
     * @return (string)
     * 
     * Inside the function:
     *  1. returns a random word from the array of random words
     */
    public static String randomWord() {
        int numWords = words.length;
        double randomDouble = Math.random();
        int randomIndex = (int)(randomDouble * numWords);
        return words[randomIndex];
    }

    /**
     * Function name: checkGuess
     * 
     * @param word (string)
     * @param guess (char)
     * @return (boolean)
     * 
     * Inside the function:
     *  1. returns true if the user guessed a letter from the word correctly
     */
    public static boolean checkGuess(String word, char guess) {

        //loop through each letter of the word. if any letter matches the guessed letter then return true
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function name: updatePlaceholders
     * 
     * @param word
     * @param placeholders
     * @param guess
     * 
     * Inside the function:
     *  1. updates the placeholders when the user makes a correct guess
     */
    public static void updatePlaceholders(String word, char[] placeholders, char guess) {
        for (int i = 0; i < word.length(); i++) {

            if (word.charAt(i) == guess) {
                placeholders[i] = guess;
            }
        }
    }

    /**
     * Function name: printPlaceHolders
     * 
     * @param placeholders (char[])
     * 
     * Inside the function:
     *  1. prints out the placeholders on the same line with a space in between each element
     */
    public static void printPlaceHolders(char[] placeholders) {
        for (int i = 0; i < placeholders.length; i++) {
            System.out.print(" " + placeholders[i]);
        }
        System.out.print("\n");
    }

    /**
     * Function name: printMissedGuesses
     * 
     * @param missedGuesses
     * 
     * Inside the function:
     *  1. prints guesses that the user missed
     */
    public static void printMissedGuesses(char[] missedGuesses) {
        for (int i = 0; i < missedGuesses.length; i++) {
            System.out.print(missedGuesses[i]);
        }
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //select a random word from the words array
        String word = randomWord();

        //create a char array to hold the placeholders. the amount of placeholders will equal the chosen random word length
        char[] placeholders = new char[word.length()];

        //loop through the placeholders array to insert a '_' char in each index
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = '_';
        }

        //when a player starts they will have no missed guesses
        int missed = 0;

        //the max amount of missed guesses is 6. keep track of the missed guesses
        char[] missedGuesses = new char[6];

        //while missed guesses is less than 6 the user can keep playing
        while (missed < 6) {
            System.out.print(gallows[missed]);

            System.out.print("Word:   ");
            printPlaceHolders(placeholders);
            System.out.print("\n");

            System.out.print("Missed:   ");
            printMissedGuesses(missedGuesses);
            System.out.print("\n\n");

            System.out.print("Guess:    ");
            char guess = scan.nextLine().charAt(0);
            System.out.print("\n");


            //if user's checkGuess returns true then update the placeholders
            if (checkGuess(word, guess)) {
                updatePlaceholders(word, placeholders, guess);
            }
            //otherwise add the incorrect guess to the missedGuesses array
            else {
                missedGuesses[missed] = guess;
                missed++;
            }

            //if the placeholders are the same as the random word then the user won
            if (Arrays.equals(placeholders, word.toCharArray())) {
                System.out.print(gallows[missed]);
                System.out.print("\nWord:   ");
                printPlaceHolders(placeholders);
                System.out.println("\nYOU WON!");                
                break;
            }

        }

        //if the user reaches 6 missed guesses
        if (missed == 6) {
            System.out.print(gallows[6]);
            System.out.println("\nRIP YOU LOST!");
            System.out.println("\nThe word was: '" + word + "'");
        }
        scan.close();
    }

}





