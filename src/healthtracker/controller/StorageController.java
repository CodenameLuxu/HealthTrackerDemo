//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.controller;

import healthtracker.model.*;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StorageController implements Serializable {

    static final long serialVersionUID = 101;

    private List<User> users;
    private List<Group> groups;
    private List<FoodItem> food;
    private List<FoodItem> drinks;
    private List<ExerciseItem> exerciseList;

    public List<FoodItem> getFood() {
        return food;
    }

    public List<FoodItem> getDrinks() {
        return drinks;
    }

    public void setFood(List<FoodItem> newFood) {
        food = newFood;
    }

    public void setDrinks(List<FoodItem> newDrinks) {
        drinks = newDrinks;
    }

    public List<ExerciseItem> getExerciseList() {
        return exerciseList;
    }

    public void setExcerciseList(List<ExerciseItem> newList) {
        exerciseList = newList;
    }

    public StorageController() throws FileNotFoundException {
        users = new ArrayList<>();
        exerciseList = new ArrayList<>();
        food = new ArrayList<>();
        drinks = new ArrayList<>();
        groups = new ArrayList();
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void setUsers(List<User> inUsers) {
        users = inUsers;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public void setGroups(List<Group> inGroups) {
        groups = inGroups;
    }

    //Returns a user with a matching username
    public User getByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            User currUser = users.get(i);
            if (currUser.getUsername().equals(username)) {
                return currUser;
            }
        }
        return null;
    }

    public ArrayList<String> getAllExerciseNames() {
        ArrayList<String> names = new ArrayList();
        for (int i = 0; i < getExerciseList().size(); i++) {
            names.add(getExerciseList().get(i).getExerciseName());
        }
        return names;
    }
}
