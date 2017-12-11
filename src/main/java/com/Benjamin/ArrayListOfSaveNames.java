package com.Benjamin;

import java.util.ArrayList;

public class ArrayListOfSaveNames {

    private static ArrayList<String> namesFromSaved = new ArrayList();
    private static String nameLoadMePleaseFromLoadScreen = new String();

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

}
