package progettose.triggerPackage;

// Interface representing a trigger.
public interface Trigger {

    // Evaluates whether the trigger condition is met.
    boolean evaluate();

    // Returns the type of the trigger.
    String getType();

    // Returns a CSV representation of the trigger.
    String getToCSV();
}
