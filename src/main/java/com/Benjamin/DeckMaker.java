package com.Benjamin;


import java.util.ArrayList;
// Took this from my cardGameSpades assignment

class DeckMaker {

    //String[] completeDeck = new String[52];
    ArrayList<String> completeDeck = new ArrayList<>(52);  // This arrayList will become a complete deck of cards meant to return to main()

//    String[] cardValues = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // All the different types of cards but jokers.
//    String[] cardTypes = new  String[] {"C", "D", "H", "S" } ;  // Club, Diamonds, Hearts ands spades for each suit

    public ArrayList<String> getCompleteDeck() {  // What is used to call for a new deck
        setCompleteDeck();  // Making the deck

        return completeDeck; // Sending the dect back to main()
    }

    void setCompleteDeck() {
        boolean jokersAdd = ArrayListOfSaveNames.getJokersInPlay();  // finds out if the user wants to play with jokers.
        // Below is both card numbers or ace or royality and card type
        String[] cardValues = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" }; // All the different types of cards but jokers.
        String[] cardTypes = new  String[] {"C", "D", "H", "S" } ;  // Club, Diamonds, Hearts ands spades for each suit

        for (int i = 0; i < 4; i++) { // The first for loop is for each cardType (clubs, hearts, etc)
            String cardT = cardTypes[i]; // Identifing the card type to
            for (int j = 0; j < cardValues.length; j++) {
                completeDeck.add(cardValues[j] + cardT);
            }
        }

        if (jokersAdd == true) { // If they want to play with joker will remove two cards and add two jokers.
            for (int i = 0; i < 52; i++) { // Will search the whole deck for two cards to be removed.
                if (completeDeck.get(i).equalsIgnoreCase("2H") ) { // looking for the two of hearts
                    completeDeck.remove(i); // gets rid of the two of hearts
                    completeDeck.add(i, "LS");  // adds the little joker
                }
                if (completeDeck.get(i).equalsIgnoreCase("2D") ) { // looking for two of diamonds
                    completeDeck.remove(i); // tossing out two of diamonds
                    completeDeck.add(i, "BS"); // Adding  big joker.
                }

            }
        }
    }
}

//  Not need will just use the built in feature from Collections.shuffle()
//class shuffle {
//    ArrayList<String> ShuffleMeDeck = new ArrayList<>();
//
//    public ArrayList<String> getShuffleMeDeck() {
//        setShuffleMeDeck(ShuffleMeDeck);
//        return ShuffleMeDeck;
//    }
//
//    void setShuffleMeDeck(ArrayList Deck) {
//        ArrayList<String> TempDeck = Deck;
//        String temp;
//        String tempSwitcher;
//        Random rand = new Random(52);
//
//        for (int i = 0; i < TempDeck.size(); i++ ) {
//            temp = TempDeck.get(i).toString();
//            int tempNumber = rand.nextInt();
//            tempSwitcher = TempDeck.get(tempNumber).toString();
//            TempDeck.remove(tempNumber);
//            TempDeck.add(tempNumber, temp);
//            TempDeck.remove(i);
//            TempDeck.add(i, tempSwitcher);
//        }
//
//    }
//    //Collections.shuffle(ShuffleMeDeck);
//}
