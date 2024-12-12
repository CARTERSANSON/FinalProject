/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalproject2;
import java.util.*;

public class FinalPProject2 {

    public static void main(String[] args) {
        //set up scanner
        Scanner scanner = new Scanner(System.in);
        //create wordbank using ArrayList to hold possible words
        ArrayList<String> wordBank = new ArrayList<>();
        wordBank.add("programming");
        wordBank.add("java");
        wordBank.add("hangman");
        wordBank.add("developer");
        wordBank.add("puzzle");
        //display a welcome message and instructions
        System.out.println("Welcome to Hangman!");
        System.out.println("Guess the word, one letter at a time.");
        System.out.println("You have 6 attempts. Good luck!");
        //start the game by passing the word bank to the playHangman method
        playHangman(wordBank);
    }

    // Main game logic
    public static void playHangman(ArrayList<String> wordBank) {
        //select a random word from the word bank
        Random random = new Random();
        String wordToGuess = wordBank.get(random.nextInt(wordBank.size())).toUpperCase();
        //create a caharacter array to represent the partially guessed word
        //Initially contains underscores for eadh letter in the word
        char[] guessedWord = new char[wordToGuess.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }
        
        //Create an ArrayList to store guessed letters
        ArrayList<Character> guessedLetters = new ArrayList<>();
        //set the number of remaining attempts
        int remainingAttempts = 6;
        //intialize a scanner for player input
        Scanner scanner = new Scanner(System.in);
        //game loop; Continue until the word is guessed or attempts to run out
        while (remainingAttempts > 0 && !isWordGuessed(guessedWord)) {
            //display the current game state
            System.out.println("\nCurrent word: " + String.valueOf(guessedWord));
            System.out.println("Guessed letters: " + guessedLetters);
            System.out.println("Remaining attempts: " + remainingAttempts);
            //prompt the use to enter a guess
            System.out.print("\nEnter your guess (a single letter): ");
            String input = scanner.nextLine();
            //validate the player's input
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                //if the input is not a single letter, display an error message and retry
                System.out.println("Invalid input. Please enter a single letter.");
                continue; //skip the rest of the loop iteration
            }
            //convert the input to uppercase to ensure consistency
            char guess = Character.toUpperCase(input.charAt(0));
            //check if the letter has already been guessed
            if (guessedLetters.contains(guess)) {
                System.out.println("You already guessed that letter!");
                continue; //skip the rest of the iteration
            }
            //add the guessed letter to the list of guessed letters
            guessedLetters.add(guess);
            // check if the guessed is in the word
            if (wordToGuess.indexOf(guess) >= 0) {
                //if correct, update the guessed word
                System.out.println("Good guess!");
                updateGuessedWord(wordToGuess, guessedWord, guess);
            } else {
                //if incorrect. decrement the remaining attempts
                System.out.println("Wrong guess!");
                remainingAttempts--;
            }
        }
        // game over logic: chekc if the play won or lost
        if (isWordGuessed(guessedWord)) {
            //player won
            System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
        } else {
            //player lost
            System.out.println("\nGame over! The word was: " + wordToGuess);
        }
    }
    /**
     * Updates the guessed word with the correct guess
     * @param wordToGuess - The word the player is trying to guess
     * @param guessedWord - The current state of the guessed word
     * @param guess - The letter guessed by the player
     */

    // Update guessed word based on correct guess
    public static void updateGuessedWord(String wordToGuess, char[] guessedWord, char guess) {
        //Loop through each character in the word
        for (int i = 0; i < wordToGuess.length(); i++) {
            //if the character mathces the guessed letter, update the guessed word
            if (wordToGuess.charAt(i) == guess) {
                guessedWord[i] = guess;
            }
        }
    }
    /**
     * Checks if the word has been completely guessed
     * @param guessedWord - The current state of the guessed word.
     * @return True if all underscores have been replaced with letter, false otherwise
     */

    // Check if the word is fully guessed
    public static boolean isWordGuessed(char[] guessedWord) {
        //loop through each character in the guessed word
        for (char c : guessedWord) {
            //if there are still underscores the word is not fullyt guessed
            if (c == '_') {
                return false;
            }
        }
        //if no underscores remain, the word is fully guessed
        return true;
    }
}

