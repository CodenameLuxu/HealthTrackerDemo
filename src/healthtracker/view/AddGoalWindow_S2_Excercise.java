//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.*;
import healthtracker.model.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddGoalWindow_S2_Excercise {

    public AddGoalWindow_S2_Excercise(StorageController s, User user,
            String inType) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String today = localDate.format(formatter);
        System.out.println(today);

        JFrame s2 = new JFrame("Add Goal: " + inType + " target");
        JPanel panel = new JPanel();

        //Target: excercise label
        JLabel targetLabel = new JLabel("Target: Excercise");
        panel.add(targetLabel);
        //Exercise goal drop down box
        ArrayList<String> options = s.getAllExerciseNames();
        final JComboBox excerciselist = 
                new JComboBox(options.toArray(new String[options.size()]));
        excerciselist.setSelectedIndex(1);
        panel.add(excerciselist);

        //Target: duration label
        JLabel durationLabel = new JLabel("Target Duration(minute(s)): ");
        panel.add(durationLabel);
        //Target: Duration input
        final JTextField valueText = new JTextField("60",7);
        valueText.setBounds(10, 10, 20, 20);
        panel.add(valueText);

        JLabel deadlineLabel = new JLabel("Goal Duration(days): ");
        panel.add(deadlineLabel);
        JTextField deadlineText = new JTextField("7", 3);
        panel.add(deadlineText);

        //Add goal button
        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.addActionListener(new actionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate today = LocalDate.now();
                    int goalID = user.getGoals().size() + 1;
                    int targetDuration = Integer.parseInt(valueText.getText());
                    int days = Integer.parseInt(deadlineText.getText());
                    if (days > 0 && targetDuration > 0) {
                        LocalDate deadline = today.plusDays(days);
                        String type
                                = excerciselist.getSelectedItem().toString();
                        GoalController.createGoal(user, goalID, "exercise",
                                type, today, deadline,
                                targetDuration, deadline, 0);
                        s2.dispose();
                        new MainWindow(s, user);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(s2, "Invalid input");
                }
            }
        });
        panel.add(addGoalButton);

        //Button to return to previous page
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddGoalWindow_S1(s, user);
                s2.dispose();
            }
        });
        panel.add(backButton);

        s2.add(panel);
        s2.pack();
        s2.setResizable(false);
        s2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s2.setVisible(true);
    }
}
