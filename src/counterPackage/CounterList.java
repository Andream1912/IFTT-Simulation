package counterPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CounterList {

    private final String filePath = System.getProperty("user.dir") + "/counter.csv";
    private HashMap<String, Integer> hashCount;

    public CounterList() {
        // Constructor initializes the HashMap for storing key-value pairs.
        this.hashCount = new HashMap<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.loadFromFile();
        } catch (IOException ex) {
            Logger.getLogger(CounterList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void increment(String key) {
        // Increments the counter for the specified key.
        hashCount.put(key, hashCount.getOrDefault(key, 0) + 1);
        saveToFile();
    }

    public int getCount(String key) {
        // Returns the count for the specified key.
        return hashCount.getOrDefault(key, 0);
    }

    public HashMap<String, Integer> getHashMap() {
        // Returns the entire HashMap.
        return hashCount;
    }

    public void addValue(String key, int value) {
        // Adds a new key-value pair to the HashMap.
        // If the key already exists, updates the value.
        hashCount.put(key, value);
        saveToFile();
    }

    public void setValue(String key, int value) {
        // Sets the specified value for the given key.
        hashCount.put(key, value);
        saveToFile();
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (HashMap.Entry<String, Integer> entry : hashCount.entrySet()) {
                writer.println(entry.getKey() + ";" + entry.getValue());
            }
        } catch (IOException ex) {
            Logger.getLogger(CounterList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2) {
                    hashCount.put(parts[0], Integer.valueOf(parts[1]));
                }
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(CounterList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
