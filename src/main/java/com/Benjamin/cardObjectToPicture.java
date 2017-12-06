package com.Benjamin;


import javax.swing.*;

//public class cardObjectToPicture {
//
//    String card;
//
//    public String getCard(Object ShowThisCard) {
//        card = setcardObjectToPicture(ShowThisCard);
//        return card;
//    }
//
//    public static String setcardObjectToPicture(Object cardNumberAndSuitLetter) {
//
//        String stringOfCardToWorkWith = cardNumberAndSuitLetter.toString();
//        String suitOfCard = stringOfCardToWorkWith.substring(1);
//        String numberOrRoyalityOfCard = stringOfCardToWorkWith.substring(0,1);
//
//        String suit = suitValue(suitOfCard);
//
//        String cardValueis = cardValue(numberOrRoyalityOfCard);
//
//        String returnMePictureString = suit + cardValueis + ".PNG";
//        return returnMePictureString;
//    }
//
//    public static String suitValue(String findSuit) {
//        String suit = "";
//
//        if (findSuit.equalsIgnoreCase("C")) {
//            suit = "Club";
//        } else if (findSuit.equalsIgnoreCase("D")) {
//            suit = "Diamond";
//        } else if (findSuit.equalsIgnoreCase("H")) {
//            suit = "Heart";
//        } else if (findSuit.equalsIgnoreCase("S")) {
//            suit = "Spades";
//        }
//        return suit;
//    }
//
//    public static String cardValue(String findValue) {
//
//        switch (findValue) { // Testing the card to find its value
//            case "A":  // Ace
//                return "Ace"; // worth only one point, pretty low on the spectrum
//            case "2":
//                return "Two";
//            case "3":
//                return "Three";
//            case "4":
//                return "Four";
//            case "5":
//                return "Five";
//            case "6":
//                return "Six";
//            case "7":
//                return "Seven";
//            case "8":
//                return "Eight";
//            case "9":
//                return "Nine";
//            case "1":  // Ten would only need 1 because there are no other ones.
//                return "Ten";
//            case "J": // Jack
//                return "Jack";
//            case "Q": // Queen
//                return "Queen";
//            case "K": // King
//                return "King"; // Most points you can get for card value
//            default:
//                return "";
//
//        }
//    }
//
//
//
//
//
//
//    // All the club card and their matching picture.
//    ImageIcon ClubAce = new ImageIcon("ClubAce.png");
//    ImageIcon ClubTwo = new ImageIcon("ClubTwo.png");
//    ImageIcon ClubThree = new ImageIcon("ClubThree.png");
//    ImageIcon ClubFour = new ImageIcon("ClubFour.png");
//    ImageIcon ClubFive = new ImageIcon("ClubFive.png");
//    ImageIcon ClubSix = new ImageIcon("ClubSix.png");
//    ImageIcon ClubSeven = new ImageIcon("ClubSeven.png");
//    ImageIcon ClubEight = new ImageIcon("ClubEight.png");
//    ImageIcon ClubNine = new ImageIcon("ClubNine.png");
//    ImageIcon ClubTen = new ImageIcon("ClubTen.png");
//    ImageIcon ClubJack = new ImageIcon("ClubJack.png");
//    ImageIcon ClubQueen = new ImageIcon("ClubQueen.png");
//    ImageIcon ClubKing = new ImageIcon("ClubKing.png");




//
//// All the club card and their matching picture.
//        ImageIcon ClubAce = new ImageIcon("ClubAce.png"); // These might not be needed.
//                ImageIcon ClubTwo = new ImageIcon("ClubTwo.png");
//                ImageIcon ClubThree = new ImageIcon("ClubThree.png");
//                ImageIcon ClubFour = new ImageIcon("ClubFour.png");
//                ImageIcon ClubFive = new ImageIcon("ClubFive.png");
//                ImageIcon ClubSix = new ImageIcon("ClubSix.png");
//                ImageIcon ClubSeven = new ImageIcon("ClubSeven.png");
//                ImageIcon ClubEight = new ImageIcon("ClubEight.png");
//                ImageIcon ClubNine = new ImageIcon("ClubNine.png");
//                ImageIcon ClubTen = new ImageIcon("ClubTen.png");
//                ImageIcon ClubJack = new ImageIcon("ClubJack.png");
//                ImageIcon ClubQueen = new ImageIcon("ClubQueen.png");
//                ImageIcon ClubKing = new ImageIcon("ClubKing.png");
//
//                // All the Diamond card and their matching picture.
//                ImageIcon DiamondAce = new ImageIcon("DiamondAce.png");
//                ImageIcon DiamondTwo = new ImageIcon("DiamondTwo.png");
//                ImageIcon DiamondThree = new ImageIcon("DiamondThree.png");
//                ImageIcon DiamondFour = new ImageIcon("DiamondFour.png");
//                ImageIcon DiamondFive = new ImageIcon("DiamondFive.png");
//                ImageIcon DiamondSix = new ImageIcon("DiamondSix.png");
//                ImageIcon DiamondSeven = new ImageIcon("DiamondSeven.png");
//                ImageIcon DiamondEight = new ImageIcon("DiamondEight.png");
//                ImageIcon DiamondNine = new ImageIcon("DiamondNine.png");
//                ImageIcon DiamondTen = new ImageIcon("DiamondTen.png");
//                ImageIcon DiamondJack = new ImageIcon("DiamondJack.png");
//                ImageIcon DiamondQueen = new ImageIcon("DiamondQueen.png");
//                ImageIcon DiamondKing = new ImageIcon("DiamondKing.png");
//
//                // All the Heart card and their matching picture.
//                ImageIcon HeartAce = new ImageIcon("HeartAce.png");
//                ImageIcon HeartTwo = new ImageIcon("HeartTwo.png");
//                ImageIcon HeartThree = new ImageIcon("HeartThree.png");
//                ImageIcon HeartFour = new ImageIcon("HeartFour.png");
//                ImageIcon HeartFive = new ImageIcon("HeartFive.png");
//                ImageIcon HeartSix = new ImageIcon("HeartSix.png");
//                ImageIcon HeartSeven = new ImageIcon("HeartSeven.png");
//                ImageIcon HeartEight = new ImageIcon("HeartEight.png");
//                ImageIcon HeartNine = new ImageIcon("HeartNine.png");
//                ImageIcon HeartTen = new ImageIcon("HeartTen.png");
//                ImageIcon HeartJack = new ImageIcon("HeartJack.png");
//                ImageIcon HeartQueen = new ImageIcon("HeartQueen.png");
//                ImageIcon HeartKing = new ImageIcon("HeartKing.png");
//
//                // All the Spades card and their matching picture.
//                ImageIcon SpadesAce = new ImageIcon("SpadesAce.png");
//                ImageIcon SpadesTwo = new ImageIcon("SpadesTwo.png");
//                ImageIcon SpadesThree = new ImageIcon("SpadesThree.png");
//                ImageIcon SpadesFour = new ImageIcon("SpadesFour.png");
//                ImageIcon SpadesFive = new ImageIcon("SpadesFive.png");
//                ImageIcon SpadesSix = new ImageIcon("SpadesSix.png");
//                ImageIcon SpadesSeven = new ImageIcon("SpadesSeven.png");
//                ImageIcon SpadesEight = new ImageIcon("SpadesEight.png");
//                ImageIcon SpadesNine = new ImageIcon("SpadesNine.png");
//                ImageIcon SpadesTen = new ImageIcon("SpadesTen.png");
//                ImageIcon SpadesJack = new ImageIcon("SpadesJack.png");
//                ImageIcon SpadesQueen = new ImageIcon("SpadesQueen.png");
//                ImageIcon SpadesKing = new ImageIcon("SpadesKing.png");
//
//                ImageIcon JokerBig = new ImageIcon("JokerBig.PND");
//                ImageIcon JokerSmall = new ImageIcon("JokerLittle.PNG");
////
////}
////


