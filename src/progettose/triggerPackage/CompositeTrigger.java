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
    private boolean evaluation;
    private boolean changed;

    // Constructor to create a composite trigger with given name, left trigger, right trigger, and logical operator.
    public CompositeTrigger(String name, Trigger t1, Trigger t2, String s) {
        this.leftTrigger = t1;
        this.rightTrigger = t2;
        this.logicalOperator = s;
        this.type = "Composite - " + name;
        this.evaluation = false;
        this.changed = false;
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
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        if (this.rightTrigger == null) {
            return "(" + this.logicalOperator + " " + this.leftTrigger.toString() + ")";
        } else {
            return "(" + this.leftTrigger.toString() + " " + this.logicalOperator + " " + this.rightTrigger.toString() + ")";
        }
    }

    @Override
    public void evaluate() {
        boolean newEvaluation = false;
        if (this.rightTrigger != null) {
            this.leftTrigger.reset();
            this.rightTrigger.reset();
            this.leftTrigger.evaluate();
            this.rightTrigger.evaluate();
            if (this.logicalOperator.equals("OR")) {
                newEvaluation = this.leftTrigger.returnEvaluation() || this.rightTrigger.returnEvaluation();

            } else {
                newEvaluation = this.leftTrigger.returnEvaluation() && this.rightTrigger.returnEvaluation();
            }
        } else {
            this.leftTrigger.reset();
            this.leftTrigger.evaluate();
            newEvaluation = !this.leftTrigger.returnEvaluation();
        }
        this.changed = this.evaluation != newEvaluation;
        this.evaluation = newEvaluation;

    }

    // Getter for the type of the trigger.
    @Override
    public boolean returnEvaluation() {

        if (this.changed) {
            if (this.evaluation) {
                return true;
            }
        }
        return false;
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
    public void reset() {
        if (this.rightTrigger != null) {
            this.rightTrigger.reset();
        }
        this.leftTrigger.reset();

        this.evaluation = false;
        this.changed = false;
    }
}
