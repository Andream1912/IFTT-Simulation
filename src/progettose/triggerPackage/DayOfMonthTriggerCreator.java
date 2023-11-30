/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.triggerPackage;

/**
 *
 * @author manue
 */
public class DayOfMonthTriggerCreator extends TriggerCreator{
    private int day;
   
    public DayOfMonthTriggerCreator(int day) {
        this.day = day;
    }

    @Override
    public Trigger createTrigger() {
        return new DayOfMonthTrigger(this.day);
    }
}
