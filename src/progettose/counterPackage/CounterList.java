package progettose.counterPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

// Manages a counter list, which is a HashMap storing key-value pairs for counting occurrences.
public class CounterList {

    // File path for storing the counter data in a CSV file.
    private final String filePath = System.getProperty("user.dir") + "/counter.csv";
    private HashMap<String, Integer> hashCount; // HashMap to store key-value pairs.

    // Constructor initializes the HashMap and loads data from the file if available.
    public CounterList() {
        this.hashCount = new HashMap<>();
        try {
            // Creates a new file if it doesn't exist and loads data from an existing file.
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.loadFromFile();
        } catch (IOException ex) {
            Logger.getLogger(CounterList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Increments the counter for the specified key and saves changes to the file.
    public void increment(String key) {
        hashCount.put(key, hashCount.getOrDefault(key, 0) + 1);
        saveToFile();
    }

    // Returns the count for the specified key.
    public int getCounter(String key) {
        return hashCount.getOrDefault(key, 0);
    }

    // Returns the entire HashMap.
    public HashMap<String, Integer> getHashMap() {
        return hashCount;
    }

    // Adds a new key-value pair to the HashMap or updates the value if the key already exists.
    public void addCounter(String key, int value) {
        hashCount.put(key, value);
        saveToFile();
    }

    // Removes the specified key and its associated value from the HashMap.
    public void removeCounter(String key) {
        this.hashCount.remove(key);
        saveToFile();
    }

    // Sets the specified value for the given key and saves changes to the file.
    public void setValue(String key, int value) {
        hashCount.put(key, value);
        saveToFile();
    }

    // Updates the key of a key-value pair and saves changes to the file.
    public void updateKeyName(String oldKey, String newKey) {
        if (hashCount.containsKey(oldKey)) {
            int value = hashCount.remove(oldKey);
            hashCount.put(newKey, value);
            saveToFile();
        }
    }

    // Checks if a key exists in the HashMap.
    public boolean checkKey(String key) {
        return hashCount.containsKey(key);
    }

    // Saves the current state of the HashMap to the CSV file.
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (HashMap.Entry<String, Integer> entry : hashCount.entrySet()) {
                writer.println(entry.getKey() + ";" + entry.getValue());
            }
        } catch (IOException ex) {
            Logger.getLogger(CounterList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Loads data from the CSV file into the HashMap.
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
