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

    LinkedList<JButton> cardButtonList = new LinkedList<>(); // Making a list of buttons.

    HashMap<JButton, Object> buttonsToCardValueHashMap = new HashMap<>();

    static String displayer = new String();





    GameLayout(ArrayList playerHandArray) {

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

        // All the club card and their matching picture.
        ImageIcon ClubAce = new ImageIcon("ClubAce.png"); // These might not be needed.
        ImageIcon ClubTwo = new ImageIcon("ClubTwo.png");
        ImageIcon ClubThree = new ImageIcon("ClubThree.png");
        ImageIcon ClubFour = new ImageIcon("ClubFour.png");
        ImageIcon ClubFive = new ImageIcon("ClubFive.png");
        ImageIcon ClubSix = new ImageIcon("ClubSix.png");
        ImageIcon ClubSeven = new ImageIcon("ClubSeven.png");
        ImageIcon ClubEight = new ImageIcon("ClubEight.png");
        ImageIcon ClubNine = new ImageIcon("ClubNine.png");
        ImageIcon ClubTen = new ImageIcon("ClubTen.png");
        ImageIcon ClubJack = new ImageIcon("ClubJack.png");
        ImageIcon ClubQueen = new ImageIcon("ClubQueen.png");
        ImageIcon ClubKing = new ImageIcon("ClubKing.png");

        // All the Diamond card and their matching picture.
        ImageIcon DiamondAce = new ImageIcon("DiamondAce.png");
        ImageIcon DiamondTwo = new ImageIcon("DiamondTwo.png");
        ImageIcon DiamondThree = new ImageIcon("DiamondThree.png");
        ImageIcon DiamondFour = new ImageIcon("DiamondFour.png");
        ImageIcon DiamondFive = new ImageIcon("DiamondFive.png");
        ImageIcon DiamondSix = new ImageIcon("DiamondSix.png");
        ImageIcon DiamondSeven = new ImageIcon("DiamondSeven.png");
        ImageIcon DiamondEight = new ImageIcon("DiamondEight.png");
        ImageIcon DiamondNine = new ImageIcon("DiamondNine.png");
        ImageIcon DiamondTen = new ImageIcon("DiamondTen.png");
        ImageIcon DiamondJack = new ImageIcon("DiamondJack.png");
        ImageIcon DiamondQueen = new ImageIcon("DiamondQueen.png");
        ImageIcon DiamondKing = new ImageIcon("DiamondKing.png");

        // All the Heart card and their matching picture.
        ImageIcon HeartAce = new ImageIcon("HeartAce.png");
        ImageIcon HeartTwo = new ImageIcon("HeartTwo.png");
        ImageIcon HeartThree = new ImageIcon("HeartThree.png");
        ImageIcon HeartFour = new ImageIcon("HeartFour.png");
        ImageIcon HeartFive = new ImageIcon("HeartFive.png");
        ImageIcon HeartSix = new ImageIcon("HeartSix.png");
        ImageIcon HeartSeven = new ImageIcon("HeartSeven.png");
        ImageIcon HeartEight = new ImageIcon("HeartEight.png");
        ImageIcon HeartNine = new ImageIcon("HeartNine.png");
        ImageIcon HeartTen = new ImageIcon("HeartTen.png");
        ImageIcon HeartJack = new ImageIcon("HeartJack.png");
        ImageIcon HeartQueen = new ImageIcon("HeartQueen.png");
        ImageIcon HeartKing = new ImageIcon("HeartKing.png");

        // All the Spades card and their matching picture.
        ImageIcon SpadesAce = new ImageIcon("SpadesAce.png");
        ImageIcon SpadesTwo = new ImageIcon("SpadesTwo.png");
        ImageIcon SpadesThree = new ImageIcon("SpadesThree.png");
        ImageIcon SpadesFour = new ImageIcon("SpadesFour.png");
        ImageIcon SpadesFive = new ImageIcon("SpadesFive.png");
        ImageIcon SpadesSix = new ImageIcon("SpadesSix.png");
        ImageIcon SpadesSeven = new ImageIcon("SpadesSeven.png");
        ImageIcon SpadesEight = new ImageIcon("SpadesEight.png");
        ImageIcon SpadesNine = new ImageIcon("SpadesNine.png");
        ImageIcon SpadesTen = new ImageIcon("SpadesTen.png");
        ImageIcon SpadesJack = new ImageIcon("SpadesJack.png");
        ImageIcon SpadesQueen = new ImageIcon("SpadesQueen.png");
        ImageIcon SpadesKing = new ImageIcon("SpadesKing.png");

        ImageIcon JokerBig = new ImageIcon("JokerBig.PND");
        ImageIcon JokerSmall = new ImageIcon("JokerLittle.PNG");

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
                    ImageIcon displayMe1 = new ImageIcon(cardObjectToPicture(card1)); // Getting the Image form the card data
                    putYourThrownCard.setIcon(displayMe1); // Displaying the card in the label.
                }catch (NullPointerException nfe) {
                   putYourThrownCard.setText(null); // Set text to null of nothing happens.
                }
                cardButton1Image.setIcon(null); // To clear the button once the card is used.
            }
        });

        cardButton2Image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });





    }


    // A method that will take in a card number and suit and match it to its picture.
    public static String cardObjectToPicture(Object cardNumberAndSuitLetter) {

        String stringOfCardToWorkWith = cardNumberAndSuitLetter.toString(); // Making the input a string.
        String suitOfCard = stringOfCardToWorkWith.substring(stringOfCardToWorkWith.length() -1); // Getting the last letter of it which will decide the suit.
        String numberOrRoyalityOfCard = stringOfCardToWorkWith.substring(0,1); // Getting the first char of it which will decide the card value

        String suit = suitValue(suitOfCard); // sent to figure out the suit

        String cardValueis = cardValue(numberOrRoyalityOfCard); // Sent ot figure out the card value.

        String returnMePictureString = suit + cardValueis + ".PNG"; // WIll write which picture to bring up assuming the picture is spelled correctly.
        return returnMePictureString;
    }

    // For figuring out the suit of the card, and returning the suit letter to a suit word in string format
    public static String suitValue(String findSuit) {
        String suit = ""; // Emtpy variable to put it in

        if (findSuit.equalsIgnoreCase("C")) { // The matching game, does the suit match which letter C, D, H, S
            suit = "Club"; // Redefining the variable
        } else if (findSuit.equalsIgnoreCase("D")) {
            suit = "Diamond";
        } else if (findSuit.equalsIgnoreCase("H")) {
            suit = "Heart";
        } else if (findSuit.equalsIgnoreCase("S")) {
            suit = "Spades";
        } else {
            suit = "e"; // for error!
        }
        return suit; // Sent that sucker back
    }

    // To help define the card value, why you ask i didn't do it like the suits above, because its good to try and learn things by doing them differently.
    public static String cardValue(String findValue) {

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
