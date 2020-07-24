//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.GoalController;
import healthtracker.controller.StorageController;
import healthtracker.model.User;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddGoalWindow_S2_General {

    public AddGoalWindow_S2_General(StorageController s, User user,
            String inType) {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String today = localDate.format(formatter);
        System.out.println(today);

        JFrame s2 = new JFrame("Add Goal: " + inType + " target");
        JPanel panel = new JPanel();

        //Target value label
        JLabel targetLabel = new JLabel("Target : " + inType + "  : ");
        panel.add(targetLabel);
        //Target value text field
        final JTextField targetText = new JTextField("60",5);
        if (inType.equals("bmi"))
            targetText.setText("23");
        targetText.setBounds(10, 10, 20, 20);
        panel.add(targetText);

        //Deadline label
        JLabel deadlineLabel = new JLabel("Deadline/days : ");
        panel.add(deadlineLabel);
        //Deadline text field
        final JTextField daysText = new JTextField("7",5);
        panel.add(daysText);

        //Add goal button
        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate today = LocalDate.now();
                    int goalID = user.getGoals().size() + 1;
                    int target = Integer.parseInt(targetText.getText());
                    int days = Integer.parseInt(daysText.getText());
                    LocalDate deadline = today.plusDays(days);

                    GoalController.createGoal(user, goalID, inType,
                            "Value", today, deadline, target, deadline, 0);
                    s2.dispose();
                    new MainWindow(s, user);
                } catch (Exception ex) {
                    System.out.println("Invalid number");
                }
            }
        });
        panel.add(addGoalButton);

        //Button to return to main page
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
