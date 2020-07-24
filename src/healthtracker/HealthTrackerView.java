//Group 18
//Student numbers: 100174968
//                 100168222
//                 100190648
//                 100094997

package healthtracker;

import healthtracker.view.*;
import healthtracker.controller.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HealthTrackerView {

    public static StorageController s;

    public static void main(String[] args) throws FileNotFoundException {
        JVMShutdownHook jvmSutdownHook = new JVMShutdownHook();
        Runtime.getRuntime().addShutdownHook(jvmSutdownHook);

        s = new StorageController();
        s = loadStorage(s, "storage.ser");

        if (s.getExerciseList().isEmpty()) {
            ExerciseController.readExercises(s, "/assets/jsonExercises.json");
        }
        if (s.getFood().isEmpty() || s.getDrinks().isEmpty()) {
            FoodController.readFood(s, "/assets/jsonFood.json", "Food");
            FoodController.readFood(s, "/assets/jsonDrinks.json", "Drink");
        }

        //new RegistrationWindow(s);
        new LoginWindow(s);
    }

    public static void serializeStorage(StorageController s, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            try (ObjectOutputStream out = new ObjectOutputStream(fos)) {
                out.writeObject(s);
            }
        } catch (IOException e) {
            System.out.println("Saving to byte code ERROR: " + e);
        }
        System.out.println("SUCCESS: The storage is serialized.\n");
    }

    public static StorageController loadStorage(StorageController s, 
            String filename) {
        StorageController newS;
        try {
            FileInputStream fis = new FileInputStream(filename);
            try (ObjectInputStream in = new ObjectInputStream(fis)) {
                newS = (StorageController) in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loading from byte code ERROR: " + e);
            System.out.println("LOAD FAILED");
            return s;
        }
        System.out.println("Loaded storage: " + filename + "\n");
        return newS;
    }

    private static class JVMShutdownHook extends Thread {

        @Override
        public void run() {
            serializeStorage(s, "storage.ser");
        }
    }
}
