package com.Benjamin;





// Many of the card pictures from https://depositphotos.com/18063029/stock-illustration-poker-cards-full-deck.html
// Joker little from http://www.fanpop.com/clubs/the-joker/images/12392220/title/cute-joker-card-photo
// Joker Big from https://batmib.deviantart.com/art/JOKER-card-422785101


import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static DeckMaker deck = new DeckMaker(); // Makes a new shuffeled deck for the game round.

    // my TODO this spot might need to be changed to do more then one round.
    static DealHands playerHand = new DealHands();
    static DealHands opponentLeft = new DealHands();
    static DealHands teamMatesHand = new DealHands();
    static DealHands opponentRight = new DealHands();

    static SortCards playerHandSorted = new SortCards();


    int playerTeamScore = 0;
    int oppoentsTeamScore = 0;


    public static void main(String[] args) {
	// write your code here

        ArrayList<String> checkDeck = deck.getCompleteDeck(); // Getting the deck I plan to work with
        Collections.shuffle(checkDeck);  // This is a quick built in feature to shuffle the deck.
        //playerHand.getHand(checkDeck);
        ArrayList<String> playerHandArray =  playerHand.getHand(checkDeck); // Each player getting a random hand. With the last 13 cards in the deck for the last opponent
        playerHandArray = playerHandSorted.getPlayersHandSorted(playerHandArray); // Summons the sort by suit for easyer card reading for the user

        // TODo sort the players hand for easier viewing.
        ArrayList<String> opponentLeftArray = opponentLeft.getHand(checkDeck);
        ArrayList<String> teamMatesHandArray = teamMatesHand.getHand(checkDeck);
        //ArrayList<String> opponentRightArray = opponentRight.getHand(checkDeck);  // Not sure this is needed because I can use the last 13 cards in the deck for the last person

        int PlayerTeamBooks = 0;
        int ComputerTeamBooks = 0;

        GameLayout gui = new GameLayout(playerHandArray);





    }
}
