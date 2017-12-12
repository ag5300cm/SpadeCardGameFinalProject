package com.Benjamin;

import java.util.ArrayList;

// This class is to get the guess of of your oppoents and teammate for how many books they can take in with their hand
public class getBooks {

     static int booksGuessOfCanWin = 0; // Master int for useage everywhere if need be. Or to send back

    // To get and return the data for how many books in their hand they can make
    public static int getBooksGuessOfCanWin(ArrayList<String> scanMeArraylist) {

         booksGuessOfCanWin = setBooksGuessOfCanWin(scanMeArraylist);

        return booksGuessOfCanWin;
    }

    public static int setBooksGuessOfCanWin(ArrayList<String> scanMeArrayList) {
        int howManyWinners = 0; // Int form
        double howManyWinnersDouble = 0; // double form for different estimate on usages

        for (int i = 0; i < 13; i++) {
            String worthSomething = scanMeArrayList.get(i);

            if (worthSomething.substring(0,1).matches("L | B | K | Q" ) ) { // Looks for the joker and kings and queens. The ones most likely to win.
                howManyWinnersDouble += 1;
            }
            if (worthSomething.substring(worthSomething.length()-1).equalsIgnoreCase("S")) { // Looks at how many spades you have and give half point for each
                howManyWinnersDouble += 0.5;
            }
        }

        howManyWinners = (int) howManyWinnersDouble; // Transforming everying into integers
        return howManyWinners;
    }


}
