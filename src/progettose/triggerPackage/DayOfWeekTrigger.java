/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.triggerPackage;

import java.time.LocalDate;
import java.time.DayOfWeek;

/**
 *
 * @author manue
 */
public class DayOfWeekTrigger implements Trigger{
    private String day;
    
    public DayOfWeekTrigger(String day) {
        this.day=day; 
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public boolean evaluate() {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        return DayOfWeek.valueOf(day)==currentDay;
    }
    
    @Override
    public String toString() {
        return "On " + day;
    }

    @Override
    public String getType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getToCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
