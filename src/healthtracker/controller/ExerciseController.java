//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.controller;

import healthtracker.JSON.*;
import healthtracker.model.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

public class ExerciseController {

    private static DateTimeFormatter dtf = 
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Returns empty ExerciseItem object
    public static ExerciseItem newExerciseItem() {
        return new ExerciseItem();
    }

    public static ExerciseItem getExercise(ExerciseRecord thisSession) {
        return thisSession.getExercise();
    }

    public static ExerciseItem getExerciseByName(StorageController s, 
            String exerciseName) {
        for (int i = 0; i < s.getExerciseList().size(); i++) {
            ExerciseItem currExercise = s.getExerciseList().get(i);
            if (currExercise.getExerciseName().equals(exerciseName)) {
                return currExercise;
            }
        }
        return null;
    }

    public static void setExercise(ExerciseItem newExercise, 
            ExerciseRecord thisSession) {
        thisSession.setExercise(newExercise);
    }

    public static int getDuration(ExerciseRecord thisSession) {
        return thisSession.getDuration();
    }

    public static void setDuration(int newDuration, 
            ExerciseRecord thisSession) {
        thisSession.setDuration(newDuration);
    }

    public static int getTotalCaloriesBurned(ExerciseRecord thisSession) {
        return thisSession.getTotalCaloriesBurned();
    }

    public static String getExerciseName(ExerciseItem thisExercise) {
        return thisExercise.getExerciseName();
    }

    public static void setExerciseName(String activityName, 
            ExerciseItem thisActivity) {
        thisActivity.setExerciseName(activityName);
    }

    public static int getCaloriesPerMinute(ExerciseItem thisActivity) {
        return thisActivity.getCaloriesPerMinute();
    }

    public static void setCaloriesPerMinute(int caloriesPerMinute, 
            ExerciseItem thisActivity) {
        thisActivity.setCaloriesPerMinute(caloriesPerMinute);
    }

    public static LocalDate getDate(ExerciseRecord thisSession) {
        return thisSession.getDate();
    }

    //Returns date of a record as a String
    public static String getDateString(ExerciseRecord thisSession) {
        return dtf.format(thisSession.getDate());
    }

    public static void setDate(LocalDate newDate, ExerciseRecord thisSession) {
        thisSession.setDate(newDate);
    }

    public static int calculateTotalCalories(ExerciseItem activity,
            int duration) {
        return ExerciseController.getCaloriesPerMinute(activity) * duration;
    }

    public static List<ExerciseItem> getActivities(StorageController storage) {
        return storage.getExerciseList();
    }

    public static void readExercises(StorageController s, String path) {
        List<ExerciseItem> newList = new ArrayList<>();
        JSONObject obj = JSONUtils.getJSONObjectFromFile(path);
        JSONArray jsonArray = obj.getJSONArray("List");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject a = (JSONObject) jsonArray.get(i);
            String name = a.getString("activityName");
            int calories = a.getInt("caloriesPerMinute");

            ExerciseItem newItem = ExerciseController.newExerciseItem();
            newItem.setExerciseName(name);
            newItem.setCaloriesPerMinute(calories);
            newList.add(newItem);
        }
        s.setExcerciseList(newList);
    }
}
