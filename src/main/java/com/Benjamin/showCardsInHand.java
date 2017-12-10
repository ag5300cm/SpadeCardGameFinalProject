package com.Benjamin;

import javax.swing.*;
import java.util.ArrayList;
//
//public class showCardsInHand {
//
//
//    void showCards(ArrayList playerHandArraylist) {
//
//        for (int i = 0; i < 13; i++) {  // This is for displaying all of the cards in your hand.
//            Object card = playerHandArray.get(i);  // Getthing the players hand and putting it in a a single card object to be displayed.
//            ImageIcon displayMe = new ImageIcon( cardObjectToPicture(card)); // Running it throught the cardObjectToPicture to get ImageIcon
//            buttonsToCardValueHashMap.put(cardButtonList.get(i), card);  // Adding to my Hashmap matching buttons and there cards.
//
//            if (displayMe.equals(null)) { // Seeing if its empty
//                // Hoping it will show me what went wrong by letting
//                cardButtonList.get(i).setText(null);
//                cardButtonList.get(i).setIcon(null);
//            } else if (displayMe.toString().length() < 4) { // Will display what card it should be with a number and suit, one char each mostlikely.
//                String showWhatwentWrong = card.toString(); // Making it a string for display
//                cardButtonList.get(i).setText(showWhatwentWrong); // Displaying text
//            } else { // Yeah it works
//                cardButtonList.get(i).setIcon(displayMe); // Displaying picture for each card.
//            }
//        }
//
//    }
//
//}
