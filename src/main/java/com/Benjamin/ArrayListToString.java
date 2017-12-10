package com.Benjamin;

import java.util.ArrayList;

// The methods here will be for saving and loading a of game cards
public class ArrayListToString {

    // This method is for condensing the arraylist into a string so I can save it on MySQL
    public static String ArrayListToString(ArrayList Arrayie) { // Taks in Arraylist Arrayie and spits back out a String
        String wholeArrayListString = "" ; // My empty String to add all the arraylist cards variables to

        for (int i = 0; i < Arrayie.size(); i++) { // Going through the Arraylist one at a time.
            String str = Arrayie.get(i).toString(); // Grabing the individual card out of the arraylist and converting to String

            wholeArrayListString += str + ","; // Adding it to my main String variable. All new variables seperated by a comma.

        }

        return wholeArrayListString; // Send that important data back to the requester.
    }

    // TO createa n arraylist out of a saved string
    public static ArrayList StringToArraylist(String savedString) { // Acceptind and saved string, and needs to return an arraylist

        ArrayList<String>  arrayListCards = new ArrayList<>(); // making an empty arraylist that will be returned.

        String basicCards = savedString; // putting string into my string
        String separateCards[] = basicCards.split(","); // seperating my string by the comma's that I added to them when saving

        for (int i = 0; i <13; i++) { // 13 cards in a deck so will add 13 spots if need be.

            if (separateCards[i].isEmpty()) { // If less then 13 cards being loaded this is my default catcher.
                arrayListCards.add(null); // Adding nothing to arraylist if needed.
            } else {
                arrayListCards.add(i, separateCards[i]); // Adding to arraylist from the array of strings
            }
        }
        return arrayListCards; // Returning the saved arraylist.
    }

    // This method is not need I just used .clear() instead
//    public static void cleanOutArraylist(ArrayList emptyThisArraylist) {
//
//        for (int i = 0; i < 13; i++ ) {
//            try {
//                if (emptyThisArraylist.get(i).equals(null)) {
//                    break;
//                } else {
//                    try {
//                        emptyThisArraylist.remove(0);
//                    } catch (IndexOutOfBoundsException ioubE) {
//                        System.out.println("index 7, size 6  " + ioubE);
//                    }
//
//                }
//            } catch (IndexOutOfBoundsException ioubE) {
//                System.out.println("index 7, size 6   " + ioubE);
//            }
//        }
//
//    }

}
