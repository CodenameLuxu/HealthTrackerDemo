//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.*;
import healthtracker.model.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FoodWindow extends JFrame {

    public FoodWindow(StorageController s, User user) {
        final JFrame addFoodFrame = new JFrame("Add Meal");
        JPanel panel = new JPanel();

        JLabel nameLabel = new JLabel("Name : ");
        panel.add(nameLabel);
        final JComboBox dropDownBox_Food;

        //Creates a list of food and drink names
        final String[] options_Food = FoodController.getAllFoodNames(s);
        final String[] options_Drink = FoodController.getAllDrinkNames(s);
        final List<String> options_all = new ArrayList();

        //Combines the two string arrays into one
        for (String food : options_Food) {
            options_all.add(food);
        }
        for (String drink : options_Drink) {
            options_all.add(drink);
        }

        //Creates an array of food to create a drop down box
        String[] options;
        String[] empty = {""};
        if (options_all.size() < 1) {
            options = empty;
        } else {
            options = options_all.toArray(new String[options_all.size()]);
        }

        dropDownBox_Food = new JComboBox(options);  //Drop down box
        dropDownBox_Food.setSelectedIndex(0);
        panel.add(dropDownBox_Food);

        JButton addFoodButton = new JButton("Add Meal");    //Add meal button
        addFoodButton.setBounds(10, 80, 80, 25);
        addFoodButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chosenFood
                        = (String) dropDownBox_Food.getSelectedItem();
                FoodItem thisFood
                        = FoodController.getFoodByName(s, chosenFood);
                if (UserController.addFood(user, thisFood)) {
                    JOptionPane.showMessageDialog(addFoodFrame,
                            "Meal Added");
                } else {
                    JOptionPane.showMessageDialog(addFoodFrame,
                            "Unable to process chosen consumable item");
                }
            }
        });
        panel.add(addFoodButton);

        //Button to return to main page
        JButton backButton = new JButton("Done");
        backButton.setBounds(10, 80, 80, 25);
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFoodFrame.dispose();
                new MainWindow(s, user);
            }
        });
        panel.add(backButton);

        addFoodFrame.add(panel);
        addFoodFrame.pack();
        addFoodFrame.setResizable(false);
        addFoodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addFoodFrame.setVisible(true);
    }
}
