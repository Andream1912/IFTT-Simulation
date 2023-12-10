package progettose.triggerPackage;

// Represents a composite trigger that combines two triggers using a logical operator.
public class CompositeTrigger implements Trigger {

    // The left and right triggers to be combined.
    private Trigger leftTrigger;
    private Trigger rightTrigger;

    // The logical operator used for combining triggers.
    private String logicalOperator;

    // The type of the composite trigger.
    private final String type;

    // Constructor to create a composite trigger with given name, left trigger, right trigger, and logical operator.
    public CompositeTrigger(String name, Trigger t1, Trigger t2, String s) {
        this.leftTrigger = t1;
        this.rightTrigger = t2;
        this.logicalOperator = s;
        this.type = "Composite - " + name;
    }

    // Getter for the left trigger.
    public Trigger getLeftTrigger() {
        return leftTrigger;
    }

    // Getter for the right trigger.
    public Trigger getRightTrigger() {
        return rightTrigger;
    }

    // Evaluates the composite trigger based on the logical operator.
    @Override
    public boolean evaluate() {
        if (this.rightTrigger != null) {
            if (this.logicalOperator.equals("OR")) {
                return this.leftTrigger.evaluate() || this.rightTrigger.evaluate();
            } else {
                return this.leftTrigger.evaluate() && this.rightTrigger.evaluate();
            }
        } else {
            return !this.leftTrigger.evaluate();
        }
    }

    // Getter for the type of the trigger.
    @Override
    public String getType() {
        return this.type;
    }

    // Returns the CSV representation of the composite trigger.
    @Override
    public String getToCSV() {
        if (!this.logicalOperator.equals("NOT")) {
            return this.leftTrigger.getType() + ";" + this.leftTrigger.getToCSV() + ";" + this.logicalOperator + ";" + this.rightTrigger.getType() + ";" + this.rightTrigger.getToCSV();
        } else {
            return this.leftTrigger.getType() + ";" + this.leftTrigger.getToCSV() + ";" + this.logicalOperator;
        }
    }

    // Returns a string representation of the composite trigger.
    @Override
    public String toString() {
        if (this.rightTrigger == null) {
            return "(" + this.logicalOperator + " " + this.leftTrigger.toString() + ")";
        } else {
            return "(" + this.leftTrigger.toString() + "\n " + this.logicalOperator + "\n " + this.rightTrigger.toString() + ")";
        }
    }
}
