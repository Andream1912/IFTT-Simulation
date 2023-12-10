package progettose.triggerPackage;

import progettose.counterPackage.CounterList;

public class CheckValueToCounterTrigger implements Trigger{
    
    //Attributes for checkValueToCounterTrigger
    public final String counter;
    public final String checkOperation;
    public final int checkValue;
    public final String type;
    
    //Constructor for checkValueToCounterTrigger
    public CheckValueToCounterTrigger(String counter, String checkOperation,int checkValue){
        this.counter = counter;
        this.checkOperation = checkOperation;
        this.checkValue = checkValue;
        this.type= "Compare Counter to Value";
    }
    
    //toString method for checkValueToCounterTrigger
    @Override
    public String toString(){
        return "Checking if '" + this.counter + "' " + this.checkOperation + " '" + this.checkValue + "'";
    }
    
    @Override
    public boolean evaluate(){
        try{
            CounterList countList = new CounterList();
            switch(this.checkOperation){
                case "=":
                    return countList.getCounter(this.counter) == this.checkValue;     
                case ">":
                    return countList.getCounter(this.counter) > this.checkValue;
                case ">=":
                    return countList.getCounter(this.counter) >= this.checkValue;
                case "<":
                    return countList.getCounter(this.counter) < this.checkValue;
                case "<=":
                    return countList.getCounter(this.counter) <= this.checkValue;
            }
        } catch (Exception e){
            System.out.println("Error while checking if '" + this.counter + "' " + this.checkOperation + " '" 
            + this.checkValue + "'" + e.getStackTrace());
        }
        return false;
    }
    
    @Override
    public String getType(){
        return this.type;
    }
    
    @Override
    public String getToCSV(){
        return this.counter + ";" + this.checkOperation + ";" + this.checkValue;
    }
    
}
