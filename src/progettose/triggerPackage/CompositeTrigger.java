package progettose.triggerPackage;


public class CompositeTrigger implements Trigger{
    private Trigger leftTrigger;
    private Trigger rightTrigger;
    private String logicalOperator;
    private final String type;
    
    public CompositeTrigger(String name, Trigger t1, Trigger t2, String s){
        this.leftTrigger = t1;
        this.rightTrigger = t2;
        this.logicalOperator = s;
        this.type = "Composite - "+name;
    }

    public Trigger getLeftTrigger() {
        return leftTrigger;
    }

    public Trigger getRightTrigger() {
        return rightTrigger;
    }
    
    @Override
    public boolean evaluate() {
        if(this.rightTrigger != null){
            if(this.logicalOperator.equals("OR"))
                return this.leftTrigger.evaluate() || this.rightTrigger.evaluate();
            else
                return this.leftTrigger.evaluate() && this.rightTrigger.evaluate();
        }else{
            return !this.leftTrigger.evaluate();
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getToCSV() {
        if(!this.logicalOperator.equals("NOT"))
            return this.leftTrigger.getType()+";"+this.leftTrigger.getToCSV()+";"+this.logicalOperator+";"+this.rightTrigger.getType()+";"+this.rightTrigger.getToCSV();
        else
            return this.leftTrigger.getType()+";"+this.leftTrigger.getToCSV()+";"+this.logicalOperator;
    }
    
    @Override
    public String toString(){
        if(this.rightTrigger == null)
            return "(" + this.logicalOperator + " " + this.leftTrigger.toString() + ")";
        else
            return "(" + this.leftTrigger.toString() + "\n " + this.logicalOperator + "\n " + this.rightTrigger.toString() + ")";
    }
    
}
