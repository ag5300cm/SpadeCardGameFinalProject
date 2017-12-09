package com.Benjamin;

import java.util.ArrayList;
import java.util.Collections;

public class SortCards {

    ArrayList<String> playersHandSorted = new ArrayList<>(); // Master arraylist in the Sortcards Classs meant to be sent back sorted.

    public ArrayList<String> getPlayersHandSorted(ArrayList<String> playerHandArray) { // Used to talk with the main class.

        setPlayersHandSorted( playerHandArray); // Using the setter below to sort the cards and add to playersHandSorted Arraylist.

        return playersHandSorted; // GO, Go, back home. Or to Main.
    }

    void setPlayersHandSorted(ArrayList<String> playerHandArray) { // the setter takes in the playHandArray to sort

        ArrayList<String> clubSort = new ArrayList<>();  // Arraylist to sort by suit.
        ArrayList<String> diamondSort = new ArrayList<>();
        ArrayList<String> heartSort = new ArrayList<>();
        ArrayList<String> spadeSort = new ArrayList<>();
        ArrayList<String> defaultSort = new ArrayList<>();

        for (int i = 0; i < 13; i++) { // Going through each card in the hand
            String sortME =  playerHandArray.get(i); // The card to be sorted.

            if ((sortME.substring(sortME.length()-1).equalsIgnoreCase("C")) ) {  // Is it clubs? then add to clubSort Arraylist
                clubSort.add(sortME);
            } else if ((sortME.substring(sortME.length()-1).equalsIgnoreCase("D")) ) {
                diamondSort.add(sortME);
            } else if ((sortME.substring(sortME.length()-1).equalsIgnoreCase("H")) ) {
                heartSort.add(sortME);
            } else if ((sortME.substring(sortME.length()-1).equalsIgnoreCase("S")) ) {
                spadeSort.add(sortME);
            } else {
                defaultSort.add(sortME); // Current default so any extras will be add near the end of the list
            }
        }

        //sortByValue(heartSort, 1, 15);
        playersHandSorted.addAll(heartSort);  // simple option or other below.
//        for (String str : heartSort) {
//            playersHandSorted.add(str);
//        }

        //clubSort = sortByValue(clubSort);
        for (String str : clubSort) {  // Same as addAll above, good to show I can do it both ways.
            playersHandSorted.add(str);
        }

        //diamondSort = sortByValue(diamondSort);
        for (String str : diamondSort) {
            playersHandSorted.add(str);
        }

        //spadeSort = sortByValue(spadeSort);
        for (String str : spadeSort) {
            playersHandSorted.add(str);
        }

        playersHandSorted.addAll(defaultSort); // catch all for anything not of the four suits.

//        handSort.add(heartSort);  handSort.add(clubSort);  handSort.add(diamondSort);  handSort.add(spadeSort);
//
//        for (int i = 0; i < handSort.size(); i++) {
//            ArrayList<String> addMeTOplayerHandSorted = new ArrayList<>();
//            addMeTOplayerHandSorted.addAll(        heartSort.get(i));
//        }
    }

//    void sortByValue(ArrayList<String> cardSuitArrayLIst, int from, int to) {
//
//        if (from != to) {
//            if (from > to) {
//                sortByValue(cardSuitArrayLIst, from -1, to);
//            } else {
//                sortByValue(cardSuitArrayLIst, from+1, to);
//            }
//            Collections.swap(cardSuitArrayLIst, from, to);
//        }
//
//    }

//    ArrayList sortByValue(ArrayList cardSuitArrayLIst) {
//        ArrayList<String> sortedValueArraylist = cardSuitArrayLIst;
//
//        int counter = 0;
//
//        do {
//            String compareMe = cardSuitArrayLIst.get(counter).toString();
//            if (sortedValueArraylist.get(counter+1).isEmpty()) {
//                return sortedValueArraylist;
//            }
//            String nextCard = cardSuitArrayLIst.get(counter + 1).toString();
//
//            int compareMeInt = getNumberValue(compareMe);
//            int nextCardInt = getNumberValue(nextCard);
//
//            if (compareMeInt > nextCardInt) {
//
//            }
//        } while ()
//
//
//        for (int i = 0; i < cardSuitArrayLIst.size(); i++) {
//            String compareMe = cardSuitArrayLIst.get(i).toString();
//
//            try {
//                if (sortedValueArraylist.get(i+1).isEmpty()) {
//                    return sortedValueArraylist;
//                }
//
//                String nextCard = cardSuitArrayLIst.get(i + 1).toString();
//
//                int compareMeInt = getNumberValue(compareMe);
//                int nextCardInt = getNumberValue(nextCard);
//
//                if (compareMeInt > nextCardInt) {
//                    sortedValueArraylist.remove(compareMe);
//                    sortedValueArraylist.add(i+1, compareMe);
//
//
//
//                    //     Collections.swap(sortedValueArraylist, sortedValueArraylist.get(i), sortedValueArraylist.get(i+1));
//
////                    String toMove = sortedValueArraylist.get(i);
////                    sortedValueArraylist.set(i, sortedValueArraylist.get(i-1));
////                    sortedValueArraylist.set(i-1, toMove);
//
////                    sortedValueArraylist.remove(compareMe);
////                    sortedValueArraylist.add(i+1, compareMe);
//                }
//            } catch (IndexOutOfBoundsException iOOBE) {
//                return sortedValueArraylist;
//            }
//
//        }
//
//        return sortedValueArraylist;
//    }

    int getNumberValue(String card) {

        card = card.substring(0, 1).toUpperCase(); // Gets the first charater of it to figure out its numaric value.
        int cardValue ;
        switch (card) { // Testing the card to find its value
            case "A" :  // Ace
                return 1; // worth only one point, pretty low on the spectrum
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "1":  // Ten would only need 1 because there are no other ones.
                return 10;
            case "J": // Jack
                return 11;
            case "Q": // Queen
                return 12 ;
            case "K": // King
                return 13; // Most points you can get for card value
            default:
                return 0;
        }

    }
}
