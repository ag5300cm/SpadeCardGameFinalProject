package com.Benjamin;

import java.util.ArrayList;

public class ArrayListOfSaveNames { // This is a method to keep an arraylist and string for easier tranfering between all of the different things

    private static ArrayList<String> namesFromSaved = new ArrayList();
    private static String nameLoadMePleaseFromLoadScreen = new String();

    private static Boolean jokersInPlay = false ;

    public static ArrayList<String> getNamesFromSaved() {
        return namesFromSaved;
    }

    public static void setNamesFromSaved(ArrayList<String> namesSaved) {
        namesFromSaved = namesSaved;
    }

    public static String getNameLoadMePleaseFromLoadScreen() {
        return nameLoadMePleaseFromLoadScreen;
    }

    public static void setNameLoadMePleaseFromLoadScreen(String nameFromLoad) {
        nameLoadMePleaseFromLoadScreen = nameFromLoad;
    }

    public static boolean getJokersInPlay() {
        return jokersInPlay;
    }

    public static void setJokersInPlay(boolean jokersChecked) {
        jokersInPlay = jokersChecked;
    }

}
