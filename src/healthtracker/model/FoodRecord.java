//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FoodRecord implements Serializable {

    static final long serialVersionUID = 110;

    transient DateTimeFormatter dtf
            = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private LocalDate date;
    private FoodItem foodItem;

    public FoodRecord(FoodItem newFood) {
        date = LocalDate.now();
        foodItem = newFood;
    }

    public LocalDate getDate() {
        return date;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setDate(LocalDate thisDate) {
        date = thisDate;
    }

    public void setFoodItem(FoodItem newFoodItem) {
        foodItem = newFoodItem;
    }

    //Prints out the date and food name of a food record
    @Override
    public String toString() {
        return (date + " |" + foodItem.getFoodName());
    }
}
