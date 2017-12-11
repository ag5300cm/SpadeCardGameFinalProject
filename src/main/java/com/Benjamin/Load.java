package com.Benjamin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Load extends JFrame { // Much of this was learned from and used from Java2545 Examples the ToDOList delete button and the two-windows
                                        //  https://github.com/minneapolis-edu/Java2545Examples
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

 // If a person picks a game they want to load then
      loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedIndex = LoadList.getSelectedIndex(); // Gets the index slot they selected.
                if (selectedIndex != -1) { //if and item is selected will do the  following
                    String nameLoadMePlease = listModel.get(selectedIndex); // Finding selected item on the list, making it a string
                    //  send data back to sql
                    ArrayListOfSaveNames.setNameLoadMePleaseFromLoadScreen(nameLoadMePlease); // //sets name to a more easier to work with variable
                    parentComponent.setEnabled(true); // Making the  GameLayout Gui active again
                    parentComponent.recieveLoadReqest(playerHandArray, opponentLeftArray, teamMatesHandArray, checkDeck   ); // Sending back this information
                    // Why you ask I did both ArrayListOfSaveNames.setNameLoadMePleaseFromLoadScreen() and the above? Basicly to challenge myself.
                    setVisible(false); // Making the Load GUI Screen disappear.
                    Load.this.dispose(); // Closing down the Load GUI screen.
                } else { // In case something goes wrong
                    JOptionPane.showMessageDialog(LoadList , "Please select an item to load");
                }

            }
        });

      // Delete button to remove unwanted saves from the list.
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;

                int selectedIndex = LoadList.getSelectedIndex();  // Getting the index of the item selected on the list
                if (selectedIndex != -1) { // If and item selected will hopefully do the following.
                    String nameOfDeleteMe = listModel.get(selectedIndex); // Changing it to a string format to match it with the MySQL name slot.
                    if (JOptionPane.showConfirmDialog(deleteButton, "Delete " + selectedIndex + "?", "Delete",
                            JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) { // WIll show a comfiem option dialog button to make sure delete button was not accenditly pressed.
                        listModel.remove(selectedIndex); // Removing the item from the shown list.

                        try { // The purpose of this is to delete your choice from the shown list.
                            connection = ConnectionConfiguration.getConnection(); // connecting to SQL
                            PreparedStatement deleteSaveDataSQL = connection.prepareStatement( "DELETE FROM spades WHERE NAME = (?)");  // MySQL syntax for delete of selected
                            deleteSaveDataSQL.setString(1, nameOfDeleteMe); // adding the name (string) of it to a premade statement
                            deleteSaveDataSQL.executeUpdate(); // Deleting from SQL

                            connection.close(); // Peace out, Yo.

                        } catch (SQLException sqlE) { // Default action if nothing there so things don't crash.
                            System.out.println("There was no " + nameOfDeleteMe + " to delete" + sqlE);
                        }

                    }

                } else { // If button pressed an nothing selected.
                    JOptionPane.showMessageDialog(LoadList , "Please select an item to delete");
                }

            }
        });

        // In case they change their mind
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.exit(0);  // Nope closes everything down
                //Load.this.EXIT_ON_CLOSE;
                parentComponent.setEnabled(true); // ReEnables the parent component.
                setVisible(false); // Makes the load screen vanish
                Load.this.dispose(); // Disvoles the  Load GUI
                //return GameLayout;
            }
        });
    }

    public void findLoadNameData (ArrayList nameForLoad) {
             nameForLoad = nameForLoad;
    }
}
