//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.model.*;
import healthtracker.controller.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class UpdateWeightWindow extends JFrame {

    public UpdateWeightWindow(StorageController s, User user) {
        final JFrame weightFrame = new JFrame("Update Weight");
        JPanel panel = new JPanel();

        JLabel weightLabel = new JLabel("Weight/KG");
        JTextField weightField = 
                new JTextField(Integer.toString((int) UserController.
                        getWeight(user)), 5);
        panel.add(weightLabel);
        panel.add(weightField);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(0, 0, 80, 25);
        updateButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(weightField.getText());
                    if (weight > 0) {
                        UserController.setWeight(weight, user);
                        System.out.println("Weight: " + UserController.
                                getWeight(user));
                        weightFrame.dispose();
                        new MainWindow(s, user);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(weightFrame, 
                            "Invalid Number");

                }
            }
        });
        panel.add(updateButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(0, 0, 80, 25);
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weightFrame.dispose();
                new MainWindow(s, user);
            }
        });
        panel.add(backButton);

        weightFrame.add(panel);
        weightFrame.setResizable(false);
        weightFrame.pack();
        weightFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        weightFrame.setVisible(true);
    }

}
