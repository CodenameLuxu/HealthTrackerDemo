//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.controller;

import healthtracker.JSON.JSONUtils;
import healthtracker.model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FoodController {

    //The date formatter
    private static DateTimeFormatter dtf
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Returns an empty Fooditem object
    public static FoodItem newFoodItem() {
        return new FoodItem();
    }

    public static String getFoodName(FoodItem thisConsumable) {
        return thisConsumable.getFoodName();
    }

    public static void setFoodName(String foodName, FoodItem thisFoodItem) {
        thisFoodItem.setFoodName(foodName);
    }

    public static int getCalories(FoodItem thisFoodItem) {
        return thisFoodItem.getCalories();
    }

    public static void setCalories(int calories, FoodItem thisFoodItem) {
        thisFoodItem.setCalories(calories);
    }

    public static List<FoodItem> getFood(StorageController s) {
        return s.getFood();
    }

    public static List<FoodItem> getDrinks(StorageController s) {
        return s.getDrinks();
    }

    public static FoodItem getFoodByName(StorageController s, String name) {
        for (int i = 0; i < s.getFood().size(); i++) {
            FoodItem currConsumable = s.getFood().get(i);
            if (currConsumable.getFoodName().equals(name)) {
                return currConsumable;
            }
        }
        for (int i = 0; i < s.getDrinks().size(); i++) {
            FoodItem currConsumable = s.getDrinks().get(i);
            if (currConsumable.getFoodName().equals(name)) {
                return currConsumable;
            }
        }
        return null;
    }

    public static String[] getAllFoodNames(StorageController s) {

        String[] foods = new String[s.getFood().size()];
        for (int i = 0; i < s.getFood().size(); i++) {
            foods[i] = s.getFood().get(i).getFoodName();
        }
        return foods;
    }

    public static String[] getAllDrinkNames(StorageController s) {

        String[] drinks = new String[s.getDrinks().size()];
        for (int i = 0; i < s.getDrinks().size(); i++) {
            drinks[i] = s.getDrinks().get(i).getFoodName();
        }
        return drinks;
    }

    public static void readFood(StorageController s, String path,
            String type) {
        List<FoodItem> newList = new ArrayList<>();
        JSONObject obj = JSONUtils.getJSONObjectFromFile(path);
        JSONArray jsonArray = obj.getJSONArray("List");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject newObject = (JSONObject) jsonArray.get(i);
            String name = newObject.getString("foodName");
            int calories = newObject.getInt("calories");

            FoodItem newItem = FoodController.newFoodItem();
            newItem.setFoodName(name);
            newItem.setCalories(calories);
            newList.add(newItem);
        }
        if (type.equals("Food")) {
            s.setFood(newList);
        } else if (type.equals("Drink")) {
            s.setDrinks(newList);
        }
    }

    public static LocalDate getDate(FoodRecord thisFoodRecord) {
        return thisFoodRecord.getDate();
    }

    public static String getDateString(FoodRecord thisFoodRecord) {
        return dtf.format(thisFoodRecord.getDate());
    }

    public static void setDate(LocalDate newDate, FoodRecord thisFoodRecord) {
        thisFoodRecord.setDate(newDate);
    }

    public static FoodItem getFood(FoodRecord thisFoodRecord) {
        return thisFoodRecord.getFoodItem();
    }

    public static void setFood(FoodItem newConsumable,
            FoodRecord thisFoodRecord) {
        thisFoodRecord.setFoodItem(newConsumable);
    }
}
