package com.Benjamin;





// Many of the card pictures from https://depositphotos.com/18063029/stock-illustration-poker-cards-full-deck.html
// Joker little from http://www.fanpop.com/clubs/the-joker/images/12392220/title/cute-joker-card-photo
// Joker Big from https://batmib.deviantart.com/art/JOKER-card-422785101


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

// TODO darn missing save data of the guess of how many books each team can get.

public class Main {

    public static void main(String[] args) {
        // write your code here

        // Below for database connection and making a table if one does not exist.
        Connection connection = null;  //
        try {
            connection = ConnectionConfiguration.getConnection(); // Getting a connection to the database
            if (connection != null) { // if no connection don't even try anymore.

                try {
                    Statement statement = connection.createStatement(); // Connection statement for easier to work with connecting

                    // Making the table to store game data.  It shoudl have a name all four player hands, the currrent books and  score.
                    String createTableSpadesSaved = "CREATE TABLE IF NOT EXISTS spades (Name varchar(12), PlayerHand varchar(49), OpponentLeft varchar(49), TeammateAccoss varchar(49), OpponentRight varchar(49), BooksUser int, BooksOpponent int, ScoreUser int, ScoreOpponent int )";
                    statement.executeUpdate(createTableSpadesSaved); // Updating it so the table is made if one does not exist.

                } catch (SQLException sqlExcept) {
                    //Statement statement = connection.createStatement(); // Connection statement for easier to work with connecting
                    // Seems table already exists, If this exception thrown
                    //String useTableSpadesSaved = "USE spades";
                    //statement.executeUpdate(useTableSpadesSaved);
                }
                connection.close();  // closing the connection
            }
        } catch (Exception e) { // I case something goes wrong don't want the program to crash
            e.printStackTrace();
        }


        GameLayout gui = new GameLayout(); // starts up game right here

    }
}




//        int biggerNumberForWin = 0;
//        do {
//
//            GameLayout gui = new GameLayout();
//
//
//            if (playerTeamScore > oppoentsTeamScore  ) {
//                biggerNumberForWin = playerTeamScore;
//            } else {
//                biggerNumberForWin = oppoentsTeamScore;
//            }
//
//        } while (biggerNumberForWin  < 500); // 500 is the averages score in spadse.

