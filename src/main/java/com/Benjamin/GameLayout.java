package com.Benjamin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//import static com.Benjamin.cardObjectToPicture.setcardObjectToPicture;

//                          |/    \/ NEEDED!!!
public class GameLayout extends JFrame {

    private JButton cardButton1Image;
    private JLabel happyBirdLabelPic;
    private JPanel mainPanel;
    private JLabel TucanBirdLabelPic;
    private JLabel cuteKittyLabelPic;
    private JLabel putLeftOfUserCardsHere;
    private JButton cardButton7Image;
    private JButton cardButton6Image;
    private JButton cardButton5Image;
    private JButton cardButton4Image;
    private JButton cardButton3Image;
    private JButton cardButton2Image;
    private JButton cardButton9Image;
    private JButton cardButton10Image;
    private JButton cardButton8Image;
    private JButton cardButton11Image;
    private JButton cardButton12Image;
    private JButton cardButton13Image;
    private JButton exitButton;
    private JButton optionsButton;
    private JLabel putRigthOfUserCardsHere;
    private JLabel putAcrossOfUserCardsHere;
    private JLabel putYourThrownCard;
    private JLabel BooksPerTeam;
    private JLabel oppoentleftTextLabel; //
    private JLabel oppoentRightTextLabel;
    private JLabel accossOfUserTextLabel;
    private JButton loadButton;
    private JLabel testingLabel;  // I use these when need for testing and trouble-shooting.
    private JLabel testingLabel2;
    private JLabel testingLabel3;
    private JButton saveButton;
    private JButton loadLastSaveButton;

    LinkedList<JButton> cardButtonList = new LinkedList<>(); // Making a list of buttons.

    HashMap<JButton, Object> buttonsToCardValueHashMap = new HashMap<>();

    String displayer = new String();
    static String cardPicked = ""; // To be filled in by whatever card you picked.

    int PlayerTeamBooks = 0; // Keeping track for each book collected for the team
    int ComputerTeamBooks = 0;


    GameLayout(ArrayList playerHandArray, ArrayList opponentLeftArray, ArrayList teamMatesHandArray, ArrayList checkDeck) {

        cardButtonList.add(cardButton1Image);  cardButtonList.add(cardButton2Image);  cardButtonList.add(cardButton3Image);  cardButtonList.add(cardButton4Image); // Adding buttons to the list.
        cardButtonList.add(cardButton5Image);  cardButtonList.add(cardButton6Image);  cardButtonList.add(cardButton7Image);  cardButtonList.add(cardButton8Image);
        cardButtonList.add(cardButton9Image);  cardButtonList.add(cardButton10Image);  cardButtonList.add(cardButton11Image);  cardButtonList.add(cardButton12Image);
        cardButtonList.add(cardButton13Image);

        setContentPane(mainPanel);
        setSize(1600, 1000); // For size of the program, doesn't work weel with pack()

        ImageIcon happyBirdIcon = new ImageIcon("HappyBird2.jpg");
        happyBirdLabelPic.setIcon(happyBirdIcon);

        ImageIcon owlStareIcon = new ImageIcon("owlStare.jpg");
        TucanBirdLabelPic.setIcon(owlStareIcon);

        ImageIcon cuteKittyIcon = new ImageIcon("cuteKittyPic.jpg");
        cuteKittyLabelPic.setIcon(cuteKittyIcon);

        //pack();  // Does not work well with set size.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        showCards((playerHandArray)); // This takes the arraylist of card for the player and displays them on the buttons.

        // Will close the program, and add a save to it
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = ConnectionConfiguration.getConnection();
                    Statement statement = connection.createStatement();
                    String LastSave_ = "LastSave";
                    if (connection != null) {
                        String playerHand2 = ArrayListToString.ArrayListToString(playerHandArray);  // transforing each players hand into a String to go into the database table
                        String opponentLeft2 = ArrayListToString.ArrayListToString(opponentLeftArray);
                        String TeammateAccoss2 = ArrayListToString.ArrayListToString(teamMatesHandArray);
                        String opponentRight2 = ArrayListToString.ArrayListToString(checkDeck);
                        int BooksUser2 = PlayerTeamBooks; // Adding the current amount of books each team has.
                        int BooksOpponent2 = ComputerTeamBooks;
                        // above this the data that will go into MySQL

                        try { // The purpose of this is to delete your 'LastSave' for the new one.
                            String  deleteSaveDataSQL = "DELETE FROM spades WHERE NAME = 'LastSave' ";  // MySQL syntax for delete previous one.
                            statement.executeUpdate(deleteSaveDataSQL); // Making it happen
                        } catch (SQLException sqlE) { // Default action if nothing there so things don't crash.
                            System.out.println("There was no 'LastSave' to delete" + sqlE + "\n but there is now.");
                        }

 //spades (Name varchar(12), PlayerHand varchar(40), OpponentLeft varchar(40), TeammateAccoss varchar(40), OpponentRight varchar(40),
//      BooksUser int, BooksOpponent int, ScoreUser int, ScoreOpponent int )";
                        String addSaveDataSQL = "INSERT INTO spades (Name, playerHand, opponentleft, TeammateAccoss, opponentRight, BooksUser, BooksOpponent, ScoreUser, ScoreOpponent )" +
                                    " VALUES (?, ? , ? , ? , ? , ?, ?, NULL, NULL )";
                        // my TODO to add  the total score for games of more the one round.
                            // Creating the SQL statement which will be used to add your data to the table .
                            PreparedStatement psInsert = connection.prepareStatement(addSaveDataSQL); // An insert statement for easier use of the table
                            psInsert.setString(1, "LastSave"); // Matching up data for the nine ? spots (no zero)
                            psInsert.setString(2, playerHand2);
                            psInsert.setString(3, opponentLeft2);
                            psInsert.setString(4, TeammateAccoss2);
                            psInsert.setString(5, opponentRight2);
                            psInsert.setInt(6, BooksUser2);
                            psInsert.setInt(7, BooksOpponent2);
                            psInsert.executeUpdate(); // update and adding it to the statement
                            statement.executeUpdate(addSaveDataSQL);  // update the table.
//spades (Name varchar(12), PlayerHand varchar(40), OpponentLeft varchar(40), TeammateAccoss varchar(40), OpponentRight varchar(40),
//      BooksUser int, BooksOpponent int, ScoreUser int, ScoreOpponent int )";
                    }
                    statement.close(); // closing SQL stuff and helpers
                    connection.close();
                } catch (SQLException sqlE) {
                    System.out.println("Did not save " + sqlE); // This seem to pop up even if it does save?
                    //JOptionPane.showMessageDialog(GameLayout.this, "Error! \n Did not save, \n Sorry. ");
                }
                System.exit(0); // Exiting program
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    connection = ConnectionConfiguration.getConnection();
                    Statement statement = connection.createStatement();
                    String saveName = JOptionPane.showInputDialog("Save game as: " );  // Pops-up and input statement for the user to save the game with name of choice.
                    while (saveName.length() > 12 ) { // Data validation for name saving
                        saveName = JOptionPane.showInputDialog("Must be less then 12 characters. \n Save game as: " );
                    }

                    if (connection != null) {
                        String playerHand2 = ArrayListToString.ArrayListToString(playerHandArray);  // transforing each players hand into a String to go into the database table
                        String opponentLeft2 = ArrayListToString.ArrayListToString(opponentLeftArray);
                        String TeammateAccoss2 = ArrayListToString.ArrayListToString(teamMatesHandArray);
                        String opponentRight2 = ArrayListToString.ArrayListToString(checkDeck);
                        int BooksUser2 = PlayerTeamBooks; // Adding the current amount of books each team has.
                        int BooksOpponent2 = ComputerTeamBooks;
                        // above this the data that will go into MySQL

                        //spades (Name varchar(12), PlayerHand varchar(40), OpponentLeft varchar(40), TeammateAccoss varchar(40), OpponentRight varchar(40),
//      BooksUser int, BooksOpponent int, ScoreUser int, ScoreOpponent int )";
                        String addSaveDataSQL = "INSERT INTO spades (Name, playerHand, opponentleft, TeammateAccoss, opponentRight, BooksUser, BooksOpponent, ScoreUser, ScoreOpponent )" +
                                " VALUES (?, ? , ? , ? , ? , ?, ?, NULL, NULL )";
                        // my TODO to add  the total score for games of more the one round.
                        // Creating the SQL statement which will be used to add your data to the table .
                        PreparedStatement psInsert = connection.prepareStatement(addSaveDataSQL); // An insert statement for easier use of the table
                        psInsert.setString(1, saveName); // Matching up data for the nine ? spots (no zero)
                        psInsert.setString(2, playerHand2);
                        psInsert.setString(3, opponentLeft2);
                        psInsert.setString(4, TeammateAccoss2);
                        psInsert.setString(5, opponentRight2);
                        psInsert.setInt(6, BooksUser2);
                        psInsert.setInt(7, BooksOpponent2);
                        psInsert.executeUpdate(); // update and adding it to the statement
                        statement.executeUpdate(addSaveDataSQL);  // update the table.
//spades (Name varchar(12), PlayerHand varchar(40), OpponentLeft varchar(40), TeammateAccoss varchar(40), OpponentRight varchar(40),
//      BooksUser int, BooksOpponent int, ScoreUser int, ScoreOpponent int )";
                    }
                    statement.close(); // closing SQL stuff and helpers
                    connection.close();
                } catch (SQLException sqlE) {
                    System.out.println("Did not save " + sqlE); // This seem to pop up even if it does save?
                    //JOptionPane.showMessageDialog(GameLayout.this, "Error! \n Did not save, \n Sorry. ");
                }
            }
        });

        loadLastSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                        try {
                            // tranformed a good amount of this code from https://github.com/minneapolis-edu/DogSQL/blob/master/src/main/java/com/company/DogDB.java
                            connection = ConnectionConfiguration.getConnection(); // getting connection
                            Statement statement = connection.createStatement(); // using prepared statements for easier work with MySQL syntax


                            // By selecting all it just seems to load the last one, which would be whatever was saved last.
                            String fetchAllDataSQL = "SELECT * FROM spades";  // Getting the saved data.
                            ResultSet resultSet = statement.executeQuery(fetchAllDataSQL); // puting it in a resultSet
                            //testingLabel.setText(resultSet.toString());

//playerHandArray, ArrayList opponentLeftArray, ArrayList teamMatesHandArray, ArrayList checkDeck
                            while (resultSet.next()) {
                                String name = resultSet.getString("Name");
                                String pHand = resultSet.getString("PlayerHand"); // Getting saved arraylist of the players hand.
                                playerHandArray.clear(); // clearing current arraylist to make room for the saved one.
                                playerHandArray.addAll( ArrayListToString.StringToArraylist(pHand)); // Adding all the saved cards to the arraylist for functionality.
                                showCards(playerHandArray); // Showing your saved data's hand

                                String oLHand = resultSet.getString("OpponentLeft"); // Getting other computer player cards in a simple string
                                opponentLeftArray.clear(); // Clearing the current list of cards in the arraylist
                                opponentLeftArray.addAll(ArrayListToString.StringToArraylist(oLHand)); // Making the String into an arraylist of cards for the computer from saved data

                                String tMAHand = resultSet.getString("TeammateAccoss");
                                teamMatesHandArray.clear();
                                teamMatesHandArray.addAll(ArrayListToString.StringToArraylist(tMAHand));

                                String oRHand = resultSet.getString("OpponentRight");
                                checkDeck.clear();
                                checkDeck.addAll(ArrayListToString.StringToArraylist(oRHand));

                                int booksUserInt = resultSet.getInt("BooksUser"); // Getting how many books each team has from saved MySQL data
                                PlayerTeamBooks = booksUserInt; // Putting it as current books.
                                int booksOpponentInt = resultSet.getInt("BooksOpponent");
                                ComputerTeamBooks = booksOpponentInt;
                                //TODO add score

                            }

                            resultSet.close(); // Closing time. Time for you to go out go out into the world.
                            statement.close(); // Closing time. Turn the lights up over every boy and every girl.
                            connection.close();; // Closing time. One last call for alcohol so finish your whiskey or beer.
                            // Closing time. You don't have to go home but you can't stay here.
                        } catch (SQLException sqlE) {
                            System.out.println("Did not load " + sqlE); // This seem to pop up even if it does save?
                            JOptionPane.showMessageDialog(GameLayout.this, "Error! \n Did not load, \n Sorry. ");
                        }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                try {
                    // tranformed a good amount of this code from https://github.com/minneapolis-edu/DogSQL/blob/master/src/main/java/com/company/DogDB.java
                    connection = ConnectionConfiguration.getConnection(); // getting connection
                    Statement statement = connection.createStatement(); // using prepared statements for easier work with MySQL syntax
                    String fetchAllDataSQL = "SELECT * FROM spades";  // Getting the saved data.
                    ResultSet resultSet = statement.executeQuery(fetchAllDataSQL); // puting it in a resultSet
                    ArrayList<String> namesList = new ArrayList(); // This arraylist is to be filled up with all the options that you saved from the previous game
                    while (resultSet.next()) { // ResultSet being active
                        String loadNames = resultSet.getString("Name"); // Get the name of a save
                        namesList.add(loadNames); // Adding the name of save to my arraylist to be shown in hte load screen
                    }
                    ArrayListOfSaveNames.setNamesFromSaved(namesList); // Setting it in a method for easier transforing
                    statement.close(); // CLosing connection.
                    connection.close();
                    Load getRequestedLoad = new Load(GameLayout.this, playerHandArray, opponentLeftArray, teamMatesHandArray, checkDeck );
                    // Above data is for going to the separate load screen

                } catch (SQLException sqlE) {
                    System.out.println("Did not load " + sqlE); // This seem to pop up even if it does save?
                    JOptionPane.showMessageDialog(GameLayout.this, "Error! \n Did not load, \n Sorry. ");
                }
            }
        });
        // this checks if you need to load something other it will skip right by it.
        if (ArrayListOfSaveNames.getNameLoadMePleaseFromLoadScreen().length() > 0) {
            recieveLoadReqest(playerHandArray,opponentLeftArray, teamMatesHandArray, checkDeck);
        }

        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Options pickSelectedOptions = new Options(GameLayout.this);
            }
        });

        // my TODO I know these buttons should have more methods they call on instead of the whole program with in them.
        cardButton1Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(0); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;  // This takes the card picked and allows the computer to run their turn
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard); // The computer figures out what to play with the computer turn program
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed)); // Finding the image Icon for what the computer wants to play.
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent); // Displaying it.
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();  // Creates something to run WhoWinsRound
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed); // Compares everyones cards they played.
                    if (PointForBook == true) { // Adding the points for human and teammate.
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  ); // Displays books for each team after this round.
                    } else {
                        ComputerTeamBooks++; // Adding porints for computer opponent.
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                   putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton1Image.setIcon(null); // To clear the button once the card is used.
            }
        });

        cardButton2Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(1); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton2Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton3Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(2); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton3Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton4Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(3); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton4Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton5Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(4); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton5Image.setIcon(null); // To clear the button once the card is used.

            }
        });
        cardButton6Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(5); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton6Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton7Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(6); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton7Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton8Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(7); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton8Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton9Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(8); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton9Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton10Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(9); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton10Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton11Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(10); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton11Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton12Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(11); // This needs to line up with the button number.
                    cardPicked = card1.toString();
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
                    //oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }

                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton12Image.setIcon(null); // To clear the button once the card is used.

            }
        });

        cardButton13Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // When clicked will throw the current card it holds forward, or the card it played
                try {
                    //Object card1 =  buttonsToCardValueHashMap.get(0);
                    Object card1 = playerHandArray.get(12); // This needs to line up with the button number.
                    cardPicked = card1.toString();

                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.

                     //   String pickedCard = playerTurn(cardPicked, playerHandArray); // cardpicked is the button they clicked on.
                        // Players turn
                    String pickedCard = cardPicked;
                    String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
                    ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
                    putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
//                    leftOpponentPlayed = suitValue(leftOpponentPlayed) + " of " + cardValue(leftOpponentPlayed) ;  // Was used for trouble shooting
//                    oppoentleftTextLabel.setText(leftOpponentPlayed);

                    String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
                    ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
                    putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);

                    String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
                    ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
                    putRigthOfUserCardsHere.setIcon(displayRigthOppoent);

                    WhoWinsRound thisRound = new WhoWinsRound();
                    boolean PointForBook = thisRound.getPlayerTeamWinners(pickedCard, teamMatePlayed, leftOpponentPlayed, rightOpponentPlayed);
                    if (PointForBook == true) {
                        PlayerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    } else {
                        ComputerTeamBooks++;
                        BooksPerTeam.setText("Your Team: \n " + PlayerTeamBooks +"\n \n Oppoents Team: \n" + ComputerTeamBooks  );
                    }


                }catch (NullPointerException nfe) {
                    putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton13Image.setIcon(null); // To clear the button once the card is used.

            }
        });

    }

    public static String computerTurn(ArrayList computersHand, String pickedCard) { // Looking at computers hand and comparing it to card picked so it matches suit.

        try {
            // make sure it matches the suit.
            String suitPlayed = pickedCard.substring(pickedCard.length() - 1, pickedCard.length());  // This should let us know whatever suit it is.
            //String suitPlayedLowerCase = (suitPlayed); // Making it lowercase for comparison purposes.
            for (int i = 0; i < computersHand.size(); i++) {  // This searching the hand for a matching suit
                String compareMe = computersHand.get(i).toString().toLowerCase();  //
                if (suitPlayed.equalsIgnoreCase(compareMe.substring(compareMe.length() -1))) { // Seeing if they match suits
                    computersHand.remove(i); // removes the card from the deck
                    return compareMe;   // Returning the a card if suits match.
                    // Should I change back to uppercase ???
                }
            }
            String cardComputerWillPlay = computersHand.get(0).toString(); // Default if no match found.
            computersHand.remove(0);
            return cardComputerWillPlay;

        } catch (StringIndexOutOfBoundsException siouR) {
            System.out.println(siouR);
        }


        String cardComputerWillPlay = computersHand.get(0).toString(); // Default if no match found.
        computersHand.remove(0);
        return cardComputerWillPlay;

    }

    public static String playerTurn(String cardPicked, ArrayList playerHandArray) {

        String cardPlayed = cardPicked; // To place the card they picked in this variable
        boolean checkCardInHand = false;  // For  the do-while loop to

        do {
            for (int i = 0; i < playerHandArray.size(); i++) {
                String compareMe = playerHandArray.get(i).toString();
                if (cardPicked.equalsIgnoreCase(compareMe)) {
                    checkCardInHand = true; // Will break the loop if it is a card they have in their hand
                    //playerHandArray.remove(i); // Removing that card from thier hand.
                }
            }

        } while (!checkCardInHand);

        return cardPlayed;
    }

    // Took this from the go fish game because it works so well.
    public static boolean gameOver(ArrayList playerHandArray) { // To end the game if nothing left in hand.
        if (playerHandArray.isEmpty()) {
            return true; // Lets the game know nothing left in the hand
        } else {
            return false; // If you still have cards left will return false to keep playing.
        }
    }


    void showCards(ArrayList playerHandArraylist) {

        for (int i = 0; i < 13; i++) {  // This is for displaying all of the cards in your hand.
            Object card = playerHandArraylist.get(i);  // Getthing the players hand and putting it in a a single card object to be displayed.
            ImageIcon displayMe = new ImageIcon( cardObjectToPicture(card)); // Running it throught the cardObjectToPicture to get ImageIcon
            buttonsToCardValueHashMap.put(cardButtonList.get(i), card);  // Adding to my Hashmap matching buttons and there cards.

            if (displayMe.equals(null)) { // Seeing if its empty
                // Hoping it will show me what went wrong by letting
                cardButtonList.get(i).setText(null);
                cardButtonList.get(i).setIcon(null);
            } else if (displayMe.toString().length() < 4) { // Will display what card it should be with a number and suit, one char each mostlikely.
                String showWhatwentWrong = card.toString(); // Making it a string for display
                cardButtonList.get(i).setText(showWhatwentWrong); // Displaying text
            } else { // Yeah it works
                cardButtonList.get(i).setIcon(displayMe); // Displaying picture for each card.
            }
        }

    }


    // A method that will take in a card number and suit and match it to its picture.
    public static String cardObjectToPicture(Object cardNumberAndSuitLetter) {

        String stringOfCardToWorkWith = cardNumberAndSuitLetter.toString(); // Making the input a string.
        String suitOfCard = stringOfCardToWorkWith.substring(stringOfCardToWorkWith.length() -1, stringOfCardToWorkWith.length()); // Getting the last letter of it which will decide the suit.
        String numberOrRoyalityOfCard = stringOfCardToWorkWith.substring(0,1); // Getting the first char of it which will decide the card value

        String suit = suitValue(suitOfCard); // sent to figure out the suit

        String cardValueis = cardValue(numberOrRoyalityOfCard); // Sent ot figure out the card value.

        String returnMePictureString = suit + cardValueis + ".PNG"; // WIll write which picture to bring up assuming the picture is spelled correctly.
        return returnMePictureString;
    }

    // For figuring out the suit of the card, and returning the suit letter to a suit word in string format
    public static String suitValue(String findSuit) {
        String suit = ""; // Emtpy variable to put it in

        if (findSuit.equalsIgnoreCase("c")) { // The matching game, does the suit match which letter C, D, H, S
            suit = "Club"; // Redefining the variable
        } else if (findSuit.equalsIgnoreCase("d")) {
            suit = "Diamond";
        } else if (findSuit.equalsIgnoreCase("h")) {
            suit = "Heart";
        } else if (findSuit.equalsIgnoreCase("s")) {
            suit = "Spades"; // All jokers will be known as spades
        }
//        else {
//            suit = "e"; // for error!
//        }
        return suit; // Sent that sucker back
    }

    // To help define the card value, why you ask i didn't do it like the suits above, because its good to try and learn things by doing them differently.
    public static String cardValue(String findValue) {
        findValue = findValue.toUpperCase();

        switch (findValue) { // Testing the card to find its value
            case "A":  // Ace
                return "Ace"; // worth only one point, pretty low on the spectrum
            case "2":
                return "Two";
            case "3":
                return "Three";
            case "4":
                return "Four";
            case "5":
                return "Five";
            case "6":
                return "Six";
            case "7":
                return "Seven";
            case "8":
                return "Eight";
            case "9":
                return "Nine";
            case "1":  // Ten would only need 1 because there are no other ones.
                return "Ten";
            case "J": // Jack
                return "Jack";
            case "Q": // Queen
                return "Queen";
            case "K": // King
                return "King"; // Most points you can get for card value
            case "L": // Little Joker
                return "Little";
            case "B": // Big joker
                return "Big";
            default:
                return findValue; // if does not match will return this so hopefull I can find the error.

        }
    }

    // Below is a method that should load the selected save from the "Load" GUI list.
    public void recieveLoadReqest( ArrayList playerHandArray, ArrayList opponentLeftArray, ArrayList teamMatesHandArray, ArrayList checkDeck ) {
        Connection connection = null; // How we gonna' start everything. Having a continuous connection eats up resources.

        try {
        String pickedLoadedName = ArrayListOfSaveNames.getNameLoadMePleaseFromLoadScreen(); // This gets the name from ArrayListOfSaveNames that should have been saved from the load screen
        connection = ConnectionConfiguration.getConnection(); // getting connection
        PreparedStatement findLastSave = connection.prepareStatement("SELECT  * FROM  spades WHERE Name = (?)");  // The SQL string that will hopefully get the selected name and all data with it.
        findLastSave.setString(1, pickedLoadedName); // Telling it what name to get.
        ResultSet resultSetLoad = findLastSave.executeQuery(); // running the Query on the database.
        //testingLabel.setText(resultSet.toString());

//playerHandArray, ArrayList opponentLeftArray, ArrayList teamMatesHandArray, ArrayList checkDeck
        while (resultSetLoad.next()) {
            //String name = resultSetLoad.getString("Name");
            String pHand = resultSetLoad.getString("PlayerHand"); // Getting saved arraylist of the players hand.
            playerHandArray.clear(); // clearing current arraylist to make room for the saved one.
            playerHandArray.addAll(ArrayListToString.StringToArraylist(pHand)); // Adding all the saved cards to the arraylist for functionality.
            showCards(playerHandArray); // Showing your saved data's hand

            String oLHand = resultSetLoad.getString("OpponentLeft"); // Getting other computer player cards in a simple string
            opponentLeftArray.clear(); // Clearing the current list of cards in the arraylist
            opponentLeftArray.addAll(ArrayListToString.StringToArraylist(oLHand)); // Making the String into an arraylist of cards for the computer from saved data

            String tMAHand = resultSetLoad.getString("TeammateAccoss");
            teamMatesHandArray.clear();
            teamMatesHandArray.addAll(ArrayListToString.StringToArraylist(tMAHand));

            String oRHand = resultSetLoad.getString("OpponentRight");
            checkDeck.clear();
            checkDeck.addAll(ArrayListToString.StringToArraylist(oRHand));

            int booksUserInt = resultSetLoad.getInt("BooksUser"); // Getting how many books each team has from saved MySQL data
            PlayerTeamBooks = booksUserInt; // Putting it as current books.
            int booksOpponentInt = resultSetLoad.getInt("BooksOpponent");
            ComputerTeamBooks = booksOpponentInt;
            //TODO add score
            }
            resultSetLoad.close(); // Closing time. Time for you to go out go out into the world.
            //PreparedStatement.CLOSE_CURRENT_RESULT; // Closing time. Turn the lights up over every boy and every girl.
            connection.close();; // Closing time. One last call for alcohol so finish your whiskey or beer.
            // Closing time. You don't have to go home but you can't stay here.

        } catch (SQLException SQLE) {
            System.out.println("Loading data error" + SQLE);

          }

    }


}
