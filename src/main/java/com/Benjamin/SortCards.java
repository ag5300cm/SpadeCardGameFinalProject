package com.Benjamin;

import java.util.ArrayList;

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

        playersHandSorted.addAll(heartSort);  // simple option or other below.
//        for (String str : heartSort) {
//            playersHandSorted.add(str);
//        }

        for (String str : clubSort) {  // Same as addAll above, good to show I can do it both ways.
            playersHandSorted.add(str);
        }

        for (String str : diamondSort) {
            playersHandSorted.add(str);
        }

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
}
