//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.view;

import healthtracker.controller.*;
import healthtracker.model.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.*;

public class AddGoalWindow_S2_Diet {

    public AddGoalWindow_S2_Diet(StorageController s, User user, String inType) {
        JFrame s2 = new JFrame("Add Goal: S2: Diet ");
        JPanel panel = new JPanel();

        JLabel targetDescriptionLabel = new JLabel("Target Calorie Intake: ");
        panel.add(targetDescriptionLabel);

        JTextField target = new JTextField("2000",8);
        panel.add(target);

        JLabel deadlineLabel = new JLabel("Target(days): ");
        panel.add(deadlineLabel);
        JTextField dayText = new JTextField("7",5);
        panel.add(dayText);

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

        JButton addGoalButton = new JButton("Add goal");
        addGoalButton.addActionListener(new actionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate today = LocalDate.now();
                    int goalID = user.getGoals().size() + 1;
                    int targetDuration = Integer.parseInt(target.getText());
                    int days = Integer.parseInt(dayText.getText());
                    LocalDate deadline = today.plusDays(days);
                    GoalController.createGoal(user, goalID, "diet", "InTake", 
                            today, deadline, targetDuration, deadline, 0);
                    s2.dispose();
                    new MainWindow(s, user);
                } catch (Exception ex) {
                    System.out.println("Invalid number");
                }
            }
        });
        panel.add(addGoalButton);

        s2.add(panel);
        s2.pack();
        s2.setResizable(false);
        s2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s2.setVisible(true);
    }
}
