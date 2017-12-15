package com.Benjamin;

import java.util.ArrayList;

public class ArrayListOfSaveNames { // This is a method to keep an arraylist and string for easier tranfering between all of the different things

    private static ArrayList<String> namesFromSaved = new ArrayList();
    private static String nameLoadMePleaseFromLoadScreen = new String();

    private static Boolean jokersInPlay = false ;

    private static int PlayerTeamScoreTo500 = 0;
    private static int ComputerTeamScoreTo500 = 0;

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

    public static int getPlayerTeamScoreTo500() {
        return PlayerTeamScoreTo500;
    }

    public static void setPlayerTeamScoreTo500(int addToPlayerTeamScore) {
        PlayerTeamScoreTo500 = PlayerTeamScoreTo500 + addToPlayerTeamScore;
    }

    public static int getComputerTeamScoreTo500() {
        return ComputerTeamScoreTo500;
    }

    public static void setComputerTeamScoreTo500(int addToComputerTeamScore) {
        ComputerTeamScoreTo500 = ComputerTeamScoreTo500 + addToComputerTeamScore;
    }


}
