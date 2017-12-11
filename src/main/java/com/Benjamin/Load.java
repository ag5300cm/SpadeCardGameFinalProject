package com.Benjamin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Load extends JFrame { // Much of this was learned from and used from Java2545 Examples
    private JPanel loadFormPanel;
    private JList LoadList;
    private JButton loadButton;
    private JButton deleteButton;
    private JButton cancelButton;

    DefaultListModel<String> listModel; //

    ArrayList<String> nameForLoad = ArrayListOfSaveNames.getNamesFromSaved();


    public Load(final GameLayout parentComponent, ArrayList playerHandArray, ArrayList opponentLeftArray, ArrayList teamMatesHandArray, ArrayList checkDeck ) {
        parentComponent.setEnabled(false);
        setContentPane(loadFormPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<String>();

        //nameForLoad.addAll(findLoadNameData(ArrayList names));

        for (int i = 0; i < nameForLoad.size(); i++) {
            listModel.addElement(nameForLoad.get(i));
        }



        LoadList.setModel(listModel);

        LoadList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        getRootPane().setDefaultButton(loadButton);


      loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = LoadList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String nameLoadMePlease = listModel.get(selectedIndex);
                    //  send data back to sql
                    ArrayListOfSaveNames.setNameLoadMePleaseFromLoadScreen(nameLoadMePlease);
                    parentComponent.setEnabled(true);
                    parentComponent.recieveLoadReqest(playerHandArray, opponentLeftArray, teamMatesHandArray, checkDeck   );
                    setVisible(false);
                    Load.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(LoadList , "Please select an item to delete");
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = LoadList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                    // TODO send data back to sql to delete.

                } else {
                    JOptionPane.showMessageDialog(LoadList , "Please select an item to delete");
                }

            }
        });

        //TODO crash/freeze here after action?
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.exit(0);  // Nope closes everything down
                //Load.this.EXIT_ON_CLOSE;
                parentComponent.setEnabled(true);
                setVisible(false);
                Load.this.dispose();
                //return GameLayout;
            }
        });
    }

    public void findLoadNameData (ArrayList nameForLoad) {
             nameForLoad = nameForLoad;
    }
}
