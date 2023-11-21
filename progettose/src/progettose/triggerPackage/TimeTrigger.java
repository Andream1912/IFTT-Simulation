/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose.triggerPackage;

import java.time.LocalTime;


/**
 *
 * @author aweyx
 */
public class TimeTrigger implements Trigger {

    private LocalTime time;

    public TimeTrigger(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return this.time;
    }

    @Override
    public void evaluate() {
        System.out.println("Evaluate not yet available");
    }
}
