package com.Benjamin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel oppoentleftTextLabel;
    private JLabel oppoentRightTextLabel;
    private JLabel accossOfUserTextLabel;

    LinkedList<JButton> cardButtonList = new LinkedList<>(); // Making a list of buttons.

    HashMap<JButton, Object> buttonsToCardValueHashMap = new HashMap<>();

    String displayer = new String();
    static String cardPicked = "";

    int PlayerTeamBooks = 0;
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


        for (int i = 0; i < 13; i++) {  // This is for displaying all of the cards in your hand.
            Object card = playerHandArray.get(i);  // Getthing the players hand and putting it in a a single card object to be displayed.
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

        // My TODO Save the program to SQL
        // Will close the program, maybe try and add a save to it???
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exiting program
            }
        });


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

//        while (!gameOver(playerHandArray)) {
//
//            String pickedCard = playerTurn(cardPicked, playerHandArray); // cardpicked is the button they clicked on.
//            // Players turn
//
//            String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
//            ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
//            putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
//
//
//            String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
//            ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
//            putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);
//
//            String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
//            ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
//            putRigthOfUserCardsHere.setIcon(displayRigthOppoent);
//        }

    }



//    public class computerPlays(String getCardPicked)
//
//        String pickedCard = getCardPicked;
//
//
//        String leftOpponentPlayed = computerTurn(opponentLeftArray, pickedCard);
//        ImageIcon displayLeftOppoent = new ImageIcon(cardObjectToPicture(leftOpponentPlayed));
//        putLeftOfUserCardsHere.setIcon(displayLeftOppoent);
//
//
//        String teamMatePlayed = computerTurn(teamMatesHandArray, pickedCard);
//        ImageIcon displayTeamMateCard = new ImageIcon(cardObjectToPicture(teamMatePlayed));
//        putAcrossOfUserCardsHere.setIcon(displayTeamMateCard);
//
//        String rightOpponentPlayed = computerTurn(checkDeck, pickedCard);
//        ImageIcon displayRigthOppoent = new ImageIcon(cardObjectToPicture(rightOpponentPlayed));
//        putRigthOfUserCardsHere.setIcon(displayRigthOppoent);
//    }

    public static String computerTurn(ArrayList computersHand, String pickedCard) {

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
            suit = "Spades";
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
            default:
                return findValue; // if does not match will return this so hopefull I can find the error.

        }
    }



}
