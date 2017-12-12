package com.Benjamin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JFrame {
    private JPanel OptionsJPanel;
    private JButton exitButton;
    private JCheckBox addJokersCheckBox;
    private JButton activateChangesButton;


    public Options(final GameLayout parentComponent) {
        this.setLocationRelativeTo(null); // Using this to centerizer the window when it comes up.
        parentComponent.setEnabled(false); // Makes the other one disappear
        setContentPane(OptionsJPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // This button is for the changes in the way the spades program for the way you want to play it.
        activateChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (addJokersCheckBox.isSelected()) { // Checks if jokers checkbox is selected.
                    ArrayListOfSaveNames.setJokersInPlay(true);
                } else {
                    ArrayListOfSaveNames.setJokersInPlay(false);
                }
                // a way to start a new game.
                GameLayout gui = new GameLayout();

                parentComponent.setEnabled(true); // ReEnables the parent component.
                setVisible(false); // Makes the load screen vanish
                Options.this.dispose(); // Disvoles the  Load GUI
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentComponent.setEnabled(true); // ReEnables the parent component.
                setVisible(false); // Makes the load screen vanish
                Options.this.dispose(); // Disvoles the  Load GUI
            }
        });
    }

}
