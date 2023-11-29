/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.triggerPackage;

import java.time.LocalDate;

/**
 *
 * @author manue
 */
public class DayOfMonthTrigger implements Trigger{
     private int day;
     private String type;
    
    public DayOfMonthTrigger(int day) {
        this.day=day;
        this.type="Day of Month";
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        int currentDay = LocalDate.now().getDayOfMonth();
        return day==currentDay;
    }
    
    @Override
    public String toString() {
        return "On " + day;
    }

    @Override
    public String getType() {
        return this.type;
    }
    
    @Override
    public String getToCSV() {
        return Integer.toString(this.day);
    }
    
}   
