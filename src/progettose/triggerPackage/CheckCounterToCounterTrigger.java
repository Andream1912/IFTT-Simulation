package progettose.triggerPackage;

import progettose.counterPackage.CounterList;

public class CheckCounterToCounterTrigger implements Trigger{
    
    //Attributes for checkCounterToCounterTrigger
    private final String counter1;
    private final String counter2;
    private final String checkOperation;
    private final String type;
    private boolean evaluation;
    private boolean changed;
    
    //Constructor for checkCounterToCounterTrigger
    public CheckCounterToCounterTrigger(String counter1, String counter2, String checkOperation){
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.checkOperation = checkOperation;
        this.type = "Compare Counter to Counter";
        this.evaluation = false;
        this.changed = false;
    }
    
    //toString method for checkCounterToCounterTrigger
    @Override
    public String toString(){
        return "Checking if '" + this.counter1 + "' " + this.checkOperation + " '" + this.counter2 + "'";
    }
    
    @Override
    public void evaluate(){
        try{
            boolean newEvaluation = false;
            CounterList countList = new CounterList();
            switch(this.checkOperation){
                case "=":
                    newEvaluation = countList.getCounter(this.counter1) == countList.getCounter(this.counter2);
                    break;
                case ">":
                    newEvaluation = countList.getCounter(this.counter1) > countList.getCounter(this.counter2);
                    break;
                case ">=":
                    newEvaluation = countList.getCounter(this.counter1) >= countList.getCounter(this.counter2);
                    break;
                case "<":
                    newEvaluation = countList.getCounter(this.counter1) < countList.getCounter(this.counter2);
                    break;
                case "<=":
                    newEvaluation = countList.getCounter(this.counter1) <= countList.getCounter(this.counter2);
                    break;
            }
            this.changed = this.evaluation != newEvaluation;
            this.evaluation = newEvaluation;
        } catch (Exception e){
            System.out.println("Error while checking if '" + this.counter1 + "' " + this.checkOperation + " '" 
                    + this.counter2 + "'" + e.getStackTrace());
        }
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override
    public String getToCSV(){
        return this.counter1 + ";" + this.counter2 + ";" + this.checkOperation;
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
