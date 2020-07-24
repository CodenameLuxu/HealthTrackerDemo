//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.controller;

import healthtracker.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public static List<User> getUsers(StorageController s) {
        return s.getUsers();
    }

    public static boolean createUser(StorageController s, String newUsername,
            String newEmail, String newFirstName, String newLastName,
            double newWeight, double newHeight) {
        User user = getUserByUsername(s, newUsername);
        if (user != null) {
            return false;
        }
        user = getUserByEmail(s, newEmail);
        if (user != null) {
            return false;
        }
        User newUser = new User(newUsername, newEmail, newFirstName,
                newLastName, newWeight, newHeight,
                calculateBMI(newHeight, newWeight));

        if (!isValidEmail(newEmail)) {
            return false;
        }
        if (!isValidHeight(newHeight) || !isValidWeight(newWeight)) {
            return false;
        }
        return s.getUsers().add(newUser);
    }

    //Checks if the height is within an acceptable range
    public static boolean isValidHeight(double height) {
        return height > 0.1 && height < 5;
    }

    //Checks if the weight is a valid number
    public static boolean isValidWeight(double weight) {
        return weight > 1;
    }

    //Checks if the e-mail has a valid domain
    public static boolean isValidEmail(String email) {
        return (email.endsWith("@gmail.com")
                || email.endsWith("@hotmail.co.uk"))
                && !email.contains(" ") && !email.startsWith("@")
                && email.chars().anyMatch(Character::isLetter)
                || email.chars().anyMatch(Character::isDigit);
    }

    public static User getUserByUsername(StorageController s,
            String thisUsername) {
        for (int i = 0; i < s.getUsers().size(); i++) {
            if (s.getUsers().get(i).getUsername().equals(thisUsername)) {
                return s.getUsers().get(i);
            }
        }
        return null;
    }

    public static User getUserByEmail(StorageController s, String thisEmail) {
        for (int i = 0; i < s.getUsers().size(); i++) {
            if (s.getUsers().get(i).getEmail().equals(thisEmail)) {
                return s.getUsers().get(i);
            }
        }
        return null;
    }

    public static String getUsername(User thisUser) {
        return thisUser.getUsername();
    }

    public static void setUsername(String newUsername, User thisUser) {
        thisUser.setUsername(newUsername);
    }

    public static String getEmail(User thisUser) {
        return thisUser.getEmail();
    }

    public static void setEmail(String newEmail, User thisUser) {
        thisUser.setEmail(newEmail);
    }

    //PersonalInfo methods
    public static String getFirstName(User thisUser) {
        return thisUser.getPersonalInfo().getFirstName();
    }

    public static void setFirstName(String newFirst, User thisUser) {
        thisUser.getPersonalInfo().setFirstName(newFirst);
    }

    public static String getLastName(User thisUser) {
        return thisUser.getPersonalInfo().getSurname();
    }

    public static void setLastName(String newSurname, User thisUser) {
        thisUser.getPersonalInfo().setSurname(newSurname);
    }

    public static double getHeight(User thisUser) {
        return thisUser.getPersonalInfo().getHeight();
    }

    public static void setHeight(double newHeight, User thisUser) {
        thisUser.getPersonalInfo().setHeight(newHeight);
        calculateBMI(thisUser.getPersonalInfo().getHeight(),
                thisUser.getPersonalInfo().getWeight());
    }

    public static double getWeight(User thisUser) {
        return thisUser.getPersonalInfo().getWeight();
    }

    public static void setWeight(double newWeight, User thisUser) {
        thisUser.getPersonalInfo().setWeight(newWeight);
        calculateBMI(thisUser.getPersonalInfo().getHeight(),
                thisUser.getPersonalInfo().getWeight());
    }

    public static double getBMI(User thisUser) {
        return thisUser.getPersonalInfo().getBMI();
    }

    public static double calculateBMI(double height, double weight) {
        return weight / (height * height);
    }

    public static double calculateFoodWeightUpdate(User thisUser,
            FoodItem aFoodItem) {
        double caloriesToKg = (aFoodItem.getCalories()
                * 0.0001285714);
        return thisUser.getPersonalInfo().getWeight()
                + caloriesToKg;
    }

    public static double calculateExerciseWeightUpdate(User thisUser,
            ExerciseItem anExerciseItem, int duration) {
        double caloriesToKgLost
                = (ExerciseController.calculateTotalCalories(anExerciseItem,
                        duration) * 0.0001285714);
        return thisUser.getPersonalInfo().getWeight() - caloriesToKgLost;
    }

    //Consumables and Activities class methods
    public static boolean addFood(User thisUser, FoodItem newConsumable) {
        FoodRecord newAte = new FoodRecord(newConsumable);
        thisUser.getPersonalInfo().setWeight(UserController.
                calculateFoodWeightUpdate(thisUser, newConsumable));
        thisUser.getPersonalInfo().setBMI(UserController.
                calculateBMI(thisUser.getPersonalInfo().getHeight(),
                        thisUser.getPersonalInfo().getWeight()));
        return thisUser.getDietHistory().add(newAte);
    }

    public static boolean addExercise(User thisUser,
            ExerciseItem AnExerciseItem, int duration) {
        ExerciseRecord newSession = new ExerciseRecord(AnExerciseItem,
                duration);
        thisUser.getPersonalInfo().setWeight(UserController.
                calculateExerciseWeightUpdate(thisUser, AnExerciseItem,
                        duration));
        thisUser.getPersonalInfo().setBMI(UserController.calculateBMI(thisUser.
                getPersonalInfo().getHeight(),
                thisUser.getPersonalInfo().getWeight()));
        return thisUser.getWorkoutHistory().add(newSession);
    }

    public static List<FoodRecord> getDietHistory(User thisUser) {
        return thisUser.getDietHistory();
    }

    public static List<ExerciseRecord> getWorkOutHistory(User thisUser) {
        return thisUser.getWorkoutHistory();
    }

    public static int getTotalIntake(User user) {
        int total = 0;
        List<FoodRecord> meals = getDietHistory(user);
        for (FoodRecord meal : meals) {
            total += meal.getFoodItem().getCalories();
        }
        return total;
    }

    //Gets calorie intake for a specified day
    public static int getCalorieIntake(User thisUser, LocalDate aDate) {
        int totalCalories = 0;
        List<FoodRecord> aList = getDietHistory(thisUser);
        for (FoodRecord a : aList) {
            if (a.getDate().isEqual(aDate)) {
                totalCalories += a.getFoodItem().getCalories();
            }
        }
        return totalCalories;
    }

    //Gets a total calorie intake between two dates
    public static int getCalorieIntake(User thisUser, LocalDate aDate, 
            LocalDate bDate) {
        int totalCalories = 0;
        List<FoodRecord> aList = getDietHistory(thisUser);
        for (FoodRecord a : aList) {
            if ((a.getDate().isAfter(aDate) && a.getDate().isBefore(bDate))
                    || a.getDate().isEqual(aDate) || 
                    a.getDate().isEqual(bDate)) {
                totalCalories += a.getFoodItem().getCalories();
            }
        }
        return totalCalories;
    }

    //Gets calories burned on a specified day
    public static int getCaloriesBurned(User user, LocalDate date) {
        int total = 0;
        List<ExerciseRecord> excerciseDone = user.getWorkoutHistory();
        for (ExerciseRecord e : excerciseDone) {
            if (e.getDate().isEqual(date)) {
                total += e.getTotalCaloriesBurned();
            }
        }
        return total;
    }

    //Gets total calories burned between two dates
    public static int getCaloriesBurned(User user, LocalDate dateA,
            LocalDate dateB) {
        int total = 0;
        List<ExerciseRecord> excerciseDone = user.getWorkoutHistory();
        for (ExerciseRecord e : excerciseDone) {
            if (e.getDate().isEqual(dateA) || e.getDate().isEqual(dateB)
                    || (e.getDate().isAfter(dateA)
                    && e.getDate().isBefore(dateB))) {
                total += e.getTotalCaloriesBurned();
            }
        }
        return total;
    }

    //Remove a goal from a list if the goal is met or expired
    public static void processGoal(StorageController s, User user) {
        List<Goal> met = new ArrayList();
        List<Goal> fail = new ArrayList();
        for (Goal g : getAllGoals(user)) {
            GoalController.updateDayCount(g);
            //check if goal is met
            if (GoalController.goalIsMet(g, user)) {
                if (g.getGoalName().equals("diet")) {
                    fail.add(g);
                } else {
                    met.add(g);
                }
            }
            //Check if goal expired
            if (GoalController.isExpire(g)) {
                if (g.getGoalName().equals("diet")) {
                    met.add(g);
                } else {
                    fail.add(g);
                }
            }
        }
        for (Goal g : met) {
            getAllGoals(user).remove(g);
            System.out.println(g.toString() + " --> Goal met --> "
                    + "Goal removed");
            updateAchievementToGroup(s, user, g, "Achieved");
        }
        for (Goal g : fail) {
            getAllGoals(user).remove(g);
            System.out.println(g.toString() + " --> Goal Expired --> "
                    + "Goal removed");
            updateAchievementToGroup(s, user, g, "Failed");
        }
        System.out.println("Goals processed");

    }

    public static void updateAchievementToGroup(StorageController s, 
            User user, Goal goal, String status) {
        System.out.println();
        for (Group group : s.getGroups()) {
            System.out.println("Adding " + goal.toString() + " to " + 
                    group.getGroupName());
            GroupController.addToBoard(user, goal, group, status);
        }
    }

    //Returns a list of user's goals
    public static List<Goal> getAllGoals(User thisUser) {
        return thisUser.getGoals();
    }

    //Removes a specific goal from user's list
    public static boolean removeGoal(User user, Goal thisGoal) {
        return user.getGoals().remove(thisGoal);
    }
}
