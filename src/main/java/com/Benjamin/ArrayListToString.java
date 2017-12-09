package com.Benjamin;

import java.util.ArrayList;

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

}
