package com.company.controllers;

import com.company.models.WordList;
import com.company.views.CmdLineView;

public class Main {

    public static String[] letters;
    public static int numLetters = 0;
    public static int numGuesses;
    public static String theGuess;
    public static String[] hints;
    public static CmdLineView view;
    public static boolean correctGuess;
    public static int correctLetters;
    public static boolean winGame = false;

    public static void main(String[] args) {

        numGuesses = 6;
        GetWord getWord = new GetWord();
        String theWord = getWord.getTheWord();

        WordList word = new WordList(theWord);

        //WordList word = new getWord.getTheWord();

        letters = calculateLetters(word.getTheWord());

        view = new CmdLineView(letters);
        view.startGame();
        //view.cheat(word.getTheWord());
        hints = withGuesses();

        while(numGuesses > 0) {
            hints = guess();
            view.displayHints(hints);
            //display
            //System.out.println(hints);
            correctLetters = 0;
            if(correctGuess == false) {
                numGuesses--;
                System.out.print("Wrong guess..\n");
            }
            for(int i = 0; i < letters.length; i++) {
                if(hints[i] != "_") {
                    correctLetters++;
                }
            }
            if(correctLetters == letters.length) {
                numGuesses = 0;
                winGame = true;
            }
        }

        if(winGame == true){
            System.out.println("Congratulations! You win!");
        }
        else {
            System.out.println("Too bad... Better luck next time!");
        }

    }

    private static String[] calculateLetters(String theWord) {
        String[] letters = theWord.split("");
        numLetters = letters.length;
        return letters;
    }

    public static String[] withGuesses() {
        String[] hints = new String[letters.length];
        for(int i = 0; i < letters.length; i++) {
            hints[i] = "_";
        }
        return hints;
    }

    private static String[] guess() {

        theGuess = view.makeAGuess();
        correctGuess = false;

        String msg = "";

        for(int i = 0; i < letters.length; i++) {
            if(letters[i].equals(theGuess)) {
                hints[i] = theGuess;
                correctGuess = true;
            }
        }
        return hints;
    }
}
