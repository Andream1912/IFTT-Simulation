package progettose.triggerPackage;

public class CompositeTrigger implements Trigger {

    private Trigger leftTrigger;
    private Trigger rightTrigger;
    private String logicalOperator;
    private final String type;
    private boolean evaluation;
    private boolean changed;

    public CompositeTrigger(String name, Trigger t1, Trigger t2, String s) {
        this.leftTrigger = t1;
        this.rightTrigger = t2;
        this.logicalOperator = s;
        this.type = "Composite - " + name;
        this.evaluation = false;
        this.changed = false;
    }

    public Trigger getLeftTrigger() {
        return leftTrigger;
    }

    public Trigger getRightTrigger() {
        return rightTrigger;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        if (!this.logicalOperator.equals("NOT")) {
            return this.leftTrigger.getType() + ";" + this.leftTrigger.getToCSV() + ";" + this.logicalOperator + ";" + this.rightTrigger.getType() + ";" + this.rightTrigger.getToCSV();
        } else {
            return this.leftTrigger.getType() + ";" + this.leftTrigger.getToCSV() + ";" + this.logicalOperator;
        }
    }

    @Override
    public String toString() {
        if (this.rightTrigger == null) {
            return "(" + this.logicalOperator + " " + this.leftTrigger.toString() + ")";
        } else {
            return "(" + this.leftTrigger.toString() + "\n " + this.logicalOperator + "\n " + this.rightTrigger.toString() + ")";
        }
    }

    /*@Override
    public boolean evaluate() {
        if(this.rightTrigger != null){
            if(this.logicalOperator.equals("OR"))
                return this.leftTrigger.evaluate() || this.rightTrigger.evaluate();
            else
                return this.leftTrigger.evaluate() && this.rightTrigger.evaluate();
        }else{
            return !this.leftTrigger.evaluate();
        }
    }*/
    
    @Override
    public void evaluate() {
        boolean newEvaluation = false;
        if (this.rightTrigger != null) {
            this.leftTrigger.evaluate();
            this.rightTrigger.evaluate();
            if (this.logicalOperator.equals("OR")) {
                newEvaluation = this.leftTrigger.returnEvaluation() || this.rightTrigger.returnEvaluation();
            } else {
                newEvaluation = this.leftTrigger.returnEvaluation() && this.rightTrigger.returnEvaluation();
            }
        } else {
            this.leftTrigger.evaluate();
            newEvaluation = !this.leftTrigger.returnEvaluation();
        }
        this.changed = this.evaluation != newEvaluation;
        this.evaluation = newEvaluation;

    }

    @Override
    public boolean returnEvaluation() {
        if (this.changed) {
            if (this.evaluation) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void reset() {
        this.evaluation = false;
        this.changed = false;
    }
}
