//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.model.*;
import healthtracker.controller.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddGoalWindow_S1 extends JFrame {

    public AddGoalWindow_S1(StorageController s, User user) {
        String[] options_type = {"diet", "exercise", "weight", "bmi"};

        final JFrame s1 = new JFrame("Add Goal: S1: Type");
        JPanel panel = new JPanel();

        JLabel label_Type = new JLabel("Type: ");
        panel.add(label_Type);

        final JComboBox dropDownBox = new JComboBox(options_type);
        dropDownBox.setSelectedIndex(0);
        panel.add(dropDownBox);

        //Next button (submits the information)
        JButton addAteButton = new JButton("Next");
        addAteButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = dropDownBox.getSelectedItem().toString();
                if (type.equals("exercise")) {
                    new AddGoalWindow_S2_Excercise(s, user, type);
                } else if (type.equals("diet")) {
                    new AddGoalWindow_S2_Diet(s, user, type);
                } else {
                    new AddGoalWindow_S2_General(s, user, type);
                }
                s1.dispose();
            }
        });
        panel.add(addAteButton);

        //Button to return to previous page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainWindow(s, user);
                s1.dispose();
            }
        });
        panel.add(backButton);

        s1.add(panel);
        s1.pack();
        s1.setResizable(false);
        s1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s1.setSize(320, 200);
        s1.setVisible(true);
    }
}
