package progettose.triggerPackage;

import progettose.counterPackage.CounterList;

public class CheckCounterToCounterTrigger implements Trigger{
    
    //Attributes for checkCounterToCounterTrigger
    private final String counter1;
    private final String counter2;
    private final String checkOperation;
    private final String type;
    
    //Constructor for checkCounterToCounterTrigger
    public CheckCounterToCounterTrigger(String counter1, String counter2, String checkOperation){
        this.counter1 = counter1;
        this.counter2 = counter2;
        this.checkOperation = checkOperation;
        this.type = "Compare Counter to Counter";
    }
    
    //toString method for checkCounterToCounterTrigger
    @Override
    public String toString(){
        return "Checking if '" + this.counter1 + "' " + this.checkOperation + " '" + this.counter2 + "'";
    }
    
    @Override
    public boolean evaluate(){
        try{
            CounterList countList = new CounterList();
            switch(this.checkOperation){
                case "=":
                    return countList.getCounter(this.counter1) == countList.getCounter(this.counter2);
                case ">":
                    return countList.getCounter(this.counter1) > countList.getCounter(this.counter2);
                case ">=":
                    return countList.getCounter(this.counter1) >= countList.getCounter(this.counter2);
                case "<":
                    return countList.getCounter(this.counter1) < countList.getCounter(this.counter2);
                case "<=":
                    return countList.getCounter(this.counter1) <= countList.getCounter(this.counter2);
            }
        } catch (Exception e){
            System.out.println("Error while checking if '" + this.counter1 + "' " + this.checkOperation + " '" 
                    + this.counter2 + "'" + e.getStackTrace());
        }
        return false;
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override
    public String getToCSV(){
        return this.counter1 + ";" + this.counter2 + ";" + this.checkOperation;
    }
}
