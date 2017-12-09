package com.Benjamin;





// Many of the card pictures from https://depositphotos.com/18063029/stock-illustration-poker-cards-full-deck.html
// Joker little from http://www.fanpop.com/clubs/the-joker/images/12392220/title/cute-joker-card-photo
// Joker Big from https://batmib.deviantart.com/art/JOKER-card-422785101


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
        ArrayList<String> playerHandArray = playerHand.getHand(checkDeck); // Each player getting a random hand. With the last 13 cards in the deck for the last opponent
        playerHandArray = playerHandSorted.getPlayersHandSorted(playerHandArray); // Summons the sort by suit for easyer card reading for the user

        // TODo sort the players hand  by numbers for easier viewing.
        ArrayList<String> opponentLeftArray = opponentLeft.getHand(checkDeck);
        ArrayList<String> teamMatesHandArray = teamMatesHand.getHand(checkDeck);
        //ArrayList<String> opponentRightArray = opponentRight.getHand(checkDeck);  // Not sure this is needed because I can use the last 13 cards in the deck for the last person

        int PlayerTeamBooks = 0;
        int ComputerTeamBooks = 0;

        //CardPicked cardPickled = new CardPicked();

        // Below for database connection and making a table if one does not exist.
        Connection connection = null;  //
        try {
            connection = ConnectionConfiguration.getConnection(); // Getting a connection to the database
            if (connection != null) { // if no connection don't even try anymore.

                try {
                    Statement statement = connection.createStatement(); // Connection statement for easier to work with connecting

                    // Making the table to store game data.  It shoudl have a name all four player hands, the currrent books and  score.
                    String createTableSpadesSaved = "CREATE TABLE IF NOT EXISTS spades (Name varchar(12), PlayerHand varchar(49), OpponentLeft varchar(49), TeammateAccoss varchar(49), OpponentRight varchar(49), BooksUser int, BooksOpponent int, ScoreUser int, ScoreOpponent int )";
                    statement.executeUpdate(createTableSpadesSaved); // Updating it so the table will come into existance.

                } catch (SQLException sqlExcept) {
                    // Seems table already exists, If this exception thrown
                }
                connection.close();  // closing the connection
            }
        } catch (Exception e) { // I case something goes wrong don't want the program to crash
            e.printStackTrace();
        }





        GameLayout gui = new GameLayout(playerHandArray, opponentLeftArray, teamMatesHandArray, checkDeck);

    }


}
