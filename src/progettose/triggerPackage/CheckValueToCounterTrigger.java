package progettose.triggerPackage;

import progettose.counterPackage.CounterList;

public class CheckValueToCounterTrigger implements Trigger{
    
    //Attributes for checkValueToCounterTrigger
    public final String counter;
    public final String checkOperation;
    public final int checkValue;
    public final String type;
    private boolean evaluation;
    private boolean changed;
    
    //Constructor for checkValueToCounterTrigger
    public CheckValueToCounterTrigger(String counter, String checkOperation,int checkValue){
        this.counter = counter;
        this.checkOperation = checkOperation;
        this.checkValue = checkValue;
        this.type= "Compare Counter to Value";
        this.evaluation = false;
        this.changed = false;
    }
    
    //toString method for checkValueToCounterTrigger
    @Override
    public String toString(){
        return "Checking if '" + this.counter + "' " + this.checkOperation + " '" + this.checkValue + "'";
    }
    
    @Override
    public void evaluate(){
        try{
            boolean newEvaluation = false;
            CounterList countList = new CounterList();
            switch(this.checkOperation){
                case "=":
                    newEvaluation = countList.getCounter(this.counter) == this.checkValue;
                    break;
                case ">":
                    newEvaluation = countList.getCounter(this.counter) > this.checkValue;
                    break;
                case ">=":
                    newEvaluation = countList.getCounter(this.counter) >= this.checkValue;
                    break;
                case "<":
                    newEvaluation = countList.getCounter(this.counter) < this.checkValue;
                    break;
                case "<=":
                    newEvaluation = countList.getCounter(this.counter) <= this.checkValue;
                    break;
            }
            this.changed = this.evaluation != newEvaluation;
            this.evaluation = newEvaluation;
        } catch (Exception e){
            System.out.println("Error while checking if '" + this.counter + "' " + this.checkOperation + " '" 
            + this.checkValue + "'" + e.getStackTrace());
        }
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override
    public String getToCSV(){
        return this.counter + ";" + this.checkOperation + ";" + this.checkValue;
    }
    
    @Override 
    public boolean returnEvaluation(){
        if(this.changed)
            if(this.evaluation)
                return true;
        return false;
    }

    @Override
    public void reset() {
       this.evaluation = false;
        this.changed = false;
    }
}
