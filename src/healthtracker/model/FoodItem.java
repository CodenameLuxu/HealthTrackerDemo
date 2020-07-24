//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;

public class FoodItem implements Serializable {

    static final long serialVersionUID = 106;

    private String foodName;
    private int calories;

    public String getFoodName() {
        return foodName;
    }

    public int getCalories() {
        return calories;
    }

    public void setFoodName(String thisName) {
        foodName = thisName;
    }

    public void setCalories(int thisCalories) {
        calories = thisCalories;
    }

}
